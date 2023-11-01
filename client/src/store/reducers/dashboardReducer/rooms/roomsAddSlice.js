// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from 'axios';

export const addRooms = createAsyncThunk(
  'rooms/add',
  async (roomsData) => {
    try {
      const response = await axios.post('http://localhost:8083/api/rooms', roomsData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.status !== 201) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      return response.data;
    } catch (error) {
      alert('Error adding room ', error);
      throw error;
    }
  }
);


const roomsSlice = createSlice({
  name: "rooms",
  initialState: {
    addingStatus: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addRooms.pending, (state) => {
        state.addingStatus = "loading";
      })
      .addCase(addRooms.fulfilled, (state) => {
        state.addingStatus = "succeeded";
      })
      .addCase(addRooms.rejected, (state) => {
        state.addingStatus = "failed";
      });
  },
});

export default roomsSlice.reducer;