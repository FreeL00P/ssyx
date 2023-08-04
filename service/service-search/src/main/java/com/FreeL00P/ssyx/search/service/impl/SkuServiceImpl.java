package com.FreeL00P.ssyx.search.service.impl;

import com.FreeL00P.ssyx.client.product.ProductFeignClient;
import com.FreeL00P.ssyx.search.repository.SkuEsRepository;
import com.FreeL00P.ssyx.search.service.SkuService;
import com.FreeL00P.ssyx.enums.SkuType;
import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.model.search.SkuEs;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SkuServiceImpl
 *
 * @author fj
 * @since 2023/8/3 20:02
 */
@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuEsRepository skuEsRepository;

    @Resource
    private ProductFeignClient productFeignClient;

    @Override
    public void upperSku(Long skuId) {
        //远程调用通过skuId获取skuInfo
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if (skuInfo==null) return;
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        //封装SkuEs数据
        SkuEs skuEs = new SkuEs();
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if(skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品

        }
        //添加到ES
        SkuEs save = skuEsRepository.save(skuEs);
        log.info("upperSku："+ JSON.toJSONString(save));

    }

    @Override
    public void lowerSku(Long skuId) {
        this.skuEsRepository.deleteById(skuId);
    }
}
