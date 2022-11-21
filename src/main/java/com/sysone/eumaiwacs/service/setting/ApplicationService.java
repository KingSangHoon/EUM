package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.realtime.ApplicationSearchIpMacPortForRealtimePageINF;
import com.sysone.eumaiwacs.entity.setting.InfoApplication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ApplicationService {

    List<InfoApplication> findAllPolicyByDeletedFalse();
    Map<String, Object> findOnePolicyDetail(Integer id);

    void updatePolicyById(Map<String, Object> policyOneItem, LoginUser loginUser, HttpServletRequest req);
    void insertPolicyById(Map<String, Object> policyMap, LoginUser loginUser, HttpServletRequest req);

    void deletePolicy(String idStr, LoginUser loginUser, HttpServletRequest req);

    Map<String, Object> searchIpAndMacAndPortByDateOrIp(Map<String, Object> param);
    List<ApplicationSearchIpMacPortForRealtimePageINF> searchAllIpAndMacAndPortByIp();

    List<Map<String, Object>> detailFindAll();
}
