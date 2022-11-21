package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingContinentCountryGeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface MappingContinentCountryGeoRepository extends JpaRepository<MappingContinentCountryGeo,Integer> {

    List<MappingContinentCountryGeo> findByCustomContinentId(Integer continentId);

    @Query("SELECT a from MappingContinentCountryGeo a WHERE a.customContinentId = ?1 and a.customCountryId = ?2")
    List<MappingContinentCountryGeo> findByCustomContinentIdAndCountryId(Integer continentId, Integer countryId);

    @Query("SELECT DISTINCT a.continentId FROM MappingContinentCountryGeo a WHERE a.customContinentId = ?1")
    Set<Integer> findByCustomContinentIdSet(Integer continentId);

    @Query("SELECT DISTINCT a.continentId FROM MappingContinentCountryGeo a WHERE a.customCountryId = ?1")
    Set<Integer> findByCountryidSet(Integer countryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingContinentCountryGeo a WHERE a.customContinentId IN ?1")
    void deleteByCustomContinentIdSet(Set<Integer> customContinentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingContinentCountryGeo a WHERE a.customContinentId = ?1")
    void deleteByCustomContinentId(Integer customContinentId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MappingContinentCountryGeo a WHERE a.customCountryId IN ?1")
    void deleteByCustomCountryIdSet(Set<Integer> customCountryId);

}
