// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import axios from "axios";

export const deleteCustomer = createAsyncThunk(
  "customers/delete",
  async (customerId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8083/api/customers/${customerId}`
      );

      if (response.status !== 200) {
        throw new Error(`No se pudo eliminar el cliente.`);
      }

      return customerId;
    } catch (error) {
      alert("Error deleting category ", error);
      throw error;
    }
  }
);

const customerSlice = createSlice({
  name: "customers",
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteCustomer.pending, (state) => {
        state.loading = "loading";
      })
      .addCase(deleteCustomer.fulfilled, (state, action) => {
        state.loading = "succeeded";
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter(
          (customer) => customer.idCustomer !== action.payload
        );
      })
      .addCase(deleteCustomer.rejected, (state) => {
        state.loading = "failed";
      });
  },
});

export default customerSlice.reducer;
