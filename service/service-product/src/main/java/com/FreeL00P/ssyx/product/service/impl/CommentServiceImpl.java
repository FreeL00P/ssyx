package com.FreeL00P.ssyx.product.service.impl;

import com.FreeL00P.ssyx.model.product.Comment;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.FreeL00P.ssyx.product.service.CommentService;
import com.FreeL00P.ssyx.product.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author freeloop
* @description 针对表【comment(商品评价)】的数据库操作Service实现
* @createDate 2023-08-01 12:26:41
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




