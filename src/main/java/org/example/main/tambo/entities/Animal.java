package org.example.main.tambo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@NoArgsConstructor
@Data
public abstract class Animal {
    private int id;
    private String especie;
    private LocalDate fechaNacimiento;
    private double pesoActual;
    private String sexo;
    private String estadoSalud;
    private Boolean activo;     //Si esta en el establecimiento o fue vendido o murio
    private Establecimiento establecimiento;

    public Animal(int id, String especie, LocalDate fechaNacimiento, double pesoActual, String sexo, String estadoSalud, Boolean activo, Establecimiento establecimiento) {
        setId(id);
        setEspecie(especie);
        setFechaNacimiento(fechaNacimiento);
        setPesoActual(pesoActual);
        setSexo(sexo);
        setEstadoSalud(estadoSalud);
        setActivo(activo);
        setEstablecimiento(establecimiento);
    }

    public int getEdadEnMeses() {
        if (fechaNacimiento == null) return 0;
        return (int) ChronoUnit.MONTHS.between(fechaNacimiento, LocalDate.now());
    }

    public double registrarPesajeNuevo(double pesoActual){
        return getPesoActual() + pesoActual;
    }
}
