"use client";
import React from "react";

export default function ContainerRegister() {
  const [showCheckIn, setShowCheckIn] = React.useState(true);
  return (
    <section className="pt-5">
      <div className="bg-white rounded-2xl w-60 p-2">
        <p>
          Hotel Managment / <strong className="text-gray-600">Registers</strong>
        </p>
      </div>

      <section className="bg-white rounded-2xl mt-5 h-[32rem]">
        <div className="w-full p-3 flex justify-end gap-3 mb-2">
          <button
            type="button"
            onClick={() => setShowCheckIn(true)}
            className="bg-sky-500 text-white px-4 py-2 rounded-xl hover:bg-sky-700"
          >
            CHECK IN
          </button>
          <button
            type="button"
            onClick={() => setShowCheckIn(false)}
            className="bg-green-500 text-white px-4 py-2 rounded-xl hover:bg-green-700"
          >
            CHECK OUT
          </button>
        </div>

        {showCheckIn ? (
          <div className="p-2">
            <table className="w-full">
              <thead>
                <tr>
                  <th className="bg-sky-200 text-sky-800 px-4 py-2">
                    CHECK IN
                  </th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
        ) : (
          <div className="p-2">
            <table className="w-full">
              <thead>
                <tr>
                  <th className="bg-green-200 text-green-800 px-4 py-2">
                    CHECK OUT
                  </th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
        )}
      </section>
    </section>
  );
}
