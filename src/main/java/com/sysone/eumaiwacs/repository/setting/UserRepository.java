package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.loginId = ?1")
    User findLoginInfo(String loginId);

    @Query("SELECT u FROM User u WHERE (u.deleted = false OR u.deleted is NULL) ORDER BY u.userId")
    List<User> findAllUser();

    User findUserByUserId(Integer userId);

    @Query("SELECT u FROM User u WHERE (u.deleted = false OR u.deleted is NULL) AND u.companyId = ?1 ORDER BY u.userId ")
    List<User> findUserByCompanyId(Integer companyId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.deleted = true WHERE u.userId IN ?1")
    void deleteUserByIdSet(Set<Integer> userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.active = ?1 WHERE u.userId IN ?2")
    void activeUserByIdSet(Boolean flag, Set<Integer> userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.companyId = 1 WHERE u.companyId IN ?1")
    void deleteMappingUserByCompanyIdSet(Set<Integer> companyIdSet);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.companyId = 1 WHERE u.companyId = ?1")
    void deleteMappingUserByCompanyId(Integer companyId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.companyId = ?1 WHERE u.userId IN ?2")
    void updateMappingUserByUserIdSet(Integer companyId, Set<Integer> companyIdSet);
}
