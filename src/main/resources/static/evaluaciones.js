function iniciarEvaluacion() {
  const sesionActiva = JSON.parse(localStorage.getItem("sesionActiva"));
  if (!sesionActiva || !sesionActiva.user) {
    alert("Sesión no válida. Inicie sesión.");
    window.location.href = "InicioSesión.html";
    return;
  }

  fetch(`/api/create-evaluation/${sesionActiva.user}`, {
    method: "POST",
  })
    .then((response) => {
      if (!response.ok) throw new Error("Error al crear evaluación");
      return response.text();
    })
    .then((msg) => {
      const idEval = msg.match(/ID:\s*(\d+)/)?.[1];
      if (!idEval) {
        alert("No se pudo obtener el ID de evaluación.");
        return;
      }
      window.location.href = `hacerEvaluacion.html?evaluationId=${idEval}`;
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("No se pudo crear la evaluación.");
    });
}
