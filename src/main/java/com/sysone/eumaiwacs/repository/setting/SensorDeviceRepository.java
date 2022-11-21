package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.SensorDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface SensorDeviceRepository extends JpaRepository<SensorDevice, Integer> {

    @Query("SELECT a FROM SensorDevice a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<SensorDevice> findAllSensorDeviceNotDeleted();

    @Query("SELECT a FROM SensorDevice a WHERE (a.deleted = false OR a.deleted is NULL) AND a.active = true ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<SensorDevice> findAllSensorDeviceActive();

    @Query("SELECT a FROM SensorDevice a WHERE a.sensorId = ?1")
    SensorDevice findBySensorId(Integer sensorId);

    @Query("SELECT a FROM SensorDevice a WHERE a.sensorId = ?1")
    public SensorDevice findSensorDeviceById(Integer deviceId);

    @Modifying
    @Transactional
    @Query(value="UPDATE tbl_sensor_device SET active = ?1, modify_date = now(), last_writer = ?2 WHERE sensor_id IN (?3)", nativeQuery=true)
    void activeSensorDevices(Boolean active, String userName, Set<Integer> deviceIdSet);

    @Modifying
    @Transactional
    @Query(value="UPDATE tbl_sensor_device SET deleted = true, modify_date = now(), last_writer = ?1 WHERE sensor_id IN (?2)", nativeQuery=true)
    void deleteSensordevice(String userName, Set<Integer> deviceIdSet);

    @Query("SELECT s.sensorName FROM SensorDevice s WHERE s.sensorId = ?1 AND s.active = true AND s.deleted = false")
    List<String> findSensorNameBySensorId(Integer sensorId);

}
