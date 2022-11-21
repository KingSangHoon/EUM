package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.InfoDomesticPrimary;
import com.sysone.eumaiwacs.entity.setting.InfoDomesticSub1;
import com.sysone.eumaiwacs.entity.setting.InfoDomesticSub2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GeoDomesticService {

//    List<InfoDomesticPrimary> findAllDomesticPrimaryInfo();
//    List<InfoDomesticSub1> findAllDomesticSub1InfoByPrimaryId(Integer primaryId);
//    List<InfoDomesticSub2> findAllDomesticSub2InfoByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id);

//    List<Object> findPartDomesticIpRangeByPrimaryId(Integer primaryId, Integer nextPage, Integer offset);
//    List<Object> findPartDomesticIpRangeByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id, Integer nextPage, Integer offset);
//    List<Object> findPartDomesticIpRangeByPrimaryIdAndSub1IdAndSub2Id(Integer primaryId, Integer sub1Id, Integer sub2Id, Integer nextPage, Integer offset);

//    long countPartDomesticIpRangeByPrimaryId(Integer primaryId);
//    long countPartDomesticIpRangeByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id);
//    long countPartDomesticIpRangeByPrimaryIdAndSub1IdAndSub2Id(Integer primaryId, Integer sub1Id, Integer sub2Id);
//
//    List<Object> findDomesticIpRangeByIp(String ip);

    void deleteInfoDomesticPrimary(Set<Integer> idSet);
    void deleteInfoDomesticSub1(Set<Integer> idSet);
    void deleteInfoDomesticSub2(Set<Integer> idSet);

    Map<String, Object> findInfoDomesticPrimary(Integer primaryId);
    Map<String, Object> findInfoDomesticSub1(Integer primaryId, Integer sub1Id);
    Map<String, Object> findInfoDomesticSub2(Integer primaryId, Integer sub1Id, Integer sub2Id);

    List<InfoDomesticPrimary> findAllForPrimary();

    ArrayList<Object> findAllForPrimaryInSub1(Integer primaryId);
    ArrayList<Object> findAllForPrimaryInSub1AndSub2(Integer primaryId, Integer sub1Id);

    void createDomesticPrimary(Map<String, Object> map);
    void createDomesticSub1(Map<String, Object> domesticMap);
    void createDomesticSub2(Map<String, Object> domesticMap);

    void updateDomesticPrimary(Map<String, Object> domesticMap, Integer primaryId);
    void updateDomesticSub1(Map<String, Object> sub1Map, Integer primaryId, Integer sub1Id);
    void updateDomesticSub2(Map<String, Object> sub2Map, Integer primaryId, Integer sub1Id, Integer sub2Id);

    Boolean createMappingDomesticPublic(Map<String, Object> domesticMap);
    Boolean createMappingDomesticPrivate(Map<String, Object> domesticMap);
    Boolean updateMappingDomesticPrivate(Integer id, Map<String, Object> ipMap);

    void deleteMappingDomestic(Set<Integer> idSet);

    List<Object> findDomesticAll(Integer id,Integer sub1Id,Integer sub2Id);

    Boolean createMultiMappingDomesticPublic(List<Map<String, Object>> ipMap);

    List<Object> findAllDomestic();

    Object findDomestic(Map<String, Object> param);
    Object findIp(Map<String, Object> param);
    Object search(Map<String, Object> param);
}
