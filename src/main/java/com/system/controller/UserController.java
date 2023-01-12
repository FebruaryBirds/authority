package com.system.controller;

import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.UserService;
import com.system.vo.user.UserDetailResultVo;
import com.system.vo.user.UserRegisterParamVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    @ResponseBody
    @AuthPassPort
    public ResultData register(@RequestBody @Valid UserRegisterParamVo userRegisterParamVo) {
        int i = userService.register(userRegisterParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/update")
    @ResponseBody
    public ResultData update(@RequestBody @Valid UserRegisterParamVo userRegisterParamVo) {
        int i = userService.update(userRegisterParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("详情，带关联名称")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/detail/{id}")
    @ResponseBody
    public ResultData<UserDetailResultVo> detail(@PathVariable Integer id) {
        UserDetailResultVo userDetailResultVo = userService.detail(id);
        return ResultUtils.render(userDetailResultVo);
    }

    @ApiOperation(value = "注销用户")
    @PostMapping("/cancellation")
    @ResponseBody
    public ResultData cancellation(UserRegisterParamVo userRegisterParamVo) {
        int i = userService.update(userRegisterParamVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

}
