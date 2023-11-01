// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import axios from "axios";

export const addCategories = createAsyncThunk(
  "categories/add",
  async (categoryData) => {
    try {
      const response = await axios.post(
        "http://localhost:8083/api/categories",
        categoryData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status !== 200) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      return response.data;
    } catch (error) {
      alert("Error adding category ", error);
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
