package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.service.setting.CriticalService;
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
@RequestMapping("/api/setting/critical")
public class CriticalController {

    @Autowired CriticalService criticalService;

    @GetMapping("/findByLastDefault/{oneDept}")
    public ResponseEntity<Map<String, Object>> findLastDefault(@PathVariable("oneDept") String oneDept) {
        return new ResponseEntity<>(criticalService.findByLastDefault(oneDept), HttpStatus.OK);
    }

    @GetMapping("/findByTypeAndSub2Id/{oneDept}/{sub2Id}")
    public ResponseEntity<Map<String, Object>> findByTypeAndSub2Id(@PathVariable("oneDept") String oneDept, @PathVariable("sub2Id") Integer sub2Id) {
        return new ResponseEntity<>(criticalService.findByTypeAndSub2Id(oneDept, sub2Id), HttpStatus.OK);
    }

    @PostMapping("/insertDefaultResource/{oneDept}")
    public ResponseEntity insertDefaultResource(@PathVariable("oneDept") String oneDept, @RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        criticalService.insertDefaultResource(oneDept, policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/insertPolicy/{oneDept}")
    public ResponseEntity insertPolicy(@PathVariable("oneDept") String oneDept, @RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        criticalService.insertPolicy(oneDept, policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updatePolicy/{oneDept}/{policyId}")
    public ResponseEntity updatePolicy(@PathVariable("oneDept") String oneDept, @PathVariable("policyId") Integer policyId, @RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) {
        criticalService.updatePolicy(oneDept, policyId, policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findPolicyByPolicyId/{oneDept}/{policyId}")
    public ResponseEntity<Map<String, Object>> findPolicyByPolicyId(@PathVariable("oneDept") String oneDept, @PathVariable("policyId") Integer policyId) {
        return new ResponseEntity<>(criticalService.findPolicyByPolicyId(oneDept, policyId), HttpStatus.OK);
    }

    @GetMapping("/deletePolicy/{oneDept}/{idStr}")
    public ResponseEntity deletePolicy(@PathVariable("oneDept") String oneDept, @PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        criticalService.deletePolicyById(oneDept, idStr, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findPolicyAll/{oneDept}")
    public ResponseEntity<List<Map<String, Object>>> findPolicyAll(@PathVariable("oneDept") String oneDept) {
        return new ResponseEntity<>(criticalService.findPolicyAll(oneDept), HttpStatus.OK);
    }

}
