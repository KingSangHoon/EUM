package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.NdpiProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NdpiProtocolRepository extends JpaRepository<NdpiProtocol,Integer> {

    @Query("SELECT a FROM NdpiProtocol a WHERE a.ndpiProtocolNumber=?1")
    NdpiProtocol findId(Integer ndpiProtocolAppNumber);
}
