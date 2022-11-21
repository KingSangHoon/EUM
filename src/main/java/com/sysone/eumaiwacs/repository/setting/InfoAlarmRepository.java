package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.realtime.ApplicaionInfoAndMappingSimpleINF;
import com.sysone.eumaiwacs.entity.setting.InfoAlarm;
import com.sysone.eumaiwacs.entity.setting.InfoApplicationIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoAlarmRepository extends JpaRepository<InfoAlarm, Integer> {

    @Query("SELECT a FROM InfoAlarm a WHERE (a.deleted = false OR a.deleted is NULL) ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<InfoAlarm> findAllByDeleteIsFalse();
}
