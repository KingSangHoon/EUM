package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoTransaction;
import com.sysone.eumaiwacs.service.setting.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //참조데이터 FIND
    @PostMapping("/search")
    public List<Object> searchReferTransactionCategory(@RequestBody Map<String, Object> param) {
        return transactionService.search(param);
    }

    @PostMapping("/find/chk/uri")
    public List<Object> findChkUri(@RequestBody Map<String, Object> param) {
        return transactionService.findChkUri(param);
    }

    // Transaction 설정
    @GetMapping("/findAll")
    public List<InfoTransaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/find/{id}")
    public Map<String, Object> find(@PathVariable("id") Integer id) {
        return transactionService.find(id);
    }

    @GetMapping("/delete/{idStr}")
    public void delete(@PathVariable("idStr") String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        transactionService.delete(idStr, loginUser, req);
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return transactionService.insert(param, loginUser, req);
    }

    @PostMapping("/update")
    public Object update(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return transactionService.update(param, loginUser, req);
    }

}
