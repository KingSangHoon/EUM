package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldPageRepository extends JpaRepository<InfoFieldPage, Integer> {

    @Query("SELECT a FROM InfoFieldPage a WHERE a.fieldViewName = ?1")
    InfoFieldPage findInfoFieldPageByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldPage a WHERE a.id IN ?1")
    List<String> findInfoFieldPageFieldViewNameByIdSet(List<Integer> idList);

}
