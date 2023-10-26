import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../reducers/loginReducer/loginSlice';
import clientsReducer from "../reducers/dashboardReducer/clients/clientsSlice";
import roomsReducer from "../reducers/dashboardReducer/rooms/roomsSlice";
import categoriessReducer from "../reducers/dashboardReducer/categories/categoriesSlice";
import bookingSlice from "../reducers/dashboardReducer/booking/bookingSlice"
import clientsAddReducer from "../reducers/dashboardReducer/clients/clientAddSlice";
import clientsDeleteReducer from "../reducers/dashboardReducer/clients/clientDeleteSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    customers: clientsReducer,
    addcustomer: clientsAddReducer,
    deletecustomer: clientsDeleteReducer,
    rooms: roomsReducer,
    categories: categoriessReducer,
    booking: bookingSlice
  },
});


