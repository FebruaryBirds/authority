package com.system.vo.role;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RoleParamVo {
    private Long id;
    private Integer parentId;
    private String code;
    private String name;
    private Integer type;
    private String remark;
}
