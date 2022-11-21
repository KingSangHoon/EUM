package com.sysone.eumaiwacs.controller.protocol;

import com.sysone.eumaiwacs.entity.realtime.RealtimePage;
import com.sysone.eumaiwacs.service.protocol.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/protocol/http")
public class HttpController {

    @Autowired
    private HttpService httpService;

    @PostMapping("/find")
    public Object find(@RequestBody Map<String, Object> param) {
        return httpService.searchRealtimePage(param);
    }

    @PostMapping("/detail")
    public Map<String, Object> detail(@RequestBody Map<String, Object> param) {
        return httpService.viewRealtimePage(param);
    }

}
