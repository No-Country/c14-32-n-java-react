"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useFormik } from "formik";
import * as Yup from "yup";
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
  // 
  const [isPopupOpen, setIsPopupOpen] = React.useState(false);

  const addingStatus = useSelector((state) => state.addcategories.addingStatus);


  // Definir el esquema de validación Yup para las categorías
  const categoryValidationSchema = Yup.object({
    categoryName: Yup.string().required("Category Name is required"),
    categoryDescription: Yup.string().required(
      "Category Description is required"
    ),
    basePrice: Yup.number()
      .required("Base Price is required")
      .positive("Base Price must be positive"),
  });

  // Formulario para agregar y editar categorías
  const categoryFormik = useFormik({
    initialValues: {
      idCategory: null,
      categoryName: "",
      categoryDescription: "",
      basePrice: null,
    },
    validationSchema: categoryValidationSchema,
    onSubmit: (values, { resetForm }) => {
      if (values.idCategory) {
        // Realizar la solicitud PUT para actualizar la categoría
        dispatch(updateCategories(values))
          .then(() => {
            dispatch(fetchCategoriesByPage(page));
            setIsPopupOpen(false);
            resetForm();
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        // Realizar la solicitud POST para agregar la categoría
        dispatch(addCategories(values))
          .then(() => {
            dispatch(fetchCategoriesByPage(page));
            setAddingPopup(false);
            resetForm();
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  });

  const handleEditClick = (idCategory) => {
    const selectedCategory = categories.find(
      (category) => category.idCategory === idCategory
    );
    categoryFormik.setValues(selectedCategory);
    setIsPopupOpen(true);
  };
  // END TO ADDING CATEGORY


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
    <section className="pt-5  flex flex-col gap-5">
      <div className='bg-white rounded-2xl w-64 p-2'>
        <p>Hotel Managment / <strong className='text-gray-600'>Categories</strong></p>
      </div>
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
                  <form
                    onSubmit={categoryFormik.handleSubmit}
                    className="block text-gray-700 text-sm font-bold mb-2"
                  >
                    <div className="mb-4">
                      <label
                        htmlFor="categoryName"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Category Name:
                      </label>
                      <input
                        type="text"
                        id="categoryName"
                        name="categoryName"
                        value={categoryFormik.values.categoryName}
                        onChange={categoryFormik.handleChange}
                        onBlur={categoryFormik.handleBlur}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      />
                      {categoryFormik.touched.categoryName &&
                        categoryFormik.errors.categoryName && (
                          <p className="text-red-500 text-xs italic">
                            {categoryFormik.errors.categoryName}
                          </p>
                        )}
                    </div>

                    <div className="mb-6">
                      <label
                        htmlFor="categoryDescription"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Category Description:
                      </label>
                      <textarea
                        id="categoryDescription"
                        name="categoryDescription"
                        value={categoryFormik.values.categoryDescription}
                        onChange={categoryFormik.handleChange}
                        onBlur={categoryFormik.handleBlur}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      />
                      {categoryFormik.touched.categoryDescription &&
                        categoryFormik.errors.categoryDescription && (
                          <p className="text-red-500 text-xs italic">
                            {categoryFormik.errors.categoryDescription}
                          </p>
                        )}
                    </div>

                    <div className="mb-6">
                      <label
                        htmlFor="basePrice"
                        className="block text-gray-700 text-sm font-bold mb-2"
                      >
                        Base Price:
                      </label>
                      <input
                        type="number"
                        id="basePrice"
                        name="basePrice"
                        value={categoryFormik.values.basePrice}
                        onChange={categoryFormik.handleChange}
                        onBlur={categoryFormik.handleBlur}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      />
                      {categoryFormik.touched.basePrice &&
                        categoryFormik.errors.basePrice && (
                          <p className="text-red-500 text-xs italic">
                            {categoryFormik.errors.basePrice}
                          </p>
                        )}
                    </div>

                    <div className="flex items-center justify-between">
                      <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                      >
                        {addingStatus === "loading"
                          ? "Adding..."
                          : "Add Category"}
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
            {/*  */}
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
        <article className="overflow-x-auto bg-white rounded-2xl" style={{ height: "50vh"}}>
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
                      <button onClick={() => handleEditClick(category.idCategory)}>
                        <i className="icon-edit"></i>
                      </button>
                      <button
                        onClick={() => handleShowModal(category.idCategory)}
                      >
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
          {/* END TO DELETE */}
          {/* UPDATE */}
          {isPopupOpen && (
            <div className="fixed inset-0 flex items-center justify-center z-50">
              <div className="modal bg-white rounded shadow-lg p-4 sm:p-8">
                <form
                  onSubmit={categoryFormik.handleSubmit}
                  className="block text-gray-700 text-sm font-bold mb-2"
                >
                  <div className="mb-4">
                    <label
                      htmlFor="categoryName"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Category Name:
                    </label>
                    <input
                      type="text"
                      id="categoryName"
                      name="categoryName"
                      value={categoryFormik.values.categoryName}
                      onChange={categoryFormik.handleChange}
                      onBlur={categoryFormik.handleBlur}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {categoryFormik.touched.categoryName &&
                      categoryFormik.errors.categoryName && (
                        <p className="text-red-500 text-xs italic">
                          {categoryFormik.errors.categoryName}
                        </p>
                      )}
                  </div>

                  <div className="mb-6">
                    <label
                      htmlFor="categoryDescription"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Category Description:
                    </label>
                    <textarea
                      id="categoryDescription"
                      name="categoryDescription"
                      value={categoryFormik.values.categoryDescription}
                      onChange={categoryFormik.handleChange}
                      onBlur={categoryFormik.handleBlur}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {categoryFormik.touched.categoryDescription &&
                      categoryFormik.errors.categoryDescription && (
                        <p className="text-red-500 text-xs italic">
                          {categoryFormik.errors.categoryDescription}
                        </p>
                      )}
                  </div>

                  <div className="mb-6">
                    <label
                      htmlFor="basePrice"
                      className="block text-gray-700 text-sm font-bold mb-2"
                    >
                      Base Price:
                    </label>
                    <input
                      type="number"
                      id="basePrice"
                      name="basePrice"
                      value={categoryFormik.values.basePrice}
                      onChange={categoryFormik.handleChange}
                      onBlur={categoryFormik.handleBlur}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    />
                    {categoryFormik.touched.basePrice &&
                      categoryFormik.errors.basePrice && (
                        <p className="text-red-500 text-xs italic">
                          {categoryFormik.errors.basePrice}
                        </p>
                      )}
                  </div>

                  <div className="flex items-center justify-between">
                    <button
                      type="submit"
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                      Submit
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
          {/* END TO UPDATE */}
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
