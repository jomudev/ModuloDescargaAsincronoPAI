# Asynchronous File Download Module (Java MVC & Concurrency)

![Java](https://img.shields.io/badge/Java-Concurrency-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Architecture](https://img.shields.io/badge/Pattern-MVC-10B981?style=flat-square)
![Maven](https://img.shields.io/badge/Build-Apache_Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)

## Executive Summary

A **Java & Maven** asynchronous file download simulator built with **MVC architecture**, demonstrating background thread execution, simulated progress tracking, and explicit timeout fault handling (`TiempoExcedidoException`).

---

## Engineering Highlights

- **Strict MVC Separation:**
  - **Model (`Modelo/ArchivoDescarga.java`):** Represents download metadata, size, and transfer progress state.
  - **Controller (`Controlador/DescargaController.java`):** Orchestrates asynchronous background workers and coordinates UI state updates.
  - **View (`Vista/DescargaForm.java`):** Responsive Swing interface displaying live download status.
- **Fault Tolerance (`Excepciones/TiempoExcedidoException.java`):** Custom checked exception handling network/execution timeout scenarios gracefully.

---

## Project Structure

```text
ModuloDescargaAsincronoPAI/
├── pom.xml
└── src/main/java/
    ├── Controlador/DescargaController.java
    ├── Excepciones/TiempoExcedidoException.java
    ├── Modelo/ArchivoDescarga.java
    ├── Vista/DescargaForm.java
    └── com/mycompany/modulodescargaasincrono/ModuloDescargaAsincrono.java
```

---

## Getting Started

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="com.mycompany.modulodescargaasincrono.ModuloDescargaAsincrono"
```

---

## Author & Contact

**Héctor Joann Murillo Mejía** — Mobile & Full-Stack Software Engineer (React Native · TypeScript · Node.js)

- **Email:** [joannmurillo@icloud.com](mailto:joannmurillo@icloud.com)
- **LinkedIn:** [linkedin.com/in/hector-joann-murillo-mejia](https://www.linkedin.com/in/hector-joann-murillo-mejia/)
- **GitHub:** [github.com/jomudev](https://github.com/jomudev)
- **Portfolio:** [joannmurillo.com](https://www.joannmurillo.com)
