import { useState } from "react";
import styles from "../styles/Book.module.css";

export default function Book() {
  const [formData, setFormData] = useState({
    service: "",
    date: "",
    time: "",
  });

  const services = ["Haircut", "Hair Coloring", "Beard Trim", "Facial"];

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Booking Confirmed for ${formData.service} on ${formData.date} at ${formData.time}`);
  };

  return (
    <div className={styles.bookingContainer}>
      <form className={styles.bookingForm} onSubmit={handleSubmit}>
        <h2>Book an Appointment</h2>

        <div className={styles.formGroup}>
          <label>Service</label>
          <select name="service" value={formData.service} onChange={handleChange} required>
            <option value="">Select Service</option>
            {services.map((service, index) => (
              <option key={index} value={service}>{service}</option>
            ))}
          </select>
        </div>

        <div className={styles.formGroup}>
          <label>Date</label>
          <input type="date" name="date" value={formData.date} onChange={handleChange} required />
        </div>

        <div className={styles.formGroup}>
          <label>Time</label>
          <input type="time" name="time" value={formData.time} onChange={handleChange} required />
        </div>

        <button type="submit" className={styles.submitBtn}>Book Now</button>
      </form>
    </div>
  );
}
