"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchCategoriesByPage,
  setPage,
} from "@/store/reducers/dashboardReducer/categories/categoriesSlice";

import { addCategories } from "@/store/reducers/dashboardReducer/categories/categoriesAddSlice";
import { updateCategories } from "@/store/reducers/dashboardReducer/categories/categoriesEditSlice";
import { deleteCategories } from "@/store/reducers/dashboardReducer/categories/categoriesDeleteSlice";

export default function ContainerCategories() {
  const dispatch = useDispatch();
  const categories = useSelector((state) => state.categories.data);
  const loading = useSelector((state) => state.categories.loading);
  const page = useSelector((state) => state.categories.page);
  const totalPages = useSelector((state) => state.categories.totalPages);

  // Adding Category
  const [isAddingPopup, setAddingPopup] = React.useState(false);

  const addingStatus = useSelector((state) => state.addcategories.addingStatus);

  const [categoryData, setCategoryData] = React.useState({
    categoryName: "",
    categoryDescription: "",
    basePrice: null,
  });
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCategoryData({ ...categoryData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(addCategories(categoryData)).then(() => {
      dispatch(fetchCategoriesByPage(page));
    });
    setAddingPopup(false);
  };
  // END TO ADDING CATEGORY


  // TODO: FALTA INTEGRAR EL ACTUALIZADO
  // Editing Category
  const [formData, setFormData] = React.useState({
    idCategory: null,
    categoryName: "",
    categoryDescription: "",
    basePrice: null,
  });
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);

  const handleEditClick = (categoryId) => {
    // Copiar los datos del cliente seleccionado al estado formData
    const selectedCategory = categories.find(
      (category) => category.idCustomer === categoryId
    );
    setFormData(selectedCategory);

    // Abrir el popup
    setIsPopupOpen(true);
  };

  const handleCancelEdit = () => {
    setIsPopupOpen(false);
  };

  const handleSaveEdit = (e) => {
    e.preventDefault();
    // Realizar la solicitud PUT para actualizar el cliente
    dispatch(updateCategories(formData))
      .then(() => {
         dispatch(fetchCategoriesByPage(page));
        setIsPopupOpen(false);
        // Puedes actualizar la lista de clientes si es necesario
      })
      .catch((error) => {
        // Lidiar con errores, mostrar un mensaje de error, etc.
        console.log(error)
      });
  };

  const handleInputEditChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
  // END Editing CATEGORY


  // Delete category
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [categoryIdToDelete, setCategoryIdToDelete] = React.useState(null);

  const handleShowModal = (categoryId) => {
    setCategoryIdToDelete(categoryId);
    setIsModalOpen(true);
  };

  const handleDeleteCategory = () => {
    dispatch(deleteCategories(categoryIdToDelete)).then(() => {
      dispatch(fetchCategoriesByPage(page));
    });
    setIsModalOpen(false);
  };

  // end to delete

  // LAZY LOADING
  const [showLoading, setShowLoading] = React.useState(true);

  React.useEffect(() => {
    const loadData = async () => {
      await dispatch(fetchCategoriesByPage(page));
      setTimeout(() => {
        setShowLoading(false);
      }, 500);
    };
    loadData();
  }, [dispatch, page]);

  // END TO LAZY LOADING

  //Search
  const [searchTerm, setSearchTerm] = React.useState("");
  const filteredCategories = categories.filter((category) => {
    const categoryNameLower = category.categoryName.toLowerCase();
    const basePriceString = `${category.basePrice}`;
    const basePriceLower = basePriceString.toLowerCase();

    return (
      categoryNameLower.includes(searchTerm.toLowerCase()) ||
      basePriceLower.includes(searchTerm.toLowerCase())
    );
  });
  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(0); // Reset to the first page when searching
  };
  //end to search

  // NAVIGATION
  const itemsPerPage = 10;
  const [currentPage, setCurrentPage] = React.useState(0);

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentCategories = filteredCategories.slice(startIndex, endIndex);
  const handlePageChange = (newPage) => {
    dispatch(setPage(newPage));
  };

  // END TO NAVIGATION

  // Export Excel
  const exportToCSV = () => {
    const csvData = currentCategories.map((category) => {
      return `${category.categoryName}, ${category.basePrice}`;
    });

    const csvContent = `Category Name, Base Price\n${csvData.join("\n")}`;
    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "categories.csv";
    a.click();
    window.URL.revokeObjectURL(url);
  };

  return (
    <section className="pt-5 h-4/6 flex flex-col gap-5">
      <h1 className="font-bold text-xl">Section Categories</h1>
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
            <button
              onClick={() => setAddingPopup(true)}
              className="bg-sky-600 text-white px-4 py-2 rounded-md hover:bg-sky-700"
            >
              Add Client
            </button>
            {isAddingPopup && (
              <div className="fixed inset-0 flex items-center justify-center z-50">
                <div className="modal bg-white rounded shadow-lg p-4 sm:p-8">
                  <form onSubmit={handleSubmit} className="flex flex-col gap-5">
                    <div className="flex justify-between">
                      <h3>Add Category</h3>
                      <p
                        className="cursor-pointer"
                        onClick={() => setAddingPopup(false)}
                      >
                        <i className="fas fa-x-ray"></i>
                      </p>
                    </div>
                    <input
                      type="text"
                      name="categoryName"
                      value={categoryData.categoryName}
                      onChange={handleInputChange}
                      placeholder="Name"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="text"
                      name="categoryDescription"
                      value={categoryData.categoryDescription}
                      onChange={handleInputChange}
                      placeholder="Description"
                      className="border rounded p-2 mb-2"
                    />
                    <input
                      type="text"
                      name="basePrice"
                      value={categoryData.basePrice}
                      onChange={handleInputChange}
                      placeholder="Price"
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
                          : "Add Categorie"}
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
        <div className="overflow-x-auto">
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
                <tr key={index} className="animate-pulse">
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900 bg-gray-200"></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : loading === "failed" ? (
        <p>Error Here!!</p>
      ) : (
        <article className="overflow-x-auto bg-white rounded-2xl h-full">
          <table className="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  category Name
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  Price Base
                </th>
                <th className="px-6 py-3 bg-gray-50 text-left text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider">
                  actions
                </th>
              </tr>
            </thead>
            <tbody>
              {currentCategories.map((category) => (
                <tr key={category.idCategory}>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    {category.categoryName}
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    ${category.basePrice}.00
                  </td>
                  <td className="px-6 py-4 whitespace-no-wrap text-sm leading-5 text-gray-900">
                    <div className="flex gap-3">
                      <i className="icon-edit"></i>{" "}
                      <button  onClick={() => handleShowModal(category.idCategory)}>
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
                  ¿Estás seguro de que deseas eliminar esta categoria?
                </p>
                <button
                  onClick={handleDeleteCategory}
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
        </article>
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
