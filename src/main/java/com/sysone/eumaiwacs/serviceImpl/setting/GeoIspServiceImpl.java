package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoIspIpv4;
import com.sysone.eumaiwacs.entity.setting.MappingIspIpv4Server;
import com.sysone.eumaiwacs.repository.setting.InfoIspIpv4Repository;
import com.sysone.eumaiwacs.repository.setting.MappingIspIpv4ServerRepository;
import com.sysone.eumaiwacs.service.setting.GeoIspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GeoIspServiceImpl implements GeoIspService {

    @Autowired private AuditUtil auditUtil;
    @Autowired InfoIspIpv4Repository infoIspIpv4Repository;
    @Autowired MappingIspIpv4ServerRepository mappingIspIpv4ServerRepository;

    @Override
    public List<InfoIspIpv4> findAllIsp() {
        List<InfoIspIpv4> ispList = infoIspIpv4Repository.findAll(Sort.by(Sort.Direction.ASC, "modifyFlag" ,"nameEng"));
        for(InfoIspIpv4 item : ispList) item.setName(Util.latin1ToUtf8(item.getName()));
        return ispList;
    }

    @Override
    public List<MappingIspIpv4Server> findIpById(Integer id) {
        return mappingIspIpv4ServerRepository.findIspAllByIspId(id);
    }

    @Override
    public InfoIspIpv4 findIspPrimary(Integer id) {
        InfoIspIpv4 ispItem = infoIspIpv4Repository.findIspPrimary(id);
        ispItem.setName(Util.latin1ToUtf8(ispItem.getName()));
        return ispItem;
    }

    @Override
    public void createIsp(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req) {

        String krName = (String) map.get("name");
        String enName = (String) map.get("nameEng");

        InfoIspIpv4 createIsp = new InfoIspIpv4();
        createIsp.setName(Util.utf8ToLatin1(krName));
        createIsp.setNameEng(Util.utf8ToLatin1(enName));
        createIsp.setModifyFlag(true);
        createIsp.setLastUpdateDate(LocalDateTime.now());
        infoIspIpv4Repository.save(createIsp);

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, null, null, krName, req);
    }

    @Override
    public void updateIsp(Integer id, Map<String, Object> map, LoginUser loginUser, HttpServletRequest req) {
        String krName = (String) map.get("name");
        String enName = (String) map.get("nameEng");

        InfoIspIpv4 updateIsp = new InfoIspIpv4();
        updateIsp.setId(id);
        updateIsp.setName(Util.utf8ToLatin1(krName));
        updateIsp.setNameEng(Util.utf8ToLatin1(enName));
        updateIsp.setModifyFlag(true);
        updateIsp.setLastUpdateDate(LocalDateTime.now());
        infoIspIpv4Repository.save(updateIsp);

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, null, null, krName, req);
    }

    @Override
    @Transactional
    public void deleteIspGroup(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req) {

        for(Integer id : idSet) {
            InfoIspIpv4 ispInfo = infoIspIpv4Repository.findIspPrimary(id);
            String name = Util.latin1ToUtf8(ispInfo.getName());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, null, null, name, req);
        }

        infoIspIpv4Repository.deleteIspByIdSet(idSet);
        mappingIspIpv4ServerRepository.deleteIspMappingIpByIspSet(idSet);
    }

    @Override
    public Map<String, Object> findIspIpRangeByIp(String ip) {

        Map<String, Object> result = new HashMap<>();

        Long ipNum = Util.ipToLong(ip);

        List<MappingIspIpv4Server> ispMappingIpList = mappingIspIpv4ServerRepository.findIspIpRangeByIp(ipNum);

        Integer ispId = ispMappingIpList.get(0).getIspId();
        result.put("ipRange", ispMappingIpList);

        InfoIspIpv4 ispItem = null;
        if(ispId != null){
            ispItem = infoIspIpv4Repository.findIspPrimary(ispId);
            ispItem.setName(Util.latin1ToUtf8(ispItem.getName()));
            ispItem.setNameEng(Util.latin1ToUtf8(ispItem.getNameEng()));
        }
        result.put("isp", ispItem);

        return result;
    }

    @Override
    public Boolean createMappingIsp(Map<String, Object> ispMap, LoginUser loginUser, HttpServletRequest req) {
        Boolean result;

        Integer mappingId = Integer.parseInt(String.valueOf(ispMap.get("mappingId")));
        String startIp = (String) ispMap.get("startIp");
        String endIp = (String) ispMap.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        Long doubleCheckNum = mappingIspIpv4ServerRepository.doubleCheck(startDigits, endDigits);

        if (doubleCheckNum > 0) {
            result = false;
        } else {
            MappingIspIpv4Server ispItem = new MappingIspIpv4Server();
            ispItem.setIspId(mappingId);
            ispItem.setStartIp(startIp);
            ispItem.setEndIp(endIp);
            ispItem.setStartIpNum(startDigits);
            ispItem.setEndIpNum(endDigits);
            ispItem.setModifyFlag(true);
            mappingIspIpv4ServerRepository.save(ispItem);

            result = true;

            String ispName = Util.latin1ToUtf8(infoIspIpv4Repository.findIspPrimary(mappingId).getName());
            String targetIp = startIp + " ~ " + endIp;
            auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, ispName, "Mapping IP", targetIp, req);
        }
        return result;
    }

    @Override
    public Boolean updateMappingIsp(Integer id, Map<String, Object> ipMap, LoginUser loginUser, HttpServletRequest req) {
        Boolean result;

        Integer mappingId = Integer.parseInt(String.valueOf(ipMap.get("mappingId")));
        String startIp = (String) ipMap.get("startIp");
        String endIp = (String) ipMap.get("endIp");

        Long startDigits = Util.ipToLong(startIp);
        Long endDigits = Util.ipToLong(endIp);

        Long doubleCheckNum = mappingIspIpv4ServerRepository.doubleCheckExMy(startDigits, endDigits, id);

        if (doubleCheckNum > 0) {
            result = false;
        } else {
            MappingIspIpv4Server ispItem = new MappingIspIpv4Server();
            ispItem.setId(id);
            ispItem.setIspId(mappingId);
            ispItem.setStartIp(startIp);
            ispItem.setEndIp(endIp);
            ispItem.setStartIpNum(startDigits);
            ispItem.setEndIpNum(endDigits);
            ispItem.setModifyFlag(true);
            mappingIspIpv4ServerRepository.save(ispItem);

            result = true;

            String ispName = Util.latin1ToUtf8(infoIspIpv4Repository.findIspPrimary(mappingId).getName());
            String targetIp = startIp + " ~ " + endIp;
            auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, ispName, "Mapping IP", targetIp, req);
        }
        return result;
    }

    @Override
    public void deleteIpMapping(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req) {
        for(Integer id : idSet) {
            MappingIspIpv4Server mappingIp = mappingIspIpv4ServerRepository.findMappingIspIpv4ServerByMappingId(id);
            InfoIspIpv4 infoIsp = infoIspIpv4Repository.findIspPrimary(mappingIp.getIspId());

            String name = Util.latin1ToUtf8(infoIsp.getName());
            String targetIp = mappingIp.getStartIp() + " ~ " + mappingIp.getEndIp();

            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, name, "Mapping IP", targetIp, req);
        }
        mappingIspIpv4ServerRepository.deleteIpMapping(idSet);
    }
}
