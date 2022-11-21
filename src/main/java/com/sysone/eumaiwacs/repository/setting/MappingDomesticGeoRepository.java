package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingDomesticGeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface MappingDomesticGeoRepository extends JpaRepository<MappingDomesticGeo, Integer> {

    @Query("SELECT DISTINCT a.primaryId FROM MappingDomesticGeo a WHERE a.primaryId=?1 AND a.sub2Id is null")
    List<MappingDomesticGeo> findByCustomPrimaryId(Integer primaryId);

    @Query("SELECT DISTINCT a.primaryId FROM MappingDomesticGeo a WHERE a.customPrimaryId=?1 AND a.customSub1Id=?2")
    Set<Integer> findPrimaryIdByCustomPrimaryIdAndSub1Id(Integer customPrimaryId, Integer sub1Id);

    @Query("SELECT DISTINCT a.primaryId FROM MappingDomesticGeo a WHERE a.customPrimaryId=?1")
    Set<Integer> findPrimaryIdByPrimaryId(Integer primaryId);

    @Query("SELECT DISTINCT a.primaryId FROM MappingDomesticGeo a WHERE a.customPrimaryId=?1 AND a.customSub1Id=?2")
    Set<Integer> findPrimaryIdByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id);

    @Query("SELECT a.id, a.customPrimaryId, a.customSub1Id, a.customSub2Id, a.primaryId, a.sub1Id, a.sub2Id FROM MappingDomesticGeo a WHERE a.customPrimaryId=?1 AND a.customSub1Id IS NULL AND a.customSub2Id IS NULL")
    List<Object[]> findSelectedByPrimaryId(Integer primaryId);

    @Query("SELECT a.id, a.customPrimaryId, a.customSub1Id, a.customSub2Id, a.primaryId, a.sub1Id, a.sub2Id FROM MappingDomesticGeo a WHERE a.customPrimaryId=?1 AND a.customSub1Id=?2 AND a.customSub2Id IS NULL")
    List<Object[]> findSelectedByPrimaryIdAndSub1Id(Integer primaryId, Integer sub1Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customPrimaryId = ?1")
    void deleteByCustomPrimaryId(Integer customPrimaryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customSub1Id = ?1")
    void deleteByCustomSub1Id(Integer sub1Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customSub2Id = ?1")
    void deleteByCustomSub2Id(Integer sub2Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customPrimaryId IN ?1")
    void deleteByCustomPrimaryIdSet(Set<Integer> customPrimaryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customSub1Id IN ?1")
    void deleteByCustomSub1IdSet(Set<Integer> sub1Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingDomesticGeo a WHERE a.customSub2Id IN ?1")
    void deleteByCustomSub2IdSet(Set<Integer> sub2Id);

    @Query(value="select distinct primary_id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id is null AND custom_sub2_id is null", nativeQuery=true)
    Set<Integer> primaryIdSetByCustomPrimaryId(Integer primaryId);

    @Query(value="select distinct primary_id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id = ?2 AND custom_sub2_id is null", nativeQuery=true)
    Set<Integer> primaryIdSetByPrimaryIdAndSub1(Integer primaryId, Integer sub1Id);

    @Query(value="select distinct sub1_Id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id = ?2 AND custom_sub2_id is null", nativeQuery=true)
    Set<Integer> sub1IdSetByPrimaryIdAndSub1(Integer primaryId, Integer sub1Id);

    @Query(value="select distinct primary_id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id = ?2 AND custom_sub2_id = ?3", nativeQuery=true)
    Set<Integer> primaryIdSetByPrimaryIdAndSub1AndSub2(Integer primaryId, Integer sub1Id, Integer sub2Id);

    @Query(value="select distinct sub1_Id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id = ?2 AND custom_sub2_id = ?3", nativeQuery=true)
    Set<Integer> sub1IdSetByPrimaryIdAndSub1AndSub2(Integer primaryId, Integer sub1Id, Integer sub2Id);

    @Query(value="select distinct sub2_Id from tbl_mapping_domestic_geo where custom_primary_id=?1 AND custom_sub1_id = ?2 AND custom_sub2_id = ?3", nativeQuery=true)
    Set<Integer> sub2IdSetByPrimaryIdAndSub1AndSub2(Integer primaryId, Integer sub1Id, Integer sub2Id);
}
