package com.sysone.eumaiwacs.repository.realtime;

import com.sysone.eumaiwacs.entity.realtime.ApplicationSearchIpMacPortForRealtimePageINF;
import com.sysone.eumaiwacs.entity.realtime.RealtimePage;
import com.sysone.eumaiwacs.entity.realtime.RealtimePageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RealtimePageRepository extends JpaRepository<RealtimePage, RealtimePageKey> {

    /*
    검색 공통 모듈 (Union - QueryDsl 미지원 / Select Subquery 후처리 다시)
     */
    @Query(value="SELECT src_ip ip FROM tbl_eum_rad_http_page WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY src_ip UNION SELECT dst_ip ip FROM tbl_eum_rad_http_page WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY dst_ip ORDER BY ip", nativeQuery=true)
    List<String> searchHttpPageBothIp(BigDecimal begin, BigDecimal end);

    @Query(value="SELECT src_port port FROM tbl_eum_rad_http_page WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY src_port UNION SELECT dst_port port FROM tbl_eum_rad_http_page WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY dst_port ORDER BY port", nativeQuery=true)
    List<Integer> searchHttpPageBothPort(BigDecimal begin, BigDecimal end);

    @Query(value="SELECT transaction_url_id, (SELECT url_name FROM tbl_info_transaction WHERE url_id = transaction_url_id ) url_name FROM tbl_eum_rad_http_page WHERE ts_frame_arrival BETWEEN ?1 and ?2 GROUP BY transaction_url_id ORDER BY transaction_url_id", nativeQuery=true)
    List<Object[]> searchHttpPageTransaction(BigDecimal begin, BigDecimal end);

    @Query("SELECT a.realtimePageKey.dstIp AS dstIp, a.dstMac AS dstMac, a.realtimePageKey.dstPort AS dstPort FROM RealtimePage a WHERE a.realtimePageKey.dstIp LIKE %?3% AND a.realtimePageKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.realtimePageKey.dstIp, a.dstMac, a.realtimePageKey.dstPort ORDER BY a.realtimePageKey.dstIp, a.dstMac")
    List<ApplicationSearchIpMacPortForRealtimePageINF> searchIpAndMacAndPortByDateAndIp(BigDecimal begin, BigDecimal end, String ip);

    @Query("SELECT a.realtimePageKey.dstIp AS dstIp, a.dstMac AS dstMac, a.realtimePageKey.dstPort AS dstPort FROM RealtimePage a WHERE a.realtimePageKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.realtimePageKey.dstIp, a.dstMac, a.realtimePageKey.dstPort ORDER BY a.realtimePageKey.dstIp, a.dstMac")
    List<ApplicationSearchIpMacPortForRealtimePageINF> searchIpAndMacAndPortByDate(BigDecimal begin, BigDecimal end);

    @Query("SELECT a.realtimePageKey.dstIp AS dstIp, a.dstMac AS dstMac, a.realtimePageKey.dstPort AS dstPort FROM RealtimePage a GROUP BY a.realtimePageKey.dstIp, a.dstMac, a.realtimePageKey.dstPort ORDER BY a.realtimePageKey.dstIp, a.dstMac")
    List<ApplicationSearchIpMacPortForRealtimePageINF> searchAllIpAndMacAndPortByDate();

}
