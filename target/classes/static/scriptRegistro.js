document.addEventListener("DOMContentLoaded", () => {
  const eyeIcon = document.querySelector(".eye-icon");
  const passwordInput = document.getElementById("password");

  if (eyeIcon && passwordInput) {
    eyeIcon.addEventListener("click", () => {
      const isPassword = passwordInput.type === "password";
      passwordInput.type = isPassword ? "text" : "password";

      const icon = eyeIcon.querySelector("svg path");
      icon.setAttribute("fill", isPassword ? "#007CF0" : "#6C757D");
    });
  }

  const cancelBtn = document.querySelector(".cancel-btn");
  if (cancelBtn) {
    cancelBtn.addEventListener("click", () => {
      window.location.href = "InicioSesión.html";
    });
  }

  const loginLink = document.querySelector(".login-link a");
  if (loginLink) {
    loginLink.addEventListener("click", (e) => {
      e.preventDefault();
      window.location.href = "InicioSesión.html";
    });
  }

  const form = document.querySelector(".register-form");
  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const usuario = document.getElementById("username").value.trim();
      const firstname = document.getElementById("name").value.trim();
      const middlename = document.getElementById("middlename").value.trim();
      const apellidoP = document.getElementById("lastnameP").value.trim();
      const apellidoM = document.getElementById("lastnameM").value.trim();
      const email = document.getElementById("email").value.trim();
      const contraseña = document.getElementById("password").value;

      const datos = {
        user: usuario,
        firstName: firstname,
        middleName: middlename,
        lastNameFather: apellidoP,
        lastNameMother: apellidoM,
        email: email,
        password: contraseña
      };

      try {
        const response = await fetch("/api/Registro", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(datos)
        });

        const message = await response.text();

        if (response.ok) {
          alert(message);
          window.location.href = "InicioSesión.html";
        } else {
          alert("Error: " + message);
        }
      } catch (error) {
        console.error("Error al registrar:", error);
        alert("Ocurrió un error al conectar con el servidor.");
      }
    });
  }
});
