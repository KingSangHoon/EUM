package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface InfoCountryRepository extends JpaRepository<InfoCountry, Integer> {

    @Query("SELECT a FROM InfoCountry a WHERE a.continentCode=?1")
    List<InfoCountry> findAllByContinentCode(String code);

    @Query("SELECT a FROM InfoCountry a WHERE a.continentCode=?1")
    HashSet<String> findCountryByContinentCode(String continentCode);

    @Query("SELECT a.countryCode, a.countryName, a.countryId, a.modifyFlag FROM InfoCountry a WHERE a.continentCode LIKE ?1")
    List<Object[]> findByContinentCode(String continentCode);

    @Query("SELECT a FROM InfoCountry a WHERE a.countryCode=?1")
    List<InfoCountry> checkCountryCode(String countryCode);

    @Transactional
    @Modifying
    @Query("DELETE FROM InfoCountry a WHERE a.countryId IN ?1")
    void deleteCountry(Set<Integer> idSet);

    @Query("SELECT a FROM InfoCountry a WHERE a.countryId=?1")
    InfoCountry findByCountryId(Integer countryId);
}
