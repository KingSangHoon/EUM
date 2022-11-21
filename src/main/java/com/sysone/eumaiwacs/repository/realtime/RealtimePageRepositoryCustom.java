package com.sysone.eumaiwacs.repository.realtime;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.QRealtimePage;
import com.sysone.eumaiwacs.entity.realtime.RealtimePage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class RealtimePageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QRealtimePage realtimePage = QRealtimePage.realtimePage;

    public RealtimePage findRealtimePageDetail(Map<String, Object> param) {

        String srcIp = (String) param.get("srcIp");
        String dstIp = (String) param.get("dstIp");
        Integer srcPort = (Integer) param.get("srcPort");
        Integer dstPort = (Integer) param.get("dstPort");
        BigDecimal tsFrameArrival = new BigDecimal((String) param.get("tsFrameArrival"));
        BigDecimal tsFrameLandOff = new BigDecimal((String) param.get("tsFrameLandOff"));
        BigInteger pageIdx = new BigInteger((String) param.get("pageIdx"));

        RealtimePage result = queryFactory.selectFrom(realtimePage)
                .where(realtimePage.realtimePageKey.srcIp.eq(srcIp).and(realtimePage.realtimePageKey.dstIp.eq(dstIp))
                        .and(realtimePage.realtimePageKey.srcPort.eq(srcPort)).and(realtimePage.realtimePageKey.dstPort.eq(dstPort))
                        .and(realtimePage.realtimePageKey.tsFrameArrival.eq(tsFrameArrival)).and(realtimePage.realtimePageKey.tsFrameLandOff.eq(tsFrameLandOff))
                        .and(realtimePage.realtimePageKey.pageIdx.eq(pageIdx)))
                .fetchOne();

        return result;
    }

    public Object searchRealtimePage(Map<String, Object> param) {

        String type = (String) param.get("type"); //excel/chart/grid
        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        List<Integer> srcZoneIsp = param.get("srcZoneIsp") == null ? null : (List<Integer>) param.get("srcZoneIsp");
        List<Integer> srcZoneIdc = param.get("srcZoneIdc") == null ? null : (List<Integer>) param.get("srcZoneIdc");
        List<Integer> srcZoneContinent = param.get("srcZoneContinent") == null ? null : (List<Integer>) param.get("srcZoneContinent");
        List<Integer> srcZoneCountry = param.get("srcZoneCountry") == null ? null : (List<Integer>) param.get("srcZoneCountry");
        List<Integer> srcZonePrimary = param.get("srcZonePrimary") == null ? null : (List<Integer>) param.get("srcZonePrimary");
        List<Integer> srcZoneSub1 = param.get("srcZoneSub1") == null ? null : (List<Integer>) param.get("srcZoneSub1");
        List<Integer> srcZoneSub2 = param.get("srcZoneSub2") == null ? null : (List<Integer>) param.get("srcZoneSub2");

        List<Integer> dstZoneIsp = param.get("dstZoneIsp") == null ? null : (List<Integer>) param.get("dstZoneIsp");
        List<Integer> dstZoneIdc = param.get("dstZoneIdc") == null ? null : (List<Integer>) param.get("dstZoneIdc");
        List<Integer> dstZoneContinent = param.get("dstZoneContinent") == null ? null : (List<Integer>) param.get("dstZoneContinent");
        List<Integer> dstZoneCountry = param.get("dstZoneCountry") == null ? null : (List<Integer>) param.get("dstZoneCountry");
        List<Integer> dstZonePrimary = param.get("dstZonePrimary") == null ? null : (List<Integer>) param.get("dstZonePrimary");
        List<Integer> dstZoneSub1 = param.get("dstZoneSub1") == null ? null : (List<Integer>) param.get("dstZoneSub1");
        List<Integer> dstZoneSub2 = param.get("dstZoneSub2") == null ? null : (List<Integer>) param.get("dstZoneSub2");

        List<Integer> bothZoneIsp = param.get("bothZoneIsp") == null ? null : (List<Integer>) param.get("bothZoneIsp");
        List<Integer> bothZoneIdc = param.get("bothZoneIdc") == null ? null : (List<Integer>) param.get("bothZoneIdc");
        List<Integer> bothZoneContinent = param.get("bothZoneContinent") == null ? null : (List<Integer>) param.get("bothZoneContinent");
        List<Integer> bothZoneCountry = param.get("bothZoneCountry") == null ? null : (List<Integer>) param.get("bothZoneCountry");
        List<Integer> bothZonePrimary = param.get("bothZonePrimary") == null ? null : (List<Integer>) param.get("bothZonePrimary");
        List<Integer> bothZoneSub1 = param.get("bothZoneSub1") == null ? null : (List<Integer>) param.get("bothZoneSub1");
        List<Integer> bothZoneSub2 = param.get("bothZoneSub2") == null ? null : (List<Integer>) param.get("bothZoneSub2");

        Boolean isTextSrcIp = param.get("isTextSrcIp") == null ? null : (Boolean) param.get("isTextSrcIp");
        String textSrcIp = param.get("textSrcIp") == null ? null : (String) param.get("textSrcIp");
        List<String> srcIp = param.get("srcIp") == null ? null : (List<String>) param.get("srcIp");

        Boolean isTextDstIp = param.get("isTextDstIp") == null ? null : (Boolean) param.get("isTextDstIp");
        String textDstIp = param.get("textDstIp") == null ? null : (String) param.get("textDstIp");
        List<String> dstIp = param.get("dstIp") == null ? null : (List<String>) param.get("dstIp");

        Boolean isTextBothIp = param.get("isTextBothIp") == null ? null : (Boolean) param.get("isTextBothIp");
        String textBothIp = param.get("textBothIp") == null ? null : (String) param.get("textBothIp");
        List<String> bothIp = param.get("bothIp") == null ? null : (List<String>) param.get("bothIp");

        Boolean isTextSrcPort = param.get("isTextSrcPort") == null ? null : (Boolean) param.get("isTextSrcPort");
        Integer textSrcPort = param.get("textSrcPort") == null ? null : Integer.parseInt(param.get("textSrcPort").toString());
        List<Integer> srcPort = param.get("srcPort") == null ? null : (List<Integer>) param.get("srcPort");

        Boolean isTextDstPort = param.get("isTextDstPort") == null ? null : (Boolean) param.get("isTextDstPort");
        Integer textDstPort = param.get("textDstPort") == null ? null : Integer.parseInt(param.get("textDstPort").toString());
        List<Integer> dstPort = param.get("dstPort") == null ? null : (List<Integer>) param.get("dstPort");

        Boolean isTextBothPort = param.get("isTextBothPort") == null ? null : (Boolean) param.get("isTextBothPort");
        Integer textBothPort = param.get("textBothPort") == null ? null : Integer.parseInt(param.get("textBothPort").toString());
        List<Integer> bothPort = param.get("bothPort") == null ? null : (List<Integer>) param.get("bothPort");

        List<Integer> applicationId = param.get("applicationId") == null ? null : (List<Integer>) param.get("applicationId");
        List<Integer> transactionId = param.get("transactionId") == null ? null : (List<Integer>) param.get("transactionId");

        Boolean isTextHost = param.get("isTextHost") == null ? null : (Boolean) param.get("isTextHost");
        String textHost = param.get("textHost") == null ? null : (String) param.get("textHost");
        List<String> host = param.get("host") == null ? null : (List<String>) param.get("host");

        Boolean isTextPage = param.get("isTextPage") == null ? null : (Boolean) param.get("isTextPage");
        String textPage = param.get("textPage") == null ? null : (String) param.get("textPage");
        List<String> page = param.get("page") == null ? null : (List<String>) param.get("page");

        Boolean isTextUseragent = param.get("isTextUseragent") == null ? null : (Boolean) param.get("isTextUseragent");
        String textUseragent = param.get("textUseragent") == null ? null : (String) param.get("textUseragent");
        List<String> useragent = param.get("useragent") == null ? null : (List<String>) param.get("useragent");

        Boolean isTextReferer = param.get("isTextReferer") == null ? null : (Boolean) param.get("isTextReferer");
        String textReferer = param.get("textReferer") == null ? null : (String) param.get("textReferer");
        List<String> referer = param.get("referer") == null ? null : (List<String>) param.get("referer");

        Boolean isTextResponseCode = param.get("isTextResponseCode") == null ? null : (Boolean) param.get("isTextResponseCode");
        Integer textResponseCode = param.get("textResponseCode") == null ? null : Integer.parseInt(param.get("textResponseCode").toString());
        List<Integer> responseCode = param.get("responseCode") == null ? null : (List<Integer>) param.get("responseCode");

        List<String> method = param.get("method") == null ? null : (List<String>) param.get("method");
        List<String> browser = param.get("browser") == null ? null : (List<String>) param.get("browser");
        List<String> hardware = param.get("hardware") == null ? null : (List<String>) param.get("hardware");
        List<String> os = param.get("os") == null ? null : (List<String>) param.get("os");
        List<String> platform = param.get("platform") == null ? null : (List<String>) param.get("platform");

        List<Map<String, Object>> protocol = param.get("protocol") == null ? null : (List<Map<String, Object>>) param.get("protocol");

        Boolean isRangeResTime = param.get("isRangeResTime") == null ? null : (Boolean) param.get("isRangeResTime");
        String lessUnit = param.get("lessUnit") == null ? null : (String) param.get("lessUnit");
        String moreUnit = param.get("moreUnit") == null ? null : (String) param.get("moreUnit");
        BigDecimal lessVal = param.get("lessVal") == null ? null : BigDecimal.valueOf((Integer) param.get("lessVal"));
        BigDecimal moreVal = param.get("moreVal") == null ? null : BigDecimal.valueOf((Integer) param.get("moreVal"));

        if(type.equals("excel")) {
            List<RealtimePage> queryResult = queryFactory.selectFrom(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(beginDate, endDate),
                            srcZoneIspIn(srcZoneIsp), srcZoneIdcIn(srcZoneIdc), srcZoneContinentIn(srcZoneContinent), srcZoneCountryIn(srcZoneCountry),
                            srcZonePrimaryIn(srcZonePrimary), srcZoneSub1In(srcZoneSub1), srcZoneSub2In(srcZoneSub2),
                            dstZoneIspIn(dstZoneIsp), dstZoneIdcIn(dstZoneIdc), dstZoneContinentIn(dstZoneContinent), dstZoneCountryIn(dstZoneCountry),
                            dstZonePrimaryIn(dstZonePrimary), dstZoneSub1In(dstZoneSub1), dstZoneSub2In(dstZoneSub2),
                            bothZoneIspIn(bothZoneIsp), bothZoneIdcIn(bothZoneIdc), bothZoneContinentIn(bothZoneContinent), bothZoneCountryIn(bothZoneCountry),
                            bothZonePrimaryIn(bothZonePrimary), bothZoneSub1In(bothZoneSub1), bothZoneSub2In(bothZoneSub2),
                            searchSrcIp(isTextSrcIp, textSrcIp, srcIp), searchDstIp(isTextDstIp, textDstIp, dstIp), searchBothIp(isTextBothIp, textBothIp, bothIp),
                            searchSrcPort(isTextSrcPort, textSrcPort, srcPort), searchDstPort(isTextDstPort, textDstPort, dstPort), searchBothPort(isTextBothPort, textBothPort, bothPort),
                            applicationIdIn(applicationId), transactionIdIn(transactionId),
                            searchHost(isTextHost, textHost, host), searchPage(isTextPage, textPage, page), searchUseragent(isTextUseragent, textUseragent, useragent),
                            searchReferer(isTextReferer, textReferer, referer), searchResponseCode(isTextResponseCode, textResponseCode, responseCode),
                            methodIn(method), browserIn(browser), hardwareIn(hardware), osIn(os), platformIn(platform), searchProtocol(protocol),
                            searchResponseTime(isRangeResTime, lessVal, moreVal, lessUnit, moreUnit))
                    .orderBy(realtimePage.realtimePageKey.tsFrameArrival.asc())
                    .fetch();
            return queryResult;
        } else if(type.equals("chart")) {
            List<RealtimePage> queryResult = queryFactory.selectFrom(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(beginDate, endDate),
                            srcZoneIspIn(srcZoneIsp), srcZoneIdcIn(srcZoneIdc), srcZoneContinentIn(srcZoneContinent), srcZoneCountryIn(srcZoneCountry),
                            srcZonePrimaryIn(srcZonePrimary), srcZoneSub1In(srcZoneSub1), srcZoneSub2In(srcZoneSub2),
                            dstZoneIspIn(dstZoneIsp), dstZoneIdcIn(dstZoneIdc), dstZoneContinentIn(dstZoneContinent), dstZoneCountryIn(dstZoneCountry),
                            dstZonePrimaryIn(dstZonePrimary), dstZoneSub1In(dstZoneSub1), dstZoneSub2In(dstZoneSub2),
                            bothZoneIspIn(bothZoneIsp), bothZoneIdcIn(bothZoneIdc), bothZoneContinentIn(bothZoneContinent), bothZoneCountryIn(bothZoneCountry),
                            bothZonePrimaryIn(bothZonePrimary), bothZoneSub1In(bothZoneSub1), bothZoneSub2In(bothZoneSub2),
                            searchSrcIp(isTextSrcIp, textSrcIp, srcIp), searchDstIp(isTextDstIp, textDstIp, dstIp), searchBothIp(isTextBothIp, textBothIp, bothIp),
                            searchSrcPort(isTextSrcPort, textSrcPort, srcPort), searchDstPort(isTextDstPort, textDstPort, dstPort), searchBothPort(isTextBothPort, textBothPort, bothPort),
                            applicationIdIn(applicationId), transactionIdIn(transactionId),
                            searchHost(isTextHost, textHost, host), searchPage(isTextPage, textPage, page), searchUseragent(isTextUseragent, textUseragent, useragent),
                            searchReferer(isTextReferer, textReferer, referer), searchResponseCode(isTextResponseCode, textResponseCode, responseCode),
                            methodIn(method), browserIn(browser), hardwareIn(hardware), osIn(os), platformIn(platform), searchProtocol(protocol),
                            searchResponseTime(isRangeResTime, lessVal, moreVal, lessUnit, moreUnit))
                    .orderBy(realtimePage.realtimePageKey.tsFrameArrival.desc())
                    .fetch();
            return queryResult;
        } else if(type.equals("grid")) {
            Integer limit = (Integer) param.get("limit");
            Integer offset = (Integer) param.get("offset");

            QueryResults<RealtimePage> queryResult = queryFactory.selectFrom(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(beginDate, endDate),
                            srcZoneIspIn(srcZoneIsp), srcZoneIdcIn(srcZoneIdc), srcZoneContinentIn(srcZoneContinent), srcZoneCountryIn(srcZoneCountry),
                            srcZonePrimaryIn(srcZonePrimary), srcZoneSub1In(srcZoneSub1), srcZoneSub2In(srcZoneSub2),
                            dstZoneIspIn(dstZoneIsp), dstZoneIdcIn(dstZoneIdc), dstZoneContinentIn(dstZoneContinent), dstZoneCountryIn(dstZoneCountry),
                            dstZonePrimaryIn(dstZonePrimary), dstZoneSub1In(dstZoneSub1), dstZoneSub2In(dstZoneSub2),
                            bothZoneIspIn(bothZoneIsp), bothZoneIdcIn(bothZoneIdc), bothZoneContinentIn(bothZoneContinent), bothZoneCountryIn(bothZoneCountry),
                            bothZonePrimaryIn(bothZonePrimary), bothZoneSub1In(bothZoneSub1), bothZoneSub2In(bothZoneSub2),
                            searchSrcIp(isTextSrcIp, textSrcIp, srcIp), searchDstIp(isTextDstIp, textDstIp, dstIp), searchBothIp(isTextBothIp, textBothIp, bothIp),
                            searchSrcPort(isTextSrcPort, textSrcPort, srcPort), searchDstPort(isTextDstPort, textDstPort, dstPort), searchBothPort(isTextBothPort, textBothPort, bothPort),
                            applicationIdIn(applicationId), transactionIdIn(transactionId),
                            searchHost(isTextHost, textHost, host), searchPage(isTextPage, textPage, page), searchUseragent(isTextUseragent, textUseragent, useragent),
                            searchReferer(isTextReferer, textReferer, referer), searchResponseCode(isTextResponseCode, textResponseCode, responseCode),
                            methodIn(method), browserIn(browser), hardwareIn(hardware), osIn(os), platformIn(platform), searchProtocol(protocol),
                            searchResponseTime(isRangeResTime, lessVal, moreVal, lessUnit, moreUnit))
                    .orderBy(realtimePage.realtimePageKey.tsFrameArrival.desc())
                    .limit(limit)
                    .offset(offset)
                    .fetchResults();
            return queryResult;
        }
        return "";
    }

    private BooleanExpression srcZoneIspIn(List<Integer> srcZoneIsp) { return srcZoneIsp != null ? realtimePage.ispIdReq.in(srcZoneIsp) : null; }
    private BooleanExpression srcZoneIdcIn(List<Integer> srcZoneIdc) { return srcZoneIdc != null ? realtimePage.idcIdReq.in(srcZoneIdc) : null; }
    private BooleanExpression srcZoneContinentIn(List<Integer> srcZoneContinent) { return srcZoneContinent != null ? realtimePage.continentIdReq.in(srcZoneContinent) : null; }
    private BooleanExpression srcZoneCountryIn(List<Integer> srcZoneCountry) { return srcZoneCountry != null ? realtimePage.countryIdReq.in(srcZoneCountry) : null; }
    private BooleanExpression srcZonePrimaryIn(List<Integer> srcZonePrimary) { return srcZonePrimary != null ? realtimePage.domesticPrimaryIdReq.in(srcZonePrimary) : null; }
    private BooleanExpression srcZoneSub1In(List<Integer> srcZoneSub1) { return srcZoneSub1 != null ? realtimePage.domesticSub1IdReq.in(srcZoneSub1) : null; }
    private BooleanExpression srcZoneSub2In(List<Integer> srcZoneSub2) { return srcZoneSub2 != null ? realtimePage.domesticSub2IdReq.in(srcZoneSub2) : null; }

    private BooleanExpression dstZoneIspIn(List<Integer> dstZoneIsp) { return dstZoneIsp != null ? realtimePage.ispIdRes.in(dstZoneIsp) : null; }
    private BooleanExpression dstZoneIdcIn(List<Integer> dstZoneIdc) { return dstZoneIdc != null ? realtimePage.idcIdRes.in(dstZoneIdc) : null; }
    private BooleanExpression dstZoneContinentIn(List<Integer> dstZoneContinent) { return dstZoneContinent != null ? realtimePage.continentIdRes.in(dstZoneContinent) : null; }
    private BooleanExpression dstZoneCountryIn(List<Integer> dstZoneCountry) { return dstZoneCountry != null ? realtimePage.countryIdRes.in(dstZoneCountry) : null; }
    private BooleanExpression dstZonePrimaryIn(List<Integer> dstZonePrimary) { return dstZonePrimary != null ? realtimePage.domesticPrimaryIdRes.in(dstZonePrimary) : null; }
    private BooleanExpression dstZoneSub1In(List<Integer> dstZoneSub1) { return dstZoneSub1 != null ? realtimePage.domesticSub1IdRes.in(dstZoneSub1) : null; }
    private BooleanExpression dstZoneSub2In(List<Integer> dstZoneSub2) { return dstZoneSub2 != null ? realtimePage.domesticSub2IdRes.in(dstZoneSub2) : null; }

    private BooleanExpression bothZoneIspIn(List<Integer> bothZoneIsp) { return bothZoneIsp != null ? realtimePage.ispIdReq.in(bothZoneIsp).or(realtimePage.ispIdRes.in((bothZoneIsp))) : null; }
    private BooleanExpression bothZoneIdcIn(List<Integer> bothZoneIdc) { return bothZoneIdc != null ? realtimePage.idcIdReq.in(bothZoneIdc).or(realtimePage.idcIdRes.in((bothZoneIdc))) : null; }
    private BooleanExpression bothZoneContinentIn(List<Integer> bothZoneContinent) { return bothZoneContinent != null ? realtimePage.continentIdReq.in(bothZoneContinent).or(realtimePage.continentIdRes.in((bothZoneContinent))) : null; }
    private BooleanExpression bothZoneCountryIn(List<Integer> bothZoneCountry) { return bothZoneCountry != null ? realtimePage.countryIdReq.in(bothZoneCountry).or(realtimePage.countryIdRes.in((bothZoneCountry))) : null; }
    private BooleanExpression bothZonePrimaryIn(List<Integer> bothZonePrimary) { return bothZonePrimary != null ? realtimePage.domesticPrimaryIdReq.in(bothZonePrimary).or(realtimePage.domesticPrimaryIdRes.in((bothZonePrimary))) : null; }
    private BooleanExpression bothZoneSub1In(List<Integer> bothZoneSub1) { return bothZoneSub1 != null ? realtimePage.domesticSub1IdReq.in(bothZoneSub1).or(realtimePage.domesticSub1IdRes.in((bothZoneSub1))) : null; }
    private BooleanExpression bothZoneSub2In(List<Integer> bothZoneSub2) { return bothZoneSub2 != null ? realtimePage.domesticSub2IdReq.in(bothZoneSub2).or(realtimePage.domesticSub2IdRes.in((bothZoneSub2))) : null; }


    private BooleanExpression searchSrcIp(Boolean isTextSrcIp, String textSrcIp, List<String> srcIp) {
        if(isTextSrcIp != null && isTextSrcIp) return textSrcIp != null ? realtimePage.realtimePageKey.srcIp.contains(textSrcIp) : null;
        else return srcIp != null ? realtimePage.realtimePageKey.srcIp.in(srcIp) : null;
    }

    private BooleanExpression searchDstIp(Boolean isTextDstIp, String textDstIp, List<String> dstIp) {
        if(isTextDstIp != null && isTextDstIp) return textDstIp != null ? realtimePage.realtimePageKey.dstIp.contains(textDstIp) : null;
        else return dstIp != null ? realtimePage.realtimePageKey.dstIp.in(dstIp) : null;
    }

    private BooleanExpression searchBothIp(Boolean isTextBothIp, String textBothIp, List<String> bothIp) {
        if(isTextBothIp != null && isTextBothIp) return realtimePage.realtimePageKey.srcIp.contains(textBothIp).or(realtimePage.realtimePageKey.dstIp.contains(textBothIp));
        else return bothIp != null ? realtimePage.realtimePageKey.srcIp.in(bothIp).or(realtimePage.realtimePageKey.dstIp.in(bothIp)) : null;
    }

    private BooleanExpression searchSrcPort(Boolean isTextSrcPort, Integer textSrcPort, List<Integer> srcPort) {
        if(isTextSrcPort != null && isTextSrcPort) return textSrcPort != null ? realtimePage.realtimePageKey.srcPort.eq(textSrcPort) : null;
        else return srcPort != null ? realtimePage.realtimePageKey.srcPort.in(srcPort) : null;
    }

    private BooleanExpression searchDstPort(Boolean isTextDstPort, Integer textDstPort, List<Integer> dstPort) {
        if(isTextDstPort != null && isTextDstPort) return textDstPort != null ? realtimePage.realtimePageKey.dstPort.eq(textDstPort) : null;
        else return dstPort != null ? realtimePage.realtimePageKey.dstPort.in(dstPort) : null;
    }

    private BooleanExpression searchBothPort(Boolean isTextBothPort, Integer textBothPort, List<Integer> bothPort) {
        if(isTextBothPort != null && isTextBothPort) return realtimePage.realtimePageKey.srcPort.eq(textBothPort).or(realtimePage.realtimePageKey.dstPort.eq(textBothPort));
        else return bothPort != null ? realtimePage.realtimePageKey.srcPort.in(bothPort).or(realtimePage.realtimePageKey.dstPort.in(bothPort)) : null;
    }

    private BooleanExpression applicationIdIn(List<Integer> applicationId){ return applicationId != null ? realtimePage.applicationId.in(applicationId) : null; }
    private BooleanExpression transactionIdIn(List<Integer> transactionId){ return transactionId != null ? realtimePage.transactionUrlId.in(transactionId) : null; }

    private BooleanExpression searchHost(Boolean isTextHost, String textHost, List<String> host) {
        if(isTextHost != null && isTextHost) return textHost != null ? realtimePage.httpHost.contains(textHost) : null;
        else return host != null ? realtimePage.httpHost.in(host) : null;
    }

    private BooleanExpression searchPage(Boolean isTextPage, String textPage, List<String> page) {
        if(isTextPage != null && isTextPage) return textPage != null ? realtimePage.httpUri.contains(textPage) : null;
        else return page != null ? realtimePage.httpUri.in(page) : null;
    }

    private BooleanExpression searchUseragent(Boolean isTextUseragent, String textUseragent, List<String> useragent) {
        if(isTextUseragent != null && isTextUseragent) return textUseragent != null ? realtimePage.httpUserAgent.contains(textUseragent) : null;
        else return useragent != null ? realtimePage.httpUserAgent.in(useragent) : null;
    }

    private BooleanExpression searchReferer(Boolean isTextReferer, String textReferer, List<String> referer) {
        if(isTextReferer != null && isTextReferer) return textReferer != null ? realtimePage.httpReferer.contains(textReferer) : null;
        else return referer != null ? realtimePage.httpReferer.in(referer) : null;
    }

    private BooleanExpression searchResponseCode(Boolean isTextResponseCode, Integer textResponseCode, List<Integer> responseCode) {
        if(isTextResponseCode != null && isTextResponseCode) return textResponseCode != null ? realtimePage.httpResCode.eq(textResponseCode) : null;
        else return responseCode != null ? realtimePage.httpResCode.in(responseCode) : null;
    }

    private BooleanExpression methodIn(List<String> method) { return method != null ? realtimePage.httpMethod.in(method) : null; }
    private BooleanExpression browserIn(List<String> browser) { return browser != null ? realtimePage.userAgentSoftwareName.in(browser) : null; }
    private BooleanExpression hardwareIn(List<String> hardware) { return hardware != null ? realtimePage.userAgentHardwareType.in(hardware) : null; }
    private BooleanExpression osIn(List<String> os) { return os != null ? realtimePage.userAgentOperatingSystemName.in(os) : null; }
    private BooleanExpression platformIn(List<String> platform) { return platform != null ? realtimePage.userAgentOperatingPlatform.in(platform) : null; }

    private BooleanBuilder searchProtocol(List<Map<String, Object>> protocol) {

        BooleanBuilder builder = new BooleanBuilder();
        if(protocol != null) {
            for(Map<String, Object> map : protocol) {
                Integer appId = (Integer) map.get("appId");
                Integer masterId = (Integer) map.get("masterId");

                builder.or(realtimePage.ndpiProtocolMasterId.eq(masterId).and(realtimePage.ndpiProtocolAppId.eq(appId)));
            }
            return builder;
        }
        else return null;
    }

    private BooleanBuilder searchResponseTime(Boolean isRangeResTime, BigDecimal lessVal, BigDecimal moreVal, String lessUnit, String moreUnit) {

        BooleanBuilder builder = new BooleanBuilder();

        if(lessVal != null && lessUnit != null) {
            if(isRangeResTime != null && isRangeResTime) {
                if(moreUnit.equals("goe")) builder.and(realtimePage.tsPage.goe(moreVal));
                else if(moreUnit.equals("gt")) builder.and(realtimePage.tsPage.gt(moreVal));

                if(lessUnit.equals("loe")) builder.and(realtimePage.tsPage.loe(lessVal));
                else if(lessUnit.equals("lt")) builder.and(realtimePage.tsPage.lt(lessVal));
            } else {
                if(lessUnit.equals("goe")) builder.and(realtimePage.tsPage.goe(lessVal));
                else if(lessUnit.equals("gt")) builder.and(realtimePage.tsPage.gt(lessVal));
                else if(lessUnit.equals("loe")) builder.and(realtimePage.tsPage.loe(lessVal));
                else if(lessUnit.equals("lt")) builder.and(realtimePage.tsPage.lt(lessVal));
            }
            return builder;
        }
        else return null;
    }

    /*
    검색 공통 모듈
     */
    public List<Object> searchHttpPageCategory(String category, BigDecimal begin, BigDecimal end) {
        List<Object> result = new ArrayList<>();

        if(category.equals("srcIp")) {
            List<String> queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.srcIp)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("dstIp")) {
            List<String> queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.dstIp)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("srcPort")) {
            List<Integer> queryResult = queryFactory.select(realtimePage.realtimePageKey.srcPort).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("dstPort")) {
            List<Integer> queryResult = queryFactory.select(realtimePage.realtimePageKey.dstPort).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("application")) {
            List<Tuple> queryResult = queryFactory.select(realtimePage.applicationId, realtimePage.applicationName).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.applicationId, realtimePage.applicationName)
                    .orderBy(realtimePage.applicationId.asc())
                    .fetch();

            if(queryResult != null && queryResult.size() > 0) {
                for(Tuple tuple : queryResult) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("applicationId", tuple.get(realtimePage.applicationId));
                    map.put("applicationName", Util.latin1ToUtf8(tuple.get(realtimePage.applicationName)));
                    result.add(map);
                }
            }
        } else if(category.equals("host")) {
            List<String> queryResult = queryFactory.select(realtimePage.httpHost).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("page")) {
            List<String> queryResult = queryFactory.select(realtimePage.httpUri.concat(realtimePage.httpUriSplit)).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpUri.concat(realtimePage.httpUriSplit))
                    .orderBy(realtimePage.httpUri.concat(realtimePage.httpUriSplit).asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("userAgent")) {
            List<String> queryResult = queryFactory.select(realtimePage.httpUserAgent).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpUserAgent)
                    .orderBy(realtimePage.httpUserAgent.asc())
                    .fetch();

            List<String> resultList = new ArrayList<>();
            resultList.add(Constants.USERAGENT_NOT_INFO);
            resultList.add(Constants.USERAGENT_DIRECT_PARSING);
            resultList.addAll(queryResult);
            result.add(resultList);
        } else if(category.equals("referer")) {
            List<String> queryResult = queryFactory.select(realtimePage.httpReferer).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpReferer)
                    .orderBy(realtimePage.httpReferer.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("browser")) {
            List<String> queryResult = queryFactory.select(realtimePage.userAgentSoftwareName).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.userAgentSoftwareName)
                    .orderBy(realtimePage.userAgentSoftwareName.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("os")) {
            List<String> queryResult = queryFactory.select(realtimePage.userAgentOperatingSystemName).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.userAgentOperatingSystemName)
                    .orderBy(realtimePage.userAgentOperatingSystemName.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("platform")) {
            List<String> queryResult = queryFactory.select(realtimePage.userAgentOperatingPlatform).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.userAgentOperatingPlatform)
                    .orderBy(realtimePage.userAgentOperatingPlatform.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("hardware")) {
            List<String> queryResult = queryFactory.select(realtimePage.userAgentHardwareType).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.userAgentHardwareType)
                    .orderBy(realtimePage.userAgentHardwareType.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("protocol")) {
            List<Tuple> queryResult = queryFactory.select(realtimePage.ndpiProtocolApp, realtimePage.ndpiProtocolAppId, realtimePage.ndpiProtocolMaster, realtimePage.ndpiProtocolMasterId).from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.ndpiProtocolApp, realtimePage.ndpiProtocolAppId, realtimePage.ndpiProtocolMaster, realtimePage.ndpiProtocolMasterId)
                    .orderBy(realtimePage.ndpiProtocolApp.asc())
                    .fetch();

            if(queryResult != null && queryResult.size() > 0) {
                for(Tuple tuple : queryResult) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("appName", tuple.get(realtimePage.ndpiProtocolApp));
                    map.put("appId", tuple.get(realtimePage.ndpiProtocolAppId));
                    map.put("masterName", tuple.get(realtimePage.ndpiProtocolMaster));
                    map.put("masterId", tuple.get(realtimePage.ndpiProtocolMasterId));
                    result.add(map);
                }
            }
        }
        return result;
    }

    /*
       트랜잭션 설정 - 참조 PAGE Search
    */
    public List<Object> searchTransactionInfo(String searchType, BigDecimal begin, BigDecimal end, String searchText, Integer searchInteger) {
        List<Object> result = new ArrayList<>();
        List<Tuple> queryResult = new ArrayList<>();

        if(searchType.equals("all")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        }else if(searchType.equals("host")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpHost.contains(searchText)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("url")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUri.contains(searchText)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("argument")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUriSplit.contains(searchText)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("srcIp")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcIp.contains(searchText)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("srcPort")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcPort.eq(searchInteger)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("dstIp")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstIp.contains(searchText)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        } else if(searchType.equals("dstPort")) {
            queryResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstPort.eq(searchInteger)))
                    .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                    .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                    .fetch();
        }

        if(queryResult != null && queryResult.size() > 0) {
            for(Tuple tuple : queryResult) {
                Map<String, Object> map = new HashMap<>();
                map.put("httpHost", tuple.get(realtimePage.httpHost));
                map.put("httpUri", tuple.get(realtimePage.httpUri));
                map.put("httpUriSplit", tuple.get(realtimePage.httpUriSplit));
                result.add(map);
            }
        }
        return result;
    }

    public List<Object> searchTransactionHttpHostInfo(String searchType, BigDecimal begin, BigDecimal end, String searchText, Integer searchInteger) {
        List<Object> result = new ArrayList<>();
        List<String> queryResult = new ArrayList<>();

        if(searchType.equals("all")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        }else if(searchType.equals("host")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpHost.contains(searchText)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("url")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUri.contains(searchText)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("argument")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUriSplit.contains(searchText)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("srcIp")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcIp.contains(searchText)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("srcPort")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcPort.eq(searchInteger)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("dstIp")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstIp.contains(searchText)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        } else if(searchType.equals("dstPort")) {
            queryResult = queryFactory.select(realtimePage.httpHost)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstPort.eq(searchInteger)))
                    .groupBy(realtimePage.httpHost)
                    .orderBy(realtimePage.httpHost.asc())
                    .fetch();
        }

        if(queryResult != null && queryResult.size() > 0) {
            for(String host : queryResult) {
                Map<String, Object> map = new HashMap<>();
                map.put("host", host);

                List<Tuple> hostResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .from(realtimePage)
                        .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpHost.eq(host)))
                        .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                        .fetch();

                List<Object> hostResultList = new ArrayList<>();
                if(hostResult != null && hostResult.size() > 0) {
                    for(Tuple tuple : hostResult) {
                        Map<String, Object> hostMap = new HashMap<>();
                        hostMap.put("httpHost", tuple.get(realtimePage.httpHost));
                        hostMap.put("httpUri", tuple.get(realtimePage.httpUri));
                        hostMap.put("httpUriSplit", tuple.get(realtimePage.httpUriSplit));
                        hostResultList.add(hostMap);
                    }
                }
                map.put("result", hostResultList);
                result.add(map);
            }
        }
        return result;
    }

    public List<Object> searchTransactionDstInfo(String searchType, BigDecimal begin, BigDecimal end, String searchText, Integer searchInteger) {
        List<Object> result = new ArrayList<>();
        List<Tuple> queryResult = new ArrayList<>();

        if(searchType.equals("all")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        }else if(searchType.equals("host")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpHost.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("url")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUri.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("argument")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUriSplit.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("srcIp")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcIp.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("srcPort")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcPort.eq(searchInteger)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("dstIp")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstIp.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        } else if(searchType.equals("dstPort")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstPort.eq(searchInteger)))
                    .groupBy(realtimePage.realtimePageKey.dstIp, realtimePage.realtimePageKey.dstPort)
                    .orderBy(realtimePage.realtimePageKey.dstIp.asc(), realtimePage.realtimePageKey.dstPort.asc())
                    .fetch();
        }

        if(queryResult != null && queryResult.size() > 0) {
            for(Tuple dstInfo : queryResult) {
                Map<String, Object> map = new HashMap<>();
                map.put("dstIp", dstInfo.get(realtimePage.realtimePageKey.dstIp));
                map.put("dstPort", dstInfo.get(realtimePage.realtimePageKey.dstPort));

                List<Tuple> dstInfoResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .from(realtimePage)
                        .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end)
                                .and(realtimePage.realtimePageKey.dstIp.eq(dstInfo.get(realtimePage.realtimePageKey.dstIp)))
                                .and(realtimePage.realtimePageKey.dstPort.eq(dstInfo.get(realtimePage.realtimePageKey.dstPort))))
                        .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                        .fetch();

                List<Object> dstInfoResultList = new ArrayList<>();
                if(dstInfoResult != null && dstInfoResult.size() > 0) {
                    for(Tuple tuple : dstInfoResult) {
                        Map<String, Object> hostMap = new HashMap<>();
                        hostMap.put("httpHost", tuple.get(realtimePage.httpHost));
                        hostMap.put("httpUri", tuple.get(realtimePage.httpUri));
                        hostMap.put("httpUriSplit", tuple.get(realtimePage.httpUriSplit));
                        dstInfoResultList.add(hostMap);
                    }
                }
                map.put("result", dstInfoResultList);
                result.add(map);
            }
        }
        return result;
    }

    public List<Object> searchTransactionSrcInfo(String searchType, BigDecimal begin, BigDecimal end, String searchText, Integer searchInteger) {
        List<Object> result = new ArrayList<>();
        List<Tuple> queryResult = new ArrayList<>();

        if(searchType.equals("all")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        }else if(searchType.equals("host")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpHost.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("url")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUri.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("argument")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.httpUriSplit.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("srcIp")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcIp.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("srcPort")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.srcPort.eq(searchInteger)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("dstIp")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstIp.contains(searchText)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        } else if(searchType.equals("dstPort")) {
            queryResult = queryFactory.select(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .from(realtimePage)
                    .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end).and(realtimePage.realtimePageKey.dstPort.eq(searchInteger)))
                    .groupBy(realtimePage.realtimePageKey.srcIp, realtimePage.realtimePageKey.srcPort)
                    .orderBy(realtimePage.realtimePageKey.srcIp.asc(), realtimePage.realtimePageKey.srcPort.asc())
                    .fetch();
        }

        if(queryResult != null && queryResult.size() > 0) {
            for(Tuple dstInfo : queryResult) {
                Map<String, Object> map = new HashMap<>();
                map.put("srcIp", dstInfo.get(realtimePage.realtimePageKey.srcIp));
                map.put("srcPort", dstInfo.get(realtimePage.realtimePageKey.srcPort));

                List<Tuple> dstInfoResult = queryFactory.select(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .from(realtimePage)
                        .where(realtimePage.realtimePageKey.tsFrameArrival.between(begin, end)
                                .and(realtimePage.realtimePageKey.srcIp.eq(dstInfo.get(realtimePage.realtimePageKey.srcIp)))
                                .and(realtimePage.realtimePageKey.srcPort.eq(dstInfo.get(realtimePage.realtimePageKey.srcPort))))
                        .groupBy(realtimePage.httpHost, realtimePage.httpUri, realtimePage.httpUriSplit)
                        .orderBy(realtimePage.httpHost.asc(), realtimePage.httpUri.asc(), realtimePage.httpUriSplit.asc())
                        .fetch();

                List<Object> dstInfoResultList = new ArrayList<>();
                if(dstInfoResult != null && dstInfoResult.size() > 0) {
                    for(Tuple tuple : dstInfoResult) {
                        Map<String, Object> hostMap = new HashMap<>();
                        hostMap.put("httpHost", tuple.get(realtimePage.httpHost));
                        hostMap.put("httpUri", tuple.get(realtimePage.httpUri));
                        hostMap.put("httpUriSplit", tuple.get(realtimePage.httpUriSplit));
                        dstInfoResultList.add(hostMap);
                    }
                }
                map.put("result", dstInfoResultList);
                result.add(map);
            }
        }
        return result;
    }

}
