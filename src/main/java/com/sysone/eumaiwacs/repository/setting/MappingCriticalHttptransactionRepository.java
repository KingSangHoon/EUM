package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalHttptransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MappingCriticalHttptransactionRepository extends JpaRepository<MappingCriticalHttptransaction,Integer> {

    @Query("SELECT a FROM MappingCriticalHttptransaction a WHERE a.policyId=?1")
    ArrayList<MappingCriticalHttptransaction> findByPolicyId(Integer policyId);

    @Transactional
    void deleteByPolicyId(Integer policyId);
}
