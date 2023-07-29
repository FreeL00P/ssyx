package com.FreeL00P.ssyx.acl.controller;

import com.FreeL00P.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * IndexController
 *
 * @author fj
 * @since 2023/7/29 20:46
 */
@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin     //跨域
@Api(tags = "后台登录与权限管理")
public class IndexController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map<String,String>> login(){
        //返回token
        Map<String,String> map=new HashMap<>();
        map.put("token","token-admin");
        return Result.ok(map);
    }
    /**
     * 2 获取用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","atguigu");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * 3 退出
     */
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
