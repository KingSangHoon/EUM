package com.sysone.eumaiwacs.repository.setting;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.QUser;
import com.sysone.eumaiwacs.entity.setting.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QUser user = QUser.user;

    public List<User> searchUser(String loginId, String username, String email, String phoneNumber, List<String> roles, Boolean active) {
        List<User> queryResult = queryFactory
                .selectFrom(user)
                .where(loginIdContains(loginId), usernameContains(username), emailContains(email), phoneNumberContains(phoneNumber), rolesIn(roles), activeEq(active),
                        (user.deleted.eq(false).or(user.deleted.isNull())))
                .orderBy(user.userId.asc())
                .fetch();

        for(User user : queryResult) {
            user.setUsername(Util.latin1ToUtf8(user.getUsername()));
        }
        return queryResult;
    }

    private BooleanExpression loginIdContains(String loginId) {
        return loginId != null? user.loginId.contains(loginId) : null;
    }

    private BooleanExpression usernameContains(String username) {
        return username != null? user.username.contains(username) : null;
    }

    private BooleanExpression emailContains(String email) {
        return email != null? user.email.contains(email) : null;
    }

    private BooleanExpression phoneNumberContains(String phoneNumber) {
        return phoneNumber != null? user.loginId.contains(phoneNumber) : null;
    }

    private BooleanExpression rolesIn(List<String> roles) {
        return roles != null ? user.role.in(roles) : null;
    }

    private BooleanExpression activeEq(Boolean active) {
        return active != null? user.active.eq(active) : null;
    }

}
