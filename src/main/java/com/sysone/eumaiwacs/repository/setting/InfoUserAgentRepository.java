package com.sysone.eumaiwacs.repository.setting;

import com.sysone.eumaiwacs.entity.setting.InfoUserAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoUserAgentRepository extends JpaRepository<InfoUserAgent, Integer> {
    
    @Query("SELECT a.softwareName FROM InfoUserAgent a WHERE a.softwareName IS NOT NULL GROUP BY a.softwareName ORDER BY a.softwareName")
    public List<String> getSoftwareNameGroup();

    @Query("SELECT DISTINCT a.software, a.softwareVersion FROM InfoUserAgent a WHERE a.softwareName = ?1 AND a.software IS NOT NULL ORDER BY a.software")
    public List<Object[]> getInfoUserAgentBySoftwareName(String softwareName);

    @Query("SELECT DISTINCT a.software, a.softwareVersion FROM InfoUserAgent a WHERE a.softwareName = ?1 AND a.userAgent like %?2% AND a.software IS NOT NULL ORDER BY a.software")
    public List<Object[]> getCustomUserAgentBySoftwareName(String softwareName, String agentName);

    @Query("SELECT a.operatingSystemName FROM InfoUserAgent a WHERE a.operatingSystemName IS NOT NULL GROUP BY a.operatingSystemName ORDER BY a.operatingSystemName")
    public List<String> getOperatingSystemNameGroup();

    @Query("SELECT DISTINCT a.operatingSystem, a.operatingSystemVersion FROM InfoUserAgent a WHERE a.operatingSystemName = ?1 AND a.operatingSystem IS NOT NULL ORDER BY a.operatingSystem")
    public List<Object[]> getInfoUserAgentByOperatingSystemName(String operatingSystemName);

    @Query("SELECT DISTINCT a.operatingSystem, a.operatingSystemVersion FROM InfoUserAgent a WHERE a.operatingSystemName = ?1 AND a.userAgent like %?2% AND a.operatingSystem IS NOT NULL ORDER BY a.operatingSystem")
    public List<Object[]> getCustomUserAgentByOperatingSystemName(String operatingSystemName, String agentName);

    @Query("SELECT DISTINCT a.operatingPlatform, a.operatingPlatformCode, a.operatingPlatformCodeName, a.operatingPlatformVendorName FROM InfoUserAgent a WHERE a.operatingPlatform IS NOT NULL ORDER BY a.operatingPlatform")
    public List<Object[]> getOperatingPlatformGroup();

    @Query("SELECT DISTINCT a.operatingPlatform, a.operatingPlatformCode, a.operatingPlatformCodeName, a.operatingPlatformVendorName FROM InfoUserAgent a WHERE a.operatingPlatform IS NOT NULL AND a.userAgent like %?1% ORDER BY a.operatingPlatform")
    public List<Object[]> getCustomOperatingPlatformGroup(String agentName);

    @Query("SELECT a.softwareType FROM InfoUserAgent a WHERE a.softwareType IS NOT NULL GROUP BY a.softwareType ORDER BY a.softwareType")
    public List<String> getSoftwareTypeGroup();

    @Query("SELECT DISTINCT a.softwareSubType, a.softwareTypeSpecific FROM InfoUserAgent a WHERE a.softwareType = ?1 AND a.softwareSubType IS NOT NULL ORDER BY a.softwareSubType")
    public List<Object[]> getInfoUserAgentBySoftwareType(String operatingSystemName);

    @Query("SELECT DISTINCT a.softwareSubType, a.softwareTypeSpecific FROM InfoUserAgent a WHERE a.softwareType = ?1 AND a.userAgent like %?2% AND a.softwareSubType IS NOT NULL ORDER BY a.softwareSubType")
    public List<Object[]> getInfoUserAgentBySoftwareType(String operatingSystemName, String agentName);

    @Query("SELECT a.hardwareType FROM InfoUserAgent a WHERE a.hardwareType IS NOT NULL GROUP BY a.hardwareType ORDER BY a.hardwareType")
    public List<String> getHardwareTypeGroup();

    @Query("SELECT DISTINCT a.hardwareSubType, a.hardwareSubSubType FROM InfoUserAgent a WHERE a.hardwareType = ?1 AND a.hardwareSubType IS NOT NULL ORDER BY a.hardwareSubType")
    public List<Object[]> getInfoUserAgentByHardwareType(String operatingSystemName);

    @Query("SELECT DISTINCT a.hardwareSubType, a.hardwareSubSubType FROM InfoUserAgent a WHERE a.hardwareType = ?1 AND a.userAgent like %?2% AND a.hardwareSubType IS NOT NULL ORDER BY a.hardwareSubType")
    public List<Object[]> getCustomUserAgentByHardwareType(String operatingSystemName, String agentName);

    @Query("SELECT a.layoutEngineName FROM InfoUserAgent a WHERE a.layoutEngineName IS NOT NULL GROUP BY a.layoutEngineName ORDER BY a.layoutEngineName")
    public List<String> getLayoutEngineNameGroup();

    @Query("SELECT a.layoutEngineName FROM InfoUserAgent a WHERE a.layoutEngineName IS NOT NULL AND a.userAgent like %?1% GROUP BY a.layoutEngineName ORDER BY a.layoutEngineName")
    public List<String> getCustomLayoutEngineNameGroup(String agentName);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.software = ?1 ORDER BY a.id")
    public List<InfoUserAgent> findInfoUserAgentBySoftware(String software);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.operatingSystem = ?1 ORDER BY a.id")
    public List<InfoUserAgent> findInfoUserAgentByOperatingSystem(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.operatingPlatform = ?1 ORDER BY a.id")
    public List<InfoUserAgent> findInfoUserAgentByOperatingPlatform(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.softwareSubType = ?1 ORDER BY a.id  ")
    public List<InfoUserAgent> findInfoUserAgentBySoftwareType(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.hardwareSubType = ?1 ORDER BY a.id  ")
    public List<InfoUserAgent> findInfoUserAgentByHardwareType(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.layoutEngineName = ?1 ORDER BY a.id  ")
    public List<InfoUserAgent> findInfoUserAgentByLayoutEngineName(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.software like %?1% ORDER BY a.id")
    public List<InfoUserAgent> findCustomUserAgentBySoftware(String software);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.operatingSystem like %?1% ORDER BY a.id")
    public List<InfoUserAgent> findCustomUserAgentByOperatingSystem(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.operatingPlatform like %?1% ORDER BY a.id")
    public List<InfoUserAgent> findCustomUserAgentByOperatingPlatform(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.softwareSubType like %?1% ORDER BY a.id  ")
    public List<InfoUserAgent> findCustomUserAgentBySoftwareType(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.hardwareSubType like %?1% ORDER BY a.id  ")
    public List<InfoUserAgent> findCustomUserAgentByHardwareType(String operatingSystem);

    @Query("SELECT a FROM InfoUserAgent a WHERE a.layoutEngineName like %?1% ORDER BY a.id  ")
    public List<InfoUserAgent> findCustomUserAgentByLayoutEngineName(String operatingSystem);

    @Query("SELECT a.id, a.userAgent FROM InfoUserAgent a WHERE a.userAgent like %?1% ORDER BY a.id")
    List<Object[]> getCustomUserAgentByKeyword(String schUserAgent);

    @Query("SELECT count(a) FROM InfoUserAgent a WHERE a.userAgent like %?1%")
    public long findCntAllAgent(String schUserAgent);

    @Query(value="SELECT id, user_agent FROM tbl_info_user_agent WHERE user_agent like %?1% ORDER BY id DESC LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Object[]> findAgent(String schUserAgent, Integer nextPage, Integer offset);
}
