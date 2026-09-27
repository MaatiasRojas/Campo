# Gestión de Campo

Aplicación de escritorio en Java para la gestión integral de un establecimiento agropecuario: control de animales, silos de alimento, tambos y registros de ordeñe.

## Tecnologías

- **Java 21**
- **JavaFX** — interfaz gráfica de escritorio
- **SQLite** — persistencia de datos embebida
- **Lombok** — reducción de código repetitivo
- **Maven** — gestión de dependencias y build

## Arquitectura

El proyecto sigue una arquitectura en capas:

- `entities/` — modelo de dominio (Animal, Vaca, Toro, Ternero, Silo, Tambo, etc.)
- `repositories/` — acceso a datos, con interfaces separadas de su implementación JDBC
- `services/` — lógica de negocio (en desarrollo)
- `controllers/` — controladores de las pantallas JavaFX/FXML

## Decisiones de diseño

- **Herencia con tabla única**: `Animal` es una clase abstracta con subtipos `Vaca`, `Toro` y `Ternero`. En la base de datos se modeló como una sola tabla con una columna `tipo` para discriminar el subtipo al reconstruir el objeto, evitando joins innecesarios para la escala del proyecto.
- **JDBC plano en vez de JPA/Hibernate**: se optó por JDBC directo porque Hibernate no tiene soporte oficial para SQLite, y para el alcance del proyecto el control manual de las consultas resultó más simple que lidiar con un ORM de terceros.
- **Repositorio genérico**: todas las interfaces de repositorio extienden una interfaz base `RepositorioGenerico<T, ID>` con las operaciones CRUD comunes.

## Estado actual

Proyecto en desarrollo activo, hecho con fines de aprendizaje y práctica de arquitectura en capas, persistencia y JavaFX.

## Cómo correrlo

\`\`\`bash
mvn clean javafx:run
\`\`\`
