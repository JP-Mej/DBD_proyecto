# Sistema de Evaluación Virtual

Este proyecto implementa una solución integral para la gestión y aplicación de evaluaciones virtuales. Incluye  un backend desarrollado en Java con Maven, que permite la creación, distribución y evaluación automatizada de exámenes, con conexion a una BD MySQL.

---

## 📁 Estructura del Proyecto

dbd/
├── pom.xml # Archivo de configuración Maven
├── src/
│ ├── main/
│ │ ├── java/ # Código fuente Java
│ │ └── resources/ # Recursos de configuración, SQL, etc.
│ └── test/ # Pruebas unitarias (si están definidas)
├── .git/ # Carpeta del repositorio Git





## 1. 🗃️ Diseño de la Base de Datos (MySQL)

La base de datos ha sido diseñada para manejar eficientemente todos los elementos involucrados en el proceso de evaluación:

- **Preguntas**: 
  - Enunciado
  - Nivel de dificultad
  - Etiquetas temáticas

- **Respuestas**:
  - Asociadas a preguntas
  - Respuesta correcta indicada para autocorrección

- **Exámenes**:
  - Conjuntos organizados de preguntas
  - Opciones para aleatorización y cronometraje

- **Estudiantes**:
  - Información de autenticación
  - Seguimiento del progreso

- **Resultados y estadísticas**:
  - Calificaciones almacenadas
  - Retroalimentación automática detallada

---

## 2. 💻 Prototipo de Página Web

Actualmente no se ha identificado una interfaz web dentro del proyecto, pero se contempla:

- Acceso seguro mediante login
- Panel de usuario con exámenes disponibles
- Entorno de evaluación compatible con móviles
- Resultados inmediatos al finalizar
- Retroalimentación con aciertos, errores y sugerencias

---

## 3. ⚙️ Backend y Lógica de Negocio

El backend, desarrollado en **Java con Maven**, se encarga de:

- Generar exámenes dinámicamente desde el banco de preguntas
- Calificar automáticamente las preguntas objetivas
- Permitir revisión manual para respuestas abiertas
- Controlar tiempo, intentos y sesiones activas
- Registrar toda la actividad del estudiante

---


