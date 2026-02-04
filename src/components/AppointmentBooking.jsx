import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { bookAppointment } from "../api/userApi";
import ServiceSelector from "./ServiceSelector";
import "../styles/booking.css";

export default function AppointmentBooking({
  customer,
  onLogout,
  hideHeader = false
}) {
  const navigate = useNavigate();

  // 🛡️ Safety guard
  if (!customer || !customer.id) {
    return <h3>Loading booking details...</h3>;
  }

  const [services, setServices] = useState([]);
  const [date, setDate] = useState("");
  const [startTime, setStartTime] = useState("");
  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);

  // 🔢 Calculations
  const totalDuration = services.reduce(
    (sum, s) => sum + s.durationMinutes,
    0
  );

  const totalPrice = services.reduce(
    (sum, s) => sum + s.price,
    0
  );

  // 📌 BOOK APPOINTMENT
  const handleBook = async () => {
    if (!date || !startTime || services.length === 0) {
      alert("Please select services, date and time");
      return;
    }

    try {
      setLoading(true);

      await bookAppointment({
        customerId: customer.id,
        serviceIds: services.map(s => s.id),
        appointmentDate: date,
        startTime: startTime
      });

      setMsg("✅ Appointment booked successfully");

      // reset
      setServices([]);
      setDate("");
      setStartTime("");

      // optional redirect
      // setTimeout(() => navigate("/dashboard"), 2000);

    } catch (err) {
      console.error(err);
      alert("Booking failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-container">

      {/* HEADER (hideable for mobile profile page) */}
      {!hideHeader && (
        <div className="app-header">
          <div>
            <h3>Salon Booking</h3>
            <small>{customer.customerName}</small>
          </div>

          <button className="logout-btn" onClick={onLogout}>
            Logout
          </button>
        </div>
      )}

      {/* CARD */}
      <div className="card">
        <ServiceSelector onChange={setServices} />

        <hr />

        <p><b>Total Time:</b> {totalDuration} mins</p>
        <p><b>Total Price:</b> ₹{totalPrice}</p>

        <input
          type="date"
          value={date}
          onChange={e => setDate(e.target.value)}
        />

        <input
          type="time"
          value={startTime}
          onChange={e => setStartTime(e.target.value)}
        />

        <button
          className="book-btn"
          onClick={handleBook}
          disabled={loading}
        >
          {loading ? "Booking..." : "Book Appointment"}
        </button>

        {msg && <p className="success-msg">{msg}</p>}
      </div>
    </div>
  );
}
