package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingIspIpv4Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface MappingIspIpv4ServerRepository extends JpaRepository<MappingIspIpv4Server, Integer> {

    @Query("SELECT a FROM MappingIspIpv4Server a WHERE a.id = ?1")
    MappingIspIpv4Server findMappingIspIpv4ServerByMappingId(Integer id);

    @Query("SELECT a FROM MappingIspIpv4Server a WHERE a.ispId=?1")
    List<MappingIspIpv4Server> findIspAllByIspId(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingIspIpv4Server a WHERE a.ispId IN ?1")
    void deleteIspMappingIpByIspSet(Set<Integer> idSet);

    @Query("SELECT a FROM MappingIspIpv4Server a WHERE a.startIpNum <= ?1 AND a.endIpNum >= ?1")
    List<MappingIspIpv4Server> findIspIpRangeByIp(Long ipNum);

    @Query("SELECT count(a) FROM MappingIspIpv4Server a WHERE (a.startIpNum <= ?1 and ?1 <= a.endIpNum) OR (a.startIpNum <= ?2 and ?2 <= a.endIpNum)" )
    Long doubleCheck(Long startDigits, Long endDigits);

    @Query("SELECT count(a) FROM MappingIspIpv4Server a WHERE a.id <> ?3 AND ((a.startIpNum <= ?1 and ?1 <= a.endIpNum) OR (a.startIpNum <= ?2 and ?2 <= a.endIpNum))" )
    Long doubleCheckExMy(Long startDigits, Long endDigits, Integer id);

    @Transactional
    @Modifying
    @Query(value="delete from MappingIspIpv4Server a where a.id in (?1)")
    void deleteIpMapping(Set<Integer> idSet);

}
