package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.entity.setting.InfoUserAgent;
import com.sysone.eumaiwacs.service.setting.CodeUserAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/code/userAgent")
public class CodeUserAgentController {

    @Autowired CodeUserAgentService codeUserAgentService;

    @GetMapping("/findUserAgentType/{category}")
    public List<Object> findUserAgentByCateogoryType(@PathVariable("category") String category) {
        return codeUserAgentService.findUserAgentTypeByCategory(category);
    }

    @PostMapping("/findUserAgent/{category}")
    public List<InfoUserAgent> findUserAgent(@PathVariable("category") String category, @RequestBody Map<String,Object> params) {
        String key = (String) params.get("value");
        return codeUserAgentService.findUserAgent(category, key);
    }

    @GetMapping("/findAllAgent")
    public List<InfoUserAgent> findAllAgent() { return codeUserAgentService.findAllAgent(); }

    @PostMapping("/findAgent/{nextPage}/{offset}")
    public List<Object> findAgent(@RequestBody Map<String,Object> userAgentMap, @PathVariable Integer nextPage, @PathVariable Integer offset) {
        return codeUserAgentService.findAgent(userAgentMap, nextPage, offset);
    }

    @PostMapping("/findCntAllAgent")
    public long findCntAllAgent(@RequestBody Map<String,Object> userAgentMap) {
        return codeUserAgentService.findCntAllAgent(userAgentMap);
    }

    @PostMapping("/findUserAgentByUser")
    public List<Object> findUserAgentByUser(@RequestBody Map<String,Object> userAgentMap) {
        return codeUserAgentService.findUserAgentByUser(userAgentMap);
    }


}
