package com.sysone.eumaiwacs.repository.realtime;

import com.sysone.eumaiwacs.entity.realtime.RealtimeUri;
import com.sysone.eumaiwacs.entity.realtime.RealtimeUriKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RealtimeUriRepository extends JpaRepository<RealtimeUri, RealtimeUriKey> {

    @Query("SELECT a.httpHost, a.httpUri, a.httpUriSplit, a.realtimeUriKey.dstIp, a.realtimeUriKey.dstPort, a.realtimeUriKey.srcIp, a.realtimeUriKey.srcPort " +
            "FROM RealtimeUri a " +
            "WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 AND a.httpUri = ?3 " +
            "GROUP BY a.httpHost, a.httpUri, a.httpUriSplit, a.realtimeUriKey.dstIp, a.realtimeUriKey.dstPort, a.realtimeUriKey.srcIp, a.realtimeUriKey.srcPort")
    List<Object[]> findReferTransactionUri(BigDecimal begin, BigDecimal end, String httpUri);

//    @Query("SELECT a.httpHost, a.httpUri, a.httpUriSplit FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.httpHost, a.httpUri, a.httpUriSplit ORDER BY a.httpHost, a.httpUri, a.httpUriSplit")
//    List<Object[]> findUriInfoByTsFrameArrival(BigDecimal begin, BigDecimal end);
//
//    @Query("SELECT a.httpHost FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.httpHost ORDER BY a.httpHost")
//    List<String> findUriHttpHostInfoByTsFrameArrival(BigDecimal begin, BigDecimal end);
//
//    @Query("SELECT a.realtimeUriKey.dstIp, a.realtimeUriKey.dstPort FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.realtimeUriKey.dstIp, a.realtimeUriKey.dstPort ORDER BY a.realtimeUriKey.dstIp, a.realtimeUriKey.dstPort")
//    List<Object[]> findUriDstInfoByTsFrameArrival(BigDecimal begin, BigDecimal end);
//
//    @Query("SELECT a.realtimeUriKey.srcIp, a.realtimeUriKey.srcPort FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 GROUP BY a.realtimeUriKey.srcIp, a.realtimeUriKey.srcPort ORDER BY a.realtimeUriKey.srcIp, a.realtimeUriKey.srcPort")
//    List<Object[]> findUriSrcInfoByTsFrameArrival(BigDecimal begin, BigDecimal end);
//
//    @Query("SELECT a.httpHost, a.httpUri, a.httpUriSplit FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 AND a.httpHost = ?3 GROUP BY a.httpHost, a.httpUri, a.httpUriSplit ORDER BY a.httpHost, a.httpUri, a.httpUriSplit")
//    List<Object[]> findUriInfoByTsFrameArrivalAndHttpHost(BigDecimal begin, BigDecimal end, String httpHost);
//
//    @Query("SELECT a.httpHost, a.httpUri, a.httpUriSplit FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 AND a.realtimeUriKey.dstIp = ?3 AND a.realtimeUriKey.dstPort = ?4 GROUP BY a.httpHost, a.httpUri, a.httpUriSplit ORDER BY a.httpHost, a.httpUri, a.httpUriSplit")
//    List<Object[]> findUriInfoByTsFrameArrivalAndDstInfo(BigDecimal begin, BigDecimal end, String dstIp, Integer dstPort);
//
//    @Query("SELECT a.httpHost, a.httpUri, a.httpUriSplit FROM RealtimeUri a WHERE a.realtimeUriKey.tsFrameArrival BETWEEN ?1 AND ?2 AND a.realtimeUriKey.srcIp = ?3 AND a.realtimeUriKey.srcPort = ?4 GROUP BY a.httpHost, a.httpUri, a.httpUriSplit ORDER BY a.httpHost, a.httpUri, a.httpUriSplit")
//    List<Object[]> findUriInfoByTsFrameArrivalAndSrcInfo(BigDecimal begin, BigDecimal end, String srcIp, Integer srcPort);

    /*
   검색 공통 모듈 (Union - QueryDsl 미지원 / Select Subquery 후처리 다시)
    */
    @Query(value="SELECT src_ip ip FROM tbl_eum_rad_http_uri WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY src_ip UNION SELECT dst_ip ip FROM tbl_eum_rad_http_uri WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY dst_ip ORDER BY ip", nativeQuery=true)
    List<String> searchHttpUriBothIp(BigDecimal begin, BigDecimal end);

    @Query(value="SELECT src_port port FROM tbl_eum_rad_http_uri WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY src_port UNION SELECT dst_port port FROM tbl_eum_rad_http_uri WHERE ts_frame_arrival BETWEEN ?1 AND ?2 GROUP BY dst_port ORDER BY port", nativeQuery=true)
    List<Integer> searchHttpUriBothPort(BigDecimal begin, BigDecimal end);

    @Query(value="SELECT transaction_url_id, (SELECT url_name FROM tbl_info_transaction WHERE url_id = transaction_url_id ) url_name FROM tbl_eum_rad_http_uri WHERE ts_frame_arrival BETWEEN ?1 and ?2 GROUP BY transaction_url_id ORDER BY transaction_url_id", nativeQuery=true)
    List<Object[]> searchHttpUriTransaction(BigDecimal begin, BigDecimal end);

}
