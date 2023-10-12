// redux/store.js

import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../reducers/loginReducer/loginSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});


