import { useEffect, useState } from "react";
import { getAllServices } from "../api/userApi";
import "../styles/profile.css";

/* 🔥 TEMP IMAGE MAP (FRONTEND ONLY) */
const serviceImageMap = {
  "Haircut": "/images/hairstyle.jpg",
  "Hair Coloring": "/images/haircolur.jpg",
  "Hair Spa": "/images/hair-spa.jpg",
  "Beard": "/images/beardgroom.jpg",
  "Facial": "/images/face.jpg",
  "Head Massage": "/images/headmassage.jpg",
  "Keratin": "/images/keratin.jpg"
};

export default function ServiceSelector({ onChange }) {
  const [services, setServices] = useState([]);
  const [selected, setSelected] = useState([]);

  useEffect(() => {
    getAllServices().then(setServices);
  }, []);

  /* 🔹 GROUP SERVICES BY CATEGORY */
  const grouped = services.reduce((acc, s) => {
    const cat = s.category || "Other";
    acc[cat] = acc[cat] || [];
    acc[cat].push(s);
    return acc;
  }, {});

  /* 🔹 SELECT / UNSELECT */
  const toggle = (service) => {
    let updated;
    if (selected.find(x => x.id === service.id)) {
      updated = selected.filter(x => x.id !== service.id);
    } else {
      updated = [...selected, service];
    }
    setSelected(updated);
    onChange(updated);
  };

  return (
    <div className="service-selector">
      <h4>Select Services</h4>

      {Object.keys(grouped).map(cat => (
        <div key={cat} className="service-category">
          <h5>{cat}</h5>

          <div className="service-card-row">
            {grouped[cat].map(s => {
              const active = selected.find(x => x.id === s.id);

              return (
                <div
                  key={s.id}
                  className={`service-card ${active ? "active" : ""}`}
                  onClick={() => toggle(s)}
                >
                  <img
                    src={serviceImageMap[cat] || "/images/default-service.jpg"}
                    alt={s.name}
                  />

                  <div className="service-info">
                    <b>{s.name}</b>
                    <span>⏱ {s.durationMinutes} min</span>
                    <span>₹ {s.price}</span>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      ))}
    </div>
  );
}
