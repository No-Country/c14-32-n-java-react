// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

// Define una acción asincrónica para agregar un cliente
export const addCustomer = createAsyncThunk(
  "customers/add",
  async (customerData) => {
    try {
      const response = await fetch("http://localhost:8083/api/customers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(customerData),
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