import { useEffect, useState } from "react";
import "../styles/profile.css";

export default function CustomerProfileForm({ initialData, onSave }) {

  const [form, setForm] = useState({});
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [saving, setSaving] = useState(false);

  // 🔑 Sync form when initialData changes
  useEffect(() => {
    if (!initialData) return;

    setForm({
      customerName: initialData.customerName || "",
      phoneNumber: initialData.phoneNumber || "",
      gender: initialData.gender || "",
      dateOfBirth: initialData.dateOfBirth
        ? initialData.dateOfBirth.slice(0, 10) // 🔥 date fix
        : "",
      addressLine1: initialData.addressLine1 || "",
      addressLine2: initialData.addressLine2 || "",
      city: initialData.city || "",
      state: initialData.state || "",
      pincode: initialData.pincode || "",
      country: initialData.country || "",
      hairType: initialData.hairType || "",
      skinType: initialData.skinType || "",
      preferences: initialData.preferences || "",
      allergies: initialData.allergies || "",
    });
  }, [initialData]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    try {
      setSaving(true);
      setError("");
      await onSave(form);               // 🔥 PUT API
      setMessage("✅ Profile updated successfully");
    } catch (err) {
      console.error(err);
      setError("❌ Profile update failed");
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="profile-wrapper">
      <div className="profile-card">
        <h2>Edit Profile ✏️</h2>

        {/* BASIC INFO */}
        <section>
          <h4>Basic Information</h4>

          <input
            name="customerName"
            value={form.customerName || ""}
            onChange={handleChange}
            placeholder="Full Name"
          />

          <input
            name="phoneNumber"
            value={form.phoneNumber || ""}
            onChange={handleChange}
            placeholder="Phone Number"
          />

          <select
            name="gender"
            value={form.gender || ""}
            onChange={handleChange}
          >
            <option value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
          </select>

          <input
            type="date"
            name="dateOfBirth"
            value={form.dateOfBirth || ""}
            onChange={handleChange}
          />
        </section>

        {/* ADDRESS */}
        <section>
          <h4>Address</h4>
          <input name="addressLine1" value={form.addressLine1 || ""} onChange={handleChange} placeholder="Address Line 1" />
          <input name="addressLine2" value={form.addressLine2 || ""} onChange={handleChange} placeholder="Address Line 2" />
          <input name="city" value={form.city || ""} onChange={handleChange} placeholder="City" />
          <input name="state" value={form.state || ""} onChange={handleChange} placeholder="State" />
          <input name="pincode" value={form.pincode || ""} onChange={handleChange} placeholder="Pincode" />
          <input name="country" value={form.country || ""} onChange={handleChange} placeholder="Country" />
        </section>

        {/* SALON */}
        <section>
          <h4>Salon Preferences</h4>
          <input name="hairType" value={form.hairType || ""} onChange={handleChange} placeholder="Hair Type" />
          <input name="skinType" value={form.skinType || ""} onChange={handleChange} placeholder="Skin Type" />
          <input name="preferences" value={form.preferences || ""} onChange={handleChange} placeholder="Preferences" />
          <input name="allergies" value={form.allergies || ""} onChange={handleChange} placeholder="Allergies" />
        </section>

        <button
          className="save-btn"
          onClick={handleSave}
          disabled={saving}
        >
          {saving ? "Saving..." : "Save Profile"}
        </button>

        {message && <p className="success">{message}</p>}
        {error && <p className="error">{error}</p>}
      </div>
    </div>
  );
}
