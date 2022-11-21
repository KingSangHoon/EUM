package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoFieldUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFieldUriRepository extends JpaRepository<InfoFieldUri, Integer> {

    @Query("SELECT a FROM InfoFieldUri a WHERE a.fieldViewName = ?1")
    InfoFieldUri findInfoFieldUriByFieldViewName(String fieldViewName);

    @Query("SELECT a.fieldViewName FROM InfoFieldUri a WHERE a.id IN ?1")
    List<String> findInfoFieldUriFieldViewNameByIdSet(List<Integer> idList);

}
