package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InfoBandRepository extends JpaRepository<InfoBand, Integer> {

    @Query("SELECT a FROM InfoBand a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<InfoBand> findAllByDeleteIsFalse();

    @Query("SELECT a FROM InfoBand a WHERE a.bandId=?1")
    InfoBand findByBandId(Integer bandId);

    @Query("SELECT a.bandId FROM InfoBand a WHERE a.deleted=FALSE")
    Set<Integer> findIdSetByDeletedFalse();
}
