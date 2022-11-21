package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoIspIpv4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface InfoIspIpv4Repository extends JpaRepository<InfoIspIpv4, Integer> {

    @Query("SELECT a FROM InfoIspIpv4 a WHERE a.id=?1" )
    InfoIspIpv4 findIspPrimary(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM InfoIspIpv4 a WHERE a.id IN ?1")
    void deleteIspByIdSet(Set<Integer> idSet);

}
