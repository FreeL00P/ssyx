package com.FreeL00P.ssyx.activity.service;

import com.FreeL00P.ssyx.model.activity.ActivityInfo;
import com.FreeL00P.ssyx.model.product.SkuInfo;
import com.FreeL00P.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author freeloop
* @description 针对表【activity_info(活动表)】的数据库操作Service
* @createDate 2023-08-04 11:15:00
*/
public interface ActivityInfoService extends IService<ActivityInfo> {

    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    Map<String,Object> findActivityRuleList(Long id);

    void saveActivityRule(ActivityRuleVo activityRuleVo);

    List<SkuInfo>  findSkuInfoByKeyword(String keyword);
}
