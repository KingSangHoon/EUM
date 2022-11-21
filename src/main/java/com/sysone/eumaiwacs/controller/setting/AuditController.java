package com.sysone.eumaiwacs.controller.setting;

import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import com.sysone.eumaiwacs.service.setting.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping("/search")
    public QueryResults<AuditHistory> search(@RequestBody Map<String, Object> param) throws ParseException {
        return auditService.searchAudit(param);
    }

}
