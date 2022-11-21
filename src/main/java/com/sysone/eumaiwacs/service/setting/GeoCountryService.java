package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.InfoContinent;
import com.sysone.eumaiwacs.entity.setting.InfoCountry;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GeoCountryService {
    List<InfoContinent> findAllContinent();
    List<Object> findAllCountry();
    List<InfoCountry> findAllCountryByCode(String code);
    Map<String, Object> findMappingGeoip(String continentCode, String countryCode, Integer nextPage, Integer offset);
    Integer findCntMappingGeoip(String continentCode, String countryCode);
    List<Object> findCountryIpRangeByIp(String ip);
    Boolean creacteContinent(Map<String, Object> continentMap);
    Map<String, Object> findInfoContinent(Integer continentCode);
    void deleteContinent(Set<Integer> idSet);
    Boolean updateInfoContinent(Integer continentId, Map<String, Object> continentMap);
    List<Object> findAllForContinentInCountry(Integer continentId);
    List<Object> findCountryByModifyFlagFalse();
    Boolean createInfoCountry(Map<String, Object> countryMap);

    void deleteCountry(Set<Integer> idSet);

    Map<String, Object> findInfoCountry(Integer continentId, Integer countryId);
}
