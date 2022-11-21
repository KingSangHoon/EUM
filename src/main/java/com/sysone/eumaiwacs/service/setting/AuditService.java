package com.sysone.eumaiwacs.service.setting;

import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface AuditService {

    QueryResults<AuditHistory> searchAudit(Map<String, Object> param) throws ParseException;
    List<Object[]> searchAuditByExcel(Map<String, Object> param);
}
