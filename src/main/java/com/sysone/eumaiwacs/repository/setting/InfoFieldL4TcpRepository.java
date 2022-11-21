package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldL4Tcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldL4TcpRepository extends JpaRepository<InfoFieldL4Tcp, Integer> {

    @Query("SELECT a FROM InfoFieldL4Tcp a WHERE a.fieldViewName = ?1")
    InfoFieldL4Tcp findInfoFieldL4TcpByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldL4Tcp a WHERE a.id IN ?1")
    List<String> findInfoFieldL4TcpFieldViewNameByIdSet(List<Integer> idList);

}
