"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchRoomsByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/rooms/roomsSlice";

import { useFormik } from "formik";
import * as Yup from "yup";

import { addRooms } from "@/store/reducers/dashboardReducer/rooms/roomsAddSlice";
import { deleteRoom } from "@/store/reducers/dashboardReducer/rooms/roomsDeleteSlice";
import { updateRoom } from "@/store/reducers/dashboardReducer/rooms/roomsEditSlice";
import { fetchCategoriesByPage } from "@/store/reducers/dashboardReducer/categories/categoriesSlice";

export default function ContainerRooms() {
  const dispatch = useDispatch();
  const rooms = useSelector((state) => state.rooms.data);
  const loading = useSelector((state) => state.rooms.loading);
  const page = useSelector((state) => state.rooms.page);
  const totalPages = useSelector((state) => state.rooms.totalPages);

  // Find categories
  const categories = useSelector((state) => state.categories.data);

  // Only Search categories

  // Adding Room
  const [isAddingPopup, setAddingPopup] = React.useState(false);
  // Popup Edit item
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);
  const addingStatus = useSelector((state) => state.addrooms.addingStatus);

  const roomValidationSchema = Yup.object({
    roomNumber: Yup.number()
      .required("Room Number is required")
      .positive("Room Number must be positive"),
    roomState: Yup.number()
      .required("Room State is required")
      .oneOf([0, 1, 2, 3], "Invalid Room State"),
    roomCategory: Yup.number()
      .required("Room Category is required")
      .positive("Room Category must be positive"),
  });

  const roomFormik = useFormik({
    initialValues: {
      idRoom: null,
      roomNumber: "",
      roomState: "",
      roomCategory: "",
    },
    validationSchema: roomValidationSchema,
    onSubmit: (values, { resetForm }) => {
      if (values.idRoom) {
        dispatch(updateRoom(values)).then(() => {
          dispatch(fetchRoomsByPage(page));
          setIsPopupOpen(false);
          resetForm();
        });
      } else {
        dispatch(addRooms(values))
          .then(() => {
            dispatch(fetchRoomsByPage(page));
            setAddingPopup(false);
            resetForm();
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  });

  // END TO REDUX ADDING ITEM
  // UPDATE
  const handleEditClick = (roomId) => {
    const selectedRooms = rooms.find((room) => room.idRoom === roomId);
    roomFormik.setValues(selectedRooms);
    setIsPopupOpen(true);
  };
  // end to adding room

  // DELETE
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [roomIdToDelete, setRoomIdToDelete] = React.useState(null);

  const handleShowModal = (roomId) => {
    setRoomIdToDelete(roomId);
    setIsModalOpen(true);
  };

  const handleDeleteRoom = () => {
    dispatch(deleteRoom(roomIdToDelete)).then(() => {
      dispatch(fetchRoomsByPage(page));
    });
    setIsModalOpen(false);
  };

  // DELETE Room

  // Lazy loading
  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      // fetch categories
      await dispatch(fetchCategoriesByPage(0));

      // fetch rooms
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

  // Search
  const [searchTerm, setSearchTerm] = React.useState("");
  const filteredRooms = rooms.filter((room) => {
    return (
      String(room.roomNumber)
        .toLowerCase()
        .includes(searchTerm.toLowerCase()) ||
      String(room.roomState).toLowerCase().includes(searchTerm.toLowerCase()) ||
      String(room.roomCategory.categoryName)
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    );
  });

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0); // Reset to the first page when searching
  };

  // End to search

  // Navigation
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);
  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentRooms = filteredRooms.slice(startIndex, endIndex);

  // end to Navigation

  return (
    <section className="flex flex-col gap-6 pt-5">
      <div className="bg-white rounded-2xl w-60 p-2">
        <p>
          Hotel Managment / <strong className="text-gray-600">Rooms</strong>
        </p>
      </div>
      {showLoading ? (
        <section className="flex flex-col md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-5 bg-white ">
          <div className="animate-pulse">
            <div className="p-2 border border-gray-300 rounded-md h-10 w-64"></div>
          </div>
        </section>
      ) : (
        <section className="p-5 bg-white rounded-lg flex flex-col md:flex-row md:justify-between gap-2">
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={handleSearchChange}
            className="p-2 border border-gray-300 rounded"
          />
          <button
            onClick={() => setAddingPopup(true)}
            className="bg-sky-600 text-white px-4 py-2 rounded-md hover:bg-sky-700"
          >
            Add Room
          </button>
          {isAddingPopup && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-4 sm:p-8 w-96">
                <form
                  onSubmit={roomFormik.handleSubmit}
                  className="flex flex-col gap-5"
                >
                  <div className="flex justify-between items-center">
                    <h3 className="text-xl font-semibold">Add Room</h3>
                    <p
                      className="cursor-pointer text-gray-600 hover-text-gray-800"
                      onClick={() => {
                        setAddingPopup(false);
                        roomFormik.resetForm();
                      }}
                    >
                      X
                    </p>
                  </div>
                  <input
                    type="text"
                    name="roomNumber"
                    value={roomFormik.values.roomNumber}
                    onChange={roomFormik.handleChange}
                    placeholder="Room Number"
                    className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                  />
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomState">Room State:</label>
                    <select
                      id="roomState"
                      name="roomState"
                      value={roomFormik.values.roomState}
                      onChange={roomFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>SELECT</option>
                      <option value={0}>BUSY</option>
                      <option value={1}>AVAILABLE</option>
                      <option value={2}>CLEANING</option>
                      <option value={3}>RESERVED</option>
                      {/* Agrega más opciones de estado según sea necesario */}
                    </select>
                  </div>
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomCategory">Room Category:</label>
                    <select
                      id="roomCategory"
                      name="roomCategory"
                      value={roomFormik.values.roomCategory}
                      onChange={roomFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>SELECT</option>
                      {categories.map((category) => (
                        <option
                          key={category.idCategory}
                          value={category.idCategory}
                        >
                          {category.categoryName}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="flex justify-end items-center mt-4">
                    <button
                      type="submit"
                      className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mr-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      {addingStatus === "loading" ? "Adding..." : "Add Room"}
                    </button>
                    <button
                      onClick={() => {
                        setAddingPopup(false);
                        roomFormik.resetForm();
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
        </section>
      )}

      {showLoading ? (
        <div className="grid grid-cols-1 gap-4 md:grid-cols-5 md:gap-8">
          {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map((index) => (
            <div
              key={index}
              className="p-4 bg-gray-200 animate-pulse shadow-md rounded-lg"
            >
              <div className="h-20 bg-white mb-2"></div>
              <div className="h-3 bg-white mb-2"></div>
              <div className="h-3 bg-white"></div>
            </div>
          ))}
        </div>
      ) : loading === "failed" ? (
        <p>Error al cargar los datos</p>
      ) : (
        <section className="grid grid-cols-1 gap-4 md:grid-cols-5 md:gap-5">
          {currentRooms.map((room) => (
            <article
              key={room.idRoom}
              className="p-4 bg-white shadow-md rounded-lg"
            >
              <div className="flex justify-between">
                <div className="flex flex-col gap-1">
                  <h3 className="text-xl font-semibold">{room.roomNumber}</h3>
                  <p className="text-gray-500">State: {room.roomState}</p>
                  <p className="text-gray-500">
                    Category: {room.roomCategory.categoryName}
                  </p>
                </div>
                <div className="flex flex-col ">
                  <i
                    className="icon-remove cursor-pointer"
                    onClick={() => handleShowModal(room.idRoom)}
                  ></i>
                  <i
                    className="icon-edit cursor-pointer"
                    onClick={() => handleEditClick(room.idRoom)}
                  ></i>
                </div>
              </div>
            </article>
          ))}
          {/* DELETE */}
          {isModalOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-8">
                <p className="mb-4">
                  Are you sure you want to delete this room?
                </p>
                <button
                  onClick={handleDeleteRoom}
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
                <form
                  onSubmit={roomFormik.handleSubmit}
                  className="flex flex-col gap-5"
                >
                  <div className="flex justify-between items-center">
                    <h3 className="text-xl font-semibold">
                      Add or Update Room
                    </h3>
                    <p
                      className="cursor-pointer text-gray-600 hover-text-gray-800"
                      onClick={() => {
                        setIsPopupOpen(false);
                        roomFormik.resetForm();
                      }}
                    >
                      X
                    </p>
                  </div>
                  <input
                    type="text"
                    name="roomNumber"
                    value={roomFormik.values.roomNumber}
                    onChange={roomFormik.handleChange}
                    placeholder="Room Number"
                    className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                  />
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomState">Room State:</label>
                    <select
                      id="roomState"
                      name="roomState"
                      value={roomFormik.values.roomState}
                      onChange={roomFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>Select</option>
                      <option value={0}>BUSY</option>
                      <option value={1}>AVAILABLE</option>
                      <option value={2}>CLEANING</option>
                      <option value={3}>RESERVED</option>
                      {/* Agrega más opciones de estado según sea necesario */}
                    </select>
                  </div>
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomCategory">Room Category:</label>
                    <select
                      id="roomCategory"
                      name="roomCategory"
                      value={roomFormik.values.roomCategory}
                      onChange={roomFormik.handleChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option>Select</option>
                      {categories.map((category) => (
                        <option
                          key={category.idCategory}
                          value={category.idCategory}
                        >
                          {category.categoryName}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="flex justify-end items-center mt-4">
                    <button
                      type="submit"
                      className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mr-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      Update Room
                    </button>
                    <button
                      onClick={() => {
                        setIsPopupOpen(false);
                        roomFormik.resetForm();
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
        </section>
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
    </section>
  );
}
