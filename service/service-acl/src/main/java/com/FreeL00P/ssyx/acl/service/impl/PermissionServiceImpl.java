package com.FreeL00P.ssyx.acl.service.impl;

import com.FreeL00P.ssyx.acl.helper.PermissionHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.model.acl.Permission;
import com.FreeL00P.ssyx.acl.service.PermissionService;
import com.FreeL00P.ssyx.acl.mapper.PermissionMapper;
import lombok.var;
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




