package com.sysone.eumaiwacs.repository.setting;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sysone.eumaiwacs.entity.setting.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MappingDomesticRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    QMappingDomestic mappingDomestic = QMappingDomestic.mappingDomestic;

    public QueryResults<MappingDomestic> find(Map<String, Object> param) {

        String category = (String) param.get("category");
        Integer primaryId = param.get("primaryId") == null ? null : (Integer) param.get("primaryId");
        Integer sub1Id = (Integer) param.get("sub1Id");
        Integer sub2Id = (Integer) param.get("sub2Id");

        Integer limit = (Integer) param.get("limit");
        Integer offset = (Integer) param.get("offset");

        QueryResults<MappingDomestic> queryResults = null;

        if(category.equals("primary") && primaryId != null) {
            queryResults = queryFactory.selectFrom(mappingDomestic)
                    .where(mappingDomestic.primaryId.eq(primaryId))
                    .orderBy(mappingDomestic.id.asc())
                    .limit(limit)
                    .offset(offset)
                    .fetchResults();
        }else if(category.equals("sub1")) {
            queryResults = queryFactory.selectFrom(mappingDomestic)
                    .where(mappingDomestic.primaryId.eq(primaryId).and(mappingDomestic.sub1Id.eq(sub1Id)))
                    .orderBy(mappingDomestic.id.asc())
                    .limit(limit)
                    .offset(offset)
                    .fetchResults();
        } else if(category.equals("sub2")) {
            queryResults = queryFactory.selectFrom(mappingDomestic)
                    .where(mappingDomestic.primaryId.eq(primaryId).and(mappingDomestic.sub1Id.eq(sub1Id)).and(mappingDomestic.sub2Id.eq(sub2Id)))
                    .orderBy(mappingDomestic.id.asc())
                    .limit(limit)
                    .offset(offset)
                    .fetchResults();
        }
        return queryResults;
    }

    public QueryResults<MappingDomestic> search(Long ipNum, Integer limit, Integer offset) {

        QueryResults<MappingDomestic> queryResults = queryFactory.selectFrom(mappingDomestic)
                .where(mappingDomestic.startIpNum.loe(ipNum).and(mappingDomestic.endIpNum.goe(ipNum)))
                .orderBy(mappingDomestic.id.desc())
                .limit(limit)
                .offset(offset)
                .fetchResults();

        return queryResults;
    }


}
