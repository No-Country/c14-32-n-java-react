import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

export const fetchCategories = createAsyncThunk('categories/fetch', async () => {
  const response = await fetch('https://api.jsonbin.io/v3/b/652f26dd54105e766fc3ad1f');
  const data = await response.json();
  return data.record.content;
});

const categoriesSlice = createSlice({
  name: 'categories',
  initialState: {
    data: [],
    loading: 'idle',
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchCategories.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchCategories.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload;
      })
      .addCase(fetchCategories.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export default categoriesSlice.reducer;
