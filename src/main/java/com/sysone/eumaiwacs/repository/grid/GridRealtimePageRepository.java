package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridRealtimePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridRealtimePageRepository extends JpaRepository<GridRealtimePage, Integer> {

    @Query("SELECT a FROM GridRealtimePage a WHERE a.userId = ?1")
    GridRealtimePage findGridRealtimePageByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridRealtimePage a WHERE a.userId IN ?1")
    void deleteGridRealtimePageByUserId(Set<Integer> userIdSet);

}
