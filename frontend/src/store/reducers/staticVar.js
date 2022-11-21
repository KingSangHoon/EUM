// types
import { createSlice } from '@reduxjs/toolkit';

// initial state
const initialState = {
    responseCode: []
};

// ==============================|| SLICE - MENU ||============================== //

const staticVar = createSlice({
    name: 'staticVar',
    initialState,
    reducers: {
        setResponseCode(state, action) {
            state.responseCode = action.payload;
        }
    }
});

export default staticVar.reducer;

export const { setResponseCode } = staticVar.actions;