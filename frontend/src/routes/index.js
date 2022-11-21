import { useEffect } from 'react';
import { useRoutes, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';

// types
import { activeItem } from '../store/reducers/menu';

// project import
import LoginRoutes from './LoginRoutes';
import MainRoutes from './MainRoutes';

// common
import { gridApiObj } from '../lib/common';

// ==============================|| ROUTING RENDER ||============================== //

export default function ThemeRoutes() {
    const location = useLocation();
    const dispatch = useDispatch();

    const loginedUser = localStorage.getItem('authenticatedUser');
    const targetRoute = loginedUser ? [MainRoutes] : [LoginRoutes];

    useEffect(() => {
        // Empty Grid Api Object
        Object.keys(gridApiObj).forEach(key => {
            delete gridApiObj[key];
        });

        // Side Menu Active
        if (!location.pathname.includes("popup/")) {
            const detailParam = location.pathname.split("/");
            let menuNav = [];

            detailParam.forEach(str => {
                if (str) {
                    menuNav.push(str);
                }
            });

            const menuId = menuNav.length === 0 ? 'dashboard-overview' : menuNav.join("-");

            dispatch(activeItem({ openItem: [menuId], openNav: menuNav }));
        }
    }, [location]);

    return useRoutes(targetRoute);
}