package com.sysone.eumaiwacs.controller.protocol;

import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;
import com.sysone.eumaiwacs.service.protocol.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/protocol/uri")
public class UriController
{
    @Autowired
    private UriService uriService;

    @PostMapping("/find")
    public Object find(@RequestBody Map<String, Object> param) {
        return uriService.searchRealtimeUri(param);
    }

    @PostMapping("/detail")
    public RealtimeUri detail(@RequestBody Map<String, Object> param) {
        return uriService.viewRealtimeUri(param);
    }
}
