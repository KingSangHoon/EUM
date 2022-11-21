package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.realtime.ApplicationSearchIpMacPortForRealtimePageINF;
import com.sysone.eumaiwacs.entity.setting.*;
import com.sysone.eumaiwacs.repository.realtime.RealtimePageRepository;
import com.sysone.eumaiwacs.repository.setting.*;
import com.sysone.eumaiwacs.service.setting.ApplicationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired private AuditUtil auditUtil;
    @Autowired InfoApplicationRepository infoApplicationRepository;
    @Autowired MappingAppllicationVipRipRepository mappingAppllicationVipRipRepository;
    @Autowired InfoApplicationIpRepository infoApplicationIpRepository;
    @Autowired MappingApplicationIpVhostRepository mappingApplicationIpVhostRepository;
    @Autowired RealtimePageRepository realtimePageRepository;

    @Override
    public List<InfoApplication> findAllPolicyByDeletedFalse() {
        List<InfoApplication> result = infoApplicationRepository.findAllPolicyByDeletedFalse();
        for(InfoApplication item : result){
            item.setTitle(Util.latin1ToUtf8(item.getTitle()));
        }
        return result;
    }

    @Override
    @Transactional
    public void updatePolicyById(Map<String, Object> policyOneItem, LoginUser loginUser, HttpServletRequest req) {
        InfoApplication updatePolicyItem = infoApplicationRepository.findOnePolicyDetail((Integer) policyOneItem.get("id"));

        String title = (String) policyOneItem.get("title");

        updatePolicyItem.setTitle(Util.utf8ToLatin1(title));
        updatePolicyItem.setLastWrite(loginUser.getLoginId());
        updatePolicyItem.setModifyDate(LocalDateTime.now());
        infoApplicationRepository.save(updatePolicyItem);

        deleteApplicationForMapping((Integer) policyOneItem.get("id"));

        //applicationPolicyForMappingInsert(policyOneItem, updatePolicyItem);
        insertPolicyForIpOnlyVipRip(policyOneItem, updatePolicyItem);

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_APPLICATION, null, null, title, req);
    }

    @Override
    @Transactional
    public void insertPolicyById(Map<String, Object> policyMap, LoginUser loginUser, HttpServletRequest req) {

        InfoApplication applicationEntity = new InfoApplication();

        String title = (String) policyMap.get("title");

        applicationEntity.setTitle(Util.utf8ToLatin1(title));
        applicationEntity.setFirstWrite(loginUser.getLoginId());
        InfoApplication savedApplicationEntity = infoApplicationRepository.save(applicationEntity);

//        applicationPolicyForMappingInsert(policyMap, savedApplicationEntity);
        insertPolicyForIpOnlyVipRip(policyMap, savedApplicationEntity);

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_APPLICATION, null, null, title, req);
    }

    @Override
    @Transactional
    public void deletePolicy(String idStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> applicationIdSet = Util.getStringToIntegerSet(idStr);
        for(Integer applicationId : applicationIdSet){

            InfoApplication application = infoApplicationRepository.findOnePolicyDetail(applicationId);
            String title = Util.latin1ToUtf8(application.getTitle());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_GRID, null, null, title, req);

            deleteApplicationForMapping(applicationId);
        }
        infoApplicationRepository.deleteByIdSet(applicationIdSet);

    }

    @Override
    public Map<String, Object> findOnePolicyDetail(Integer applicationId) {
        Map<String, Object> result = new HashMap<>();
        InfoApplication policyItem = infoApplicationRepository.findOnePolicyDetail(applicationId);
        policyItem.setTitle(Util.latin1ToUtf8(policyItem.getTitle()));

        Set<Integer> ripIdSet = mappingAppllicationVipRipRepository.findRipIdByApplicationId(applicationId);
        ArrayList<Map<String, Object>> ripList = new ArrayList<>();

        for(Integer ripId : ripIdSet){

            Map<String, Object> ripInfoMap = new HashMap<String, Object>();

            InfoApplicationIp ripItem = infoApplicationIpRepository.findByIpId(ripId);
            Set<Integer> vipIdSet = mappingAppllicationVipRipRepository.findVipIdByApplicationAndRipId(applicationId, ripId);
            ArrayList<Map<String, Object>> vipList = new ArrayList<>();

            String isRipVip = null;

            for(Integer vipId : vipIdSet){

                Map<String, Object> vipInfoMap = new HashMap<String, Object>();
                InfoApplicationIp vipItem = infoApplicationIpRepository.findByIpId(vipId);

                if(vipItem.getIsVHost()){
                    ArrayList<MappingApplicationIpVhost> vipInVHostList = mappingApplicationIpVhostRepository.findAllByIpId(vipId, applicationId);
                    for(MappingApplicationIpVhost vhostItem : vipInVHostList) vhostItem.setVHost(Util.latin1ToUtf8(vhostItem.getVHost()));
                    vipInfoMap.put("vHost", vipInVHostList);
                }
                vipInfoMap.put("vipInfo", vipItem);
                vipList.add(vipInfoMap);

                isRipVip = vipItem.getType();
            }

            if(ripItem.getIsVHost()){
                ArrayList<MappingApplicationIpVhost> ripInVHostList = mappingApplicationIpVhostRepository.findAllByIpId(ripId, applicationId);
                for(MappingApplicationIpVhost vhostItem : ripInVHostList) vhostItem.setVHost(Util.latin1ToUtf8(vhostItem.getVHost()));
                ripInfoMap.put("vHost", ripInVHostList);
            }

            ripInfoMap.put("ripInfo", ripItem);
            ripInfoMap.put("vipInfo", vipList);

            ripInfoMap.put("type", isRipVip);

            ripList.add(ripInfoMap);
        }

        result.put("applicationInfo", policyItem);
        result.put("ripInfo", ripList);

        return result;
    }

    @Override
    public List<Map<String, Object>> detailFindAll() {

        List<Map<String, Object>> resultAll = new ArrayList<>();

        Set<Integer> applicationIdSet = infoApplicationRepository.findAllIdSetByByDeletedFalse();

        for(Integer applicationId : applicationIdSet){

            Map<String, Object> itemResult = new HashMap<>();

            InfoApplication policyItem = infoApplicationRepository.findOnePolicyDetail(applicationId);
            policyItem.setTitle(Util.latin1ToUtf8(policyItem.getTitle()));

            Set<Integer> ripIdSet = mappingAppllicationVipRipRepository.findRipIdByApplicationId(applicationId);
            ArrayList<Map<String, Object>> ripList = new ArrayList<>();

            for(Integer ripId : ripIdSet){

                Map<String, Object> ripInfoMap = new HashMap<String, Object>();

                InfoApplicationIp ripItem = infoApplicationIpRepository.findByIpId(ripId);
                Set<Integer> vipIdSet = mappingAppllicationVipRipRepository.findVipIdByApplicationAndRipId(applicationId, ripId);
                ArrayList<Map<String, Object>> vipList = new ArrayList<>();

                for(Integer vipId : vipIdSet){

                    Map<String, Object> vipInfoMap = new HashMap<String, Object>();
                    InfoApplicationIp vipItem = infoApplicationIpRepository.findByIpId(vipId);

                    if(vipItem.getIsVHost()){
                        ArrayList<MappingApplicationIpVhost> vipInVHostList = mappingApplicationIpVhostRepository.findAllByIpId(vipId, applicationId);
                        for(MappingApplicationIpVhost vhostItem : vipInVHostList) vhostItem.setVHost(Util.latin1ToUtf8(vhostItem.getVHost()));
                        vipInfoMap.put("vHost", vipInVHostList);
                    }
                    vipInfoMap.put("vipInfo", vipItem);
                    vipList.add(vipInfoMap);
                }

                if(ripItem.getIsVHost()){
                    ArrayList<MappingApplicationIpVhost> ripInVHostList = mappingApplicationIpVhostRepository.findAllByIpId(ripId, applicationId);
                    for(MappingApplicationIpVhost vhostItem : ripInVHostList) vhostItem.setVHost(Util.latin1ToUtf8(vhostItem.getVHost()));
                    ripInfoMap.put("vHost", ripInVHostList);
                }

                ripInfoMap.put("ripInfo", ripItem);
                ripInfoMap.put("vipInfo", vipList);

                if(vipList.size() == 0) ripInfoMap.put("type", "RIP");
                else ripInfoMap.put("type", "VIP");

                ripList.add(ripInfoMap);
            }

            itemResult.put("applicationInfo", policyItem);
            itemResult.put("ripInfo", ripList);

            resultAll.add(itemResult);
        }
        return resultAll;
    }

    @Override
    public Map<String, Object> searchIpAndMacAndPortByDateOrIp(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();

        List<ApplicationSearchIpMacPortForRealtimePageINF> searchIpList = new ArrayList<>();

        String begin = (String) param.get("begin");
        String end = (String) param.get("end");
        boolean isSearchTxt = (boolean) param.get("isSearchTxt");
        String searchTxt = (String) param.get("searchTxt");

        BigDecimal beginDate = Util.formatStringToBigDecimal(begin);
        BigDecimal endDate = Util.formatStringToBigDecimal(end);

        if(isSearchTxt) searchIpList = realtimePageRepository.searchIpAndMacAndPortByDateAndIp(beginDate, endDate, searchTxt);
        else searchIpList = realtimePageRepository.searchIpAndMacAndPortByDate(beginDate, endDate);

        result.put("searchIpList", searchIpList);
        result.put("infoApplicationIp", infoApplicationIpRepository.findAllInfoAndMapping());

        return result;
    }

    @Override
    public List<ApplicationSearchIpMacPortForRealtimePageINF> searchAllIpAndMacAndPortByIp() {
        return realtimePageRepository.searchAllIpAndMacAndPortByDate();
    }

    private void deleteApplicationForMapping(Integer applicationId) {
        Set<Integer> applicationMappingByRipId = mappingAppllicationVipRipRepository.findRipIdByApplicationId(applicationId);
        Set<Integer> applicationMappingByVipId = mappingAppllicationVipRipRepository.findVipIdByApplicationId(applicationId);

        infoApplicationIpRepository.deleteById(applicationMappingByRipId);
        infoApplicationIpRepository.deleteById(applicationMappingByVipId);
        mappingApplicationIpVhostRepository.deleteByApplicationId(applicationId);
        mappingAppllicationVipRipRepository.deleteByApplicationId(applicationId);
    }

    private void insertPolicyForIpOnlyVipRip(Map<String, Object> policyOneItem, InfoApplication updatePolicyItem) {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Object> applicationIpList = (ArrayList<Object>) policyOneItem.get("applicationIpList");

        for(Object item : applicationIpList){
            JSONObject applicationIpItem = mapper.convertValue(item, JSONObject.class);

            InfoApplicationIp ipItem = new InfoApplicationIp();
            InfoApplicationIp savedRipEntity = infoApplicationIpRepository.save(ipItem);

            ArrayList<Object> vipList = (ArrayList<Object>) applicationIpItem.get("applicationIpListRow");
            for(Object vipItem : vipList){
                JSONObject getVipObj = mapper.convertValue(vipItem, JSONObject.class);

                InfoApplicationIp ipEntity = new InfoApplicationIp();
                ipEntity.setIpAddr((String)getVipObj.get("twoDeptIpAddr"));
                ipEntity.setStartPort(Integer.parseInt((String)getVipObj.get("twoDeptPortStart")));
                if(getVipObj.get("twoDeptPortType").equals("range")) ipEntity.setEndPort(Integer.parseInt((String)getVipObj.get("twoDeptPortEnd")));
                else ipEntity.setEndPort(Integer.parseInt((String)getVipObj.get("twoDeptPortStart")));
                ipEntity.setIpMac((String)getVipObj.get("twoDeptMac"));
                ipEntity.setPortTypeIsRange((String)getVipObj.get("twoDeptPortType"));
                ipEntity.setProtocol((String)getVipObj.get("twoDeptIsTcpUdp"));
                ipEntity.setIsVHost((Boolean) getVipObj.get("isTwoDeptVHostType"));
                ipEntity.setIpAddrNum(Util.ipToLong((String)getVipObj.get("twoDeptIpAddr")));
                ipEntity.setType((String)applicationIpItem.get("oneDeptIpType"));

                InfoApplicationIp savedVipEntity = infoApplicationIpRepository.save(ipEntity);

                MappingApplicationVipRip mappingVipRipEntity = new MappingApplicationVipRip();
                mappingVipRipEntity.setApplicationId(updatePolicyItem.getId());
                mappingVipRipEntity.setVipId(savedVipEntity.getId());
                mappingVipRipEntity.setRipId(savedRipEntity.getId());

                mappingAppllicationVipRipRepository.save(mappingVipRipEntity);

                if((Boolean) getVipObj.get("isTwoDeptVHostType")){
                    ArrayList<Object> vHostList = (ArrayList<Object>) getVipObj.get("applicationIpListVHostList");
                    for(Object vHostItem : vHostList){
                        JSONObject getVHostItem = mapper.convertValue(vHostItem, JSONObject.class);
                        MappingApplicationIpVhost vipVHostItem = new MappingApplicationIpVhost();
                        vipVHostItem.setVHost((String)getVHostItem.get("vHostDeatil"));
                        vipVHostItem.setApplicationId(updatePolicyItem.getId());
                        vipVHostItem.setIpId(savedVipEntity.getId());

                        mappingApplicationIpVhostRepository.save(vipVHostItem);
                    }
                }
            }
        }
    }

    private void applicationPolicyForMappingInsert(Map<String, Object> policyOneItem, InfoApplication updatePolicyItem) {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Object> applicationIpList = (ArrayList<Object>) policyOneItem.get("applicationIpList");
        for(Object item : applicationIpList){
            JSONObject applicationIpItem = mapper.convertValue(item, JSONObject.class);

            if(applicationIpItem.get("oneDeptIpType").equals("RIP")){
                InfoApplicationIp ipItem = new InfoApplicationIp();
                ArrayList<Object> ripJson = (ArrayList<Object>) applicationIpItem.get("applicationIpListRow");
                JSONObject getRipItem = mapper.convertValue(ripJson.get(0), JSONObject.class);

                ipItem.setIpAddr((String) getRipItem.get("twoDeptIpAddr"));
                ipItem.setIpMac((String) getRipItem.get("twoDeptMac"));
                ipItem.setProtocol((String) getRipItem.get("twoDeptIsTcpUdp"));
                ipItem.setPortTypeIsRange((String) getRipItem.get("twoDeptPortType"));
                ipItem.setStartPort(Integer.parseInt((String)getRipItem.get("twoDeptPortStart")));
                if(getRipItem.get("twoDeptPortType").equals("range")) ipItem.setEndPort(Integer.parseInt((String)getRipItem.get("twoDeptPortEnd")));
                else ipItem.setEndPort(Integer.parseInt((String)getRipItem.get("twoDeptPortStart")));
                ipItem.setIsVHost((Boolean) getRipItem.get("isTwoDeptVHostType"));
                ipItem.setIpAddrNum(Util.ipToLong((String)getRipItem.get("twoDeptIpAddr"))) ;
                ipItem.setType((String)applicationIpItem.get("oneDeptIpType"));

                InfoApplicationIp savedIpEntity = infoApplicationIpRepository.save(ipItem);

                MappingApplicationVipRip mappingVipRipEntity = new MappingApplicationVipRip();
                mappingVipRipEntity.setApplicationId(updatePolicyItem.getId());
                mappingVipRipEntity.setRipId(savedIpEntity.getId());

                mappingAppllicationVipRipRepository.save(mappingVipRipEntity);

                if((Boolean) getRipItem.get("isTwoDeptVHostType")){
                    ArrayList<Object> vHostList = (ArrayList<Object>) getRipItem.get("applicationIpListVHostList");
                    for(Object vHostItem : vHostList){
                        JSONObject getVHostItem = mapper.convertValue(vHostItem, JSONObject.class);
                        MappingApplicationIpVhost ipVHostItem = new MappingApplicationIpVhost();
                        ipVHostItem.setVHost((String)getVHostItem.get("vHostDeatil"));
                        ipVHostItem.setApplicationId(updatePolicyItem.getId());
                        ipVHostItem.setIpId(savedIpEntity.getId());

                        mappingApplicationIpVhostRepository.save(ipVHostItem);
                    }
                }
            }else if(applicationIpItem.get("oneDeptIpType").equals("VIP")){

                InfoApplicationIp ipItem = new InfoApplicationIp();
                ipItem.setIpAddr((String)applicationIpItem.get("oneDeptIpAddr"));
                ipItem.setStartPort(Integer.parseInt((String)applicationIpItem.get("oneDeptPortStart")));
                if(applicationIpItem.get("isOneDeptPortType").equals("range")) {
                    ipItem.setEndPort(Integer.parseInt((String) applicationIpItem.get("oneDeptPortEnd")));
                }
                else {
                    ipItem.setEndPort(Integer.parseInt((String) applicationIpItem.get("oneDeptPortStart")));
                }
                ipItem.setPortTypeIsRange((String)applicationIpItem.get("isOneDeptPortType"));
                ipItem.setIpAddrNum(Util.ipToLong((String)applicationIpItem.get("oneDeptIpAddr")));
                ipItem.setType("RIP");
                InfoApplicationIp savedRipEntity = infoApplicationIpRepository.save(ipItem);

                ArrayList<Object> vipList = (ArrayList<Object>) applicationIpItem.get("applicationIpListRow");
                for(Object vipItem : vipList){
                    JSONObject getVipObj = mapper.convertValue(vipItem, JSONObject.class);

                    InfoApplicationIp ipEntity = new InfoApplicationIp();
                    ipEntity.setIpAddr((String)getVipObj.get("twoDeptIpAddr"));
                    ipEntity.setStartPort(Integer.parseInt((String)getVipObj.get("twoDeptPortStart")));
                    if(getVipObj.get("twoDeptPortType").equals("range")) ipEntity.setEndPort(Integer.parseInt((String)getVipObj.get("twoDeptPortEnd")));
                    else ipEntity.setEndPort(Integer.parseInt((String)getVipObj.get("twoDeptPortStart")));
                    ipEntity.setIpMac((String)getVipObj.get("twoDeptMac"));
                    ipEntity.setPortTypeIsRange((String)getVipObj.get("twoDeptPortType"));
                    ipEntity.setProtocol((String)getVipObj.get("twoDeptIsTcpUdp"));
                    ipEntity.setIsVHost((Boolean) getVipObj.get("isTwoDeptVHostType"));
                    ipEntity.setIpAddrNum(Util.ipToLong((String)getVipObj.get("twoDeptIpAddr")));
                    ipEntity.setType("VIP");

                    InfoApplicationIp savedVipEntity = infoApplicationIpRepository.save(ipEntity);

                    MappingApplicationVipRip mappingVipRipEntity = new MappingApplicationVipRip();
                    mappingVipRipEntity.setApplicationId(updatePolicyItem.getId());
                    mappingVipRipEntity.setVipId(savedVipEntity.getId());
//                    mappingVipRipEntity.setRipId(savedRipEntity.getId());

                    mappingAppllicationVipRipRepository.save(mappingVipRipEntity);

                    if((Boolean) getVipObj.get("isTwoDeptVHostType")){
                        ArrayList<Object> vHostList = (ArrayList<Object>) getVipObj.get("applicationIpListVHostList");
                        for(Object vHostItem : vHostList){
                            JSONObject getVHostItem = mapper.convertValue(vHostItem, JSONObject.class);
                            MappingApplicationIpVhost vipVHostItem = new MappingApplicationIpVhost();
                            vipVHostItem.setVHost((String)getVHostItem.get("vHostDeatil"));
                            vipVHostItem.setApplicationId(updatePolicyItem.getId());
                            vipVHostItem.setIpId(savedVipEntity.getId());

                            mappingApplicationIpVhostRepository.save(vipVHostItem);
                        }
                    }
                }
            }
        }
    }

}


