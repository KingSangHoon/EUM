package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.NdpiProtocol;
import com.sysone.eumaiwacs.service.setting.CodeProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/code/protocol")
public class CodeProtocolController {

    @Autowired CodeProtocolService codeProtocolService;

    @GetMapping("/findAllProtocol")
    public List<NdpiProtocol> findAllProtocol(){
        return codeProtocolService.findProtocolAll();
    }

    @GetMapping("/findAllMappingProtocol")
    public List<Map<String,Object>> findByMappingNdpiProtocol(){
        return codeProtocolService.findByMappingProtocol();
    }

    @PostMapping("/createProtocolMapping")
    public void createProtocolMapping(@RequestBody Map<String,Object> map) {
        codeProtocolService.insertByProptocol(map);
    }

    @PostMapping("/deleteProtocolMapping")
    public void deleteProtocolMapping(@RequestBody Map<String,Object> map) {
        Set<Integer> idSet = null;
        if(map.get("id") != null) { idSet = Util.getDeviceSetInteger((String) map.get("id")); }
        codeProtocolService.deleteProtocolMapping(idSet);
    }

    @GetMapping("/findOneProtocol/{mappingId}")
    public Map<String, Object> findOneProtocol(@PathVariable("mappingId") Integer mappingId) {
        return codeProtocolService.findOneProtocol(mappingId);
    }

    @PostMapping("/updateProtocolMapping/{mappingId}")
    public void updateProtocolMapping(@PathVariable("mappingId") Integer mappingId, @RequestBody Map<String,Object> map) {
        codeProtocolService.updateProtocolMapping(map, mappingId);
    }



}
