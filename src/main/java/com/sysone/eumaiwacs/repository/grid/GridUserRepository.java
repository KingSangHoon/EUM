package com.sysone.eumaiwacs.repository.grid;

import com.sysone.eumaiwacs.entity.grid.GridUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface GridUserRepository extends JpaRepository<GridUser, Integer> {

    @Query("SELECT a FROM GridUser a WHERE a.userId = ?1")
    GridUser findGridUserByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM GridUser a WHERE a.userId IN ?1")
    void deleteGridUsersByUserId(Set<Integer> userIdSet);

}
