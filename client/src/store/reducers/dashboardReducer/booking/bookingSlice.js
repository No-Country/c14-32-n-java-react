import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import axios from 'axios';

export const fetchBookingsByPage = createAsyncThunk(
  'bookings/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await axios.get(`http://localhost:8083/api/bookings/page/${pageNumber}`);
      return response.data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const bookingsSlice = createSlice({
  name: 'bookings',
  initialState: {
    data: [],
    loading: 'idle',
    page: 0,
    totalPages: 0,
  },
  reducers: {
    setPage: (state, action) => {
      state.page = action.payload.pageable.pageNumber;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchBookingsByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchBookingsByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
        state.totalElements = action.payload.totalElements;
      })
      .addCase(fetchBookingsByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = bookingsSlice.actions;

export default bookingsSlice.reducer;