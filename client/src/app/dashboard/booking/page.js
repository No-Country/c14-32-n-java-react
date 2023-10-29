"use client";

import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchBooking } from "@/store/reducers/dashboardReducer/booking/bookingSlice";

export default function ContainerReceptions() {
  const dispatch = useDispatch();
  const booking = useSelector((state) => state.booking.data);
  const loading = useSelector((state) => state.booking.loading);

  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchBooking());
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch]);

  // Navigation and Search
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);
  const [searchTerm, setSearchTerm] = React.useState("");

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;

  const filteredBooking = booking.filter((book) => {
    const bookAsString = JSON.stringify(book).toLowerCase();
    return bookAsString.includes(searchTerm.toLowerCase());
  });

  const currentBooking = filteredBooking.slice(startIndex, endIndex);
  const totalPages = Math.ceil(filteredBooking.length / itemsPerPage);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0); // Reset to the first page when searching
  };

  return (
    <div className="mt-5">
      <h1 className="font-bold text-2xl">Booking</h1>
      <div className="mb-4">
        <input
          type="text"
          placeholder="Search..."
          value={searchTerm}
          onChange={handleSearchChange}
          className="p-2 border border-gray-300 rounded"
        />
      </div>
      {showLoading ? (
        <div className="bg-white rounded-2xl">
          <table className="min-w-full">
            <thead>
              <tr>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  <div className="bg-gray-200 h-6 w-2/3 mx-auto"></div>
                </th>
              </tr>
            </thead>
            <tbody>
              {Array(10)
                .fill(null)
                .map((_, index) => (
                  <tr key={index} className="animate-pulse">
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 bg-gray-100"></td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      ) : loading === "failed" ? (
        <div>Error Here!!</div>
      ) : (
        <div className="bg-white rounded-2xl">
          <table className="min-w-full">
            <thead>
              <tr>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Check-In Date
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Check-Out Date
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Number of Guests
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Payment Status
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Customer
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Room
                </th>
              </tr>
            </thead>
            <tbody>
              {currentBooking.map((book) => (
                <tr key={book.id}>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.CheckInDate}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.CheckOutDate}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.NumberOfGuests}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.PaymentStatus.visa}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.CustomerId}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.roomId}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      <div className="flex justify-between mt-4">
        <button
          onClick={() => handlePageChange(currentPage - 1)}
          disabled={currentPage === 0}
          className={`${
            currentPage === 0 ? "bg-gray-200" : "bg-blue-500 hover:bg-blue-600"
          } text-white px-4 py-2 rounded`}
        >
          Previous
        </button>
        <span className="mx-4">
          Page {currentPage + 1} of {totalPages}
        </span>
        <button
          onClick={() => handlePageChange(currentPage + 1)}
          disabled={currentPage === totalPages - 1}
          className={`${
            currentPage === totalPages - 1
              ? "bg-gray-200"
              : "bg-blue-500 hover:bg-blue-600"
          } text-white px-4 py-2 rounded`}
        >
          Next
        </button>
      </div>
    </div>
  );
}
