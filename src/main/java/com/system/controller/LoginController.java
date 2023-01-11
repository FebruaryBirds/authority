package com.system.controller;

import com.common.common.bean.BusinessException;
import com.common.common.bean.ResultData;
import com.common.common.util.JsonMapper;
import com.common.common.util.RSAUtil;
import com.common.common.util.ResultUtils;
import com.system.entity.UserEntity;
import com.system.service.UserService;
import com.system.vo.login.LoginInfoVo;
import com.system.vo.login.LoginVo;
import com.common.verify.annotation.AuthPassPort;
import com.common.verify.bean.TokenInfo;
import com.common.verify.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/user")
    @AuthPassPort
    @ResponseBody
    public ResultData<LoginInfoVo> login(@RequestBody @Valid LoginVo loginVo){
        UserEntity user = userService.getByAccount(loginVo.getAccount());
        // 校验密码，开发时简单校验
        if (user == null || !BCrypt.checkpw(loginVo.getPassword().trim(), user.getPassword())) {
            throw new BusinessException("账号或密码错误");
        }


        // 生产前端使用RSA加密密码，后端用私钥解密后进行密码校验
        // 密码规则：1.保存时，对于前端传的加密密码，使用RSA解密之后，再使用BCrypt进行加密，然后进行保存
        // 考虑是否直接保存RSA加密密码？
//        try {
//            if (user == null || !BCrypt.checkpw(RSAUtil.decrypt(loginVo.getPassword()), user.getPassword())) {
//                throw new BusinessException("账号或密码错误");
//            }
//        } catch (Exception e) {
//            log.error("密码解析错误：" + e);
//        }

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(user.getId());
        tokenInfo.setAccount(user.getAccount());
        tokenInfo.setUserName(user.getName());
        tokenInfo.setLoginTime(System.currentTimeMillis());
        String token =  JwtUtil.generateToken(jsonMapper.toJSONString(tokenInfo), 60*8);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(user, loginInfoVo);
        loginInfoVo.setToken(token);
        return ResultUtils.render(loginInfoVo);
    }
}
