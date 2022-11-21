package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdCommonRepository extends JpaRepository<InfoThresholdCommon, Integer> {

    @Query(value="SELECT a.* FROM tbl_info_threshold_common a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdCommon findInfoThresholdCommonLatest();

    @Query(value="SELECT a.* FROM tbl_info_threshold_common a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdCommon findInfoThresholdCommonFirst();

    @Query(value="SELECT a.* FROM tbl_info_threshold_common a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id < ?1 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdCommon findInfoThresholdCommonPrev(Integer id);

    @Query(value="SELECT a.* FROM tbl_info_threshold_common a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id > ?1 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdCommon findInfoThresholdCommonNext(Integer id);

    @Query("SELECT a.id, a.regDate, a.modifyDate, a.firstWriter, a.lastWriter, a.policyName FROM InfoThresholdCommon a WHERE (a.deleted = false or a.deleted is null) AND a.type = 1 ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<Object[]> findAllInfoThresholdPolicyInfo();

    @Query("SELECT a FROM InfoThresholdCommon a WHERE a.id = ?1")
    InfoThresholdCommon findInfoThresholdCommonById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdCommon a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdCommonByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdCommon a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdCommonById(Integer id);

}
