package com.FreeL00P.ssyx.acl.service;

import com.FreeL00P.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【permission(权限)】的数据库操作Service
* @createDate 2023-07-30 09:42:47
*/
public interface PermissionService extends IService<Permission> {

    List<Permission> queryAllMenu();

    void removeChildById(Long id);
}
