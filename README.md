# Proyecto API de Mitos y Leyendas de Halloween

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

## Descripción

Esta API permite la gestión de una base de datos de preguntas sobre de mitos y leyendas de Halloween, almacenando atributos como categorías y niveles de dificultad. Está documentada con **Swagger** para facilitar su uso y pruebas.

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

6. **(Opcional) Insertar datos de prueba**.

   Ejecuta el archivo `insert.sql` para insertar categorías de ejemplo:

   ```sh
   psql -U tu_usuario -d tu_base_de_datos -f src/main/resources/insert.sql
   ```

## Uso de la API

Esta API proporciona puntos finales para administrar y recuperar preguntas de trivia sobre criaturas sobrenaturales y seres míticos. A continuación se muestran los puntos finales disponibles y sus formatos de solicitud/respuesta esperados.

### Agregar Una Sola Pregunta

`POST /questions`

Agrega una nueva pregunta individual a la base de datos.

**Cuerpo de la Solicitud:**

```json
{
  "question": "¿Qué alimento es mortal para los vampiros?",
  "options": ["Ajo", "Cebolla", "Pimienta", "Sal"],
  "correctAnswer": "Ajo",
  "difficulty": "EASY",
  "category": "Vampiros"
}
```

**Campos obligatorios:**

- `question`: String - El texto de la pregunta
- `options`: Array of Strings - Posibles respuestas (4 opciones)
- `correctAnswer`: String - Debe coincidir con una de las opciones
- `difficulty`: String - Debe ser "EASY", "MEDIUM"o " HARD"
- `category`: String - La categoría de la pregunta

### Agregar Preguntas por Lotes

`POST /questions/batch`

Permite agregar múltiples preguntas en una sola solicitud.

**Cuerpo de la Solicitud:**

```json
[
  {...},
  {
    "question": "¿En qué fase lunar se transforma un hombre lobo?",
    "options": [
      "Luna llena",
      "Luna nueva",
      "Cuarto menguante",
      "Cuarto creciente"
    ],
    "correctAnswer": "Luna llena",
    "difficulty": "EASY",
    "category": "Hombres lobo"
  },
  {
    "question": "¿Cuál es el elemento más asociado con las brujas?",
    "options": ["Escoba voladora", "Varita", "Sombrero", "Caldero"],
    "correctAnswer": "Escoba voladora",
    "difficulty": "EASY",
    "category": "Brujas"
  }
]
```

La estructura de cada pregunta en la matriz sigue el mismo formato que el punto final de la pregunta única.

### Obtener Cuestionario Aleatorio

`GET /questions/quiz/random`

Recupera una selección aleatoria de preguntas para formar un cuestionario.

**Respuesta:**

```json
[
  {
    "id": 33,
    "question": "¿Qué objeto usan principalmente los hechiceros para canalizar su magia?",
    "options": ["Varita mágica", "Anillo", "Collar", "Brazalete"],
    "correctAnswer": "Varita mágica",
    "difficulty": "EASY",
    "category": "Hechiceros"
  },
  {
    "id": 7,
    "question": "¿De qué color suelen representarse los esqueletos fantasmales?",
    "options": ["Blanco", "Negro", "Rojo", "Verde"],
    "correctAnswer": "Blanco",
    "difficulty": "EASY",
    "category": "Esqueletos"
  },
  {
    "id": 34,
    "question": "¿Qué tipo de magia practican los nigromantes?",
    "options": [
      "Magia de muertos",
      "Magia elemental",
      "Magia curativa",
      "Magia de luz"
    ],
    "correctAnswer": "Magia de muertos",
    "difficulty": "EASY",
    "category": "Nigromantes"
  },
  {
    "id": 37,
    "question": "¿De qué tamaño son típicamente los trolls?",
    "options": ["Gigantes", "Pequeños", "Medianos", "Variables"],
    "correctAnswer": "Gigantes",
    "difficulty": "EASY",
    "category": "Trolls"
  },
  {
    "id": 6,
    "question": "¿Qué necesitan los zombis para sobrevivir según los mitos modernos?",
    "options": ["Cerebros", "Sangre", "Carne", "Huesos"],
    "correctAnswer": "Cerebros",
    "difficulty": "EASY",
    "category": "Zombis"
  }
]
```

**Campos de respuesta:**

- Incluye todos los campos del formato de pregunta
- `id`: Número-Identificador único para la pregunta

### Notas

- Todas las solicitudes y respuestas usan formato JSON
- Las preguntas deben tener 4 o 5 opciones
- La respuesta correcta debe coincidir exactamente con una de las opciones proporcionadas
- Las categorías deben ser relevantes para criaturas sobrenaturales / míticas
- Los niveles de dificultad están estandarizados como: "FÁCIL"," MEDIO","DIFÍCIL"
