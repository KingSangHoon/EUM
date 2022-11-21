package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridRealtimePageIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridRealtimePageIndexRepository extends JpaRepository<GridRealtimePageIndex, Integer> {

    @Query("SELECT a FROM GridRealtimePageIndex a WHERE a.userId = ?1")
    GridRealtimePageIndex findGridRealtimePageIndexByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridRealtimePageIndex a WHERE a.userId IN ?1")
    void deleteGridRealtimePageIndexByUserId(Set<Integer> userIdSet);
}
