package com.sysone.eumaiwacs.service.common;

import java.util.Map;

public interface RealtimeSettingService {

    void restartSensorDevice(Integer sensorDeviceId);
    void restartAnalyticsDevice();
    void restartStatDevice();
    void applyUserAgent();
    void applyApplicationSettings();
    void applyAlarmSettings();
    void applySslKey(Integer sensorDeviceId);
    void applyPCapBpf(Integer sensorDeviceId);
    void applyTransactionSettings();
    void loadSensorIniFileRequest(Integer sensorDeviceId);
    Map<Integer, String> loadSensorIniFileResponse(Integer sensorDeviceId);
    void applySensorIniFile(Integer sensorDeviceId, String iniFileContents);
}
