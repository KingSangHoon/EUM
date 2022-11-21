package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingApplicationIpVhost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface MappingApplicationIpVhostRepository extends JpaRepository<MappingApplicationIpVhost, Integer> {

    @Query("SELECT a FROM MappingApplicationIpVhost a WHERE a.ipId=?1 AND a.applicationId=?2")
    ArrayList<MappingApplicationIpVhost> findAllByIpId(Integer ripId, Integer applicationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MappingApplicationIpVhost a WHERE a.applicationId = ?1")
    void deleteByApplicationId(Integer applicationId);
}
