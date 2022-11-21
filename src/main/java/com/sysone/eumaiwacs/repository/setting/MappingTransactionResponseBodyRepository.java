package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingTransactionResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingTransactionResponseBodyRepository extends JpaRepository<MappingTransactionResponseBody, Integer> {

    @Query("SELECT a FROM MappingTransactionResponseBody a WHERE a.urlId = ?1")
    List<MappingTransactionResponseBody> findMappingTransactionResponseBodyByUrlId(Integer urlId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionResponseBody a WHERE a.urlId IN ?1")
    void deleteMappingTransactionResponseBodyByUrlIdSet(Set<Integer> urlIdSet);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionResponseBody a WHERE a.urlId = ?1")
    void deleteMappingTransactionResponseBodyByUrlId(Integer urlId);
}
