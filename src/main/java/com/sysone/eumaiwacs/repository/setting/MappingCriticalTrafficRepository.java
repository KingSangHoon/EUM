package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import com.sysone.eumaiwacs.entity.setting.MappingCriticalTraffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalTrafficRepository extends JpaRepository<MappingCriticalTraffic,Integer> {
    @Query("SELECT a FROM MappingCriticalTraffic a WHERE a.policyId=?1")
    ArrayList<MappingCriticalTraffic> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
