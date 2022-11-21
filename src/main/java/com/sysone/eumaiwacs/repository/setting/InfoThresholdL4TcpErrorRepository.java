package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdL4TcpError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdL4TcpErrorRepository extends JpaRepository<InfoThresholdL4TcpError, Integer> {

    @Query("SELECT a FROM InfoThresholdL4TcpError a WHERE a.id = ?1")
    InfoThresholdL4TcpError findInfoThresholdL4TcpErrorById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL4TcpError a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdL4TcpErrorByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL4TcpError a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdL4TcpErrorById(Integer id);

}
