package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoAlarm;

import java.util.List;
import java.util.Map;

public interface AlarmService {
    List<InfoAlarm> findAllPolicyByDeletedFalse();
    void insertPolicy(Map<String, Object> policyMap, LoginUser loginUser);
    Object findOnePolicyDetail(Integer id);
    void updatePolicyById(Map<String, Object> policyMap, LoginUser loginUser);
}
