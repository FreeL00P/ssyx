package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.vo.product.SkuInfoQueryVo;
import com.FreeL00P.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_info(sku信息)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfo(SkuInfoVo skuInfoVo);

    void updateSkuInfo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuInfoVo(Long id);

    void publish(Long skuId, Integer status);

    void isNewPerson(Long skuId, Integer status);

    void check(Long skuId, Integer status);

    List<SkuInfo> findSkuInfoList(List<Long> skuIdList);

    List<SkuInfo> findSkuInfoByKeyWord(String keyword);
}
