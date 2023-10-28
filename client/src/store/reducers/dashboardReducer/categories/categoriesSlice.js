import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import axios from 'axios';

export const fetchCategoriesByPage = createAsyncThunk(
  'categories/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await axios.get(`http://localhost:8083/api/categories/page/${pageNumber}`);
      return response.data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const categoriesSlice = createSlice({
  name: 'categories',
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
      .addCase(fetchCategoriesByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchCategoriesByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
      })
      .addCase(fetchCategoriesByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = categoriesSlice.actions;

export default categoriesSlice.reducer;