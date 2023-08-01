package com.FreeL00P.ssys.sys.controller;

import com.FreeL00P.ssys.sys.service.WareService;
import com.FreeL00P.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * WareController
 *
 * @author fj
 * @since 2023/7/30 21:47
 */
@Api(value = "Ware管理", tags = "Ware管理")
@RestController
@RequestMapping(value="/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Resource
    private WareService wareService;

    @ApiOperation(value = "获取全部仓库")
    @GetMapping("findAllList")
    public Result findAllList() {
        return Result.ok(wareService.list());
    }

}