import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

export const addRegisterCheckOut = createAsyncThunk(
  "checkout/add",
  async (checkOutData) => {
    try {
      const response = await axios.post(
        "http://localhost:8083/api/registrations/check-out",
        checkOutData,
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
        alert("Error adding a check out ", error);
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
      .addCase(addRegisterCheckOut.pending, (state) => {
        state.status = "loading";
      })
      .addCase(addRegisterCheckOut.fulfilled, (state, action) => {
        state.status = "succeeded";
      })
      .addCase(addRegisterCheckOut.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message;
      });
  },
});

export default registerCheckInSlice.reducer;
