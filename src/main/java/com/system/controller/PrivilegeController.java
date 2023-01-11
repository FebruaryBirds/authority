package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.PrivilegeService;
import com.system.vo.privilege.PrivilegeAddParamVo;
import com.system.vo.privilege.PrivilegeDetailVo;
import com.system.vo.privilege.PrivilegeQueryParamVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "权限接口")
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @ApiOperation(value = "添加权限")
    @PostMapping
    @ResponseBody
    public ResultData add(PrivilegeAddParamVo privilegeAddParamVo) {
        int i = privilegeService.add(privilegeAddParamVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改权限")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(PrivilegeAddParamVo privilegeAddParamVo) {
        int i = privilegeService.update(privilegeAddParamVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<PrivilegeDetailVo> detail(@PathVariable Integer id) {
        PrivilegeDetailVo privilegeDetailVo = privilegeService.detail(id);
        return ResultUtils.render(privilegeDetailVo);
    }

    @ApiOperation(value = "删除权限")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = privilegeService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("权限")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<PrivilegeDetailVo>> list(PrivilegeQueryParamVo privilegeQueryParamVo, Paging paging) {
        PageList<PrivilegeDetailVo> pageList = privilegeService.list(privilegeQueryParamVo, paging);
        return ResultUtils.render(pageList);
    }

}
