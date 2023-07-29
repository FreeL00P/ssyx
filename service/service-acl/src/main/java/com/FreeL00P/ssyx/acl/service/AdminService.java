package com.FreeL00P.ssyx.acl.service;

import com.FreeL00P.ssyx.model.acl.Admin;
import com.FreeL00P.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author freeloop
* @description 针对表【admin(用户表)】的数据库操作Service
* @createDate 2023-07-29 21:44:41
*/
public interface AdminService extends IService<Admin> {

    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}
