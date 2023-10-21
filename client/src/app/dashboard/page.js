export default function Home() {
  return (
    <section className="p-5">
      <h1 className="font-bold text-2xl">Welcome to hotel management</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        {Array(3)
          .fill(null)
          .map((_, index) => (
            <div key={index} className="bg-white rounded-lg p-4 animate-pulse">
              <div className="h-20 w-20 bg-gray-100 rounded-md mb-4"></div>
              <div className="h-4 w-20 bg-gray-100 rounded-md mb-2"></div>
              <div className="h-4 w-28 bg-gray-100 rounded-md"></div>
            </div>
          ))}
      </div>
      <div className="bg-white rounded-lg p-4 animate-pulse mt-3">
        <div className="h-32 w-32 bg-gray-100 rounded-md mb-4"></div>
        <div className="h-4 w-24 bg-gray-100 rounded-md mb-2"></div>
        <div className="h-4 w-28 bg-gray-100 rounded-md"></div>
      </div>
    </section>
  );
}
