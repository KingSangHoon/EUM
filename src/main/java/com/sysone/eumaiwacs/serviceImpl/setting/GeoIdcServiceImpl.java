package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoIdc;
import com.sysone.eumaiwacs.entity.setting.MappingIdcServer;
import com.sysone.eumaiwacs.repository.setting.InfoIdcRepository;
import com.sysone.eumaiwacs.repository.setting.MappingIdcServerRepository;
import com.sysone.eumaiwacs.service.setting.GeoIdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GeoIdcServiceImpl implements GeoIdcService {

    @Autowired private AuditUtil auditUtil;
    @Autowired private InfoIdcRepository infoIdcRepository;
    @Autowired private MappingIdcServerRepository mappingIdcServerRepository;

    @Override
    public List<InfoIdc> findAllIdc() {
        List<InfoIdc> idcList = infoIdcRepository.findAll();
        for(InfoIdc item : idcList){
            item.setName(Util.latin1ToUtf8(item.getName()));
            item.setNameEng(Util.latin1ToUtf8(item.getNameEng()));
        }

        return idcList;
    }

    @Override
    public List<MappingIdcServer> findIpById(Integer groupId) {
        return mappingIdcServerRepository.findAllByIdcId(groupId);
    }

    @Override
    public void createIdc(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req) {

        String krName = (String) map.get("name");
        String enName = (String) map.get("nameEng");

        InfoIdc createIdc = new InfoIdc();
        createIdc.setName(Util.utf8ToLatin1(krName));
        createIdc.setNameEng(Util.utf8ToLatin1(enName));
        createIdc.setLastUpdateDate(LocalDateTime.now());
        infoIdcRepository.save(createIdc);

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, null, null, krName, req);
    }

    @Override
    public void updateIdc(Integer id, Map<String, Object> map, LoginUser loginUser, HttpServletRequest req) {

        String krName = (String) map.get("name");
        String enName = (String) map.get("nameEng");

        InfoIdc updateIdc = new InfoIdc();
        updateIdc.setId(id);
        updateIdc.setName(Util.utf8ToLatin1(krName));
        updateIdc.setNameEng(Util.utf8ToLatin1(enName));
        updateIdc.setLastUpdateDate(LocalDateTime.now());
        infoIdcRepository.save(updateIdc);

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, null, null, krName, req);
    }

    @Override
    @Transactional
    public void delete(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req) {

        for(Integer id : idSet) {
            InfoIdc infoIdc = infoIdcRepository.findIdcPrimary(id);
            String name = Util.latin1ToUtf8(infoIdc.getName());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, null, null, name, req);
        }
        infoIdcRepository.deleteIdcByIdSet(idSet);
        mappingIdcServerRepository.deleteIdcMappingIpByIdcSet(idSet);
    }

    @Override
    public InfoIdc findIdcPrimary(Integer id) {
        InfoIdc item = infoIdcRepository.findIdcPrimary(id);
        item.setName(Util.latin1ToUtf8(item.getName()));
        return item;
    }

    @Override
    public List<MappingIdcServer> findIdcIpRangeByIp(String ip) {

        List<MappingIdcServer> result;
        Long ipNum = Util.ipToLong(ip);
        result = mappingIdcServerRepository.findIdcIpRangeByIp(ipNum);

        return result;
    }

    @Override
    public void createIdcMappingIp(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req) {

        Integer mappingId = (Integer) map.get("mappingId");
        String startIp = (String) map.get("startIp");
        String endIp = (String) map.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        MappingIdcServer idcItem = new MappingIdcServer();
        idcItem.setIdcId(mappingId);
        idcItem.setStartIp(startIp);
        idcItem.setEndIp(endIp);
        idcItem.setStartIpNum(startDigits);
        idcItem.setEndIpNum(endDigits);
        idcItem.setModifyFlag(true);
        mappingIdcServerRepository.save(idcItem);

        String idcName = Util.latin1ToUtf8(infoIdcRepository.findIdcPrimary(mappingId).getName());
        String targetIp = startIp + " ~ " + endIp;
        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, idcName, "Mapping IP", targetIp, req);
    }

    @Override
    public void updateIdcMappingIp(Map<String, Object> map, Integer id, LoginUser loginUser, HttpServletRequest req) {

        Integer mappingId = (Integer) map.get("mappingId");
        String startIp = (String) map.get("startIp");
        String endIp = (String) map.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        MappingIdcServer idcItem = new MappingIdcServer();
        idcItem.setId(id);
        idcItem.setIdcId(mappingId);
        idcItem.setStartIp(startIp);
        idcItem.setEndIp(endIp);
        idcItem.setStartIpNum(startDigits);
        idcItem.setEndIpNum(endDigits);
        idcItem.setModifyFlag(true);
        mappingIdcServerRepository.save(idcItem);

        String idcName = Util.latin1ToUtf8(infoIdcRepository.findIdcPrimary(mappingId).getName());
        String targetIp = startIp + " ~ " + endIp;
        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, idcName, "Mapping IP", targetIp, req);
    }

    @Override
    public void deleteIpMapping(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req) {
        for(Integer id : idSet) {
            MappingIdcServer mappingIp = mappingIdcServerRepository.findByMappingId(id);
            InfoIdc infoIdc = infoIdcRepository.findIdcPrimary(mappingIp.getIdcId());

            String name = Util.latin1ToUtf8(infoIdc.getName());
            String targetIp = mappingIp.getStartIp() + " ~ " + mappingIp.getEndIp();

            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, name, "Mapping IP", targetIp, req);
        }
        mappingIdcServerRepository.deleteIpMapping(idSet);
    }

}
