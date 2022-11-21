package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.entity.setting.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoHttpTypeRepository extends JpaRepository<InfoHttpType, Integer> {

    @Query("SELECT DISTINCT a.type FROM InfoHttpType a GROUP BY a.type ")
    List<Object[]> findType();

    @Query("SELECT a FROM InfoHttpType a WHERE a.type=?1 ")
    List<InfoHttpType> findHttpTypeByName(String type);

    @Query("SELECT a FROM InfoHttpType a WHERE a.id=?1 ")
    InfoHttpType findHttpTypeByid(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoHttpType a WHERE a.id IN ?1")
    void deleteHttp(Set<Integer> idSet);

    @Query("SELECT a FROM InfoHttpType a WHERE a.type = ?1 AND CAST(a.name AS integer) >= ?2 AND CAST(a.name AS integer) < ?3")
    List<InfoHttpType> findHttpResponseCodeByRange(String type, Integer startCode, Integer endCode);

    @Query("SELECT a FROM Test a")
    List<Test> test();
}
