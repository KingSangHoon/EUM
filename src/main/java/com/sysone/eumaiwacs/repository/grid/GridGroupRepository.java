package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface GridGroupRepository extends JpaRepository<GridGroup, Integer> {

    @Query("SELECT a FROM GridGroup a WHERE a.gridGroupId = ?1")
    GridGroup findGridGroupByGridGroupId(Integer gridGroupId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridGroup a WHERE a.gridGroupId IN ?1")
    void deleteGridGroupByGridGroupIds(Set<Integer> gridGroupIdSet);

}
