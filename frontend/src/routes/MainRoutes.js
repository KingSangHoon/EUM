import { lazy } from 'react';

// project import
import Loadable from '../components/Loadable';
import MainLayout from '../layout/MainLayout';

// render - Common
const PageNotFound = Loadable(lazy(() => import('../pages/Common/PageNotFound')));

// render - Dashboard
const DashboardOverviewIndex = Loadable(lazy(() => import('../pages/Dashboard/OverviewIndex')));

// render - Protocol
const ProtocolHttpPagesIndex = Loadable(lazy(() => import('../pages/Protocol/HttpPagesIndex')));
const ProtocolHttpPagesDetail = Loadable(lazy(() => import('../pages/Protocol/HttpPagesDetail')));
const ProtocolHttpUriIndex = Loadable(lazy(() => import('../pages/Protocol/HttpUriIndex')));
const ProtocolHttpUriDetail = Loadable(lazy(() => import('../pages/Protocol/HttpUriDetail')));

// render - Map
const MapWorldIndex = Loadable(lazy(() => import('../pages/Map/WorldIndex')));
const MapDomesticIndex = Loadable(lazy(() => import('../pages/Map/DomesticIndex')));

// render - Setting
const SetGridSettingIndex = Loadable(lazy(() => import('../pages/Setting/GridSettingIndex')));
const SetCompanySettingIndex = Loadable(lazy(() => import('../pages/Setting/CompanySettingIndex')));
const SetUserSettingIndex = Loadable(lazy(() => import('../pages/Setting/UserSettingIndex')));
const SetSensorDeviceIndex = Loadable(lazy(() => import('../pages/Setting/SensorDeviceIndex')));
const SetSslSettingIndex = Loadable(lazy(() => import('../pages/Setting/SslSettingIndex')));
const SetTransactionIndex = Loadable(lazy(() => import('../pages/Setting/TransactionIndex')));
const SetThresholdIndex = Loadable(lazy(() => import('../pages/Setting/SetThresholdIndex')));
const SetApplicationIndex = Loadable(lazy(() => import('../pages/Setting/ApplicationIndex')));
const SetBandIndex = Loadable(lazy(() => import('../pages/Setting/BandIndex')));
const SetAuditHistoryIndex = Loadable(lazy(() => import('../pages/Setting/AuditHistoryIndex')));
const SetAlarmIndex = Loadable(lazy(() => import('../pages/Setting/AlarmIndex')));
const SetCriticalValueIndex = Loadable(lazy(() => import('../pages/Setting/SetCriticalValueIndex')));
// render - Setting(코드 관리)
const SetProtocolIndex = Loadable(lazy(() => import('../pages/Setting/Code/ProtocolIndex')));
const SetUserAgentIndex = Loadable(lazy(() => import('../pages/Setting/Code/UserAgentIndex')));
const SetMimeIndex = Loadable(lazy(() => import('../pages/Setting/Code/MimeIndex')));
const SetHttpIndex = Loadable(lazy(() => import('../pages/Setting/Code/HttpIndex')));
// render - Setting(IP 대역대 관리)
const SetIpBandDomesticIndex = Loadable(lazy(() => import('../pages/Setting/IpBand/DomesticIndex')));
const SetIpBandCountryIndex = Loadable(lazy(() => import('../pages/Setting/IpBand/CountryIndex')));
const SetIpBandIdcIndex = Loadable(lazy(() => import('../pages/Setting/IpBand/IdcIndex')));
const SetIpBandIspIndex = Loadable(lazy(() => import('../pages/Setting/IpBand/IspIndex')));

// render - Popup
const PopupDashboardSetWidget = Loadable(lazy(() => import('../pages/Common/Popup/PopupDashboardSetWidget')));
const PopupSetUserSettingReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetUserSettingReg')));
const PopupSetGridSettingReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetGridSettingReg')));
const PopupSetCompanySettingReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCompanySettingReg')));
const PopupSetCompanySettingMapping = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCompanySettingMapping')));
const PopupSetIpBandDomesticRegRegion = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandDomesticRegRegion')));
const PopupSetIpBandDomesticRegIp = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandDomesticRegIp')));
const PopupSetIpBandCountryRegRegion = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandCountryRegRegion')));
const PopupSetIpBandCountryRegIp = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandCountryRegIp')));
const PopupSetIpBandIdcRegRegion = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandIdcRegRegion')));
const PopupSetIpBandIdcRegIp = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandIdcRegIp')));
const PopupSetIpBandIspRegRegion = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandIspRegRegion')));
const PopupSetIpBandIspRegIp = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetIpBandIspRegIp')));
const PopupSetSensorDeviceReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetSensorDeviceReg')));
const PopupSetSslSettingReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetSslSettingReg')));
const PopupSetHttpDetailReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCodeHttpDetailReg')));
const PopupSetMimeDetailReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCodeMimeDetailReg')));
const PopupSetTransactionReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetTransactionReg')));
const PopupSetApplicationReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetApplicationReg')));
const PopupSetBandReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetBandReg')));
const PopupSetAlarmReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetAlarmReg')));
const PopupSetThresholdTabPolicyReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetThresholdTabPolicyReg')));
const PopupSetProtocolDetailReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCodeProtocolDetailReg')));
const PopupSetCriticalValueTabPolicyReg = Loadable(lazy(() => import('../pages/Common/Popup/PopupSetCriticalValueTabPolicyReg')));
// render - Popup(search)
const PopupSearchFormResourcesSingle = Loadable(lazy(() => import('../pages/Common/Popup/PopupSearchFormResourcesSingle')));
const PopupSearchFormResourcesMulti = Loadable(lazy(() => import('../pages/Common/Popup/PopupSearchFormResourcesMulti')));
const PopupSearchFormZone = Loadable(lazy(() => import('../pages/Common/Popup/PopupSearchFormZone')));
const PopupSearchFormHttp = Loadable(lazy(() => import('../pages/Common/Popup/PopupSearchFormHttp')));
const PopupSearchFormUser = Loadable(lazy(() => import('../pages/Common/Popup/PopupSearchFormUser')));
// render - Popup(공통)
const PopupCommonChartLatency = Loadable(lazy(() => import('../pages/Common/Popup/PopupCommonChartLatency')));
const PopupCommonRegExp = Loadable(lazy(() => import('../pages/Common/Popup/PopupCommonRegExp')));
const PopupCommonExport = Loadable(lazy(() => import('../pages/Common/Popup/PopupCommonExport')));


// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
    path: '/',
    element: <MainLayout />,
    children: [
        {
            path: '*',
            element: <PageNotFound />
        },
        {
            path: '/',
            element: <DashboardOverviewIndex />
        },
        {
            path: '/dashboard',
            children: [
                {
                    path: 'overview',
                    element: <DashboardOverviewIndex />
                }
            ]
        },
        {
            path: '/protocol',
            children: [
                {
                    path: 'http/pages',
                    element: <ProtocolHttpPagesIndex />
                },
                {
                    path: 'http/pages/detail',
                    element: <ProtocolHttpPagesDetail />
                },
                {
                    path: 'http/uri',
                    element: <ProtocolHttpUriIndex />
                },
                {
                    path: 'http/uri/detail',
                    element: <ProtocolHttpUriDetail />
                },
            ]
        },
        {
            path: '/map',
            children: [
                {
                    path: 'world',
                    element: <MapWorldIndex />
                },
                {
                    path: 'domestic',
                    element: <MapDomesticIndex />
                }
            ]
        },
        {
            path: '/setting',
            children: [
                {
                    path: 'GridSetting',
                    element: <SetGridSettingIndex />
                },
                {
                    path: 'companySetting',
                    element: <SetCompanySettingIndex />
                },
                {
                    path: 'userSetting',
                    element: <SetUserSettingIndex />
                },
                {
                    path: 'sensorDevice',
                    element: <SetSensorDeviceIndex />
                },
                {
                    path: 'sslSetting',
                    element: <SetSslSettingIndex />
                },
                {
                    path: 'transaction',
                    element: <SetTransactionIndex />
                },
                {
                    path: 'threshold',
                    element: <SetThresholdIndex />
                },
                {
                    path: 'alarm',
                    element: <SetAlarmIndex />
                },
                {
                    path: 'application',
                    element: <SetApplicationIndex />
                },
                {
                    path: 'band',
                    element: <SetBandIndex />
                },
                {
                    path: 'auditHistory',
                    element: <SetAuditHistoryIndex />
                },
                {
                    path: 'criticalValue',
                    element: <SetCriticalValueIndex />
                },
                /* 코드 관리 */
                {
                    path: 'code/protocol',
                    element: <SetProtocolIndex />
                },
                {
                    path: 'code/userAgent',
                    element: <SetUserAgentIndex />
                },
                {
                    path: 'code/mime',
                    element: <SetMimeIndex />
                },
                {
                    path: 'code/http',
                    element: <SetHttpIndex />
                },
                /* IP 대역대 관리 */
                {
                    path: 'ipBand/domestic',
                    element: <SetIpBandDomesticIndex />
                },
                {
                    path: 'ipBand/Country',
                    element: <SetIpBandCountryIndex />
                },
                {
                    path: 'ipBand/idc',
                    element: <SetIpBandIdcIndex />
                },
                {
                    path: 'ipBand/isp',
                    element: <SetIpBandIspIndex />
                }
            ]
        },
        {
            path: '/popup',
            children: [
                /* Dashboard */
                {
                    path: 'dashboard/setWidget',
                    element: <PopupDashboardSetWidget />
                },
                /* 환경설정 */
                {
                    path: 'setting/userSetting/reg/:id',
                    element: <PopupSetUserSettingReg />
                },
                {
                    path: 'setting/gridSetting/reg/:id',
                    element: <PopupSetGridSettingReg />
                },
                {
                    path: 'setting/companySetting/reg/:id',
                    element: <PopupSetCompanySettingReg />
                },
                {
                    path: 'setting/companySetting/mapping/:id',
                    element: <PopupSetCompanySettingMapping />
                },
                {
                    path: 'setting/sensorDevice/reg/:id',
                    element: <PopupSetSensorDeviceReg />
                },
                {
                    path: 'setting/sslSetting/reg/:id',
                    element: <PopupSetSslSettingReg />
                },
                {
                    path: 'setting/application/reg/:id',
                    element: <PopupSetApplicationReg />
                },
                {
                    path: 'setting/band/reg/:group/:id',
                    element: <PopupSetBandReg />
                },
                {
                    path: 'setting/alarm/reg/:id',
                    element: <PopupSetAlarmReg />
                },
                {
                    path: 'setting/transaction/reg/:id',
                    element: <PopupSetTransactionReg />
                },
                {
                    path: 'setting/threshold/policy/reg/:id',
                    element: <PopupSetThresholdTabPolicyReg />
                },
                {
                    path: 'setting/ipBand/domestic/reg/region/:id',
                    element: <PopupSetIpBandDomesticRegRegion />
                },
                {
                    path: 'setting/ipBand/domestic/reg/ip/:id',
                    element: <PopupSetIpBandDomesticRegIp />
                },
                {
                    path: 'setting/ipBand/country/reg/region/:id',
                    element: <PopupSetIpBandCountryRegRegion />
                },
                {
                    path: 'setting/ipBand/country/reg/ip/:id',
                    element: <PopupSetIpBandCountryRegIp />
                },
                {
                    path: 'setting/ipBand/idc/reg/region/:id',
                    element: <PopupSetIpBandIdcRegRegion />
                },
                {
                    path: 'setting/ipBand/idc/reg/ip/:id',
                    element: <PopupSetIpBandIdcRegIp />
                },
                {
                    path: 'setting/ipBand/isp/reg/region/:id',
                    element: <PopupSetIpBandIspRegRegion />
                },
                {
                    path: 'setting/ipBand/isp/reg/ip/:id',
                    element: <PopupSetIpBandIspRegIp />
                },
                {
                    path: 'setting/code/http/reg/:id',
                    element: <PopupSetHttpDetailReg />
                },
                {
                    path: 'setting/code/protocol/reg/:id',
                    element: <PopupSetProtocolDetailReg />
                },
                {
                    path: 'setting/code/mime/reg/:id',
                    element: <PopupSetMimeDetailReg />
                },
                {
                    path: 'setting/criticalValue/policy/reg/:id',
                    element: <PopupSetCriticalValueTabPolicyReg />
                },
                /* 검색폼 */
                {
                    path: 'search/resources/single',
                    element: <PopupSearchFormResourcesSingle />
                },
                {
                    path: 'search/resources/multi',
                    element: <PopupSearchFormResourcesMulti />
                },
                {
                    path: 'search/zone',
                    element: <PopupSearchFormZone />
                },
                {
                    path: 'search/http',
                    element: <PopupSearchFormHttp />
                },
                {
                    path: 'search/user',
                    element: <PopupSearchFormUser />
                },
                /* 공통 */
                {
                    path: 'common/xrange',
                    element: <PopupCommonChartLatency />
                },
                {
                    path: 'common/regExp',
                    element: <PopupCommonRegExp />
                },
                {
                    path: 'common/export',
                    element: <PopupCommonExport />
                },
            ]
        }
    ]
};

export default MainRoutes;