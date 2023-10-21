import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchCustomers = createAsyncThunk('customers/fetch', async () => {
  const response = await fetch('https://api.jsonbin.io/v3/b/652f12da12a5d376598d1890');
  const data = await response.json();
  return data.record.content;
});

const customerSlice = createSlice({
  name: 'customers',
  initialState: {
    data: [],
    loading: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchCustomers.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchCustomers.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload;
      })
      .addCase(fetchCustomers.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export default customerSlice.reducer;
