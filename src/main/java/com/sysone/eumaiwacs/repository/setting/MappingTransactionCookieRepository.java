package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingTransactionCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface MappingTransactionCookieRepository extends JpaRepository<MappingTransactionCookie, Integer> {

    @Query("SELECT a FROM MappingTransactionCookie a WHERE a.urlId = ?1")
    List<MappingTransactionCookie> findMappingTransactionCookieByUrlId(Integer urlId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionCookie a WHERE a.urlId IN ?1")
    void deleteMappingTransactionCookieByurlIdSet(Set<Integer> urlIdSet);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingTransactionCookie a WHERE a.urlId = ?1")
    void deleteMappingTransactionCookieByurlId(Integer urlId);
}
