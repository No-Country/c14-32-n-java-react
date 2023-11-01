"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import Link from "next/link";

import { fetchBookingsByPage } from "@/store/reducers/dashboardReducer/booking/bookingSlice";
import { fetchRoomsByPage } from "@/store/reducers/dashboardReducer/rooms/roomsSlice";
import { fetchCustomersByPage } from "@/store/reducers/dashboardReducer/clients/clientsSlice";
import { fetchCategoriesByPage } from "@/store/reducers/dashboardReducer/categories/categoriesSlice";

export default function Home() {
  const dispatch = useDispatch();

  const bookingElements = useSelector((state) => state.booking.totalElements);
  const roomsElements = useSelector((state) => state.rooms.totalElements);
  const customersElements = useSelector(
    (state) => state.customers.totalElements
  );
  const categoriesElements = useSelector(
    (state) => state.categories.totalElements
  );

  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchBookingsByPage(0));
      await dispatch(fetchCustomersByPage(0));
      await dispatch(fetchRoomsByPage(0));
      await dispatch(fetchCategoriesByPage(0));
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch]);

  return (
    <section className="p-5">
      <h1 className="font-bold text-2xl mb-5">Welcome to hotel management</h1>

      {showLoading ? (
        <React.Fragment>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {Array(4)
              .fill(null)
              .map((_, index) => (
                <div
                  key={index}
                  className="bg-white rounded-lg p-4 animate-pulse"
                >
                  <div className="h-20 w-20 bg-gray-100 rounded-md mb-4"></div>
                  <div className="h-4 w-20 bg-gray-100 rounded-md mb-2"></div>
                  <div className="h-4 w-28 bg-gray-100 rounded-md"></div>
                </div>
              ))}
          </div>
          {/* <div className="bg-white rounded-lg p-4 animate-pulse mt-3">
            <div className="h-32 w-32 bg-gray-100 rounded-md mb-4"></div>
            <div className="h-4 w-24 bg-gray-100 rounded-md mb-2"></div>
            <div className="h-4 w-28 bg-gray-100 rounded-md"></div>
          </div> */}
        </React.Fragment>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-4">
          <div className="mb-4">
            <div className="bg-white rounded-lg shadow-md p-4">
              <h2 className="text-lg font-semibold mb-2">Bookings</h2>
              <p className="text-gray-700 mb-2">
                Total bookings: {bookingElements}
              </p>
              <Link href="/dashboard/booking" className="text-blue-600">View details</Link>
            </div>
          </div>

          <div className="mb-4">
            <div className="bg-white rounded-lg shadow-md p-4">
              <h2 className="text-lg font-semibold mb-2">Rooms</h2>
              <p className="text-gray-700 mb-2">Total rooms: {roomsElements}</p>
              <Link href="/dashboard/rooms" className="text-red-600">Explore rooms</Link>
            </div>
          </div>

          <div className="mb-4">
            <div className="bg-white rounded-lg shadow-md p-4">
              <h2 className="text-lg font-semibold mb-2">Customers</h2>
              <p  className="text-gray-700 mb-2">
                Total customers: {customersElements}
              </p>
              <Link href="/dashboard/clients" className="text-green-600">Manage customers</Link>
            </div>
          </div>

          <div className="mb-4">
            <div className="bg-white rounded-lg shadow-md p-4">
              <h2 className="text-lg font-semibold mb-2">Categories</h2>
              <p className="text-gray-700 mb-2">
                Total categories: {categoriesElements}
              </p>
              <Link href="/dashboard/categories" className="text-yellow-600">Explore categories</Link>
            </div>
          </div>

          {/* <div className="bg-white rounded-lg shadow-md p-4">
            <h2 className="text-lg font-semibold mb-2">
              Círculo de Estadísticas
            </h2>
            <div className="text-gray-700 mb-2">
              Description or content here...
            </div>

            <div className="relative w-16 h-16 rounded-full bg-blue-500">
              <div className="absolute left-0 top-0 w-1/2 h-full bg-red-500 rounded-tl-full rounded-bl-full"></div>
              <div className="absolute right-0 top-0 w-1/2 h-full bg-blue-500 rounded-tr-full rounded-br-full"></div>
            </div>
          </div> */}
        </div>
      )}
    </section>
  );
}
