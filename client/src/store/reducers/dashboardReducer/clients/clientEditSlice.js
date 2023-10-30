// slices/customerSlice.js

import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const updateCustomer = createAsyncThunk(
  'customers/update',
  async (customerData) => {
    const response = await fetch('http://localhost:8083/api/customers', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(customerData),
    });

    if (!response.ok) {
      throw new Error('No se pudo actualizar el cliente.');
    }

    const data = await response.json();
    return data;
  }
);

const customerSlice = createSlice({
    name: 'customers',
    initialState: {
      data: [],
      status: 'idle',
      error: null,
    },
    reducers: {
      // Otros reducers para cargar datos de clientes, etc.
    },
    extraReducers: (builder) => {
      builder
        .addCase(updateCustomer.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(updateCustomer.fulfilled, (state, action) => {
          state.status = 'succeeded';
          // Puedes actualizar el cliente en el estado si es necesario
        })
        .addCase(updateCustomer.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        });
    },
  });
  
  export default customerSlice.reducer;
