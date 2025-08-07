import styles from "../styles/Home.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <h1 className={styles.heading}>Welcome to Our Salon Booking System</h1>
      <p className={styles.subText}>
        Book your favorite salon services easily with our online platform. Choose from expert stylists and get the best experience.
      </p>
      <button className={styles.bookButton}>Book Now</button>
    </div>
  );
}
