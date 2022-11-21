package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdUsedRepository extends JpaRepository<InfoThresholdUsed, Integer> {

    @Query("SELECT a FROM InfoThresholdUsed a WHERE a.thresholdId = ?1")
    List<InfoThresholdUsed> findInfoThresholdUsedByThresholdId(Integer thresholdId);

    @Query("SELECT a.fieldId FROM InfoThresholdUsed a WHERE a.thresholdId = ?1")
    List<Integer> findInfoThresholdUsedFieldIdByThresholdId(Integer thresholdId);

    @Query("SELECT a.fieldId FROM InfoThresholdUsed a WHERE a.thresholdId = ?1 AND a.type = ?2")
    List<Integer> findInfoThresholdUsedFieldIdByThresholdIdAndType(Integer thresholdId, String type);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoThresholdUsed a WHERE a.thresholdId IN ?1")
    void deleteInfoThresholdUsedByThresholdIdSet(Set<Integer> thresholdIdSet);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoThresholdUsed a WHERE a.thresholdId = ?1")
    void deleteInfoThresholdUsedByThresholdId(Integer thresholdId);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoThresholdUsed a WHERE a.thresholdId = ?1 AND a.type = ?2")
    void deleteInfoThresholdUsedByThresholdIdAndType(Integer thresholdId, String type);


}
