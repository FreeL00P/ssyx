package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_attr_value(spu属性值)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SkuAttrValue> getSkuAttrValueBySkuId(Long id);
}
