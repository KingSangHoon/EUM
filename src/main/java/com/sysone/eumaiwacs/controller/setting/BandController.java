package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoBand;
import com.sysone.eumaiwacs.service.setting.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/band")
public class BandController {

    @Autowired BandService bandService;

    @GetMapping("/findAll")
    public ResponseEntity<ArrayList<Map<String, Object>>> findAllPolicyByDeletedFalse() {
        return new ResponseEntity<>(bandService.findAllPolicyByDeletedFalse(), HttpStatus.OK);
    }

    @PostMapping("/insertBand")
    public ResponseEntity insertBand(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) throws IOException {
        bandService.insertBand(policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateBand/{bandId}")
    public ResponseEntity updateBand(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser, @PathVariable("bandId") Integer bandId) throws IOException {
        bandService.updateBand(policyMap, bandId, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/DeleteBand/{bandIdStr}")
    public ResponseEntity DeleteBand(@AuthenticationPrincipal LoginUser loginUser, @PathVariable("bandIdStr") String bandIdStr) throws IOException {
        bandService.DeleteBand(bandIdStr, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/find/{bandId}/{ruleId}")
    public ResponseEntity<Map<String, Object>> findOnePolicyDetail(@PathVariable("bandId") Integer bandId, @PathVariable("ruleId") Integer ruleId) {
        return new ResponseEntity<>(bandService.findOnePolicyDetail(bandId, ruleId), HttpStatus.OK);
    }

    @PostMapping("/insertRule")
    public ResponseEntity insertRule(@RequestBody Map<String, Object> policyMap, @AuthenticationPrincipal LoginUser loginUser) throws IOException {
        bandService.insertRule(policyMap, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateRule/{bandId}/{ruleId}")
    public ResponseEntity updateRule(@RequestBody Map<String, Object> policyMap, @PathVariable("bandId") Integer bandId, @PathVariable("ruleId") Integer ruleId, @AuthenticationPrincipal LoginUser loginUser) throws IOException {
        bandService.updateRule(policyMap, bandId, ruleId, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/deleteRule/{bandId}/{ruleIdStr}")
    public ResponseEntity deleteRule(@PathVariable("bandId") Integer bandId, @PathVariable("ruleIdStr") String ruleIdStr, @AuthenticationPrincipal LoginUser loginUser) throws IOException {
        bandService.deleteRule(bandId, ruleIdStr, loginUser);
        return new ResponseEntity(HttpStatus.OK);
    }







}
