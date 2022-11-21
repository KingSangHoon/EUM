package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ThresholdService {

    Object findLatest(String category);
    Object findThreshold(String dir, String category, Integer id);
    Object insertThreshold(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);

    List<Object> findAllPolicy(String category);
    Object findPolicy(String category, Integer id);
    Object insertThresholdPolicy(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    Object updateThresholdPolicy(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    void deleteThresholdPolicy(String category, String idStr, LoginUser loginUser, HttpServletRequest req);

}
