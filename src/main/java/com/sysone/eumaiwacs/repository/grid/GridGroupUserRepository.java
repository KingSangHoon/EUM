package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridGroupUserRepository extends JpaRepository<GridGroupUser, Integer> {

    @Query("SELECT a FROM GridGroupUser a WHERE a.gridGroupId = ?1")
    GridGroupUser findGridGroupUsersByGridGroupId(Integer gridGroupId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridGroupUser a WHERE a.gridGroupId IN ?1")
    void deleteGridGroupUserByGridGroupIdSet(Set<Integer> gridGrouIdSet);


}
