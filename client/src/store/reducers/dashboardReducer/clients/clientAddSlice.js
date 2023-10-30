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

      if (response.status !== 201) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      return response.data;
    } catch (error) {
      console.error("Error al agregar un cliente:", error);
      throw error;
    }
  }
);

const customerSlice = createSlice({
  name: "customers",
  initialState: {
    addingStatus: "idle", // Estados posibles: 'idle', 'loading', 'succeeded', 'failed'
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addCustomer.pending, (state) => {
        state.addingStatus = "loading";
      })
      .addCase(addCustomer.fulfilled, (state) => {
        state.addingStatus = "succeeded";
      })
      .addCase(addCustomer.rejected, (state) => {
        state.addingStatus = "failed";
      });
  },
});

export default customerSlice.reducer;