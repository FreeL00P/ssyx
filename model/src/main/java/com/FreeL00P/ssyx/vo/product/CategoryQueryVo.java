package com.FreeL00P.ssyx.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryQueryVo {

    @ApiModelProperty(value = "分类名称")
    private String name;

}

