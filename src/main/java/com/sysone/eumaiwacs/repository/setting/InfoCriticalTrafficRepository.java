package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalTraffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalTrafficRepository extends JpaRepository<InfoCriticalTraffic, Integer> {

    @Query("SELECT a FROM InfoCriticalTraffic a WHERE a.deleted=FALSE")
    List<InfoCriticalTraffic> findAllByDeleteFalse();
}
