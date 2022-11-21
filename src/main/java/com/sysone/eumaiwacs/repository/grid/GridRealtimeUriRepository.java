package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridRealtimeUri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface GridRealtimeUriRepository extends JpaRepository<GridRealtimeUri, Integer> {

    @Query("SELECT a FROM GridRealtimeUri a WHERE a.userId = ?1")
    GridRealtimeUri findGridRealtimeUriByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridRealtimeUri a WHERE a.userId IN ?1")
    void deleteGridRealtimeUriByUserId(Set<Integer> userIdSet);
}
