package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findCompanyByCompanyId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Company c WHERE c.companyId IN ?1")
    void deleteCompanyByCompantIdSet(Set<Integer> companyIdSet);

}
