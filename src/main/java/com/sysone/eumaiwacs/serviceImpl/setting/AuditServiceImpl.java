package com.sysone.eumaiwacs.serviceImpl.setting;

import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import com.sysone.eumaiwacs.repository.setting.AuditHistoryRepositoryCustom;
import com.sysone.eumaiwacs.service.setting.AuditService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuditHistoryRepositoryCustom auditHistoryRepositoryCustom;

    @Override
    public QueryResults<AuditHistory> searchAudit(Map<String, Object> param) throws ParseException {

        QueryResults<AuditHistory> result = auditHistoryRepositoryCustom.searchAuditHistory(param);

        if(result != null) {
            List<AuditHistory> resultList = result.getResults();
            if(resultList.size() > 0) {
                for(AuditHistory ah : resultList) {
                    if(ah.getMenuDepth1() != null) ah.setMenuDepth1(Util.latin1ToUtf8(ah.getMenuDepth1()));
                    if(ah.getMenuDepth2() != null) ah.setMenuDepth2(Util.latin1ToUtf8(ah.getMenuDepth2()));
                    if(ah.getMenuDepth3() != null) ah.setMenuDepth3(Util.latin1ToUtf8(ah.getMenuDepth3()));
                    if(ah.getMenuDepth4() != null) ah.setMenuDepth4(Util.latin1ToUtf8(ah.getMenuDepth4()));
                    if(ah.getTarget() != null) ah.setTarget(Util.latin1ToUtf8(ah.getTarget()));
                }
            }
        }
        return result;
    }

    @Override
    public List<Object[]> searchAuditByExcel(Map<String, Object> param) {

        String begin = (String) param.get("begin");
        String end = (String) param.get("end");

        LocalDateTime beginDateTime = Util.formatStringToLocalDateTime(begin);
        LocalDateTime endDateTime = Util.formatStringToLocalDateTime(end);

        List<Integer> userIdList = param.get("userId") == null ? null : (List<Integer>) param.get("userId");
        List<String> actionList = param.get("action") == null ? null : (List<String>) param.get("action");

        List<String> field = param.get("field") == null ? null : (List<String>) param.get("field");
        List<String> convertField = Util.convertCamelToSnake(field);
        String selectField = StringUtils.join(convertField, ",");

        Boolean filter = (Boolean) param.get("filter");

        StringBuilder query = new StringBuilder();

        query.append("SELECT ").append(selectField).append(" FROM tbl_audit_history ");
        query.append("WHERE date BETWEEN '").append(beginDateTime).append("' AND '").append(endDateTime).append("' ");

        if(filter) {
            if(userIdList != null && userIdList.size() > 0) {
                String userIdField = StringUtils.join(userIdList, ",");
                query.append(" AND user_id IN (").append(userIdField).append(") ");
            }
            if(actionList != null && actionList.size() > 0) {
                StringBuilder stb = new StringBuilder();

                for(String str : actionList) {
                    stb.append("'").append(str).append("',");
                }

                String queryStr = stb.toString();
                queryStr = queryStr.substring(0, queryStr.length()-1);
                query.append(" AND action IN (").append(queryStr).append(") ");
            }
        }

        query.append(" ORDER BY date ASC ");

        List<Object[]> queryResult = entityManager.createNativeQuery(String.format(query.toString())).getResultList();
        return queryResult;
    }

}
