package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepositoryCustom;
import com.sysone.eumaiwacs.repository.realtime.RealtimeUriRepository;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AuditUtil auditUtil;

    @Autowired
    private RealtimeUriRepository realtimeUriRepository;

    @Autowired
    private InfoTransactionRepository infoTransactionRepository;
    @Autowired
    private MappingTransactionUriRepository mappingTransactionUriRepository;
    @Autowired
    private MappingTransactionCookieRepository mappingTransactionCookieRepository;
    @Autowired
    private MappingTransactionResponseBodyRepository mappingTransactionResponseBodyRepository;
    @Autowired
    private MappingTransactionHeaderRepository mappingTransactionHeaderRepository;

    @Autowired
    private InfoThresholdTransactionRepository infoThresholdTransactionRepository;
    @Autowired
    private InfoThresholdUriRepository infoThresholdUriRepository;
    @Autowired
    private InfoThresholdUsedRepository infoThresholdUsedRepository;
    @Autowired
    private InfoFieldPageRepository infoFieldPageRepository;
    @Autowired
    private InfoFieldUriRepository infoFieldUriRepository;

    @Autowired
    private RealtimePageRepositoryCustom realtimePageRepositoryCustom;

    @Override
    public List<Object> search(Map<String, Object> param) {
        List<Object> result = new ArrayList<>();

        String type = (String) param.get("type");
        String category = (String) param.get("category");
        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        Boolean isSearchTxt = (Boolean) param.get("isSearchTxt");
        String searchType = param.get("searchType") == null ? null : (String) param.get("searchType");
        String searchTxt = param.get("searchTxt") == null ? null : (String) param.get("searchTxt");

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        if(isSearchTxt == false) searchType = "all";

        Integer searchInteger = 0;
        if(searchType.equals("srcPort") || searchType.equals("dstPort")) searchInteger = Integer.parseInt(searchTxt);

        if(type.equals("url")) {
            if(category.equals("url")) result = realtimePageRepositoryCustom.searchTransactionInfo(searchType, beginDate, endDate, searchTxt, searchInteger);
            else if(category.equals("host")) result = realtimePageRepositoryCustom.searchTransactionHttpHostInfo(searchType, beginDate, endDate, searchTxt, searchInteger);
            else if(category.equals("dst")) result = realtimePageRepositoryCustom.searchTransactionDstInfo(searchType, beginDate, endDate, searchTxt, searchInteger);
            else if(category.equals("src")) result = realtimePageRepositoryCustom.searchTransactionSrcInfo(searchType, beginDate, endDate, searchTxt, searchInteger);
        } else if(category.equals("uri")) {

        }

        return result;
    }

    @Override
    public List<Object> findChkUri(Map<String, Object> param) {
        List<Object> result = new ArrayList<>();

        String begin = param.get("begin").toString();
        String end = param.get("end").toString();
        String url = param.get("url").toString();

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        List<Object[]> resultList = realtimeUriRepository.findReferTransactionUri(beginDate, endDate, url);

        if(resultList.size() > 0) {
            for(Object[] obj : resultList) {
                Map<String, Object> map = new HashMap<>();
                map.put("httpHost", obj[0]);
                map.put("httpUri", obj[1]);
                map.put("httpUriSplit", obj[2]);
                map.put("dstIp", obj[3]);
                map.put("dstPort", obj[4]);
                map.put("srcIp", obj[5]);
                map.put("srcPort", obj[6]);
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<InfoTransaction> findAll() {
        List<InfoTransaction> result = infoTransactionRepository.findAllTransactionNotDeleted();

        if(result.size() > 0) {
            for(InfoTransaction tr : result) {
                tr.setUrlAlias(Util.latin1ToUtf8(tr.getUrlAlias()));
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> find(Integer id) {
        Map<String, Object> result = new HashMap<>();

        InfoTransaction infoTransaction = infoTransactionRepository.findInfoTransactionById(id);
        infoTransaction.setUrlAlias(Util.latin1ToUtf8(infoTransaction.getUrlAlias()));

        List<MappingTransactionUri> mappingUri = mappingTransactionUriRepository.findMappingTransactionUriByUrlId(id);
        List<MappingTransactionCookie> mappingCookie = mappingTransactionCookieRepository.findMappingTransactionCookieByUrlId(id);
        List<MappingTransactionResponseBody> mappingResponseBody = mappingTransactionResponseBodyRepository.findMappingTransactionResponseBodyByUrlId(id);
        List<MappingTransactionHeader> mappingHeader = mappingTransactionHeaderRepository.findMappingTransactionHeaderByUrlId(id);

        //사용자 정의 임계치일 때
        if(infoTransaction.getTransactionThresholdType() == 2) {
            List<String> usedFieldList = new ArrayList<>();
            Integer thresholdId = infoTransaction.getTransactionThresholdId();
            InfoThresholdTransaction infoThresholdTransaction = infoThresholdTransactionRepository.findInfoThresholdTransactionById(thresholdId);
            List<Integer> usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdId(thresholdId);
            if(usedField.size() > 0) usedFieldList = infoFieldPageRepository.findInfoFieldPageFieldViewNameByIdSet(usedField);

            result.put("thresholdTransaction", infoThresholdTransaction);
            result.put("usedResourceTransaction", usedFieldList);
        }

        if(infoTransaction.getUriThresholdType() == 2) {
            List<String> usedFieldList = new ArrayList<>();
            Integer thresholdId = infoTransaction.getUriThresholdId();
            InfoThresholdUri infoThresholdUri = infoThresholdUriRepository.findInfoThresholdUriById(thresholdId);
            List<Integer> usedField = infoThresholdUsedRepository.findInfoThresholdUsedFieldIdByThresholdId(thresholdId);
            if(usedField.size() > 0) usedFieldList = infoFieldUriRepository.findInfoFieldUriFieldViewNameByIdSet(usedField);

            result.put("thresholdUri", infoThresholdUri);
            result.put("usedResourceUri", usedFieldList);
        }

        result.put("transaction", infoTransaction);
        result.put("mappingUri", mappingUri);
        result.put("mappingCookie", mappingCookie);
        result.put("mappingResponseBody", mappingResponseBody);
        result.put("mappingHeader", mappingHeader);

        return result;
    }

    @Transactional
    @Override
    public void delete(String idStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = Util.getStringToIntegerSet(idStr);

        //Mapping 정보 삭제 (URI/Cookie/Header/Body)
        mappingTransactionCookieRepository.deleteMappingTransactionCookieByurlIdSet(idSet);
        mappingTransactionHeaderRepository.deleteMappingTransactionHeaderByUrlIdSet(idSet);
        mappingTransactionResponseBodyRepository.deleteMappingTransactionResponseBodyByUrlIdSet(idSet);
        mappingTransactionUriRepository.deleteMappingTransactionUriByUrlIdSet(idSet);

        //사용자 임계치 정책 삭제
        for(Integer id : idSet) {
            InfoTransaction infoTransaction = infoTransactionRepository.findInfoTransactionById(id);

            String alias = Util.utf8ToLatin1(infoTransaction.getUrlAlias());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_TRANSACTION, null, null, alias, req);

            if(infoTransaction.getTransactionThresholdType() == 2) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(infoTransaction.getTransactionThresholdId());
                infoThresholdTransactionRepository.deleteInfoThresholdTransactionById(infoTransaction.getTransactionThresholdId());
            }

            if(infoTransaction.getUriThresholdType() == 2) {
                infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(infoTransaction.getUriThresholdId());
                infoThresholdUriRepository.deleteInfoThresholdUriById(infoTransaction.getUriThresholdId());
            }
        }

        //Transaction Update Delete TRUE
        infoTransactionRepository.deleteByIdSet(idSet, LocalDateTime.now(), loginUser.getLoginId());
    }

    @Transactional
    @Override
    public Object insert(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Map<String, Object> transactionMap = (Map<String, Object>) param.get("transaction");
        List<Map<String, Object>> mappingUri = (List<Map<String, Object>>) param.get("mappingUri");
        List<Map<String, Object>> mappingCookie = (List<Map<String, Object>>) param.get("mappingCookie");
        List<Map<String, Object>> mappingResponseBody = (List<Map<String, Object>>) param.get("mappingResponseBody");
        List<Map<String, Object>> mappingRequestHeader = (List<Map<String, Object>>) param.get("mappingRequestHeader");
        List<Map<String, Object>> mappingResponseHeader = (List<Map<String, Object>>) param.get("mappingResponseHeader");

        Map<String, Object> transactionThreshold = (Map<String, Object>) param.get("transactionThreshold");
        List<String> transactionActiveField = (List<String>) param.get("transactionActiveField");

        Map<String, Object> uriThreshold = (Map<String, Object>) param.get("uriThreshold");
        List<String> uriActiveField = (List<String>) param.get("uriActiveField");

        ObjectMapper objectMapper = new ObjectMapper();
        InfoTransaction infoTransaction = objectMapper.convertValue(transactionMap, InfoTransaction.class);
        infoTransaction.setUrlAlias(Util.utf8ToLatin1(infoTransaction.getUrlAlias()));
        infoTransaction.setDeleted(false);
        infoTransaction.setFirstWriter(loginUser.getLoginId());
        infoTransaction.setRegDate(LocalDateTime.now());

        // Transaction 임계치정책 사용자 일 때
        if(infoTransaction.getTransactionThresholdType() == 2) {
            ObjectMapper thresholdObjectMapper = new ObjectMapper();
            InfoThresholdTransaction infoThresholdTransaction = thresholdObjectMapper.convertValue(transactionThreshold, InfoThresholdTransaction.class);
            infoThresholdTransaction.setType(2); //사용자 정의
            infoThresholdTransaction.setDeleted(false);
            infoThresholdTransaction.setFirstWriter(loginUser.getLoginId());
            infoThresholdTransaction.setRegDate(LocalDateTime.now());

            InfoThresholdTransaction insertInfoThresholdTransaction = infoThresholdTransactionRepository.save(infoThresholdTransaction);
            Integer thresholdId = insertInfoThresholdTransaction.getId();

            if(transactionActiveField.size() > 0 && transactionActiveField != null) {
                for(String fieldName : transactionActiveField) {
                    InfoFieldPage fieldPage = infoFieldPageRepository.findInfoFieldPageByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("transaction");
                    infoThresholdUsed.setFieldId(fieldPage.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            infoTransaction.setTransactionThresholdId(thresholdId);
        }

        // URI 임계치정책 사용자 일 때
        if(infoTransaction.getUriThresholdType() == 2) {
            ObjectMapper thresholdObjectMapper = new ObjectMapper();
            InfoThresholdUri infoThresholdUri = thresholdObjectMapper.convertValue(uriThreshold, InfoThresholdUri.class);
            infoThresholdUri.setType(2); //사용자 정의
            infoThresholdUri.setDeleted(false);
            infoThresholdUri.setFirstWriter(loginUser.getLoginId());
            infoThresholdUri.setRegDate(LocalDateTime.now());

            InfoThresholdUri insertInfoThresholdUri = infoThresholdUriRepository.save(infoThresholdUri);
            Integer thresholdId = insertInfoThresholdUri.getId();

            if(uriActiveField.size() > 0 && uriActiveField != null) {
                for(String fieldName : uriActiveField) {
                    InfoFieldUri fieldUri = infoFieldUriRepository.findInfoFieldUriByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("uri");
                    infoThresholdUsed.setFieldId(fieldUri.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            infoTransaction.setUriThresholdId(thresholdId);
        }

        InfoTransaction insertInfoTransaction = infoTransactionRepository.save(infoTransaction);
        insertInfoTransaction.setUrlAlias(Util.latin1ToUtf8(insertInfoTransaction.getUrlAlias()));
        Integer urlId = insertInfoTransaction.getUrlId();

        if(infoTransaction.getIsUriChk() == true) {
            if(mappingUri != null) {
                for(Map<String, Object> map : mappingUri) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionUri uri = mappingObjectMapper.convertValue(map, MappingTransactionUri.class);
                    uri.setUrlId(urlId);
                    mappingTransactionUriRepository.save(uri);
                }
            }
        }

        if(infoTransaction.getIsRequestHeader() == true) {
            if(mappingRequestHeader != null) {
                for(Map<String, Object> map : mappingRequestHeader) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionHeader header = mappingObjectMapper.convertValue(map, MappingTransactionHeader.class);
                    header.setUrlId(urlId);
                    header.setHeaderType("request");
                    mappingTransactionHeaderRepository.save(header);
                }
            }
        }

        if(infoTransaction.getIsResponseHeader() == true) {
            if(mappingResponseHeader != null) {
                for(Map<String, Object> map : mappingResponseHeader) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionHeader header = mappingObjectMapper.convertValue(map, MappingTransactionHeader.class);
                    header.setUrlId(urlId);
                    header.setHeaderType("response");
                    mappingTransactionHeaderRepository.save(header);
                }
            }
        }

        if(infoTransaction.getIsCookie() == true) {
            if(mappingCookie != null) {
                for(Map<String, Object> map : mappingCookie) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionCookie cookie = mappingObjectMapper.convertValue(map, MappingTransactionCookie.class);
                    cookie.setUrlId(urlId);
                    mappingTransactionCookieRepository.save(cookie);
                }
            }
        }

        if(infoTransaction.getIsResponseBody() == true) {
            if(mappingResponseBody != null) {
                for(Map<String, Object> map : mappingResponseBody) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionResponseBody responseBody = mappingObjectMapper.convertValue(map, MappingTransactionResponseBody.class);
                    responseBody.setUrlId(urlId);
                    mappingTransactionResponseBodyRepository.save(responseBody);
                }
            }
        }

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_TRANSACTION, null, null, infoTransaction.getUrlAlias(), req);

        Map<String, Object> result = new HashMap<>();
        result.put("transaction", insertInfoTransaction);
        return result;
    }

    @Override
    public Object update(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Map<String, Object> transactionMap = (Map<String, Object>) param.get("transaction");
        List<Map<String, Object>> mappingUri = (List<Map<String, Object>>) param.get("mappingUri");
        List<Map<String, Object>> mappingCookie = (List<Map<String, Object>>) param.get("mappingCookie");
        List<Map<String, Object>> mappingResponseBody = (List<Map<String, Object>>) param.get("mappingResponseBody");
        List<Map<String, Object>> mappingRequestHeader = (List<Map<String, Object>>) param.get("mappingRequestHeader");
        List<Map<String, Object>> mappingResponseHeader = (List<Map<String, Object>>) param.get("mappingResponseHeader");

        Map<String, Object> transactionThreshold = (Map<String, Object>) param.get("transactionThreshold");
        List<String> transactionActiveField = (List<String>) param.get("transactionActiveField");

        Map<String, Object> uriThreshold = (Map<String, Object>) param.get("uriThreshold");
        List<String> uriActiveField = (List<String>) param.get("uriActiveField");

        Integer urlId = (Integer) transactionMap.get("urlId");
        InfoTransaction originTransaction = infoTransactionRepository.findInfoTransactionById(urlId);

        ObjectMapper objectMapper = new ObjectMapper();
        InfoTransaction infoTransaction = objectMapper.convertValue(transactionMap, InfoTransaction.class);
        infoTransaction.setUrlAlias(Util.utf8ToLatin1(infoTransaction.getUrlAlias()));
        infoTransaction.setDeleted(false);
        infoTransaction.setFirstWriter(originTransaction.getFirstWriter());
        infoTransaction.setRegDate(originTransaction.getRegDate());
        infoTransaction.setLastWriter(loginUser.getLoginId());
        infoTransaction.setModifyDate(LocalDateTime.now());

        // 이전 Transaction 임계치정책 사용자 일 때 이전 사용자정책 삭제
        if(originTransaction.getTransactionThresholdType() == 2) {
            infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(originTransaction.getTransactionThresholdId());
            infoThresholdTransactionRepository.deleteInfoThresholdTransactionById(originTransaction.getTransactionThresholdId());
        }

        // Transaction 임계치정책 사용자 일 때
        if(infoTransaction.getTransactionThresholdType() == 2) {
            ObjectMapper thresholdObjectMapper = new ObjectMapper();
            InfoThresholdTransaction infoThresholdTransaction = thresholdObjectMapper.convertValue(transactionThreshold, InfoThresholdTransaction.class);
            infoThresholdTransaction.setType(2); //사용자 정의
            infoThresholdTransaction.setDeleted(false);
            infoThresholdTransaction.setFirstWriter(loginUser.getLoginId());
            infoThresholdTransaction.setRegDate(LocalDateTime.now());

            InfoThresholdTransaction insertInfoThresholdTransaction = infoThresholdTransactionRepository.save(infoThresholdTransaction);
            Integer thresholdId = insertInfoThresholdTransaction.getId();

            if(transactionActiveField.size() > 0 && transactionActiveField != null) {
                for(String fieldName : transactionActiveField) {
                    InfoFieldPage fieldPage = infoFieldPageRepository.findInfoFieldPageByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("transaction");
                    infoThresholdUsed.setFieldId(fieldPage.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            infoTransaction.setTransactionThresholdId(thresholdId);
        }

        // 이전 URI 임계치정책 사용자 일 때 이전 사용자정책 삭제
        if(originTransaction.getUriThresholdType() == 2) {
            infoThresholdUsedRepository.deleteInfoThresholdUsedByThresholdId(infoTransaction.getUriThresholdId());
            infoThresholdUriRepository.deleteInfoThresholdUriById(infoTransaction.getUriThresholdId());
        }

        // URI 임계치정책 사용자 일 때
        if(infoTransaction.getUriThresholdType() == 2) {
            ObjectMapper thresholdObjectMapper = new ObjectMapper();
            InfoThresholdUri infoThresholdUri = thresholdObjectMapper.convertValue(uriThreshold, InfoThresholdUri.class);
            infoThresholdUri.setType(2); //사용자 정의
            infoThresholdUri.setDeleted(false);
            infoThresholdUri.setFirstWriter(loginUser.getLoginId());
            infoThresholdUri.setRegDate(LocalDateTime.now());

            InfoThresholdUri insertInfoThresholdUri = infoThresholdUriRepository.save(infoThresholdUri);
            Integer thresholdId = insertInfoThresholdUri.getId();

            if(uriActiveField.size() > 0 && uriActiveField != null) {
                for(String fieldName : uriActiveField) {
                    InfoFieldUri fieldUri = infoFieldUriRepository.findInfoFieldUriByFieldViewName(fieldName);
                    InfoThresholdUsed infoThresholdUsed = new InfoThresholdUsed();
                    infoThresholdUsed.setThresholdId(thresholdId);
                    infoThresholdUsed.setType("uri");
                    infoThresholdUsed.setFieldId(fieldUri.getId());
                    infoThresholdUsedRepository.save(infoThresholdUsed);
                }
            }
            infoTransaction.setUriThresholdId(thresholdId);
        }

        InfoTransaction updateInfoTransaction = infoTransactionRepository.save(infoTransaction);
        updateInfoTransaction.setUrlAlias(Util.latin1ToUtf8(updateInfoTransaction.getUrlAlias()));

        mappingTransactionUriRepository.deleteMappingTransactionUriByUrlId(urlId);
        mappingTransactionHeaderRepository.deleteMappingTransactionHeaderByUrlId(urlId);
        mappingTransactionCookieRepository.deleteMappingTransactionCookieByurlId(urlId);
        mappingTransactionResponseBodyRepository.deleteMappingTransactionResponseBodyByUrlId(urlId);

        if(infoTransaction.getIsUriChk() == true) {
            if(mappingUri != null) {
                for(Map<String, Object> map : mappingUri) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionUri uri = mappingObjectMapper.convertValue(map, MappingTransactionUri.class);
                    uri.setUrlId(urlId);
                    mappingTransactionUriRepository.save(uri);
                }
            }
        }

        if(infoTransaction.getIsRequestHeader() == true) {
            if(mappingRequestHeader != null) {
                for(Map<String, Object> map : mappingRequestHeader) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionHeader header = mappingObjectMapper.convertValue(map, MappingTransactionHeader.class);
                    header.setUrlId(urlId);
                    header.setHeaderType("request");
                    mappingTransactionHeaderRepository.save(header);
                }
            }
        }

        if(infoTransaction.getIsResponseHeader() == true) {
            if(mappingResponseHeader != null) {
                for(Map<String, Object> map : mappingResponseHeader) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionHeader header = mappingObjectMapper.convertValue(map, MappingTransactionHeader.class);
                    header.setUrlId(urlId);
                    header.setHeaderType("response");
                    mappingTransactionHeaderRepository.save(header);
                }
            }
        }

        if(infoTransaction.getIsCookie() == true) {
            if(mappingCookie != null) {
                for(Map<String, Object> map : mappingCookie) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionCookie cookie = mappingObjectMapper.convertValue(map, MappingTransactionCookie.class);
                    cookie.setUrlId(urlId);
                    mappingTransactionCookieRepository.save(cookie);
                }
            }
        }

        if(infoTransaction.getIsResponseBody() == true) {
            if(mappingResponseBody != null) {
                for(Map<String, Object> map : mappingResponseBody) {
                    ObjectMapper mappingObjectMapper = new ObjectMapper();
                    MappingTransactionResponseBody responseBody = mappingObjectMapper.convertValue(map, MappingTransactionResponseBody.class);
                    responseBody.setUrlId(urlId);
                    mappingTransactionResponseBodyRepository.save(responseBody);
                }
            }
        }

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_TRANSACTION, null, null, infoTransaction.getUrlAlias(), req);

        Map<String, Object> result = new HashMap<>();
        result.put("transaction", updateInfoTransaction);
        return result;
    }

}
