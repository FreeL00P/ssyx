package com.FreeL00P.ssyx.product.service.impl;

import com.FreeL00P.ssyx.model.product.SkuAttrValue;
import com.FreeL00P.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.product.service.SkuAttrValueService;
import com.FreeL00P.ssyx.product.mapper.SkuAttrValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_attr_value(spu属性值)】的数据库操作Service实现
* @createDate 2023-08-01 12:26:41
*/
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue>
    implements SkuAttrValueService{

    @Override
    public List<SkuAttrValue> getSkuAttrValueBySkuId(Long skuId) {
        //构造查询条件
        LambdaQueryWrapper<SkuAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId,skuId);
        return this.list(wrapper);
    }
}




