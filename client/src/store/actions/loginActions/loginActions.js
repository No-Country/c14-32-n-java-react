import { createAsyncThunk } from "@reduxjs/toolkit";
import Cookies from "js-cookie";
import axios from "axios";

export const login = createAsyncThunk(
  "auth/login",
  async ({ email, password }) => {
    try {
      // Realiza una solicitud POST a la API de inicio de sesión utilizando Axios
      const response = await axios.post("https://reqres.in/api/login", {
        email,
        password,
      });

      // Verifica si se capturó el token
      if (response.status !== 200) {
        throw new Error("Datos incorrectos");
      }

      const data = response.data;

      // Guarda el token en una cookie
      Cookies.set("token", data.token);

      return data;
    } catch (error) {
      throw error;
    }
  }
);
