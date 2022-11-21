package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldL3Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldL3IpRepository extends JpaRepository<InfoFieldL3Ip, Integer> {

    @Query("SELECT a FROM InfoFieldL3Ip a WHERE a.fieldViewName = ?1")
    InfoFieldL3Ip findInfoFieldL3IpByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldL3Ip a WHERE a.id IN ?1")
    List<String> findInfoFieldL3IpFieldViewNameByIdSet(List<Integer> idList);

}
