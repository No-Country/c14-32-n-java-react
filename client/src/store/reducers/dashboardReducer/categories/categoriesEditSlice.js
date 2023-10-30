// slices/customerSlice.js

import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const updateCategories = createAsyncThunk(
  'categories/update',
  async (customerData) => {
    const response = await fetch('http://localhost:8083/api/categories', {
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

const categoriesSlice = createSlice({
    name: 'categories',
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
        .addCase(updateCategories.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(updateCategories.fulfilled, (state, action) => {
          state.status = 'succeeded';
          // Puedes actualizar el cliente en el estado si es necesario
        })
        .addCase(updateCategories.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        });
    },
  });
  
  export default categoriesSlice.reducer;
