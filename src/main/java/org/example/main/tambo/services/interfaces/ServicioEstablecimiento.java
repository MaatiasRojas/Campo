package org.example.main.tambo.services.interfaces;

import org.example.main.tambo.entities.Animal;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.entities.Silo;
import org.example.main.tambo.entities.Tambo;

public interface ServicioEstablecimiento {
    Establecimiento crear(Establecimiento est);
    void agregarTambo(int establecimientoId, Tambo tambo);
    void agregarSilo(int establecimientoId, Silo silo);
    void agregarAnimal(int establecimientoId, Animal animal);
}
