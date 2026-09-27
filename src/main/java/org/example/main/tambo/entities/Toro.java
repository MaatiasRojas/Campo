package org.example.main.tambo.entities;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Toro extends Animal {
    private boolean esReproductorActivo;

    public Toro(int id, String especie, LocalDate fechaNacimiento, double pesoActual, String sexo, String estadoSalud, Boolean activo, Establecimiento establecimiento, boolean esReproductorActivo) {
        super(id, especie, fechaNacimiento, pesoActual, "Macho", estadoSalud, activo, establecimiento);
        setEsReproductorActivo(esReproductorActivo);
    }

}
