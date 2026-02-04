import { useState } from "react";
import { GoogleLogin } from "@react-oauth/google";
import { loginUser } from "../api/userApi";
import { useNavigate } from "react-router-dom";

export default function Login({ onLogin, goSignup }) {
  const navigate = useNavigate();

  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  // 🔐 NORMAL LOGIN
  const handleLogin = async () => {
    try {
      const res = await loginUser(userName, password);
      onLogin(res);
      navigate("/profile", { replace: true }); // 🔥 FIX
    } catch {
      alert("Invalid username or password");
    }
  };

  // 🔵 GOOGLE LOGIN
  const handleGoogleSuccess = async (credentialResponse) => {
    try {
      const token = credentialResponse.credential;

      const res = await fetch("http://localhost:8080/user/google", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ token }),
      });

      if (!res.ok) throw new Error();

      const user = await res.json();
      onLogin(user);
      navigate("/profile", { replace: true }); // 🔥 FIX
    } catch {
      alert("Google login failed");
    }
  };

  return (
    <div style={box}>
      <h2>Login</h2>

      <input
        style={input}
        placeholder="Username"
        onChange={e => setUserName(e.target.value)}
      />

      <input
        style={input}
        type="password"
        placeholder="Password"
        onChange={e => setPassword(e.target.value)}
      />

      <button style={btn} onClick={handleLogin}>
        Login
      </button>

      <div style={{ margin: "15px 0" }}>OR</div>

      <GoogleLogin
        onSuccess={handleGoogleSuccess}
        onError={() => alert("Google Login Failed")}
      />

      <p style={{ marginTop: 15 }}>
        New user?{" "}
        <span style={link} onClick={goSignup}>
          Signup
        </span>
      </p>
    </div>
  );
}

/* styles */
const box = {
  width: 320,
  margin: "100px auto",
  padding: 20,
  border: "1px solid #ccc",
  borderRadius: 8,
  textAlign: "center",
};

const input = {
  width: "100%",
  padding: 8,
  marginBottom: 10,
};

const btn = {
  width: "100%",
  padding: 8,
  background: "#4CAF50",
  color: "white",
  border: "none",
};

const link = {
  color: "blue",
  cursor: "pointer",
};
