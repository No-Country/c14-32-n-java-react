import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import axios from 'axios';

export const fetchCustomersByPage = createAsyncThunk(
  'customers/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await axios.get(`http://localhost:8083/api/customers/page/${pageNumber}`);
      return response.data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const customerSlice = createSlice({
  name: 'customers',
  initialState: {
    data: [],
    loading: 'idle',
    page: 0,
    totalPages: 0,
  },
  reducers: {
    setPage: (state, action) => {
      state.page = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCustomersByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchCustomersByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
        state.totalElements = action.payload.totalElements;
      })
      .addCase(fetchCustomersByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = customerSlice.actions;

export default customerSlice.reducer;