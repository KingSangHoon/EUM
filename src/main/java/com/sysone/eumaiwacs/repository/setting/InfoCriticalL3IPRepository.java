package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalL3Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalL3IPRepository extends JpaRepository<InfoCriticalL3Ip, Integer> {
    @Query("SELECT a FROM InfoCriticalL3Ip a WHERE a.deleted=FALSE")
    List<InfoCriticalL3Ip> findAllByDeleteFalse();
}
