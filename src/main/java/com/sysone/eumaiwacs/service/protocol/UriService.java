package com.sysone.eumaiwacs.service.protocol;

import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;

import java.util.Map;

public interface UriService {

    Object searchRealtimeUri(Map<String, Object> param);
    RealtimeUri viewRealtimeUri(Map<String, Object> param);
}
