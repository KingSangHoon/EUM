package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.auth.ResponseResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SslPolicyService {

    List<Object> findAll();
    Object find(Integer sensorId);
    ResponseResult save(MultipartHttpServletRequest req, LoginUser loginUser) throws IllegalStateException, IOException;
    void delete(String idStr, LoginUser loginUser);
}
