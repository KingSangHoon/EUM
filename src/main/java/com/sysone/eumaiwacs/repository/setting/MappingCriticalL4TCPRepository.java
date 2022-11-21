package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import com.sysone.eumaiwacs.entity.setting.MappingCriticalL4Tcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalL4TCPRepository extends JpaRepository<MappingCriticalL4Tcp,Integer> {
    @Query("SELECT a FROM MappingCriticalL4Tcp a WHERE a.policyId=?1")
    ArrayList<MappingCriticalL4Tcp> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
