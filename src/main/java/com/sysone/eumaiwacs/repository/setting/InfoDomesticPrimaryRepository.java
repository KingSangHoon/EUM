package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoDomesticPrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface InfoDomesticPrimaryRepository extends JpaRepository<InfoDomesticPrimary, Integer> {

    InfoDomesticPrimary findByid(Integer primaryItemId);
    List<InfoDomesticPrimary> findByModifyFlag(boolean b);

    @Transactional
    @Modifying
    @Query("DELETE FROM InfoDomesticPrimary a WHERE a.id IN ?1")
    void deleteDomesticPrimaryById(Set<Integer> id);

    @Query("SELECT a FROM InfoDomesticPrimary a WHERE a.id = ?1")
    InfoDomesticPrimary findDomesticPrimaryById(Integer primaryId);

    @Query("SELECT a FROM InfoDomesticPrimary a WHERE a.modifyFlag=false")
    List<InfoDomesticPrimary> findAllByModifyFalse();

    @Query("SELECT a.name FROM InfoDomesticPrimary a WHERE a.id = ?1")
    String getPrimaryName(Integer primaryId);

    @Query("SELECT a FROM InfoDomesticPrimary a WHERE a.id=?1")
    InfoDomesticPrimary findInfoDomesticPrimaryById(Integer id);

    @Query("SELECT a FROM InfoDomesticPrimary a ORDER BY a.id ASC")
    List<InfoDomesticPrimary> findInfoDomesticPrimary();


}
