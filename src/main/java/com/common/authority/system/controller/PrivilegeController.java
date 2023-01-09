package com.common.authority.system.controller;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.common.bean.ResultData;
import com.common.authority.common.util.ResultUtils;
import com.common.authority.system.service.PrivilegeService;
import com.common.authority.system.vo.privilege.PrivilegeAddParamVo;
import com.common.authority.system.vo.privilege.PrivilegeDetailVo;
import com.common.authority.system.vo.privilege.PrivilegeQueryParamVo;
import com.common.authority.verify.annotation.AuthPassPort;
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
