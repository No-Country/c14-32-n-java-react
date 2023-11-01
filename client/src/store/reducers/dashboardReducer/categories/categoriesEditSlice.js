// slices/customerSlice.js

import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import axios from "axios";

export const updateCategories = createAsyncThunk(
  "categories/update",
  async (categoryData) => {
    try {
      const response = await axios.put(
        "http://localhost:8083/api/categories",
        categoryData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status !== 200) {
        throw new Error("No se pudo actualizar la categoría.");
      }

      return response.data;
    } catch (error) {
      console.error("Error updating category:", error);
      throw error;
    }
  }
);

const categoriesSlice = createSlice({
  name: "categories",
  initialState: {
    data: [],
    status: "idle",
    error: null,
  },
  reducers: {
    // Otros reducers para cargar datos de clientes, etc.
  },
  extraReducers: (builder) => {
    builder
      .addCase(updateCategories.pending, (state) => {
        state.status = "loading";
      })
      .addCase(updateCategories.fulfilled, (state, action) => {
        state.status = "succeeded";
        // Puedes actualizar el cliente en el estado si es necesario
      })
      .addCase(updateCategories.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export default categoriesSlice.reducer;
