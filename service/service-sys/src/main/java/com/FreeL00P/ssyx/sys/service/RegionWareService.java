package com.FreeL00P.ssyx.sys.service;

import com.FreeL00P.ssyx.model.sys.RegionWare;
import com.FreeL00P.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author freeloop
* @description 针对表【region_ware(城市仓库关联表)】的数据库操作Service
* @createDate 2023-07-30 21:36:30
*/
public interface RegionWareService extends IService<RegionWare> {

    IPage<RegionWare> selectPage(Page<RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo);

    void saveRegionWare(RegionWare regionWare);

    void updateStatus(Long id, Integer status);
}
