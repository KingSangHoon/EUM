package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingTransactionUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingTransactionUriRepository extends JpaRepository<MappingTransactionUri, Integer> {

    @Query("SELECT a FROM MappingTransactionUri a WHERE a.urlId = ?1")
    List<MappingTransactionUri> findMappingTransactionUriByUrlId(Integer urlId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionUri a WHERE a.urlId IN ?1")
    void deleteMappingTransactionUriByUrlIdSet(Set<Integer> urlIdSet);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionUri a WHERE a.urlId = ?1")
    void deleteMappingTransactionUriByUrlId(Integer urlId);
}
