package com.sysone.eumaiwacs.common;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import com.sysone.eumaiwacs.repository.setting.AuditHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Component
public class AuditUtil {

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    private String getIp() {
        InetAddress local;
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
        return local.getHostAddress();
    }

    public void insertAudit(LoginUser loginUser, String action, String menu1, String menu2, String menu3, String menu4, String target, HttpServletRequest req) {

        String referer = req.getHeader("Referer");
        String[] splitPageUrl = referer.split("/");

        String pageUrl = "/";
        if(splitPageUrl.length > 3) {
            for(int i=3;i<splitPageUrl.length;i++) pageUrl+= splitPageUrl[i]+"/";
            pageUrl = pageUrl.substring(0, pageUrl.length()-1);
        }

        String actionUrl = req.getRequestURI();

        AuditHistory audit = new AuditHistory();
        audit.setUserId(loginUser.getUserId());
        audit.setLoginId(loginUser.getLoginId());
        audit.setAction(action);
        audit.setMenuDepth1((menu1 != null) ? Util.utf8ToLatin1(menu1) : null);
        audit.setMenuDepth2((menu2 != null) ? Util.utf8ToLatin1(menu2) : null);
        audit.setMenuDepth3((menu3 != null) ? Util.utf8ToLatin1(menu3) : null);
        audit.setMenuDepth4((menu4 != null) ? Util.utf8ToLatin1(menu4) : null);
        audit.setTarget((target != null) ? Util.utf8ToLatin1(target) : null);
        audit.setPageUrl(pageUrl);
        audit.setActionUrl(actionUrl);
        audit.setDate(LocalDateTime.now());
        audit.setIp(getIp());

        auditHistoryRepository.save(audit);
    }
}
