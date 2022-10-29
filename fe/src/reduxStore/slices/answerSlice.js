import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchAnswer = createAsyncThunk(
  "questions/fetchAnswer",
  async () => {
    return axios
      .get("http://localhost:3001/answer/")
      .then((res) => res.data)
      .catch((err) => console.log(err));
  }
);

export const addAnswer = createAsyncThunk(
  "questions/addAnswer",
  async (answerData) => {
    return axios
      .post(`http://localhost:3001/answer/`, answerData)
      .then((res) => res.data)
      .catch((err) => console.log(err));
  }
);

export const deleteAnswer = createAsyncThunk(
  "questions/deleteAnswer",
  async (id) => {
    return axios
      .delete(`http://localhost:3001/answer/${id}`)
      .then((res) => res.data)
      .catch((err) => console.log(err));
  }
);

const answerSlice = createSlice({
  name: "questions",
  initialState: {
    answers: [],
    loading: false,
    error: "",
  },
  reducers: {},
  extraReducers: {
    [fetchAnswer.pending]: (state) => {
      state.answers = [];
      state.loading = true;
      state.error = "";
    },
    [fetchAnswer.fulfilled]: (state, action) => {
      state.answers = action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchAnswer.rejected]: (state, action) => {
      state.answers = [];
      state.loading = false;
      state.error = action.payload;
    },
    [addAnswer.pending]: (state) => {
      state.answers = [];
      state.loading = true;
      state.error = "";
    },
    [addAnswer.fulfilled]: (state, action) => {
      state.answers = [action.payload];
      state.loading = false;
      state.error = "";
    },
    [addAnswer.rejected]: (state, action) => {
      state.answers = [];
      state.loading = false;
      state.error = action.payload.message;
    },
    [deleteAnswer.pending]: (state) => {
      state.answers = [];
      state.loading = true;
      state.error = "";
    },
    [deleteAnswer.fulfilled]: (state, action) => {
      const id = action.meta.arg;
      // console.log(id);
      if (id) {
        // console.log(state.answers);
        // state.answer = state.answer.filter((item) => item._id !== id);
        // state.answer = state.answer.filter((item) => item._id !== id);
      }
      state.answers = [action.payload];
      state.loading = false;
      state.error = "";
    },
    [deleteAnswer.rejected]: (state, action) => {
      console.log(action.payload);
      state.answers = [];
      state.loading = false;
      state.error = action.payload.message;
    },
  },
});

export default answerSlice.reducer;