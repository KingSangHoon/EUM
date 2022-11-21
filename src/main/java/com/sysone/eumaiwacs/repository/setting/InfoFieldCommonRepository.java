package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldCommonRepository extends JpaRepository<InfoFieldCommon, Integer> {

    @Query("SELECT a FROM InfoFieldCommon a WHERE a.fieldViewName = ?1")
    InfoFieldCommon findInfoFieldCommonByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldCommon a WHERE a.id IN ?1")
    List<String> findInfoFieldCommonFieldViewNameByIdSet(List<Integer> idList);

}
