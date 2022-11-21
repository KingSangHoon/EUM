package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalHttpuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalHttpURIRepository extends JpaRepository<InfoCriticalHttpuri, Integer> {
    @Query("SELECT a FROM InfoCriticalHttpuri a WHERE a.deleted=FALSE")
    List<InfoCriticalHttpuri> findAllByDeleteFalse();
}
