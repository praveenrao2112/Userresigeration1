import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <header className="fixed top-0 left-0 w-full bg-green-700 text-white shadow-md z-50">
      <div className="max-w-7xl mx-auto px-6 py-3 flex justify-between items-center">
        {/* Logo */}
        <h1 className="text-2xl font-bold">
          <Link to="/" className="hover:text-yellow-300 transition">
            AgriFarm
          </Link>
        </h1>

        {/* Links */}
        <nav className="space-x-6 text-lg">
          <Link to="/" className="hover:text-yellow-300 transition">
            Login
          </Link>
          <Link to="/register" className="hover:text-yellow-300 transition">
            Register
          </Link>
        </nav>
      </div>
    </header>
  );
}
