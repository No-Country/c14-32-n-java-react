import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Define una acción asincrónica que acepta un argumento "pageNumber"

export const fetchCustomersByPage = createAsyncThunk(
  'customers/fetchByPage',
  async (pageNumber) => {
    try {
      const response = await fetch(`http://localhost:8083/api/customers/page/${pageNumber}`);
      if (!response.ok) {
        throw new Error(`Request failed with status: ${response.status}`);
      }
      const data = await response.json();
      return data.response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
);

const customerSlice = createSlice({
  name: 'customers',
  initialState: {
    data: [],
    loading: 'idle',
    page: 0,
    totalPages: 0,
  },
  reducers: {
    setPage: (state, action) => {
      state.page = action.payload.pageable.pageNumber;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCustomersByPage.pending, (state) => {
        state.loading = 'loading';
      })
      .addCase(fetchCustomersByPage.fulfilled, (state, action) => {
        state.loading = 'succeeded';
        state.data = action.payload.content;
        state.totalPages = action.payload.totalPages;
        state.page = action.payload.pageable.pageNumber;
      })
      .addCase(fetchCustomersByPage.rejected, (state) => {
        state.loading = 'failed';
      });
  },
});

export const { setPage } = customerSlice.actions;

export default customerSlice.reducer;