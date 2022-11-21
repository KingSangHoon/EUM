package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridGroupRealtimePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridGroupRealtimePageRepository extends JpaRepository<GridGroupRealtimePage, Integer> {

    @Query("SELECT a FROM GridGroupRealtimePage a WHERE a.gridGroupId = ?1")
    GridGroupRealtimePage findGridGroupRealtimePageByGridGroupId(Integer gridGroupId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridGroupRealtimePage a WHERE a.gridGroupId IN ?1")
    void deleteGridGroupRealtimePageByGridGroupIdSet(Set<Integer> gridGrouIdSet);
}
