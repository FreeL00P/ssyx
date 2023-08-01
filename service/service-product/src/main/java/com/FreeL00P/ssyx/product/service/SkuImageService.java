package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_image(商品图片)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> getSkuImgBySkuId(Long id);
}
