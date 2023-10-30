import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../reducers/loginReducer/loginSlice";


import roomsReducer from "../reducers/dashboardReducer/rooms/roomsSlice";
import roomsAddReducer from "../reducers/dashboardReducer/rooms/roomsAddSlice";

import bookingSlice from "../reducers/dashboardReducer/booking/bookingSlice";

// TODO: ORDERARLO!!! >:V
import categoriessReducer from "../reducers/dashboardReducer/categories/categoriesSlice";
import categoriessAddReducer from "../reducers/dashboardReducer/categories/categoriesAddSlice";
import categoriessDeleteReducer from "../reducers/dashboardReducer/categories/categoriesDeleteSlice";
import categoriessEditReducer from "../reducers/dashboardReducer/categories/categoriesEditSlice";

// TODO: ORDENAR ESTO!!! >:V
import clientsReducer from "../reducers/dashboardReducer/clients/clientsSlice";
import clientsAddReducer from "../reducers/dashboardReducer/clients/clientAddSlice";
import clientsDeleteReducer from "../reducers/dashboardReducer/clients/clientDeleteSlice";
import clientsEditReducer from "../reducers/dashboardReducer/clients/clientEditSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    //
    customers: clientsReducer,
    addcustomer: clientsAddReducer,
    deletecustomer: clientsDeleteReducer,
    editcustomer: clientsEditReducer,
    //
    rooms: roomsReducer,
    addrooms: roomsAddReducer,
    //
    categories: categoriessReducer,
    addcategories: categoriessAddReducer,
    deletecategories: categoriessDeleteReducer,
    editcategories: categoriessEditReducer,
    // 
    booking: bookingSlice,
  },
});
