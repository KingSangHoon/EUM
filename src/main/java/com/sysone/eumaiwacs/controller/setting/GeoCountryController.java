package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoContinent;
import com.sysone.eumaiwacs.entity.setting.InfoCountry;
import com.sysone.eumaiwacs.service.setting.GeoCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/geoCountry")
public class GeoCountryController {

    @Autowired
    private GeoCountryService geoCountryService;

    @GetMapping("/findAllCountry")
    public List<InfoContinent> findAllContinent() {
        return geoCountryService.findAllContinent();
    }

    @GetMapping("/findAllCountryByCode/{code}")
    public List<InfoCountry> findAllCountryByCode(@PathVariable String code){ return geoCountryService.findAllCountryByCode(code); }

    @GetMapping("/findMappingGeoip/{continentCode}/{countryCode}/{nextPage}/{offset}")
    public Map<String, Object> findMappingGeoip(@PathVariable String continentCode, @PathVariable String countryCode, @PathVariable Integer nextPage, @PathVariable Integer offset) {
        return geoCountryService.findMappingGeoip(continentCode, countryCode, nextPage, offset);
    }

    @GetMapping("/findCntMappingGeoip/{continentCode}/{countryCode}")
    public Integer findCntMappingGeoip(@PathVariable String continentCode, @PathVariable String countryCode) {
        return geoCountryService.findCntMappingGeoip(continentCode, countryCode);
    }

    @GetMapping("/findCountryIpRangeByIp")
    public List<Object> findCountryIpRangeByIp(@RequestParam("ip") String ip) throws UnsupportedEncodingException {
        return geoCountryService.findCountryIpRangeByIp(ip);
    }

    @PostMapping("/createContinent")
    public Boolean creacteContinent(@RequestBody Map<String,Object> continentMap) throws UnsupportedEncodingException {
        return geoCountryService.creacteContinent(continentMap);
    }

    @GetMapping("/findInfoContinent/{continentId}")
    public Map<String, Object> findInfoContinent(@PathVariable Integer continentId) {
        return geoCountryService.findInfoContinent(continentId);
    }

    @PostMapping("/deleteContinent")
    public Boolean deleteContinent(@RequestBody Map<String, Object> continentMap) {
        Set<Integer> idSet = null;
        if(continentMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) continentMap.get("id")); }
        geoCountryService.deleteContinent(idSet);
        return true;
    }

    @PostMapping("/updateContinent/{continentId}")
    public Boolean updateCustomContinent(@PathVariable Integer continentId, @RequestBody Map<String,Object> continentMap) throws UnsupportedEncodingException {
        return geoCountryService.updateInfoContinent(continentId, continentMap);
    }

    @GetMapping("/findCountryByModifyFlagFalse")
    List<Object> findCountryByModifyFlagFalse() {
        return geoCountryService.findCountryByModifyFlagFalse();
    }

    @GetMapping("/findAllForContinentInCountry/{continentId}")
    public List<Object> findAllForContinentInCountry(@PathVariable Integer continentId){
        return geoCountryService.findAllForContinentInCountry(continentId);
    }

    @PostMapping("/createCountry")
    public Boolean createCountry(@RequestBody Map<String,Object> countryMap) throws UnsupportedEncodingException {
        return geoCountryService.createInfoCountry(countryMap);
    }

    @PostMapping("/deleteCountry")
    public Boolean deleteCountry(@RequestBody Map<String, Object> countryMap) {
        Set<Integer> idSet = null;
        if(countryMap.get("id") != null) { idSet = Util.getDeviceSetInteger((String) countryMap.get("id")); }
        geoCountryService.deleteCountry(idSet);
        return true;
    }

    @GetMapping("/findContinentInCountry/{continentId}/{countryId}")
    public Map<String, Object> findContinentInCountry(@PathVariable Integer continentId, @PathVariable Integer countryId) {
        return geoCountryService.findInfoCountry(continentId, countryId);
    }


}
