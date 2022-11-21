package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.InfoUserAgent;

import java.util.List;
import java.util.Map;

public interface CodeUserAgentService {
    List<Object> findUserAgentTypeByCategory(String category);
    List<InfoUserAgent> findUserAgent(String category, String key);
    List<InfoUserAgent> findAllAgent();
    List<Object> findUserAgentByUser(Map<String, Object> userMap);
    long findCntAllAgent(Map<String, Object> userAgentMap);
    List<Object> findAgent(Map<String, Object> userAgentMap, Integer nextPage, Integer offset);
}
