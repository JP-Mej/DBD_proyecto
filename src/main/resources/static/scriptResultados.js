document.addEventListener("DOMContentLoaded", () => {
  const sesionActiva = JSON.parse(localStorage.getItem("sesionActiva"));

  if (!sesionActiva) {
    window.location.href = "InicioSesión.html";
    return;
  }

  const tbody = document.getElementById("evaluaciones-body");
  if (!tbody) {
    console.error("No se encontró el elemento tbody con id 'evaluaciones-body'");
    return;
  }

  fetch(`/api/evaluations/${sesionActiva.user}`)
    .then(response => {
      if (!response.ok) throw new Error("No se pudieron cargar las evaluaciones");
      return response.json();
    })
    .then(evaluations => {
      console.log("Evaluaciones recibidas:", evaluations);

      tbody.innerHTML = "";

      evaluations.forEach(ev => {
        const tr = document.createElement("tr");

        const tdNombre = document.createElement("td");
        tdNombre.textContent = ev.resultadoNombre;
        tdNombre.classList.add("nombre-eval");

        const tdNota = document.createElement("td");
        tdNota.textContent = ev.nota;
        tdNota.classList.add("nota");

        // Crear celda para el botón
        const tdBoton = document.createElement("td");
        const btnDetalle = document.createElement("button");
        btnDetalle.textContent = "Ver Detalles";
        btnDetalle.classList.add("btn-detalle"); // puedes agregar estilos a esta clase si quieres
        // Al hacer click, redirige a la página de detalles con el id en query param
        btnDetalle.addEventListener("click", () => {
          window.location.href = `VerDetalles.html?evaluationId=${ev.id}`;
        });
        tdBoton.appendChild(btnDetalle);

        tr.appendChild(tdNombre);
        tr.appendChild(tdNota);
        tr.appendChild(tdBoton);

        tbody.appendChild(tr);
      });
    })
    .catch(error => {
      console.error("Error al cargar evaluaciones:", error);
      alert("Ocurrió un error al cargar las evaluaciones del estudiante.");
    });
});
