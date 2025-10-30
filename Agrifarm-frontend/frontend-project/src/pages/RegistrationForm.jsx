import React, {useState} from "react";

export default function RegistrationForm() {
  const [f,setF] = useState({name:"", email:"", password:""});
  const [msg,setMsg] = useState("");

  const handleSubmit = async (e) => {
  e.preventDefault();
  const res = await fetch("/api/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(form),
  });
  if (res.ok) {
    alert("User registered successfully!");
  } else {
    alert("Registration failed!");
  }
};


  return (
    <form onSubmit={submit}>
      <h2>Register as Farmer</h2>
      <input placeholder="Name" value={f.name} onChange={e=>setF({...f,name:e.target.value})} required/>
      <input placeholder="Email" value={f.email} onChange={e=>setF({...f,email:e.target.value})} type="email" required/>
      <input placeholder="Password" value={f.password} onChange={e=>setF({...f,password:e.target.value})} type="password" required/>
      <button type="submit">Register</button>
      <div>{msg}</div>
    </form>
  );
}

