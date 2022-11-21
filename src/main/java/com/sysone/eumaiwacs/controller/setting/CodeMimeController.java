package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.entity.setting.InfoMimeType;
import com.sysone.eumaiwacs.service.setting.CodeMimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/code/mime")
public class CodeMimeController {

    @Autowired CodeMimeService codeMimeService;

    @GetMapping("/findMimeTypeCategory")
    public ArrayList getMimeTypeCategory() {
        return codeMimeService.findInfoMimeType();
    }

    @GetMapping("/findMimeName/{type}")
    public List<InfoMimeType> findMimeName(@PathVariable String type) {
        return codeMimeService.findMimeTypeByName(type);
    }

    @GetMapping("/findMimeDetailById/{id}")
    public InfoMimeType findHttpDetailById(@PathVariable Integer id) {
        return codeMimeService.findMimeDetailById(id);
    }

    @PostMapping("/createMimeDetail")
    public Boolean createHttpDetail(@RequestBody Map<String,Object> map){
        codeMimeService.createMimeDetail(map);
        return true;
    }

    @PostMapping("/updateMimeDetail/{id}")
    public Boolean updateMimeDetail(@PathVariable Integer id, @RequestBody Map<String, Object> map){
        codeMimeService.updateMimeDetail(id, map);
        return true;
    }
    @PostMapping("/detailMimes")
    public Boolean detailMimes(@RequestBody Map<String, Object> httpMap) {
        Set<Integer> idSet = null;
        if(httpMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) httpMap.get("id")); }
        codeMimeService.detailMimes(idSet);
        return true;
    }


}
