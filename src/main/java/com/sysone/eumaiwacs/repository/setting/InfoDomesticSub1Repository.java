package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoDomesticSub1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface InfoDomesticSub1Repository extends JpaRepository<InfoDomesticSub1, Integer> {

    InfoDomesticSub1 findByid(Integer sub1Id);

    @Query("SELECT a FROM InfoDomesticSub1 a WHERE a.primaryId = ?1 ORDER BY a.id ASC")
    List<InfoDomesticSub1> findInfoDomesticSub1ByPrimaryId(Integer primaryId);

    @Query("SELECT a.id, a.primaryId, a.name, a.geoKey, a.modifyFlag, a.nameVar FROM InfoDomesticSub1 a WHERE a.primaryId = ?1")
    List<Object[]> findObjectByPrimaryId(Integer id);

    @Query("SELECT a.nameVar FROM InfoDomesticSub1 a WHERE a.id = ?1")
    String getsub1NameVar(Integer sub1Id);

    @Query("SELECT a.id, a.primaryId, a.nameVar, a.modifyFlag FROM InfoDomesticSub1 a WHERE a.id in (?1) AND a.modifyFlag=false")
    List<Object[]> findSub1ByPrimaryId(Set<Integer> sub1IdList);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoDomesticSub1 a WHERE a.primaryId IN ?1")
    void deleteDomesticSub1ByPrimaryId(Set<Integer> id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoDomesticSub1 a WHERE a.id IN ?1")
    void deleteDomesticSub1Byid(Set<Integer> id);

}
