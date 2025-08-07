import { useState } from "react";
import styles from "../styles/CustomerDashboard.module.css";

export default function CustomerDashboard() {
  const [appointments, setAppointments] = useState([
    { id: 1, service: "Haircut", stylist: "John", time: "10:00 AM", status: "PENDING" },
    { id: 2, service: "Hair Color", stylist: "Emma", time: "11:00 AM", status: "ACCEPTED" },
    { id: 3, service: "Beard Trim", stylist: "Alex", time: "12:00 PM", status: "REJECTED" },
  ]);

  const cancelAppointment = (id) => {
    setAppointments(appointments.filter((appt) => appt.id !== id));
  };

  return (
    <div className={styles.dashboard}>
      <h1 className={styles.header}>Customer Dashboard</h1>
      <div className={styles.tableContainer}>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Service</th>
              <th>Stylist</th>
              <th>Time</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map((appt) => (
              <tr key={appt.id}>
                <td>{appt.service}</td>
                <td>{appt.stylist}</td>
                <td>{appt.time}</td>
                <td>{appt.status}</td>
                <td>
                  {appt.status === "PENDING" && (
                    <button onClick={() => cancelAppointment(appt.id)}>Cancel</button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
