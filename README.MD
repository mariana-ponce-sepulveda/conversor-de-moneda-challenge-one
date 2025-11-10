# 💱 Conversor de Monedas - Proyecto Java


[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Gson](https://img.shields.io/badge/Gson-2.10.1-blue)](https://github.com/google/gson)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![Estado](https://img.shields.io/badge/Estado-En%20Desarrollo-yellow)]()

---

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estado del proyecto](#estado-del-proyecto)
3. [Demostración de funciones y aplicaciones](#demostración-de-funciones-y-aplicaciones)
4. [Acceso al Proyecto](#acceso-al-proyecto)
5. [Tecnologías utilizadas](#tecnologías-utilizadas)
6. [Personas Desarrolladoras del Proyecto](#personas-desarrolladoras-del-proyecto)
7. [Licencia](#licencia)

---

## Descripción del Proyecto
**Conversor de Monedas** es una aplicación de consola escrita en **Java 17** que permite convertir entre distintas divisas (USD, CLP, ARS, EUR, BRL, MXN, etc.) consultando tasas en **tiempo real** mediante la API ExchangeRate.  
Incluye una interfaz de consola enriquecida con **ASCII Art**, **emojis**, **colores ANSI**, animación de carga y un historial de conversiones. El código está organizado en paquetes (`app`, `modelo`, `servicio`, `util`) y documentado para fines educativos y de entrega.

---

## Estado del proyecto
- **Estado actual:** En desarrollo / funcional para demostración en consola.  
- **Funcionalidades completadas:**
  - Menú interactivo en consola con arte ASCII.
  - Consulta a ExchangeRate API con manejo básico de errores.
  - Validación de entradas de usuario (números, montos > 0).
  - Historial de conversiones en memoria.
  
- **Pendiente / Futuras mejoras:**
  - Guardado persistente del historial a `.txt` o `.json`.
  - Interfaz gráfica (Swing/JavaFX).
  - Manejo avanzado de errores y pruebas unitarias.
  - Internacionalización (soporte multi-idioma).

---

## 📁 Estructura del Proyecto

```
ConversorDeMonedas/
│
├── src/
│   └── com/conversor/
│       ├── app/
│       │   └── Principal.java
│       │
│       ├── model/
│       │   └── Moneda.java
│       │
│       ├── service/
│       │   └── Conversor.java
│       │
│       └── util/
│           └── ConsolaUtil.java
│
├── lib/
│   └── gson-2.10.1.jar   (librería externa para manejar JSON)
│
└── README.md
```

## Demostración de funciones y aplicaciones
A continuación se describen escenarios y ejemplos de uso:

1. **Inicio y menú**  
   Al ejecutar la aplicación se muestra un banner ASCII y un menú con opciones de conversión:
  
   ```
  ![Inicio](images/inicio.png)
   ```

2. **Conversión en tiempo real**  
   El usuario selecciona una opción (por ejemplo 1: USD → CLP) y proporciona un monto.  
   La aplicación consulta la API y muestra:
 
  ```
 ![Conversión](images/conversion.png)
   ```

3. **Historial**  
El usuario puede ver las conversiones realizadas durante la sesión:

 ```
 ![Historial](images/historial.png)
 
```
4. **Validacion del sistema**  
validacion del sistema solo adminte numero y mayor de 0 en la conversion:

 ```
 ![Validacion](images/validacion.png)
 
```  
4
 **Mensaje al salir del sistema**  
  
   ```
   ![Mensaje](images/mensaje.png)
   ```

---

## Acceso al Proyecto
### ✅ Ejecutar localmente (requisitos)
- Java JDK 17+ instalado.
- Conexión a internet.
- `gson-2.10.1.jar` en `lib/` (o usar gestor de dependencias).

### 📥 Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/conversor-monedas.git
cd conversor-monedas
```

### 🛠️ Compilar y ejecutar (Windows / consola)
```bash
javac -cp lib\gson-2.10.1.jar -d out src\com\conversor\**\*.java
java -cp "out;lib\gson-2.10.1.jar" com.conversor.app.Principal
```

---

## Tecnologías utilizadas
- **Java 17** (jdk)
- **Gson 2.10.1** (Google) — para parseo JSON
- **ExchangeRate API** — proveedor de tasas de cambio
- **Consola / ANSI colors** — salida visual en terminal
- Opcional: **IntelliJ IDEA** o **VS Code** como IDE

---


## Personas Desarrolladoras del Proyecto
- **Mariana Ponce** — Desarrollo principal, diseño de la interfaz de consola, integración API, documentación.

---

## Licencia
Este proyecto está disponible bajo la licencia **MIT**.  


---


