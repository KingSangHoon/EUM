// third-party
import { combineReducers } from 'redux';

// project import
import menu from './menu';
import staticVar from './staticVar';

// ==============================|| COMBINE REDUCERS ||============================== //

const reducers = combineReducers({ menu, staticVar });

export default reducers;