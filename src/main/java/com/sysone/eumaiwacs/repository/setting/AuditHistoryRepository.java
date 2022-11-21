package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditHistoryRepository extends JpaRepository<AuditHistory, Integer> {
}
