// assets
import { FundProjectionScreenOutlined, SettingOutlined, GlobalOutlined, DesktopOutlined } from '@ant-design/icons';

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //
const menuItem = [{
    id: 'group-dashboard',
    title: 'Dashboard',
    type: 'group',
    icon: FundProjectionScreenOutlined,
    collpase: false,
    children: [
        {
            id: 'dashboard-overview',
            title: 'Overview',
            type: 'item',
            url: '/dashboard/overview',
            breadcrumbs: false
        }
    ]
},
{
    id: 'group-protocol',
    title: 'Protocol',
    type: 'group',
    icon: DesktopOutlined,
    collpase: false,
    children: [
        {
            id: 'protocol-http',
            title: 'HTTP',
            type: 'subGroup',
            collpase: false,
            children: [
                {
                    id: 'protocol-http-pages',
                    title: 'Pages',
                    type: 'item',
                    url: '/protocol/http/pages',
                    breadcrumbs: false
                },
                {
                    id: 'protocol-http-uri',
                    title: 'URI',
                    type: 'item',
                    url: '/protocol/http/uri',
                    breadcrumbs: false
                }
            ],
            breadcrumbs: false
        }
    ]
},
{
    id: 'group-map',
    title: 'Map',
    type: 'group',
    icon: GlobalOutlined,
    collpase: false,
    children: [
        {
            id: 'map-world',
            title: 'World',
            type: 'item',
            url: '/map/world',
            breadcrumbs: false
        },
        {
            id: 'map-domestic',
            title: '국내',
            type: 'item',
            url: '/map/domestic',
            breadcrumbs: false
        },
    ]
},
{
    id: 'group-setting',
    title: '환경설정',
    type: 'group',
    icon: SettingOutlined,
    collpase: false,
    children: [
        {
            id: 'setting-gridSetting',
            title: '그리드 설정',
            type: 'item',
            url: '/setting/gridSetting',
            breadcrumbs: false
        },
        {
            id: 'setting-companySetting',
            title: '고객사 설정',
            type: 'item',
            url: '/setting/companySetting',
            breadcrumbs: false
        },
        {
            id: 'setting-userSetting',
            title: '사용자 설정',
            type: 'item',
            url: '/setting/userSetting',
            breadcrumbs: false
        },
        {
            id: 'setting-sensorDevice',
            title: 'Sensor Device 설정',
            type: 'item',
            url: '/setting/sensorDevice',
            breadcrumbs: false
        },
        {
            id: 'setting-sslSetting',
            title: 'SSL 설정',
            type: 'item',
            url: '/setting/sslSetting',
            breadcrumbs: false
        },
        {
            id: 'setting-transaction',
            title: '트랜잭션 설정',
            type: 'item',
            url: '/setting/transaction',
            breadcrumbs: false
        },
        {
            id: 'setting-threshold',
            title: '레벨 설정',
            type: 'item',
            url: '/setting/threshold',
            breadcrumbs: false
        },
        {
            id: 'setting-alarm',
            title: '알림 설정',
            type: 'item',
            url: '/setting/alarm',
            breadcrumbs: false
        },
        {
            id: 'setting-application',
            title: '어플리케이션 설정',
            type: 'item',
            url: '/setting/application',
            breadcrumbs: false
        },
        {
            id: 'setting-band',
            title: '대역 설정',
            type: 'item',
            url: '/setting/band',
            breadcrumbs: false
        },
        {
            id: 'setting-criticalValue',
            title: '임계치 설정',
            type: 'item',
            url: '/setting/criticalValue',
            breadcrumbs: false
        },
        {
            id: 'setting-auditHistory',
            title: '감사 이력',
            type: 'item',
            url: '/setting/auditHistory',
            breadcrumbs: false
        },
        /* 코드 관리 */
        {
            id: 'setting-code',
            title: '코드 관리',
            type: 'subGroup',
            collpase: false,
            children: [
                {
                    id: 'setting-code-protocol',
                    title: 'Protocol',
                    type: 'item',
                    url: '/setting/code/protocol',
                    breadcrumbs: false
                },
                {
                    id: 'setting-code-userAgent',
                    title: 'User Agent',
                    type: 'item',
                    url: '/setting/code/userAgent',
                    breadcrumbs: false
                },
                {
                    id: 'setting-code-mime',
                    title: 'MIME',
                    type: 'item',
                    url: '/setting/code/mime',
                    breadcrumbs: false
                },
                {
                    id: 'setting-code-http',
                    title: 'HTTP',
                    type: 'item',
                    url: '/setting/code/http',
                    breadcrumbs: false
                }
            ],
            breadcrumbs: false
        },
        /* IP 대역대 관리 */
        {
            id: 'setting-ipBand',
            title: 'IP 대역대 관리',
            type: 'subGroup',
            collpase: false,
            children: [
                {
                    id: 'setting-ipBand-isp',
                    title: 'ISP',
                    type: 'item',
                    url: '/setting/ipBand/isp',
                    breadcrumbs: false
                },
                {
                    id: 'setting-ipBand-idc',
                    title: 'IDC',
                    type: 'item',
                    url: '/setting/ipBand/idc',
                    breadcrumbs: false
                },
                {
                    id: 'setting-ipBand-domestic',
                    title: '국내-지역별',
                    type: 'item',
                    url: '/setting/ipBand/domestic',
                    breadcrumbs: false
                },
                {
                    id: 'setting-ipBand-country',
                    title: '대륙-국가별',
                    type: 'item',
                    url: '/setting/ipBand/country',
                    breadcrumbs: false
                }
            ],
            breadcrumbs: false
        },
    ]
}];

// ==============================|| MENU ITEMS ||============================== //
const menuItems = {
    items: menuItem
};

export default menuItems;