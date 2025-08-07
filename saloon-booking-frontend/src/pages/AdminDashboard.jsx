import React from "react";

export default function AdminDashboard() {
  return (
    <div style={{ padding: "20px", background: "#f5f6fa", minHeight: "100vh" }}>
      <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Admin Dashboard</h2>
      
      <div style={{ textAlign: "center" }}>
        <p>Here you can manage:</p>
        <ul style={{ listStyle: "none", padding: 0 }}>
          <li>👤 Manage Users</li>
          <li>💇 Manage Stylists</li>
          <li>📅 View All Appointments</li>
          <li>🛠 Manage Services</li>
        </ul>
      </div>
    </div>
  );
}
