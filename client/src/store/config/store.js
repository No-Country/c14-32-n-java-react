import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../reducers/loginReducer/loginSlice';
import clientsReducer from "../reducers/dashboardReducer/clients/clientsSlice";
import roomsReducer from "../reducers/dashboardReducer/rooms/roomsSlice";
import categoriessReducer from "../reducers/dashboardReducer/categories/categoriesSlice";
import bookingSlice from "../reducers/dashboardReducer/booking/bookingSlice"

export const store = configureStore({
  reducer: {
    auth: authReducer,
    customers: clientsReducer,
    rooms: roomsReducer,
    categories: categoriessReducer,
    booking: bookingSlice
  },
});


