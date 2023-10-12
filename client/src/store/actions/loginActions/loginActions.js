import { createAsyncThunk } from "@reduxjs/toolkit";
import Cookies from "js-cookie";

export const login = createAsyncThunk(
  "auth/login",
  async ({ email, password }) => {
    try {
      // Realiza una solicitud POST a la API de inicio de sesión
      const response = await fetch("https://reqres.in/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      // Verifica si se capturó el token
      if (!response.ok) {
        throw new Error("Datos incorrectos");
      }

      const data = await response.json();

      // console.log(data)
      // Guarda el token en una cookie
      Cookies.set("token", data.token);

      return data;
    } catch (error) {
      throw error;
    }
  }
);
