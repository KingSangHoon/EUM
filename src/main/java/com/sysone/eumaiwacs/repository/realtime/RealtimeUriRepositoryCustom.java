package com.sysone.eumaiwacs.repository.realtime;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.QRealtimeUri;
import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;
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
public class RealtimeUriRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QRealtimeUri realtimeUri = QRealtimeUri.realtimeUri;

    public RealtimeUri findRealtimeUriDetail(Map<String, Object> param) {
        String srcIp = (String) param.get("srcIp");
        String dstIp = (String) param.get("dstIp");
        Integer srcPort = (Integer) param.get("srcPort");
        Integer dstPort = (Integer) param.get("dstPort");
        BigDecimal tsFrameArrival = new BigDecimal((String) param.get("tsFrameArrival"));
        BigDecimal tsFrameLandOff = new BigDecimal((String) param.get("tsFrameLandOff"));
        BigInteger pageIdx = new BigInteger((String) param.get("pageIdx"));
        BigInteger uriIdx = new BigInteger((String) param.get("uriIdx"));

        RealtimeUri result = queryFactory.selectFrom(realtimeUri)
                .where(realtimeUri.realtimeUriKey.srcIp.eq(srcIp).and(realtimeUri.realtimeUriKey.dstIp.eq(dstIp))
                        .and(realtimeUri.realtimeUriKey.srcPort.eq(srcPort)).and(realtimeUri.realtimeUriKey.dstPort.eq(dstPort))
                        .and(realtimeUri.realtimeUriKey.tsFrameArrival.eq(tsFrameArrival)).and(realtimeUri.realtimeUriKey.tsFrameLandOff.eq(tsFrameLandOff))
                        .and(realtimeUri.realtimeUriKey.pageIdx.eq(pageIdx)).and(realtimeUri.realtimeUriKey.uriIdx.eq(uriIdx)))
                .fetchOne();

        return result;
    }

    public List<RealtimeUri> findRealtimeUriByRealtimePageKey(Map<String, Object> param) {
        String srcIp = (String) param.get("srcIp");
        String dstIp = (String) param.get("dstIp");
        Integer dstPort = (Integer) param.get("dstPort");
        BigDecimal tsFrameArrival = new BigDecimal((String) param.get("tsFrameArrival"));
        BigDecimal tsFrameLandOff = new BigDecimal((String) param.get("tsFrameLandOff"));
        BigInteger pageIdx = new BigInteger((String) param.get("pageIdx"));

        List<RealtimeUri> result = queryFactory.selectFrom(realtimeUri)
                .where(realtimeUri.realtimeUriKey.srcIp.eq(srcIp).and(realtimeUri.realtimeUriKey.dstIp.eq(dstIp))
                        .and(realtimeUri.realtimeUriKey.dstPort.eq(dstPort))
                        .and(realtimeUri.tsFrameArrivalPage.eq(tsFrameArrival)).and(realtimeUri.tsFrameLandoffPage.eq(tsFrameLandOff))
                        .and(realtimeUri.realtimeUriKey.pageIdx.eq(pageIdx)))
                .orderBy(realtimeUri.realtimeUriKey.tsFrameArrival.asc())
                .fetch();

        return result;
    }

    public Object searchRealtimeUri(Map<String, Object> param) {

        String type = (String) param.get("type");
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

        Boolean isRangeResTime = param.get("isRangeResTime") == null ? null : (Boolean) param.get("isRangetResTime");
        String lessUnit = param.get("lessUnit") == null ? null : (String) param.get("lessUnit");
        String moreUnit = param.get("moreUnit") == null ? null : (String) param.get("moreUnit");
        BigDecimal lessVal = param.get("lessVal") == null ? null : BigDecimal.valueOf((Integer) param.get("lessVal"));
        BigDecimal moreVal = param.get("moreVal") == null ? null : BigDecimal.valueOf((Integer) param.get("moreVal"));

        if(type.equals("excel")) {
            List<RealtimeUri> queryResult = queryFactory.selectFrom(realtimeUri)
                    .orderBy(realtimeUri.realtimeUriKey.tsFrameArrival.asc())
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(beginDate, endDate), srcZoneIspIn(srcZoneIsp), srcZoneIdcIn(srcZoneIdc), srcZoneContinentIn(srcZoneContinent), srcZoneCountryIn(srcZoneCountry),
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
                    .fetch();

            return queryResult;
        } else if(type.equals("chart")) {
            List<RealtimeUri> queryResult = queryFactory.selectFrom(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(beginDate, endDate),
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
                    .orderBy(realtimeUri.realtimeUriKey.tsFrameArrival.desc())
                    .fetch();

            return queryResult;
        } else if(type.equals("grid")) {
            Integer limit = (Integer) param.get("limit");
            Integer offset = (Integer) param.get("offset");
            String sort = "desc";
            if(param.get("sort") != null) sort = (String) param.get("sort");

            if(sort.equals("asc")) {
                QueryResults<RealtimeUri> queryResult = queryFactory.selectFrom(realtimeUri)
                        .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(beginDate, endDate),
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
                        .orderBy(realtimeUri.realtimeUriKey.tsFrameArrival.asc())
                        .limit(limit)
                        .offset(offset)
                        .fetchResults();

                return queryResult;
            } else if(sort.equals("desc")) {
                QueryResults<RealtimeUri> queryResult = queryFactory.selectFrom(realtimeUri)
                        .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(beginDate, endDate),
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
                        .orderBy(realtimeUri.realtimeUriKey.tsFrameArrival.desc())
                        .limit(limit)
                        .offset(offset)
                        .fetchResults();
                return queryResult;
            }
        }
        return "";
    }

    private BooleanExpression srcZoneIspIn(List<Integer> srcZoneIsp) { return srcZoneIsp != null ? realtimeUri.ispIdReq.in(srcZoneIsp) : null; }
    private BooleanExpression srcZoneIdcIn(List<Integer> srcZoneIdc) { return srcZoneIdc != null ? realtimeUri.idcIdReq.in(srcZoneIdc) : null; }
    private BooleanExpression srcZoneContinentIn(List<Integer> srcZoneContinent) { return srcZoneContinent != null ? realtimeUri.continentIdReq.in(srcZoneContinent) : null; }
    private BooleanExpression srcZoneCountryIn(List<Integer> srcZoneCountry) { return srcZoneCountry != null ? realtimeUri.countryIdReq.in(srcZoneCountry) : null; }
    private BooleanExpression srcZonePrimaryIn(List<Integer> srcZonePrimary) { return srcZonePrimary != null ? realtimeUri.domesticPrimaryIdReq.in(srcZonePrimary) : null; }
    private BooleanExpression srcZoneSub1In(List<Integer> srcZoneSub1) { return srcZoneSub1 != null ? realtimeUri.domesticSub1IdReq.in(srcZoneSub1) : null; }
    private BooleanExpression srcZoneSub2In(List<Integer> srcZoneSub2) { return srcZoneSub2 != null ? realtimeUri.domesticSub2IdReq.in(srcZoneSub2) : null; }

    private BooleanExpression dstZoneIspIn(List<Integer> dstZoneIsp) { return dstZoneIsp != null ? realtimeUri.ispIdRes.in(dstZoneIsp) : null; }
    private BooleanExpression dstZoneIdcIn(List<Integer> dstZoneIdc) { return dstZoneIdc != null ? realtimeUri.idcIdRes.in(dstZoneIdc) : null; }
    private BooleanExpression dstZoneContinentIn(List<Integer> dstZoneContinent) { return dstZoneContinent != null ? realtimeUri.continentIdRes.in(dstZoneContinent) : null; }
    private BooleanExpression dstZoneCountryIn(List<Integer> dstZoneCountry) { return dstZoneCountry != null ? realtimeUri.countryIdRes.in(dstZoneCountry) : null; }
    private BooleanExpression dstZonePrimaryIn(List<Integer> dstZonePrimary) { return dstZonePrimary != null ? realtimeUri.domesticPrimaryIdRes.in(dstZonePrimary) : null; }
    private BooleanExpression dstZoneSub1In(List<Integer> dstZoneSub1) { return dstZoneSub1 != null ? realtimeUri.domesticSub1IdRes.in(dstZoneSub1) : null; }
    private BooleanExpression dstZoneSub2In(List<Integer> dstZoneSub2) { return dstZoneSub2 != null ? realtimeUri.domesticSub2IdRes.in(dstZoneSub2) : null; }

    private BooleanExpression bothZoneIspIn(List<Integer> bothZoneIsp) { return bothZoneIsp != null ? realtimeUri.ispIdReq.in(bothZoneIsp).or(realtimeUri.ispIdRes.in((bothZoneIsp))) : null; }
    private BooleanExpression bothZoneIdcIn(List<Integer> bothZoneIdc) { return bothZoneIdc != null ? realtimeUri.idcIdReq.in(bothZoneIdc).or(realtimeUri.idcIdRes.in((bothZoneIdc))) : null; }
    private BooleanExpression bothZoneContinentIn(List<Integer> bothZoneContinent) { return bothZoneContinent != null ? realtimeUri.continentIdReq.in(bothZoneContinent).or(realtimeUri.continentIdRes.in((bothZoneContinent))) : null; }
    private BooleanExpression bothZoneCountryIn(List<Integer> bothZoneCountry) { return bothZoneCountry != null ? realtimeUri.countryIdReq.in(bothZoneCountry).or(realtimeUri.countryIdRes.in((bothZoneCountry))) : null; }
    private BooleanExpression bothZonePrimaryIn(List<Integer> bothZonePrimary) { return bothZonePrimary != null ? realtimeUri.domesticPrimaryIdReq.in(bothZonePrimary).or(realtimeUri.domesticPrimaryIdRes.in((bothZonePrimary))) : null; }
    private BooleanExpression bothZoneSub1In(List<Integer> bothZoneSub1) { return bothZoneSub1 != null ? realtimeUri.domesticSub1IdReq.in(bothZoneSub1).or(realtimeUri.domesticSub1IdRes.in((bothZoneSub1))) : null; }
    private BooleanExpression bothZoneSub2In(List<Integer> bothZoneSub2) { return bothZoneSub2 != null ? realtimeUri.domesticSub2IdReq.in(bothZoneSub2).or(realtimeUri.domesticSub2IdRes.in((bothZoneSub2))) : null; }


    private BooleanExpression searchSrcIp(Boolean isTextSrcIp, String textSrcIp, List<String> srcIp) {
        if(isTextSrcIp != null && isTextSrcIp) return textSrcIp != null ? realtimeUri.realtimeUriKey.srcIp.contains(textSrcIp) : null;
        else return srcIp != null ? realtimeUri.realtimeUriKey.srcIp.in(srcIp) : null;
    }

    private BooleanExpression searchDstIp(Boolean isTextDstIp, String textDstIp, List<String> dstIp) {
        if(isTextDstIp != null && isTextDstIp) return textDstIp != null ? realtimeUri.realtimeUriKey.dstIp.contains(textDstIp) : null;
        else return dstIp != null ? realtimeUri.realtimeUriKey.dstIp.in(dstIp) : null;
    }

    private BooleanExpression searchBothIp(Boolean isTextBothIp, String textBothIp, List<String> bothIp) {
        if(isTextBothIp != null && isTextBothIp) return realtimeUri.realtimeUriKey.srcIp.contains(textBothIp).or(realtimeUri.realtimeUriKey.dstIp.contains(textBothIp));
        else return bothIp != null ? realtimeUri.realtimeUriKey.srcIp.in(bothIp).or(realtimeUri.realtimeUriKey.dstIp.in(bothIp)) : null;
    }

    private BooleanExpression searchSrcPort(Boolean isTextSrcPort, Integer textSrcPort, List<Integer> srcPort) {
        if(isTextSrcPort != null && isTextSrcPort) return textSrcPort != null ? realtimeUri.realtimeUriKey.srcPort.eq(textSrcPort) : null;
        else return srcPort != null ? realtimeUri.realtimeUriKey.srcPort.in(srcPort) : null;
    }

    private BooleanExpression searchDstPort(Boolean isTextDstPort, Integer textDstPort, List<Integer> dstPort) {
        if(isTextDstPort != null && isTextDstPort) return textDstPort != null ? realtimeUri.realtimeUriKey.dstPort.eq(textDstPort) : null;
        else return dstPort != null ? realtimeUri.realtimeUriKey.dstPort.in(dstPort) : null;
    }

    private BooleanExpression searchBothPort(Boolean isTextBothPort, Integer textBothPort, List<Integer> bothPort) {
        if(isTextBothPort != null && isTextBothPort) return realtimeUri.realtimeUriKey.srcPort.eq(textBothPort).or(realtimeUri.realtimeUriKey.dstPort.eq(textBothPort));
        else return bothPort != null ? realtimeUri.realtimeUriKey.srcPort.in(bothPort).or(realtimeUri.realtimeUriKey.dstPort.in(bothPort)) : null;
    }

    private BooleanExpression applicationIdIn(List<Integer> applicationId){ return applicationId != null ? realtimeUri.applicationId.in(applicationId) : null; }
    private BooleanExpression transactionIdIn(List<Integer> transactionId){ return transactionId != null ? realtimeUri.transactionUrlId.in(transactionId) : null; }

    private BooleanExpression searchHost(Boolean isTextHost, String textHost, List<String> host) {
        if(isTextHost != null && isTextHost) return textHost != null ? realtimeUri.httpHost.contains(textHost) : null;
        else return host != null ? realtimeUri.httpHost.in(host) : null;
    }

    private BooleanExpression searchPage(Boolean isTextPage, String textPage, List<String> page) {
        if(isTextPage != null && isTextPage) return textPage != null ? realtimeUri.httpUri.contains(textPage) : null;
        else return page != null ? realtimeUri.httpUri.in(page) : null;
    }

    private BooleanExpression searchUseragent(Boolean isTextUseragent, String textUseragent, List<String> useragent) {
        if(isTextUseragent != null && isTextUseragent) return textUseragent != null ? realtimeUri.httpUserAgent.contains(textUseragent) : null;
        else return useragent != null ? realtimeUri.httpUserAgent.in(useragent) : null;
    }

    private BooleanExpression searchReferer(Boolean isTextReferer, String textReferer, List<String> referer) {
        if(isTextReferer != null && isTextReferer) return textReferer != null ? realtimeUri.httpReferer.contains(textReferer) : null;
        else return referer != null ? realtimeUri.httpReferer.in(referer) : null;
    }

    private BooleanExpression searchResponseCode(Boolean isTextResponseCode, Integer textResponseCode, List<Integer> responseCode) {
        if(isTextResponseCode != null && isTextResponseCode) return textResponseCode != null ? realtimeUri.httpResCode.eq(textResponseCode) : null;
        else return responseCode != null ? realtimeUri.httpResCode.in(responseCode) : null;
    }

    private BooleanExpression methodIn(List<String> method) { return method != null ? realtimeUri.httpMethod.in(method) : null; }
    private BooleanExpression browserIn(List<String> browser) { return browser != null ? realtimeUri.userAgentSoftwareName.in(browser) : null; }
    private BooleanExpression hardwareIn(List<String> hardware) { return hardware != null ? realtimeUri.userAgentHardwareType.in(hardware) : null; }
    private BooleanExpression osIn(List<String> os) { return os != null ? realtimeUri.userAgentOperatingSystemName.in(os) : null; }
    private BooleanExpression platformIn(List<String> platform) { return platform != null ? realtimeUri.userAgentOperatingPlatform.in(platform) : null; }

    private BooleanBuilder searchProtocol(List<Map<String, Object>> protocol) {

        BooleanBuilder builder = new BooleanBuilder();
        if(protocol != null) {
            for(Map<String, Object> map : protocol) {
                Integer appId = (Integer) map.get("appId");
                Integer masterId = (Integer) map.get("masterId");

                builder.or(realtimeUri.ndpiProtocolMasterId.eq(masterId).and(realtimeUri.ndpiProtocolAppId.eq(appId)));
            }
            return builder;
        }
        else return null;
    }

    private BooleanBuilder searchResponseTime(Boolean isRangeResTime, BigDecimal lessVal, BigDecimal moreVal, String lessUnit, String moreUnit) {

        BooleanBuilder builder = new BooleanBuilder();

        if(lessVal != null && lessUnit != null) {
            if(isRangeResTime != null && isRangeResTime) {
                if(moreUnit.equals("goe")) builder.and(realtimeUri.tsRsqDelayResponse.goe(moreVal));
                else if(moreUnit.equals("gt")) builder.and(realtimeUri.tsRsqDelayResponse.gt(moreVal));

                if(lessUnit.equals("loe")) builder.and(realtimeUri.tsRsqDelayResponse.loe(lessVal));
                else if(lessUnit.equals("lt")) builder.and(realtimeUri.tsRsqDelayResponse.lt(lessVal));
            } else {
                if(lessUnit.equals("goe")) builder.and(realtimeUri.tsRsqDelayResponse.goe(lessVal));
                else if(lessUnit.equals("gt")) builder.and(realtimeUri.tsRsqDelayResponse.gt(lessVal));
                else if(lessUnit.equals("loe")) builder.and(realtimeUri.tsRsqDelayResponse.loe(lessVal));
                else if(lessUnit.equals("lt")) builder.and(realtimeUri.tsRsqDelayResponse.lt(lessVal));
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
            List<String> queryResult = queryFactory.select(realtimeUri.realtimeUriKey.srcIp).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.realtimeUriKey.srcIp)
                    .orderBy(realtimeUri.realtimeUriKey.srcIp.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("dstIp")) {
            List<String> queryResult = queryFactory.select(realtimeUri.realtimeUriKey.dstIp).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.realtimeUriKey.dstIp)
                    .orderBy(realtimeUri.realtimeUriKey.dstIp.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("srcPort")) {
            List<Integer> queryResult = queryFactory.select(realtimeUri.realtimeUriKey.srcPort).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.realtimeUriKey.srcPort)
                    .orderBy(realtimeUri.realtimeUriKey.srcPort.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("dstPort")) {
            List<Integer> queryResult = queryFactory.select(realtimeUri.realtimeUriKey.dstPort).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.realtimeUriKey.dstPort)
                    .orderBy(realtimeUri.realtimeUriKey.dstPort.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("application")) {
            List<Tuple> queryResult = queryFactory.select(realtimeUri.applicationId, realtimeUri.applicationName).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.applicationId, realtimeUri.applicationName)
                    .orderBy(realtimeUri.applicationId.asc())
                    .fetch();

            if(queryResult != null && queryResult.size() > 0) {
                for(Tuple tuple : queryResult) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("applicationId", tuple.get(realtimeUri.applicationId));
                    map.put("applicationName", Util.latin1ToUtf8(tuple.get(realtimeUri.applicationName)));
                    result.add(map);
                }
            }
        } else if(category.equals("host")) {
            List<String> queryResult = queryFactory.select(realtimeUri.httpHost).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.httpHost)
                    .orderBy(realtimeUri.httpHost.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("page")) {
            List<String> queryResult = queryFactory.select(realtimeUri.httpUri.concat(realtimeUri.httpUriSplit)).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.httpUri.concat(realtimeUri.httpUriSplit))
                    .orderBy(realtimeUri.httpUri.concat(realtimeUri.httpUriSplit).asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("userAgent")) {
            List<String> queryResult = queryFactory.select(realtimeUri.httpUserAgent).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.httpUserAgent)
                    .orderBy(realtimeUri.httpUserAgent.asc())
                    .fetch();

            List<String> resultList = new ArrayList<>();
            resultList.add(Constants.USERAGENT_NOT_INFO);
            resultList.add(Constants.USERAGENT_DIRECT_PARSING);
            resultList.addAll(queryResult);
            result.add(resultList);
        } else if(category.equals("referer")) {
            List<String> queryResult = queryFactory.select(realtimeUri.httpReferer).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.httpReferer)
                    .orderBy(realtimeUri.httpReferer.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("browser")) {
            List<String> queryResult = queryFactory.select(realtimeUri.userAgentSoftwareName).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.userAgentSoftwareName)
                    .orderBy(realtimeUri.userAgentSoftwareName.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("os")) {
            List<String> queryResult = queryFactory.select(realtimeUri.userAgentOperatingSystemName).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.userAgentOperatingSystemName)
                    .orderBy(realtimeUri.userAgentOperatingSystemName.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("platform")) {
            List<String> queryResult = queryFactory.select(realtimeUri.userAgentOperatingPlatform).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.userAgentOperatingPlatform)
                    .orderBy(realtimeUri.userAgentOperatingPlatform.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("hardware")) {
            List<String> queryResult = queryFactory.select(realtimeUri.userAgentHardwareType).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.userAgentHardwareType)
                    .orderBy(realtimeUri.userAgentHardwareType.asc())
                    .fetch();
            result.addAll(queryResult);
        } else if(category.equals("protocol")) {
            List<Tuple> queryResult = queryFactory.select(realtimeUri.ndpiProtocolApp, realtimeUri.ndpiProtocolAppId, realtimeUri.ndpiProtocolMaster, realtimeUri.ndpiProtocolMasterId).from(realtimeUri)
                    .where(realtimeUri.realtimeUriKey.tsFrameArrival.between(begin, end))
                    .groupBy(realtimeUri.ndpiProtocolApp, realtimeUri.ndpiProtocolAppId, realtimeUri.ndpiProtocolMaster, realtimeUri.ndpiProtocolMasterId)
                    .orderBy(realtimeUri.ndpiProtocolApp.asc())
                    .fetch();

            if(queryResult != null && queryResult.size() > 0) {
                for(Tuple tuple : queryResult) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("appName", tuple.get(realtimeUri.ndpiProtocolApp));
                    map.put("appId", tuple.get(realtimeUri.ndpiProtocolAppId));
                    map.put("masterName", tuple.get(realtimeUri.ndpiProtocolMaster));
                    map.put("masterId", tuple.get(realtimeUri.ndpiProtocolMasterId));
                    result.add(map);
                }
            }
        }
        return result;
    }
}
