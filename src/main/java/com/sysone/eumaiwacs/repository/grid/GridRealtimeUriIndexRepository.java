package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridRealtimeUriIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface GridRealtimeUriIndexRepository extends JpaRepository<GridRealtimeUriIndex, Integer> {

    @Query("SELECT a FROM GridRealtimeUriIndex a WHERE a.userId = ?1")
    GridRealtimeUriIndex findGridRealtimeUriIndexByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridRealtimeUriIndex a WHERE a.userId IN ?1")
    void deleteGridRealtimeUriIndexByUserId(Set<Integer> userIdSet);
}
