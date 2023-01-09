package com.common.authority.system.controller;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.common.bean.ResultData;
import com.common.authority.common.util.ResultUtils;
import com.common.authority.system.service.MenuFunctionService;
import com.common.authority.system.vo.menufunction.MenuFunctionAddParamVo;
import com.common.authority.system.vo.menufunction.MenuFunctionDetailVo;
import com.common.authority.system.vo.menufunction.MenuFunctionQueryParamVo;
import com.common.authority.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "菜单/功能接口")
@RestController
@RequestMapping("/menuFunction")
public class MenuFunctionController {
    @Autowired
    private MenuFunctionService menuFunctionService;

    @ApiOperation(value = "添加菜单/功能")
    @PostMapping
    @ResponseBody
    public ResultData add(MenuFunctionAddParamVo menuFunctionAddParamVo) {
        int i = menuFunctionService.add(menuFunctionAddParamVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改菜单/功能")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(MenuFunctionAddParamVo menuFunctionAddParamVo) {
        int i = menuFunctionService.update(menuFunctionAddParamVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("菜单/功能详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<MenuFunctionDetailVo> detail(@PathVariable Integer id) {
        MenuFunctionDetailVo menuFunctionDetailVo = menuFunctionService.detail(id);
        return ResultUtils.render(menuFunctionDetailVo);
    }

    @ApiOperation(value = "删除菜单/功能")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = menuFunctionService.delete(ids);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("菜单/功能列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<MenuFunctionDetailVo>> list(MenuFunctionQueryParamVo menuFunctionQueryParamVo, Paging paging) {
        PageList<MenuFunctionDetailVo> pageList = menuFunctionService.list(menuFunctionQueryParamVo, paging);
        return ResultUtils.render(pageList);
    }

}
