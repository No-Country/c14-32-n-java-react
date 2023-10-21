"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchCustomers } from "@/store/reducers/dashboardReducer/clients/clientsSlice";

export default function ContainerClients() {
  // Redux
  const dispatch = useDispatch();
  const customers = useSelector((state) => state.customers.data);
  const loading = useSelector((state) => state.customers.loading);

  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchCustomers());
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch]);

  // Search
  const [searchTerm, setSearchTerm] = React.useState("");

  const filteredCustomers = customers.filter((customer) => {
    const fullName = `${customer.firstname} ${customer.lastname}`;
    return fullName.toLowerCase().includes(searchTerm.toLowerCase());
  });

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0);
  };

  // Navigation
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentCustomers = filteredCustomers.slice(startIndex, endIndex);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };
  const totalPages = Math.ceil(filteredCustomers.length / itemsPerPage);

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
            <button className="bg-sky-600 text-white px-4 py-2 rounded-md hover:bg-sky-700">
              Add Client
            </button>
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
                      <i className="icon-remove"></i>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      <div className="flex justify-end mt-4">
        <button
          className="cursor-pointer"
          onClick={() => handlePageChange(currentPage - 1)}
          disabled={currentPage === 0}
        >
          Previous
        </button>
        <span className="mx-4">
          Page {currentPage + 1} of {totalPages}
        </span>
        <button
          className="cursor-pointer"
          onClick={() => handlePageChange(currentPage + 1)}
          disabled={currentPage === totalPages - 1}
        >
          Next
        </button>
      </div>
    </section>
  );
}
