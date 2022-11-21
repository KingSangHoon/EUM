package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import com.sysone.eumaiwacs.entity.setting.MappingCriticalL3Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalL3IPRepository extends JpaRepository<MappingCriticalL3Ip,Integer> {
    @Query("SELECT a FROM MappingCriticalL3Ip a WHERE a.policyId=?1")
    ArrayList<MappingCriticalL3Ip> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
