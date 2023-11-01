// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const addCustomer = createAsyncThunk(
  "customers/add",
  async (customerData) => {
    try {
      const response = await axios.post("http://localhost:8083/api/customers", customerData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.status !== 200) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      return response.data;
    } catch (error) {
      alert("Error al agregar un cliente ", error);
      throw error;
    }
  }
);

const customerSlice = createSlice({
  name: "customers",
  initialState: {
    addingStatus: "idle", // Estados posibles: 'idle', 'loading', 'succeeded', 'failed'
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addCustomer.pending, (state) => {
        state.addingStatus = "loading";
        state.error = null;
      })
      .addCase(addCustomer.fulfilled, (state) => {
        state.addingStatus = "succeeded";
      })
      .addCase(addCustomer.rejected, (state, action) => {
        state.addingStatus = "failed";
        state.error = action.error.message;
      });
  },
});

export default customerSlice.reducer;