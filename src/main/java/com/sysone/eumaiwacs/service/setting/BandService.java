package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoBand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BandService {
    ArrayList<Map<String, Object>> findAllPolicyByDeletedFalse();
    void insertRule(Map<String, Object> policyMap, LoginUser loginUser) throws IOException;
    Map<String, Object> findOnePolicyDetail(Integer bnadId, Integer ruleId);
    void insertBand(Map<String, Object> policyMap, LoginUser loginUser);
    void updateBand(Map<String, Object> policyMap, Integer bandId, LoginUser loginUser);
    void DeleteBand(String bandIdStr, LoginUser loginUser);
    void updateRule(Map<String, Object> policyMap, Integer bandId, Integer ruleId, LoginUser loginUser);
    void deleteRule(Integer bandId, String ruleIdStr, LoginUser loginUser);
}
