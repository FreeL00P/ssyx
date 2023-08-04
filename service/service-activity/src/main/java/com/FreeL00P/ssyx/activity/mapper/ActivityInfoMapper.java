package com.FreeL00P.ssyx.activity.mapper;

import com.FreeL00P.ssyx.model.activity.ActivityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.stream.Stream;

/**
* @author freeloop
* @description 针对表【activity_info(活动表)】的数据库操作Mapper
* @createDate 2023-08-04 11:15:00
* @Entity com.FreeL00P.ssys.activity.domain.ActivityInfo
*/
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<Long> selectExistSkuIdList(Stream<Long> skuIdList);
}




