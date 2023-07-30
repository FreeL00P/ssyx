package com.FreeL00P.ssyx.acl.controller;

/**
 * PermissionAdminController
 *
 * @author fj
 * @since 2023/7/30 10:05
 */

import com.FreeL00P.ssyx.acl.service.PermissionService;
import com.FreeL00P.ssyx.common.result.Result;
import com.FreeL00P.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理 前端控制器
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
@CrossOrigin //跨域
public class PermissionAdminController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取菜单")
    @GetMapping
    public Result index(){
        List<Permission> list=permissionService.queryAllMenu();
        return Result.ok(list);
    }
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单数据")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<Permission> allPermissions = permissionService.findPermissionByRoleId(roleId);
        return Result.ok(allPermissions);
    }

    @ApiOperation(value = "根据角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long roleId, @RequestParam Long[] permissionId) {
        permissionService.saveRolePermissionRelationship(roleId,permissionId);
        return Result.ok();
    }
}
