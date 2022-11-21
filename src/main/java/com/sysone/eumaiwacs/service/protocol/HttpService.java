package com.sysone.eumaiwacs.service.protocol;

import java.util.Map;

public interface HttpService {

    Object searchRealtimePage(Map<String, Object> param);
    Map<String, Object> viewRealtimePage(Map<String, Object> param);
}
