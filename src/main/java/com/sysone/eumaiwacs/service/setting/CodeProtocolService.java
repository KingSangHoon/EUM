package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.NdpiProtocol;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CodeProtocolService {
    List<NdpiProtocol> findProtocolAll();
    List<Map<String, Object>> findByMappingProtocol();
    void insertByProptocol(Map<String, Object> map);
    void deleteProtocolMapping(Set<Integer> idSet);
    Map<String, Object> findOneProtocol(Integer mappingId);
    void updateProtocolMapping(Map<String, Object> map, Integer mappingId);
}
