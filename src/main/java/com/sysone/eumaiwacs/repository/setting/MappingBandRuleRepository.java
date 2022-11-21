package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoBandPort;
import com.sysone.eumaiwacs.entity.setting.MappingBandRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface MappingBandRuleRepository extends JpaRepository<MappingBandRule, Integer> {

    @Query("SELECT a.ruleId FROM MappingBandRule a WHERE a.bandId=?1")
    Set<Integer> findAllByBandId(Integer bandId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MappingBandRule a WHERE a.bandId IN ?1")
    void deleteByBandId(Integer bandId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MappingBandRule a WHERE a.ruleId IN ?1")
    void deleteByRuleId(Integer ruleId);
}
