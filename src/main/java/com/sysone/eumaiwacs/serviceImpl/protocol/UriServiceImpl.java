package com.sysone.eumaiwacs.serviceImpl.protocol;

import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;
import com.sysone.eumaiwacs.repository.realtime.RealtimeUriRepositoryCustom;
import com.sysone.eumaiwacs.repository.setting.InfoDomesticSub1Repository;
import com.sysone.eumaiwacs.service.protocol.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UriServiceImpl implements UriService {

    @Autowired
    private RealtimeUriRepositoryCustom realtimeUriRepositoryCustom;
    @Autowired
    private InfoDomesticSub1Repository infoDomesticSub1Repository;

    @Override
    public Object searchRealtimeUri(Map<String, Object> param) {

        String type = (String) param.get("type");
        Object result = realtimeUriRepositoryCustom.searchRealtimeUri(param);

        List<RealtimeUri> uriList = new ArrayList<>();

        if(type.equals("excel")) {
            uriList = (List<RealtimeUri>) result;
        } else if(type.equals("grid")) {
            QueryResults<RealtimeUri> queryResults = (QueryResults<RealtimeUri>) result;
            uriList = queryResults.getResults();
        }

        for(RealtimeUri uri : uriList) {

            Integer domesticSub1IdReqUri = uri.getDomesticSub1IdReq();
            Integer domesticSub1IdResUri = uri.getDomesticSub1IdRes();

            String domesticSub1ReqNameUri = "";
            String domesticSub1ResNameUri = "";

            if(domesticSub1IdReqUri > 0 && domesticSub1IdReqUri != null) domesticSub1ReqNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdReqUri).getNameVar());
            if(domesticSub1IdResUri > 0&& domesticSub1IdResUri != null) domesticSub1ResNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdResUri).getNameVar());

            uri.setAsNameEngReq(Util.latin1ToUtf8(uri.getAsNameEngReq()));
            uri.setAsNameEngRes(Util.latin1ToUtf8(uri.getAsNameEngRes()));
            uri.setAsNameReq(Util.latin1ToUtf8(uri.getAsNameReq()));
            uri.setAsNameRes(Util.latin1ToUtf8(uri.getAsNameRes()));
            uri.setCountryNameReq(Util.latin1ToUtf8(uri.getCountryNameReq()));
            uri.setCountryNameRes(Util.latin1ToUtf8(uri.getCountryNameRes()));
            uri.setContinentNameReq(Util.latin1ToUtf8(uri.getContinentNameReq()));
            uri.setContinentNameRes(Util.latin1ToUtf8(uri.getContinentNameRes()));
            uri.setCityNameReq(Util.latin1ToUtf8(uri.getCityNameReq()));
            uri.setCityNameRes(Util.latin1ToUtf8(uri.getCityNameRes()));
            uri.setCityCountryNameReq(Util.latin1ToUtf8(uri.getCityCountryNameReq()));
            uri.setCityCountryNameRes(Util.latin1ToUtf8(uri.getCityCountryNameRes()));
            uri.setDomesticPrimaryNameReq(Util.latin1ToUtf8(uri.getDomesticPrimaryNameReq()));
            uri.setDomesticPrimaryNameRes(Util.latin1ToUtf8(uri.getDomesticPrimaryNameRes()));
            uri.setDomesticSub1NameReq(domesticSub1ReqNameUri);
            uri.setDomesticSub1NameRes(domesticSub1ResNameUri);
            uri.setDomesticSub2NameReq(Util.latin1ToUtf8(uri.getDomesticSub2NameReq()));
            uri.setDomesticSub2NameRes(Util.latin1ToUtf8(uri.getDomesticSub2NameRes()));
            uri.setIspNameReq(Util.latin1ToUtf8(uri.getIspNameReq()));
            uri.setIspNameRes(Util.latin1ToUtf8(uri.getIspNameRes()));
            uri.setIspNameEngReq(Util.latin1ToUtf8(uri.getIspNameEngReq()));
            uri.setIspNameEngRes(Util.latin1ToUtf8(uri.getIspNameEngRes()));
            uri.setIdcNameReq(Util.latin1ToUtf8(uri.getIdcNameReq()));
            uri.setIdcNameRes(Util.latin1ToUtf8(uri.getIdcNameRes()));
            uri.setIdcNameEngReq(Util.latin1ToUtf8(uri.getIdcNameEngReq()));
            uri.setIdcNameEngRes(Util.latin1ToUtf8(uri.getIdcNameEngRes()));
        }
        return result;
    }

    @Override
    public RealtimeUri viewRealtimeUri(Map<String, Object> param) {
        RealtimeUri realtimeUri = realtimeUriRepositoryCustom.findRealtimeUriDetail(param);

        Integer domesticSub1IdReqUri = realtimeUri.getDomesticSub1IdReq();
        Integer domesticSub1IdResUri = realtimeUri.getDomesticSub1IdRes();

        String domesticSub1ReqNameUri = "";
        String domesticSub1ResNameUri = "";

        if(domesticSub1IdReqUri > 0) domesticSub1ReqNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdReqUri).getNameVar());
        if(domesticSub1IdResUri > 0) domesticSub1ResNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdResUri).getNameVar());

        realtimeUri.setAsNameEngReq(Util.latin1ToUtf8(realtimeUri.getAsNameEngReq()));
        realtimeUri.setAsNameEngRes(Util.latin1ToUtf8(realtimeUri.getAsNameEngRes()));
        realtimeUri.setAsNameReq(Util.latin1ToUtf8(realtimeUri.getAsNameReq()));
        realtimeUri.setAsNameRes(Util.latin1ToUtf8(realtimeUri.getAsNameRes()));
        realtimeUri.setCountryNameReq(Util.latin1ToUtf8(realtimeUri.getCountryNameReq()));
        realtimeUri.setCountryNameRes(Util.latin1ToUtf8(realtimeUri.getCountryNameRes()));
        realtimeUri.setContinentNameReq(Util.latin1ToUtf8(realtimeUri.getContinentNameReq()));
        realtimeUri.setContinentNameRes(Util.latin1ToUtf8(realtimeUri.getContinentNameRes()));
        realtimeUri.setCityNameReq(Util.latin1ToUtf8(realtimeUri.getCityNameReq()));
        realtimeUri.setCityNameRes(Util.latin1ToUtf8(realtimeUri.getCityNameRes()));
        realtimeUri.setCityCountryNameReq(Util.latin1ToUtf8(realtimeUri.getCityCountryNameReq()));
        realtimeUri.setCityCountryNameRes(Util.latin1ToUtf8(realtimeUri.getCityCountryNameRes()));
        realtimeUri.setDomesticPrimaryNameReq(Util.latin1ToUtf8(realtimeUri.getDomesticPrimaryNameReq()));
        realtimeUri.setDomesticPrimaryNameRes(Util.latin1ToUtf8(realtimeUri.getDomesticPrimaryNameRes()));
        realtimeUri.setDomesticSub1NameReq(domesticSub1ReqNameUri);
        realtimeUri.setDomesticSub1NameRes(domesticSub1ResNameUri);
        realtimeUri.setDomesticSub2NameReq(Util.latin1ToUtf8(realtimeUri.getDomesticSub2NameReq()));
        realtimeUri.setDomesticSub2NameRes(Util.latin1ToUtf8(realtimeUri.getDomesticSub2NameRes()));
        realtimeUri.setIspNameReq(Util.latin1ToUtf8(realtimeUri.getIspNameReq()));
        realtimeUri.setIspNameRes(Util.latin1ToUtf8(realtimeUri.getIspNameRes()));
        realtimeUri.setIspNameEngReq(Util.latin1ToUtf8(realtimeUri.getIspNameEngReq()));
        realtimeUri.setIspNameEngRes(Util.latin1ToUtf8(realtimeUri.getIspNameEngRes()));
        realtimeUri.setIdcNameReq(Util.latin1ToUtf8(realtimeUri.getIdcNameReq()));
        realtimeUri.setIdcNameRes(Util.latin1ToUtf8(realtimeUri.getIdcNameRes()));
        realtimeUri.setIdcNameEngReq(Util.latin1ToUtf8(realtimeUri.getIdcNameEngReq()));
        realtimeUri.setIdcNameEngRes(Util.latin1ToUtf8(realtimeUri.getIdcNameEngRes()));

        return realtimeUri;
    }
}
