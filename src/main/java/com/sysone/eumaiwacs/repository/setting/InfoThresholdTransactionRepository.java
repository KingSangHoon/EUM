package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdTransactionRepository extends JpaRepository<InfoThresholdTransaction, Integer> {

    @Query(value="SELECT a.* FROM tbl_info_threshold_transaction a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdTransaction findInfoThresholdTransactionLatest();

    @Query(value="SELECT a.* FROM tbl_info_threshold_transaction a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdTransaction findInfoThresholdTransactionFirst();

    @Query(value="SELECT a.* FROM tbl_info_threshold_transaction a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id < ?1 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdTransaction findInfoThresholdTransactionPrev(Integer id);

    @Query(value="SELECT a.* FROM tbl_info_threshold_transaction a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id > ?1 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdTransaction findInfoThresholdTransactionNext(Integer id);

    @Query("SELECT a.id, a.regDate, a.modifyDate, a.firstWriter, a.lastWriter, a.policyName FROM InfoThresholdTransaction a WHERE (a.deleted = false or a.deleted is null) AND a.type = 1 ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<Object[]> findAllInfoThresholdPolicyInfo();

    @Query("SELECT a FROM InfoThresholdTransaction a WHERE a.id = ?1")
    InfoThresholdTransaction findInfoThresholdTransactionById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdTransaction a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdTransactionByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdTransaction a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdTransactionById(Integer id);

}
