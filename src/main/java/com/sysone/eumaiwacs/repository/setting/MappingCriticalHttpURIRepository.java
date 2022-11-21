package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttpuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalHttpURIRepository extends JpaRepository<MappingCriticalHttpuri,Integer> {
    @Query("SELECT a FROM MappingCriticalHttpuri a WHERE a.policyId=?1")
    ArrayList<MappingCriticalHttpuri> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
