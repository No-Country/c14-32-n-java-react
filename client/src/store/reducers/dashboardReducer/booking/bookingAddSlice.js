// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const addBooking = createAsyncThunk(
  "bookings/add",
  async (customerData) => {
    try {
      const response = await axios.post("https://hotel-backend-production-b446.up.railway.app/api/bookings", customerData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.status !== 200) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      return response.data;
    } catch (error) {
      alert("Error al agregar un cliente ", error);
      throw error;
    }
  }
);

const bookingsSlice = createSlice({
  name: "bookings",
  initialState: {
    addingStatus: "idle", // Estados posibles: 'idle', 'loading', 'succeeded', 'failed'
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addBooking.pending, (state) => {
        state.addingStatus = "loading";
      })
      .addCase(addBooking.fulfilled, (state) => {
        state.addingStatus = "succeeded";
      })
      .addCase(addBooking.rejected, (state) => {
        state.addingStatus = "failed";
      });
  },
});

export default bookingsSlice.reducer;