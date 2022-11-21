package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoIspIpv4;
import com.sysone.eumaiwacs.entity.setting.MappingIspIpv4Server;
import com.sysone.eumaiwacs.service.setting.GeoIspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/geoIsp")
public class GeoIspController {

    @Autowired private GeoIspService geoIspService;

    @GetMapping("/findAllIsp")
    public List<InfoIspIpv4> findAllIsp() throws UnsupportedEncodingException {
        return geoIspService.findAllIsp();
    }

    @GetMapping("/findIpById/{groupId}")
    public List<MappingIspIpv4Server> findIpById(@PathVariable Integer groupId) throws UnsupportedEncodingException {
        return geoIspService.findIpById(groupId);
    }

    @PostMapping("/createIspPrimary")
    public Boolean createIspPrimary(@RequestBody Map<String,Object> map, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIspService.createIsp(map, loginUser, req);
        return true;
    }

    @PostMapping("/updateIspPrimary/{id}")
    public Boolean updateIspPrimary(@PathVariable Integer id, @RequestBody Map<String, Object> map, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIspService.updateIsp(id, map, loginUser, req);
        return true;
    }

    @GetMapping("/findIspPrimary/{id}")
    public InfoIspIpv4 findIspPrimary(@PathVariable Integer id){
        return geoIspService.findIspPrimary(id);
    }

    @PostMapping("/deleteIspGroup")
    public Boolean deleteIspGroup(@RequestBody Map<String, Object> continentMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = null;
        if(continentMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) continentMap.get("id")); }
        geoIspService.deleteIspGroup(idSet, loginUser, req);
        return true;
    }

    @GetMapping("/findIspIpRangeByIp")
    public Map<String, Object> findIspIpRangeByIp(@RequestParam("ip") String ip){
        return geoIspService.findIspIpRangeByIp(ip);
    }

    @PostMapping("/createMappingIsp")
    public Boolean createMappingDomesticPrivate(@RequestBody Map<String,Object> ipMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return geoIspService.createMappingIsp(ipMap, loginUser, req);
    }

    @PostMapping("/updateMappingIsp/{id}")
    public Boolean updateMappingDomesticPrivate(@PathVariable("id")Integer id, @RequestBody Map<String,Object> ipMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return geoIspService.updateMappingIsp(id, ipMap, loginUser, req);
    }

    @PostMapping("/deleteIpMapping")
    public Boolean deleteIpMapping(@RequestBody Map<String, Object> continentMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = null;
        if(continentMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) continentMap.get("id")); }
        geoIspService.deleteIpMapping(idSet, loginUser, req);
        return true;
    }

}
