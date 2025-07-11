document.addEventListener("DOMContentLoaded", () => {
  const linkRegistro = document.querySelector(".register-link a");
  if (linkRegistro) {
    linkRegistro.addEventListener("click", (e) => {
      e.preventDefault();
      window.location.href = "registro.html";
    });
  }

  const eyeButton = document.querySelector(".eye-icon");
  const passwordInput = document.getElementById("password");

  if (eyeButton && passwordInput) {
    eyeButton.addEventListener("click", () => {
      const isPassword = passwordInput.type === "password";
      passwordInput.type = isPassword ? "text" : "password";

      const icon = eyeButton.querySelector("svg path");
      if (icon) {
        icon.setAttribute("fill", isPassword ? "#007CF0" : "#6C757D");
      }
    });
  }

  const formLogin = document.querySelector(".login-form");
  if (formLogin) {
    formLogin.addEventListener("submit", async (e) => {
      e.preventDefault();

      const userInput = document.getElementById("username").value.trim();
      const passInput = document.getElementById("password").value;

      try {
        const response = await fetch("/api/InicioSesion", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            user: userInput,
            password: passInput
          })
        });


        if (response.ok) {
          const student = await response.json(); 
          // Si deseas guardar la sesión activa (puede ser con ID o username)
          localStorage.setItem("sesionActiva", JSON.stringify(student));
          window.location.href = "Panel.html";
        } else {
          const message = await response.text;
          alert(message); // Mensajes como "Contraseña incorrecta" o "Usuario no encontrado"
        }
      } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Error al conectar con el servidor");
      }
    });
  } else {
    console.error("No se encontró el formulario con clase .login-form");
  }
});
