// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

import axios from "axios";

export const deleteRoom = createAsyncThunk(
  "rooms/delete",
  async (roomId) => {
    try {
      const response = await axios.delete(
        `https://hotel-backend-production-b446.up.railway.app/api/rooms/${roomId}`
      );

      if (response.status !== 200) {
        throw new Error(`No se pudo eliminar el cliente.`);
      }

      return roomId;
    } catch (error) {
      alert("Error deleting category ", error);
      throw error;
    }
  }
);

const roomsSlice = createSlice({
  name: "rooms",
  initialState: {
    data: [], // Tu estado de datos de clientes
    loading: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(deleteRoom.pending, (state) => {
        state.loading = "loading";
      })
      .addCase(deleteRoom.fulfilled, (state, action) => {
        state.loading = "succeeded";
        // Eliminar el cliente de la lista de datos
        state.data = state.data.filter(
          (room) => room.idRoom !== action.payload
        );
      })
      .addCase(deleteRoom.rejected, (state) => {
        state.loading = "failed";
      });
  },
});

export default roomsSlice.reducer;
