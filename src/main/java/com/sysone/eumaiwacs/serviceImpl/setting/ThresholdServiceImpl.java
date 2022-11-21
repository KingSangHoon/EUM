package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private AuditUtil auditUtil;

    @Autowired
    private InfoThresholdUsedRepository infoThresholdUsedRepository;
    @Autowired
    private InfoThresholdTransactionRepository infoThresholdTransactionRepository;
    @Autowired
    private InfoThresholdL4TcpRepository infoThresholdL4TcpRepository;
    @Autowired
    private InfoThresholdL4TcpErrorRepository infoThresholdL4TcpErrorRepository;
    @Autowired
    private InfoThresholdL4UdpRepository infoThresholdL4UdpRepository;
    @Autowired
    private InfoThresholdL3IpRepository infoThresholdL3IpRepository;
    @Autowired
    private InfoThresholdCommonRepository infoThresholdCommonRepository;
    @Autowired
    private InfoFieldPageRepository infoFieldPageRepository;
    @Autowired
    private InfoThresholdUriRepository infoThresholdUriRepository;
    @Autowired
    private InfoFieldUriRepository infoFieldUriRepository;
    @Autowired
    private InfoFieldL4TcpRepository infoFieldL4TcpRepository;
    @Autowired
    private InfoFieldL4UdpRepository infoFieldL4UdpRepository;
    @Autowired
    private InfoFieldL3IpRepository infoFieldL3IpRepository;
    @Autowired
    private InfoFieldCommonRepository infoFieldCommonRepository;

    @Override
    public Object findLatest(String category) {
        Map<String, Object> result = new HashMap<>();
        List<String> usedFieldList = new ArrayList<>();
        List<Integer> usedField;
        Integer thresholdId;

        if(category.equals("transaction")) {
            InfoThresholdTransaction infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionLatest();

            if(infoThresholdTransaction != null) {
                thresholdId = infoThresholdTransaction.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldPageRepository.findInfoFieldPageFieldViewNameByIdSet(usedField);
            }

            result.put("threshold", infoThresholdTransaction);
        } else if(category.equals("uri")) {
            InfoThresholdUri infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriLatest();

            if(infoThresholdUri != null) {
                thresholdId = infoThresholdUri.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldUriRepository.findInfoFieldUriFieldViewNameByIdSet(usedField);
            }

            result.put("threshold", infoThresholdUri);
        } else if(category.equals("tcp")) {
            InfoThresholdL4Tcp infoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpLatest();

            if(infoThresholdL4Tcp != null) {
                thresholdId = infoThresholdL4Tcp.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(usedField);

                if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                    InfoThresholdL4TcpError infoThresholdL4TcpError = infoThresholdL4TcpErrorRepository.findInfoThresholdL4TcpErrorById(thresholdId);
                    List<Integer> usedErrorField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, "tcperror");
                    List<String> usedErrorFieldList = new ArrayList<>();
                    if(usedErrorField.size() > 0) usedErrorFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(usedErrorField);

                    result.put("thresholdError", infoThresholdL4TcpError);
                    result.put("usedResourceError", usedErrorFieldList);
                }
            }

            result.put("threshold", infoThresholdL4Tcp);
        } else if(category.equals("udp")) {
            InfoThresholdL4Udp infoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpLatest();

            if(infoThresholdL4Udp != null) {
                thresholdId = infoThresholdL4Udp.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList= infoFieldL4UdpRepository.findInfoFieldL4UdpFieldViewNameByIdSet(usedField);
            }

            result.put("threshold", infoThresholdL4Udp);
        } else if(category.equals("ip")) {
            InfoThresholdL3Ip infoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpLatest();

            if(infoThresholdL3Ip != null) {
                thresholdId = infoThresholdL3Ip.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL3IpRepository.findInfoFieldL3IpFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdL3Ip);
        } else if(category.equals("traffic")) {
            InfoThresholdCommon infoThresholdCommon = infoThresholdCommonRepository.findInfoThresholdCommonLatest();

            if(infoThresholdCommon != null) {
                thresholdId = infoThresholdCommon.getId();
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, "common");
                if(usedField.size() > 0) usedFieldList = infoFieldCommonRepository.findInfoFieldCommonFieldViewNameByIdSet(usedField);
            }

            result.put("threshold", infoThresholdCommon);
        }
        result.put("usedResource", usedFieldList);
        return result;
    }

    @Override
    public Object findThreshold(String dir, String category, Integer id) {
        Map<String, Object> result = new HashMap<>();
        List<String> usedFieldList = new ArrayList<>();
        List<Integer> usedField;
        Boolean isFirst = false;
        Boolean isLatest = false;
        Integer thresholdId;
        Integer firstThresholdId;
        Integer latestThresholdId;

        if(category.equals("transaction")) {
            firstThresholdId = infoThresholdTransactionRepository.findInfoThresholdTransactionFirst().getId();
            latestThresholdId = infoThresholdTransactionRepository.findInfoThresholdTransactionLatest().getId();

            InfoThresholdTransaction infoThresholdTransaction = null;

            if(dir.equals("prev")) infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionPrev(id);
            else if (dir.equals("next")) infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionNext(id);

            if(infoThresholdTransaction != null) {
                thresholdId = infoThresholdTransaction.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldPageRepository.findInfoFieldPageFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdTransaction);
        } else if(category.equals("uri")) {
            firstThresholdId = infoThresholdUriRepository.findInfoThresholdUriFirst().getId();
            latestThresholdId = infoThresholdUriRepository.findInfoThresholdUriLatest().getId();

            InfoThresholdUri infoThresholdUri = null;

            if(dir.equals("prev")) infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriPrev(id);
            else if(dir.equals("next")) infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriNext(id);

            if(infoThresholdUri != null) {
                thresholdId = infoThresholdUri.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldUriRepository.findInfoFieldUriFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdUri);
        } else if(category.equals("tcp")) {
            firstThresholdId = infoThresholdL4TcpRepository.findInfoThresholdL4TcpFirst().getId();
            latestThresholdId = infoThresholdL4TcpRepository.findInfoThresholdL4TcpLatest().getId();

            InfoThresholdL4Tcp infoThresholdL4Tcp = null;

            if(dir.equals("prev")) infoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpPrev(id);
            else if(dir.equals("next")) infoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpNext(id);

            if(infoThresholdL4Tcp != null) {
                thresholdId = infoThresholdL4Tcp.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(usedField);

                if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                    InfoThresholdL4TcpError infoThresholdL4TcpError = infoThresholdL4TcpErrorRepository.findInfoThresholdL4TcpErrorById(thresholdId);
                    List<Integer> usedErrorField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, "tcperror");
                    List<String> usedErrorFieldList = new ArrayList<>();
                    if(usedErrorField.size() > 0) usedErrorFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(usedErrorField);

                    result.put("thresholdError", infoThresholdL4TcpError);
                    result.put("usedResourceError", usedErrorFieldList);
                }
            }
            result.put("threshold", infoThresholdL4Tcp);
        } else if(category.equals("udp")) {
            firstThresholdId = infoThresholdL4UdpRepository.findInfoThresholdL4UdpFirst().getId();
            latestThresholdId = infoThresholdL4UdpRepository.findInfoThresholdL4UdpLatest().getId();

            InfoThresholdL4Udp infoThresholdL4Udp = null;

            if(dir.equals("prev")) infoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpPrev(id);
            else if(dir.equals("next")) infoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpNext(id);

            if(infoThresholdL4Udp != null) {
                thresholdId = infoThresholdL4Udp.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL4UdpRepository.findInfoFieldL4UdpFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdL4Udp);
        } else if(category.equals("ip")){
            firstThresholdId = infoThresholdL3IpRepository.findInfoThresholdL3IpFirst().getId();
            latestThresholdId = infoThresholdL3IpRepository.findInfoThresholdL3IpLatest().getId();

            InfoThresholdL3Ip infoThresholdL3Ip = null;

            if(dir.equals("prev")) infoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpPrev(id);
            else if(dir.equals("next")) infoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpNext(id);

            if(infoThresholdL3Ip != null) {
                thresholdId = infoThresholdL3Ip.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL3IpRepository.findInfoFieldL3IpFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdL3Ip);
        } else if(category.equals("traffic")) {
            firstThresholdId = infoThresholdCommonRepository.findInfoThresholdCommonFirst().getId();
            latestThresholdId = infoThresholdCommonRepository.findInfoThresholdCommonLatest().getId();

            InfoThresholdCommon infoThresholdCommon = null;

            if(dir.equals("prev")) infoThresholdCommon = infoThresholdCommonRepository.findInfoThresholdCommonPrev(id);
            else if(dir.equals("next")) infoThresholdCommon = infoThresholdCommonRepository.findInfoThresholdCommonNext(id);

            if(infoThresholdCommon != null) {
                thresholdId = infoThresholdCommon.getId();
                if(thresholdId == firstThresholdId) isFirst = true;
                else if(thresholdId == latestThresholdId) isLatest = true;
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(thresholdId, "common");
                if(usedField.size() > 0) usedFieldList = infoFieldCommonRepository.findInfoFieldCommonFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdCommon);
        }

        result.put("usedResource", usedFieldList);
        result.put("isFirst", isFirst);
        result.put("isLatest", isLatest);
        return result;
    }

    @Override
    @Transactional
    public Object insertThreshold(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Object result = new Object();

        String loginId = loginUser.getLoginId();

        Map<String, Object> data = (Map<String, Object>) param.get("data");
        List<String> activeField = (List<String>) param.get("activeField");

        Map<String, Object> errorData = (Map<String, Object>) param.get("errorData");
        List<String> errorField = (List<String>) param.get("errorField");

        Integer thresholdId;

        Boolean isActiveField = false;
        if(activeField != null && activeField.size() > 0)  isActiveField = true;

        ObjectMapper objectMapper = new ObjectMapper();

        if(category.equals("transaction")) {
            InfoThresholdTransaction infoThresholdTransaction = objectMapper.convertValue(data, InfoThresholdTransaction.class);
            infoThresholdTransaction.setDeleted(false);
            infoThresholdTransaction.setRegDate(LocalDateTime.now());
            infoThresholdTransaction.setFirstWriter(loginId);

            InfoThresholdTransaction insertInfoThresholdTransaction = infoThresholdTransactionRepository.save(infoThresholdTransaction);

            if(isActiveField) {
                thresholdId = insertInfoThresholdTransaction.getId();
                for(String fieldName : activeField) {
                    InfoFieldPage fieldPage = infoFieldPageRepository.findInfoFieldPageByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldPage.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdTransaction;
        } else if(category.equals("uri")) {
            InfoThresholdUri infoThresholdUri = objectMapper.convertValue(data, InfoThresholdUri.class);
            infoThresholdUri.setDeleted(false);
            infoThresholdUri.setRegDate(LocalDateTime.now());
            infoThresholdUri.setFirstWriter(loginId);

            InfoThresholdUri insertInfoThresholdUri = infoThresholdUriRepository.save(infoThresholdUri);

            if(isActiveField) {
                thresholdId = insertInfoThresholdUri.getId();
                for(String fieldName : activeField) {
                    InfoFieldUri fieldUri = infoFieldUriRepository.findInfoFieldUriByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldUri.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdUri;
        } else if(category.equals("tcp")) {
            InfoThresholdL4Tcp infoThresholdL4Tcp = objectMapper.convertValue(data, InfoThresholdL4Tcp.class);
            infoThresholdL4Tcp.setDeleted(false);
            infoThresholdL4Tcp.setRegDate(LocalDateTime.now());
            infoThresholdL4Tcp.setFirstWriter(loginId);

            InfoThresholdL4Tcp insertInfoThresholdL4Tcp = infoThresholdL4TcpRepository.save(infoThresholdL4Tcp);
            thresholdId = insertInfoThresholdL4Tcp.getId();

            if(isActiveField) {
                for(String fieldName : activeField) {
                    InfoFieldL4Tcp fieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }

            if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                ObjectMapper errorObjectMapper = new ObjectMapper();
                InfoThresholdL4TcpError infoThresholdL4TcpError = errorObjectMapper.convertValue(errorData, InfoThresholdL4TcpError.class);
                infoThresholdL4TcpError.setId(thresholdId);

                infoThresholdL4TcpErrorRepository.save(infoThresholdL4TcpError);

                for(String fieldName : errorField) {
                    InfoFieldL4Tcp errorFieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("tcperror");
                    infoThresholdUsed.setFieldId(errorFieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdL4Tcp;
        } else if(category.equals("udp")) {
            InfoThresholdL4Udp infoThresholdL4Udp = objectMapper.convertValue(data, InfoThresholdL4Udp.class);
            infoThresholdL4Udp.setDeleted(false);
            infoThresholdL4Udp.setRegDate(LocalDateTime.now());
            infoThresholdL4Udp.setFirstWriter(loginId);

            InfoThresholdL4Udp insertInfoThresholdL4Udp = infoThresholdL4UdpRepository.save(infoThresholdL4Udp);

            if(isActiveField) {
                thresholdId = insertInfoThresholdL4Udp.getId();
                for(String fieldName : activeField) {
                    InfoFieldL4Udp fieldL4Udp = infoFieldL4UdpRepository.findInfoFieldL4UdpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Udp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdL4Udp;
        } else if(category.equals("ip")) {
            InfoThresholdL3Ip infoThresholdL3Ip = objectMapper.convertValue(data, InfoThresholdL3Ip.class);
            infoThresholdL3Ip.setDeleted(false);
            infoThresholdL3Ip.setRegDate(LocalDateTime.now());
            infoThresholdL3Ip.setFirstWriter(loginId);

            InfoThresholdL3Ip insertInfoThreholdL3Ip = infoThresholdL3IpRepository.save(infoThresholdL3Ip);

            if(isActiveField) {
                thresholdId = infoThresholdL3Ip.getId();
                for(String fieldName : activeField) {
                    InfoFieldL3Ip infoFieldL3Ip = infoFieldL3IpRepository.findInfoFieldL3IpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(infoFieldL3Ip.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThreholdL3Ip;
        } else if(category.equals("traffic")) {
            InfoThresholdCommon infoThresholdCommon = objectMapper.convertValue(data, InfoThresholdCommon.class);
            infoThresholdCommon.setDeleted(false);
            infoThresholdCommon.setRegDate(LocalDateTime.now());
            infoThresholdCommon.setFirstWriter(loginId);

            InfoThresholdCommon insertInfoThresholdCommon = infoThresholdCommonRepository.save(infoThresholdCommon);

            if(isActiveField) {
                thresholdId = insertInfoThresholdCommon.getId();
                for(String fieldName : activeField) {
                    InfoFieldCommon fieldCommon = infoFieldCommonRepository.findInfoFieldCommonByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("common");
                    infoThresholdUsed.setFieldId(fieldCommon.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdCommon;
        }
        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "DEFAULT", category, null, req);
        return result;
    }

    @Override
    public List<Object> findAllPolicy(String category) {
        List<Object> result = new ArrayList<>();
        List<Object[]> thresholdList = new ArrayList<>();
        
        if(category.equals("transaction")) thresholdList = infoThresholdTransactionRepository.findAllInfoThresholdPolicyInfo();
        else if(category.equals("uri")) thresholdList = infoThresholdUriRepository.findAllInfoThresholdPolicyInfo();
        else if(category.equals("tcp")) thresholdList = infoThresholdL4TcpRepository.findAllInfoThresholdPolicyInfo();
        else if(category.equals("udp")) thresholdList = infoThresholdL4UdpRepository.findAllInfoThresholdPolicyInfo();
        else if(category.equals("ip")) thresholdList = infoThresholdL3IpRepository.findAllInfoThresholdPolicyInfo();
        else if(category.equals("traffic")) thresholdList = infoThresholdCommonRepository.findAllInfoThresholdPolicyInfo();

        if(thresholdList.size() > 0) {
            for(Object[] obj : thresholdList) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", obj[0]);
                map.put("regDate", obj[1]);
                map.put("modifyDate", obj[2]);
                map.put("firstWriter", obj[3]);
                map.put("lastWriter", obj[4]);
                map.put("policyName", Util.latin1ToUtf8(obj[5].toString()));
                result.add(map);
            }
        }
        
        return result;
    }

    @Override
    public Object findPolicy(String category, Integer id) {
        Map<String, Object> result = new HashMap<>();
        List<String> usedFieldList = new ArrayList<>();
        List<Integer> usedField;

        if(category.equals("transaction")) {
            InfoThresholdTransaction infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionById(id);
            if(infoThresholdTransaction.getPolicyName() != null) infoThresholdTransaction.setPolicyName(Util.latin1ToUtf8(infoThresholdTransaction.getPolicyName()));

            if(infoThresholdTransaction != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, category);
                if(usedField.size() > 0) usedFieldList = infoFieldPageRepository.findInfoFieldPageFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdTransaction);
        } else if(category.equals("uri")) {
            InfoThresholdUri infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriById(id);
            if(infoThresholdUri.getPolicyName() != null) infoThresholdUri.setPolicyName(Util.latin1ToUtf8(infoThresholdUri.getPolicyName()));

            if(infoThresholdUri != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, category);
                if(usedField.size() > 0) usedFieldList = infoFieldUriRepository.findInfoFieldUriFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdUri);
        } else if(category.equals("tcp")) {
            InfoThresholdL4Tcp infoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpById(id);
            if(infoThresholdL4Tcp.getPolicyName() != null) infoThresholdL4Tcp.setPolicyName(Util.latin1ToUtf8(infoThresholdL4Tcp.getPolicyName()));

            if(infoThresholdL4Tcp != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(usedField);

                if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                    InfoThresholdL4TcpError infoThresholdL4TcpError = infoThresholdL4TcpErrorRepository.findInfoThresholdL4TcpErrorById(id);
                    List<Integer> errorUsedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, "tcperror");
                    List<String> errorUsedFieldList = new ArrayList<>();
                    if(errorUsedField.size() > 0) errorUsedFieldList = infoFieldL4TcpRepository.findInfoFieldL4TcpFieldViewNameByIdSet(errorUsedField);
                    result.put("thresholdError", infoThresholdL4TcpError);
                    result.put("usedResourceError", errorUsedFieldList);
                }
            }
            result.put("threshold", infoThresholdL4Tcp);
        } else if(category.equals("udp")) {
            InfoThresholdL4Udp infoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpById(id);
            if(infoThresholdL4Udp.getPolicyName() != null) infoThresholdL4Udp.setPolicyName(Util.latin1ToUtf8(infoThresholdL4Udp.getPolicyName()));

            if(infoThresholdL4Udp != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL4UdpRepository.findInfoFieldL4UdpFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdL4Udp);
        } else if(category.equals("ip")) {
            InfoThresholdL3Ip infoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpById(id);
            if(infoThresholdL3Ip.getPolicyName() != null) infoThresholdL3Ip.setPolicyName(Util.latin1ToUtf8(infoThresholdL3Ip.getPolicyName()));

            if(infoThresholdL3Ip != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, category);
                if(usedField.size() > 0) usedFieldList = infoFieldL3IpRepository.findInfoFieldL3IpFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdL3Ip);
        } else if(category.equals("traffic")) {
            InfoThresholdCommon infoThresholdCommon = infoThresholdCommonRepository.findInfoThresholdCommonById(id);
            if(infoThresholdCommon.getPolicyName() != null) infoThresholdCommon.setPolicyName(Util.latin1ToUtf8(infoThresholdCommon.getPolicyName()));

            if(infoThresholdCommon != null) {
                usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdIdAndType(id, "common");
                if(usedField.size() > 0) usedFieldList = infoFieldCommonRepository.findInfoFieldCommonFieldViewNameByIdSet(usedField);
            }
            result.put("threshold", infoThresholdCommon);
        }
        result.put("usedResource", usedFieldList);
        return result;
    }

    @Override
    @Transactional
    public Object insertThresholdPolicy(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Object result = new Object();

        String loginId = loginUser.getLoginId();

        Map<String, Object> data = (Map<String, Object>) param.get("data");
        List<String> activeField = (List<String>) param.get("activeField");

        Map<String, Object> errorData = (Map<String, Object>) param.get("errorData");
        List<String> errorField = (List<String>) param.get("errorField");

        String policyName = (String) data.get("policyName");
        Integer thresholdId;

        Boolean isActiveField = false;
        if(activeField != null && activeField.size() > 0) isActiveField = true;

        ObjectMapper objectMapper = new ObjectMapper();

        if(category.equals("transaction")) {
            InfoThresholdTransaction infoThresholdTransaction = objectMapper.convertValue(data, InfoThresholdTransaction.class);
            infoThresholdTransaction.setDeleted(false);
            infoThresholdTransaction.setRegDate(LocalDateTime.now());
            infoThresholdTransaction.setFirstWriter(loginId);
            infoThresholdTransaction.setPolicyName(Util.utf8ToLatin1(infoThresholdTransaction.getPolicyName()));

            InfoThresholdTransaction insertInfoThresholdTransaction = infoThresholdTransactionRepository.save(infoThresholdTransaction);

            if(isActiveField) {
                thresholdId = insertInfoThresholdTransaction.getId();
                for(String fieldName : activeField) {
                    InfoFieldPage fieldPage = infoFieldPageRepository.findInfoFieldPageByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldPage.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdTransaction;
        } else if(category.equals("uri")) {
            InfoThresholdUri infoThresholdUri = objectMapper.convertValue(data, InfoThresholdUri.class);
            infoThresholdUri.setDeleted(false);
            infoThresholdUri.setRegDate(LocalDateTime.now());
            infoThresholdUri.setFirstWriter(loginId);
            infoThresholdUri.setPolicyName(Util.utf8ToLatin1(infoThresholdUri.getPolicyName()));

            InfoThresholdUri insertInfoThresholdUri = infoThresholdUriRepository.save(infoThresholdUri);

            if(isActiveField) {
                thresholdId = insertInfoThresholdUri.getId();
                for(String fieldName : activeField) {
                    InfoFieldUri fieldUri = infoFieldUriRepository.findInfoFieldUriByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldUri.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdUri;
        } else if(category.equals("tcp")) {
            InfoThresholdL4Tcp infoThresholdL4Tcp = objectMapper.convertValue(data, InfoThresholdL4Tcp.class);
            infoThresholdL4Tcp.setDeleted(false);
            infoThresholdL4Tcp.setRegDate(LocalDateTime.now());
            infoThresholdL4Tcp.setFirstWriter(loginId);
            infoThresholdL4Tcp.setPolicyName(Util.utf8ToLatin1(infoThresholdL4Tcp.getPolicyName()));

            policyName = infoThresholdL4Tcp.getPolicyName();

            InfoThresholdL4Tcp insertInfoThresholdL4Tcp = infoThresholdL4TcpRepository.save(infoThresholdL4Tcp);
            thresholdId = insertInfoThresholdL4Tcp.getId();

            if(isActiveField) {
                for(String fieldName : activeField) {
                    InfoFieldL4Tcp fieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }

            if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                ObjectMapper errorObjectMapper = new ObjectMapper();
                InfoThresholdL4TcpError infoThresholdL4TcpError = errorObjectMapper.convertValue(errorData, InfoThresholdL4TcpError.class);
                infoThresholdL4TcpError.setId(thresholdId);
                infoThresholdL4TcpErrorRepository.save(infoThresholdL4TcpError);

                for(String fieldName : errorField) {
                    InfoFieldL4Tcp errorFieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("tcperror");
                    infoThresholdUsed.setFieldId(errorFieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdL4Tcp;
        } else if(category.equals("udp")) {
            InfoThresholdL4Udp infoThresholdL4Udp = objectMapper.convertValue(data, InfoThresholdL4Udp.class);
            infoThresholdL4Udp.setDeleted(false);
            infoThresholdL4Udp.setRegDate(LocalDateTime.now());
            infoThresholdL4Udp.setFirstWriter(loginId);
            infoThresholdL4Udp.setPolicyName(Util.utf8ToLatin1(infoThresholdL4Udp.getPolicyName()));

            InfoThresholdL4Udp insertInfoThresholdL4Udp = infoThresholdL4UdpRepository.save(infoThresholdL4Udp);

            if(isActiveField) {
                thresholdId = insertInfoThresholdL4Udp.getId();
                for(String fieldName : activeField) {
                    InfoFieldL4Udp fieldL4Udp = infoFieldL4UdpRepository.findInfoFieldL4UdpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Udp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdL4Udp;
        } else if(category.equals("ip")) {
            InfoThresholdL3Ip infoThresholdL3Ip = objectMapper.convertValue(data, InfoThresholdL3Ip.class);
            infoThresholdL3Ip.setDeleted(false);
            infoThresholdL3Ip.setRegDate(LocalDateTime.now());
            infoThresholdL3Ip.setFirstWriter(loginId);
            infoThresholdL3Ip.setPolicyName(Util.utf8ToLatin1(infoThresholdL3Ip.getPolicyName()));

            InfoThresholdL3Ip insertInfoThresholdL3Ip = infoThresholdL3IpRepository.save(infoThresholdL3Ip);

            if(isActiveField) {
                thresholdId = insertInfoThresholdL3Ip.getId();
                for(String fieldName : activeField) {
                    InfoFieldL3Ip fieldL3Ip = infoFieldL3IpRepository.findInfoFieldL3IpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL3Ip.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdL3Ip;
        } else if(category.equals("traffic")) {
            InfoThresholdCommon infoThresholdCommon = objectMapper.convertValue(data, InfoThresholdCommon.class);
            infoThresholdCommon.setDeleted(false);
            infoThresholdCommon.setRegDate(LocalDateTime.now());
            infoThresholdCommon.setFirstWriter(loginId);
            infoThresholdCommon.setPolicyName(Util.utf8ToLatin1(infoThresholdCommon.getPolicyName()));

            InfoThresholdCommon insertInfoThresholdCommon = infoThresholdCommonRepository.save(infoThresholdCommon);

            if(isActiveField) {
                thresholdId = insertInfoThresholdCommon.getId();
                for(String fieldName : activeField) {
                    InfoFieldCommon fieldCommon = infoFieldCommonRepository.findInfoFieldCommonByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("common");
                    infoThresholdUsed.setFieldId(fieldCommon.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = insertInfoThresholdCommon;
        }
        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
        return result;
    }

    @Override
    @Transactional
    public Object updateThresholdPolicy(String category, Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Object result = new Object();

        String loginId = loginUser.getLoginId();

        Map<String, Object> data = (Map<String, Object>) param.get("data");
        List<String> activeField = (List<String>) param.get("activeField");

        Map<String, Object> errorData = (Map<String, Object>) param.get("errorData");
        List<String> errorField = (List<String>) param.get("errorField");

        Integer thresholdId = (Integer) data.get("id");
        String policyName = (String) data.get("policyName");

        Boolean isActiveField = false;
        if(activeField != null && activeField.size() > 0) isActiveField = true;

        ObjectMapper objectMapper = new ObjectMapper();

        if(category.equals("transaction")) {
            InfoThresholdTransaction originInfoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionById(thresholdId);

            InfoThresholdTransaction infoThresholdTransaction = objectMapper.convertValue(data, InfoThresholdTransaction.class);
            infoThresholdTransaction.setRegDate(originInfoThresholdTransaction.getRegDate());
            infoThresholdTransaction.setFirstWriter(originInfoThresholdTransaction.getFirstWriter());
            infoThresholdTransaction.setDeleted(false);
            infoThresholdTransaction.setModifyDate(LocalDateTime.now());
            infoThresholdTransaction.setLastWriter(loginId);
            infoThresholdTransaction.setPolicyName(Util.utf8ToLatin1(infoThresholdTransaction.getPolicyName()));

            InfoThresholdTransaction updateInfoThresholdTransaction = infoThresholdTransactionRepository.save(infoThresholdTransaction);

            if(isActiveField) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldPage fieldPage = infoFieldPageRepository.findInfoFieldPageByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldPage.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdTransaction;
        } else if(category.equals("uri")) {
            InfoThresholdUri originInfoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriById(thresholdId);

            InfoThresholdUri infoThresholdUri = objectMapper.convertValue(data, InfoThresholdUri.class);
            infoThresholdUri.setRegDate(originInfoThresholdUri.getRegDate());
            infoThresholdUri.setFirstWriter(originInfoThresholdUri.getFirstWriter());
            infoThresholdUri.setDeleted(false);
            infoThresholdUri.setModifyDate(LocalDateTime.now());
            infoThresholdUri.setLastWriter(loginId);
            infoThresholdUri.setPolicyName(Util.utf8ToLatin1(infoThresholdUri.getPolicyName()));

            InfoThresholdUri updateInfoThresholdUri = infoThresholdUriRepository.save(infoThresholdUri);

            if(isActiveField) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldUri fieldUri = infoFieldUriRepository.findInfoFieldUriByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldUri.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdUri;
        } else if(category.equals("tcp")) {
            InfoThresholdL4Tcp originInfoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpById(thresholdId);

            InfoThresholdL4Tcp infoThresholdL4Tcp = objectMapper.convertValue(data, InfoThresholdL4Tcp.class);
            infoThresholdL4Tcp.setRegDate(originInfoThresholdL4Tcp.getRegDate());
            infoThresholdL4Tcp.setFirstWriter(originInfoThresholdL4Tcp.getFirstWriter());
            infoThresholdL4Tcp.setDeleted(false);
            infoThresholdL4Tcp.setModifyDate(LocalDateTime.now());
            infoThresholdL4Tcp.setLastWriter(loginId);
            infoThresholdL4Tcp.setPolicyName(Util.utf8ToLatin1(infoThresholdL4Tcp.getPolicyName()));

            InfoThresholdL4Tcp updateInfoThresholdL4Tcp = infoThresholdL4TcpRepository.save(infoThresholdL4Tcp);

            if(isActiveField) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldL4Tcp fieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }

            if(infoThresholdL4Tcp.getIsTcpError() != null && infoThresholdL4Tcp.getIsTcpError() == true) {
                ObjectMapper errorObjectMapper = new ObjectMapper();
                InfoThresholdL4TcpError infoThresholdL4TcpError = errorObjectMapper.convertValue(errorData, InfoThresholdL4TcpError.class);
                infoThresholdL4TcpError.setId(thresholdId);
                infoThresholdL4TcpErrorRepository.save(infoThresholdL4TcpError);

                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdIdAndType(thresholdId, "tcperror");

                for(String fieldName : errorField) {
                    InfoFieldL4Tcp errorFieldL4Tcp = infoFieldL4TcpRepository.findInfoFieldL4TcpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("tcperror");
                    infoThresholdUsed.setFieldId(errorFieldL4Tcp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdL4Tcp;
        } else if(category.equals("udp")) {
            InfoThresholdL4Udp originInfoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpById(thresholdId);

            InfoThresholdL4Udp infoThresholdL4Udp = objectMapper.convertValue(data, InfoThresholdL4Udp.class);
            infoThresholdL4Udp.setRegDate(originInfoThresholdL4Udp.getRegDate());
            infoThresholdL4Udp.setFirstWriter(originInfoThresholdL4Udp.getFirstWriter());
            infoThresholdL4Udp.setDeleted(false);
            infoThresholdL4Udp.setModifyDate(LocalDateTime.now());
            infoThresholdL4Udp.setLastWriter(loginId);
            infoThresholdL4Udp.setPolicyName(Util.utf8ToLatin1(infoThresholdL4Udp.getPolicyName()));

            InfoThresholdL4Udp updateInfoThresholdL4Udp = infoThresholdL4UdpRepository.save(infoThresholdL4Udp);

            if(isActiveField) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldL4Udp fieldL4Udp = infoFieldL4UdpRepository.findInfoFieldL4UdpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL4Udp.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdL4Udp;
        } else if(category.equals("ip")) {
            InfoThresholdL3Ip originInfoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpById(thresholdId);

            InfoThresholdL3Ip infoThresholdL3Ip = objectMapper.convertValue(data, InfoThresholdL3Ip.class);
            infoThresholdL3Ip.setRegDate(originInfoThresholdL3Ip.getRegDate());
            infoThresholdL3Ip.setFirstWriter(originInfoThresholdL3Ip.getFirstWriter());
            infoThresholdL3Ip.setDeleted(false);
            infoThresholdL3Ip.setModifyDate(LocalDateTime.now());
            infoThresholdL3Ip.setLastWriter(loginId);
            infoThresholdL3Ip.setPolicyName(Util.utf8ToLatin1(infoThresholdL3Ip.getPolicyName()));

            InfoThresholdL3Ip updateInfoThresholdL3Ip = infoThresholdL3IpRepository.save(infoThresholdL3Ip);

            if(isActiveField) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldL3Ip fieldL3Ip = infoFieldL3IpRepository.findInfoFieldL3IpByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType(category);
                    infoThresholdUsed.setFieldId(fieldL3Ip.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdL3Ip;
        } else if(category.equals("traffic")) {
            InfoThresholdCommon originInfoThreshlodCommon = infoThresholdCommonRepository.findInfoThresholdCommonById(thresholdId);

            InfoThresholdCommon infoThresholdCommon = objectMapper.convertValue(data, InfoThresholdCommon.class);
            infoThresholdCommon.setRegDate(originInfoThreshlodCommon.getRegDate());
            infoThresholdCommon.setFirstWriter(originInfoThreshlodCommon.getFirstWriter());
            infoThresholdCommon.setDeleted(false);
            infoThresholdCommon.setModifyDate(LocalDateTime.now());
            infoThresholdCommon.setLastWriter(loginId);
            infoThresholdCommon.setPolicyName(Util.utf8ToLatin1(infoThresholdCommon.getPolicyName()));

            InfoThresholdCommon updateInfoThresholdCommon = infoThresholdCommonRepository.save(infoThresholdCommon);

            if(isActiveField) {
                infoThresholdCommonRepository.deleteInfoThresholdCommonById(thresholdId);

                for(String fieldName : activeField) {
                    InfoFieldCommon fieldCommon = infoFieldCommonRepository.findInfoFieldCommonByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("common");
                    infoThresholdUsed.setFieldId(fieldCommon.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            result = updateInfoThresholdCommon;
        }
        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
        return result;
    }


    @Override
    @Transactional
    public void deleteThresholdPolicy(String category, String idStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = Util.getStringToIntegerSet(idStr);

        infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdIdSet(idSet);

        if(category.equals("transaction")) {
            for(Integer id : idSet) {
                InfoThresholdTransaction infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdTransaction.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdTransactionRepository.deleteInfoThresholdTransactionByIdSet(idSet);
        }
        else if(category.equals("uri")) {
            for(Integer id : idSet) {
                InfoThresholdUri infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdUri.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdUriRepository.deleteInfoThresholdUriByIdSet(idSet);
        }
        else if(category.equals("udp")) {
            for(Integer id : idSet) {
                InfoThresholdL4Udp infoThresholdL4Udp = infoThresholdL4UdpRepository.findInfoThresholdL4UdpById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdL4Udp.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdL4UdpRepository.deleteInfoThresholdL4UdpByIdSet(idSet);
        }
        else if(category.equals("ip")) {
            for(Integer id : idSet) {
                InfoThresholdL3Ip infoThresholdL3Ip = infoThresholdL3IpRepository.findInfoThresholdL3IpById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdL3Ip.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdL3IpRepository.deleteInfoThresholdL3IpByIdSet(idSet);
        }
        else if(category.equals("traffic")) {
            for(Integer id : idSet) {
                InfoThresholdCommon infoThresholdCommon = infoThresholdCommonRepository.findInfoThresholdCommonById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdCommon.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdCommonRepository.deleteInfoThresholdCommonByIdSet(idSet);
        }
        else if(category.equals("tcp")) {
            for(Integer id : idSet) {
                InfoThresholdL4Tcp infoThresholdL4Tcp = infoThresholdL4TcpRepository.findInfoThresholdL4TcpById(id);
                String policyName = Util.latin1ToUtf8(infoThresholdL4Tcp.getPolicyName());
                auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_LEVEL, "정책", category, policyName, req);
            }
            infoThresholdL4TcpRepository.deleteInfoThresholdL4TcpByIdSet(idSet);
            infoThresholdL4TcpErrorRepository.deleteInfoThresholdL4TcpErrorByIdSet(idSet);
        }
    }

}
