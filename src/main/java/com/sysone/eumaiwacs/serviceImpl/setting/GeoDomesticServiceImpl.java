package com.sysone.eumaiwacs.serviceImpl.setting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.QueryResults;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.GeoDomesticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GeoDomesticServiceImpl implements GeoDomesticService {

    @Autowired private InfoDomesticPrimaryRepository infoDomesticPrimaryRepository;
    @Autowired private InfoDomesticSub1Repository infoDomesticSub1Repository;
    @Autowired private InfoDomesticSub2Repository infoDomesticSub2Repository;
    @Autowired private MappingDomesticRepository mappingDomesticRepository;
    @Autowired private MappingDomesticGeoRepository mappingDomesticGeoRepository;

    @Autowired private MappingDomesticRepositoryCustom mappingDomesticRepositoryCustom;

    @Override
    public List<Object> findAllDomestic() {
        List<Object> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<InfoDomesticPrimary> infoDomesticPrimaryList = infoDomesticPrimaryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        for(InfoDomesticPrimary infoDomesticPrimary : infoDomesticPrimaryList) {
            infoDomesticPrimary.setName(Util.latin1ToUtf8(infoDomesticPrimary.getName()));
            Map<String, Object> primaryMap = objectMapper.convertValue(infoDomesticPrimary, Map.class);

            List<Object> sub1List = new ArrayList<>();
            List<InfoDomesticSub1> infoDomesticSub1List = infoDomesticSub1Repository.findInfoDomesticSub1ByPrimaryId(infoDomesticPrimary.getId());
            for (InfoDomesticSub1 infoDomesticSub1 : infoDomesticSub1List) {
                infoDomesticSub1.setNameVar(Util.latin1ToUtf8(infoDomesticSub1.getNameVar()));
                infoDomesticSub1.setName(Util.latin1ToUtf8(infoDomesticSub1.getName()));
                Map<String, Object> sub1Map = objectMapper.convertValue(infoDomesticSub1, Map.class);

                List<Map<String, Object>> sub2List = new ArrayList<>();
                List<InfoDomesticSub2> infoDomesticSub2List = infoDomesticSub2Repository.findInfoDomesticSub2ByPrimaryIdAndSub1Id(infoDomesticPrimary.getId(), infoDomesticSub1.getId());
                for (InfoDomesticSub2 infoDomesticSub2 : infoDomesticSub2List) {
                    infoDomesticSub2.setName(Util.latin1ToUtf8(infoDomesticSub2.getName()));
                    Map<String, Object> sub2Map = objectMapper.convertValue(infoDomesticSub2, Map.class);
                    sub2List.add(sub2Map);
                }
                sub1Map.put("sub2", sub2List);
                sub1List.add(sub1Map);
            }
            primaryMap.put("sub1", sub1List);
            result.add(primaryMap);
        }
        return result;
    }

    @Override
    public Object findDomestic(Map<String, Object> param) {

        Object result = new Object();

        String category = (String) param.get("category");
        Integer primaryId = (Integer) param.get("primaryId");
        Integer sub1Id = (Integer) param.get("sub1Id");

        if(category.equals("primary")) {

            if(primaryId == null) {
                List<InfoDomesticPrimary> domesticResult = infoDomesticPrimaryRepository.findInfoDomesticPrimary();

                if(domesticResult != null && domesticResult.size() > 0) {
                    for(InfoDomesticPrimary info : domesticResult) {
                        info.setName(Util.latin1ToUtf8(info.getName()));
                    }
                }
                result = domesticResult;
            } else {
                List<InfoDomesticSub1> domesticResult = infoDomesticSub1Repository.findInfoDomesticSub1ByPrimaryId(primaryId);
                if(domesticResult != null && domesticResult.size() > 0) {
                    for(InfoDomesticSub1 info : domesticResult) {
                        info.setName(Util.latin1ToUtf8(info.getName()));
                        info.setNameVar(Util.latin1ToUtf8(info.getNameVar()));
                    }
                }
                result = domesticResult;
            }
        } else if(category.equals("sub1")) {
            List<InfoDomesticSub2> domesticResult = infoDomesticSub2Repository.findInfoDomesticSub2ByPrimaryIdAndSub1Id(primaryId, sub1Id);
            if(domesticResult != null && domesticResult.size() > 0) {
                for(InfoDomesticSub2 info : domesticResult) {
                    info.setName(Util.latin1ToUtf8(info.getName()));
                }
            }
            result = domesticResult;
        }
        return result;
    }

    @Override
    public Object findIp(Map<String, Object> param) {
        QueryResults<MappingDomestic> result = mappingDomesticRepositoryCustom.find(param);
        if(result != null && result.getResults().size() > 0) {
            for(MappingDomestic md : result.getResults()) {
                if(md.getOrg() != null) md.setOrg(Util.latin1ToUtf8(md.getOrg()));
                if(md.getPrimaryName() != null) md.setPrimaryName(Util.latin1ToUtf8(md.getPrimaryName()));
                if(md.getR1Name() != null) md.setR1Name(Util.latin1ToUtf8(md.getR1Name()));
                if(md.getR2Name() != null) md.setR2Name(Util.latin1ToUtf8(md.getR2Name()));
            }
        }
        return result;
    }

    @Override
    public Object search(Map<String, Object> param) {

        Map<String, Object> map = new HashMap<>();

        String ip = (String) param.get("ip");
        Integer offset = (Integer) param.get("offset");
        Integer limit = (Integer) param.get("limit");

        List<Map<String, Object>> primaryList = new ArrayList<>();

        QueryResults<MappingDomestic> result = mappingDomesticRepositoryCustom.search(Util.ipToLong(ip), limit, offset);
        if(result != null && result.getResults().size() > 0) {
            for(MappingDomestic md : result.getResults()) {

                String org = md.getOrg() == null ? ""  : Util.latin1ToUtf8(md.getOrg());
                String primaryName = md.getPrimaryName() == null ? "" : Util.latin1ToUtf8(md.getPrimaryName());
                String sub1Name = md.getR1Name() == null ? "" : Util.latin1ToUtf8(md.getR1Name());
                String sub2Name = md.getR2Name() == null ? "" : Util.latin1ToUtf8(md.getR2Name());

                md.setOrg(org);
                md.setPrimaryName(primaryName);
                md.setR1Name(sub1Name);
                md.setR2Name(sub2Name);

                List<Map<String, Object>> sub2List = new ArrayList<>();
                Map<String, Object> sub2Map = new HashMap<>();
                sub2Map.put("id", md.getSub2Id());
                sub2Map.put("name", sub2Name);
                sub2List.add(sub2Map);

                List<Map<String, Object>> sub1List = new ArrayList<>();
                Map<String, Object> sub1Map = new HashMap<>();
                sub1Map.put("id", md.getSub1Id());
                sub1Map.put("name", sub1Name);
                sub1Map.put("sub2", sub2List);
                sub1List.add(sub1Map);


                Map<String, Object> primaryMap = new HashMap<>();
                primaryMap.put("id", md.getPrimaryId());
                primaryMap.put("name", primaryName);
                primaryMap.put("sub1", sub1List);
                primaryList.add(primaryMap);
            }
        }

        map.put("info", primaryList);
        map.put("result", result);

        return map;
    }

    @Override
    @Transactional
    public void deleteInfoDomesticPrimary(Set<Integer> idSet) {
        mappingDomesticGeoRepository.deleteByCustomPrimaryIdSet(idSet);
        mappingDomesticRepository.deleteDomesticByPrimaryId(idSet);
        infoDomesticSub1Repository.deleteDomesticSub1ByPrimaryId(idSet);
        infoDomesticSub2Repository.deleteDomesticSub2ByPrimaryId(idSet);
        infoDomesticPrimaryRepository.deleteDomesticPrimaryById(idSet);
    }

    @Override
    @Transactional
    public void deleteInfoDomesticSub1(Set<Integer> idSet) {
        mappingDomesticGeoRepository.deleteByCustomSub1IdSet(idSet);
        mappingDomesticRepository.deleteDomesticBySub1d(idSet);
        infoDomesticSub1Repository.deleteDomesticSub1Byid(idSet);
        infoDomesticSub2Repository.deleteDomesticSub2BySub1Id(idSet);
    }

    @Override
    @Transactional
    public void deleteInfoDomesticSub2(Set<Integer> idSet) {
        mappingDomesticGeoRepository.deleteByCustomSub2IdSet(idSet);
        mappingDomesticRepository.deleteDomesticBySub2Id(idSet);
        infoDomesticSub2Repository.deleteDomesticSub2ById(idSet);
    }

    @Override
    @Transactional
    public void createDomesticPrimary(Map<String, Object> map) {
        String primaryName = Util.utf8ToLatin1((String) map.get("name"));
        List<Map<String, Object>> geoMapping = (List<Map<String, Object>>) map.get("geo");

        InfoDomesticPrimary primary = new InfoDomesticPrimary();
        primary.setName(primaryName);
        primary.setModifyFlag(true);

        infoDomesticPrimaryRepository.save(primary);

        for(Map<String, Object> item : geoMapping){
            MappingDomesticGeo mappingDomesticGeo = new MappingDomesticGeo();
            mappingDomesticGeo.setCustomPrimaryId(infoDomesticPrimaryRepository.save(primary).getId());
            mappingDomesticGeo.setPrimaryId((Integer) item.get("primaryId"));
            mappingDomesticGeoRepository.save(mappingDomesticGeo);
        }

        if ((boolean) map.get("unKnownFlag")) {
            InfoDomesticSub1 sub1Entity = new InfoDomesticSub1();
            sub1Entity.setNameVar("Unknown");
            sub1Entity.setName("Unknown");
            sub1Entity.setModifyFlag(true);
            sub1Entity.setPrimaryId(infoDomesticPrimaryRepository.save(primary).getId());

            infoDomesticSub1Repository.save(sub1Entity);
        }
    }

    @Override
    @Transactional
    public void createDomesticSub1(Map<String, Object> domesticMap) {
        String name = (String) domesticMap.get("name");
        Integer primaryId = (Integer) domesticMap.get("primaryId");
        String encodeName = Util.utf8ToLatin1(name);

        ArrayList<Map<String, Object>> geoMapping = (ArrayList<Map<String, Object>>) domesticMap.get("geo");

        InfoDomesticSub1 sub1 = new InfoDomesticSub1();
        sub1.setPrimaryId(primaryId);
        sub1.setName(encodeName);
        sub1.setNameVar(encodeName);
        sub1.setModifyFlag(true);
        infoDomesticSub1Repository.save(sub1);

        if (geoMapping.size() > 0) {

            for (int i = 0; i < geoMapping.size(); i++) {
                MappingDomesticGeo geo = new MappingDomesticGeo();
                geo.setCustomPrimaryId((Integer) geoMapping.get(i).get("customPrimaryId"));
                geo.setCustomSub1Id(infoDomesticSub1Repository.save(sub1).getId());
                geo.setPrimaryId((Integer) geoMapping.get(i).get("primaryId"));
                geo.setSub1Id((Integer) geoMapping.get(i).get("sub1Id"));

                mappingDomesticGeoRepository.save(geo);
            }
        }

        if ((boolean) domesticMap.get("unKnownFlag")) {
            InfoDomesticSub2 sub2Entity = new InfoDomesticSub2();
            sub2Entity.setName("Unknown");
            sub2Entity.setModifyFlag(true);
            sub2Entity.setSub1Id(infoDomesticSub1Repository.save(sub1).getId());
            sub2Entity.setPrimaryId(primaryId);

            infoDomesticSub2Repository.save(sub2Entity);
        }
    }

    @Override
    @Transactional
    public void createDomesticSub2(Map<String, Object> domesticMap) {
        String name = (String) domesticMap.get("name");
        Integer primaryId = (Integer) domesticMap.get("primaryId");
        Integer sub1Id = (Integer) domesticMap.get("sub1Id");
        String encodeName = Util.utf8ToLatin1(name);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) domesticMap.get("geo");

        InfoDomesticSub2 sub2 = new InfoDomesticSub2();
        sub2.setPrimaryId(primaryId);
        sub2.setSub1Id(sub1Id);
        sub2.setName(encodeName);
        sub2.setModifyFlag(true);
        infoDomesticSub2Repository.save(sub2);

        if (geoMapping.size() > 0) {
            for (int i = 0; i < geoMapping.size(); i++) {
                MappingDomesticGeo geo = new MappingDomesticGeo();
                geo.setCustomPrimaryId((Integer) geoMapping.get(i).get("customPrimaryId"));
                geo.setCustomSub1Id((Integer) geoMapping.get(i).get("customSub1Id"));
                geo.setCustomSub2Id(infoDomesticSub2Repository.save(sub2).getId());
                geo.setPrimaryId((Integer) geoMapping.get(i).get("primaryId"));
                geo.setSub1Id((Integer) geoMapping.get(i).get("sub1Id"));
                geo.setSub2Id((Integer) geoMapping.get(i).get("sub2Id"));
                mappingDomesticGeoRepository.save(geo);
            }
        }
    }

    @Override
    @Transactional
    public void updateDomesticPrimary(Map<String, Object> domesticMap, Integer primaryId) {
        String name = (String) domesticMap.get("name");
        String encodeName = Util.utf8ToLatin1(name);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) domesticMap.get("geo");

        InfoDomesticPrimary primary = new InfoDomesticPrimary();
        primary.setId(primaryId);
        primary.setName(encodeName);
        primary.setModifyFlag(true);
        infoDomesticPrimaryRepository.save(primary);

        if (geoMapping.size() > 0) {
            mappingDomesticGeoRepository.deleteByCustomPrimaryId(primaryId);
            for (int i = 0; i < geoMapping.size(); i++) {
                MappingDomesticGeo geo = new MappingDomesticGeo();
                geo.setCustomPrimaryId(primaryId);
                geo.setPrimaryId((Integer) geoMapping.get(i).get("primaryId"));
                mappingDomesticGeoRepository.save(geo);
            }
        }
    }

    @Override
    @Transactional
    public void updateDomesticSub1(Map<String, Object> sub1Map, Integer primaryId, Integer sub1Id) {

        String name = (String) sub1Map.get("name");
        String encodeName = Util.utf8ToLatin1(name);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) sub1Map.get("geo");

        InfoDomesticSub1 sub1 = new InfoDomesticSub1();
        sub1.setId(sub1Id);
        sub1.setPrimaryId(primaryId);
        sub1.setName(encodeName);
        sub1.setNameVar(encodeName);
        sub1.setModifyFlag(true);
        infoDomesticSub1Repository.save(sub1);

        if (geoMapping.size() > 0) {
            mappingDomesticGeoRepository.deleteByCustomSub1Id(sub1Id);
            for (int i = 0; i < geoMapping.size(); i++) {
                MappingDomesticGeo geo = new MappingDomesticGeo();
                geo.setCustomPrimaryId((Integer) geoMapping.get(i).get("customPrimaryId"));
                geo.setCustomSub1Id(sub1Id);
                geo.setPrimaryId((Integer) geoMapping.get(i).get("primaryId"));
                geo.setSub1Id((Integer) geoMapping.get(i).get("sub1Id"));
                mappingDomesticGeoRepository.save(geo);
            }
        }
    }

    @Override
    @Transactional
    public void updateDomesticSub2(Map<String, Object> sub2Map, Integer primaryId, Integer sub1Id, Integer sub2Id) {
        String name = (String) sub2Map.get("name");
        String encodeName = Util.utf8ToLatin1(name);

        List<Map<String, Object>> geoMapping = new ArrayList<Map<String, Object>>();
        geoMapping = (List<Map<String, Object>>) sub2Map.get("geo");

        InfoDomesticSub2 sub2 = new InfoDomesticSub2();
        sub2.setId(sub2Id);
        sub2.setPrimaryId(primaryId);
        sub2.setSub1Id(sub1Id);
        sub2.setName(encodeName);
        sub2.setModifyFlag(true);
        infoDomesticSub2Repository.save(sub2);

        if (geoMapping.size() > 0) {
            mappingDomesticGeoRepository.deleteByCustomSub2Id(sub2Id);
            for (int i = 0; i < geoMapping.size(); i++) {
                MappingDomesticGeo geo = new MappingDomesticGeo();
                geo.setCustomPrimaryId((Integer) geoMapping.get(i).get("customPrimaryId"));
                geo.setCustomSub1Id((Integer) geoMapping.get(i).get("customSub1Id"));
                geo.setCustomSub2Id(infoDomesticSub2Repository.save(sub2).getId());
                geo.setPrimaryId((Integer) geoMapping.get(i).get("primaryId"));
                geo.setSub1Id((Integer) geoMapping.get(i).get("sub1Id"));
                geo.setSub2Id((Integer) geoMapping.get(i).get("sub2Id"));
                mappingDomesticGeoRepository.save(geo);
            }
        }
    }

    @Override
    @Transactional
    public Boolean createMappingDomesticPublic(Map<String, Object> domesticMap) {

        Boolean result;

        Integer primaryId = (Integer) domesticMap.get("primaryId");
        Integer sub1Id = null;
        if ((Integer) domesticMap.get("sub1Id") != 0) sub1Id = (Integer) domesticMap.get("sub1Id");
        Integer sub2Id = null;
        if ((Integer) domesticMap.get("sub2Id") != 0) sub2Id = (Integer) domesticMap.get("sub2Id");

        String startIp = (String) domesticMap.get("startIp");
        String endIp = (String) domesticMap.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        String primaryName = infoDomesticPrimaryRepository.getPrimaryName(primaryId);
        String sub1Name = null;
        if (sub1Id != null) sub1Name = infoDomesticSub1Repository.getsub1NameVar(sub1Id);
        String sub2Name = null;
        if (sub2Id != null) sub2Name = infoDomesticSub2Repository.getsub2Name(sub2Id);

        Long doubleCheckNum = mappingDomesticRepository.doubleCheck(startDigits, endDigits);

        if (doubleCheckNum > 0) {
            result = false;
        } else {
            MappingDomestic DomesticItem = new MappingDomestic();
            DomesticItem.setPrimaryId(primaryId);
            DomesticItem.setSub1Id(sub1Id);
            DomesticItem.setSub2Id(sub2Id);
            DomesticItem.setPrimaryName(primaryName);
            DomesticItem.setR1Name(sub1Name);
            DomesticItem.setR2Name(sub2Name);
            DomesticItem.setStartIp(startIp);
            DomesticItem.setEndIp(endIp);
            DomesticItem.setStartIpNum(startDigits);
            DomesticItem.setEndIpNum(endDigits);
            DomesticItem.setModifyFlag(true);
            DomesticItem.setType("public");
            mappingDomesticRepository.save(DomesticItem);

            result = true;
        }

        return result;
    }

    @Override
    @Transactional
    public Boolean createMappingDomesticPrivate(Map<String, Object> domesticMap) {

        Boolean result;

        Integer primaryId = (Integer) domesticMap.get("primaryId");
        Integer sub1Id = null;
        if ((Integer) domesticMap.get("sub1Id") != 0) sub1Id = (Integer) domesticMap.get("sub1Id");
        Integer sub2Id = null;
        if ((Integer) domesticMap.get("sub2Id") != 0) sub2Id = (Integer) domesticMap.get("sub2Id");

        String startIp = (String) domesticMap.get("startIp");
        String endIp = (String) domesticMap.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        String primaryName = infoDomesticPrimaryRepository.getPrimaryName(primaryId);
        String sub1Name = null;
        if (sub1Id != null) sub1Name = infoDomesticSub1Repository.getsub1NameVar(sub1Id);
        String sub2Name = null;
        if (sub2Id != null) sub2Name = infoDomesticSub2Repository.getsub2Name(sub2Id);

        MappingDomestic DomesticItem = new MappingDomestic();
        DomesticItem.setPrimaryId(primaryId);
        DomesticItem.setSub1Id(sub1Id);
        DomesticItem.setSub2Id(sub2Id);
        DomesticItem.setPrimaryName(primaryName);
        DomesticItem.setR1Name(sub1Name);
        DomesticItem.setR2Name(sub2Name);
        DomesticItem.setStartIp(startIp);
        DomesticItem.setEndIp(endIp);
        DomesticItem.setStartIpNum(startDigits);
        DomesticItem.setEndIpNum(endDigits);
        DomesticItem.setModifyFlag(true);
        DomesticItem.setType("private");
        mappingDomesticRepository.save(DomesticItem);

        result = true;

        return result;
    }

    @Override
    @Transactional
    public Boolean updateMappingDomesticPrivate(Integer id, Map<String, Object> ipMap) {
        Boolean result = true;

        String startIp = (String) ipMap.get("startIp");
        String endIp = (String) ipMap.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        mappingDomesticRepository.updateItem(id, startIp, endIp, startDigits, endDigits);

        return result;
    }

    @Override
    public void deleteMappingDomestic(Set<Integer> idSet) {
        mappingDomesticRepository.deleteMappingDomestic(idSet);
    }

    @Override
    public List<Object> findDomesticAll(Integer primaryId,Integer sub1Id,Integer sub2Id) {
        List<Object> result = new ArrayList<Object>();

        Set<Integer> primaryIdList = new HashSet<Integer>();
        Set<Integer> sub1IdList = new HashSet<Integer>();
        Set<Integer> sub2IdList = new HashSet<Integer>();

        if (sub1Id == 0 && sub2Id == 0) {
            primaryIdList = mappingDomesticGeoRepository.primaryIdSetByCustomPrimaryId(primaryId);
        } else if (sub2Id == 0) {
            primaryIdList = mappingDomesticGeoRepository.primaryIdSetByPrimaryIdAndSub1(primaryId, sub1Id);
            sub1IdList = mappingDomesticGeoRepository.sub1IdSetByPrimaryIdAndSub1(primaryId, sub1Id);
        } else {
            primaryIdList = mappingDomesticGeoRepository.primaryIdSetByPrimaryIdAndSub1AndSub2(primaryId, sub1Id, sub2Id);
            sub1IdList = mappingDomesticGeoRepository.sub1IdSetByPrimaryIdAndSub1AndSub2(primaryId, sub1Id, sub2Id);
            sub2IdList = mappingDomesticGeoRepository.sub2IdSetByPrimaryIdAndSub1AndSub2(primaryId, sub1Id, sub2Id);
        }

        List<InfoDomesticPrimary> first = infoDomesticPrimaryRepository.findAllById(primaryIdList);

        for (InfoDomesticPrimary f : first) {
            Map<String, Object> firstMap = new HashMap<String, Object>();
            firstMap.put("id", f.getId());
            firstMap.put("name", Util.latin1ToUtf8(f.getName()));
            firstMap.put("modifyFlag", f.getModifyFlag());

            List<Object> secondResult = new ArrayList<Object>();
            List<Object[]> secondList = infoDomesticSub1Repository.findSub1ByPrimaryId(sub1IdList);

            for (Object[] s : secondList) {
                Map<String, Object> secondMap = new HashMap<String, Object>();
                secondMap.put("id", s[0]);
                secondMap.put("primaryId", s[1]);
                secondMap.put("name", Util.latin1ToUtf8(s[2].toString()));
                secondMap.put("modifyFlag", s[3]);


                List<Object> thirdResult = new ArrayList<Object>();
                List<Object[]> thirdList = infoDomesticSub2Repository.findSub2ByPrimaryId(sub2IdList);

                for (Object[] t : thirdList) {
                    String name = (String) t[3];
                    HashMap<String, Object> thirdMap = new HashMap<String, Object>();
                    thirdMap.put("id", t[0]);
                    thirdMap.put("primaryId", t[1]);
                    thirdMap.put("sub1Id", t[2]);
                    thirdMap.put("name", Util.latin1ToUtf8(name));
                    thirdMap.put("modifyFlag", t[4]);
                    thirdResult.add(thirdMap);
                }

                secondMap.put("sub2", thirdResult);
                secondResult.add(secondMap);
            }
            firstMap.put("sub1", secondResult);
            result.add(firstMap);
        }
        return result;
    }

    @Override
    public Boolean createMultiMappingDomesticPublic(List<Map<String, Object>> ipMap) {
        Boolean result = null;

        for (int i = 0; i < ipMap.size(); i++) {

            Integer primaryId = (Integer) ipMap.get(i).get("primaryId");
            Integer sub1Id = null;
            if ((Integer) ipMap.get(i).get("sub1Id") != 0) sub1Id = (Integer) ipMap.get(i).get("sub1Id");
            Integer sub2Id = null;
            if ((Integer) ipMap.get(i).get("sub2Id") != 0) sub2Id = (Integer) ipMap.get(i).get("sub2Id");

            String startIp = (String) ipMap.get(i).get("startIp");
            String endIp = (String) ipMap.get(i).get("endIp");

            Long startDigits = Util.ipToLong(startIp);
            Long endDigits = Util.ipToLong(endIp);

            String primaryName = infoDomesticPrimaryRepository.getPrimaryName(primaryId);
            String sub1Name = null;
            if (sub1Id != null) sub1Name = infoDomesticSub1Repository.getsub1NameVar(sub1Id);
            String sub2Name = null;
            if (sub2Id != null) sub2Name = infoDomesticSub2Repository.getsub2Name(sub2Id);

            MappingDomestic ip = new MappingDomestic();
            ip.setPrimaryId(primaryId);
            ip.setSub1Id(sub1Id);
            ip.setSub2Id(sub2Id);
            ip.setPrimaryName(primaryName);
            ip.setR1Name(sub1Name);
            ip.setR2Name(sub2Name);
            ip.setStartIp(startIp);
            ip.setEndIp(endIp);
            ip.setStartIpNum(startDigits);
            ip.setEndIpNum(endDigits);
            ip.setModifyFlag(true);
            ip.setType("public");
            mappingDomesticRepository.save(ip);

            result = true;
        }
        return result;
    }

    @Override
    public Map<String, Object> findInfoDomesticPrimary(Integer primaryId) {
        Map<String, Object> result = new HashMap<String, Object>();

        InfoDomesticPrimary resultItems = infoDomesticPrimaryRepository.findDomesticPrimaryById(primaryId);

        result.put("id", resultItems.getId());
        result.put("name", Util.latin1ToUtf8(resultItems.getName()));
        result.put("modifyFlag", resultItems.getModifyFlag());
        result.put("geoKey", resultItems.getGeoKey());

        List<InfoDomesticPrimary> domesticPrimaryAll = infoDomesticPrimaryRepository.findByModifyFlag(false);
        for(InfoDomesticPrimary item : domesticPrimaryAll){
            item.setName(Util.latin1ToUtf8(item.getName()));
        }
        result.put("domestic", domesticPrimaryAll);

        List<Object[]> selectedItem = mappingDomesticGeoRepository.findSelectedByPrimaryId(primaryId);
        ArrayList<HashMap<String, Object>> resultSelectItem = new ArrayList<>();
        for(Object[] item : selectedItem){
            HashMap<String, Object> itemMap = new HashMap<>();
            itemMap.put("id", item[0]);
            itemMap.put("custom_primary_id", item[1]);
            itemMap.put("custom_sub1_id", item[2]);
            itemMap.put("custom_sub2_id", item[3]);
            itemMap.put("primary_id", item[4]);
            itemMap.put("sub1_id", item[5]);
            itemMap.put("sub2_id", item[6]);
            resultSelectItem.add(itemMap);
        }

        result.put("selected", resultSelectItem);

        return result;
    }

    @Override
    public Map<String, Object> findInfoDomesticSub1(Integer primaryId, Integer sub1Id) {
        Map<String, Object> result = new HashMap<String, Object>();

        InfoDomesticSub1 resultItems = infoDomesticSub1Repository.findByid(sub1Id);

        result.put("id", resultItems.getId());
        result.put("primaryId", resultItems.getPrimaryId());
        result.put("name", Util.latin1ToUtf8(resultItems.getName()));
        result.put("nameVar", Util.latin1ToUtf8(resultItems.getNameVar()));
        result.put("modifyFlag", resultItems.getModifyFlag());
        result.put("geoKey", resultItems.getGeoKey());

        Map<String, Object> geoDomestic = new HashMap<String, Object>();

        Set<Integer> findPrimaryIdByPrimaryIds = mappingDomesticGeoRepository.findPrimaryIdByPrimaryId(primaryId);
        ArrayList<Object> domesticResult = new ArrayList<Object>();
        for (Integer primaryItemId : findPrimaryIdByPrimaryIds) {
            Map<String, Object> primaryMap = new HashMap<String, Object>();
            InfoDomesticPrimary primaryOneItem = infoDomesticPrimaryRepository.findByid(primaryItemId);

            primaryMap.put("id", primaryOneItem.getId());
            primaryMap.put("name", Util.latin1ToUtf8(primaryOneItem.getName()));
            primaryMap.put("modifyFlag", primaryOneItem.getModifyFlag());

            List<Object> sub1Result = new ArrayList<Object>();
            List<Object[]> sub1List = infoDomesticSub1Repository.findObjectByPrimaryId(primaryItemId);

            for (Object[] sub1Item : sub1List) {
                Map<String, Object> sub1Map = new HashMap<String, Object>();
                sub1Map.put("id", sub1Item[0]);
                sub1Map.put("primaryId", sub1Item[1]);
                sub1Map.put("name", Util.latin1ToUtf8(sub1Item[2].toString()));
                sub1Map.put("goeKey", sub1Item[3]);
                sub1Map.put("modifyFlag", sub1Item[4]);
                sub1Map.put("nameVar", Util.latin1ToUtf8(sub1Item[5].toString()));

                sub1Result.add(sub1Map);
            }

            primaryMap.put("sub1", sub1Result);
            domesticResult.add(primaryMap);
        }

        geoDomestic.put("geoDomestic", domesticResult);
        result.put("domestic", geoDomestic);
        InfoDomesticSub1 selectedItem = infoDomesticSub1Repository.findByid(sub1Id);
        selectedItem.setName(selectedItem.getName());
        selectedItem.setNameVar(selectedItem.getNameVar());

        result.put("selected", selectedItem);

        return result;
    }

    @Override
    public Map<String, Object> findInfoDomesticSub2(Integer primaryId, Integer sub1Id, Integer sub2Id) {
        Map<String, Object> result = new HashMap<String, Object>();

        InfoDomesticSub2 resultItems = infoDomesticSub2Repository.findByid(sub2Id);

        result.put("id", resultItems.getId());
        result.put("primaryId", resultItems.getPrimaryId());
        result.put("name", Util.latin1ToUtf8(resultItems.getName()));
        result.put("modifyFlag", resultItems.getModifyFlag());

        Map<String, Object> geoDomestic = new HashMap<String, Object>();

        Set<Integer> findPrimaryIdByPrimaryIds = mappingDomesticGeoRepository.findPrimaryIdByPrimaryIdAndSub1Id(primaryId, sub1Id);
        ArrayList<Object> domesticResult = new ArrayList<Object>();
        for (Integer primaryItemId : findPrimaryIdByPrimaryIds) {
            Map<String, Object> primaryMap = new HashMap<String, Object>();
            InfoDomesticPrimary primaryOneItem = infoDomesticPrimaryRepository.findByid(primaryItemId);

            primaryMap.put("id", primaryOneItem.getId());
            primaryMap.put("name", Util.latin1ToUtf8(primaryOneItem.getName()));
            primaryMap.put("modifyFlag", primaryOneItem.getModifyFlag());

            List<Object> sub1Result = new ArrayList<Object>();
            List<Object[]> sub1List = infoDomesticSub1Repository.findObjectByPrimaryId(primaryItemId);

            for (Object[] sub1Item : sub1List) {
                Map<String, Object> sub1Map = new HashMap<>();
                sub1Map.put("id", sub1Item[0]);
                sub1Map.put("primaryId", sub1Item[1]);
                sub1Map.put("name", Util.latin1ToUtf8(sub1Item[2].toString()));
                sub1Map.put("goeKey", sub1Item[3]);
                sub1Map.put("modifyFlag", sub1Item[4]);
                sub1Map.put("nameVar", Util.latin1ToUtf8(sub1Item[5].toString()));

                List<Object> sub2Result = new ArrayList<Object>();
                List<Object[]> sub2List = infoDomesticSub2Repository.findObjectByPrimaryIdAndSub1Id(primaryItemId, (Integer) sub1Item[0]);

                for (Object[] sub2Item : sub2List) {
                    String name = (String) sub2Item[3];
                    HashMap<String, Object> thirdMap = new HashMap<String, Object>();
                    thirdMap.put("id", sub2Item[0]);
                    thirdMap.put("primaryId", sub2Item[1]);
                    thirdMap.put("sub1Id", sub2Item[2]);
                    thirdMap.put("name", Util.latin1ToUtf8(name));
                    thirdMap.put("modify_flag", sub2Item[4]);
                    sub2Result.add(thirdMap);
                }
                sub1Map.put("sub2", sub2Result);
                sub1Result.add(sub1Map);
            }

            primaryMap.put("sub1", sub1Result);
            domesticResult.add(primaryMap);
        }

        geoDomestic.put("geoDomestic", domesticResult);
        result.put("domestic", geoDomestic);

        InfoDomesticSub2 selectedItem = infoDomesticSub2Repository.findByid(sub2Id);
        selectedItem.setName(selectedItem.getName());

        result.put("selected", selectedItem);

        return result;
    }

    @Override
    public List<InfoDomesticPrimary> findAllForPrimary() {
        List<InfoDomesticPrimary> PrimaryList = infoDomesticPrimaryRepository.findAllByModifyFalse();
        for(InfoDomesticPrimary item : PrimaryList){ item.setName(Util.latin1ToUtf8(item.getName())); }
        return PrimaryList;
    }

    @Override
    public ArrayList<Object> findAllForPrimaryInSub1(Integer primaryId) {
        Map<String, Object> result = new HashMap<String, Object>();

        InfoDomesticSub1 resultItems = infoDomesticSub1Repository.findByid(primaryId);

        result.put("id", resultItems.getId());
        result.put("primaryId", resultItems.getPrimaryId());
        result.put("name", Util.latin1ToUtf8(resultItems.getName()));
        result.put("nameVar", Util.latin1ToUtf8(resultItems.getNameVar()));
        result.put("modifyFlag", resultItems.getModifyFlag());
        result.put("geoKey", resultItems.getGeoKey());

        Set<Integer> findPrimaryIdByPrimaryIds = mappingDomesticGeoRepository.findPrimaryIdByPrimaryId(primaryId);
        ArrayList<Object> domesticResult = new ArrayList<Object>();
        for (Integer primaryItemId : findPrimaryIdByPrimaryIds) {
            Map<String, Object> primaryMap = new HashMap<String, Object>();
            InfoDomesticPrimary primaryOneItem = infoDomesticPrimaryRepository.findByid(primaryItemId);

            primaryMap.put("id", primaryOneItem.getId());
            primaryMap.put("name", Util.latin1ToUtf8(primaryOneItem.getName()));
            primaryMap.put("modifyFlag", primaryOneItem.getModifyFlag());

            List<Object> sub1Result = new ArrayList<Object>();
            List<Object[]> sub1List = infoDomesticSub1Repository.findObjectByPrimaryId(primaryItemId);

            for (Object[] sub1Item : sub1List) {
                Map<String, Object> sub1Map = new HashMap<String, Object>();
                sub1Map.put("id", sub1Item[0]);
                sub1Map.put("primaryId", sub1Item[1]);
                sub1Map.put("name", Util.latin1ToUtf8(sub1Item[2].toString()));
                sub1Map.put("goeKey", sub1Item[3]);
                sub1Map.put("modifyFlag", sub1Item[4]);
                sub1Map.put("nameVar", Util.latin1ToUtf8(sub1Item[5].toString()));

                sub1Result.add(sub1Map);
            }

            primaryMap.put("sub1", sub1Result);
            domesticResult.add(primaryMap);
        }
        return domesticResult;
    }

    @Override
    public ArrayList<Object> findAllForPrimaryInSub1AndSub2(Integer primaryId, Integer sub1Id) {
        Map<String, Object> result = new HashMap<String, Object>();
        InfoDomesticSub1 resultItems = infoDomesticSub1Repository.findByid(sub1Id);

        result.put("id", resultItems.getId());
        result.put("primaryId", resultItems.getPrimaryId());
        result.put("name", Util.latin1ToUtf8(resultItems.getName()));
        result.put("nameVar", Util.latin1ToUtf8(resultItems.getNameVar()));
        result.put("modifyFlag", resultItems.getModifyFlag());
        result.put("geoKey", resultItems.getGeoKey());

        Set<Integer> findPrimaryIdByPrimaryIds = mappingDomesticGeoRepository.findPrimaryIdByPrimaryIdAndSub1Id(primaryId, sub1Id);
        ArrayList<Object> domesticResult = new ArrayList<Object>();
        for (Integer primaryItemId : findPrimaryIdByPrimaryIds) {
            Map<String, Object> primaryMap = new HashMap<String, Object>();
            InfoDomesticPrimary primaryOneItem = infoDomesticPrimaryRepository.findByid(primaryItemId);

            primaryMap.put("id", primaryOneItem.getId());
            primaryMap.put("name", Util.latin1ToUtf8(primaryOneItem.getName()));
            primaryMap.put("modifyFlag", primaryOneItem.getModifyFlag());

            List<Object> sub1Result = new ArrayList<Object>();
            List<Object[]> sub1List = infoDomesticSub1Repository.findObjectByPrimaryId(primaryItemId);

            for (Object[] sub1Item : sub1List) {
                Map<String, Object> sub1Map = new HashMap<>();
                sub1Map.put("id", sub1Item[0]);
                sub1Map.put("primaryId", sub1Item[1]);
                sub1Map.put("name", Util.latin1ToUtf8(sub1Item[2].toString()));
                sub1Map.put("goeKey", sub1Item[3]);
                sub1Map.put("modifyFlag", sub1Item[4]);
                sub1Map.put("nameVar", Util.latin1ToUtf8(sub1Item[5].toString()));

                List<Object> sub2Result = new ArrayList<Object>();
                List<Object[]> sub2List = infoDomesticSub2Repository.findObjectByPrimaryIdAndSub1Id(primaryItemId, (Integer) sub1Item[0]);

                for (Object[] sub2Item : sub2List) {
                    String name = (String) sub2Item[3];
                    HashMap<String, Object> thirdMap = new HashMap<String, Object>();
                    thirdMap.put("id", sub2Item[0]);
                    thirdMap.put("primaryId", sub2Item[1]);
                    thirdMap.put("sub1Id", sub2Item[2]);
                    thirdMap.put("name", Util.latin1ToUtf8(name));
                    thirdMap.put("modify_flag", sub2Item[4]);
                    sub2Result.add(thirdMap);
                }
                sub1Map.put("sub2", sub2Result);
                sub1Result.add(sub1Map);
            }

            primaryMap.put("sub1", sub1Result);
            domesticResult.add(primaryMap);
        }
        return domesticResult;
    }

}
