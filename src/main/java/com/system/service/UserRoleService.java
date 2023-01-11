package com.system.service;

import com.common.common.bean.PageList;
import com.common.common.bean.Paging;
import com.system.vo.userrole.*;

public interface UserRoleService {
    int userAddRole(UserRoleParamVo userRoleParamVo);

    int roleAddUser(RoleUserParamVo roleUserParamVo);

    UserRoleResultVo userRole(Integer id);

    int delete(UserRoleParamVo userRoleParamVo);

    PageList<RoleUserInfo> roleUser(Integer id, Paging paging);

    PageList<RoleUserResultVo> roleList(RoleUserListParamVo roleUserListParamVo, Paging paging);
}
