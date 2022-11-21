package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingApplicationVipRip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface MappingAppllicationVipRipRepository extends JpaRepository<MappingApplicationVipRip, Integer> {

    @Query("SELECT DISTINCT a.ripId FROM MappingApplicationVipRip a WHERE a.applicationId = ?1")
    Set<Integer> findRipIdByApplicationId(Integer id);

    @Query("SELECT DISTINCT a.vipId FROM MappingApplicationVipRip a WHERE a.applicationId = ?1")
    Set<Integer> findVipIdByApplicationId(Integer id);

    @Query("SELECT DISTINCT a.vipId FROM MappingApplicationVipRip a WHERE a.applicationId = ?1 AND a.ripId=?2 AND a.vipId IS NOT NULL")
    Set<Integer> findVipIdByApplicationAndRipId(Integer applicationId, Integer ripId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MappingApplicationVipRip a WHERE a.applicationId = ?1")
    void deleteByApplicationId(Integer applicationId);
}
