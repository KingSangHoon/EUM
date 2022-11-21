package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.entity.setting.MappingNdpiProtocol;
import com.sysone.eumaiwacs.entity.setting.NdpiProtocol;
import com.sysone.eumaiwacs.repository.setting.MappingNdpiProtocolRepository;
import com.sysone.eumaiwacs.repository.setting.NdpiProtocolRepository;
import com.sysone.eumaiwacs.service.setting.CodeProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class CodeProtocolServiceImpl implements CodeProtocolService {

    @Autowired NdpiProtocolRepository ndipProtocolRepository;
    @Autowired MappingNdpiProtocolRepository mappingNdpiProtocolRepository;

    @Override
    public List<NdpiProtocol> findProtocolAll() {
        return ndipProtocolRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Map<String, Object>> findByMappingProtocol() {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Object[]> protocolList = mappingNdpiProtocolRepository.findMappingProtocolAll();

        for (Object[] item : protocolList){
            Map<String, Object> oneItem = new HashMap<>();

            oneItem.put("mappingId", Integer.valueOf((item[0].toString())));
            oneItem.put("appName", (String)item[1]);
            oneItem.put("masterName", (String)item[2]);
            oneItem.put("isWhiteList", item[3]);

            result.add(oneItem);
        }

        return result;
    }

    @Override
    public void insertByProptocol(Map<String, Object> map) {

        MappingNdpiProtocol protocol = new MappingNdpiProtocol();
        protocol.setNdpiProtocolAppNumber((Integer) map.get("appId"));
        protocol.setNdpiProtocolMasterNumber((Integer) map.get("masterId"));
        protocol.setIsWhiteList((Boolean) map.get("type"));
        protocol.setUpdateDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        mappingNdpiProtocolRepository.save(protocol);
    }

    @Override
    public void deleteProtocolMapping(Set<Integer> idSet) {
        mappingNdpiProtocolRepository.deleteMappingId(idSet);
    }

    @Override
    public Map<String, Object> findOneProtocol(Integer mappingId) {

        Map<String, Object> result = new HashMap<>();
        MappingNdpiProtocol mappingItem = mappingNdpiProtocolRepository.findId(mappingId);
        result.put("appProtocol", ndipProtocolRepository.findId(mappingItem.getNdpiProtocolAppNumber()));
        result.put("masterProtocol", ndipProtocolRepository.findId(mappingItem.getNdpiProtocolMasterNumber()));
        result.put("mappingInfo", mappingItem);

        return result;
    }

    @Override
    public void updateProtocolMapping(Map<String, Object> map, Integer mappingId) {
        MappingNdpiProtocol protocol = new MappingNdpiProtocol();
        protocol.setMappingId(mappingId);
        protocol.setNdpiProtocolAppNumber((Integer) map.get("appId"));
        protocol.setNdpiProtocolMasterNumber((Integer) map.get("masterId"));
        protocol.setIsWhiteList((Boolean) map.get("type"));
        protocol.setUpdateDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        mappingNdpiProtocolRepository.save(protocol);

    }
}
