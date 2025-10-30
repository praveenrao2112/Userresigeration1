import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import LoginPage from "./pages/LoginPage";
import RegistrationForm from "./pages/RegistrationForm";

export default function App() {
  return (
    <Router>
      {/* ✅ Navbar at top */}
      <Navbar />

      {/* ✅ Add top padding to prevent overlap */}
      <div className="pt-20 bg-gray-100 min-h-screen">
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/register" element={<RegistrationForm />} />
        </Routes>
      </div>
    </Router>
  );
}
