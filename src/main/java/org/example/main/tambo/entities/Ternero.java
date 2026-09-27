package org.example.main.tambo.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Ternero extends Animal{
    private double pesoAlNacer;
    private double peso;
    private Vaca madre;
    private Toro padre;

    public Ternero(int id, String especie, LocalDate fechaNacimiento, double pesoActual, String sexo, String estadoSalud, Boolean activo, Establecimiento establecimiento, double pesoAlNacer, double peso, Vaca madre, Toro padre){
        super(id, especie, fechaNacimiento, pesoActual, sexo, estadoSalud, activo, establecimiento);
        setPesoAlNacer(pesoAlNacer);
        setPeso(peso);
        setMadre(madre);
        setPadre(padre);
    }

    public double actualizarPeso(double p){
        return getPeso() + p;
    }


}
