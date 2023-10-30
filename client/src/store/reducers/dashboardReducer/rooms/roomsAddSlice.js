// customerSlice.js
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

// Define una acción asincrónica para agregar un cliente
export const addRooms = createAsyncThunk(
  "rooms/add",
  async (roomsData) => {
    try {
      const response = await fetch("http://localhost:8083/api/rooms", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(roomsData),
      });

      if (!response.ok) {
        throw new Error(`Request failed with status: ${response.status}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Error al agregar un cliente:", error);
      throw error;
    }
  }
);


const roomsSlice = createSlice({
  name: "rooms",
  initialState: {
    addingStatus: "idle", // Estados posibles: 'idle', 'loading', 'succeeded', 'failed'
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