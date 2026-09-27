package org.example.main.tambo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
    private static final String URL = "jdbc:sqlite:tambo.db";
    private static Connection conexion;

    public static Connection getConexion(){
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
                conexion.createStatement().execute("PRAGMA foreign_keys = ON;");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al conectar con la base de datos", ex);
        }
    return conexion;
    }

    public static void inicializarEsquema(){
        try (Statement stmt = getConexion().createStatement()){
            stmt.execute("""    
                CREATE TABLE IF NOT EXISTS establecimientos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    razon_social TEXT,
                    codigo_registro_ganadero TEXT,
                    superficie_total_hectareas REAL
                                                           )
""");
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS silo (
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     identificador_silo TEXT,
                     tipo_alimento TEXT,
                     capacidad_maxima_kg REAL,
                     stock_actual_kg REAL,
                     costo_alimento_kg REAL,
                     fecha_llenado TEXT,
                     esta_activo INTEGER,
                     establecimiento_id INTEGER,
                     FOREIGN KEY (establecimiento_id) REFERENCES establecimientos(id)
                                                 )
""");
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS animal (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tipo TEXT NOT NULL,
                    especie TEXT,
                    fecha_nacimiento TEXT,
                    peso_actual REAL,
                    sexo TEXT,
                    estado_salud TEXT,
                    activo INTEGER,
                    establecimiento_id INTEGER,
                    estado_reproductivo TEXT,
                    estado_lactancia TEXT,
                    tambo_actual_id INTEGER,
                    es_reproductor_activo INTEGER,
                    peso_al_nacer REAL,
                    peso REAL,
                    madre_id INTEGER,
                    padre_id INTEGER,
                    FOREIGN KEY (establecimiento_id) REFERENCES establecimientos(id)
    )
""");
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el esquema", e);
        }
    }
}
