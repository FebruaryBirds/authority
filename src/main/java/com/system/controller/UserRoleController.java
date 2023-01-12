package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.UserRoleService;
import com.system.vo.userrole.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户角色接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "用户添加角色")
    @PostMapping("/userAddRole")
    @ResponseBody
    public ResultData userAddRole(@RequestBody @Valid UserRoleParamVo userRoleParamVo) {
        int i = userRoleService.userAddRole(userRoleParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "角色添加用户")
    @PostMapping("/roleAddUser")
    @ResponseBody
    public ResultData roleAddUser(@RequestBody @Valid RoleUserParamVo roleUserParamVo) {
        int i = userRoleService.roleAddUser(roleUserParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("用户角色详情，带关联名称")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<UserRoleResultVo> detail(@PathVariable Integer id) {
        UserRoleResultVo userRoleResultVo = userRoleService.userRole(id);
        return ResultUtils.render(userRoleResultVo);
    }

    @ApiOperation(value = "删除用户角色")
    @DeleteMapping
    @ResponseBody
    public ResultData delete(UserRoleParamVo userRoleParamVo) {
        int i = userRoleService.delete(userRoleParamVo);
        if (i< 1) {
            return ResultUtils.error("删除失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("角色详情，带关联名称")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/role/{id}")
    @ResponseBody
    public ResultData<PageList<RoleUserInfo>> roleUser(@PathVariable Integer id, Paging paging) {
        PageList<RoleUserInfo> roleUserInfoPageList = userRoleService.roleUser(id,paging);
        return ResultUtils.render(roleUserInfoPageList);
    }

    @ApiOperation("角色详情，带关联名称")
    @GetMapping("/role")
    @ResponseBody
    public ResultData<PageList<RoleUserResultVo>> roleList(RoleUserListParamVo roleUserListParamVo, Paging paging) {
        PageList<RoleUserResultVo> resultVoPageList = userRoleService.roleList(roleUserListParamVo, paging);
        return ResultUtils.render(resultVoPageList);
    }

}
