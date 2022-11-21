package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoApplication;
import com.sysone.eumaiwacs.service.setting.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/application")
public class ApplicationController {

    @Autowired ApplicationService appliactionService;

    @GetMapping("/findAll")
    public ResponseEntity<List<InfoApplication>> findAllPolicyByDeletedFalse() {
        return new ResponseEntity<>(appliactionService.findAllPolicyByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findOnePolicyDetail(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(appliactionService.findOnePolicyDetail(id), HttpStatus.OK);
    }

    @GetMapping("/detailFindAll")
    public ResponseEntity<List<Map<String, Object>>> detailFindAll() {
        return new ResponseEntity<>(appliactionService.detailFindAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insertPolicyById(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        appliactionService.insertPolicyById(policyMap, loginUser, req);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updatePolicyById(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        appliactionService.updatePolicyById(policyMap, loginUser, req);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete/{idStr}")
    public ResponseEntity deletePolicy(@PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        appliactionService.deletePolicy(idStr, loginUser, req);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/searchIpAndMacAndPortByDateOrIp")
    public ResponseEntity<Map<String, Object>> searchIpAndMacAndPortByDateOrIp(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        return new ResponseEntity(appliactionService.searchIpAndMacAndPortByDateOrIp(policyMap), HttpStatus.OK);
    }

}
