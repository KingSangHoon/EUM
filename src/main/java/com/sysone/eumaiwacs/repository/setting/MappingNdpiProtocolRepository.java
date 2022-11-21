package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingNdpiProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface MappingNdpiProtocolRepository extends JpaRepository<MappingNdpiProtocol, Integer> {

    @Query(value = "select " +
                    "    tmnp.mapping_id, " +
                    "    (select ndpi_protocol_name from tbl_ndpi_protocol where ndpi_protocol_number=tmnp.ndpi_protocol_app_number) as appName, " +
                    "    (select ndpi_protocol_name from tbl_ndpi_protocol where ndpi_protocol_number=tmnp.ndpi_protocol_master_number) as masterName, " +
                    "    tmnp.is_white_list " +
                    "from tbl_mapping_ndpi_protocol AS tmnp", nativeQuery = true)
    List<Object[]> findMappingProtocolAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingNdpiProtocol a WHERE a.mappingId IN ?1")
    void deleteMappingId(Set<Integer> idSet);

    @Query("SELECT a FROM MappingNdpiProtocol a WHERE a.mappingId=?1")
    MappingNdpiProtocol findId(Integer mappingId);
}
