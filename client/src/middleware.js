import { NextResponse } from "next/server";

export async function middleware(request) {
  // Obtiene el token del usuario
  // const token = Cookies.get('token')
  // Se trae de request
  const token = request.cookies.get("token");

  // Verifica si el token existe
  if (!token) {
    // El usuario no está autenticado, así que lo mandamos al login
    return NextResponse.redirect(new URL("/", request.url));
  }

  NextResponse.next();
}

export const config = {
  matcher: [
    "/dashboard",
    "/dashboard/rooms",
    "/dashboard/clients",
    "/dashboard/booking",
    "/dashboard/registers",
  ],
};
