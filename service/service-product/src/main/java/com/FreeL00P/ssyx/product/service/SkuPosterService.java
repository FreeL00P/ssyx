package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_poster(商品海报表)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> getSkuPosterBySkuId(Long id);
}
