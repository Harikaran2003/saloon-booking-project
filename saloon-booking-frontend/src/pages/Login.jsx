import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import styles from "../styles/Login.module.css"; // ✅ Import CSS

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/api/users/login", {
        email,
        password,
      });

      // ✅ Check if backend returned an error message
      if (response.data.error) {
        alert(response.data.error); // "Invalid credentials"
        return;
      }

      const user = response.data;

      // ✅ Store details in localStorage
      localStorage.setItem("role", user.role);
      localStorage.setItem("userId", user.id);

      // ✅ Redirect based on role
      if (user.role === "ADMIN") navigate("/admin/dashboard");
      else if (user.role === "STYLIST") navigate("/stylist/dashboard");
      else navigate("/customer/dashboard");

    } catch (err) {
      // Handle backend 401 or 500 errors
      if (err.response && err.response.status === 401) {
        alert("Invalid credentials!");
      } else {
        alert("Server error, please try again later.");
      }
      console.error("Login error:", err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.formBox}>
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <input
            className={styles.inputField}
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            className={styles.inputField}
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit" className={styles.button}>
            Login
          </button>
        </form>
      </div>
    </div>
  );
}
