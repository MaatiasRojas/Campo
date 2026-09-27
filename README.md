# Gestión de Campo

Aplicación de escritorio en Java para la gestión integral de un establecimiento agropecuario: control de animales, silos de alimento, tambos y registros de ordeñe.

## Tecnologías

- **Java 21**
- **JavaFX** — interfaz gráfica de escritorio
- **SQLite** — persistencia de datos embebida
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

## Implementación

Hasta ahora el proyecto solo tiene implementado la parte de Silo y Animales. 
En silo se pueden cargar los silos con sus respectivos datos. Adjunto imagen:
<img width="867" height="682" alt="image" src="https://github.com/user-attachments/assets/0917b179-ff68-4efb-a13c-b8f1b2ef1246" />

En animales se pueden cargar los datos de los distintos animales que se tienen en el campo (Por ahora son vacas, toros y terneros), antes de comenzar a cargar se muestra una pantalla para que elija la opcion de que animal se cargaran los datos. Adjunto imagen:
<img width="747" height="687" alt="image" src="https://github.com/user-attachments/assets/d741a0e9-7b62-4efa-a582-820881a777ea" />

Faltan implementar las demas funciones.

## Cómo correrlo

\`\`\`bash
mvn clean javafx:run
\`\`\`

<img width="732" height="527" alt="image" src="https://github.com/user-attachments/assets/a04903f2-4e9a-43a3-80fa-7b12a096223b" />

