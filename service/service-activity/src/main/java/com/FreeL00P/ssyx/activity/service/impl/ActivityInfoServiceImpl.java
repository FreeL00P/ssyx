package com.FreeL00P.ssyx.activity.service.impl;

import com.FreeL00P.ssyx.activity.mapper.ActivityRuleMapper;
import com.FreeL00P.ssyx.activity.mapper.ActivitySkuMapper;
import com.FreeL00P.ssyx.client.product.ProductFeignClient;
import com.FreeL00P.ssyx.model.activity.ActivityRule;
import com.FreeL00P.ssyx.model.activity.ActivitySku;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.model.activity.ActivityInfo;
import com.FreeL00P.ssyx.activity.service.ActivityInfoService;
import com.FreeL00P.ssyx.activity.mapper.ActivityInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author freeloop
* @description 针对表【activity_info(活动表)】的数据库操作Service实现
* @createDate 2023-08-04 11:15:00
*/
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo>
    implements ActivityInfoService{

    @Autowired
    private ActivitySkuMapper activitySkuMapper;

    @Autowired
    private ActivityRuleMapper activityRuleMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private ActivityInfoMapper activityInfoMapper;

    @Override
    public IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam) {
        Page<ActivityInfo> activityInfoPage = baseMapper.selectPage(pageParam, null);
        List<ActivityInfo> activityInfoList = activityInfoPage.getRecords();
        activityInfoList.forEach((item -> {
            item.setActivityTypeString(item.getActivityType().getComment());
        }));
        return activityInfoPage;
    }

    @Override
    public Map<String,Object> findActivityRuleList(Long id) {
        Map<String,Object> result=new HashMap<>();
        //根据活动id查询规则列表
        LambdaQueryWrapper<ActivityRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityRule::getActivityId,id);
        List<ActivityRule> activityRuleList = activityRuleMapper.selectList(wrapper);
        result.put("activityRuleList",activityRuleList);
        //根据活动id查询使用规则商品列表
        List<ActivitySku> activitySkuList = activitySkuMapper.selectList(
                new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, id)
        );
        List<Long> skuIdList = activitySkuList.stream().map(ActivitySku::getSkuId).collect(Collectors.toList());
        //远程调用service-product模块，根据skuId列表查询skuInfo信息
        List<SkuInfo> skuInfoList=productFeignClient.findSkuInfoList(skuIdList);
        result.put("skuInfoList",skuInfoList);
        return result;
    }

    @Override
    @Transactional
    public void saveActivityRule(ActivityRuleVo activityRuleVo) {
        //根据活动id删除之前规则数据
        activityRuleMapper.delete(
                new LambdaQueryWrapper<ActivityRule>().eq(ActivityRule::getActivityId,activityRuleVo.getActivityId())
        );
        activitySkuMapper.delete(
                new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId,activityRuleVo.getActivityId())
        );
        //添加数据
        List<ActivityRule> activityRuleList = activityRuleVo.getActivityRuleList();
        List<ActivitySku> activitySkuList = activityRuleVo.getActivitySkuList();
        List<Long> couponIdList = activityRuleVo.getCouponIdList();

        ActivityInfo activityInfo = this.getById(activityRuleVo.getActivityId());
        for(ActivityRule activityRule : activityRuleList) {
            activityRule.setActivityId(activityRuleVo.getActivityId());
            activityRule.setActivityType(activityInfo.getActivityType());
            activityRuleMapper.insert(activityRule);
        }

        for(ActivitySku activitySku : activitySkuList) {
            activitySku.setActivityId(activityRuleVo.getActivityId());
            activitySkuMapper.insert(activitySku);
        }
    }

    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        //根据关键字查询skuInfo信息
        List<SkuInfo> skuInfoList =productFeignClient.findSkuInfoByKeyWord(keyword);
        //判断商品之前是否参加过活动
        List<SkuInfo> notExistSkuInfoList = new ArrayList<>();
        Stream<Long> skuIdList = skuInfoList.stream().map(SkuInfo::getId);
        //已经存在的skuId，一个sku只能参加一个促销活动，所以存在的得排除
        List<Long> existSkuIdList = activityInfoMapper.selectExistSkuIdList(skuIdList);
        String existSkuIdString = "," + StringUtils.join(existSkuIdList.toArray(), ",") + ",";
        for(SkuInfo skuInfo : skuInfoList) {
            if(existSkuIdString.indexOf(","+skuInfo.getId()+",") == -1) {
                notExistSkuInfoList.add(skuInfo);
            }
        }
        return notExistSkuInfoList;
    }
}




