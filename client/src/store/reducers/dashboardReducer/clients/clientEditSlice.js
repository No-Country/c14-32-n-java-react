import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const updateCustomer = createAsyncThunk('customers/update', async (customerData) => {
  try {
    const response = await axios.put('http://localhost:8083/api/customers', customerData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (response.status !== 200) {
      throw new Error('No se pudo actualizar el cliente.');
    }

    return response.data;
  } catch (error) {
    throw error;
  }
});


const customerSlice = createSlice({
    name: 'customers',
    initialState: {
      data: [],
      status: 'idle',
      error: null,
    },
    reducers: {},
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
