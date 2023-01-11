package com.common.verify.service;

import com.common.common.util.JsonMapper;
import com.common.verify.bean.TokenInfo;
import com.common.verify.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenInfoService {
    @Resource
    protected HttpServletRequest request;
    @Resource
    JsonMapper jsonMapper;

    public TokenInfo getTokenInfo() {
        String token = request.getHeader("Token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("Token");
        }
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String json = JwtUtil.getTokenContent(token);

        TokenInfo tokenInfo = jsonMapper.parseObject(json, TokenInfo.class);
        return tokenInfo;
    }
}
