package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AlarmType1Dept;
import com.sysone.eumaiwacs.common.AlarmType2Dept;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.CriticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CriticalServiceImpl implements CriticalService {

    @Autowired InfoCriticalRepository infoCriticalRepository;
    @Autowired MappingCriticalValueRepository mappingCriticalValueRepository;

    @Autowired InfoCriticalHttptransactionRepository infoCriticalHttptransactionRepository;
    @Autowired InfoCriticalHttpURIRepository infoCriticalHttpURIRepository;
    @Autowired InfoCriticalL4TCPRepository infoCriticalL4TCPRepository;
    @Autowired InfoCriticalL4UDPRepository infoCriticalL4UDPRepository;
    @Autowired InfoCriticalL3IPRepository infoCriticalL3IPRepository;
    @Autowired InfoCriticalTrafficRepository infoCriticalTrafficRepository;

    @Autowired MappingCriticalHttptransactionRepository mappingCriticalHttptransactionRepository;
    @Autowired MappingCriticalHttpURIRepository mappingCriticalHttpURIRepository;
    @Autowired MappingCriticalL4TCPRepository mappingCriticalL4TCPRepository;
    @Autowired MappingCriticalL4UDPRepository mappingCriticalL4UDPRepository;
    @Autowired MappingCriticalL3IPRepository mappingCriticalL3IPRepository;
    @Autowired MappingCriticalTrafficRepository mappingCriticalTrafficRepository;

    @Override
    public Map<String, Object> findByLastDefault(String oneDept) {
        Map<String, Object> resultMap = new HashMap<>();
        AlarmType1Dept dept1 = AlarmType1Dept.valueOfNameCode(oneDept);

        InfoCriticalDefault lastCriticalEntity = infoCriticalRepository.findByResourceTypeTop1ByOrderByIdDesc(dept1.getIndexNum());

        resultMap = defaultDataSetting(lastCriticalEntity, dept1);

        return resultMap;
    }

    @Override
    public Map<String, Object> findByTypeAndSub2Id(String oneDept, Integer sub2Id) {
        Map<String, Object> resultMap = new HashMap<>();
        AlarmType1Dept dept1 = AlarmType1Dept.valueOfNameCode(oneDept);

        InfoCriticalDefault lastCriticalEntity = infoCriticalRepository.findByResourceTypeAndResourceId(dept1.getIndexNum(), sub2Id);

        resultMap = defaultDataSetting(lastCriticalEntity, dept1);

        return resultMap;
    }

    @Override
    public void insertPolicy(String oneDept, Map<String, Object> policyMap, LoginUser loginUser) {
        String policyTitleStr = (String)policyMap.get("title");

        Integer policyId = insertCriticalPolicy(oneDept, policyTitleStr, loginUser.getLoginId());

        if(policyId != null){
            insertMappingResourceItemArr(oneDept, policyMap, policyId);
        }
    }

    private void insertMappingResourceItemArr(String oneDept, Map<String, Object> policyMap, Integer policyId) {
        ArrayList<Map<String, Object>> resourceItemArr = (ArrayList<Map<String, Object>>) policyMap.get("resourceAll");
        for(Map<String, Object> resourceItem : resourceItemArr){
            switch (oneDept){
                case "httpTransaction" :
                    MappingCriticalHttptransaction mappingHttpTransactionEntity = new MappingCriticalHttptransaction();
                    mappingHttpTransactionEntity.setPolicyId(policyId);
                    mappingHttpTransactionEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingHttpTransactionEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingHttpTransactionEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingHttpTransactionEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingHttpTransactionEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingHttpTransactionEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingHttpTransactionEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingHttpTransactionEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingHttpTransactionEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalHttptransactionRepository.save(mappingHttpTransactionEntity);

                    break;

                case "HttpURI" :
                    MappingCriticalHttpuri mappingHttpURIEntity = new MappingCriticalHttpuri();
                    mappingHttpURIEntity.setPolicyId(policyId);
                    mappingHttpURIEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingHttpURIEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingHttpURIEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingHttpURIEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingHttpURIEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingHttpURIEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingHttpURIEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingHttpURIEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingHttpURIEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalHttpURIRepository.save(mappingHttpURIEntity);

                    break;

                case "l4TCP" :
                    MappingCriticalL4Tcp mappingL4TCPEntity = new MappingCriticalL4Tcp();
                    mappingL4TCPEntity.setPolicyId(policyId);
                    mappingL4TCPEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingL4TCPEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingL4TCPEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingL4TCPEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingL4TCPEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingL4TCPEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingL4TCPEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingL4TCPEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingL4TCPEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalL4TCPRepository.save(mappingL4TCPEntity);

                    break;

                case "l4UDP" :
                    MappingCriticalL4Udp mappingL4UDPEntity = new MappingCriticalL4Udp();
                    mappingL4UDPEntity.setPolicyId(policyId);
                    mappingL4UDPEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingL4UDPEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingL4UDPEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingL4UDPEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingL4UDPEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingL4UDPEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingL4UDPEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingL4UDPEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingL4UDPEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalL4UDPRepository.save(mappingL4UDPEntity);

                    break;

                case "l3IP" :
                    MappingCriticalL3Ip mappingL3IPEntity = new MappingCriticalL3Ip();
                    mappingL3IPEntity.setPolicyId(policyId);
                    mappingL3IPEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingL3IPEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingL3IPEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingL3IPEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingL3IPEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingL3IPEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingL3IPEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingL3IPEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingL3IPEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalL3IPRepository.save(mappingL3IPEntity);

                    break;

                case "traffic" :
                    MappingCriticalTraffic mappingTrafficEntity = new MappingCriticalTraffic();
                    mappingTrafficEntity.setPolicyId(policyId);
                    mappingTrafficEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) resourceItem.get("resource_code")).getIndexNum());
                    mappingTrafficEntity.setResourceInfo((Integer) resourceItem.get("resource_info"));
                    mappingTrafficEntity.setResourceWarning((Integer) resourceItem.get("resource_warning"));
                    mappingTrafficEntity.setResourceDanger((Integer) resourceItem.get("resource_danger"));
                    mappingTrafficEntity.setResourceFatal((Integer) resourceItem.get("resource_fatal"));

                    mappingTrafficEntity.setResourceInfoDuration((Integer) resourceItem.get("resource_info_duration"));
                    mappingTrafficEntity.setResourceWarningDuration((Integer) resourceItem.get("resource_warning_duration"));
                    mappingTrafficEntity.setResourceDangerDuration((Integer) resourceItem.get("resource_danger_duration"));
                    mappingTrafficEntity.setResourceFatalDuration((Integer) resourceItem.get("resource_fatal_duration"));

                    mappingCriticalTrafficRepository.save(mappingTrafficEntity);

                    break;
            }
        }
    }

    @Override
    public Map<String, Object> findPolicyByPolicyId(String oneDept, Integer policyId) {

        Map<String, Object> resultMap = new HashMap<>();
        switch (oneDept){
            case "httpTransaction" :
                resultMap.put("resourceItem", findHttpTransactionByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalHttptransactionRepository.findById(policyId));
                break;
            case "HttpURI" :
                resultMap.put("resourceItem", findHttpURIByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalHttpURIRepository.findById(policyId));
                break;
            case "l4TCP" :
                resultMap.put("resourceItem", findL4TCPByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalL4TCPRepository.findById(policyId));
                break;
            case "l4UDP" :
                resultMap.put("resourceItem", findL4UDPByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalL4UDPRepository.findById(policyId));
                break;
            case "l3IP" :
                resultMap.put("resourceItem", findL3IPByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalL3IPRepository.findById(policyId));
                break;
            case "traffic" :
                resultMap.put("resourceItem", findTrafficByPolicyId(policyId));
                resultMap.put("policyInfo", infoCriticalTrafficRepository.findById(policyId));
                break;
        }
        return resultMap;
    }

    @Override
    @Transactional
    public void deletePolicyById(String oneDept, String idStr, LoginUser loginUser) {
        Set<Integer> policyIdSet = Util.getStringToIntegerSet(idStr);

        for(Integer policyId : policyIdSet){
            deletePolicyAndMappingById(oneDept, policyId);
        }

    }

    @Override
    @Transactional
    public void updatePolicy(String oneDept, Integer policyId, Map<String, Object> policyMap, LoginUser loginUser) {

        deleteMappingById(oneDept, policyId);

        switch (oneDept){
            case "httpTransaction" :
                Optional<InfoCriticalHttptransaction> httptransactionEntity = infoCriticalHttptransactionRepository.findById(policyId);
                httptransactionEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalHttptransactionRepository.save(httptransactionEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;

            case "HttpURI" :
                Optional<InfoCriticalHttpuri> httpURIEntity = infoCriticalHttpURIRepository.findById(policyId);
                httpURIEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalHttpURIRepository.save(httpURIEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;

            case "l4TCP" :
                Optional<InfoCriticalL4Tcp> l4TCPEntity = infoCriticalL4TCPRepository.findById(policyId);
                l4TCPEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalL4TCPRepository.save(l4TCPEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;

            case "l4UDP" :
                Optional<InfoCriticalL4Udp> l4UDPEntity = infoCriticalL4UDPRepository.findById(policyId);
                l4UDPEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalL4UDPRepository.save(l4UDPEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;

            case "l3IP" :
                Optional<InfoCriticalL3Ip> l4IPEntity = infoCriticalL3IPRepository.findById(policyId);
                l4IPEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalL3IPRepository.save(l4IPEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;

            case "traffic" :
                Optional<InfoCriticalTraffic> trafficEntity = infoCriticalTrafficRepository.findById(policyId);
                trafficEntity.ifPresent(item -> {
                    item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
                    item.setLastWriter(loginUser.getLoginId());
                    item.setModifyDate(LocalDateTime.now());
                });
                infoCriticalTrafficRepository.save(trafficEntity.get());
                insertMappingResourceItemArr(oneDept, policyMap, policyId);
                break;
        }
    }

    @Override
    public List<Map<String, Object>> findPolicyAll(String oneDept) {
        List<Map<String, Object>> resultPolicyList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        if(oneDept.equals("httpTransaction")) {
            List<InfoCriticalHttptransaction> findPolicyList = infoCriticalHttptransactionRepository.findAllByDeleteFalse();
            for(InfoCriticalHttptransaction item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }else if(oneDept.equals("HttpURI")) {
            List<InfoCriticalHttpuri> findPolicyList = infoCriticalHttpURIRepository.findAllByDeleteFalse();
            for(InfoCriticalHttpuri item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }else if(oneDept.equals("l4TCP")) {
            List<InfoCriticalL4Tcp> findPolicyList = infoCriticalL4TCPRepository.findAllByDeleteFalse();
            for(InfoCriticalL4Tcp item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }else if(oneDept.equals("l4UDP")) {
            List<InfoCriticalL4Udp> findPolicyList = infoCriticalL4UDPRepository.findAllByDeleteFalse();
            for(InfoCriticalL4Udp item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }else if(oneDept.equals("l3IP")) {
            List<InfoCriticalL3Ip> findPolicyList = infoCriticalL3IPRepository.findAllByDeleteFalse();
            for(InfoCriticalL3Ip item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }else if(oneDept.equals("traffic")) {
            List<InfoCriticalTraffic> findPolicyList = infoCriticalTrafficRepository.findAllByDeleteFalse();
            for(InfoCriticalTraffic item : findPolicyList){
                item.setTitle(Util.latin1ToUtf8(item.getTitle()));
                Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
                resultPolicyList.add(itemMap);
            }
        }
        return resultPolicyList;
    }

    private void deleteMappingById(String oneDept, Integer policyId) {
        switch (oneDept){
            case "httpTransaction" :
                mappingCriticalHttptransactionRepository.deleteByPolicyId(policyId);
                break;
            case "HttpURI" :
                mappingCriticalHttpURIRepository.deleteByPolicyId(policyId);
                break;
            case "l4TCP" :
                mappingCriticalL4TCPRepository.deleteByPolicyId(policyId);
                break;
            case "l4UDP" :
                mappingCriticalL4UDPRepository.deleteByPolicyId(policyId);
                break;
            case "l3IP" :
                mappingCriticalL3IPRepository.deleteByPolicyId(policyId);
                break;
            case "traffic" :
                mappingCriticalTrafficRepository.deleteByPolicyId(policyId);
                break;
        }
    }

    private void deletePolicyAndMappingById(String oneDept, Integer policyId) {
        switch (oneDept){
            case "httpTransaction" :
                infoCriticalHttptransactionRepository.deleteById(policyId);
                mappingCriticalHttptransactionRepository.deleteByPolicyId(policyId);
                break;
            case "HttpURI" :
                infoCriticalHttpURIRepository.deleteById(policyId);
                mappingCriticalHttpURIRepository.deleteByPolicyId(policyId);
                break;
            case "l4TCP" :
                infoCriticalL4TCPRepository.deleteById(policyId);
                mappingCriticalL4TCPRepository.deleteByPolicyId(policyId);
                break;
            case "l4UDP" :
                infoCriticalL4UDPRepository.deleteById(policyId);
                mappingCriticalL4UDPRepository.deleteByPolicyId(policyId);
                break;
            case "l3IP" :
                infoCriticalL3IPRepository.deleteById(policyId);
                mappingCriticalL3IPRepository.deleteByPolicyId(policyId);
                break;
            case "traffic" :
                infoCriticalTrafficRepository.deleteById(policyId);
                mappingCriticalTrafficRepository.deleteByPolicyId(policyId);
                break;
        }
    }


    private ArrayList<Object> findTrafficByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalTraffic> resourceItemArr = mappingCriticalTrafficRepository.findByPolicyId(policyId);
        for(MappingCriticalTraffic item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());

            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private ArrayList<Object> findL3IPByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalL3Ip> resourceItemArr = mappingCriticalL3IPRepository.findByPolicyId(policyId);
        for(MappingCriticalL3Ip item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());

            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private ArrayList<Object> findL4UDPByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalL4Udp> resourceItemArr = mappingCriticalL4UDPRepository.findByPolicyId(policyId);
        for(MappingCriticalL4Udp item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());

            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private ArrayList<Object> findL4TCPByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalL4Tcp> resourceItemArr = mappingCriticalL4TCPRepository.findByPolicyId(policyId);
        for(MappingCriticalL4Tcp item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());

            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private ArrayList<Object> findHttpURIByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalHttpuri> resourceItemArr = mappingCriticalHttpURIRepository.findByPolicyId(policyId);
        for(MappingCriticalHttpuri item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());

            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private ArrayList<Object> findHttpTransactionByPolicyId(Integer policyId) {
        ArrayList<Object> resultArr = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ArrayList<MappingCriticalHttptransaction> resourceItemArr = mappingCriticalHttptransactionRepository.findByPolicyId(policyId);
        for(MappingCriticalHttptransaction item : resourceItemArr){
            Map<String, Object> itemMap = objectMapper.convertValue(item, Map.class);
            itemMap.put("resource_code", AlarmType2Dept.valueOfIndexNum(item.getResourceType()).getCodeName());
            resultArr.add(itemMap);
        }
        return resultArr;
    }

    private Integer insertCriticalPolicy(String oneDept, String policyTitleStr, String loginId) {

        Integer resultId = null;
        policyTitleStr = Util.utf8ToLatin1(policyTitleStr);

        switch (oneDept){
            case "httpTransaction" :
                InfoCriticalHttptransaction httpTransactionPolicyEntity = new InfoCriticalHttptransaction();
                httpTransactionPolicyEntity.setTitle(policyTitleStr);
                httpTransactionPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalHttptransactionRepository.save(httpTransactionPolicyEntity).getId();
                break;
            case "HttpURI" :
                InfoCriticalHttpuri httpURIPolicyEntity = new InfoCriticalHttpuri();
                httpURIPolicyEntity.setTitle(policyTitleStr);
                httpURIPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalHttpURIRepository.save(httpURIPolicyEntity).getId();
                break;
            case "l4TCP" :
                InfoCriticalL4Tcp l4TCPPolicyEntity = new InfoCriticalL4Tcp();
                l4TCPPolicyEntity.setTitle(policyTitleStr);
                l4TCPPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalL4TCPRepository.save(l4TCPPolicyEntity).getId();
                break;
            case "l4UDP" :
                InfoCriticalL4Udp l4UDPPolicyEntity = new InfoCriticalL4Udp();
                l4UDPPolicyEntity.setTitle(policyTitleStr);
                l4UDPPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalL4UDPRepository.save(l4UDPPolicyEntity).getId();
                break;
            case "l3IP" :
                InfoCriticalL3Ip l3IPPolicyEntity = new InfoCriticalL3Ip();
                l3IPPolicyEntity.setTitle(policyTitleStr);
                l3IPPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalL3IPRepository.save(l3IPPolicyEntity).getId();
                break;
            case "traffic" :
                InfoCriticalTraffic trafficPolicyEntity = new InfoCriticalTraffic();
                trafficPolicyEntity.setTitle(policyTitleStr);
                trafficPolicyEntity.setFirstWriter(loginId);

                resultId = infoCriticalTrafficRepository.save(trafficPolicyEntity).getId();
                break;
        }
        return resultId;
    }

    @Override
    @Transactional
    public void insertDefaultResource(String oneDept, Map<String, Object> policyMap, LoginUser loginUser) {

        saveCriticalDefaultByOneDept(oneDept);
        InfoCriticalDefault lastCriticalEntity = infoCriticalRepository.findTop1ByOrderByIdDesc();

        ArrayList<Map<String, Object>> resourceCriticalValue = (ArrayList<Map<String, Object>>) policyMap.get("resourceAll");

        for(Map<String, Object> item : resourceCriticalValue){
            MappingCriticalValue criticalEntity = new MappingCriticalValue();
            criticalEntity.setDefaultId(lastCriticalEntity.getId());
            criticalEntity.setResourceInfo((Integer) item.get("resource_info"));
            criticalEntity.setResourceWarning((Integer) item.get("resource_warning"));
            criticalEntity.setResourceDanger((Integer) item.get("resource_danger"));
            criticalEntity.setResourceFatal((Integer) item.get("resource_fatal"));

            criticalEntity.setResourceInfoDuration((Integer) item.get("resource_info_duration"));
            criticalEntity.setResourceWarningDuration((Integer) item.get("resource_warning_duration"));
            criticalEntity.setResourceDangerDuration((Integer) item.get("resource_danger_duration"));
            criticalEntity.setResourceFatalDuration((Integer) item.get("resource_fatal_duration"));
            criticalEntity.setResourceType(AlarmType2Dept.valueOfNameCode((String) item.get("resource_code")).getIndexNum());

            mappingCriticalValueRepository.save(criticalEntity);
        }
    }

    private Map<String, Object> defaultDataSetting(InfoCriticalDefault lastCriticalEntity, AlarmType1Dept dept1){
        Map<String, Object> resultMap = new HashMap<>();

        if(lastCriticalEntity != null){
            List<MappingCriticalValue> criticalValueList = mappingCriticalValueRepository.findAllByDefaultId(lastCriticalEntity.getId());

            ArrayList<Map> resourceList = new ArrayList<>();
            for (MappingCriticalValue criticalValueItem : criticalValueList){
                Map<String, Object> valueMap = new HashMap<>();
                AlarmType2Dept dept2 = AlarmType2Dept.valueOfIndexNum(criticalValueItem.getResourceType());
                valueMap.put("resource", dept2.getCodeName());
                valueMap.put("resource_info", criticalValueItem.getResourceInfo());
                valueMap.put("resource_warning", criticalValueItem.getResourceWarning());
                valueMap.put("resource_danger", criticalValueItem.getResourceDanger());
                valueMap.put("resource_fatal", criticalValueItem.getResourceFatal());

                valueMap.put("resource_info_duration", criticalValueItem.getResourceInfoDuration());
                valueMap.put("resource_warning_duration", criticalValueItem.getResourceWarningDuration());
                valueMap.put("resource_danger_duration", criticalValueItem.getResourceDangerDuration());
                valueMap.put("resource_fatal_duration", criticalValueItem.getResourceFatalDuration());

                resourceList.add(valueMap);
            }

            resultMap.put("infoDefault", lastCriticalEntity);
            resultMap.put("resourceAll", resourceList);
            resultMap.put("1Dept", dept1.getCodeName());
        }else {
            resultMap.put("infoDefault", null);
            resultMap.put("resourceAll", null);
            resultMap.put("1Dept", null);
        }

        resultMap.put("lastId", infoCriticalRepository.findLastIdByResourceType(dept1.getIndexNum()));
        return resultMap;
    }

    private void saveCriticalDefaultByOneDept(String oneDept){
        InfoCriticalDefault newCriticalEntity = new InfoCriticalDefault();

        switch (oneDept){
            case "httpTransaction" :
                infoCriticalRepository.saveDefaultHttpTransaction(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
            case "HttpURI" :
                infoCriticalRepository.saveDefaultHttpUri(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
            case "l4TCP" :
                infoCriticalRepository.saveDefaultL4TCP(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
            case "l4UDP" :
                infoCriticalRepository.saveDefaultL4UDP(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
            case "l3IP" :
                infoCriticalRepository.saveDefaultL3IP(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
            case "traffic" :
                infoCriticalRepository.saveDefaultTraffic(AlarmType1Dept.valueOfNameCode(oneDept).getIndexNum());
                break;
        }
    }

}

