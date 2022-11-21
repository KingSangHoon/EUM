package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldL4Udp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldL4UdpRepository extends JpaRepository<InfoFieldL4Udp, Integer> {

    @Query("SELECT a FROM InfoFieldL4Udp a WHERE a.fieldViewName = ?1")
    InfoFieldL4Udp findInfoFieldL4UdpByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldL4Udp a WHERE a.id IN ?1")
    List<String> findInfoFieldL4UdpFieldViewNameByIdSet(List<Integer> idList);

}
