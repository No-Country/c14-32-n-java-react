"use client";

import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchBookingsByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/booking/bookingSlice";
import { deleteBooking } from "@/store/reducers/dashboardReducer/booking/bookinDeleteSlice";
import { updateBooking } from "@/store/reducers/dashboardReducer/booking/bookingEditSlice";
import { addBooking } from "@/store/reducers/dashboardReducer/booking/bookingAddSlice";

import { fetchRoomsByPage } from "@/store/reducers/dashboardReducer/rooms/roomsSlice";
import { fetchCustomersByPage } from "@/store/reducers/dashboardReducer/clients/clientsSlice";

import { useFormik } from "formik";
import * as Yup from "yup";

export default function ContainerReceptions() {
  const dispatch = useDispatch();
  const booking = useSelector((state) => state.booking.data);
  const loading = useSelector((state) => state.booking.loading);
  const page = useSelector((state) => state.booking.page);
  const totalPages = useSelector((state) => state.booking.totalPages);

  const customers = useSelector((state) => state.customers.data);
  const rooms = useSelector((state) => state.rooms.data);

  // Adding
  const [isAddingPopup, setAddingPopup] = React.useState(false);
  // Popup Edit
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);
  const addingStatus = useSelector((state) => state.addrooms.addingStatus);

  const validationSchema = Yup.object({
    idRoom: Yup.string().required("Room is required"),
    idCustomer: Yup.string().required("Customer is required"),
    checkInDate: Yup.string().required("check In Date is required"),
    checkOutDate: Yup.string().required("check Out Date is required"),
    paymentType: Yup.string().required("paymentType is required"),
  });

  const bookingFormik = useFormik({
    initialValues: {
      idBooking: null,
      idRoom: "",
      idCustomer: "",
      checkInDate: "",
      checkOutDate: "",
      paymentType: "",
      bookingState: "",
    },
    validationSchema: validationSchema,

    onSubmit: (values, { resetForm }) => {
      const formatDateTime = (date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        const hours = String(date.getHours()).padStart(2, "0");
        const minutes = String(date.getMinutes()).padStart(2, "0");
        return `${year}-${month}-${day} ${hours}:${minutes}`;
      };
      if (values.checkInDate) {
        const checkInDate = new Date(values.checkInDate);
        values.checkInDate = formatDateTime(checkInDate);
      }
      if (values.checkOutDate) {
        const checkOutDate = new Date(values.checkOutDate);
        values.checkOutDate = formatDateTime(checkOutDate);
      }
      console.log(values);
      if (values.idBooking) {
        dispatch(updateBooking(values)).then(() => {
          dispatch(fetchBookingsByPage(page));
          setIsPopupOpen(false);
          resetForm();
        });
      } else {
        dispatch(addBooking(values)).then(() => {
          dispatch(fetchBookingsByPage(page));
          setAddingPopup(false);
          resetForm();
        });
      }
    },
  });
  //
  const handleEditClick = (bookId) => {
    const selectedBooks = booking.find((book) => book.idBooking === bookId);
    bookingFormik.setValues(selectedBooks);
    setIsPopupOpen(true);
  };
  // END EDIT

  // DELETE
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [bookIdToDelete, setBookIdToDelete] = React.useState(null);

  const handleShowModal = (bookId) => {
    setBookIdToDelete(bookId);
    setIsModalOpen(true);
  };

  const handleDeletebook = () => {
    dispatch(deleteBooking(bookIdToDelete)).then(() => {
      dispatch(fetchBookingsByPage(page));
    });
    setIsModalOpen(false);
  };

  // DELETE Room

  // Lazy loading
  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchBookingsByPage(page));
      await dispatch(fetchCustomersByPage(page));
      await dispatch(fetchRoomsByPage(page));
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch, page]);

  const handlePageChange = (newPage) => {
    dispatch(setPage(newPage));
  };

  const [searchTerm, setSearchTerm] = React.useState("");

  const filteredBooking = booking.filter((book) => {
    const bookAsString = JSON.stringify(book).toLowerCase();
    return bookAsString.includes(searchTerm.toLowerCase());
  });

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0); // Reset to the first page when searching
  };

  // Navigation
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentBooking = filteredBooking.slice(startIndex, endIndex);

  return (
    <div className="mt-5 flex flex-col gap-5">
      <div className="bg-white rounded-2xl w-60 p-2">
        <p>
          Hotel Managment / <strong className="text-gray-600">Bookings</strong>
        </p>
      </div>

      {showLoading ? (
        <section className="flex flex-col md:flex-row md:justify-between items-center md:gap-0 gap-4 mt-5 rounded-2xl p-5 bg-white ">
          <div className="animate-pulse">
            <div className="p-2 border border-gray-300 rounded-md h-10 w-64"></div>
          </div>
          <div className="flex gap-3">
            <div className="animate-pulse">
              <button className="bg-sky-300 text-gray-300 px-4 py-2 rounded-md">
                Loading...
              </button>
            </div>
          </div>
        </section>
      ) : (
        <div className="flex flex-col mb-4 md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-5 bg-white ">
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={handleSearchChange}
            className="p-2 border border-gray-300 rounded"
          />
          <div className="flex gap-3">
            <button
              onClick={() => setAddingPopup(true)}
              className="bg-sky-600 text-white px-4 py-2 rounded-md hover:bg-sky-700"
            >
              Add Booking
            </button>
            {isAddingPopup && (
              <div className="fixed inset-0 flex items-center justify-center z-50">
                <div className="modal bg-white rounded shadow-lg p-4 sm:p-8 w-96">
                  <form
                    onSubmit={bookingFormik.handleSubmit}
                    className="w-full max-w-md mx-auto"
                  >
                    <div className="mb-4 flex gap-4 items-center">
                      <label
                        htmlFor="idRoom"
                        className="block text-sm font-medium text-gray-700"
                      >
                        Room
                      </label>

                      <select
                        id="idRoom"
                        name="idRoom"
                        value={bookingFormik.values.idRoom}
                        onChange={bookingFormik.handleChange}
                        className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                      >
                        <option>Select</option>
                        {rooms
                          .filter((room) => room.roomState === "AVAILABLE")
                          .map((room) => (
                            <option key={room.idRoom} value={room.idRoom}>
                              {room.roomNumber}
                            </option>
                          ))}
                      </select>
                      {bookingFormik.errors.idRoom && (
                        <p className="text-red-600 text-sm mt-1">
                          {bookingFormik.errors.idRoom}
                        </p>
                      )}
                    </div>

                    <div className="mb-4 flex gap-2 items-center">
                      <label
                        htmlFor="idCustomer"
                        className="block text-sm font-medium text-gray-700"
                      >
                        Customer
                      </label>
                      <select
                        id="idCustomer"
                        name="idCustomer"
                        value={bookingFormik.values.idCustomer}
                        onChange={bookingFormik.handleChange}
                        className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                      >
                        <option>Select</option>
                        {customers.map((customer) => (
                          <option
                            key={customer.idCustomer}
                            value={customer.idCustomer}
                          >
                            {customer.firstname} {customer.lastname}
                          </option>
                        ))}
                      </select>

                      {bookingFormik.errors.idCustomer && (
                        <p className="text-red-600 text-sm mt-1">
                          {bookingFormik.errors.idCustomer}
                        </p>
                      )}
                    </div>

                    <div className="mb-4">
                      <label
                        htmlFor="checkInDate"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Fecha de Check-In
                      </label>
                      <input
                        type="datetime-local"
                        id="checkInDate"
                        name="checkInDate"
                        onChange={bookingFormik.handleChange}
                        value={bookingFormik.values.checkInDate}
                        className="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      />
                    </div>
                    <div className="mb-4">
                      <label
                        htmlFor="checkOutDate"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Fecha de Check-Out
                      </label>
                      <input
                        type="datetime-local"
                        id="checkOutDate"
                        name="checkOutDate"
                        onChange={bookingFormik.handleChange}
                        value={bookingFormik.values.checkOutDate}
                        className="appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      />
                    </div>

                    <div className="mb-4 flex gap-2">
                      <label
                        htmlFor="paymentType"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Tipo de Pago
                      </label>
                      <select
                        id="paymentType"
                        name="paymentType"
                        onChange={bookingFormik.handleChange}
                        value={bookingFormik.values.paymentType}
                        className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                      >
                        <option>Select</option>
                        <option value="0">CREDIT</option>
                        <option value="1">DEBIT</option>
                        <option value="2">CASH</option>
                      </select>
                    </div>

                    <div className="flex gap-4">
                      <button
                        type="submit"
                        className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-500"
                      >
                        Submit
                      </button>
                      <button
                        onClick={() => {
                          setAddingPopup(false);
                          bookingFormik.resetForm();
                        }}
                        className="bg-gray-300 text-gray-700 py-2 px-4 rounded hover:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-500"
                      >
                        Cancel
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            )}
          </div>
        </div>
      )}

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
        <div
          className="bg-white rounded-2xl overflow-x-auto"
          style={{ height: "50vh" }}
        >
          <table className="min-w-full">
            <thead>
              <tr>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Room
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Customer
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Check-In Date
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Check-Out Date
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Payment Type
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
              {currentBooking.map((book) => (
                <tr key={book.idBooking}>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.room.roomNumber}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.customer.firstname} {book.customer.lastname}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.checkInDate}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.checkOutDate}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {book.paymentType}
                  </td>
                  <td>
                    <div className="flex gap-3 px-5">
                      <button onClick={() => handleEditClick(book.idBooking)}>
                        <i className="icon-edit"></i>
                      </button>

                      <button onClick={() => handleShowModal(book.idBooking)}>
                        <i className="icon-remove"></i>
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          {/* DELETE */}
          {isModalOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-8">
                <p className="mb-4">
                  Are you sure you want to delete this booking?
                </p>
                <button
                  onClick={handleDeletebook}
                  className="bg-red-500 text-white py-2 px-4 rounded mr-2 hover:bg-red-700"
                >
                  Yes
                </button>
                <button
                  onClick={() => setIsModalOpen(false)}
                  className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700"
                >
                  No
                </button>
              </div>
            </div>
          )}
          {/* END TO DELETE */}
          {/* EDIT */}
          {isPopupOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-8">
                <form onSubmit={bookingFormik.handleSubmit}>
                  <div className="mb-4 flex gap-4 items-center">
                    <label
                      htmlFor="idRoom"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Room
                    </label>

                    <select
                      id="idRoom"
                      name="idRoom"
                      value={bookingFormik.values.idRoom}
                      onChange={bookingFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>Select</option>
                      {rooms
                        .filter((room) => room.roomState === "AVAILABLE")
                        .map((room) => (
                          <option key={room.idRoom} value={room.idRoom}>
                            {room.roomNumber}
                          </option>
                        ))}
                    </select>
                    {bookingFormik.errors.idRoom && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.idRoom}
                      </p>
                    )}
                  </div>

                  <div className="mb-4 flex gap-2 items-center">
                    <label
                      htmlFor="idCustomer"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Customer
                    </label>
                    <select
                      id="idCustomer"
                      name="idCustomer"
                      value={bookingFormik.values.idCustomer}
                      onChange={bookingFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>Select</option>
                      {customers.map((customer) => (
                        <option
                          key={customer.idCustomer}
                          value={customer.idCustomer}
                        >
                          {customer.firstname} {customer.lastname}
                        </option>
                      ))}
                    </select>

                    {bookingFormik.errors.idCustomer && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.idCustomer}
                      </p>
                    )}
                  </div>

                  <div className="mb-4">
                    <label
                      htmlFor="checkInDate"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Check-In Date
                    </label>
                    <input
                      type="datetime-local"
                      id="checkInDate"
                      name="checkInDate"
                      value={bookingFormik.values.checkInDate}
                      // value={formatDateForInput(bookingFormik.values.checkInDate)}

                      onChange={bookingFormik.handleChange}
                      className="mt-1 p-2 w-full border rounded focus:outline-none focus:ring focus:ring-blue-500"
                    />
                    {bookingFormik.errors.checkInDate && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.checkInDate}
                      </p>
                    )}
                  </div>

                  <div className="mb-4">
                    <label
                      htmlFor="checkOutDate"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Check-Out Date
                    </label>
                    <input
                      type="datetime-local"
                      id="checkOutDate"
                      name="checkOutDate"
                      value={bookingFormik.values.checkOutDate}
                      // value={formatDateForInput(bookingFormik.values.checkOutDate)}
                      onChange={bookingFormik.handleChange}
                      className="mt-1 p-2 w-full border rounded focus:outline-none focus:ring focus:ring-blue-500"
                    />
                    {bookingFormik.errors.checkOutDate && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.checkOutDate}
                      </p>
                    )}
                  </div>

                  <div className="mb-4 flex items-center gap-2">
                    <label
                      htmlFor="paymentType"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Payment Type
                    </label>
                    <select
                      id="paymentType"
                      name="paymentType"
                      onChange={bookingFormik.handleChange}
                      value={bookingFormik.values.paymentType}
                      // onSelectCapture={bookingFormik.values.paymentType}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option value="default" disabled hidden>
                        Select
                      </option>
                      <option value="0">CREDIT</option>
                      <option value="1">DEBIT</option>
                      <option value="2">CASH</option>
                    </select>
                    {bookingFormik.errors.paymentType && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.paymentType}
                      </p>
                    )}
                  </div>

                  <div className="mb-4">
                    <label
                      htmlFor="bookingState"
                      className="block text-sm font-medium text-gray-700"
                    >
                      booking State
                    </label>
                    <input
                      type="text"
                      id="bookingState"
                      name="bookingState"
                      value={bookingFormik.values.bookingState}
                      onChange={bookingFormik.handleChange}
                      className="mt-1 p-2 w-full border rounded focus:outline-none focus:ring focus:ring-blue-500"
                      disabled
                    />
                    {bookingFormik.errors.bookingState && (
                      <p className="text-red-600 text-sm mt-1">
                        {bookingFormik.errors.bookingState}
                      </p>
                    )}
                  </div>

                  <div className="flex gap-4">
                    <button
                      type="submit"
                      className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      Submit
                    </button>
                    <button
                      onClick={() => {
                        setIsPopupOpen(false);
                        bookingFormik.resetForm();
                      }}
                      className="bg-gray-300 text-gray-700 py-2 px-4 rounded hover:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-500"
                    >
                      Cancel
                    </button>
                  </div>
                </form>
              </div>
            </div>
          )}
          {/* END TO EDIT */}
        </div>
      )}

      <div className="flex justify-end mt-4">
        <button
          className={`cursor-pointer px-4 py-2 ${
            page === 0 ? "text-gray-400" : "text-blue-500 hover:text-blue-700"
          }`}
          onClick={() => handlePageChange(page - 1)}
          disabled={page === 0}
        >
          Previous
        </button>
        <span className="pt-2 text-gray-400">
          Page {page + 1} of {totalPages}
        </span>
        <button
          className={`cursor-pointer px-4 py-2 ${
            page === totalPages - 1
              ? "text-gray-400"
              : "text-blue-500 hover:text-blue-700"
          }`}
          onClick={() => handlePageChange(page + 1)}
          disabled={page === totalPages - 1}
        >
          Next
        </button>
      </div>
    </div>
  );
}
