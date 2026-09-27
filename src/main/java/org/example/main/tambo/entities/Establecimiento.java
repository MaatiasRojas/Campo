package org.example.main.tambo.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Establecimiento {
    private int id;
    private String nombre;
    private String razonSocial;
    private String codRegistroGanadero;
    private double superficieTotalHectareas;
    private ArrayList<Tambo> tambos;     // Un establecimiento puede tener uno o varios tambos/unidades de ordeñe
    private ArrayList<Silo> silos;       // Almacenamiento de alimento presente en el establecimiento
    private ArrayList<Animal> animales;  // Stock general de animales asociados al establecimiento

    public Establecimiento(int id, String nombre, String razonSocial, String codRegistroGanadero, double superficieTotalHectareas, ArrayList<Tambo> tambos,  ArrayList<Silo> silos, ArrayList<Animal> animales) {
        setId(id);
        setNombre(nombre);
        setRazonSocial(razonSocial);
        setCodRegistroGanadero(codRegistroGanadero);
        setSuperficieTotalHectareas(superficieTotalHectareas);
        setTambos(tambos);
        setSilos(silos);
        setAnimales(animales);
    }

   }
