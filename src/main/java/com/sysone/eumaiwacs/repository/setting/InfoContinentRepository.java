package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoContinent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface InfoContinentRepository extends JpaRepository<InfoContinent, Integer> {

    @Query("SELECT a FROM InfoContinent a WHERE a.continentId = ?1")
    InfoContinent findContinentByContinentId(Integer continentId);

    @Query("SELECT a.continentCode, a.continentName, a.continentId, a.modifyFlag FROM InfoContinent a")
    List<Object[]> getContinentGroup();

    @Query("SELECT a.continentId FROM InfoContinent a WHERE a.continentCode=?1")
    Integer findContinentIdByContinentCode(String code);

    @Query(value = "SELECT count(*) FROM tbl_info_continent WHERE continent_code=?1", nativeQuery = true)
    Integer cntByContinentCode(String continentCode);

    @Query(value = "SELECT count(*) FROM tbl_info_continent WHERE continent_code=?1 AND continent_id!=?2", nativeQuery = true)
    Integer cntByContinentCodeAndContinentId(String continentCode, Integer continentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM InfoContinent a WHERE a.continentId IN ?1")
    void deleteContinent(Set<Integer> continentId);

    @Query("SELECT a.continentId, a.continentCode, a.continentName, a.modifyFlag FROM InfoContinent a where a.modifyFlag = false")
    List<Object[]> findAllByModifyFlagFalse();

    @Query("SELECT a.continentId, a.continentCode, a.continentName, a.modifyFlag FROM InfoContinent a WHERE a.continentId IN ?1")
    List<Object[]> findByContinentByIdSet(Set<Integer> idSet);


}
