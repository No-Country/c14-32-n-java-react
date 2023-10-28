"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchRoomsByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/rooms/roomsSlice";

import { addRooms } from "@/store/reducers/dashboardReducer/rooms/roomsAddSlice";

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

  const addingStatus = useSelector((state) => state.addrooms.addingStatus);

  const [roomData, setRoomData] = React.useState({
    roomNumber: "",
    roomState: 0,
    roomCategory: 0,
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRoomData({ ...roomData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(addRooms(roomData)).then(() => {
      dispatch(fetchRoomsByPage(page));
    });
    setAddingPopup(false);
  };

  // end to adding room

  // Lazy loading
  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    // fetch categories
    dispatch(fetchCategoriesByPage(0));

    const loadData = async () => {
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
      String(room.categoryId).toLowerCase().includes(searchTerm.toLowerCase())
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
    <section className="flex flex-col gap-5 pt-5">
      <h1 className="font-bold text-lg">Rooms list</h1>
      {showLoading ? (
        <section className="flex flex-col md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-5 bg-white ">
          <div className="animate-pulse">
            <div className="p-2 border border-gray-300 rounded-md h-10 w-64"></div>
          </div>
        </section>
      ) : (
        <section className="p-5 bg-white rounded-lg flex justify-between">
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
                <form onSubmit={handleSubmit} className="flex flex-col gap-5">
                  <div className="flex justify-between items-center">
                    <h3 className="text-xl font-semibold">Add Room</h3>
                    <p
                      className="cursor-pointer text-gray-600 hover:text-gray-800"
                      onClick={() => setAddingPopup(false)}
                    >
                      <i className="fas fa-times"></i>
                    </p>
                  </div>
                  <input
                    type="number" // Cambiar a tipo número
                    name="roomNumber"
                    value={roomData.roomNumber}
                    onChange={handleInputChange}
                    placeholder="Room"
                    className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                  />
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomState">Room Status:</label>
                    <select
                      id="roomState"
                      name="roomState"
                      value={roomData.roomState}
                      onChange={handleInputChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
                      <option value={0}>BUSY</option>
                      <option value={1}>AVAILABLE</option>
                      <option value={2}>RESERVED</option>
                      <option value={3}>CLEANING</option>
                      {/* Agrega más opciones de estado según sea necesario */}
                    </select>
                  </div>
                  <div className="flex gap-2 items-center">
                    <label htmlFor="roomCategory">Room Category:</label>
                    <select
                      id="roomCategory"
                      name="roomCategory"
                      value={roomData.roomCategory}
                      onChange={handleInputChange}
                      className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
                    >
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
                      disabled={addingStatus === "loading"}
                      className={`bg-${
                        addingStatus === "loading" ? "gray" : "blue"
                      }-500 text-white py-2 px-4 rounded hover:bg-${
                        addingStatus === "loading" ? "gray" : "blue"
                      }-700 mr-2 focus:outline-none focus:ring focus:ring-blue-500`}
                    >
                      {addingStatus === "loading" ? "Adding..." : "Add Room"}
                    </button>
                    <button
                      onClick={() => setAddingPopup(false)}
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
        <section className="grid grid-cols-1 gap-4 md:grid-cols-5 md:gap-8">
          {currentRooms.map((room) => (
            <article
              key={room.idRoom}
              className="p-4 bg-white shadow-md rounded-lg"
            >
              <h3 className="text-xl font-semibold">{room.roomNumber}</h3>
              <p className="text-gray-500">{room.roomState}</p>
              <p className="text-gray-500">
                Category: {room.roomCategory.categoryName}
              </p>
            </article>
          ))}
        </section>
      )}
      <div className="flex justify-between mt-4">
        <button
          onClick={() => handlePageChange(page - 1)}
          disabled={page === 0}
          className={`${
            page === 0 ? "bg-gray-200" : "bg-blue-500 hover:bg-blue-600"
          } text-white px-4 py-2 rounded`}
        >
          Previous
        </button>
        <span className="mx-4">
          Page {page + 1} of {totalPages}
        </span>
        <button
          onClick={() => handlePageChange(page + 1)}
          disabled={page === totalPages - 1}
          className={`${
            page === totalPages - 1
              ? "bg-gray-200"
              : "bg-blue-500 hover:bg-blue-600"
          } text-white px-4 py-2 rounded`}
        >
          Next
        </button>
      </div>
    </section>
  );
}
