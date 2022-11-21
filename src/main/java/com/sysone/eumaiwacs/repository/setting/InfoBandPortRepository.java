package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoBandPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface InfoBandPortRepository extends JpaRepository<InfoBandPort, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoBandPort a WHERE a.id IN ?1")
    void deleteByIdSet(Set<Integer> mappingPortIdSet);
}
