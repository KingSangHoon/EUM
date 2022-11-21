package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingDomestic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface MappingDomesticRepository extends JpaRepository<MappingDomestic, Integer> {

    @Query("SELECT count(a) FROM MappingDomestic a WHERE (a.startIpNum <= ?1 and ?1 <= a.endIpNum) OR (a.startIpNum <= ?2 and ?2 <= a.endIpNum)" )
    Long doubleCheck(Long startDigits, Long endDigits);

    @Transactional
    @Modifying
    @Query(value="delete from tbl_mapping_domestic_server where primary_id in (?1)", nativeQuery=true)
    void deleteDomesticByPrimaryId(Set<Integer> idSet);

    @Transactional
    @Modifying
    @Query(value="delete from tbl_mapping_domestic_server where sub1_id in (?1)", nativeQuery=true)
    void deleteDomesticBySub1d(Set<Integer> idSet);

    @Transactional
    @Modifying
    @Query(value="delete from tbl_mapping_domestic_server where sub2_id in (?1)", nativeQuery=true)
    void deleteDomesticBySub2Id(Set<Integer> idSet);

    @Transactional
    @Modifying
    @Query(value="update tbl_mapping_domestic_server set start_ip=?2, end_ip=?3, start_ip_num=?4, end_ip_num=?5 where id=?1", nativeQuery=true)
    void updateItem(Integer id, String startIp, String endIp, Long startDigits, Long endDigits);

    @Transactional
    @Modifying
    @Query(value="delete from tbl_mapping_domestic_server where id in (?1)", nativeQuery=true)
    void deleteMappingDomestic(Set<Integer> id);
}
