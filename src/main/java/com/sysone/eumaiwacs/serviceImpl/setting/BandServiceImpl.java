package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.BandService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BandServiceImpl implements BandService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired InfoBandRepository infoBandRepository;
    @Autowired InfoBandRuleRepository infoBandRuleRepository;
    @Autowired InfoBandIpRepository infoBandIpRepository;
    @Autowired InfoBandMacRepository infoBandMacRepository;
    @Autowired InfoBandPortRepository infoBandPortRepository;

    @Autowired MappingBandRuleRepository mappingBandRuleRepository;
    @Autowired MappingBandRuleIpPortMacRepository mappingBandRuleIpPortMacRepository;

    @Autowired InfoIspIpv4Repository infoIspIpv4Repository;
    @Autowired InfoIdcRepository infoIdcRepository;
    @Autowired InfoContinentRepository infoContinentRepository;
    @Autowired InfoCountryRepository infoCountryRepository;
    @Autowired InfoDomesticPrimaryRepository infoDomesticPrimaryRepository;
    @Autowired InfoDomesticSub1Repository infoDomesticSub1Repository;
    @Autowired InfoDomesticSub2Repository infoDomesticSub2Repository;
    @Autowired InfoApplicationRepository infoApplicationRepository;
    @Autowired InfoApplicationIpRepository infoApplicationIpRepository;

    @Override
    public ArrayList<Map<String, Object>> findAllPolicyByDeletedFalse() {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        Set<Integer> bandIdSet = infoBandRepository.findIdSetByDeletedFalse();

        for(Integer bandId : bandIdSet){
            Map<String, Object> bandItem = new HashMap<>();
            Optional<InfoBand> infoBandItem = infoBandRepository.findById(bandId);
            infoBandItem.ifPresent(item -> { item.setTitle(Util.latin1ToUtf8(item.getTitle())); });
            bandItem.put("bandItem", infoBandItem);

            Set<Integer> bandRuleIdSet = mappingBandRuleRepository.findAllByBandId(bandId);

            ArrayList<Map<String, Object>> ruleItemArr = new ArrayList();
            for (Integer ruleId : bandRuleIdSet){
                ruleItemArr.add(findOneBandRule(bandId, ruleId));
                entityManager.clear();
            }

            bandItem.put("bandRuleItem", ruleItemArr);
            result.add(bandItem);
        }

        return result;
    }

    @Override
    @Transactional
    public void insertRule(Map<String, Object> policyMap, LoginUser loginUser){
        insertRuleByPolicyMap(policyMap, loginUser);
    }

    @Override
    public Map<String, Object> findOnePolicyDetail(Integer bandId, Integer ruleId) {
        return findOneBandRule(bandId, ruleId);
    }

    @Override
    public void insertBand(Map<String, Object> policyMap, LoginUser loginUser) {
        InfoBand infoBandEntity = new InfoBand();
        infoBandEntity.setTitle( Util.utf8ToLatin1((String)policyMap.get("title")));
        infoBandEntity.setFirstWriter(loginUser.getLoginId());
        infoBandEntity.setModifyDate(LocalDateTime.now());

        infoBandRepository.save(infoBandEntity);
    }

    @Override
    @Transactional
    public void updateBand(Map<String, Object> policyMap, Integer bandId, LoginUser loginUser) {
        Optional<InfoBand> infoBandEntity = infoBandRepository.findById(bandId);
        infoBandEntity.ifPresent(item -> {
            item.setTitle(Util.utf8ToLatin1((String)policyMap.get("title")));
            item.setLastWriter(loginUser.getLoginId());
            item.setModifyDate(LocalDateTime.now());
        });
        infoBandRepository.save(infoBandEntity.get());
    }

    @Override
    @Transactional
    public void DeleteBand(String bandIdStr, LoginUser loginUser) {
        Set<Integer> bandIdSet = Util.getStringToIntegerSet(bandIdStr);
        for(Integer bandId : bandIdSet){
            infoBandRepository.deleteById(bandId);
            Set<Integer> bandRuleIdSet = mappingBandRuleRepository.findAllByBandId(bandId);

            for(Integer ruleId : bandRuleIdSet){
                deleteRuleById(ruleId);
            }

            mappingBandRuleRepository.deleteByBandId(bandId);
        }
    }

    @Override
    public void updateRule(Map<String, Object> policyMap, Integer bandId, Integer ruleId, LoginUser loginUser) {
        deleteRuleById(ruleId);
        insertRuleByPolicyMap(policyMap, loginUser);
    }

    @Override
    @Transactional
    public void deleteRule(Integer bandId, String ruleIdStr, LoginUser loginUser) {
        Set<Integer> ruleIdSet = Util.getStringToIntegerSet(ruleIdStr);
        for(Integer ruleId : ruleIdSet){
            deleteRuleById(ruleId);
        }
    }

    private Map<String, Object> findOneBandRule(Integer bandId, Integer ruleId) {
        Map<String, Object> result = new HashMap<>();

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objMapper.registerModule(new JavaTimeModule());
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        InfoBand infoBand = infoBandRepository.findByBandId(bandId);
        infoBand.setTitle(infoBand.getTitle());
        result.put("band", infoBand);

        InfoBandRule infoBandRule = infoBandRuleRepository.findByRuleId(ruleId);
        infoBandRule.setTitle(Util.latin1ToUtf8(infoBandRule.getTitle()));
        Map<String, Object> bandRuleMap = objMapper.convertValue(infoBandRule, Map.class);
        if(infoBandRule.getIsIsp()){
            List<InfoIspIpv4> ispList = infoIspIpv4Repository.findAllById(Util.convertStringToSet(infoBandRule.getIspId()));
            for(InfoIspIpv4 ispItem : ispList){ ispItem.setName(Util.latin1ToUtf8(ispItem.getName())); }
            bandRuleMap.put("ispItem", ispList);
        }
        if(infoBandRule.getIsIdc()){
            List<InfoIdc> idcList = infoIdcRepository.findAllById(Util.convertStringToSet(infoBandRule.getIdcId()));
            for(InfoIdc idcItem : idcList){ idcItem.setName(Util.latin1ToUtf8(idcItem.getName())); }
            bandRuleMap.put("idcItem", idcList);
        }
        if(infoBandRule.getIsContinent()){
            List<InfoContinent> continentList = infoContinentRepository.findAllById(Util.convertStringToSet(infoBandRule.getContinentId()));
            for(InfoContinent continentItem : continentList){ continentItem.setContinentName(Util.latin1ToUtf8(continentItem.getContinentName())); }
            bandRuleMap.put("continentItem", continentList);
        }
        if(infoBandRule.getIsCountry()){
            List<InfoCountry> continentList = infoCountryRepository.findAllById(Util.convertStringToSet(infoBandRule.getCountryId()));
            for(InfoCountry countryItem : continentList){ countryItem.setCountryName(Util.latin1ToUtf8(countryItem.getCountryName())); }
            bandRuleMap.put("countryItem", continentList);
        }
        if(infoBandRule.getIsDomestic()){
            HashMap<String, Object> domesticMap = new HashMap();
            domesticMap.put("primary", null);
            domesticMap.put("sub1", null);
            domesticMap.put("sub2", null);

            if(infoBandRule.getPrimaryId() != null && !infoBandRule.getPrimaryId().equals("")) {
                List<InfoDomesticPrimary> domesticPrimaryEntity = infoDomesticPrimaryRepository.findAllById(Util.convertStringToSet(infoBandRule.getPrimaryId()));
                for(InfoDomesticPrimary primaryItem : domesticPrimaryEntity){
                    primaryItem.setName(Util.latin1ToUtf8(primaryItem.getName()));
                }
                domesticMap.put("primary", domesticPrimaryEntity);
            }
            if(infoBandRule.getSub1Id() != null && !infoBandRule.getSub1Id().equals("")) {
                List<InfoDomesticSub1> domesticSub1Entity = infoDomesticSub1Repository.findAllById(Util.convertStringToSet(infoBandRule.getSub1Id()));
                for(InfoDomesticSub1 sub1Item : domesticSub1Entity){
                    sub1Item.setNameVar(Util.latin1ToUtf8(sub1Item.getNameVar()));
                    sub1Item.setName(Util.latin1ToUtf8(sub1Item.getName()));
                }
                domesticMap.put("sub1", domesticSub1Entity);
            }
            if(infoBandRule.getSub2Id() != null && !infoBandRule.getSub2Id().equals("")) {
                List<InfoDomesticSub2> domesticSub2Entity = infoDomesticSub2Repository.findAllById(Util.convertStringToSet(infoBandRule.getSub2Id()));
                for(InfoDomesticSub2 sub2Item : domesticSub2Entity){
                    sub2Item.setName(Util.latin1ToUtf8(sub2Item.getName()));
                }
                domesticMap.put("sub2", domesticSub2Entity);
            }

            bandRuleMap.put("domesticItem", domesticMap);
        }

        Set<Integer> mappingIpSet = mappingBandRuleIpPortMacRepository.findIpIdSetByRuleId(ruleId);
        Set<Integer> mappingPortSet = mappingBandRuleIpPortMacRepository.findPortIdSetByRuleId(ruleId);
        Set<Integer> mappingMacSet = mappingBandRuleIpPortMacRepository.findMacIdSetByRuleId(ruleId);

        List<InfoBandIp> mappingIpList = null;
        List<InfoBandPort> mappingPortList = null;
        List<InfoBandMac> mappingMacList = null;

        if(mappingIpSet.size() != 0){
            mappingIpList = infoBandIpRepository.findAllById(mappingIpSet);
            bandRuleMap.put("sourceIpItem", mappingIpList);
        }
        if(mappingPortSet.size() != 0){
            mappingPortList = infoBandPortRepository.findAllById(mappingPortSet);
            bandRuleMap.put("sourcePortItem", mappingPortList);
        }
        if(mappingMacSet.size() != 0){
            mappingMacList = infoBandMacRepository.findAllById(mappingMacSet);
            bandRuleMap.put("sourceMacItem", mappingMacList);
        }

        if(infoBandRule.getIsType() == 1){
            Map<String, Object> applicationItem = new HashMap<>();
            Optional<InfoApplication> applicationEntity = infoApplicationRepository.findById(infoBandRule.getApplicationid());
            applicationEntity.ifPresent(item -> { item.setTitle(Util.latin1ToUtf8(item.getTitle())); });
            applicationItem.put("application", applicationEntity);
            applicationItem.put("applicationSub", infoApplicationIpRepository.findById(infoBandRule.getApplicationidSub()));
            bandRuleMap.put("application", applicationItem);
        }

        result.put("bandRule", bandRuleMap);

        return result;
    }

    private void deleteRuleById(Integer ruleId){
        Set<Integer> mappingIpIdSet = mappingBandRuleIpPortMacRepository.findIpIdSetByRuleId(ruleId);
        Set<Integer> mappingPortIdSet = mappingBandRuleIpPortMacRepository.findPortIdSetByRuleId(ruleId);
        Set<Integer> mappingMacIdSet = mappingBandRuleIpPortMacRepository.findMacIdSetByRuleId(ruleId);

        infoBandIpRepository.deleteByIdSet(mappingIpIdSet);
        infoBandPortRepository.deleteByIdSet(mappingPortIdSet);
        infoBandMacRepository.deleteByIdSet(mappingMacIdSet);

        infoBandRuleRepository.deleteById(ruleId);

        mappingBandRuleRepository.deleteByRuleId(ruleId);
    }

    private void insertRuleByPolicyMap(Map<String, Object> policyMap, LoginUser loginUser) {
        ObjectMapper objMapper = new ObjectMapper();

        InfoBandRule bandRuleEntity = new InfoBandRule();

        bandRuleEntity.setTitle(Util.utf8ToLatin1((String) policyMap.get("title")));
        bandRuleEntity.setIsType(Integer.parseInt((String) policyMap.get("type")));
        if(Integer.parseInt((String) policyMap.get("type"))==1) {
            bandRuleEntity.setApplicationid((Integer) policyMap.get("applicationId"));
            bandRuleEntity.setApplicationidSub((Integer) policyMap.get("applicationIdSub"));
        }
        bandRuleEntity.setIpFlow((Integer) policyMap.get("ipFlow"));
        Map<String, Object> destinationIpMap = (Map<String, Object>) policyMap.get("destinationIp");
        Map<String, Object> destinationPortMap = (Map<String, Object>) policyMap.get("destinationPort");

        bandRuleEntity.setDestinationipType((Integer) destinationIpMap.get("destinationIpType"));
        bandRuleEntity.setDestinationipStart((String) destinationIpMap.get("destinationIpStart"));
        bandRuleEntity.setDestinationipEnd((String) destinationIpMap.get("destinationIpEnd"));

        bandRuleEntity.setDestinationportType((Integer) destinationPortMap.get("destinationPortType"));
        bandRuleEntity.setDestinationportStart(destinationPortMap.get("destinationPortStart")!=null ? String.valueOf((Integer) destinationPortMap.get("destinationPortStart")) : null);
        bandRuleEntity.setDestinationportEnd(destinationPortMap.get("destinationPortEnd")!=null ? String.valueOf((Integer) destinationPortMap.get("destinationPortEnd")) : null);

        bandRuleEntity.setMacFlow((Integer) policyMap.get("macFlow"));
        bandRuleEntity.setDestinationmac((String) policyMap.get("destinationMac"));

        bandRuleEntity.setPortocolType((Integer) policyMap.get("protocolType"));
        bandRuleEntity.setPortFlow((Integer) policyMap.get("portFlow"));

        bandRuleEntity.setIsIsp((Boolean) policyMap.get("isIsp"));
        if((Boolean) policyMap.get("isIsp")) {
            ArrayList ispIdArr = (ArrayList) policyMap.get("ispId");
            bandRuleEntity.setIspId(Util.convertArrToStrBetweenComma(ispIdArr));
        }

        bandRuleEntity.setIsIdc((Boolean) policyMap.get("isIdc"));
        if((Boolean) policyMap.get("isIdc")) {
            ArrayList idcIdArr = (ArrayList) policyMap.get("idcId");
            bandRuleEntity.setIdcId(Util.convertArrToStrBetweenComma(idcIdArr));
        }

        bandRuleEntity.setIsContinent((Boolean) policyMap.get("isContinent"));
        if((Boolean) policyMap.get("isContinent")) {
            ArrayList continentIdArr = (ArrayList) policyMap.get("continentId");
            bandRuleEntity.setContinentId(Util.convertArrToStrBetweenComma(continentIdArr));
        }

        bandRuleEntity.setIsCountry((Boolean) policyMap.get("isCountry"));
        if((Boolean) policyMap.get("isCountry")) {
            ArrayList countryIdArr = (ArrayList) policyMap.get("countryId");
            bandRuleEntity.setCountryId(Util.convertArrToStrBetweenComma(countryIdArr));
        }

        bandRuleEntity.setIsDomestic((Boolean) policyMap.get("isDomestic"));
        if((Boolean) policyMap.get("isDomestic")) {
            JSONObject domesticObj = objMapper.convertValue(policyMap.get("domesticId"), JSONObject.class);

            ArrayList<Integer> primaryIdSet = (ArrayList) domesticObj.get("primaryId");
            ArrayList<Integer> sub1IdSet = (ArrayList) domesticObj.get("sub1Id");
            ArrayList<Integer> sub2IdSet = (ArrayList) domesticObj.get("sub2Id");

            bandRuleEntity.setPrimaryId(Util.convertArrToStrBetweenComma(primaryIdSet));
            bandRuleEntity.setSub1Id(Util.convertArrToStrBetweenComma(sub1IdSet));
            bandRuleEntity.setSub2Id(Util.convertArrToStrBetweenComma(sub2IdSet));
        }

        bandRuleEntity.setFirstWriter(loginUser.getLoginId());
        bandRuleEntity.setRegDate(LocalDateTime.now());

        bandRuleEntity = infoBandRuleRepository.save(bandRuleEntity);

        MappingBandRule mapBandRuleEntity = new MappingBandRule();
        mapBandRuleEntity.setRuleId(bandRuleEntity.getRuleId());
        mapBandRuleEntity.setBandId((Integer) policyMap.get("bandId"));

        mappingBandRuleRepository.save(mapBandRuleEntity);

        ArrayList<Object> sourceIpArr = (ArrayList<Object>) policyMap.get("sourceIp");
        for(Object sourceIpItem : sourceIpArr){
            JSONObject sourceIpItemObj = objMapper.convertValue(sourceIpItem, JSONObject.class);
            InfoBandIp bandIpEntity = new InfoBandIp();

            bandIpEntity.setRuleId(bandRuleEntity.getRuleId());
            bandIpEntity.setType((Integer) sourceIpItemObj.get("sourceIpType"));
            bandIpEntity.setSourceipStart((String) sourceIpItemObj.get("sourceIpStart"));
            bandIpEntity.setSourceipEnd((String) sourceIpItemObj.get("sourceIpEnd"));

            bandIpEntity = infoBandIpRepository.save(bandIpEntity);

            MappingBandRuleIpPortMac mapBandRuleIpPortMacEntity = new MappingBandRuleIpPortMac();
            mapBandRuleIpPortMacEntity.setIpId(bandIpEntity.getId());
            mapBandRuleIpPortMacEntity.setRuleId(bandRuleEntity.getRuleId());

            mappingBandRuleIpPortMacRepository.save(mapBandRuleIpPortMacEntity);
        }

        ArrayList<Object> sourcePortArr = (ArrayList<Object>) policyMap.get("sourcePort");
        for(Object sourcePortItem : sourcePortArr){
            JSONObject sourcePortItemObj = objMapper.convertValue(sourcePortItem, JSONObject.class);
            InfoBandPort bandPortEntity = new InfoBandPort();

            bandPortEntity.setRuleId(bandRuleEntity.getRuleId());
            bandPortEntity.setType((Integer) sourcePortItemObj.get("sourcePortType"));
            bandPortEntity.setSourceportStart(sourcePortItemObj.get("sourcePortStart")!=null ? String.valueOf(sourcePortItemObj.get("sourcePortStart")) : null);
            bandPortEntity.setSourceportEnd(sourcePortItemObj.get("sourcePortEnd")!=null ? String.valueOf(sourcePortItemObj.get("sourcePortEnd")) : null);

            bandPortEntity = infoBandPortRepository.save(bandPortEntity);

            MappingBandRuleIpPortMac mapBandRuleIpPortMacEntity = new MappingBandRuleIpPortMac();
            mapBandRuleIpPortMacEntity.setPortId(bandPortEntity.getId());
            mapBandRuleIpPortMacEntity.setRuleId(bandRuleEntity.getRuleId());

            mappingBandRuleIpPortMacRepository.save(mapBandRuleIpPortMacEntity);
        }

        ArrayList<String> sourceMacArr = (ArrayList<String>) policyMap.get("sourctMac");
        for(String sourceMacItem : sourceMacArr){
            InfoBandMac bandMacEntity = new InfoBandMac();

            bandMacEntity.setRuleId(bandRuleEntity.getRuleId());
            bandMacEntity.setSourceMac(sourceMacItem);

            bandMacEntity = infoBandMacRepository.save(bandMacEntity);

            MappingBandRuleIpPortMac mapBandRuleIpPortMacEntity = new MappingBandRuleIpPortMac();
            mapBandRuleIpPortMacEntity.setMacId(bandMacEntity.getId());
            mapBandRuleIpPortMacEntity.setRuleId(bandRuleEntity.getRuleId());

            mappingBandRuleIpPortMacRepository.save(mapBandRuleIpPortMacEntity);
        }
    }

}


