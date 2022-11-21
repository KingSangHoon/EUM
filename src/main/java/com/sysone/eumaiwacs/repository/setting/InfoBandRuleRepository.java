package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoBand;
import com.sysone.eumaiwacs.entity.setting.InfoBandRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoBandRuleRepository extends JpaRepository<InfoBandRule, Integer> {


    @Query("SELECT a FROM InfoBandRule a WHERE a.ruleId=?1")
    InfoBandRule findByRuleId(Integer ruleId);
}
