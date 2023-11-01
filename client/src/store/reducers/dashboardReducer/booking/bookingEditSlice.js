import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const updateBooking = createAsyncThunk('bookings/update', async (bookingData) => {
  try {
    const response = await axios.put('http://localhost:8083/api/bookings', bookingData, {
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


const bookingsSlice = createSlice({
    name: 'bookings',
    initialState: {
      data: [],
      status: 'idle',
      error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
      builder
        .addCase(updateBooking.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(updateBooking.fulfilled, (state, action) => {
          state.status = 'succeeded';
          // Puedes actualizar el cliente en el estado si es necesario
        })
        .addCase(updateBooking.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        });
    },
  });
  
  export default bookingsSlice.reducer;
