import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Book from "./pages/Book";
import Services from "./pages/services";
import Login from "./pages/Login";
import Signup from "./pages/SignUp";
import ServiceList from "./pages/ServiceList";
import BookAppointment from "./pages/BookAppointment";
import CustomerDashboard from "./pages/CustomerDashboard";
import StylistDashboard from "./pages/StylistDashBoard";
import AdminDashboard from "./pages/AdminDashboard";
import MyAppointments from "./pages/MyAppointments";

import ProtectedRoute from "./components/ProtectedRoute"; // ✅ New import

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/services" element={<ServiceList />} />
        <Route path="/book" element={<BookAppointment />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/my-appointments" element={<MyAppointments />} />

        {/* ✅ Protected Dashboards */}
        <Route
          path="/customer/dashboard"
          element={
            <ProtectedRoute allowedRoles={["CUSTOMER"]}>
              <CustomerDashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/stylist/dashboard"
          element={
            <ProtectedRoute allowedRoles={["STYLIST"]}>
              <StylistDashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/admin/dashboard"
          element={
            <ProtectedRoute allowedRoles={["ADMIN"]}>
              <AdminDashboard />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
