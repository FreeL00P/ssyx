package com.FreeL00P.ssyx.activity.service.impl;

import com.FreeL00P.ssyx.activity.mapper.CouponInfoMapper;
import com.FreeL00P.ssyx.activity.mapper.CouponRangeMapper;
import com.FreeL00P.ssyx.activity.mapper.CouponUseMapper;
import com.FreeL00P.ssyx.activity.service.CouponInfoService;
import com.FreeL00P.ssyx.client.product.ProductFeignClient;
import com.FreeL00P.ssyx.enums.CouponRangeType;
import com.FreeL00P.ssyx.model.activity.CouponInfo;
import com.FreeL00P.ssyx.model.activity.CouponRange;
import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.vo.activity.CouponRuleVo;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author freeloop
* @description 针对表【coupon_info(优惠券信息)】的数据库操作Service实现
* @createDate 2023-08-04 11:15:00
*/
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo>
    implements CouponInfoService{
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private CouponRangeMapper couponRangeMapper;

    @Autowired
    private CouponUseMapper couponUseMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    //优惠卷分页查询
    @Override
    public IPage selectPage(Page<CouponInfo> pageParam) {
        //  构造排序条件
        QueryWrapper<CouponInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<CouponInfo> page = couponInfoMapper.selectPage(pageParam, queryWrapper);
        page.getRecords().stream().forEach(item -> {
            item.setCouponTypeString(item.getCouponType().getComment());
            if(null != item.getRangeType()) {
                item.setRangeTypeString(item.getRangeType().getComment());
            }
        });
        //  返回数据集合
        return page;
    }

    //根据id获取优惠券
    @Override
    public CouponInfo getCouponInfo(String id) {
        CouponInfo couponInfo = this.getById(id);
        couponInfo.setCouponTypeString(couponInfo.getCouponType().getComment());
        if(null != couponInfo.getRangeType()) {
            couponInfo.setRangeTypeString(couponInfo.getRangeType().getComment());
        }
        return couponInfo;
    }

    //根据优惠卷id获取优惠券规则列表
    @Override
    public Map<String, Object> findCouponRuleList(Long couponId) {
        Map<String, Object> result = new HashMap<>();
        CouponInfo couponInfo = this.getById(couponId);

        QueryWrapper couponRangeQueryWrapper = new QueryWrapper<CouponRange>();
        couponRangeQueryWrapper.eq("coupon_id",couponId);
        List<CouponRange> activitySkuList = couponRangeMapper.selectList(couponRangeQueryWrapper);

        List<Long> rangeIdList = activitySkuList.stream().map(CouponRange::getRangeId).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(rangeIdList)) {
            if(couponInfo.getRangeType() == CouponRangeType.SKU) {
                List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(rangeIdList);
                result.put("skuInfoList", skuInfoList);

            } else if (couponInfo.getRangeType() == CouponRangeType.CATEGORY) {
                List<Category> categoryList = productFeignClient.findCategoryList(rangeIdList);
                result.put("categoryList", categoryList);

            } else {
                //通用
            }
        }
        return result;
    }

    //新增优惠券规则
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCouponRule(CouponRuleVo couponRuleVo) {
        /*
        优惠券couponInfo 与 couponRange 要一起操作：先删除couponRange ，更新couponInfo ，再新增couponRange ！
         */
        QueryWrapper<CouponRange> couponRangeQueryWrapper = new QueryWrapper<>();
        couponRangeQueryWrapper.eq("coupon_id",couponRuleVo.getCouponId());
        couponRangeMapper.delete(couponRangeQueryWrapper);

        //  更新数据
        CouponInfo couponInfo = this.getById(couponRuleVo.getCouponId());
        // couponInfo.setCouponType();
        couponInfo.setRangeType(couponRuleVo.getRangeType());
        couponInfo.setConditionAmount(couponRuleVo.getConditionAmount());
        couponInfo.setAmount(couponRuleVo.getAmount());
        couponInfo.setConditionAmount(couponRuleVo.getConditionAmount());
        couponInfo.setRangeDesc(couponRuleVo.getRangeDesc());

        couponInfoMapper.updateById(couponInfo);

        //  插入优惠券的规则 couponRangeList
        List<CouponRange> couponRangeList = couponRuleVo.getCouponRangeList();
        for (CouponRange couponRange : couponRangeList) {
            couponRange.setCouponId(couponRuleVo.getCouponId());
            //  插入数据
            couponRangeMapper.insert(couponRange);
        }
    }

    //根据关键字获取sku列表，活动使用
    @Override
    public List<CouponInfo> findCouponByKeyword(String keyword) {
        //  模糊查询
        QueryWrapper<CouponInfo> couponInfoQueryWrapper = new QueryWrapper<>();
        couponInfoQueryWrapper.like("coupon_name",keyword);
        return couponInfoMapper.selectList(couponInfoQueryWrapper);
    }
}




