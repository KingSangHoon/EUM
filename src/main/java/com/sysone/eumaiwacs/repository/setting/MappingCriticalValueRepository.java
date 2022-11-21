package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.MappingCriticalValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MappingCriticalValueRepository extends JpaRepository<MappingCriticalValue, Integer> {

    List<MappingCriticalValue> findAllByDefaultId(Integer id);
}
