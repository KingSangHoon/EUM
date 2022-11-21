package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalL4Tcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalL4TCPRepository extends JpaRepository<InfoCriticalL4Tcp, Integer> {
    @Query("SELECT a FROM InfoCriticalL4Tcp a WHERE a.deleted=FALSE")
    List<InfoCriticalL4Tcp> findAllByDeleteFalse();
}
