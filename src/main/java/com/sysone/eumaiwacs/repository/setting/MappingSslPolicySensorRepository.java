package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingSslPolicySensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingSslPolicySensorRepository extends JpaRepository<MappingSslPolicySensor, Integer> {

    @Query("SELECT a FROM MappingSslPolicySensor a WHERE a.sslPolicyId = ?1")
    List<MappingSslPolicySensor> findMappingSslPolicySensorBySslPolicyId(Integer sslPolicyId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingSslPolicySensor a WHERE a.sslPolicyId = ?1")
    void deleteMappingSslPolicySensorBySslPolicyId(Integer sslPolicyId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingSslPolicySensor a WHERE a.sslPolicyId IN ?1")
    void deleteMappingSslPolicySensorBySslPolicyIdSet(Set<Integer> policyIdSet);

}
