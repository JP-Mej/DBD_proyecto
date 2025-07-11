document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const evaluationId = params.get("evaluationId");

  if (!evaluationId) {
    alert("ID de evaluación no especificado");
    return;
  }

  fetch(`/api/evaluation-details/${evaluationId}`)
    .then(response => {
      if (!response.ok) throw new Error("Error al cargar detalles de evaluación");
      return response.json();
    })
    .then(data => {
      console.log("🧾 Evaluación completa:", data);

      document.querySelector(".eval-nombre").textContent = data.nombreEvaluacion || "Sin nombre";

      document.querySelector(".nota-box").textContent = 
        (data.nota !== null && data.nota !== undefined) ? `${data.nota}/100` : "Sin nota";

      const main = document.querySelector("main");

      // Eliminar preguntas previas
      const preguntasViejas = main.querySelectorAll(".pregunta");
      preguntasViejas.forEach(el => el.remove());

      if (!Array.isArray(data.preguntas)) {
        console.warn("No hay preguntas para mostrar.");
        return;
      }

      data.preguntas.forEach((pregunta, index) => {
        console.log(`📌 Pregunta ${index + 1}:`, pregunta.texto);
        console.log(`  - Número: ${pregunta.numero}`);
        console.log(`  - ¿Correcta?: ${pregunta.esCorrecta}`);
        console.log("  - Alternativas:");

        if (!Array.isArray(pregunta.alternativas)) {
          console.warn(`Pregunta ${pregunta.numero} no tiene alternativas.`);
          return;
        }

        pregunta.alternativas.forEach((alt, i) => {
          console.log(`    🔸 [${i + 1}] ${alt.descripcion}`);
          console.log(`        - Es correcta: ${alt.esCorrecta}`);
          console.log(`        - Seleccionada por estudiante: ${alt.esSeleccionada}`);
        });

        // Verificar si alguna alternativa está seleccionada
        const algunaSeleccionada = pregunta.alternativas.some(alt => alt.esSeleccionada);

        // Marcar estado general de la pregunta
        const estadoPreguntaCorrecta = pregunta.esCorrecta && algunaSeleccionada;

        const estadoClass = estadoPreguntaCorrecta ? "correcta" : "incorrecta";
        const estadoTexto = estadoPreguntaCorrecta ? "Correcta" : "Incorrecta";
        const icono = estadoPreguntaCorrecta ? "&#10003;" : "&#10006;";

        // Crear contenedor pregunta
        const preguntaDiv = document.createElement("div");
        preguntaDiv.classList.add("pregunta");

        const header = document.createElement("div");
        header.classList.add("pregunta-header");
        header.innerHTML = `
          <span class="numero">(${pregunta.numero})</span>
          <span class="texto">${pregunta.texto}</span>
          <span class="estado ${estadoClass}">${estadoTexto} <span class="icon">${icono}</span></span>
        `;
        preguntaDiv.appendChild(header);

        const alternativasDiv = document.createElement("div");
        alternativasDiv.classList.add("alternativas");

        pregunta.alternativas.forEach(alt => {
          const altDiv = document.createElement("div");
          altDiv.classList.add("alternativa");

          if (alt.esCorrecta && alt.esSeleccionada) {
            // Correcta y seleccionada → marcada correcta
            altDiv.classList.add("correcta");
          } else if (!alt.esCorrecta && alt.esSeleccionada) {
            // Incorrecta y seleccionada → marcada incorrecta y tachada
            altDiv.classList.add("incorrecta");
            altDiv.style.textDecoration = "line-through";
          }

          // Caso: ninguna seleccionada → marcar la alternativa correcta en verde
          if (!algunaSeleccionada && alt.esCorrecta) {
            altDiv.classList.add("correcta");
          }

          altDiv.innerHTML = `
            <input type="checkbox" disabled ${alt.esSeleccionada ? "checked" : ""}>
            <span>${alt.descripcion}</span>
          `;

          alternativasDiv.appendChild(altDiv);
        });

        preguntaDiv.appendChild(alternativasDiv);
        main.appendChild(preguntaDiv);
      });
    })
    .catch(error => {
      console.error(error);
      alert("No se pudo cargar la evaluación.");
    });
});
