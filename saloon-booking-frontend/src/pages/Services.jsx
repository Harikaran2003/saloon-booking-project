import styles from "../styles/Services.module.css";

export default function Services() {
  // Sample data (later we will fetch from backend)
  const services = [
    { id: 1, name: "Haircut", price: 300 },
    { id: 2, name: "Hair Coloring", price: 800 },
    { id: 3, name: "Beard Trim", price: 150 },
    { id: 4, name: "Facial", price: 500 },
  ];

  return (
    <div className={styles.servicesContainer}>
      {services.map((service) => (
        <div className={styles.serviceCard} key={service.id}>
          <h3 className={styles.serviceName}>{service.name}</h3>
          <p className={styles.servicePrice}>₹{service.price}</p>
          <button className={styles.bookBtn}>Book Now</button>
        </div>
      ))}
    </div>
  );
}
