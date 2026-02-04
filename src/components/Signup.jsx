import { useState } from "react";
import { signupUser } from "../api/userApi";
import { useNavigate } from "react-router-dom";

export default function Signup() {
  const navigate = useNavigate();

  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSignup = async () => {
    console.log("Signup clicked"); // 🔥 MUST PRINT

    if (!userName || !password) {
      alert("Username and password required");
      return;
    }

    try {
      setLoading(true);
      await signupUser({ userName, password });

      alert("Signup success");
      navigate("/login"); // 🔥 VERY IMPORTANT

    } catch (err) {
      console.error(err);
      alert("Signup failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ width: 300, margin: "100px auto" }}>
      <h2>Signup</h2>

      <input
        placeholder="Username"
        value={userName}
        onChange={e => setUserName(e.target.value)}
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />

      <br /><br />

      <button onClick={handleSignup} disabled={loading}>
        {loading ? "Creating..." : "Signup"}
      </button>
    </div>
  );
}
