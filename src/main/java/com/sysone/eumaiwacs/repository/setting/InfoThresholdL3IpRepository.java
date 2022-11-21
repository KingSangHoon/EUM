package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdL3Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdL3IpRepository extends JpaRepository<InfoThresholdL3Ip, Integer> {

    @Query(value="SELECT a.* FROM tbl_info_threshold_l3_ip a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdL3Ip findInfoThresholdL3IpLatest();

    @Query(value="SELECT a.* FROM tbl_info_threshold_l3_ip a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdL3Ip findInfoThresholdL3IpFirst();

    @Query(value="SELECT a.* FROM tbl_info_threshold_l3_ip a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id < ?1 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdL3Ip findInfoThresholdL3IpPrev(Integer id);

    @Query(value="SELECT a.* FROM tbl_info_threshold_l3_ip a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id > ?1 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdL3Ip findInfoThresholdL3IpNext(Integer id);

    @Query("SELECT a.id, a.regDate, a.modifyDate, a.firstWriter, a.lastWriter, a.policyName FROM InfoThresholdL3Ip a WHERE (a.deleted = false or a.deleted is null) AND a.type = 1 ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<Object[]> findAllInfoThresholdPolicyInfo();

    @Query("SELECT a FROM InfoThresholdL3Ip a WHERE a.id = ?1")
    InfoThresholdL3Ip findInfoThresholdL3IpById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL3Ip a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdL3IpByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL3Ip a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdL3IpById(Integer id);

}
