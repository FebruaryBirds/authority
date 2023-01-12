package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.RolePrivilegeService;
import com.system.vo.roleprivilege.RolePrivilegeAddParamVo;
import com.system.vo.roleprivilege.RolePrivilegeDetailVo;
import com.system.vo.roleprivilege.RolePrivilegeQueryParamVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "角色权限接口")
@RestController
@RequestMapping("/role/privilege")
public class RolePrivilegeController {
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @ApiOperation(value = "添加角色权限")
    @PostMapping
    @ResponseBody
    public ResultData add(@RequestBody @Valid RolePrivilegeAddParamVo rolePrivilegeAddParamVo) {
        int i = rolePrivilegeService.add(rolePrivilegeAddParamVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改角色权限")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(@RequestBody @Valid RolePrivilegeAddParamVo rolePrivilegeAddParamVo) {
        int i = rolePrivilegeService.update(rolePrivilegeAddParamVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("角色权限详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<RolePrivilegeDetailVo> detail(@PathVariable Integer id) {
        RolePrivilegeDetailVo rolePrivilegeDetailVo = rolePrivilegeService.detail(id);
        return ResultUtils.render(rolePrivilegeDetailVo);
    }

    @ApiOperation(value = "删除角色权限")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = rolePrivilegeService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("角色权限列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<RolePrivilegeDetailVo>> list(RolePrivilegeQueryParamVo rolePrivilegeQueryParamVo, Paging paging) {
        PageList<RolePrivilegeDetailVo> pageList = rolePrivilegeService.list(rolePrivilegeQueryParamVo, paging);
        return ResultUtils.render(pageList);
    }

}
