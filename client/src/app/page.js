"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { login } from "@/store/actions/loginActions/loginActions";
import { useRouter } from "next/navigation";

import { useFormik } from "formik";
import * as Yup from "yup";

export default function Home() {
  const dispatch = useDispatch();
  const { loading, error } = useSelector((state) => state.auth);
  const router = useRouter();

  // console.log(token)
  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("Email format is incorrect")
      .required("Email is required"),
    password: Yup.string().required("Password is required"),
  });

  const formik = useFormik({
    initialValues: {
      email: "eve.holt@reqres.in",
      password: "administrator",
    },
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      const response = await dispatch(login(values));

      if (response.payload && response.payload.token) {
        router.push("/dashboard");
      }
    },
  });

  return (
    <main className="min-h-screen w-screen flex items-center md:justify-start justify-center bg-image">
      <section className="p-20 md:w-8/12 lg:w-5/12  w-full h-full shadow-lg 	md:rounded-2xl	flex flex-col gap-3 justify-center bg-white">
        <h1 className="block text-2xl font-medium leading-6 text-gray-900 ">
          Login
        </h1>
        <form onSubmit={formik.handleSubmit} className="flex flex-col gap-4">
          <label className="block text-sm font-medium leading-6 text-gray-900">
            Email address
          </label>
          <input
            type="email"
            name="email"
            // className="block w-full rounded-md border-1 py-1.5  px-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-black-500 sm:text-sm sm:leading-6"
            className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
            disabled
            value={formik.values.email}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
          />
          {formik.touched.email && formik.errors.email && (
            <p className="block text-sm font-medium leading-6 text-red-500">
              {formik.errors.email}
            </p>
          )}
          <label className="block text-sm font-medium leading-6 text-gray-900">
            Password
          </label>
          <input
            type="password"
            name="password"
            // className="block w-full rounded-md border-0 py-1.5 px-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-black-500 sm:text-sm sm:leading-6"
            className="border rounded p-2 focus:outline-none focus:ring focus:ring-blue-500"
            disabled
            value={formik.values.password}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
          />
          {error && (
            <p className="block text-sm font-medium leading-6 text-red-500">
              Error: {error}
            </p>
          )}
          {formik.touched.password && formik.errors.password && (
            <p className="block text-sm font-medium leading-6 text-red-500">
              {formik.errors.password}
            </p>
          )}
          <button
            type="submit"
            disabled={loading}
            className="bg-sky-500/100 rounded-lg h-10 text-white"
          >
            log in
          </button>
          {loading && (
            <p className="block text-sm text-center font-medium leading-6 text-sky-500">
              Loading...
            </p>
          )}
        </form>
      </section>
    </main>
  );
}
