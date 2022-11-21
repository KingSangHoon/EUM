package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridGroupRealtimeUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface GridGroupRealtimeUriRepository extends JpaRepository<GridGroupRealtimeUri, Integer> {

    @Query("SELECT a FROM GridGroupRealtimeUri a WHERE a.gridGroupId = ?1")
    GridGroupRealtimeUri findGridGroupRealtimeUriByGridGroupId(Integer gridGroupId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridGroupRealtimeUri a WHERE a.gridGroupId IN ?1")
    void deleteGridGroupRealtimeUriByGridGroupIdSet(Set<Integer> gridGrouIdSet);
}
