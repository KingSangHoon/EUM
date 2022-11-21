package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalL4Udp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalL4UDPRepository extends JpaRepository<InfoCriticalL4Udp, Integer> {
    @Query("SELECT a FROM InfoCriticalL4Udp a WHERE a.deleted=FALSE")
    List<InfoCriticalL4Udp> findAllByDeleteFalse();
}
