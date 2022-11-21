package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingBandRule;
import com.sysone.eumaiwacs.entity.setting.MappingBandRuleIpPortMac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MappingBandRuleIpPortMacRepository extends JpaRepository<MappingBandRuleIpPortMac, Integer> {

    @Query("SELECT a.ipId FROM MappingBandRuleIpPortMac a WHERE a.ruleId=?1")
    Set<Integer> findIpIdSetByRuleId(Integer ruleId);

    @Query("SELECT a.portId FROM MappingBandRuleIpPortMac a WHERE a.ruleId=?1")
    Set<Integer> findPortIdSetByRuleId(Integer ruleId);

    @Query("SELECT a.macId FROM MappingBandRuleIpPortMac a WHERE a.ruleId=?1")
    Set<Integer> findMacIdSetByRuleId(Integer ruleId);
}
