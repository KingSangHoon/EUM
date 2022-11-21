package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalHttptransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoCriticalHttptransactionRepository extends JpaRepository<InfoCriticalHttptransaction, Integer> {
    @Query("SELECT a FROM InfoCriticalHttptransaction a WHERE a.deleted=FALSE")
    List<InfoCriticalHttptransaction> findAllByDeleteFalse();
}
