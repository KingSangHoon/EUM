package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoApplicationRepository extends JpaRepository<InfoApplication, Integer> {
    @Query("SELECT a FROM InfoApplication a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<InfoApplication> findAllPolicyByDeletedFalse();

    @Query("SELECT a FROM InfoApplication a WHERE a.id=?1")
    InfoApplication findOnePolicyDetail(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoApplication a WHERE a.id IN ?1")
    void deleteByIdSet(Set<Integer> applicationIdSet);

    @Query("SELECT a.id FROM InfoApplication a WHERE (a.deleted = false OR a.deleted is NULL)")
    Set<Integer> findAllIdSetByByDeletedFalse();
}
