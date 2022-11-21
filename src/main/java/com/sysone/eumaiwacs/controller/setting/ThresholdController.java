package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.service.setting.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/threshold")
public class ThresholdController {

    @Autowired
    private ThresholdService thresholdService;

    // Default 임계치

    @GetMapping("/find/{category}")
    public Object findLatest(@PathVariable("category") String category) {
        return thresholdService.findLatest(category);
    }

    /**
     *
     * @param dir : prev - 이전 임계치 조회, next - 다음 임계치 조회
     * @param category : transaction/uri
     * @param id : 임계치 id
     * @return
     */
    @GetMapping("/find/{category}/{dir}/{id}")
    public Object findThreshold(@PathVariable("category") String category, @PathVariable("dir") String dir, @PathVariable("id") Integer id) {
        return thresholdService.findThreshold(dir, category, id);
    }

    @PostMapping("/insert/{category}")
    public Object insertThreshold(@PathVariable("category") String category, @RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return thresholdService.insertThreshold(category, param, loginUser, req);
    }

    // 임계치 정책

    @GetMapping("/findAll/policy/{category}")
    public List<Object> findAllPolicy(@PathVariable("category") String category) {
        return thresholdService.findAllPolicy(category);
    }

    @GetMapping("/find/policy/{category}/{id}")
    public Object findPolicy(@PathVariable("category") String category, @PathVariable("id") Integer id) {
        return thresholdService.findPolicy(category, id);
    }

    @PostMapping("/insert/policy/{category}")
    public Object insertThresholdPolicy(@PathVariable("category") String category, @RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return thresholdService.insertThresholdPolicy(category, param, loginUser, req);
    }

    @PostMapping("/update/policy/{category}")
    public Object updateThresholdPolicy(@PathVariable("category") String category, @RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return thresholdService.updateThresholdPolicy(category, param, loginUser, req);
    }

    @GetMapping("/delete/policy/{category}/{idStr}")
    public void deleteThresholdPolicy(@PathVariable("category") String category, @PathVariable("idStr") String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        thresholdService.deleteThresholdPolicy(category, idStr, loginUser, req);
    }

}
