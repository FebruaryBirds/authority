package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.PrivilegeDataCategoryService;
import com.system.vo.privilegedata.PrivilegeDataCategoryAddParamVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryDetailVo;
import com.system.vo.privilegedata.PrivilegeDataCategoryQueryVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "权限数据接口")
@RestController
@RequestMapping("/privilege/dataCategory")
public class PrivilegeDataCategoryController {
    @Autowired
    private PrivilegeDataCategoryService privilegeDataCategoryService;

    @ApiOperation(value = "添加角色权限")
    @PostMapping
    @ResponseBody
    public ResultData add(@RequestBody @Valid PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo) {
        int i = privilegeDataCategoryService.add(privilegeDataCategoryAddParamVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改角色权限")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(@RequestBody @Valid PrivilegeDataCategoryAddParamVo privilegeDataCategoryAddParamVo) {
        int i = privilegeDataCategoryService.update(privilegeDataCategoryAddParamVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限菜单详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<PrivilegeDataCategoryDetailVo> detail(@PathVariable Integer id) {
        PrivilegeDataCategoryDetailVo privilegeDataCategoryDetailVo = privilegeDataCategoryService.detail(id);
        return ResultUtils.render(privilegeDataCategoryDetailVo);
    }

    @ApiOperation(value = "删除权限菜单")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = privilegeDataCategoryService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("删除失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限菜单列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<PrivilegeDataCategoryDetailVo>> list(PrivilegeDataCategoryQueryVo privilegeDataCategoryQueryVo, Paging paging) {
        PageList<PrivilegeDataCategoryDetailVo> pageList = privilegeDataCategoryService.list(privilegeDataCategoryQueryVo, paging);
        return ResultUtils.render(pageList);
    }

}
