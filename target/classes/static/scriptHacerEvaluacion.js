document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const evaluationId = params.get("evaluationId");

  if (!evaluationId) {
    alert("Evaluación no especificada.");
    window.location.href = "evaluaciones.html";
    return;
  }

  const contenedor = document.getElementById("contenedor-preguntas");
  let preguntasData = [];

  // Cargar preguntas de evaluación
  fetch(`/api/evaluation-details/${evaluationId}`)
    .then((res) => {
      if (!res.ok) throw new Error("No se pudo obtener la evaluación");
      return res.json();
    })
    .then((data) => {
      preguntasData = data.preguntas;

      preguntasData.forEach((pregunta) => {
        const div = document.createElement("div");
        div.classList.add("form-card");

        const opcionesHTML = pregunta.alternativas
          .map((op, i) => `
            <label>
              <input type="radio" name="p${pregunta.numero}" value="${i}">
              ${op.descripcion}
            </label>
          `)
          .join("");

        div.innerHTML = `
          <h2>${pregunta.numero}. ${pregunta.texto}</h2>
          <input type="hidden" name="detailId-${pregunta.numero}" value="${pregunta.detailEvaluationId}">
          <div class="opciones">${opcionesHTML}</div>
        `;

        contenedor.appendChild(div);
      });
    })
    .catch((error) => {
      console.error("Error al obtener preguntas:", error);
      alert("No se pudieron cargar las preguntas.");
    });

  // Envío del formulario
  const form = document.getElementById("form-evaluacion");
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const respuestas = [];

    preguntasData.forEach((pregunta) => {
      const seleccionada = document.querySelector(`input[name="p${pregunta.numero}"]:checked`);
      if (seleccionada) {
        const index = parseInt(seleccionada.value);
        const alternativa = pregunta.alternativas[index];

        respuestas.push({
          detailEvaluationId: pregunta.detailEvaluationId,
          answerId: alternativa.idRespuesta
        });
      }
    });

    if (respuestas.length === 0) {
      alert("Debe seleccionar al menos una respuesta.");
      return;
    }

    try {
      const response = await fetch("/api/respuestas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(respuestas)
      });

      if (response.ok) {
        const modal = document.getElementById("modal-exito");
        if (modal) {
          modal.classList.add("mostrar");
        } else {
          alert("Respuestas guardadas exitosamente.");
        }

        setTimeout(() => {
          window.location.href = "Panel.html";
        }, 4000);
      } else {
        const text = await response.text();
        console.error("Respuesta del servidor:", text);
        alert("Error al guardar respuestas.");
      }
    } catch (err) {
      console.error("Error al enviar respuestas:", err);
      alert("Error de red al guardar respuestas.");
    }
  });
});
