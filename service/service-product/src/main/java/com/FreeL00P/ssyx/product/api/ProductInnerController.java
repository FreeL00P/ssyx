package com.FreeL00P.ssyx.product.api;

import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.product.service.CategoryService;
import com.FreeL00P.ssyx.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductInnerController
 *
 * @author fj
 * @since 2023/8/3 20:11
 */
@RestController
@RequestMapping("/api/product")
public class ProductInnerController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuInfoService skuInfoService;

    //根据分类Id获取分类信息
    @GetMapping("inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId){
        return categoryService.getById(categoryId);
    }
    //根据skuId获取sku信息
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        return skuInfoService.getById(skuId);
    }
}
