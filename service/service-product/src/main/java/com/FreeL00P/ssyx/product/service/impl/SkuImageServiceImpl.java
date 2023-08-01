package com.FreeL00P.ssyx.product.service.impl;

import com.FreeL00P.ssyx.model.product.SkuImage;
import com.FreeL00P.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.product.service.SkuImageService;
import com.FreeL00P.ssyx.product.mapper.SkuImageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_image(商品图片)】的数据库操作Service实现
* @createDate 2023-08-01 12:26:41
*/
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage>
    implements SkuImageService{

    @Override
    public List<SkuImage> getSkuImgBySkuId(Long skuId) {
        //构造查询条件
        LambdaQueryWrapper<SkuImage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuImage::getSkuId,skuId);
        return this.list(wrapper);
    }
}




