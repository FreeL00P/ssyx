package com.FreeL00P.ssyx.acl.service;

import com.FreeL00P.ssyx.model.acl.Role;
import com.FreeL00P.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author freeloop
* @description 针对表【role(角色)】的数据库操作Service
* @createDate 2023-07-29 20:42:17
*/
public interface RoleService extends IService<Role> {

    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Map<String, Object> findRoleByUserId(Long adminId);

    void saveUserRoleRelationShip(Long adminId, Long[] roleId);
}
