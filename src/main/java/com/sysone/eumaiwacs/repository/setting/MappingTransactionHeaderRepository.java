package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingTransactionHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingTransactionHeaderRepository extends JpaRepository<MappingTransactionHeader, Integer> {

    @Query("SELECT a FROM MappingTransactionHeader a WHERE a.urlId = ?1")
    List<MappingTransactionHeader> findMappingTransactionHeaderByUrlId(Integer urlId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionHeader a WHERE a.urlId IN ?1")
    void deleteMappingTransactionHeaderByUrlIdSet(Set<Integer> urlIdSet);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionHeader a WHERE a.urlId = ?1")
    void deleteMappingTransactionHeaderByUrlId(Integer urlId);
}
