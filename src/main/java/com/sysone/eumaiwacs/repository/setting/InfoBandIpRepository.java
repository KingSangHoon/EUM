package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoBandIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface InfoBandIpRepository extends JpaRepository<InfoBandIp, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoBandIp a WHERE a.id IN ?1")
    void deleteByIdSet(Set<Integer> mappingIpIdSet);
}
