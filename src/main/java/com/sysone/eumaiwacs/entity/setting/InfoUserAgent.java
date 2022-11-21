package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@DynamicUpdate @DynamicInsert
@Table(name="tbl_info_user_agent")
@NoArgsConstructor
public class InfoUserAgent {

    @Id
    @SequenceGenerator(name="tbl_info_user_agent_seq", sequenceName="tbl_info_user_agent_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_info_user_agent_seq")
    @Column(name="id",unique=true,nullable=false)
    private Integer id;

    @Column(name="user_agent")
    private String userAgent;

    @Column(name="simple_software_string")
    private String simpleSoftwareString;

    @Column(name="simple_sub_description_string")
    private String simpleSubDescriptionString;

    @Column(name="simple_operating_platform_string")
    private String simpleOperatingPlatformString;

    @Column(name="software")
    private String software;

    @Column(name="software_name")
    private String softwareName;

    @Column(name="software_name_code")
    private String softwareNameCode;

    @Column(name="software_version")
    private String softwareVersion;

    @Column(name="software_version_full")
    private String softwareVersionFull;

    @Column(name="operating_system")
    private String operatingSystem;

    @Column(name="operating_system_name")
    private String operatingSystemName;

    @Column(name="operating_system_name_code")
    private String operatingSystemNameCode;

    @Column(name="operating_system_version")
    private String operatingSystemVersion;

    @Column(name="operating_system_version_full")
    private String operatingSystemVersionFull;

    @Column(name="operating_system_flavour")
    private String operatingSystemFlavour;

    @Column(name="operating_system_flavour_code")
    private String operatingSystemFlavourCode;

    @Column(name="operating_platform")
    private String operatingPlatform;

    @Column(name="operating_platform_code")
    private String operatingPlatformCode;

    @Column(name="operating_platform_code_name")
    private String operatingPlatformCodeName;

    @Column(name="operating_platform_vendor_name")
    private String operatingPlatformVendorName;

    @Column(name="software_type")
    private String softwareType;

    @Column(name="software_sub_type")
    private String softwareSubType;

    @Column(name="software_type_specific")
    private String softwareTypeSpecific;

    @Column(name="hardware_type")
    private String hardwareType;

    @Column(name="hardware_sub_type")
    private String hardwareSubType;

    @Column(name="hardware_sub_sub_type")
    private String hardwareSubSubType;

    @Column(name="hardware_type_specific")
    private String hardwareTypeSpecific;

    @Column(name="layout_engine_name")
    private String layoutEngineName;

}
