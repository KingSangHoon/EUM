package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoMimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface InfoMimeTypeRepository extends JpaRepository<InfoMimeType, Integer> {

    @Query("SELECT DISTINCT a.type FROM InfoMimeType a GROUP BY a.type ")
    List<Object[]> findType();

    @Query("SELECT a FROM InfoMimeType a WHERE a.type=?1 ")
    List<InfoMimeType> findMimeTypeByName(String type);

    @Query("SELECT a FROM InfoMimeType a WHERE a.id=?1 ")
    InfoMimeType findMimeTypeByid(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM InfoMimeType a WHERE a.id In ?1")
    void deleteMime(Set<Integer> idSet);

}
