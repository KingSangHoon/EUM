package com.sysone.eumaiwacs.repository.setting;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import com.sysone.eumaiwacs.entity.setting.QAuditHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AuditHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QAuditHistory auditHistory = QAuditHistory.auditHistory;

    public QueryResults<AuditHistory> searchAuditHistory(Map<String, Object> param) throws ParseException {

        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        LocalDateTime beginDateTime = Util.formatStringToLocalDateTime(begin);
        LocalDateTime endDateTime = Util.formatStringToLocalDateTime(end);

        List<Integer> userIdList = param.get("userId") == null ? null : (List<Integer>) param.get("userId");
        List<String> actionList = param.get("action") == null ? null : (List<String>) param.get("action");

        Integer limit = (Integer) param.get("limit");
        Integer offset = (Integer) param.get("offset");

        QueryResults<AuditHistory> queryResults = queryFactory.selectFrom(auditHistory)
                .where(auditHistory.date.between(beginDateTime, endDateTime), userIdIn(userIdList), actionIn(actionList))
                .orderBy(auditHistory.date.desc())
                .limit(limit)
                .offset(offset)
                .fetchResults();

        return queryResults;
    }

    private BooleanExpression userIdIn(List<Integer> userId) {
        return userId != null ? auditHistory.userId.in(userId) : null;
    }

    private BooleanExpression actionIn(List<String> actionList) {
        return actionList != null ? auditHistory.action.in(actionList) : null;
    }

}
