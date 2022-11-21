package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;

import java.util.List;
import java.util.Map;

public interface CriticalService {
    Map<String, Object> findByLastDefault(String oneDept);
    void insertDefaultResource(String oneDept, Map<String, Object> policyMap, LoginUser loginUser);
    Map<String, Object> findByTypeAndSub2Id(String oneDept, Integer sub2Id);
    void insertPolicy(String oneDept, Map<String, Object> policyMap, LoginUser loginUser);
    Map<String, Object> findPolicyByPolicyId(String oneDept, Integer policyId);
    void deletePolicyById(String oneDept, String idStr, LoginUser loginUser);
    void updatePolicy(String oneDept, Integer policyId, Map<String, Object> policyMap, LoginUser loginUser);
    List<Map<String, Object>> findPolicyAll(String oneDept);
}
