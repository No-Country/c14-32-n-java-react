"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchCustomersByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/clients/clientsSlice";
//
import {
  addCustomer,
} from "@/store/reducers/dashboardReducer/clients/clientAddSlice";
import { deleteCustomer } from "@/store/reducers/dashboardReducer/clients/clientDeleteSlice";

export default function ContainerClients() {
  const dispatch = useDispatch();
  const customers = useSelector((state) => state.customers.data);
  const loading = useSelector((state) => state.customers.loading);
  const page = useSelector((state) => state.customers.page);
  const totalPages = useSelector((state) => state.customers.totalPages);

  //
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [customerIdToDelete, setCustomerIdToDelete] = React.useState(null);

  const handleShowModal = (customerId) => {
    setCustomerIdToDelete(customerId);
    setIsModalOpen(true);
  };

  const handleDeleteCustomer = () => {
    dispatch(deleteCustomer(customerIdToDelete)).then(() => {
      dispatch(fetchCustomersByPage(page))
    });
    setIsModalOpen(false);
  };

  // REDUX ADDING ITEM
  const [isAddingPopup, setAddingPopup] = React.useState(false);

  // REDUX
  const addingStatus = useSelector((state) => state.addcustomer.addingStatus);

  const [customerData, setCustomerData] = React.useState({
    firstname: "",
    lastname: "",
    dni: "",
    email: "",
    phone: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCustomerData({ ...customerData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(addCustomer(customerData)).then(() => {
      dispatch(fetchCustomersByPage(page));
    });
    setAddingPopup(false);
  };

  // END TO REDUX ADDING ITEM

  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchCustomersByPage(page));
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

  const filteredCustomers = customers.filter((customer) => {
    const fullName = `${customer.firstname} ${customer.lastname}`;
    return fullName.toLowerCase().includes(searchTerm.toLowerCase());
  });
  // continued

  // Navigation
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentCustomers = filteredCustomers.slice(startIndex, endIndex);
  //

  // Search continued
  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(page);
  };

  // Export Excel
  const exportToCSV = () => {
    const csvData = currentCustomers.map((customer) => {
      return `${customer.firstname}, ${customer.lastname}`;
    });

    const csvContent = `First Name, Last Name\n${csvData.join("\n")}`;
    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "customers.csv";
    a.click();
    window.URL.revokeObjectURL(url);
  };

  return (
    <section className="pt-5 h-5/6 flex flex-col gap-3">
      <h1 className="font-bold text-2xl">Clients</h1>
      {showLoading ? (
        <section className="flex flex-col md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-5 bg-white ">
          <div className="animate-pulse">
            <div className="p-2 border border-gray-300 rounded-md h-10 w-64"></div>
          </div>
          <div className="flex gap-3">
            <div className="animate-pulse">
              <button className="bg-sky-300 text-gray-300 px-4 py-2 rounded-md">
                Loading...
              </button>
            </div>
            <div className="animate-pulse">
              <button className="bg-green-300 text-gray-300 px-4 py-2 rounded-md">
                Loading...
              </button>
            </div>
          </div>
        </section>
      ) : (
        <section className="flex flex-col md:flex-row md:justify-between items-center md:gap-0 gap-4 rounded-2xl p-5 bg-white ">
          <div className="">
            <input
              type="text"
              placeholder="Search..."
              value={searchTerm}
              onChange={handleSearchChange}
              className="p-2 border border-gray-300 rounded-md"
            />
          </div>
          <div className=" flex gap-3">
            {/* Button adding item */}
            <button
              onClick={() => setAddingPopup(true)}
              className="bg-sky-600 text-white px-4 py-2 rounded-md hover:bg-sky-700"
            >
              Add Client
            </button>
            {/* POPUP */}
            {isAddingPopup && (
              <div className="fixed inset-0 flex items-center justify-center z-50">
                <div className="modal bg-white rounded shadow-lg p-4 sm:p-8">
                  <form onSubmit={handleSubmit} className="flex flex-col gap-5">
                    <div className="flex justify-between">
                      <h3>Add client</h3>
                      <p
                        className="cursor-pointer"
                        onClick={() => setAddingPopup(false)}
                      >
                        <i className="fas fa-x-ray"></i>
                      </p>
                    </div>
                    <input
                      type="text"
                      name="firstname"
                      value={customerData.firstname}
                      onChange={handleInputChange}
                      placeholder="First Name"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="text"
                      name="lastname"
                      value={customerData.lastname}
                      onChange={handleInputChange}
                      placeholder="Last Name"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="text"
                      name="dni"
                      value={customerData.dni}
                      onChange={handleInputChange}
                      placeholder="DNI"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="email"
                      name="email"
                      value={customerData.email}
                      onChange={handleInputChange}
                      placeholder="Email"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="text"
                      name="phone"
                      value={customerData.phone}
                      onChange={handleInputChange}
                      placeholder="Phone"
                      className="border rounded p-2 mb-2"
                    />
                    <div className="flex justify-end items-center mt-4">
                      <button
                        type="submit"
                        disabled={addingStatus === "loading"}
                        className={`bg-${
                          addingStatus === "loading" ? "gray" : "blue"
                        }-500 text-white py-2 px-4 rounded hover:bg-${
                          addingStatus === "loading" ? "gray" : "blue"
                        }-700 mr-2`}
                      >
                        {addingStatus === "loading"
                          ? "Adding..."
                          : "Add Client"}
                      </button>
                      <button
                        onClick={() => setAddingPopup(false)}
                        className="bg-gray-300 text-gray-700 py-2 px-4 rounded hover:bg-gray-400"
                      >
                        Cancel
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            )}
            {/* end to adding */}
            <button
              onClick={exportToCSV}
              className="bg-green-600 text-white px-4 py-2 rounded-md hover:bg-green-700"
            >
              Export to Excel
            </button>
          </div>
        </section>
      )}

      {showLoading ? (
        <div className="overflow-x-auto  rounded-t-2xl">
          <table className="min-w-full divide-y divide-gray-200">
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
              </tr>
            </thead>
            <tbody>
              {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map((index) => (
                <tr key={index}>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : loading === "failed" ? (
        <p>Error al cargar los datos</p>
      ) : (
        <div className="overflow-x-auto bg-white rounded-2xl">
          <table className="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Name
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Last name
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
              {currentCustomers.map((customer) => (
                <tr key={customer.idCustomer}>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {customer.firstname}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {customer.lastname}
                  </td>
                  <td>
                    <div className="flex gap-3 px-5">
                      <i className="icon-edit"></i>
                      <button
                        onClick={() => handleShowModal(customer.idCustomer)}
                      >
                        <i className="icon-remove"></i>
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          {isModalOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-8">
                <p className="mb-4">
                  ¿Estás seguro de que deseas eliminar este cliente?
                </p>
                <button
                  onClick={handleDeleteCustomer}
                  className="bg-red-500 text-white py-2 px-4 rounded mr-2 hover:bg-red-700"
                >
                  Sí
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
        </div>
      )}
      <div className="flex justify-end mt-4">
        <button
          className="cursor-pointer"
          onClick={() => handlePageChange(page - 1)}
          disabled={page === 0}
        >
          Previous
        </button>
        <span className="mx-4">
          Page {page + 1} of {totalPages}
        </span>
        <button
          className="cursor-pointer"
          onClick={() => handlePageChange(page + 1)}
          disabled={page === totalPages - 1}
        >
          Next
        </button>
      </div>
    </section>
  );
}
