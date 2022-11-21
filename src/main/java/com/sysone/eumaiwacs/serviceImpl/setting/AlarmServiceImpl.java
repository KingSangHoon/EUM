package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.ApplicationSearchIpMacPortForRealtimePageINF;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepository;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.AlarmService;
import com.sysone.eumaiwacs.service.setting.ApplicationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Line;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired InfoAlarmRepository infoAlarmRepository;

    @Override
    public List<InfoAlarm> findAllPolicyByDeletedFalse() {
        List<InfoAlarm> infoAlarmList =infoAlarmRepository.findAllByDeleteIsFalse();
        for (InfoAlarm item : infoAlarmList) item.setAlarmTitle(Util.latin1ToUtf8(item.getAlarmTitle()));
        return infoAlarmList;
    }

    @Override
    @Transactional
    public void insertPolicy(Map<String, Object> policyMap, LoginUser loginUser){
        InfoAlarm infoAlarmEntity = new InfoAlarm();
        infoAlarmEntity.setAlarmTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
        infoAlarmEntity.setFirstWrite(loginUser.getLoginId());
        infoAlarmEntity.setRegDate(LocalDateTime.now());
        infoAlarmEntity.setDeleted(false);
        infoAlarmRepository.save(infoAlarmEntity);
    }

    @Override
    public Object findOnePolicyDetail(Integer id) {
        Optional<InfoAlarm> infoAlarm = infoAlarmRepository.findById(id);
        infoAlarm.ifPresent(item -> { item.setAlarmTitle(Util.latin1ToUtf8(item.getAlarmTitle())); });
        return infoAlarm.get();
    }

    @Override
    @Transactional
    public void updatePolicyById(Map<String, Object> policyMap, LoginUser loginUser) {
        Optional<InfoAlarm> infoAlarm = infoAlarmRepository.findById((Integer) policyMap.get("id"));
        infoAlarm.ifPresent(item -> {
            item.setAlarmTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
            item.setLastWrite(loginUser.getLoginId());
            item.setModifyDate(LocalDateTime.now());
        });
        infoAlarmRepository.save(infoAlarm.get());
    }


}


