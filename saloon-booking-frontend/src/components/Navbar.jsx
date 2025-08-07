import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

export default function Navbar() {
  const [role, setRole] = useState(null);

  useEffect(() => {
    const savedRole = localStorage.getItem("role");
    setRole(savedRole);
  }, []);

  return (
    <nav style={{ display: "flex", gap: "20px", padding: "10px", background: "#eee" }}>
      <Link to="/">Home</Link>
      <Link to="/services">Services</Link>

      {role === "CUSTOMER" && (
        <>
          <Link to="/my-appointments">My Appointments</Link>
          <Link to="/customer/dashboard">Dashboard</Link>
        </>
      )}

      {role === "STYLIST" && (
        <>
          <Link to="/stylist/dashboard">Stylist Dashboard</Link>
        </>
      )}

      {role === "ADMIN" && (
        <>
          <Link to="/admin/dashboard">Admin Dashboard</Link>
        </>
      )}

      {!role && (
        <>
          <Link to="/login">Login</Link>
          <Link to="/signup">Signup</Link>
        </>
      )}
    </nav>
  );
}
