package com.FreeL00P.ssyx.client.product;

import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ProductFeignClient
 *
 * @author fj
 * @since 2023/8/3 20:24
 */
@FeignClient("service-product")
public interface ProductFeignClient {

    //根据分类Id获取分类信息
    @GetMapping("api/product/inner/getCategory/{categoryId}")
    Category getCategory(@PathVariable("categoryId") Long categoryId);
    //根据skuId获取sku信息
    @GetMapping("api/product/inner/getSkuInfo/{skuId}")
    SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);
    //根据skuId列表获取sku信息
    @PostMapping("api/product/inner/findSkuInfoList")
    List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList);


    @GetMapping("inner/findSkuInfoByKeyWord/{keyword}")
    List<SkuInfo>  findSkuInfoByKeyWord(@PathVariable("keyword") String keyword);

    /**
     * 批量获取分类信息
     * @param categoryIdList
     * @return
     */
    @PostMapping("/api/product/inner/findCategoryList")
    List<Category> findCategoryList(@RequestBody List<Long> categoryIdList);
}
