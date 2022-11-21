package com.sysone.eumaiwacs.serviceImpl.protocol;

import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.RealtimePage;
import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepositoryCustom;
import com.sysone.eumaiwacs.repository.realtime.RealtimeUriRepositoryCustom;
import com.sysone.eumaiwacs.repository.setting.InfoDomesticSub1Repository;
import com.sysone.eumaiwacs.service.protocol.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HttpServiceImpl implements HttpService {

    @Autowired
    private RealtimePageRepositoryCustom realtimePageRepositoryCustom;

    @Autowired
    private RealtimeUriRepositoryCustom realtimeUriRepositoryCustom;

    @Autowired
    private InfoDomesticSub1Repository infoDomesticSub1Repository;

    @Override
    public Object searchRealtimePage(Map<String, Object> param) {

        String type = (String) param.get("type");
        Object result = realtimePageRepositoryCustom.searchRealtimePage(param);

        List<RealtimePage> pageList = new ArrayList<>();

        if(type.equals("excel")) {
            pageList = (List<RealtimePage>) result;
        } else if(type.equals("grid")) {
            QueryResults<RealtimePage> queryResults = (QueryResults<RealtimePage>) result;
            pageList = queryResults.getResults();
        }

        for(RealtimePage page : pageList) {
            Integer domesticSub1IdReq = page.getDomesticSub1IdReq();
            Integer domesticSub1IdRes = page.getDomesticSub1IdRes();

            String domesticSub1ReqName = "";
            String domesticSub1ResName = "";

            if(domesticSub1IdReq > 0) domesticSub1ReqName = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdReq).getNameVar());
            if(domesticSub1IdRes > 0) domesticSub1ResName = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdRes).getNameVar());

            page.setAsNameEngReq(Util.latin1ToUtf8(page.getAsNameEngReq()));
            page.setAsNameEngRes(Util.latin1ToUtf8(page.getAsNameEngRes()));
            page.setAsNameReq(Util.latin1ToUtf8(page.getAsNameReq()));
            page.setAsNameRes(Util.latin1ToUtf8(page.getAsNameRes()));
            page.setCountryNameReq(Util.latin1ToUtf8(page.getCountryNameReq()));
            page.setCountryNameRes(Util.latin1ToUtf8(page.getCountryNameRes()));
            page.setContinentNameReq(Util.latin1ToUtf8(page.getContinentNameReq()));
            page.setContinentNameRes(Util.latin1ToUtf8(page.getContinentNameRes()));
            page.setCityNameReq(Util.latin1ToUtf8(page.getCityNameReq()));
            page.setCityNameRes(Util.latin1ToUtf8(page.getCityNameRes()));
            page.setCityCountryNameReq(Util.latin1ToUtf8(page.getCityCountryNameReq()));
            page.setCityCountryNameRes(Util.latin1ToUtf8(page.getCityCountryNameRes()));
            page.setDomesticPrimaryNameReq(Util.latin1ToUtf8(page.getDomesticPrimaryNameReq()));
            page.setDomesticPrimaryNameRes(Util.latin1ToUtf8(page.getDomesticPrimaryNameRes()));
            page.setDomesticSub1NameReq(domesticSub1ReqName);
            page.setDomesticSub1NameRes(domesticSub1ResName);
            page.setDomesticSub2NameReq(Util.latin1ToUtf8(page.getDomesticSub2NameReq()));
            page.setDomesticSub2NameRes(Util.latin1ToUtf8(page.getDomesticSub2NameRes()));
            page.setIspNameReq(Util.latin1ToUtf8(page.getIspNameReq()));
            page.setIspNameRes(Util.latin1ToUtf8(page.getIspNameRes()));
            page.setIspNameEngReq(Util.latin1ToUtf8(page.getIspNameEngReq()));
            page.setIspNameEngRes(Util.latin1ToUtf8(page.getIspNameEngRes()));
            page.setIdcNameReq(Util.latin1ToUtf8(page.getIdcNameReq()));
            page.setIdcNameRes(Util.latin1ToUtf8(page.getIdcNameRes()));
            page.setIdcNameEngReq(Util.latin1ToUtf8(page.getIdcNameEngReq()));
            page.setIdcNameEngRes(Util.latin1ToUtf8(page.getIdcNameEngRes()));
        }

        return result;
    }

    @Override
    public Map<String, Object> viewRealtimePage(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();

        RealtimePage page = realtimePageRepositoryCustom.findRealtimePageDetail(param);

        Integer domesticSub1IdReq = 0;
        Integer domesticSub1IdRes = 0;

        if(page.getDomesticSub1IdReq() != null) domesticSub1IdReq = page.getDomesticSub1IdReq();
        if(page.getDomesticSub1IdRes() != null) domesticSub1IdRes = page.getDomesticSub1IdRes();

        String domesticSub1ReqName = "";
        String domesticSub1ResName = "";

        if(domesticSub1IdReq > 0) domesticSub1ReqName = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdReq).getNameVar());
        if(domesticSub1IdRes > 0) domesticSub1ResName = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdRes).getNameVar());

        page.setAsNameEngReq(Util.latin1ToUtf8(page.getAsNameEngReq()));
        page.setAsNameEngRes(Util.latin1ToUtf8(page.getAsNameEngRes()));
        page.setAsNameReq(Util.latin1ToUtf8(page.getAsNameReq()));
        page.setAsNameRes(Util.latin1ToUtf8(page.getAsNameRes()));
        page.setCountryNameReq(Util.latin1ToUtf8(page.getCountryNameReq()));
        page.setCountryNameRes(Util.latin1ToUtf8(page.getCountryNameRes()));
        page.setContinentNameReq(Util.latin1ToUtf8(page.getContinentNameReq()));
        page.setContinentNameRes(Util.latin1ToUtf8(page.getContinentNameRes()));
        page.setCityNameReq(Util.latin1ToUtf8(page.getCityNameReq()));
        page.setCityNameRes(Util.latin1ToUtf8(page.getCityNameRes()));
        page.setCityCountryNameReq(Util.latin1ToUtf8(page.getCityCountryNameReq()));
        page.setCityCountryNameRes(Util.latin1ToUtf8(page.getCityCountryNameRes()));
        page.setDomesticPrimaryNameReq(Util.latin1ToUtf8(page.getDomesticPrimaryNameReq()));
        page.setDomesticPrimaryNameRes(Util.latin1ToUtf8(page.getDomesticPrimaryNameRes()));
        page.setDomesticSub1NameReq(domesticSub1ReqName);
        page.setDomesticSub1NameRes(domesticSub1ResName);
        page.setDomesticSub2NameReq(Util.latin1ToUtf8(page.getDomesticSub2NameReq()));
        page.setDomesticSub2NameRes(Util.latin1ToUtf8(page.getDomesticSub2NameRes()));
        page.setIspNameReq(Util.latin1ToUtf8(page.getIspNameReq()));
        page.setIspNameRes(Util.latin1ToUtf8(page.getIspNameRes()));
        page.setIspNameEngReq(Util.latin1ToUtf8(page.getIspNameEngReq()));
        page.setIspNameEngRes(Util.latin1ToUtf8(page.getIspNameEngRes()));
        page.setIdcNameReq(Util.latin1ToUtf8(page.getIdcNameReq()));
        page.setIdcNameRes(Util.latin1ToUtf8(page.getIdcNameRes()));
        page.setIdcNameEngReq(Util.latin1ToUtf8(page.getIdcNameEngReq()));
        page.setIdcNameEngRes(Util.latin1ToUtf8(page.getIdcNameEngRes()));

        List<RealtimeUri> realtimeUriList = realtimeUriRepositoryCustom.findRealtimeUriByRealtimePageKey(param);
        if(realtimeUriList != null && realtimeUriList.size() > 0) {
            for(RealtimeUri uri : realtimeUriList) {

                Integer domesticSub1IdReqUri = uri.getDomesticSub1IdReq();
                Integer domesticSub1IdResUri = uri.getDomesticSub1IdRes();

                String domesticSub1ReqNameUri = "";
                String domesticSub1ResNameUri = "";

                if(domesticSub1IdReqUri > 0) domesticSub1ReqNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdReqUri).getNameVar());
                if(domesticSub1IdResUri > 0) domesticSub1ResNameUri = Util.latin1ToUtf8(infoDomesticSub1Repository.findByid(domesticSub1IdResUri).getNameVar());

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
        }

        result.put("page", page);
        result.put("uri", realtimeUriList);

        return result;
    }

}
