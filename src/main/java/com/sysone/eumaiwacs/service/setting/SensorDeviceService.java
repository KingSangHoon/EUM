package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.SensorDevice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SensorDeviceService {

    List<SensorDevice> findAll();
    List<SensorDevice> findAllActive();
    SensorDevice find(Integer sensorId);
    SensorDevice update(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
}
