package org.example.main.tambo.services.interfaces;

import org.example.main.tambo.entities.Animal;

import java.util.ArrayList;

public interface ServicioAnimal {
    Animal registrarAnimal(Animal animal);
    void darDeBaja(int animalId);
    void registrarPesaje(int animalId, double nuevoPeso);
    ArrayList<Animal> listarPorEstablecimiento(int establecimientoId);
}
