package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoAlarm;
import com.sysone.eumaiwacs.entity.setting.InfoApplication;
import com.sysone.eumaiwacs.service.setting.AlarmService;
import com.sysone.eumaiwacs.service.setting.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/alarm")
public class AlarmController {

    @Autowired AlarmService alarmService;

    @GetMapping("/findAll")
    public ResponseEntity<List<InfoAlarm>> findAllPolicyByDeletedFalse() {
        return new ResponseEntity<>(alarmService.findAllPolicyByDeletedFalse(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findOnePolicyDetail(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(alarmService.findOnePolicyDetail(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insertPolicy(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        alarmService.insertPolicy(policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updatePolicyById(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        alarmService.updatePolicyById(policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

}
