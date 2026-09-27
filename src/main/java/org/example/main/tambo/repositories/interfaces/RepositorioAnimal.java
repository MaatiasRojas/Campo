package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.Animal;

import java.util.ArrayList;

public interface RepositorioAnimal extends RepositorioGenerico<Animal, Long> {
    ArrayList<Animal> listarPorEstablecimiento(Long establecimientoId);
    ArrayList<Animal> listarPorActivos();
    ArrayList<Animal> listarPorEspecie(String especie);
}
