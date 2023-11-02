import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const updateRoom = createAsyncThunk('rooms/update', async (roomData) => {
  try {
    const response = await axios.put('https://hotel-backend-production-b446.up.railway.app/api/rooms', roomData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (response.status !== 200) {
      throw new Error('No se pudo actualizar el cuarto.');
    }

    return response.data;
  } catch (error) {
    throw error;
  }
});


const roomsSlice = createSlice({
    name: 'rooms',
    initialState: {
      data: [],
      status: 'idle',
      error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
      builder
        .addCase(updateRoom.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(updateRoom.fulfilled, (state, action) => {
          state.status = 'succeeded';
        })
        .addCase(updateRoom.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        });
    },
  });
  
  export default roomsSlice.reducer;
