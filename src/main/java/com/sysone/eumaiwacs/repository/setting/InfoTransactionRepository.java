package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoTransactionRepository extends JpaRepository<InfoTransaction, Integer> {

    @Query("SELECT a FROM InfoTransaction a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<InfoTransaction> findAllTransactionNotDeleted();

    @Query("SELECT a FROM InfoTransaction a WHERE a.urlId = ?1")
    InfoTransaction findInfoTransactionById(Integer urlId);

    @Modifying
    @Transactional
    @Query("UPDATE InfoTransaction a SET a.deleted = true, a.modifyDate = ?2, a.lastWriter = ?3 WHERE a.urlId IN ?1")
    void deleteByIdSet(Set<Integer> idSet, LocalDateTime date, String loginId);
}
