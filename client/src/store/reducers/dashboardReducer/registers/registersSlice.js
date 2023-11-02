import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import axios from 'axios';

export const fetchRegistersByPage = createAsyncThunk(
  'registers/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await axios.get(`https://hotel-backend-production-b446.up.railway.app/api/registrations/page/${pageNumber}`);
      return response.data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const registersSlice = createSlice({
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
      .addCase(fetchRegistersByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchRegistersByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
        state.totalElements = action.payload.totalElements;
      })
      .addCase(fetchRegistersByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = registersSlice.actions;

export default registersSlice.reducer;