package org.example.main.tambo.entities;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TanqueLeche {
    private int id;
    private String codIdentificador;
    private double capacidadLitros;
    private double nivelActualLitros;
    private double temperaturaActual;
    private LocalDateTime ultimaLimpieza;
    private Tambo tambo;
    private Boolean enServicio;

    public TanqueLeche(int id, double capacidadLitros, double nivelActualLitros, double temperaturaActual, Tambo tambo, Boolean enServicio) {
        setId(id);
        setCapacidadLitros(capacidadLitros);
        setNivelActualLitros(nivelActualLitros);
        setTemperaturaActual(temperaturaActual);
        setTambo(tambo);
        setEnServicio(enServicio);
    }

    public double getCapacidadLibreLitros(){
        return getCapacidadLitros() - getNivelActualLitros();
    }

    public void agregarLeche(double litros){
        if ((getNivelActualLitros() + litros) >  getCapacidadLibreLitros()){
            throw new IllegalArgumentException("El volumen supera a la capacidad disponible");
        }
        setNivelActualLitros(getNivelActualLitros() + litros);
    }

    public void vaciarTanque(){
        setNivelActualLitros(0.0);
    }
}
