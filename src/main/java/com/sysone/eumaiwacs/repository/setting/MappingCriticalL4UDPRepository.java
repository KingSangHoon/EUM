package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import com.sysone.eumaiwacs.entity.setting.MappingCriticalL4Udp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalL4UDPRepository extends JpaRepository<MappingCriticalL4Udp,Integer> {
    @Query("SELECT a FROM MappingCriticalL4Udp a WHERE a.policyId=?1")
    ArrayList<MappingCriticalL4Udp> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
