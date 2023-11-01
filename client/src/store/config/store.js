import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../reducers/loginReducer/loginSlice";

// TODO: ORDENAR
import roomsReducer from "../reducers/dashboardReducer/rooms/roomsSlice";
import roomsAddReducer from "../reducers/dashboardReducer/rooms/roomsAddSlice";
import roomsDeleteReducer from "../reducers/dashboardReducer/rooms/roomsDeleteSlice";
import roomsEditReducer from "../reducers/dashboardReducer/rooms/roomsEditSlice";

// TODO: ORDENAR
import bookingReducer from "../reducers/dashboardReducer/booking/bookingSlice";
import bookingAddReducer from "../reducers/dashboardReducer/booking/bookingAddSlice";
import bookingDeleteReducer from "../reducers/dashboardReducer/booking/bookinDeleteSlice";
import bookingEditReducer from "../reducers/dashboardReducer/booking/bookingEditSlice";


// TODO: ORDENAR
import categoriessReducer from "../reducers/dashboardReducer/categories/categoriesSlice";
import categoriessAddReducer from "../reducers/dashboardReducer/categories/categoriesAddSlice";
import categoriessDeleteReducer from "../reducers/dashboardReducer/categories/categoriesDeleteSlice";
import categoriessEditReducer from "../reducers/dashboardReducer/categories/categoriesEditSlice";

// TODO: ORDENAR
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
    deleterooms: roomsDeleteReducer,
    editrooms: roomsEditReducer,
    //
    categories: categoriessReducer,
    addcategories: categoriessAddReducer,
    deletecategories: categoriessDeleteReducer,
    editcategories: categoriessEditReducer,
    // 
    booking: bookingReducer,
    addbooking: bookingAddReducer,
    deletebooking: bookingDeleteReducer,
    editbooking: bookingEditReducer,
  },
});
