package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoThresholdL4Tcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface InfoThresholdL4TcpRepository extends JpaRepository<InfoThresholdL4Tcp, Integer> {

    @Query(value="SELECT a.* FROM tbl_info_threshold_l4_tcp a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdL4Tcp findInfoThresholdL4TcpLatest();

    @Query(value="SELECT a.* FROM tbl_info_threshold_l4_tcp a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdL4Tcp findInfoThresholdL4TcpFirst();

    @Query(value="SELECT a.* FROM tbl_info_threshold_l4_tcp a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id < ?1 ORDER BY a.id DESC LIMIT 1", nativeQuery = true)
    InfoThresholdL4Tcp findInfoThresholdL4TcpPrev(Integer id);

    @Query(value="SELECT a.* FROM tbl_info_threshold_l4_tcp a WHERE (a.deleted = false or a.deleted is null) AND a.type = 0 AND id > ?1 ORDER BY a.id ASC LIMIT 1", nativeQuery = true)
    InfoThresholdL4Tcp findInfoThresholdL4TcpNext(Integer id);

    @Query("SELECT a.id, a.regDate, a.modifyDate, a.firstWriter, a.lastWriter, a.policyName FROM InfoThresholdL4Tcp a WHERE (a.deleted = false or a.deleted is null) AND a.type = 1 ORDER BY coalesce(a.modifyDate, a.regDate) DESC, a.regDate DESC")
    List<Object[]> findAllInfoThresholdPolicyInfo();

    @Query("SELECT a FROM InfoThresholdL4Tcp a WHERE a.id = ?1")
    InfoThresholdL4Tcp findInfoThresholdL4TcpById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL4Tcp a SET a.deleted = true WHERE a.id IN ?1")
    void deleteInfoThresholdL4TcpByIdSet(Set<Integer> idSet);

    @Modifying
    @Transactional
    @Query("UPDATE InfoThresholdL4Tcp a SET a.deleted = true WHERE a.id = ?1")
    void deleteInfoThresholdL4TcpById(Integer id);

}
