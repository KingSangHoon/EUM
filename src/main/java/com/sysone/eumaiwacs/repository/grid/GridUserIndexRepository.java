package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridUserIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridUserIndexRepository extends JpaRepository<GridUserIndex, Integer> {

    @Query("SELECT a FROM GridUserIndex a WHERE a.userId = ?1")
    GridUserIndex findGridUserIndexByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridUserIndex a WHERE a.userId IN ?1")
    void deleteGridUsersByUserId(Set<Integer> userIdSet);
}
