import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const BASEURL = "http://ec2-54-180-147-29.ap-northeast-2.compute.amazonaws.com";

export const signUser = createAsyncThunk("user/addUser", async (addData) => {
  return axios
    .post(`${BASEURL}v1/sign/`, addData, {})
    .then((res) => res.data)
    .catch((err) => console.log(err));
});

export const loginUser = createAsyncThunk(
  "user/loginUser",
  async (loginData) => {
    return (
      axios
        .post(`${BASEURL}v1/login`, { ...loginData })
        // return fetch("/v1/login", {
        //   method: "POST",
        //   headers: {
        //     "Content-Length": 0,
        //     "ngrok-skip-browser-warning": "111",
        //   },
        //   body: JSON.stringify(loginData),
        // })
        .then((res) => {
          let jwtToken = res.headers.get("Authorization");
          let jwtrefreshToken = res.headers.get("refresh");
          localStorage.setItem("access_token", jwtToken);
          localStorage.setItem("refresh", jwtrefreshToken);
          return res.data;
        })
        .catch((err) => console.log(err))
    );
  }
);
const userSlice = createSlice({
  name: "users",
  initialState: {
    users: [],
    loading: false,
    error: "",
  },

  reducers: {},
  extraReducers: {
    [signUser.pending]: (state) => {
      state.users = [];
      state.loading = true;
      state.error = "";
    },
    [signUser.fulfilled]: (state, action) => {
      // state.users = action.payload;
      // state.loading = false;
      // state.error = "";
    },
    [signUser.rejected]: (state, action) => {
      state.users = [];
      state.loading = false;
      state.error = action.payload;
    },
    [loginUser.pending]: (state) => {
      state.users = [];
      state.loading = true;
      state.error = "";
    },
    [loginUser.fulfilled]: (state, action) => {
      state.users = action.payload;
      state.loading = false;
      state.error = "";
    },
    [loginUser.rejected]: (state, action) => {
      state.users = [];
      state.loading = false;
      state.error = action.payload;
    },
  },
});

export default userSlice.reducer;
