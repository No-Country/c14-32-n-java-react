import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const deleteCategories = createAsyncThunk(
  "categories/delete",
  async (categoryId) => {
    try {
      const response = await axios.delete(
        `https://hotel-backend-production-b446.up.railway.app/api/categories/${categoryId}`
      );
      if (response.status !== 200) {
        throw new Error(`Request failed with status: ${response.status}`);
      }
      return categoryId;
    } catch (error) {
      alert("Error deleting category ", error);
      throw error;
    }
  }
);

const categoriesSlice = createSlice({
  name: "categories",
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteCategories.pending, (state) => {
        state.loading = "loading";
      })
      .addCase(deleteCategories.fulfilled, (state, action) => {
        state.loading = "succeeded";
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter(
          (category) => category.idCategory !== action.payload
        );
      })
      .addCase(deleteCategories.rejected, (state) => {
        state.loading = "failed";
      });
  },
});

export default categoriesSlice.reducer;
