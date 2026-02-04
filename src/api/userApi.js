const BASE_URL = "http://localhost:8080";

/* ======================
   🔐 LOGIN
====================== */
export async function loginUser(userName, password) {
  const res = await fetch(`${BASE_URL}/user/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userName, password }),
  });

  if (!res.ok) throw new Error("Login failed");
  return res.json();
}

/* ======================
   👤 SIGNUP
====================== */
export async function signupUser(user) {
  const res = await fetch(`${BASE_URL}/user/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });

  if (!res.ok) throw new Error("Signup failed");
  return res.text();
}

/* ======================
   🧍 CREATE CUSTOMER PROFILE (ONE TIME)
   POST /customer
====================== */
export async function createCustomerProfile(data) {
  const res = await fetch(`${BASE_URL}/customer`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error("Create customer failed");
  return res.json();
}

/* ======================
   🔄 UPDATE CUSTOMER PROFILE (EDIT)
   PUT /customer/{userId}
====================== */
export async function updateCustomerProfile(userId, data) {
  const res = await fetch(`${BASE_URL}/customer/${userId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error("Update customer failed");
  return res.json();
}

/* ======================
   🔍 GET CUSTOMER BY USER
====================== */
export async function getCustomerByUser(userId) {
  const res = await fetch(`${BASE_URL}/customer/user/${userId}`);
  if (!res.ok) return null;
  return res.json();
}

/* ======================
   📅 BOOK APPOINTMENT
====================== */
export async function bookAppointment(data) {
  const res = await fetch(`${BASE_URL}/appointments/book`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  if (!res.ok) throw new Error("Booking failed");
  return res.json();
}

/* ======================
   💇 GET ALL SERVICES
====================== */
export async function getAllServices() {
  const res = await fetch(`${BASE_URL}/services`);
  if (!res.ok) throw new Error("Failed to fetch services");
  return res.json();
}
