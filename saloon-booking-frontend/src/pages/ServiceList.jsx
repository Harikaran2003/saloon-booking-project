import { useState, useEffect } from "react";
import axios from "axios";
import styles from "../styles/ServiceList.module.css";

export default function ServiceList() {
  const [services, setServices] = useState([]);

  // Fetch services from backend
useEffect(() => {
  axios
    .get("http://localhost:8080/services")
    .then((res) => {
      console.log("Fetched services:", res.data); // 👈 check this
      setServices(res.data);
    })
    .catch((err) => console.error("Error fetching services:", err));
}, []);


  // Simulate logged-in user (replace with actual auth later)
  const userId = 1;

  // Book a service
  const bookService = async (service) => {
    try {
      const response = await axios.post("http://localhost:8080/bookings/create", {
        userId: userId,
        serviceId: service.id,
        status: "PENDING",
      });
      alert(`Booking successful! ID: ${response.data.id}`);
    } catch (error) {
      console.error("Error booking service:", error);
      alert("Booking failed. Check console for details.");
    }
  };

  return (
    <div className={styles.serviceList}>
      <h1 className={styles.header}>Available Services</h1>
      <div className={styles.grid}>
        {services.map((service) => (
          <div key={service.id} className={styles.card}>
            <div className={styles.serviceName}>{service.name}</div>
            <div className={styles.stylist}>Stylist: {service.stylist?.name}</div>
            <div className={styles.price}>₹{service.cost}</div>
            <button className={styles.bookBtn} onClick={() => bookService(service)}>
              Book Now
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
