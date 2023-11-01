import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchRoomsByPage = createAsyncThunk(
  'rooms/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await axios.get(`http://localhost:8083/api/rooms/page/${pageNumber}`);
      if (response.status !== 200) {
        throw new Error(`Request failed with status: ${response.status}`);
      }
      return response.data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const roomsSlice = createSlice({
  name: 'rooms',
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
      .addCase(fetchRoomsByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchRoomsByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
        state.totalElements = action.payload.totalElements;
      })
      .addCase(fetchRoomsByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = roomsSlice.actions;

export default roomsSlice.reducer;
