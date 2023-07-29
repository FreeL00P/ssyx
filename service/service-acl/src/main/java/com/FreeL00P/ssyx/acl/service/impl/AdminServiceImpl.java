package com.FreeL00P.ssyx.acl.service.impl;

import com.FreeL00P.ssyx.acl.mapper.AdminMapper;
import com.FreeL00P.ssyx.acl.service.AdminService;
import com.FreeL00P.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.model.acl.Admin;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author freeloop
* @description 针对表【admin(用户表)】的数据库操作Service实现
* @createDate 2023-07-29 21:44:41
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
//获取用户名称条件值
        String name = userQueryVo.getName();
        //创建条件构造器
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            //封装条件
            wrapper.like(Admin::getName,name);
        }
        //调用mapper方法
        IPage<Admin> pageModel = baseMapper.selectPage(pageParam,wrapper);
        return pageModel;
    }
}




