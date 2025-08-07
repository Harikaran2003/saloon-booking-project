import { useState } from "react";
import styles from "../styles/StylistDashboard.module.css";

export default function StylistDashboard() {
  const [appointments, setAppointments] = useState([
    { id: 1, customer: "Alice", service: "Haircut", time: "10:00 AM", status: "PENDING" },
    { id: 2, customer: "Bob", service: "Hair Color", time: "11:00 AM", status: "PENDING" },
    { id: 3, customer: "Charlie", service: "Beard Trim", time: "12:00 PM", status: "ACCEPTED" },
  ]);

  const updateStatus = (id, status) => {
    setAppointments(
      appointments.map((appt) =>
        appt.id === id ? { ...appt, status } : appt
      )
    );
  };

  return (
    <div className={styles.dashboard}>
      <h1 className={styles.header}>Stylist Dashboard</h1>
      <div className={styles.cardContainer}>
        {appointments.map((appt) => (
          <div className={styles.card} key={appt.id}>
            <h3>{appt.customer}</h3>
            <p>Service: {appt.service}</p>
            <p>Time: {appt.time}</p>
            <p>Status: {appt.status}</p>
            {appt.status === "PENDING" && (
              <>
                <button onClick={() => updateStatus(appt.id, "ACCEPTED")}>Accept</button>
                <button className="reject" onClick={() => updateStatus(appt.id, "REJECTED")}>
                  Reject
                </button>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
