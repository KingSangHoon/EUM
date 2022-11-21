package com.sysone.eumaiwacs.serviceImpl.common;

import com.sysone.eumaiwacs.repository.setting.SensorDeviceRepository;
import com.sysone.eumaiwacs.service.common.RealtimeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RealtimeSettingServiceImpl implements RealtimeSettingService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SensorDeviceRepository sensorDeviceRepository;

    @Override
    public void restartSensorDevice(Integer sensorDeviceId) {
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            redisTemplate.opsForSet().add("asd:restart:request", device);
        }
    }

    @Override
    public void restartAnalyticsDevice() {
        redisTemplate.opsForValue().set("rad:restart:request", "1");
    }

    @Override
    public void restartStatDevice() {
        redisTemplate.opsForValue().set("stat:restart:request", "1");
    }

    @Override
    public void applyUserAgent() {
        redisTemplate.opsForValue().set("rad:http:useragent", "1");
    }

    @Override
    public void applyApplicationSettings() {
        redisTemplate.opsForValue().set("rad:application", "1");
    }

    @Override
    public void applyAlarmSettings() {
        redisTemplate.opsForValue().set("stat:alarmconfig", "1");
    }

    @Override
    public void applySslKey(Integer sensorDeviceId) {
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            redisTemplate.opsForSet().add("asd:sslkey", device);
        }
    }

    @Override
    public void applyPCapBpf(Integer sensorDeviceId) {
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            redisTemplate.opsForSet().add("asd:pcapbpf", device);
        }
    }

    @Override
    public void applyTransactionSettings() {
        redisTemplate.opsForValue().set("asd:transaction", "1");
    }

    // ===== 여기서부터는 추후 필요시 사용
    @Override
    public void loadSensorIniFileRequest(Integer sensorDeviceId) {
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            redisTemplate.opsForSet().add("asd:inifileread:request", device);
        }
    }

    @Override
    public Map<Integer, String> loadSensorIniFileResponse(Integer sensorDeviceId) {
        Map<Integer, String> resultMap = new HashMap<>();
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            resultMap.put(sensorDeviceId, redisTemplate.opsForValue().get("asd:inifileread:response:" + device));
        }

        return resultMap;
    }

    @Override
    public void applySensorIniFile(Integer sensorDeviceId, String iniFileContents) {
        List<String> sensorDevices = sensorDeviceRepository.findSensorNameBySensorId(sensorDeviceId);

        for (String device : sensorDevices) {
            redisTemplate.opsForValue().set("asd:inifileread:response:" + device, iniFileContents);
            redisTemplate.opsForSet().add("asd:inifilewrite:request", device);
        }
    }

}
