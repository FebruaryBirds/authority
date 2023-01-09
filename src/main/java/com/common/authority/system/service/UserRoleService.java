package com.common.authority.system.service;

import com.common.authority.common.bean.PageList;
import com.common.authority.common.bean.Paging;
import com.common.authority.system.vo.userrole.*;

public interface UserRoleService {
    int userAddRole(UserRoleParamVo userRoleParamVo);

    int roleAddUser(RoleUserParamVo roleUserParamVo);

    UserRoleResultVo userRole(Integer id);

    int delete(UserRoleParamVo userRoleParamVo);

    PageList<RoleUserInfo> roleUser(Integer id, Paging paging);

    PageList<RoleUserResultVo> roleList(RoleUserListParamVo roleUserListParamVo, Paging paging);
}
