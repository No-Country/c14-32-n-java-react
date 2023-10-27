// customerSlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const deleteCategories = createAsyncThunk('categories/delete', async (customerId) => {
  const response = await fetch(`http://localhost:8083/api/categories/${customerId}`, {
    method: 'DELETE',
  });
  if (!response.ok) {
    throw new Error(`Request failed with status: ${response.status}`);
  }
  return customerId;
});

const categoriesSlice = createSlice({
  name: 'categories',
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteCategories.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(deleteCategories.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter((customer) => customer.idCustomer !== action.payload);
      })
      .addCase(deleteCategories.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export default categoriesSlice.reducer;
