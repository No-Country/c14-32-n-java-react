// customerSlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const deleteCustomer = createAsyncThunk('customers/delete', async (customerId) => {
  const response = await fetch(`http://localhost:8083/api/customers/${customerId}`, {
    method: 'DELETE',
  });
  if (!response.ok) {
    throw new Error(`Request failed with status: ${response.status}`);
  }
  return customerId;
});

const customerSlice = createSlice({
  name: 'customers',
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteCustomer.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(deleteCustomer.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter((customer) => customer.idCustomer !== action.payload);
      })
      .addCase(deleteCustomer.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export default customerSlice.reducer;
