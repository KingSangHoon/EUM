package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.realtime.ApplicaionInfoAndMappingSimpleINF;
import com.sysone.eumaiwacs.entity.setting.InfoApplicationIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoApplicationIpRepository extends JpaRepository<InfoApplicationIp, Integer> {

    @Query("SELECT a FROM InfoApplicationIp a WHERE a.id = ?1")
    InfoApplicationIp findByIpId(Integer ripId);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoApplicationIp a WHERE a.id IN ?1")
    void deleteById(Set<Integer> applicationMappingByIpId);

    @Query(value = "SELECT DISTINCT tiai.ip_addr as ip, tiai.ip_mac as mac, tiai.start_port as startPort, tiai.end_port as endPort, tia.title as orgTitle FROM tbl_info_application_ip AS tiai LEFT OUTER JOIN tbl_mapping_application_vip_rip AS tmavr ON tiai.id = tmavr.rip_id OR tiai.id = tmavr.vip_id LEFT OUTER JOIN tbl_info_application AS tia ON tia.id = tmavr.application_id", nativeQuery = true)
    List<ApplicaionInfoAndMappingSimpleINF> findAllInfoAndMapping();
}
