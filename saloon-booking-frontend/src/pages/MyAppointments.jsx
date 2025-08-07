import { useEffect, useState } from "react";
import axios from "axios";
import styles from "../styles/MyAppointments.module.css";

export default function MyAppointments() {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    // Assume logged-in user = 1 for now
    axios.get("http://localhost:8080/bookings/user/1")
      .then(res => setAppointments(res.data))
      .catch(err => console.error("Error fetching appointments:", err));
  }, []);

  return (
    <div className={styles.container}>
      <h1 className={styles.header}>My Appointments</h1>
      {appointments.length === 0 ? (
        <p className={styles.emptyMessage}>No appointments found.</p>
      ) : (
        <table className={styles.table}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Service</th>
              <th>Stylist</th>
              <th>Status</th>
              <th>Booking Time</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map((appt) => (
              <tr key={appt.id}>
                <td>{appt.id}</td>
                <td>{appt.service?.name || "Unknown"}</td>
                <td>{appt.service?.stylist?.name || "N/A"}</td>
                <td>{appt.status}</td>
                <td>{new Date(appt.bookingTime).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
