package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoTransaction;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<Object> findChkUri(Map<String, Object> param);
    List<Object> search(Map<String, Object> param);

    List<InfoTransaction> findAll();
    Map<String, Object> find(Integer id);
    void delete(String idStr, LoginUser loginUser, HttpServletRequest req);
    Object insert(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    Object update(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);

}
