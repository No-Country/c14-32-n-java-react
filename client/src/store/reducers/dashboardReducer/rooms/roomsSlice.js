import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Define una acción asincrónica que acepta un argumento "pageNumber"

export const fetchRoomsByPage = createAsyncThunk(
  'rooms/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await fetch(`http://localhost:8083/api/rooms/page/${pageNumber}`);
      if (!response.ok) {
        throw new Error(`Request failed with status: ${response.status}`);
      }
      const data = await response.json();
      return data.response;
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
      })
      .addCase(fetchRoomsByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = roomsSlice.actions;

export default roomsSlice.reducer;
