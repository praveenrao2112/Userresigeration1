import React from "react";

export default function LoginPage() {
  return (
    <div>
      <h2>Login</h2>

      {/* Form login -> Spring Security default POST /login expects username & password */}
      <form method="post" action="http://localhost:8080/login">
        <input name="username" placeholder="Email" />
        <input name="password" type="password" placeholder="Password" />
        <button type="submit">Login (Email)</button>
      </form>

      <hr />

      {/* Initiate OAuth2 flow */}
      <a
  href="/oauth2/authorization/google"
  className="bg-red-500 hover:bg-red-600 text-white py-2 px-4 rounded-lg w-full inline-block"
>
  Login with Google
</a>


      {/* For Keycloak replace URL with /oauth2/authorization/keycloak */}
    </div>
  );
}