package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.common.Util;

import com.sysone.eumaiwacs.entity.setting.InfoContinent;
import com.sysone.eumaiwacs.entity.setting.InfoCountry;
import com.sysone.eumaiwacs.entity.setting.MappingContinentCountryGeo;
import com.sysone.eumaiwacs.repository.setting.InfoContinentRepository;
import com.sysone.eumaiwacs.repository.setting.InfoCountryRepository;
import com.sysone.eumaiwacs.repository.setting.InfoGeoipServerRepository;
import com.sysone.eumaiwacs.repository.setting.MappingContinentCountryGeoRepository;
import com.sysone.eumaiwacs.service.setting.GeoCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GeoCountryServiceImpl implements GeoCountryService {

    @Autowired InfoContinentRepository infoContinentRepository;
    @Autowired InfoCountryRepository infoCountryRepository;
    @Autowired InfoGeoipServerRepository infoGeoipServerRepository;
    @Autowired MappingContinentCountryGeoRepository mappingContinentCountryGeoRepository;

    @Override
    public List<InfoContinent> findAllContinent() {
        List<InfoContinent> continentAllList = infoContinentRepository.findAll();
        for(InfoContinent item : continentAllList) item.setContinentName(Util.latin1ToUtf8(item.getContinentName()));

        return continentAllList;
    }

    @Override
    public List<Object> findAllCountry() {
        List<Object> result = new ArrayList<>();

        List<InfoContinent> continentList = infoContinentRepository.findAll();

        for(InfoContinent continent : continentList) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.convertValue(continent, Map.class);
            map.put("country", infoCountryRepository.findAllByContinentCode(continent.getContinentCode()));
            result.add(map);
        }
        return result;
    }

    @Override
    public List<InfoCountry> findAllCountryByCode(String code) {
        return infoCountryRepository.findAllByContinentCode(code);
    }

    @Override
    public Map<String, Object> findMappingGeoip(String continentCode, String countryCode, Integer nextPage, Integer offset) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (countryCode.equals("null")) {

            List<Object[]> getipList = infoGeoipServerRepository.findGeoipByCountryCodeAndContinentCode(continentCode, nextPage, offset);
            for(Object[] item : getipList){
                Map<String, Object> itemMap = new HashMap<String, Object>();
                itemMap.put("id", item[0]);
                itemMap.put("country_code", item[1]);
                itemMap.put("country_name", item[2]);
                itemMap.put("start_ip", item[3]);
                itemMap.put("end_ip", item[4]);
                itemMap.put("start_ip_num", item[5]);
                itemMap.put("end_ip_num", item[6]);
                itemMap.put("modify_flag", item[7]);
                result.add(itemMap);
            }
            resultMap.put("geoip", result);
        } else {
            List<Object[]> getipList = infoGeoipServerRepository.findGeoipByCountryCode(countryCode, nextPage, offset);
            for(Object[] item : getipList){
                Map<String, Object> itemMap = new HashMap<String, Object>();
                itemMap.put("id", item[0]);
                itemMap.put("country_code", item[1]);
                itemMap.put("country_name", item[2]);
                itemMap.put("start_ip", item[3]);
                itemMap.put("end_ip", item[4]);
                itemMap.put("start_ip_num", item[5]);
                itemMap.put("end_ip_num", item[6]);
                itemMap.put("modify_flag", item[7]);
                result.add(itemMap);
            }
            resultMap.put("geoip", result);
        }
        return resultMap;
    }

    @Override
    public Integer findCntMappingGeoip(String continentCode, String countryCode) {
        Integer result = 0;

        if (countryCode.equals("null")) result = infoGeoipServerRepository.findCntByCountryCodeAndContinentCode(continentCode);
        else result = infoGeoipServerRepository.findCntByCountryCode(countryCode);

        return result;
    }

    @Override
    public List<Object> findCountryIpRangeByIp(String ip) {

        List<Object> result = new ArrayList<Object>();
        Long ipNum = Util.ipToLong(ip);
        List<Object[]> countryRangeList = infoGeoipServerRepository.findCountryIpRangeByIp(ipNum);

        for(Object[] item : countryRangeList){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("id", item[0]);
            itemMap.put("country_code", item[1]);
            itemMap.put("country_name", item[2]);
            itemMap.put("start_ip", item[3]);
            itemMap.put("end_ip", item[4]);
            itemMap.put("start_ip_num", item[5]);
            itemMap.put("end_ip_num", item[6]);
            itemMap.put("modify_flag", item[7]);
            result.add(itemMap);
        }

        return result;
    }

    @Override
    public Boolean creacteContinent(Map<String, Object> continentMap) {
        Boolean result = null;

        String continentName = (String) continentMap.get("name");
        String continentCode = (String) continentMap.get("code");

        String continentNameEncode = Util.utf8ToLatin1(continentName);
        String continentCodeEncode = Util.utf8ToLatin1(continentCode);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) continentMap.get("geo");

        Integer continentCnt = infoContinentRepository.cntByContinentCode(continentCodeEncode);

        if (continentCnt > 0) {
            result = false;
        } else {
            InfoContinent continent = new InfoContinent();
            continent.setContinentName(continentNameEncode);
            continent.setContinentCode(continentCodeEncode);
            continent.setModifyFlag(true);
            infoContinentRepository.save(continent);

            result = true;

            if (geoMapping.size() > 0) {
                for (int i = 0; i < geoMapping.size(); i++) {
                    MappingContinentCountryGeo geo = new MappingContinentCountryGeo();
                    geo.setCustomContinentId(infoContinentRepository.save(continent).getContinentId());
                    geo.setContinentId((Integer) geoMapping.get(i).get("continentId"));
                    mappingContinentCountryGeoRepository.save(geo);
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> findInfoContinent(Integer continentId) {
        InfoContinent continent = infoContinentRepository.findContinentByContinentId(continentId);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("continentId", continent.getContinentId());
        result.put("continentCode", Util.latin1ToUtf8(continent.getContinentCode()));
        result.put("name", Util.latin1ToUtf8(continent.getContinentName()));
        result.put("modifyFlag", continent.getModifyFlag());

        List<Object[]> continentAllList = infoContinentRepository.findAllByModifyFlagFalse();
        List<Object> geoList = new ArrayList<Object>();
        for(Object[] item : continentAllList){

            Map<String, Object> continentItem = new HashMap<String, Object>();
            continentItem.put("continentId", item[0]);
            continentItem.put("continentCode", item[1]);
            continentItem.put("name", Util.latin1ToUtf8((String)item[2]));
            continentItem.put("modifyFlag", item[3]);

            geoList.add(continentItem);
        }

        result.put("geo", geoList);
        result.put("checked", mappingContinentCountryGeoRepository.findByCustomContinentId(continentId));

        return result;
    }

    @Override
    public void deleteContinent(Set<Integer> idSet) {
        mappingContinentCountryGeoRepository.deleteByCustomContinentIdSet(idSet);
        infoContinentRepository.deleteContinent(idSet);
        //tbl_info_country delete 들어가야함
    }

    @Override
    public Boolean updateInfoContinent(Integer continentId, Map<String, Object> continentMap) {
        Boolean result = null;

        String continentName = (String) continentMap.get("name");
        String continentCode = (String) continentMap.get("code");

        String continentNameEncode = Util.utf8ToLatin1(continentName);
        String continentCodeEncode = Util.utf8ToLatin1(continentCode);

        List<Map<String, Object>> geoMapping = (List<Map<String, Object>>) continentMap.get("geo");

        Integer continentCnt = infoContinentRepository.cntByContinentCodeAndContinentId(continentCodeEncode, continentId);

        if (continentCnt > 0) {
            result = false;
        } else {
            InfoContinent continent = new InfoContinent();
            continent.setContinentId(continentId);
            continent.setContinentName(continentNameEncode);
            continent.setContinentCode(continentCodeEncode);
            continent.setModifyFlag(true);
            infoContinentRepository.save(continent);

            result = true;

            if (geoMapping.size() > 0) {
                mappingContinentCountryGeoRepository.deleteByCustomContinentId(continentId);
                for (int i = 0; i < geoMapping.size(); i++) {
                    MappingContinentCountryGeo geo = new MappingContinentCountryGeo();
                    geo.setCustomContinentId(continentId);
                    geo.setContinentId((Integer) geoMapping.get(i).get("continentId"));
                    mappingContinentCountryGeoRepository.save(geo);
                }
            }
        }

        return result;
    }



    @Override
    public List<Object> findAllForContinentInCountry(Integer continentId) {
        List<Object> result = new ArrayList<Object>();

        List<MappingContinentCountryGeo> mappingItem = mappingContinentCountryGeoRepository.findByCustomContinentId(continentId);

        Set<Integer> continentIdSet = mappingContinentCountryGeoRepository.findByCustomContinentIdSet(continentId);

        for(Integer idItem : continentIdSet){
            InfoContinent continentItem = infoContinentRepository.findContinentByContinentId(idItem);

            String continentCode = continentItem.getContinentCode();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", Util.latin1ToUtf8(continentItem.getContinentName()));
            map.put("continentCode", continentItem.getContinentCode());
            map.put("continentId", continentItem.getContinentId());
            map.put("modifyFlag", continentItem.getModifyFlag());

            List<Object> countryList = new ArrayList<Object>();
            List<Object[]> country = infoCountryRepository.findByContinentCode(continentCode);

            for (Object[] countryItem : country) {
                Map<String, Object> val = new HashMap<String, Object>();
                val.put("countryCode", countryItem[0]);
                val.put("name", Util.latin1ToUtf8((String)countryItem[1]));
                val.put("countryId", countryItem[2]);
                val.put("continentId", continentItem.getContinentId());
                val.put("modifyFlag", countryItem[3]);
                countryList.add(val);
            }
            map.put("country", countryList);
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Object> findCountryByModifyFlagFalse() {
        List<Object[]> continentAllList = infoContinentRepository.findAllByModifyFlagFalse();
        List<Object> result = new ArrayList<Object>();

        for(Object[] item : continentAllList){

            Map<String, Object> continentItem = new HashMap<String, Object>();
            continentItem.put("continentId", item[0]);
            continentItem.put("continentCode", item[1]);
            continentItem.put("name", Util.latin1ToUtf8((String)item[2]));
            continentItem.put("modifyFlag", item[3]);

            result.add(continentItem);
        }
        return result;
    }

    @Override
    public Boolean createInfoCountry(Map<String, Object> countryMap) {
        Boolean result = null;
        String countryCode = (String) countryMap.get("code");
        String countryName = (String) countryMap.get("name");

        InfoContinent continentItem = infoContinentRepository.findContinentByContinentId((Integer) countryMap.get("continentId"));
        String continentCode = continentItem.getContinentCode();
        String continentName = continentItem.getContinentName();

        String countryCodeEncode = Util.utf8ToLatin1(countryCode);
        String countryNameEncode = Util.utf8ToLatin1(countryName);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) countryMap.get("geo");

        List<InfoCountry> dupCheck = infoCountryRepository.checkCountryCode(countryCodeEncode);

        if (dupCheck.size() > 0) {
            result = false;
        } else {
            InfoCountry country = new InfoCountry();
            country.setCountryCode(countryCodeEncode);
            country.setCountryName(countryNameEncode);
            country.setContinentCode(continentCode);
            country.setContinentName(continentName);
            country.setModifyFlag(true);
            infoCountryRepository.save(country);

            result = true;

            if (geoMapping.size() > 0) {
                for (int i = 0; i < geoMapping.size(); i++) {
                    MappingContinentCountryGeo geo = new MappingContinentCountryGeo();
                    geo.setCustomContinentId((Integer) geoMapping.get(i).get("customContinentId"));
                    geo.setCustomCountryId(infoCountryRepository.save(country).getCountryId());
                    geo.setContinentId((Integer) geoMapping.get(i).get("continentId"));
                    geo.setCountryId((Integer) geoMapping.get(i).get("countryId"));
                    mappingContinentCountryGeoRepository.save(geo);
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteCountry(Set<Integer> idSet) {
        mappingContinentCountryGeoRepository.deleteByCustomCountryIdSet(idSet);
        infoCountryRepository.deleteCountry(idSet);
    }

    @Override
    public Map<String, Object> findInfoCountry(Integer continentId, Integer countryId) {
        InfoCountry country = infoCountryRepository.findByCountryId(countryId);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("countryId", country.getCountryId());
        result.put("countryCode", Util.latin1ToUtf8(country.getContinentCode()));
        result.put("name", Util.latin1ToUtf8(country.getCountryName()));
        result.put("modifyFlag", country.getModifyFlag());

        Set<Integer> mappingIdSet = mappingContinentCountryGeoRepository.findByCountryidSet(countryId);

        List<Object[]> countAllList = infoContinentRepository.findByContinentByIdSet(mappingIdSet);
        List<Object> geoList = new ArrayList<Object>();
        for(Object[] item : countAllList){

            Map<String, Object> continentItem = new HashMap<String, Object>();
            continentItem.put("continentId", item[0]);
            continentItem.put("continentCode", item[1]);
            continentItem.put("name", Util.latin1ToUtf8((String)item[2]));
            continentItem.put("modifyFlag", item[3]);

            List<Object> countryList = new ArrayList<Object>();

            List<Object[]> findCountry = infoCountryRepository.findByContinentCode((String) item[1]);
            for (Object[] countryItem : findCountry) {
                Map<String, Object> val = new HashMap<String, Object>();
                val.put("countryCode", countryItem[0]);
                val.put("name", Util.latin1ToUtf8((String)countryItem[1]));
                val.put("countryId", countryItem[2]);
                val.put("continentId", (Integer)item[0]);
                val.put("modifyFlag", countryItem[3]);
                countryList.add(val);
            }
            continentItem.put("country", countryList);

            geoList.add(continentItem);
        }

        result.put("geo", geoList);

        result.put("checked", mappingContinentCountryGeoRepository.findByCustomContinentIdAndCountryId(continentId, countryId));

        return result;
    }
}
