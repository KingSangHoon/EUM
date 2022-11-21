package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.SslPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface SslPolicyRepository extends JpaRepository<SslPolicy, Integer> {

    @Query("SELECT a FROM SslPolicy a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<SslPolicy> findAllSensorDeviceNotDeleted();

    @Query("SELECT a FROM SslPolicy a WHERE a.id = ?1")
    SslPolicy findSslPolicyById(Integer id);

    @Query("SELECT a FROM SslPolicy a WHERE a.startIp = ?1 AND a.endIp = ?2 AND a.startPort = ?3 AND a.endPort = ?4 AND (a.deleted = false OR a.deleted is NULL)")
    List<SslPolicy> findEqualIpAndPortList(String startIp, String endIp, Integer startPort, Integer endPort);

    @Modifying
    @Query("UPDATE SslPolicy a SET a.deleted = true, a.modifyDate = ?1, a.lastWriter = ?2 WHERE a.id IN ?3")
    void deleteSslPolicyBySslPolicyIdSet(LocalDateTime date, String username, Set<Integer> policyIdSet);


}
