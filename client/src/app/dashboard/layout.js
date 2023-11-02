"use client";
import React from "react";

import { useDispatch } from "react-redux";
import { logout } from "@/store/reducers/loginReducer/loginSlice";

import { useRouter } from "next/navigation";
import Link from "next/link";


const aside_data = [
  {
    id: 1,
    name: "Home",
    icon: "icon-home-page",
    link: "/dashboard",
  },
  {
    id: 2,
    name: "Booking",
    icon: "icon-reception-page",
    link: "/dashboard/booking",
  },
  {
    id: 3,
    name: "Clients",
    icon: "icon-clients-page",
    link: "/dashboard/clients",
  },
  {
    id: 4,
    name: "Rooms",
    icon: "icon-rooms-page",
    link: "/dashboard/rooms",
  },
  {
    id:5,
    name: "Categories",
    icon: "icon-categories-page",
    link: "/dashboard/categories"
  },
  {
    id:6,
    name: "Registers",
    icon: "icon-registers-page",
    link: "/dashboard/registers"
  }

];

export default function Dashboard({ children }) {
  // Hooks
  const [isNavActive, setIsNavActive] = React.useState(false);

  const handleBurgerClick = () => {
    setIsNavActive(!isNavActive);
  };

  const handleNavItemClick = () => {
    setIsNavActive(false);
  };



  React.useEffect(() => {
    const navItems = document.querySelectorAll(".aside-content ul li");

    navItems.forEach((item) => {
      item.addEventListener("click", handleNavItemClick);
    });

    return () => {
      navItems.forEach((item) => {
        item.removeEventListener("click", handleNavItemClick);
      });
    };
  }, []);

  // Logout Redux
  const dispatch = useDispatch();
  const router = useRouter();
  
  const handleLogout = () => {
    dispatch(logout());
    router.push("/");
  };


  return (
    <main className="flex md:flex-row flex-col min-h-screen w-screen bg-slate-100">
      {/* Aside */}
      <aside
        className={`flex flex-col justify-between aside-content${
          isNavActive ? " active" : ""
        } p-10 lg:p-10  md:p-5 text-white md:shadow-2xl bg-slate-950 md:w-1/12   lg:w-2/12  md:rounded-2xl  md:mx-2 md:mt-2 md:mb-2`}
      >
        <div className="flex justify-between items-center">
          <h1 className="text-lg font-bold md:hidden lg:block">
            Hotel Management
          </h1>
          <h1 className="md:block lg:hidden hidden text-lg font-bold ">HM</h1>
          <button
            className={`md:hidden${isNavActive ? " active" : ""}`}
            onClick={handleBurgerClick}
          >
            <i className="icon-close"></i>
          </button>
        </div>
        <ul className="flex flex-col gap-7 ">
          {aside_data.map((aside, i) => (
            <li  key={i}>
              <Link className="flex gap-4 cursor-pointer" href={aside.link}>
              <i className={aside.icon}></i>
              <p className="md:hidden lg:block" >
                {aside.name}
              </p></Link>
            </li>
          ))}
        </ul>
        <button onClick={handleLogout} className="flex items-center gap-1 w-24">
          <i className="icon-logout-page"></i>
          <p className="md:hidden lg:block">Log out</p>
        </button>
      </aside>

      <section className="w-full p-3">
        <nav className="flex md:justify-end justify-between rounded-2xl shadow-md bg-white h-16 items-center px-3">
          {/* Admin Name */}
          {/* Responsive Toggle Button */}
          <button
            className={`md:hidden z-0  md:z-50${isNavActive ? " active" : ""}`}
            onClick={handleBurgerClick}
          >
            <i className="icon-burger"></i>
          </button>
          <div className="flex gap-3 items-center">
            <div className="w-10 h-10 bg-gray-500 rounded-full"></div>
            <div>
              <h6>Admin</h6>
              <span className="text-sm">admin@admin.com</span>
            </div>
          </div>
        </nav>
        {children}
      </section>
    </main>
  );
}
