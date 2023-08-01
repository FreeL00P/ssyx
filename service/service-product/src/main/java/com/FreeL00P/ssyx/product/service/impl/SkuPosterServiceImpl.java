package com.FreeL00P.ssyx.product.service.impl;

import com.FreeL00P.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.product.service.SkuPosterService;
import com.FreeL00P.ssyx.product.mapper.SkuPosterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author freeloop
* @description 针对表【sku_poster(商品海报表)】的数据库操作Service实现
* @createDate 2023-08-01 12:26:41
*/
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster>
    implements SkuPosterService{

    @Override
    public List<SkuPoster> getSkuPosterBySkuId(Long skuId) {
        //构造查询条件
        LambdaQueryWrapper<SkuPoster> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuPoster::getSkuId,skuId);
        return this.list(wrapper);
    }
}




