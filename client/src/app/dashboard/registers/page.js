"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";

import { fetchBookingsByPage } from "@/store/reducers/dashboardReducer/booking/bookingSlice";

import {
  fetchRegistersByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/registers/registersSlice";
import { addRegisterCheckOut } from "@/store/reducers/dashboardReducer/registers/registerAddCheckOutSlice";
import { addRegisterCheckIn } from "@/store/reducers/dashboardReducer/registers/registersAddCheckInSlice";

import { useFormik } from "formik";
import * as Yup from "yup";

export default function ContainerRegister() {
  const dispatch = useDispatch();

  const registers = useSelector((state) => state.register.data);
  const loading = useSelector((state) => state.register.loading);
  const page = useSelector((state) => state.register.page);
  const totalPages = useSelector((state) => state.register.totalPages);

  // bookings
  const booking = useSelector((state) => state.booking.data);

  //
  const [isAddingPopup, setAddingPopup] = React.useState(false);
  // Popup Checkout
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);

  // Lazy loading
  const [showLoading, setShowLoading] = React.useState(true);

  // Forms
  const validationBookingSchema = Yup.object({
    idBooking: Yup.string().required("Booking is required"),
  });

  const bookingFormik = useFormik({
    initialValues: {
      idBooking: "",
    },
    validationSchema: validationBookingSchema,
    onSubmit: (values, { resetForm }) => {
      // console.log("submit: ", values);
      dispatch(addRegisterCheckIn(values)).then(() => {
        dispatch(fetchRegistersByPage(page));
        setAddingPopup(false);
        resetForm();
      });
    },
  });

  //
  const validationRegisterSchema = Yup.object({
    idRegistration: Yup.string().required("Register is required"),
  });

  const registerFormik = useFormik({
    initialValues: {
      idRegistration: "",
    },
    validationSchema: validationRegisterSchema,
    onSubmit: (values, { resetForm }) => {
      // console.log("Submit: ", values);
      if (values.idRegistration) {
        dispatch(addRegisterCheckOut(values)).then(() => {
          dispatch(fetchRegistersByPage(page));
          setIsPopupOpen(false);
          resetForm();
        });
      }
    },
  });

  const handleChooseClick = (chooseId) => {
    const selectedRegister = registers.find(
      (register) => register.idRegistration === chooseId
    );
    registerFormik.setValues(selectedRegister);
    setIsPopupOpen(true);
  };

  //

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchBookingsByPage(page));
      await dispatch(fetchRegistersByPage(page));
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch, page]);

  const handlePageChange = (newPage) => {
    dispatch(setPage(newPage));
  };

  // Search
  const [searchTerm, setSearchTerm] = React.useState("");

  const filteredRegisters = registers.filter((register) => {
    const registerAsString = JSON.stringify(register).toLowerCase();
    return registerAsString.includes(searchTerm.toLowerCase());
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
  const currentRegisters = filteredRegisters.slice(startIndex, endIndex);

  return (
    <section className="pt-5">
      <div className="bg-white rounded-2xl w-60 p-2">
        <p>
          Hotel Managment / <strong className="text-gray-600">Registers</strong>
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
        <div className="flex flex-col mt-5 md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-3 bg-white ">
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
              Add register
            </button>
            {isAddingPopup && (
              <div className="fixed inset-0 flex items-center justify-center z-50">
                <div className="modal bg-white rounded shadow-lg p-4 sm:p-8 w-96">
                  <form
                    onSubmit={bookingFormik.handleSubmit}
                    className="w-full max-w-md mx-auto"
                  >
                    <div className="flex gap-2 items-center mb-4">
                      <label htmlFor="idBooking" className="text-gray-700">
                        Select Booking
                      </label>
                      <select
                        id="idBooking"
                        name="idBooking"
                        onChange={bookingFormik.handleChange}
                        onBlur={bookingFormik.handleBlur}
                        value={bookingFormik.values.idBooking}
                        className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500 w-full"
                      >
                        <option value="">Select</option>
                        {booking.map((book) => (
                          <option key={book.idBooking} value={book.idBooking}>
                            {book.customer.firstname}
                          </option>
                        ))}
                      </select>
                      {bookingFormik.errors.idBooking &&
                        bookingFormik.touched.idBooking && (
                          <p className="text-red-600 text-sm mt-1">
                            {bookingFormik.errors.idBooking}
                          </p>
                        )}
                    </div>
                    <button
                      type="submit"
                      className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-500 mr-2"
                    >
                      Add
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
                  </form>
                </div>
              </div>
            )}
          </div>
        </div>
      )}

      <section
        className="bg-white rounded-2xl mt-5 min-w-full divide-y divide-gray-200 overflow-x-auto"
        style={{ height: "50vh" }}
      >
        {showLoading ? (
          <div className="animate-pulse">
            <table className="min-w-full">
              <thead>
                <tr>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                  <th className="px-6 py-3 bg-gray-100 text-left text-xs leading-4 font-medium text-gray-300 uppercase tracking-wider"></th>
                </tr>
              </thead>
              <tbody>
                {Array(5)
                  .fill(null)
                  .map((_, index) => (
                    <tr key={index}>
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
          <p>Failed to Load Data!</p>
        ) : (
          <React.Fragment>
            <table className="min-w-full">
              <thead>
                <tr>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Customer
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Check In
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Check Out
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Room State
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Service Price
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Status
                  </th>
                  <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                    Exit
                  </th>
                </tr>
              </thead>
              <tbody>
                {currentRegisters.map((register) => (
                  <tr key={register.idRegistration}>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.customer.firstname} {register.customer.lastname}
                    </td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.guestCheckIn}
                    </td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.guestCheckOut === null
                        ? "YYYY - MM - DD"
                        : register.guestCheckOut}
                    </td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.room.roomState}
                    </td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.servicePrice === 0
                        ? "---"
                        : register.servicePrice.toFixed(2)}
                    </td>

                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      {register.registrationStatus === false
                        ? "Registered"
                        : "Missing marking exit"}
                    </td>
                    <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                      <i
                        onClick={() =>
                          handleChooseClick(register.idRegistration)
                        }
                        className="icon-checkout cursor-pointer"
                      ></i>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            {isPopupOpen && (
              <div className="fixed inset-0 flex items-center justify-center z-50">
                <div className="modal bg-white rounded shadow-lg p-8">
                  <form
                    onSubmit={registerFormik.handleSubmit}
                    className="w-full max-w-md"
                  >
                    <div className="mb-4">
                      <label
                        htmlFor="exitDate"
                        className="block mb-2 font-bold text-gray-700"
                      >
                        Do you want to check out this reservation number?
                      </label>
                      <input
                        type="text"
                        disabled
                        value={registerFormik.values.idRegistration}
                        className="bg-gray-100 border rounded py-2 px-3 text-gray-700 w-full leading-tight focus:outline-none focus:shadow-outline"
                      />
                    </div>
                    <button
                      type="submit"
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mx-3"
                    >
                      Mark out
                    </button>
                    <button
                      type="button"
                      onClick={() => {
                        setIsPopupOpen(false);
                        registerFormik.resetForm();
                      }}
                      className="mt-2 bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                      Cancel
                    </button>
                  </form>
                </div>
              </div>
            )}
          </React.Fragment>
        )}
      </section>
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
    </section>
  );
}
