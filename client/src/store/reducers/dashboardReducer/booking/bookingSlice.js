import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchBooking = createAsyncThunk('booking/fetch', async () => {
  const response = await fetch('https://api.jsonbin.io/v3/b/6531b06654105e766fc48957');
  const data = await response.json();
  return data.record.content;
});

const bookingSlice = createSlice({
  name: 'booking',
  initialState: {
    data: [],
    loading: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchBooking.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchBooking.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload;
      })
      .addCase(fetchBooking.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export default bookingSlice.reducer;