import { useEffect, useState } from "react";
import { FaInstagram, FaFacebookF, FaWhatsapp } from "react-icons/fa";

import {
  getCustomerByUser,
  updateCustomerProfile
} from "../api/userApi";
import AppointmentBooking from "./AppointmentBooking";
import CustomerProfileForm from "./CustomerProfileForm";
import "../styles/profile.css";

export default function Profile({ user, onLogout }) {

  const [customer, setCustomer] = useState(null);
  const [loading, setLoading] = useState(true);

  const [edit, setEdit] = useState(false);
  const [showMenu, setShowMenu] = useState(false);
  const [activeTab, setActiveTab] = useState("home");

  /* 🔄 FETCH CUSTOMER AFTER LOGIN */
  useEffect(() => {
    if (!user?.userId) {
      setLoading(false);
      return;
    }

    getCustomerByUser(user.userId)
      .then(c => c && setCustomer(c))
      .finally(() => setLoading(false));
  }, [user]);

  /* 🔙 BACK BUTTON CONTROL (PREVENT LOGIN NAVIGATION) */
  useEffect(() => {
    window.history.pushState(null, "", window.location.pathname);

    const handleBack = () => {
      // stay in app / exit feel
      window.history.pushState(null, "", window.location.pathname);
    };

    window.addEventListener("popstate", handleBack);
    return () => window.removeEventListener("popstate", handleBack);
  }, []);

  /* ✅ SAVE PROFILE → GO HOME */
  const saveProfile = async (data) => {
    try {
      const updated = await updateCustomerProfile(
        user.userId,
        data
      );
      setCustomer(updated);
      setEdit(false);
      setActiveTab("home"); // 🔥 MAIN FIX
    } catch (err) {
      console.error("Profile update failed", err);
      alert("Profile update failed");
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div className="mobile-container">
{/* 🔝 TOP NAV */}
<div className="top-nav">
  <span className="menu" onClick={() => setShowMenu(true)}>☰</span>
  <b>Smart Saloon</b>
</div>

{/* 🌫 BACKDROP */}
{showMenu && (
  <div className="drawer-backdrop" onClick={() => setShowMenu(false)} />
)}

{/* 📂 SIDE DRAWER */}
<div className={`side-drawer ${showMenu ? "open" : ""}`}>
  <div className="drawer-header">
    <span className="drawer-logo">💈 Smart Saloon</span>
    <span className="close-btn" onClick={() => setShowMenu(false)}>✕</span>
  </div>

  <button onClick={() => { setActiveTab("home"); setShowMenu(false); }}>
    🏠 Home
  </button>

  <button onClick={() => { setActiveTab("home"); setShowMenu(false); }}>
    💈 Services
  </button>

  <button onClick={() => { setActiveTab("profile"); setShowMenu(false); }}>
    👤 Profile
  </button>

<button
  onClick={() => {
    setEdit(true);
    setActiveTab("profile");
    setShowMenu(false);
  }}
>
  ✏️ Edit Profile
</button>
  <button onClick={() => { setActiveTab("settings"); setShowMenu(false); }}>
    ℹ️ About
  </button>

  <span
    className="logout"
    onClick={() => {
      setShowMenu(false);
      onLogout();
    }}
  >
    🚪 Logout
  </span>
</div>

  
{/* 🏠 HOME */}
{activeTab === "home" && (
  <div className="profile-card">
    <h3>Welcome to Smart Saloon 💈</h3>

    <p>
      Industry-standard service with premium hygiene.
      Trusted by <b>100+ happy customers</b>.
    </p>

    {/* 🔥 SERVICE IMAGES */}
    <div className="service-row">
      <div className="service-item overlay">
        <img src="/images/hairstyle.jpg" alt="Hair Styling" />
        <div className="service-text">Hair Styling</div>
      </div>

      <div className="service-item overlay">
        <img src="/images/haircolur.jpg" alt="Hair Coloring" />
        <div className="service-text">Hair Coloring</div>
      </div>

      <div className="service-item overlay">
        <img src="/images/hair-spa.jpg" alt="Hair Spa" />
        <div className="service-text">Hair Spa</div>
      </div>

      <div className="service-item overlay">
        <img src="/images/beardgroom.jpg" alt="Beard Grooming" />
        <div className="service-text">Beard Grooming</div>
      </div>

      <div className="service-item overlay">
        <img src="/images/face.jpg" alt="Facial" />
        <div className="service-text">Facial</div>
      </div>
    </div>

    <p>✨ Innovation: Smart booking & personalized care.</p>
<p>🛡️ Hygiene-first salon experience with certified professionals.</p>
<p>⭐ Rated highly by our loyal and happy customers.</p>

{/* 🚀 BOOKING BUTTON */}
<button
  className="booking-btn"
  onClick={() => setActiveTab("booking")}
>
  📅 Start Booking
</button>

{/* 🔻 FOOTER */}
<div className="home-footer">
  <p className="footer-brand">Smart Saloon</p>
  <div className="footer-social">
    <a
      href="https://instagram.com"
      target="_blank"
      rel="noopener noreferrer"
      aria-label="Instagram"
    >
      <FaInstagram />
    </a>
<a
      href="https://facebook.com"
      target="_blank"
      rel="noopener noreferrer"
      aria-label="Facebook"
    >
      <FaFacebookF />
    </a>

    <a
      href="https://wa.me/919XXXXXXXXX"
      target="_blank"
      rel="noopener noreferrer"
      aria-label="WhatsApp"
    >
      <FaWhatsapp />
    </a>
  </div>

  <p className="footer-contact">
    📞 +91 000000000 <br />
    📍 Your City, India
  </p>

  <p className="footer-copy">
    © 2026 Smart Saloon. All rights reserved.
  </p>
</div>


  </div>
)}



      {/* 👤 PROFILE VIEW */}
      {activeTab === "profile" && !edit && customer && (
        <div className="profile-card">
          <h3>Customer Profile 👤</h3>

          <div className="profile-grid">
            <p><b>Name</b><span>{customer.customerName}</span></p>
            <p><b>Phone</b><span>{customer.phoneNumber}</span></p>
            <p><b>Gender</b><span>{customer.gender}</span></p>
            <p><b>Date of Birth</b><span>{customer.dateOfBirth}</span></p>

            <p>
              <b>Address</b>
              <span>
                {customer.addressLine1}<br />
                {customer.addressLine2}<br />
                {customer.city}, {customer.state} - {customer.pincode}<br />
                {customer.country}
              </span>
            </p>

            <p><b>Hair Type</b><span>{customer.hairType}</span></p>
            <p><b>Skin Type</b><span>{customer.skinType}</span></p>
            <p><b>Preferences</b><span>{customer.preferences}</span></p>
            <p><b>Allergies</b><span>{customer.allergies}</span></p>
          </div>
        </div>
      )}

      {/* ✏️ EDIT PROFILE */}
      {edit && (
        <CustomerProfileForm
          initialData={customer}
          onSave={saveProfile}
        />
      )}

      {/* 📅 BOOKING */}
      {activeTab === "booking" && (
        <AppointmentBooking
          customer={customer}
          hideHeader
        />
      )}
 {/* ⚙️ SETTINGS */}
      {activeTab === "settings" && (
        <div className="profile-card">
          <h3>Settings ⚙️</h3>
          <p>🔔 Notification preferences</p>
          <p>🌙 Dark mode (coming soon)</p>
          <p>📍 Preferred salon location</p>
          <p>📞 Support: +91 9XXXXXXXXX</p>
        </div>
      )}

      {/* 🔻 BOTTOM NAV (APP PILLAR) */}
      <div className="bottom-nav">
        <span
          className={activeTab === "home" ? "active" : ""}
          onClick={() => { setActiveTab("home"); setEdit(false); }}
        >🏠</span>

        <span
          className={activeTab === "profile" ? "active" : ""}
          onClick={() => { setActiveTab("profile"); setEdit(false); }}
        >👤</span>

        <span
          className={activeTab === "booking" ? "active" : ""}
          onClick={() => { setActiveTab("booking"); setEdit(false); }}
        >📅</span>

        <span
          className={activeTab === "settings" ? "active" : ""}
          onClick={() => { setActiveTab("settings"); setEdit(false); }}
        >⚙️</span>
      </div>

    </div>
  );
}
