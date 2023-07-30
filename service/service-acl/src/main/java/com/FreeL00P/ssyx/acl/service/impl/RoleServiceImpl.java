package com.FreeL00P.ssyx.acl.service.impl;

import com.FreeL00P.ssyx.acl.service.AdminRoleService;
import com.FreeL00P.ssyx.acl.service.AdminService;
import com.FreeL00P.ssyx.acl.service.RoleService;
import com.FreeL00P.ssyx.model.acl.Admin;
import com.FreeL00P.ssyx.model.acl.AdminRole;
import com.FreeL00P.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.model.acl.Role;
import com.FreeL00P.ssyx.acl.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author freeloop
* @description 针对表【role(角色)】的数据库操作Service实现
* @createDate 2023-07-29 20:42:17
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //构造查询条件
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(roleName)){
            wrapper.like(Role::getRoleName,roleName);
        }
        Page<Role> page = this.page(pageParam, wrapper);
        return page;
    }

    @Override
    public Map<String, Object> findRoleByUserId(Long adminId) {
        //查询出所有角色信息
        List<Role> allRolesList = this.list();
        //查询用户拥有的角色id
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId).select(AdminRole::getRoleId);
        List<AdminRole> userRoleList = adminRoleService.list(wrapper);
        List<Long> roleIds = userRoleList.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        //在角色信息allRoleList中筛选出用户拥有的角色
        List<Role> assignRoles=new ArrayList<>();
        for (Role role : allRolesList) {
            if (roleIds.contains(role.getId())){
                assignRoles.add(role);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("assignRoles", assignRoles);
        result.put("allRolesList", allRolesList);
        return result;
    }

    @Override
    public void saveUserRoleRelationShip(Long adminId, Long[] roleId) {
        //删除用户当前分配角色
        adminRoleService.remove(new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getAdminId,adminId));
        //给用户重新分配角色
        List<AdminRole> adminRoles=new ArrayList<>();
        for (Long id : roleId) {
            if (StringUtils.isEmpty(roleId)) continue;
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(id);
            adminRole.setAdminId(adminId);
            adminRoles.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoles);
    }
}




