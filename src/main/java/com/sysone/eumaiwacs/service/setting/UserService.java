package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();
    User findUser(Integer userId);
    User insertUser(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    User updateUser(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    void deleteUser(String userIdStr, LoginUser loginUser, HttpServletRequest req);
    void activeUser(Boolean flag, String userIdStr, LoginUser loginUser, HttpServletRequest req);
    List<User> searchUser(Map<String, Object> param);

    List<Object> findMappingCompanyUser();
}
