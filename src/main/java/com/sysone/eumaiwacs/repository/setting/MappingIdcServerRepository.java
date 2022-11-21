package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingIdcServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface MappingIdcServerRepository extends JpaRepository<MappingIdcServer,Integer> {

    @Query("SELECT a FROM MappingIdcServer a WHERE a.id = ?1")
    MappingIdcServer findByMappingId(Integer id);

    @Query("SELECT a FROM MappingIdcServer a WHERE a.idcId=?1 ORDER BY a.id asc")
    List<MappingIdcServer> findAllByIdcId(Integer idcId);

    @Query("SELECT a FROM MappingIdcServer a WHERE a.startIpNum <= ?1 AND a.endIpNum >= ?1")
    List<MappingIdcServer> findIdcIpRangeByIp(Long ipNum);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingIdcServer a WHERE a.idcId IN ?1")
    void deleteIdcMappingIpByIdcSet(Set<Integer> idSet);

    @Transactional
    @Modifying
    @Query(value="delete from MappingIdcServer a where a.id in (?1)")
    void deleteIpMapping(Set<Integer> idSet);
}
