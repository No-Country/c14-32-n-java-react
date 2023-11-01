import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import axios from "axios";

export const deleteBooking = createAsyncThunk(
  "bookings/delete",
  async (bookingId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8083/api/bookings/${bookingId}`
      );

      if (response.status !== 200) {
        throw new Error(`No se pudo eliminar el cliente.`);
      }

      return bookingId;
    } catch (error) {
      throw error;
    }
  }
);

const bookingsSlice = createSlice({
  name: "bookings",
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteBooking.pending, (state) => {
        state.loading = "loading";
      })
      .addCase(deleteBooking.fulfilled, (state, action) => {
        state.loading = "succeeded";
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter(
          (book) => book.idBooking !== action.payload
        );
      })
      .addCase(deleteBooking.rejected, (state) => {
        state.loading = "failed";
      });
  },
});

export default bookingsSlice.reducer;
