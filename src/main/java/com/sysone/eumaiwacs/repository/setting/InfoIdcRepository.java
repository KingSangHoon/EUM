package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoIdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface InfoIdcRepository extends JpaRepository<InfoIdc,Integer> {

    @Query("SELECT a FROM InfoIdc a WHERE a.id=?1" )
    InfoIdc findIdcPrimary(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM InfoIdc a WHERE a.id IN ?1")
    void deleteIdcByIdSet(Set<Integer> idSet);
}
