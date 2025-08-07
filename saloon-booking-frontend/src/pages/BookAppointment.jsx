import { useState, useEffect } from "react";
import axios from "axios";

export default function BookAppointment() {
  const [services, setServices] = useState([]);
  const [selectedService, setSelectedService] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/services")
      .then(res => setServices(res.data))
      .catch(err => console.error("Error fetching services:", err));
  }, []);

  const bookAppointment = async () => {
    if (!selectedService) {
      setMessage("Please select a service first.");
      return;
    }

    try {
      // For now, assume userId = 1
      const booking = {
        userId: 1,
        serviceId: selectedService,
        status: "PENDING"
      };

      await axios.post("http://localhost:8080/bookings", booking);
      setMessage("✅ Appointment booked successfully!");
    } catch (err) {
      console.error("Booking error:", err);
      setMessage("❌ Failed to book appointment. Try again.");
    }
  };

  return (
    <div style={{ padding: "30px" }}>
      <h1>Book an Appointment</h1>

      <select
        value={selectedService}
        onChange={(e) => setSelectedService(e.target.value)}
        style={{ padding: "10px", margin: "10px 0", width: "250px" }}
      >
        <option value="">-- Select Service --</option>
        {services.map((service) => (
          <option key={service.id} value={service.id}>
            {service.name} - ₹{service.cost}
          </option>
        ))}
      </select>

      <br />
      <button
        onClick={bookAppointment}
        style={{
          padding: "10px 20px",
          background: "#3498db",
          color: "#fff",
          border: "none",
          borderRadius: "5px",
          cursor: "pointer"
        }}
      >
        Book Now
      </button>

      {message && <p style={{ marginTop: "15px" }}>{message}</p>}
    </div>
  );
}
