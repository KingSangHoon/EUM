package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCriticalDefault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoCriticalRepository extends JpaRepository<InfoCriticalDefault, Integer> {

    InfoCriticalDefault findTop1ByOrderByIdDesc();

    @Query(value = "SELECT * FROM tbl_info_critical_default WHERE resource_type=?1 ORDER BY resource_id DESC LIMIT 1", nativeQuery = true)
    InfoCriticalDefault findByResourceTypeTop1ByOrderByIdDesc(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_http_transaction_seq'))", nativeQuery = true)
    void saveDefaultHttpTransaction(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_http_uri_seq'))", nativeQuery = true)
    void saveDefaultHttpUri(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_l4_tcp_seq'))", nativeQuery = true)
    void saveDefaultL4TCP(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_l4_udp_seq'))", nativeQuery = true)
    void saveDefaultL4UDP(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_l3_ip_seq'))", nativeQuery = true)
    void saveDefaultL3IP(Integer indexNum);

    @Modifying
    @Query(value = "INSERT INTO tbl_info_critical_default (resource_type, resource_id) VALUES(?1, nextval('tbl_info_critical_default_traffic_seq'))", nativeQuery = true)
    void saveDefaultTraffic(Integer indexNum);

    @Query("SELECT a FROM InfoCriticalDefault a WHERE a.resourceType=?1 AND a.resourceId=?2")
    InfoCriticalDefault findByResourceTypeAndResourceId(Integer indexNum, Integer sub2Id);

    @Query("SELECT MAX(a.resourceId) FROM InfoCriticalDefault a WHERE a.resourceType=?1")
    Integer findLastIdByResourceType(Integer indexNum);
}
