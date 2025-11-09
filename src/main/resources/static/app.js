const apiBase = "/api";

// --- Patients ---
async function loadPatients() {
  const res = await fetch(`${apiBase}/patients`);
  const patients = await res.json();
  const tbody = document.querySelector("#patients-table tbody");
  tbody.innerHTML = "";
  patients.forEach(p => {
    const tr = document.createElement("tr");
    tr.innerHTML = `<td>${p.id}</td><td>${p.name}</td><td>${p.age}</td>`;
    tbody.appendChild(tr);
  });
}

async function addPatient() {
  const name = document.getElementById("patient-name").value;
  const age = document.getElementById("patient-age").value;
  if(!name || !age) return alert("Remplir tous les champs !");
  await fetch(`${apiBase}/patients`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, age: parseInt(age) })
  });
  document.getElementById("patient-name").value = "";
  document.getElementById("patient-age").value = "";
  loadPatients();
}

// --- Doctors ---
async function loadDoctors() {
  const res = await fetch(`${apiBase}/doctors`);
  const doctors = await res.json();
  const tbody = document.querySelector("#doctors-table tbody");
  tbody.innerHTML = "";
  doctors.forEach(d => {
    const tr = document.createElement("tr");
    tr.innerHTML = `<td>${d.id}</td><td>${d.name}</td><td>${d.specialty}</td>`;
    tbody.appendChild(tr);
  });
}

async function addDoctor() {
  const name = document.getElementById("doctor-name").value;
  const specialty = document.getElementById("doctor-specialty").value;
  if(!name || !specialty) return alert("Remplir tous les champs !");
  await fetch(`${apiBase}/doctors`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, specialty })
  });
  document.getElementById("doctor-name").value = "";
  document.getElementById("doctor-specialty").value = "";
  loadDoctors();
}

// --- Appointments ---
async function loadAppointments() {
  const res = await fetch(`${apiBase}/appointments`);
  const apps = await res.json();
  const tbody = document.querySelector("#appointments-table tbody");
  tbody.innerHTML = "";
  apps.forEach(a => {
    const tr = document.createElement("tr");
    tr.innerHTML = `<td>${a.id}</td><td>${a.patientId}</td><td>${a.doctorId}</td><td>${a.date}</td>`;
    tbody.appendChild(tr);
  });
}

async function addAppointment() {
  const patientId = document.getElementById("appointment-patientId").value;
  const doctorId = document.getElementById("appointment-doctorId").value;
  const date = document.getElementById("appointment-date").value;
  if(!patientId || !doctorId || !date) return alert("Remplir tous les champs !");
  await fetch(`${apiBase}/appointments`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ patientId, doctorId, date })
  });
  document.getElementById("appointment-patientId").value = "";
  document.getElementById("appointment-doctorId").value = "";
  document.getElementById("appointment-date").value = "";
  loadAppointments();
}

// Load all on page load
loadPatients();
loadDoctors();
loadAppointments();