import { useState } from "react";
import AppointmentBooking from "./AppointmentBooking";
import ProfileEdit from "./ProfileEdit";

export default function Home({ customer, user }) {
  const [activeTab, setActiveTab] = useState("booking");

  return (
    <div style={{ paddingBottom: 60 }}>
      {activeTab === "booking" && (
        <AppointmentBooking customerId={customer.id} />
      )}

      {activeTab === "profile" && (
        <ProfileEdit customer={customer} user={user} />
      )}

      {/* 🔽 Bottom Navigation */}
      <div style={nav}>
        <button onClick={() => setActiveTab("booking")}>🏠 Booking</button>
        <button onClick={() => setActiveTab("profile")}>👤 Profile</button>
      </div>
    </div>
  );
}

const nav = {
  position: "fixed",
  bottom: 0,
  left: 0,
  right: 0,
  height: 50,
  display: "flex",
  justifyContent: "space-around",
  alignItems: "center",
  background: "#eee",
  borderTop: "1px solid #ccc",
};
