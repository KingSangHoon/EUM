package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoGeoipServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoGeoipServerRepository extends JpaRepository<InfoGeoipServer, Integer> {

    @Query(value = "SELECT   geoip.id,\n" +
            "         country.country_code,\n" +
            "         country.country_name,\n" +
            "         geoip.start_ip,\n" +
            "         geoip.end_ip,\n" +
            "         geoip.start_ip_num,\n" +
            "         geoip.end_ip_num,\n" +
            "         geoip.modify_flag\n" +
            "FROM     tbl_info_geoip_server AS geoip\n" +
            "JOIN     tbl_info_country      AS country\n" +
            "ON       geoip.country_code=country.country_code\n" +
            "JOIN     tbl_info_continent AS continent\n" +
            "ON       country.continent_code=continent.continent_code\n" +
            "WHERE    continent.continent_code LIKE ?1\n" +
            "ORDER BY geoip.id ASC limit ?2 offset ?3", nativeQuery = true)
    List<Object[]> findGeoipByCountryCodeAndContinentCode(String continentCode, Integer nextPage, Integer offset);

    @Query(value = "SELECT id, country_code, country_name, start_ip, end_ip, start_ip_num, end_ip_num, modify_flag FROM tbl_info_geoip_server WHERE country_code like ?1 ORDER BY id ASC limit ?2 offset ?3 ", nativeQuery = true)
    List<Object[]> findGeoipByCountryCode(String countryCode, Integer nextPage, Integer offset);

    @Query(value = "SELECT count(*) FROM tbl_info_geoip_server as geoip \n" +
            "    JOIN tbl_info_country as country on geoip.country_code=country.country_code \n" +
            "    JOIN tbl_info_continent as continent on country.continent_code=continent.continent_code \n" +
            "where continent.continent_code like ?1 ", nativeQuery = true)
    Integer findCntByCountryCodeAndContinentCode(String continentCode);

    @Query(value = "SELECT count(*) FROM tbl_info_geoip_server WHERE country_code like ?1 ", nativeQuery = true)
    Integer findCntByCountryCode(String countryCode);

    @Query(value = "SELECT geoip.id,\n" +
            "       country.country_code,\n" +
            "       country.country_name,\n" +
            "       geoip.start_ip,\n" +
            "       geoip.end_ip,\n" +
            "       geoip.start_ip_num,\n" +
            "       geoip.end_ip_num,\n" +
            "       geoip.modify_flag\n" +
            "FROM   tbl_info_geoip_server AS geoip\n" +
            "       JOIN tbl_info_country AS country\n" +
            "         ON geoip.country_code = country.country_code\n" +
            "       JOIN tbl_info_continent AS continent\n" +
            "         ON country.continent_code = continent.continent_code\n" +
            "WHERE  geoip.start_ip_num <= ?1\n" +
            "       AND ?1 <= end_ip_num", nativeQuery = true)
    List<Object[]> findCountryIpRangeByIp(Long ipNum);
}
