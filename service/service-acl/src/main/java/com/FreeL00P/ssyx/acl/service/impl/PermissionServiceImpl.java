package com.FreeL00P.ssyx.acl.service.impl;

import com.FreeL00P.ssyx.acl.helper.PermissionHelper;
import com.FreeL00P.ssyx.acl.service.RolePermissionService;
import com.FreeL00P.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.model.acl.Permission;
import com.FreeL00P.ssyx.acl.service.PermissionService;
import com.FreeL00P.ssyx.acl.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author freeloop
* @description 针对表【permission(权限)】的数据库操作Service实现
* @createDate 2023-07-30 09:42:47
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryAllMenu() {
        //获取全部权限数据(CAST(id AS SIGNED)是一种类型转换的语法，用于将字段id的数据类型转换为有符号整数类型)
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));
        return PermissionHelper.bulid(allPermissionList);
    }

    @Override
    public void removeChildById(Long id) {
        List<Long> idList=new ArrayList<>();
        idList.add(id);
        //查询所有子节点的id,并添加到idList
        this.selectChildByPid(id,idList);
        //删除
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveRolePermissionRelationship(Long roleId, Long[] permissionId) {
        //删除用户菜单
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<RolePermission>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(wrapper);
        //设置新的菜单
        //构建数据
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Long id : permissionId) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            rolePermissions.add(rolePermission);
        }
        //保存数据
        rolePermissionService.saveBatch(rolePermissions);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Long roleId) {
        //查询出所有菜单数据
        List<Permission> permissionList = this.list();
        //把数据构建成树行结构
        List<Permission> allPermissions=PermissionHelper.bulid(permissionList);

        //根据roleId查询出角色的菜单id
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId).select(RolePermission::getPermissionId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(wrapper);

        //提取出PermissionId
        List<Long> permissionIds = rolePermissionList.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

        //根据提取的permissionIds在所有菜单数据allPermissionList中查询，并设置状态
        this.setPermissionSelect(allPermissions,permissionIds);
        return allPermissions;
    }

    private void setPermissionSelect(List<Permission> children, List<Long> permissionIds) {
        for (Permission child : children) {
            if (permissionIds.contains(child.getId())){
                child.setSelect(true);
            }
            //递归
            setPermissionSelect(child.getChildren(),permissionIds);
        }

    }

    private void selectChildByPid(Long id, List<Long> idList) {
        //构造查询条件
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<Permission>().eq(Permission::getPid, id).select(Permission::getId);
        List<Permission> children = this.list(wrapper);
        children.forEach((item)->{
            idList.add(item.getId());
            this.selectChildByPid(item.getId(), idList);
        });
    }
}




