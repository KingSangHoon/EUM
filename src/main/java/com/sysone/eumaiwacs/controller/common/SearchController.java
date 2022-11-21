package com.sysone.eumaiwacs.controller.common;

import com.sysone.eumaiwacs.entity.setting.InfoIdc;
import com.sysone.eumaiwacs.entity.setting.InfoIspIpv4;
import com.sysone.eumaiwacs.entity.setting.Test;
import com.sysone.eumaiwacs.service.common.SearchService;
import com.sysone.eumaiwacs.service.setting.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private GeoIspService geoIspService;
    @Autowired
    private GeoIdcService geoIdcService;
    @Autowired
    private GeoCountryService geoCountryService;
    @Autowired
    private GeoDomesticService geoDomesticService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/isp")
    public List<InfoIspIpv4> searchIsp() {
        return geoIspService.findAllIsp();
    }

    @GetMapping("/idc")
    public List<InfoIdc> searchIdc() {
        return geoIdcService.findAllIdc();
    }

    @GetMapping("/findAllRedInfo")
    public ResponseEntity<Map<String, Object>> findAllRedInfo() {
        return new ResponseEntity(applicationService.searchAllIpAndMacAndPortByIp(), HttpStatus.OK);
    }

    @GetMapping("/geo/{category}")
    public List<Object> searchGeo(@PathVariable("category") String category) {
        List<Object> result = new ArrayList<>();

        if(category.equals("country")) result.addAll(geoCountryService.findAllCountry());
        else if(category.equals("domestic")) result.addAll(geoDomesticService.findAllDomestic());
        return result;
    }

    @PostMapping("/http/page")
    public List<Object> searchHttpPageCategory(@RequestBody Map<String, Object> param) {
        return searchService.searchHttpPageCategory(param);
    }

    @PostMapping("/http/uri")
    public List<Object> searchHttpUriCategory(@RequestBody Map<String, Object> param) {
        return searchService.searchHttpUriCategory(param);
    }

    @GetMapping("/test")
    public void test() {
        searchService.enumTest();
    }
}
