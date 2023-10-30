// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

// Define una acción asincrónica para agregar un cliente
export const addCategories = createAsyncThunk(
  "categories/add",
  async (categoryData) => {
    try {
      const response = await fetch("http://localhost:8083/api/categories", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(categoryData),
      });

      if (!response.ok) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Error al agregar un cliente:", error);
      throw error;
    }
  }
);


const categoriesSlice = createSlice({
  name: "categories",
  initialState: {
    addingStatus: "idle", // Estados posibles: 'idle', 'loading', 'succeeded', 'failed'
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addCategories.pending, (state) => {
        state.addingStatus = "loading";
      })
      .addCase(addCategories.fulfilled, (state) => {
        state.addingStatus = "succeeded";
      })
      .addCase(addCategories.rejected, (state) => {
        state.addingStatus = "failed";
      });
  },
});

export default categoriesSlice.reducer;