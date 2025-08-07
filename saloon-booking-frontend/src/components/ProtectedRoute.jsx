import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children, allowedRoles }) {
  const role = localStorage.getItem("role"); // get role from storage

  if (!role || !allowedRoles.includes(role)) {
    // ❌ If user is not logged in or role is not allowed
    return <Navigate to="/login" replace />;
  }

  // ✅ Role is allowed
  return children;
}
