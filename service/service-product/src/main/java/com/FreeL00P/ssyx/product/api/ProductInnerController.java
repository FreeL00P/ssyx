package com.FreeL00P.ssyx.product.api;

import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.product.service.CategoryService;
import com.FreeL00P.ssyx.product.service.SkuInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //根据skuId列表获取sku信息
    @PostMapping("inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList){
        return skuInfoService.findSkuInfoList(skuIdList);
    }

    //根据关键字查询skuInfo列表
    @GetMapping("inner/findSkuInfoByKeyWord/{keyword}")
    public List<SkuInfo>  findSkuInfoByKeyWord(@PathVariable String keyword){
        return skuInfoService.findSkuInfoByKeyWord(keyword);
    }
    @ApiOperation(value = "批量获取分类信息")
    @PostMapping("inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryIdList) {
        return categoryService.findCategoryList(categoryIdList);
    }
}
