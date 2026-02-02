# P1_AMC: Estrategias Algorítmicas y Análisis de Eficiencia

![Java](https://img.shields.io/badge/Java-JDK%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IDE](https://img.shields.io/badge/IDE-NetBeans%2022-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

Repositorio de la **Práctica 1 de la asignatura Algorítmica y Modelos de Computación (AMC)**. Este proyecto implementa y compara diferentes estrategias algorítmicas para resolver el clásico **Problema de los Pares de Puntos más Cercanos** en un plano 2D.

El objetivo principal es realizar un estudio empírico de la eficiencia temporal, contrastando la complejidad teórica con los resultados reales de ejecución.

## 📖 Descripción del Problema

Dado un conjunto de puntos $P = \{(x_1, y_1), \dots, (x_n, y_n)\}$, el objetivo es encontrar el par de puntos $(p_i, p_j)$ cuya distancia euclídea sea mínima.

El proyecto aborda este problema desde cuatro enfoques distintos para demostrar cómo la elección del algoritmo impacta en el rendimiento cuando el tamaño del problema ($n$) crece.

## 🚀 Algoritmos Implementados

Se han desarrollado 4 estrategias comparables:

1.  **Exhaustivo (Brute Force):**
    * Compara todos los pares posibles.
    * Complejidad: $O(n^2)$.
    * *Uso:* Base para validar la corrección de algoritmos más complejos.

2.  **Exhaustivo con Poda:**
    * Mejora del algoritmo básico ordenando los puntos por la coordenada X.
    * Descarta cálculos si la distancia en X ya supera la mínima actual.
    * *Nota:* Eficiente en casos medios, pero degenera a $O(n^2)$ en el peor caso.

3.  **Divide y Vencerás (DyV):**
    * Divide el conjunto de puntos en mitades recursivamente.
    * Resuelve el problema en cada mitad y combina las soluciones gestionando la "franja" central.
    * Complejidad: $O(n \log n)$.

4.  **Divide y Vencerás Mejorado (DyV Mejorado):**
    * Optimización de la fase de combinación del DyV.
    * Ordena los puntos de la franja por la coordenada Y, reduciendo drásticamente las comparaciones necesarias (solo compara con los vecinos siguientes en la franja).

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 21).
* **IDE:** NetBeans 22.
* **Interfaz Gráfica (GUI):** Java Swing (diseño de formularios y visualización).
* **Librerías Externas:**
    * `JFreeChart`: Para la generación de gráficas de rendimiento (Talla VS Tiempo).

## 📊 Funcionalidades de la Aplicación

1.  **Carga y Generación de Datos:**
    * Lectura de ficheros TSPLIB (`.tsp`).
    * Generación de nubes de puntos aleatorias.
    * **Modo "Peor Caso":** Generación de puntos alineados verticalmente para forzar el máximo coste en algoritmos de poda.
2.  **Visualización:**
    * Representación gráfica de los puntos y dibujo de la línea que une el par más cercano.
3.  **Comparativa de Rendimiento:**
    * Ejecución simultánea de los algoritmos.
    * Medición de **Tiempo (ms)** y **Número de Distancias Calculadas** (métrica independiente del hardware).
    * Gráficas comparativas generadas dinámicamente.

## 📸 Capturas de Pantalla
<table>
  <tr>
    <td> Interfaz Principal </td>
    <td> <img width="1905" height="1070" alt="P1_Pantalla_Principal" src="https://github.com/user-attachments/assets/abffbe7c-4a78-4cc1-9822-4890db3f3d77" /> </td>
  </tr>
  <tr>
    <td> Cargar Fichero </td>
    <td> <img width="720" height="422" alt="P1_Cargar_Fichero" src="https://github.com/user-attachments/assets/a9834543-75b4-48f2-98fd-91a99fe63663" /> </td>
  </tr>
  <tr>
    <td> Gráfica Comparación </td>
    <td> <img width="985" height="993" alt="Comparativa_Todos_Caso_Peor" src="https://github.com/user-attachments/assets/0cee134b-ab20-48ed-9688-4aafc3576828" /> </td>
  </tr>
  <tr>
    <td> Créditos </td>
    <td> <img width="626" height="671" alt="P1_Creditos" src="https://github.com/user-attachments/assets/d5abb408-57b7-4c57-aee7-9fcac962942c" /> </td>
  </tr>
</table>

## ⚙️ Instalación y Ejecución

### Requisitos Previos
* Java Development Kit (JDK) 21.
* NetBeans 22 (recomendado) o cualquier otro IDE compatible con proyectos Ant/Maven.

### Pasos para ejecutar

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/antonioabadpro/P1_AMC.git]
    ```
2.  **Abrir en NetBeans:**
    * `File` -> `Open Project` -> Seleccionar la carpeta clonada.
    * Si faltan librerías (como JFreeChart), asegúrate de añadirlas al *Library Path* del proyecto (suelen estar en la carpeta `lib` o gestionadas por dependencias).
3.  **Compilar y Correr:**
    * Ejecuta el archivo principal (Main Class) para lanzar la interfaz Swing.

## 📃​ Memoria Técnica del Proyecto

[![Memoria Técnica](https://img.shields.io/badge/PDF-Ver_Memoria_Técnica-EC1C24?style=for-the-badge&logo=adobeacrobatreader&logoColor=white)](./PRACTICA%201_ESTRATEGIAS%20ALGORÍTMICAS_AAHG.pdf)

> **Nota:** Haz clic en el botón de arriba para visualizar o descargar el análisis completo en PDF, donde se explican las diferentes estrategias utilizadas, las comparativas detalladas con sus respectivas tablas de tiempos y la conclusión.

## ​💭​ Conclusión
El estudio realizado (detallado en el documento adjunto) concluye que:
* Para tamaños pequeños ($n < 1000$), las diferencias son despreciables.
* Para tamaños grandes, los algoritmos **Divide y Vencerás** son exponencialmente más rápidos que los exhaustivos.
* El **DyV Mejorado** ofrece el mejor rendimiento general al minimizar las comparaciones en la fase de combinación.

## 👤 Autor

**Antonio Abad Hernández Gálvez**
* GitHub: [@antonioabadpro](https://github.com/antonioabadpro)
* *Proyecto realizado para la Universidad de Huelva (UHU)*

---

## ©️​ Licencia y Derechos de Autor

**© 2025. Antonio Abad Hernández Gálvez**. <br>
Todos los derechos reservados.

Este proyecto es propiedad intelectual de su autor. <br>
El código se proporciona únicamente con fines de **consulta y demostración de portfolio**.

El código fuente de este proyecto es propiedad exclusiva de su autor. 
Se permite su visualización con fines educativos y de evaluación académica.

⛔ **Prohibido su uso:** Queda estrictamente prohibida su reproducción total o parcial, modificación, distribución o uso para fines comerciales o académicos por parte de terceros sin la autorización expresa y por escrito del autor.

Este proyecto es el resultado de una evaluación académica para la Universidad de Huelva. <br>
El plagio o uso indebido de este código en otros proyectos académicos será reportado.
