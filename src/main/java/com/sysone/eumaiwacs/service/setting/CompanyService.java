package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.Company;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Object> findAll();
    Company insertCompany(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    Company updateCompany(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    void deleteCompany(String idStr, LoginUser loginUser, HttpServletRequest req);
    List<Object> mappingUserCompany(Integer companyId, String userIdStr);
}
