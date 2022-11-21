package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.SensorDevice;
import com.sysone.eumaiwacs.repository.setting.SensorDeviceRepository;
import com.sysone.eumaiwacs.service.setting.SensorDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SensorDeviceServiceImpl implements SensorDeviceService {

    @Autowired
    private AuditUtil auditUtil;

    @Autowired
    private SensorDeviceRepository sensorDeviceRepository;

    @Override
    public List<SensorDevice> findAll() {
        List<SensorDevice> sensorDeviceList = sensorDeviceRepository.findAllSensorDeviceNotDeleted();

        for(SensorDevice device : sensorDeviceList) {
            device.setSensorName(Util.latin1ToUtf8(device.getSensorName()));
            if(device.getSensorAlias() != null) device.setSensorAlias(Util.latin1ToUtf8(device.getSensorAlias()));
            if(device.getDescription() != null) device.setDescription(Util.latin1ToUtf8(device.getDescription()));
        }
        return sensorDeviceList;
    }

    @Override
    public List<SensorDevice> findAllActive() {
        List<SensorDevice> sensorDeviceList = sensorDeviceRepository.findAllSensorDeviceActive();

        for(SensorDevice device : sensorDeviceList) {
            device.setSensorName(Util.latin1ToUtf8(device.getSensorName()));
            if(device.getSensorAlias() != null) device.setSensorAlias(Util.latin1ToUtf8(device.getSensorAlias()));
            if(device.getDescription() != null) device.setDescription(Util.latin1ToUtf8(device.getDescription()));
        }
        return sensorDeviceList;
    }

    @Override
    public SensorDevice find(Integer sensorId) {
       SensorDevice device = sensorDeviceRepository.findBySensorId(sensorId);

        device.setSensorName(Util.latin1ToUtf8(device.getSensorName()));
        if(device.getSensorAlias() != null) device.setSensorAlias(Util.latin1ToUtf8(device.getSensorAlias()));
        if(device.getDescription() != null) device.setDescription(Util.latin1ToUtf8(device.getDescription()));

        return device;
    }

    @Override
    public SensorDevice update(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        Integer sensorId = Integer.parseInt(param.get("sensorId").toString());
        SensorDevice device = sensorDeviceRepository.findBySensorId(sensorId);

        String sensorAlias = param.get("sensorAlias") == null ? null : param.get("sensorAlias").toString();

        if(sensorAlias != null) device.setSensorAlias(Util.utf8ToLatin1(sensorAlias));
        if(param.get("description") != null) device.setDescription(Util.utf8ToLatin1(param.get("description").toString()));
        if(param.get("sensorOs") != null) device.setSensorOs(Integer.parseInt(param.get("sensorOs").toString()));
        if(param.get("sensorVersion") != null) device.setSensorVersion(param.get("sensorVersion").toString());
        if(param.get("active") != null) device.setActive((Boolean) param.get("active"));
        device.setModifyDate(LocalDateTime.now());
        device.setLastWriter(loginUser.getLoginId());

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_SENSOR_DEVICE, null, null, sensorAlias, req);

        return sensorDeviceRepository.save(device);
    }


}
