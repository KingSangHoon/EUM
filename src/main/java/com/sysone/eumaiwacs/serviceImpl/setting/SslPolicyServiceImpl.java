package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.auth.ResponseResult;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.MappingSslPolicySensor;
import com.sysone.eumaiwacs.entity.setting.MappingSslPolicyVhost;
import com.sysone.eumaiwacs.entity.setting.SslPolicy;
import com.sysone.eumaiwacs.repository.setting.MappingSslPolicySensorRepository;
import com.sysone.eumaiwacs.repository.setting.MappingSslPolicyVhostRepository;
import com.sysone.eumaiwacs.repository.setting.SslPolicyRepository;
import com.sysone.eumaiwacs.service.setting.SslPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SslPolicyServiceImpl implements SslPolicyService {

    @Value("${app.sslUploadPath}")
    private String sslUploadPath;

    @Autowired
    private AuditUtil auditUtil;
    @Autowired
    private SslPolicyRepository sslPolicyRepository;
    @Autowired
    private MappingSslPolicySensorRepository mappingSslPolicySensorRepository;
    @Autowired
    private MappingSslPolicyVhostRepository mappingSslPolicyVhostRepository;

    @Override
    public List<Object> findAll() {
        List<Object> result = new ArrayList<>();

        List<SslPolicy> sslPolicyList = sslPolicyRepository.findAllSensorDeviceNotDeleted();
        for(SslPolicy policy : sslPolicyList) {
            Integer sslPolicyId = policy.getId();
            if(policy.getSslKeyFile() != null) policy.setSslKeyFile(Util.latin1ToUtf8(policy.getSslKeyFile()));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            Map<String, Object> map = objectMapper.convertValue(policy, Map.class);

            map.put("sensorDeviceCnt", mappingSslPolicySensorRepository.findMappingSslPolicySensorBySslPolicyId(sslPolicyId).size());
            map.put("vhostCnt", mappingSslPolicyVhostRepository.findMappingSslPolicyVhostBySslPolicyId(sslPolicyId).size());
            result.add(map);
        }
        return result;
    }

    @Override
    public Object find(Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();

        SslPolicy sslPolicy = sslPolicyRepository.findSslPolicyById(id);
        if(sslPolicy.getSslKeyFile() != null) sslPolicy.setSslKeyFile(Util.latin1ToUtf8(sslPolicy.getSslKeyFile()));

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Map<String, Object> map = objectMapper.convertValue(sslPolicy, Map.class);

        map.put("mappingSensorDevice", mappingSslPolicySensorRepository.findMappingSslPolicySensorBySslPolicyId(id));
        map.put("mappingVhost", mappingSslPolicyVhostRepository.findMappingSslPolicyVhostBySslPolicyId(id));

        return map;
    }

    @Override
    public ResponseResult save(MultipartHttpServletRequest req, LoginUser loginUser) throws IllegalStateException, IOException {

        Integer policyId = Integer.parseInt(req.getParameterValues("policyId")[0]);
        String startIp = req.getParameterValues("startIp")[0];
        String endIp = req.getParameterValues("endIp")[0];
        Integer startPort = Integer.parseInt(req.getParameterValues("startPort")[0]);
        Integer endPort = Integer.parseInt(req.getParameterValues("endPort")[0]);
        String password = req.getParameterValues("password")[0];
        String deviceIdStr = req.getParameterValues("deviceIdStr")[0];
        String isFileUpdate = req.getParameterValues("isFileUpdate")[0];
        String vHostStr = req.getParameterValues("vHostStr")[0];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String dateStr = sdf.format(cal.getTime());

        Set<Integer> deviceIdSet = null;
        String[] vHostArr = vHostStr.split(",");

        if(policyId == 0) {
            if(sslPolicyRepository.findEqualIpAndPortList(startIp, endIp, startPort, endPort).size() > 0) {
                return new ResponseResult("NotSuccess", "Duplicated IP And Port.");
            }

            SslPolicy sslPolicy = new SslPolicy();

            if(req.getFile("file") != null) {
                MultipartFile file = req.getFile("file");

                String originFileName = new String(file.getOriginalFilename());
                String originFileExtension = originFileName.substring(originFileName.lastIndexOf("."));
                String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + dateStr + originFileExtension;

                file.transferTo(new File(sslUploadPath + File.separator + storedFileName));

                sslPolicy.setSslKeyFile(Util.utf8ToLatin1(originFileName));
                sslPolicy.setSslKeyStoredFile(storedFileName);
            } else {
                sslPolicy.setSslKeyFile(null);
                sslPolicy.setSslKeyStoredFile(null);
            }

            sslPolicy.setStartIp(startIp);
            sslPolicy.setEndIp(endIp);
            sslPolicy.setStartIpNum(Util.ipToLong(startIp));
            sslPolicy.setEndIpNum(Util.ipToLong(endIp));
            sslPolicy.setStartPort(startPort);
            sslPolicy.setEndPort(endPort);
            sslPolicy.setPassword(password);
            sslPolicy.setRegDate(LocalDateTime.now());
            sslPolicy.setFirstWriter(loginUser.getLoginId());
            sslPolicy.setDeleted(false);
            sslPolicyRepository.saveAndFlush(sslPolicy);

            if(deviceIdStr != null) {
                deviceIdSet = Util.getDeviceSetInteger(deviceIdStr);

                for(Integer deviceId : deviceIdSet) {
                    MappingSslPolicySensor mappingPolicySensor = new MappingSslPolicySensor();
                    mappingPolicySensor.setSensorId(deviceId);
                    mappingPolicySensor.setSslPolicyId(sslPolicy.getId());
                    mappingSslPolicySensorRepository.save(mappingPolicySensor);
                }
            }

            if (vHostArr[0].length() > 0) {
                for (String s : vHostArr) {
                    MappingSslPolicyVhost mappingVhost = new MappingSslPolicyVhost();
                    mappingVhost.setSslPolicyId(sslPolicy.getId());
                    mappingVhost.setVhost(s);
                    mappingSslPolicyVhostRepository.save(mappingVhost);
                }
            }

        } else {
            SslPolicy originSslPolicy = sslPolicyRepository.findSslPolicyById(policyId);

            Boolean sIpChk = originSslPolicy.getStartIp().equals(startIp);
            Boolean eIpChk = originSslPolicy.getEndIp().equals(endIp);
            Boolean sPortChk = originSslPolicy.getStartPort().equals(startPort);
            Boolean ePortChk = originSslPolicy.getEndPort().equals(endPort);

            if (!(sIpChk && eIpChk && sPortChk && ePortChk)) {
                if (sslPolicyRepository.findEqualIpAndPortList(startIp, endIp, startPort, endPort).size() > 0)
                    return new ResponseResult("NotSuccess", "Duplicated IP And Port.");
            }

            SslPolicy sslPolicy = new SslPolicy();

            if( isFileUpdate.equals("true")) {
                if (originSslPolicy.getSslKeyStoredFile() != null) {
                    File uploadFile = new File(sslUploadPath + File.separator + originSslPolicy.getSslKeyStoredFile());
                    uploadFile.delete();
                }

                if (req.getFile("file") != null) {
                    MultipartFile file = req.getFile("file");

                    String originFileName = new String(file.getOriginalFilename());
                    String originFileExtension = originFileName.substring(originFileName.lastIndexOf("."));
                    String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + dateStr + originFileExtension;

                    file.transferTo(new File(sslUploadPath + File.separator + storedFileName));

                    sslPolicy.setSslKeyFile(Util.utf8ToLatin1(originFileName));
                    sslPolicy.setSslKeyStoredFile(storedFileName);

                } else {
                    sslPolicy.setSslKeyFile(null);
                    sslPolicy.setSslKeyStoredFile(null);
                }
            } else {
                if (originSslPolicy.getSslKeyFile() != null) sslPolicy.setSslKeyFile(originSslPolicy.getSslKeyFile());
                if (originSslPolicy.getSslKeyStoredFile() != null) sslPolicy.setSslKeyStoredFile(originSslPolicy.getSslKeyStoredFile());
            }

            sslPolicy.setId(policyId);
            sslPolicy.setStartIp(startIp);
            sslPolicy.setEndIp(endIp);
            sslPolicy.setStartIpNum(Util.ipToLong(startIp));
            sslPolicy.setEndIpNum(Util.ipToLong(endIp));
            sslPolicy.setStartPort(startPort);
            sslPolicy.setEndPort(endPort);
            sslPolicy.setPassword(password);
            sslPolicy.setRegDate(originSslPolicy.getRegDate());
            sslPolicy.setFirstWriter(originSslPolicy.getFirstWriter());
            sslPolicy.setModifyDate(LocalDateTime.now());
            sslPolicy.setLastWriter(loginUser.getLoginId());
            sslPolicy.setDeleted(false);
            sslPolicyRepository.saveAndFlush(sslPolicy);

            mappingSslPolicySensorRepository.deleteMappingSslPolicySensorBySslPolicyId(policyId);
            if (deviceIdStr != null) {
                deviceIdSet = Util.getDeviceSetInteger(deviceIdStr);

                for (Integer deviceId : deviceIdSet) {
                    MappingSslPolicySensor mappingPolicySensor = new MappingSslPolicySensor();
                    mappingPolicySensor.setSensorId(deviceId);
                    mappingPolicySensor.setSslPolicyId(sslPolicy.getId());
                    mappingSslPolicySensorRepository.save(mappingPolicySensor);
                }
            }

            mappingSslPolicyVhostRepository.deleteMappingSslPolicyVhostBySslPolicyId(policyId);
            if (vHostArr[0].length() > 0) {
                for (String s : vHostArr) {
                    MappingSslPolicyVhost mappingVhost = new MappingSslPolicyVhost();
                    mappingVhost.setSslPolicyId(sslPolicy.getId());
                    mappingVhost.setVhost(s);
                    mappingSslPolicyVhostRepository.save(mappingVhost);
                }
            }
        }

        String targetIp = "";
        if(startIp.equals(endIp)) targetIp = startIp;
        else targetIp = startIp + " ~ " + endIp;

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_SSL, null, null, targetIp, req);
        return new ResponseResult("Success", "Success Save.");
    }

    @Override
    @Transactional
    public void delete(String idStr, LoginUser loginUser) {
        Set<Integer> policyIdSet = new HashSet<>();
        if(idStr != null) policyIdSet = Util.getStringToIntegerSet(idStr);

        for (Integer policyId : policyIdSet) {
            SslPolicy originSslPolicy = sslPolicyRepository.findSslPolicyById(policyId);

            File uploadFile = new File(sslUploadPath + File.separator + originSslPolicy.getSslKeyStoredFile());
            uploadFile.delete();
        }
        sslPolicyRepository.deleteSslPolicyBySslPolicyIdSet(LocalDateTime.now(), loginUser.getLoginId(), policyIdSet);
        mappingSslPolicySensorRepository.deleteMappingSslPolicySensorBySslPolicyIdSet(policyIdSet);
        mappingSslPolicyVhostRepository.deleteMappingSslPolicyVhostBySslPolicyIdSet(policyIdSet);
    }

}
