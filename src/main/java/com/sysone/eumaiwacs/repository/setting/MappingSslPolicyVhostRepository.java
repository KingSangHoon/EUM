package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingSslPolicyVhost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingSslPolicyVhostRepository extends JpaRepository<MappingSslPolicyVhost, Integer> {

    @Query("SELECT a FROM MappingSslPolicyVhost a WHERE a.sslPolicyId = ?1")
    List<MappingSslPolicyVhost> findMappingSslPolicyVhostBySslPolicyId(Integer sslPolicyId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingSslPolicyVhost a WHERE a.sslPolicyId = ?1")
    void deleteMappingSslPolicyVhostBySslPolicyId(Integer sslPolicyId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingSslPolicyVhost a WHERE a.sslPolicyId IN ?1")
    void deleteMappingSslPolicyVhostBySslPolicyIdSet(Set<Integer> sslPolicyIdSet);

}
