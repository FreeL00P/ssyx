package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【attr(商品属性)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface AttrService extends IService<Attr> {

    //根据属性分组id 获取属性列表
    List<Attr> findByAttrGroupId(Long attrGroupId);
}
