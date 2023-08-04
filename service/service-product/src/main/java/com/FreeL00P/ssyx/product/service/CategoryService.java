package com.FreeL00P.ssyx.product.service;

import com.FreeL00P.ssyx.model.product.Category;
import com.FreeL00P.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author freeloop
* @description 针对表【category(商品三级分类)】的数据库操作Service
* @createDate 2023-08-01 12:26:41
*/
public interface CategoryService extends IService<Category> {

    IPage<Category> selectPage(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);

    List<Category> findAllList();

    List<Category> findCategoryList(List<Long> categoryIdList);
}
