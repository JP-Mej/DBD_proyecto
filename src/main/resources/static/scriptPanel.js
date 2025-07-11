document.addEventListener("DOMContentLoaded", () => {
  const sesionActiva = JSON.parse(localStorage.getItem("sesionActiva"));

  if (!sesionActiva) {
    window.location.href = "InicioSesión.html";
    return;
  }

  const userInfoList = document.querySelector(".user-info ul");
  if (userInfoList) {
    userInfoList.innerHTML = `
      <li><strong>Usuario:</strong> ${sesionActiva.user}</li>
      <li><strong>Nombres:</strong> ${sesionActiva.firstName} ${sesionActiva.middleName || ''}</li>
      <li><strong>Apellidos:</strong> ${sesionActiva.lastNameFather} ${sesionActiva.lastNameMother}</li>
    `;
  }

  fetch(`/api/evaluations/${sesionActiva.user}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("No se pudieron cargar las evaluaciones");
      }
      return response.json();
    })
    .then(evaluations => {
      const resultList = document.querySelector(".result-list");
      if (!resultList) return;

      resultList.innerHTML = "";

      // Mostrar solo las 4 últimas evaluaciones
      const ultimas = evaluations.slice(-4).reverse(); // ← últimas 4, ordenadas descendente

      ultimas.forEach(ev => {
        const item = document.createElement("div");
        item.classList.add("result-item");

        item.innerHTML = `
          <div class="result-details">
            <p><strong>Nombre del Examen:</strong> ${ev.resultadoNombre}</p>
            <p><strong>Nota:</strong> ${ev.nota}</p>
          </div>
        `;

        resultList.appendChild(item);
      });
    })
    .catch(error => {
      console.error("Error al cargar evaluaciones:", error);
      alert("Ocurrió un error al cargar las evaluaciones del estudiante.");
    });

  const logoutBtn = document.querySelector(".logout-btn");
  if (logoutBtn) {
    logoutBtn.addEventListener("click", () => {
      localStorage.removeItem("sesionActiva");
      window.location.href = "InicioSesión.html";
    });
  }
});
