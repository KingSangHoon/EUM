package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdUriRepository extends JpaRepository<InfoThresholdUri, Integer> {

    @Query(value="SELECT a.* FROM tbl_info_threshold_uri a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdUri findInfoThresholdUriLatest();

    @Query(value="SELECT a.* FROM tbl_info_threshold_uri a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdUri findInfoThresholdUriFirst();

    @Query(value="SELECT a.* FROM tbl_info_threshold_uri a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id < ?1 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdUri findInfoThresholdUriPrev(Integer id);

    @Query(value="SELECT a.* FROM tbl_info_threshold_uri a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id > ?1 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdUri findInfoThresholdUriNext(Integer id);

    @Query("SELECT a.id, a.regDate, a.modifyDate, a.firstWriter, a.lastWriter, a.policyName FROM InfoThresholdUri a WHERE (a.deleted = false or a.deleted is null) AND a.type = 1 ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<Object[]> findAllInfoThresholdPolicyInfo();

    @Query("SELECT a FROM InfoThresholdUri a WHERE a.id = ?1")
    InfoThresholdUri findInfoThresholdUriById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdUri a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdUriByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdUri a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdUriById(Integer id);

}
