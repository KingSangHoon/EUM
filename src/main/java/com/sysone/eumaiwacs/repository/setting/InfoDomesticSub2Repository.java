package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoDomesticSub2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

public interface InfoDomesticSub2Repository extends JpaRepository<InfoDomesticSub2, Integer> {

    InfoDomesticSub2 findByid(Integer sub2Id);

    @Query("SELECT a FROM InfoDomesticSub2 a WHERE a.primaryId = ?1 AND a.sub1Id = ?2 ORDER BY a.id ASC")
    List<InfoDomesticSub2> findInfoDomesticSub2ByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id);

    @Query("SELECT a.id, a.primaryId, a.sub1Id, a.name, a.modifyFlag FROM InfoDomesticSub2 a WHERE a.primaryId = ?1 AND a.sub1Id = ?2")
    List<Object[]> findObjectByPrimaryIdAndSub1Id(Integer id, Integer sub1Id);

    @Query("SELECT a.name FROM InfoDomesticSub2 a WHERE a.id = ?1")
    String getsub2Name(Integer sub2Id);

    @Query("SELECT a.id,a.primaryId,a.sub1Id,a.name,a.modifyFlag FROM InfoDomesticSub2 a WHERE a.primaryId = ?1 AND a.sub1Id=?2 AND a.modifyFlag = false")
    List<Object[]> findDomesticSub1ById(Integer id, Integer integer);

    @Query("SELECT a.id,a.primaryId,a.sub1Id,a.name,a.modifyFlag FROM InfoDomesticSub2 a WHERE a.id in (?1) AND a.modifyFlag = false")
    List<Object[]> findSub2ByPrimaryId(Set<Integer> sub2IdList);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoDomesticSub2 a WHERE a.id IN ?1")
    void deleteDomesticSub2ById(Set<Integer> id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoDomesticSub2 a WHERE a.primaryId IN ?1")
    void deleteDomesticSub2ByPrimaryId(Set<Integer> id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoDomesticSub2 a WHERE a.sub1Id IN ?1")
    void deleteDomesticSub2BySub1Id(Set<Integer> id);


}
