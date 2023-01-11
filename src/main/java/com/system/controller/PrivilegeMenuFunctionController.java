package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.PrivilegeMenuFunctionService;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionAddParamVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionDetailVo;
import com.system.vo.privilegemenufunc.PrivilegeMenuFunctionQueryVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "权限菜单接口")
@RestController
@RequestMapping("/privilege/menuFunc")
public class PrivilegeMenuFunctionController {
    @Autowired
    private PrivilegeMenuFunctionService privilegeMenuFunctionService;

    @ApiOperation(value = "添加角色权限")
    @PostMapping
    @ResponseBody
    public ResultData add(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo) {
        int i = privilegeMenuFunctionService.add(privilegeMenuFunctionVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改角色权限")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(PrivilegeMenuFunctionAddParamVo privilegeMenuFunctionVo) {
        int i = privilegeMenuFunctionService.update(privilegeMenuFunctionVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限菜单详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<PrivilegeMenuFunctionDetailVo> detail(@PathVariable Integer id) {
        PrivilegeMenuFunctionDetailVo privilegeMenuFunctionDetailVo = privilegeMenuFunctionService.detail(id);
        return ResultUtils.render(privilegeMenuFunctionDetailVo);
    }

    @ApiOperation(value = "删除权限菜单")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = privilegeMenuFunctionService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("删除失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限菜单列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<PrivilegeMenuFunctionDetailVo>> list(PrivilegeMenuFunctionQueryVo privilegeMenuFunctionQueryVo, Paging paging) {
        PageList<PrivilegeMenuFunctionDetailVo> pageList = privilegeMenuFunctionService.list(privilegeMenuFunctionQueryVo, paging);
        return ResultUtils.render(pageList);
    }

}
