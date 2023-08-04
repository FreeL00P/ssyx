package com.FreeL00P.ssys.search.controller;

import com.FreeL00P.ssys.search.service.SkuService;
import com.FreeL00P.ssyx.common.result.Result;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SkuApiController
 *
 * @author fj
 * @since 2023/8/3 20:01
 */
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {

    @Autowired
    private SkuService skuService;

    //上架
    @GetMapping("inner/upperSku/{skuId}")
    public Result upperSku(@PathVariable Long skuId){
         skuService.upperSku(skuId);
         return Result.ok();
    }
    //下架
    @GetMapping("inner/lowerSku/{skuId}")
    public Result lowerSku(@PathVariable Long skuId){
        skuService.lowerSku(skuId);
        return Result.ok();
    }
}
