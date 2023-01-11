package com.system.controller;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.common.common.bean.ResultData;
import com.common.common.util.ResultUtils;
import com.system.service.DataCategoryService;
import com.system.vo.datacategory.DataCategoryAddParamVo;
import com.system.vo.datacategory.DataCategoryDetailVo;
import com.system.vo.datacategory.DataCategoryQueryVo;
import com.common.verify.annotation.AuthPassPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "数据目录归属接口")
@RestController
@RequestMapping("/dataCategory")
public class DataCategoryController {
    @Autowired
    private DataCategoryService service;

    @ApiOperation(value = "添加数据归属")
    @PostMapping
    @ResponseBody
    public ResultData add(DataCategoryAddParamVo paramVo) {
        int i = service.add(paramVo);
        if (i< 1) {
            return ResultUtils.error("添加失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation(value = "修改数据归属")
    @PostMapping("/update")
    @ResponseBody
    @AuthPassPort
    public ResultData update(DataCategoryAddParamVo paramVo) {
        int i = service.update(paramVo);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("数据归属详情")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResultData<DataCategoryDetailVo> detail(@PathVariable Integer id) {
        DataCategoryDetailVo detailVo = service.detail(id);
        return ResultUtils.render(detailVo);
    }

    @ApiOperation(value = "删除数据归属")
    @ApiImplicitParam(paramType = "body", name = "ids", value = "id集合")
    @DeleteMapping
    @ResponseBody
    @AuthPassPort
    public ResultData delete(@Size(min = 1,message = "ids不能为空") @RequestBody List<Integer> ids) {
        int i = service.delete(ids);
        if (i< 1) {
            return ResultUtils.error("修改失败");
        }
        return ResultUtils.success();
    }

    @ApiOperation("数据归属列表")
    @GetMapping("/list")
    @ResponseBody
    public ResultData<PageList<DataCategoryDetailVo>> list(DataCategoryQueryVo queryVo, Paging paging) {
        PageList<DataCategoryDetailVo> pageList = service.list(queryVo, paging);
        return ResultUtils.render(pageList);
    }

}
