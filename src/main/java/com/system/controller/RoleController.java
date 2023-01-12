package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.RoleService;
import com.system.vo.role.RoleDetailResultVo;
import com.system.vo.role.RoleParamVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "添加角色")
    @PostMapping
    @ResponseBody
    public ResultData add(@RequestBody @Valid RoleParamVo roleParamVo) {
        int i = roleService.add(roleParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改角色")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(@RequestBody @Valid RoleParamVo roleParamVo) {
        int i = roleService.update(roleParamVo);
        if (i< 1) {
            return ResultUtils.error("注册失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("角色详情，带关联名称")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<RoleDetailResultVo> detail(@PathVariable Integer id) {
        RoleDetailResultVo roleDetailResultVo = roleService.detail(id);
        return ResultUtils.render(roleDetailResultVo);
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData cancellation(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = roleService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("角色详情，带关联名称")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<RoleDetailResultVo>> list(RoleParamVo roleParamVo, Paging paging) {
        PageList<RoleDetailResultVo> roleDetailResultVoList = roleService.list(roleParamVo, paging);
        return ResultUtils.render(roleDetailResultVoList);
    }

}
