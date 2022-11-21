package com.sysone.eumaiwacs.serviceImpl.common;

import com.sysone.eumaiwacs.common.AlarmType1Dept;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.entity.setting.Test;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepository;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepositoryCustom;
import com.sysone.eumaiwacs.repository.realtime.RealtimeUriRepository;
import com.sysone.eumaiwacs.repository.realtime.RealtimeUriRepositoryCustom;
import com.sysone.eumaiwacs.repository.setting.InfoHttpTypeRepository;
import com.sysone.eumaiwacs.service.common.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired private RealtimePageRepository realtimePageRepository;
    @Autowired private RealtimePageRepositoryCustom realtimePageRepositoryCustom;
    @Autowired private RealtimeUriRepository realtimeUriRepository;
    @Autowired private RealtimeUriRepositoryCustom realtimeUriRepositoryCustom;
    @Autowired private InfoHttpTypeRepository infoHttpTypeRepository;

    @Override
    public List<Object> searchHttpPageCategory(Map<String, Object> param) {

        List<Object> result = new ArrayList<>();

        String category = (String) param.get("category");
        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        if(category.equals("bothIp")) result.addAll(realtimePageRepository.searchHttpPageBothIp(beginDate, endDate));
        else if(category.equals("bothPort")) result.addAll(realtimePageRepository.searchHttpPageBothPort(beginDate, endDate));
        else if(category.equals("transaction")) {
            List<Object[]> transactionList = realtimePageRepository.searchHttpPageTransaction(beginDate, endDate);

            for(Object[] obj : transactionList) {
                Map<String, Object> map = new HashMap<>();
                map.put("transactionId", obj[0]);

                if(obj[1] != null) map.put("transactionName", Util.latin1ToUtf8(obj[1].toString()));
                else map.put("transactionName", "");
                result.add(map);
            }
        } else if(category.equals("httpMethod")) {
            result.addAll(infoHttpTypeRepository.findHttpTypeByName("HTTP Request Method"));
        } else if(category.equals("httpResponseCode")) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_1xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_1xx_DESC);
            List<InfoHttpType> resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 100, 200);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_2xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_2xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 200, 300);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_3xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_3xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 300, 400);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_4xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_4xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 400, 500);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_5xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_5xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 500, 600);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);
        } else {
            result = realtimePageRepositoryCustom.searchHttpPageCategory(category, beginDate, endDate);
        }

        return result;
    }

    @Override
    public List<Object> searchHttpUriCategory(Map<String, Object> param) {

        List<Object> result = new ArrayList<>();

        String category = (String) param.get("category");
        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        if(category.equals("bothIp")) result.addAll(realtimeUriRepository.searchHttpUriBothIp(beginDate, endDate));
        else if(category.equals("bothPort")) result.addAll(realtimeUriRepository.searchHttpUriBothPort(beginDate, endDate));
        else if(category.equals("transaction")) {
            List<Object[]> transactionList = realtimeUriRepository.searchHttpUriTransaction(beginDate, endDate);

            for(Object[] obj : transactionList) {
                Map<String, Object> map = new HashMap<>();
                map.put("transactionId", obj[0]);

                if(obj[1] != null) map.put("transactionName", Util.latin1ToUtf8(obj[1].toString()));
                else map.put("transactionName", "");
                result.add(map);
            }
        }
        else if(category.equals("httpMethod")) {
            result.addAll(infoHttpTypeRepository.findHttpTypeByName("HTTP Request Method"));
        }
        else if(category.equals("httpResponseCode")) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_1xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_1xx_DESC);
            List<InfoHttpType> resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 100, 200);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_2xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_2xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 200, 300);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_3xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_3xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 300, 400);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_4xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_4xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 400, 500);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);

            map = new HashMap<>();
            map.put("code", Constants.HTTP_RESPONSE_CODE_5xx);
            map.put("codeDesc", Constants.HTTP_RESPONSE_CODE_5xx_DESC);
            resultList = infoHttpTypeRepository.findHttpResponseCodeByRange("HTTP Response Code", 500, 600);
            for(InfoHttpType httpType : resultList) {
                httpType.setDescription1(Util.latin1ToUtf8(httpType.getDescription1()));
                httpType.setDescription2(Util.latin1ToUtf8(httpType.getDescription2()));
            }
            map.put("responseCode", resultList);
            result.add(map);
        } else {
            realtimeUriRepositoryCustom.searchHttpPageCategory(category, beginDate, endDate);
        }
        return result;
    }

    @Override
    public List<Test> test() {
        return infoHttpTypeRepository.test();
    }

    @Override
    public void enumTest() {
        AlarmType1Dept dept1 = AlarmType1Dept.valueOfIndexNum(2);
        System.out.println("================================================================================");
        System.out.println(dept1.getName());
        System.out.println(dept1.getCodeName());
        System.out.println(dept1.getIndexNum());
        System.out.println("================================================================================");


        AlarmType1Dept dept2 = AlarmType1Dept.valueOfName("L4-UDP");
        System.out.println("================================================================================");
        System.out.println(dept2.getName());
        System.out.println(dept2.getCodeName());
        System.out.println(dept2.getIndexNum());
        System.out.println("================================================================================");


        AlarmType1Dept dept3 = AlarmType1Dept.valueOfNameCode("traffic");
        System.out.println("================================================================================");
        System.out.println(dept3.getName());
        System.out.println(dept3.getCodeName());
        System.out.println(dept3.getIndexNum());
        System.out.println("================================================================================");

    }
}
