package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoDomesticPrimary;
import com.sysone.eumaiwacs.service.setting.GeoDomesticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/geoDomestic")
public class GeoDomesticController {

    @Autowired private GeoDomesticService geoDomesticService;

    @GetMapping("/findDomesticAll/{primaryId}/{sub1Id}/{sub2Id}")
    public List<Object> findDomesticAll(@PathVariable Integer primaryId, @PathVariable Integer sub1Id, @PathVariable Integer sub2Id) {
        return geoDomesticService.findDomesticAll(primaryId, sub1Id, sub2Id);
    }

    @PostMapping("/find/domestic")
    public Object findDomestic(@RequestBody Map<String, Object> param) {
        return geoDomesticService.findDomestic(param);
    }

    @PostMapping("/find/ip")
    public Object findIp(@RequestBody Map<String, Object> param) {
        return geoDomesticService.findIp(param);
    }

    @PostMapping("/search")
    public Object search(@RequestBody Map<String, Object> param) {
        return geoDomesticService.search(param);
    }

    @PostMapping("/deleteInfoDomesticPrimary")
    public Boolean deleteInfoDomesticPrimary(@RequestBody Map<String, Object> map) {
        Set<Integer> idSet = null;
        if(map.get("id") != null) { idSet = Util.getDeviceSetInteger((String) map.get("id")); }
        geoDomesticService.deleteInfoDomesticPrimary(idSet);
        return true;
    }

    @PostMapping("/deleteInfoDomesticSub1")
    public Boolean deleteInfoDomesticSub1(@RequestBody Map<String, Object> map) {
        Set<Integer> idSet = null;
        if(map.get("id") != null) { idSet = Util.getDeviceSetInteger((String) map.get("id")); }
        geoDomesticService.deleteInfoDomesticSub1(idSet);
        return true;
    }

    @PostMapping("/deleteInfoDomesticSub2")
    public Boolean deleteInfoDomesticSub2(@RequestBody Map<String, Object> map) {
        Set<Integer> idSet = null;
        if(map.get("id") != null) { idSet = Util.getDeviceSetInteger((String) map.get("id")); }
        geoDomesticService.deleteInfoDomesticSub2(idSet);
        return true;
    }

    @GetMapping("/findInfoDomesticPrimary/{primaryId}")
    public Map<String, Object> findInfoDomesticPrimary(@PathVariable Integer primaryId) {
        return geoDomesticService.findInfoDomesticPrimary(primaryId);
    }

    @GetMapping("/findInfoDomesticSub1/{primaryId}/{sub1Id}")
    public Map<String, Object> findInfoDomesticSub1(@PathVariable Integer primaryId, @PathVariable Integer sub1Id) {
        return geoDomesticService.findInfoDomesticSub1(primaryId, sub1Id);
    }

    @GetMapping("/findInfoDomesticSub2/{primaryId}/{sub1Id}/{sub2Id}")
    public Map<String, Object> findInfoDomesticSub2(@PathVariable Integer primaryId, @PathVariable Integer sub1Id, @PathVariable Integer sub2Id) {
        return geoDomesticService.findInfoDomesticSub2(primaryId, sub1Id, sub2Id);
    }

    @GetMapping("/findAllForprimary")
    public List<InfoDomesticPrimary> findAllForPrimary() {
        return geoDomesticService.findAllForPrimary();
    }

    @GetMapping("/findAllForPrimaryInSub1/{primaryId}")
    public ArrayList<Object> findAllForPrimaryInSub1(@PathVariable Integer primaryId) {
        return geoDomesticService.findAllForPrimaryInSub1(primaryId);
    }

    @GetMapping("/findAllForPrimaryInSub1AndSub2/{primaryId}/{sub1Id}")
    public ArrayList<Object> findAllForPrimaryInSub1AndSub2(@PathVariable Integer primaryId, @PathVariable Integer sub1Id) {
        return geoDomesticService.findAllForPrimaryInSub1AndSub2(primaryId, sub1Id);
    }

    @PostMapping("/createDomesticPrimary")
    public Boolean createDomesticPrimary(@RequestBody Map<String,Object> domesticMap) {
        geoDomesticService.createDomesticPrimary(domesticMap);
        return true;
    }

    @PostMapping("/createDomesticSub1")
    public Boolean createDomesticSub1(@RequestBody Map<String,Object> domesticMap) {
        geoDomesticService.createDomesticSub1(domesticMap);
        return true;
    }

    @PostMapping("/createDomesticSub2")
    public Boolean createDomesticSub2(@RequestBody Map<String,Object> domesticMap) {
        geoDomesticService.createDomesticSub2(domesticMap);
        return true;
    }

    @PostMapping("/updateDomesticPrimary/{primaryId}")
    public Boolean updateDomesticPrimary(@RequestBody Map<String,Object> domesticMap, @PathVariable Integer primaryId) {
        geoDomesticService.updateDomesticPrimary(domesticMap, primaryId);
        return true;
    }

    @PostMapping("/updateDomesticSub1/{primaryId}/{sub1Id}")
    public Boolean updateDomesticSub1(@RequestBody Map<String,Object> sub1Map, @PathVariable Integer primaryId, @PathVariable Integer sub1Id) {
        geoDomesticService.updateDomesticSub1(sub1Map, primaryId, sub1Id);
        return true;
    }

    @PostMapping("/updateDomesticSub2/{primaryId}/{sub1Id}/{sub2Id}")
    public Boolean updateDomesticSub2(@RequestBody Map<String,Object> sub2Map, @PathVariable Integer primaryId, @PathVariable Integer sub1Id, @PathVariable Integer sub2Id) {
        geoDomesticService.updateDomesticSub2(sub2Map, primaryId, sub1Id, sub2Id);
        return true;
    }

    @PostMapping("/createMappingDomesticPublic")
    public Boolean createMappingDomesticPublic(@RequestBody Map<String,Object> domesticMap) {
        return geoDomesticService.createMappingDomesticPublic(domesticMap);
    }

    @PostMapping("/createMappingDomesticPrivate")
    public Boolean createMappingDomesticPrivate(@RequestBody Map<String,Object> domesticMap) {
        return geoDomesticService.createMappingDomesticPrivate(domesticMap);
    }

    @PostMapping("/updateMappingDomesticPrivate/{id}")
    public Boolean updateMappingDomesticPrivate(@PathVariable("id")Integer id, @RequestBody Map<String,Object> ipMap) {
        return geoDomesticService.updateMappingDomesticPrivate(id, ipMap);
    }

    @PostMapping("/deleteMappingDomestic")
    public Boolean deleteMappingDomestic(@RequestBody Map<String,Object> ipMap) {
        Set<Integer> idSet = null;
        if(ipMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) ipMap.get("id")); }
        geoDomesticService.deleteMappingDomestic(idSet);
        return true;
    }

    @PostMapping("/createMultiMappingDomesticPublic")
    public Boolean createMultiMappingDomesticPublic(@RequestBody List<Map<String, Object>> ipMap) {
        geoDomesticService.createMultiMappingDomesticPublic(ipMap);
        return true;
    }
}
