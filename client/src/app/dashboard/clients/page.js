"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useFormik } from "formik";
import * as Yup from "yup";
import {
  fetchCustomersByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/clients/clientsSlice";
//
import { addCustomer } from "@/store/reducers/dashboardReducer/clients/clientAddSlice";
import { deleteCustomer } from "@/store/reducers/dashboardReducer/clients/clientDeleteSlice";

import { updateCustomer } from "@/store/reducers/dashboardReducer/clients/clientEditSlice";

export default function ContainerClients() {
  const dispatch = useDispatch();
  const customers = useSelector((state) => state.customers.data);
  const loading = useSelector((state) => state.customers.loading);
  const page = useSelector((state) => state.customers.page);
  const totalPages = useSelector((state) => state.customers.totalPages);

  // REDUX ADDING ITEM
  const [isAddingPopup, setAddingPopup] = React.useState(false);
  // Popup Edit item
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);
  // REDUX
  const addingStatus = useSelector((state) => state.addcustomer.addingStatus);

  // Definir el esquema de validación Yup
  const validationSchema = Yup.object({
    firstname: Yup.string().required("First Name is required"),
    lastname: Yup.string().required("Last Name is required"),
    dni: Yup.string()
      .required("DNI is required")
      .matches(/^\d{6,8}$/, "DNI must be 6 to 8 digits long"),
    email: Yup.string()
      .email("Invalid email address")
      .required("Email is required"),
    phone: Yup.string()
      .matches(/^\d+$/, "Phone must contain only digits")
      .required("Phone is required"),
  });

  const formik = useFormik({
    initialValues: {
      idCustomer: null,
      firstname: "",
      lastname: "",
      dni: "",
      email: "",
      phone: "",
    },
    validationSchema,
    onSubmit: (values, { resetForm }) => {
      if (values.idCustomer) {
        // Realizar la solicitud PUT para actualizar el cliente
        dispatch(updateCustomer(values))
          .then(() => {
            dispatch(fetchCustomersByPage(page));
            setIsPopupOpen(false);
            resetForm();
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        // Realizar la solicitud POST para crear el cliente
        dispatch(addCustomer(values))
          .then(() => {
            dispatch(fetchCustomersByPage(page));
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
  const handleEditClick = (customerId) => {
    const selectedCustomer = customers.find(
      (customer) => customer.idCustomer === customerId
    );
    formik.setValues(selectedCustomer);
    setIsPopupOpen(true);
  };

  // END TO UPDATE

  // DELETE
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [customerIdToDelete, setCustomerIdToDelete] = React.useState(null);

  const handleShowModal = (customerId) => {
    setCustomerIdToDelete(customerId);
    setIsModalOpen(true);
  };

  const handleDeleteCustomer = () => {
    dispatch(deleteCustomer(customerIdToDelete)).then(() => {
      dispatch(fetchCustomersByPage(page));
    });
    setIsModalOpen(false);
  };

  // DELETE CUSTOMER

  // Lazy loading
  const [showLoading, setShowLoading] = React.useState(true);

  // Render to customers
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
  // Search continued
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
  //

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
                  <form
                    onSubmit={formik.handleSubmit}
                    className="flex flex-col gap-5"
                  >
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
                      value={formik.values.firstname}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      placeholder="First Name"
                      className="border rounded p-2 mb-2"
                    />
                    {formik.touched.firstname && formik.errors.firstname && (
                      <div className="text-red-500">
                        {formik.errors.firstname}
                      </div>
                    )}
                    <input
                      type="text"
                      name="lastname"
                      value={formik.values.lastname}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      placeholder="Last Name"
                      className="border rounded p-2 mb-2"
                    />
                    {formik.touched.lastname && formik.errors.lastname && (
                      <div className="text-red-500">
                        {formik.errors.lastname}
                      </div>
                    )}
                    <input
                      type="text"
                      name="dni"
                      value={formik.values.dni}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      placeholder="DNI"
                      className="border rounded p-2 mb-2"
                    />
                    {formik.touched.dni && formik.errors.dni && (
                      <div className="text-red-500">{formik.errors.dni}</div>
                    )}
                    <input
                      type="email"
                      name="email"
                      value={formik.values.email}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      placeholder="Email"
                      className="border rounded p-2 mb-2"
                    />
                    {formik.touched.email && formik.errors.email && (
                      <div className="text-red-500">{formik.errors.email}</div>
                    )}
                    <input
                      type="text"
                      name="phone"
                      value={formik.values.phone}
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      placeholder="Phone"
                      className="border rounded p-2 mb-2"
                    />
                    {formik.touched.phone && formik.errors.phone && (
                      <div className="text-red-500">{formik.errors.phone}</div>
                    )}
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
                        onClick={() => {
                          setAddingPopup(false);
                          formik.resetForm(); // Aquí reseteamos el formulario
                        }}
                        className="bg-gray-300 text-gray-700 py-2 px-4 rounded hover-bg-gray-400"
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
                      <button
                        onClick={() => handleEditClick(customer.idCustomer)}
                      >
                        <i className="icon-edit"></i>
                      </button>

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
          {/* Edit */}
          {isPopupOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-8">
                <form
                  onSubmit={formik.handleSubmit}
                  className="w-full max-w-md"
                >
                  <div className="mb-4">
                    <label
                      htmlFor="firstname"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Nombre:
                    </label>
                    <input
                      type="text"
                      id="firstname"
                      name="firstname"
                      value={formik.values.firstname}
                      onChange={formik.handleChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {formik.touched.firstname && formik.errors.firstname && (
                      <div className="text-red-500">
                        {formik.errors.firstname}
                      </div>
                    )}
                  </div>
                  <div className="mb-4">
                    <label
                      htmlFor="lastname"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Apellido:
                    </label>
                    <input
                      type="text"
                      id="lastname"
                      name="lastname"
                      value={formik.values.lastname}
                      onChange={formik.handleChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {formik.touched.lastname && formik.errors.lastname && (
                      <div className="text-red-500">
                        {formik.errors.lastname}
                      </div>
                    )}
                  </div>
                  <div className="mb-4">
                    <label
                      htmlFor="dni"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      DNI:
                    </label>
                    <input
                      type="text"
                      id="dni"
                      name="dni"
                      value={formik.values.dni}
                      onChange={formik.handleChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {formik.touched.dni && formik.errors.dni && (
                      <div className="text-red-500">{formik.errors.dni}</div>
                    )}
                  </div>
                  <div className="mb-4">
                    <label
                      htmlFor="email"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Correo Electrónico:
                    </label>
                    <input
                      type="email"
                      id="email"
                      name="email"
                      value={formik.values.email}
                      onChange={formik.handleChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {formik.touched.email && formik.errors.email && (
                      <div className="text-red-500">{formik.errors.email}</div>
                    )}
                  </div>
                  <div className="mb-4">
                    <label
                      htmlFor="phone"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Teléfono:
                    </label>
                    <input
                      type="text"
                      id="phone"
                      name="phone"
                      value={formik.values.phone}
                      onChange={formik.handleChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {formik.touched.phone && formik.errors.phone && (
                      <div className="text-red-500">{formik.errors.phone}</div>
                    )}
                  </div>
                  <div className="mb-4 flex gap-2">
                    <button
                      type="submit"
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                      Guardar Cambios
                    </button>
                    <button
                      type="button"
                      onClick={() => {
                        setIsPopupOpen(false);
                        formik.resetForm();
                      }}
                      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                      Cancelar
                    </button>
                  </div>
                </form>
              </div>
            </div>
          )}
        </div>
      )}
      {/* end Edit */}
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
