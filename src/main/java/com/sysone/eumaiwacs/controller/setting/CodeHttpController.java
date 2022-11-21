package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.service.setting.CodeHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/code/http")
public class CodeHttpController {

    @Autowired CodeHttpService codeHttpService;

    @GetMapping("/findHttpTypeCategory")
    public ArrayList getHttpTypeCategory() {
        return codeHttpService.findInfoHttpType();
    }

    @GetMapping("/findHttpName/{type}")
    public List<InfoHttpType> findHttpName(@PathVariable String type) {
        return codeHttpService.findHttpTypeByName(type);
    }

    @GetMapping("/findHttpDetailById/{id}")
    public InfoHttpType findHttpDetailById(@PathVariable Integer id) {
        return codeHttpService.findHttpDetailById(id);
    }

    @PostMapping("/createHttpDetail")
    public Boolean createHttpDetail(@RequestBody Map<String,Object> map){
        codeHttpService.createHttpDetail(map);
        return true;
    }

    @PostMapping("/updateHttpDetail/{id}")
    public Boolean updateHttpDetail(@PathVariable Integer id, @RequestBody Map<String, Object> map){
        codeHttpService.updateHttpDetail(id, map);
        return true;
    }
    @PostMapping("/detailHttps")
    public Boolean detailHttps(@RequestBody Map<String, Object> httpMap) {
        Set<Integer> idSet = null;
        if(httpMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) httpMap.get("id")); }
        codeHttpService.detailHttps(idSet);
        return true;
    }


}
