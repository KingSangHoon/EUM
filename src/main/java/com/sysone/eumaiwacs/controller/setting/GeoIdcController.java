package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoIdc;
import com.sysone.eumaiwacs.entity.setting.MappingIdcServer;
import com.sysone.eumaiwacs.service.setting.GeoIdcService;
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
@RequestMapping("/api/setting/geoIdc")
public class GeoIdcController {

    @Autowired private GeoIdcService geoIdcService;

    @GetMapping("/findAllIdc")
    public List<InfoIdc> findAllIdc() throws UnsupportedEncodingException {
        return geoIdcService.findAllIdc();
    }

    @GetMapping("/findIpById/{groupId}")
    public List<MappingIdcServer> findIpById(@PathVariable Integer groupId){
        return geoIdcService.findIpById(groupId);
    }

    @PostMapping("/createIdcPrimary")
    public Boolean createIdcPrimary(@RequestBody Map<String,Object> map, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIdcService.createIdc(map, loginUser, req);
        return true;
    }

    @PostMapping("/updateIdcPrimary/{id}")
    public Boolean updateIdcPrimary(@PathVariable Integer id, @RequestBody Map<String, Object> map, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIdcService.updateIdc(id, map, loginUser, req);
        return true;
    }

    @PostMapping("/deleteIdc")
    public Boolean deleteIdc(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = null;
        if(param.get("id") != null) { idSet = Util.getDeviceSetInteger((String) param.get("id")); }
        geoIdcService.delete(idSet, loginUser, req);
        return true;
    }

    @GetMapping("/findIdcPrimary/{id}")
    public InfoIdc findIdcPrimary(@PathVariable Integer id){
        return geoIdcService.findIdcPrimary(id);
    }

    @GetMapping("/findIdcIpRangeByIp")
    public List<MappingIdcServer> findIdcIpRangeByIp(@RequestParam("ip") String ip){
        return geoIdcService.findIdcIpRangeByIp(ip);
    }

    @PostMapping("/createIdcMappingIp")
    public Boolean createIdcMappingIp(@RequestBody Map<String,Object> map, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIdcService.createIdcMappingIp(map, loginUser, req);
        return true;
    }

    @PostMapping("/updateIdcMappingIp/{id}")
    public Boolean updateIdcMappingIp(@RequestBody Map<String,Object> map, @PathVariable Integer id, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        geoIdcService.updateIdcMappingIp(map, id, loginUser, req);
        return true;
    }

    @PostMapping("/deleteIdcMappingIp")
    public Boolean deleteIdcMappingIp(@RequestBody Map<String, Object> idcMappingMap, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = null;
        if(idcMappingMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) idcMappingMap.get("id")); }
        geoIdcService.deleteIpMapping(idSet, loginUser, req);
        return true;
    }


}
