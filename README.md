# Proyecto API de Mitos y Leyendas de Halloween

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

## Descripción

Esta API permite la gestión un base de datos de preguntas sobre de mitos y leyendas de Halloween, almacenando atributos como categorías y niveles de dificultad. Está documentada con **Swagger** para facilitar su uso y pruebas.

## Características

- **Registro y gestión de preguntas** con atributos personalizados.
- **Gestión de categorías** de preguntas.
- **Paginación** en las listas de preguntas.
- **Consultas personalizadas** por atributos como dificultad y categoría.
- **Swagger UI** para la documentación interactiva de la API.

## Endpoints Principales

### Preguntas

- **POST** `/questions`: Registrar una nueva pregunta.
- **GET** `/questions`: Listar preguntas con paginación.
- **GET** `/questions/{id}`: Obtener los detalles de una pregunta por su ID.
- **PUT** `/questions/{id}`: Actualizar los datos de una pregunta.
- **DELETE** `/questions/{id}`: Eliminar una pregunta.

### Búsquedas y Comparaciones

- **GET** `/questions/quiz/categories`: Obtener todas las categorías de preguntas.
- **GET** `/questions/quiz/difficulty-levels`: Obtener todos los niveles de dificultad.
- **GET** `/questions/quiz/random`: Obtener preguntas aleatorias por nivel de dificultad.

### Categorías

- **GET** `/questions/categories`: Listar todas las categorías.
- **GET** `/questions/categories/{id}`: Obtener los detalles de una categoría por su ID.
- **POST** `/questions/categories`: Crear una nueva categoría.
- **PUT** `/questions/categories/{id}`: Actualizar los datos de una categoría.
- **DELETE** `/questions/categories/{id}`: Eliminar una categoría.

## Documentación con Swagger 📜

La documentación de la API está disponible en el endpoint `/swagger-ui.html`, donde puedes probar los diferentes endpoints y ver la estructura de los datos.

## Tecnologías Utilizadas 💻

- **Java 21**
- **Spring Boot**
- **Swagger** para la documentación de la API.
- **JPA** para la persistencia de datos.
- **PostgreSQL** para la base de datos.

## Requisitos

- **Java JDK 21**
- **Maven 3**
- **PostgreSQL 14**

## Pasos para ejecutar el proyecto 🚀

1. **Instalar las dependencias** necesarias.

   Asegúrate de tener instalados Java JDK 21, Maven 3 y PostgreSQL 14.

2. **Configurar la base de datos**.

   Crea una base de datos en PostgreSQL y actualiza las credenciales en el archivo `application.properties`.

3. **Construir el proyecto** con Maven.

   ```sh
   mvn clean package
   ```

4. **Ejecutar el JAR** generado.

   ```sh
   java -jar target/nombre-del-jar-generado.jar
   ```

5. **Acceder a la documentación de la API** en `http://localhost:8080/swagger-ui.html`.
