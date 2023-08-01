package com.FreeL00P.ssyx.product.service.impl;

import com.FreeL00P.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.product.service.AttrService;
import com.FreeL00P.ssyx.product.mapper.AttrMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author freeloop
* @description 针对表【attr(商品属性)】的数据库操作Service实现
* @createDate 2023-08-01 12:26:41
*/
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr>
    implements AttrService{

    //根据属性分组id 获取属性列表
    @Override
    public List<Attr> findByAttrGroupId(Long attrGroupId) {
        LambdaQueryWrapper<Attr> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attr::getAttrGroupId,attrGroupId);
        List<Attr> attrList = baseMapper.selectList(wrapper);
        return attrList;
    }
}




