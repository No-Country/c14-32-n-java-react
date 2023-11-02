import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const addRegisterCheckIn = createAsyncThunk(
  "checkin/add",
  async (checkInData) => {
    try {
      const response = await axios.post(
        "https://hotel-backend-production-b446.up.railway.app/api/registrations",
        checkInData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status !== 200) {
        throw new Error("The client could not be added.");
      }

      return response.data;
    } catch (error) {
        alert("Error adding a check in ", error);
      throw error;
    }
  }
);

const registerCheckInSlice = createSlice({
  name: "register",
  initialState: {
    data: [],
    status: "idle",
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addRegisterCheckIn.pending, (state) => {
        state.status = "loading";
      })
      .addCase(addRegisterCheckIn.fulfilled, (state, action) => {
        state.status = "succeeded";
      })
      .addCase(addRegisterCheckIn.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export default registerCheckInSlice.reducer;