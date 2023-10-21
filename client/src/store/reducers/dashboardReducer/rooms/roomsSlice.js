import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

export const fetchRooms = createAsyncThunk("rooms/fetch", async () => {
  const response = await fetch(
    "https://api.jsonbin.io/v3/b/652f26af12a5d376598d1e54"
  );
  const data = await response.json();
  return data.record.content;
});

const roomsSlice = createSlice({
  name: "rooms",
  initialState: {
    data: [],
    loading: "idle",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchRooms.pending, (state) => {
        state.loading = "loading";
      })
      .addCase(fetchRooms.fulfilled, (state, action) => {
        state.loading = "succeeded";
        state.data = action.payload;
      })
      .addCase(fetchRooms.rejected, (state) => {
        state.loading = "failed";
      });
  },
});

export default roomsSlice.reducer;
