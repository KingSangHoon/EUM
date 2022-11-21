package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.entity.setting.InfoUserAgent;
import com.sysone.eumaiwacs.repository.setting.InfoUserAgentRepository;
import com.sysone.eumaiwacs.service.setting.CodeUserAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeUserAgentServiceImpl implements CodeUserAgentService {

    @Autowired InfoUserAgentRepository infoUserAgentRepository;

    @Override
    public List<Object> findUserAgentTypeByCategory(String category) {
        List<Object> result = new ArrayList<Object>();

        if (category.equals("software")) {
            List<String> valList = infoUserAgentRepository.getSoftwareNameGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getInfoUserAgentBySoftwareName(s);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("software", ua[0]);
                    map.put("softwareVersion", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (category.equals("operatingSystems")) {
            List<String> valList = infoUserAgentRepository.getOperatingSystemNameGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getInfoUserAgentByOperatingSystemName(s);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("operatingSystem", ua[0]);
                    map.put("operatingSystemVersion", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (category.equals("operatingPlatforms")) {
            List<Object[]> valList = infoUserAgentRepository.getOperatingPlatformGroup();

            for (Object[] s : valList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("operatingPlatform", s[0]);
                resultMap.put("operatingPlatformCode", s[1]);
                resultMap.put("operatingPlatformCodeName", s[2]);
                resultMap.put("operatingPlatformVendorName", s[3]);

                result.add(resultMap);
            }
        } else if (category.equals("softwareTypes")) {
            List<String> valList = infoUserAgentRepository.getSoftwareTypeGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getInfoUserAgentBySoftwareType(s);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("softwareSubType", ua[0]);
                    map.put("softwareSubTypeSpecific", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (category.equals("hardwareTypes")) {
            List<String> valList = infoUserAgentRepository.getHardwareTypeGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getInfoUserAgentByHardwareType(s);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("hardwareSubType", ua[0]);
                    map.put("hardwareSubSubType", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (category.equals("layoutEngines")) {
            List<String> valList = infoUserAgentRepository.getLayoutEngineNameGroup();

            for (String s : valList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                result.add(resultMap);
            }
        }
        return result;
    }

    @Override
    public List<InfoUserAgent> findUserAgent(String category, String key) {
        List<InfoUserAgent> result = new ArrayList<InfoUserAgent>();

        if (category.equals("software")) {
            result = infoUserAgentRepository.findInfoUserAgentBySoftware(key);
        } else if (category.equals("operatingSystems")) {
            result = infoUserAgentRepository.findInfoUserAgentByOperatingSystem(key);
        } else if (category.equals("operatingPlatforms")) {
            result = infoUserAgentRepository.findInfoUserAgentByOperatingPlatform(key);
        } else if (category.equals("softwareTypes")) {
            result = infoUserAgentRepository.findInfoUserAgentBySoftwareType(key);
        } else if (category.equals("hardwareTypes")) {
            result = infoUserAgentRepository.findInfoUserAgentByHardwareType(key);
        } else if (category.equals("layoutEngines")) {
            result = infoUserAgentRepository.findInfoUserAgentByLayoutEngineName(key);
        }

        return result;
    }

    @Override
    public List<InfoUserAgent> findAllAgent() {
        return infoUserAgentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public List<Object> findUserAgentByUser(Map<String, Object> userAgentMap) {
        String schUserAgent = (String) userAgentMap.get("userAgent");
        String schUserAgentType = (String)userAgentMap.get("userAgentType");

        List<Object> result = new ArrayList<Object>();

        if (schUserAgentType.equals("software")) {
            List<String> valList = infoUserAgentRepository.getSoftwareNameGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getCustomUserAgentBySoftwareName(s, schUserAgent);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("software", ua[0]);
                    map.put("softwareVersion", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("operatingSystems")) {
            List<String> valList = infoUserAgentRepository.getOperatingSystemNameGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getCustomUserAgentByOperatingSystemName(s, schUserAgent);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("operatingSystem", ua[0]);
                    map.put("operatingSystemVersion", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("operatingPlatforms")) {
            List<Object[]> valList = infoUserAgentRepository.getCustomOperatingPlatformGroup(schUserAgent);

            for (Object[] s : valList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("operatingPlatform", s[0]);
                resultMap.put("operatingPlatformCode", s[1]);
                resultMap.put("operatingPlatformCodeName", s[2]);
                resultMap.put("operatingPlatformVendorName", s[3]);

                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("softwareTypes")) {
            List<String> valList = infoUserAgentRepository.getSoftwareTypeGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getInfoUserAgentBySoftwareType(s, schUserAgent);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("softwareSubType", ua[0]);
                    map.put("softwareSubTypeSpecific", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("hardwareTypes")) {
            List<String> valList = infoUserAgentRepository.getHardwareTypeGroup();

            for (String s : valList) {
                List<Map<String, Object>> innerList = new ArrayList<Map<String, Object>>();
                List<Object[]> innerResultList = infoUserAgentRepository.getCustomUserAgentByHardwareType(s, schUserAgent);

                for (Object[] ua : innerResultList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("hardwareSubType", ua[0]);
                    map.put("hardwareSubSubType", ua[1]);
                    innerList.add(map);
                }

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                resultMap.put("values", innerList);
                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("layoutEngines")) {
            List<String> valList = infoUserAgentRepository.getCustomLayoutEngineNameGroup(schUserAgent);

            for (String s : valList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("name", s);
                result.add(resultMap);
            }
        } else if (schUserAgentType.equals("전체")) {
            List<Object[]> valList = infoUserAgentRepository.getCustomUserAgentByKeyword(schUserAgent);

            for (Object[] s : valList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("id", s[0]);
                resultMap.put("userAgent", s[1]);
                result.add(resultMap);
            }
        }
        return result;
    }

    @Override
    public long findCntAllAgent(Map<String, Object> userAgentMap) {
        String schUserAgent = (String) userAgentMap.get("userAgent");
        return infoUserAgentRepository.findCntAllAgent(schUserAgent);
    }

    @Override
    public List<Object> findAgent(Map<String, Object> userAgentMap, Integer nextPage, Integer offset) {
        String schUserAgent = (String) userAgentMap.get("userAgent");

        List<Object[]> findList = infoUserAgentRepository.findAgent(schUserAgent, nextPage, offset);
        List<Object> result = new ArrayList<>();

        for(Object[] item : findList){
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("id", item[0]);
            itemMap.put("userAgent", item[1]);
            result.add(itemMap);
        }

        return result;
    }
}
