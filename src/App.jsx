import { useEffect, useState } from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
  useNavigate
} from "react-router-dom";

import Login from "./components/Login";
import Signup from "./components/Signup";
import Profile from "./components/Profile";
import AppointmentBooking from "./components/AppointmentBooking";

function AppRoutes({ user, setUser }) {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("user");
    setUser(null);
    navigate("/login", { replace: true }); // 🔥 prevents back
  };

  const ProtectedRoute = ({ children }) => {
    if (!user) return <Navigate to="/login" replace />;
    return children;
  };

  return (
    <Routes>

      <Route
        path="/login"
        element={<Login onLogin={setUser} />}
      />

      <Route
        path="/signup"
        element={<Signup />}
      />

      <Route
        path="/profile"
        element={
          <ProtectedRoute>
            <Profile user={user} onLogout={logout} />
          </ProtectedRoute>
        }
      />

      <Route
        path="/book"
        element={
          <ProtectedRoute>
            <AppointmentBooking />
          </ProtectedRoute>
        }
      />

      <Route
        path="*"
        element={<Navigate to={user ? "/profile" : "/login"} replace />}
      />

    </Routes>
  );
}

function App() {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  useEffect(() => {
    if (user) {
      localStorage.setItem("user", JSON.stringify(user));
    }
  }, [user]);

  return (
    <BrowserRouter>
      <AppRoutes user={user} setUser={setUser} />
    </BrowserRouter>
  );
}

export default App;
