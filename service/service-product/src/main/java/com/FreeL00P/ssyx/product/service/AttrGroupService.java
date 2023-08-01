package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.AttrGroup;
import com.FreeL00P.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【attr_group(属性分组)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);

    List<AttrGroup> findAllList();
}
