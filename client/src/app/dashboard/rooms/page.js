"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchRooms } from "@/store/reducers/dashboardReducer/rooms/roomsSlice";

export default function ContainerRooms() {
  const dispatch = useDispatch();
  const rooms = useSelector((state) => state.rooms.data);
  const loading = useSelector((state) => state.rooms.loading);

  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchRooms());
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch]);

  //
  const itemsPerPage = 8;
  const [currentPage, setCurrentPage] = React.useState(0);
  const [searchTerm, setSearchTerm] = React.useState("");

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const filteredRooms = rooms.filter((room) => {
    return (
      String(room.roomNumber)
        .toLowerCase()
        .includes(searchTerm.toLowerCase()) ||
      String(room.roomState).toLowerCase().includes(searchTerm.toLowerCase()) ||
      String(room.categoryId).toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const currentRooms = filteredRooms.slice(startIndex, endIndex);

  const totalPages = Math.ceil(filteredRooms.length / itemsPerPage);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0); // Reset to the first page when searching
  };

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
        <section className="p-5 bg-white rounded-lg">
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={handleSearchChange}
            className="p-2 border border-gray-300 rounded"
          />
        </section>
      )}
      {showLoading ? (
        <div className="grid grid-cols-1 gap-4 md:grid-cols-4 md:gap-8">
          {[1, 2, 3, 4, 5, 6, 7, 8].map((index) => (
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
        <section className="grid grid-cols-1 gap-4 md:grid-cols-4 md:gap-8">
          {currentRooms.map((room) => (
            <article
              key={room.roomId}
              className="p-4 bg-white shadow-md rounded-lg"
            >
              <h3 className="text-xl font-semibold">{room.roomNumber}</h3>
              <p className="text-gray-500">{room.roomState}</p>
              <p className="text-gray-500">Category: {room.categoryId}</p>
            </article>
          ))}
        </section>
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
    </section>
  );
}
