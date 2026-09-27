package org.example.main.tambo.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Silo {
    private int id;
    private String identificadorSilo;
    private String tipoAlimento;
    private double capacidadMaximaKg;
    private double stockActualKg;
    private double costoAlimentoKg;
    private LocalDate fechaLlenado;
    private Establecimiento establecimiento;
    private boolean estaActivo;

    public Silo(int id, String identificadorSilo, String tipoAlimento, double capacidadMaximaKg, double stockActualKg, double costoAlimentoKg, LocalDate fechaLlenado, Establecimiento establecimiento, boolean estaActivo) {
        setId(id);
        setIdentificadorSilo(identificadorSilo);
        setTipoAlimento(tipoAlimento);
        setCapacidadMaximaKg(capacidadMaximaKg);
        setStockActualKg(stockActualKg);
        setCostoAlimentoKg(costoAlimentoKg);
        setFechaLlenado(fechaLlenado);
        setEstablecimiento(establecimiento);
        setEstaActivo(estaActivo);
    }

    public void descontarStock(double cantidadKg){
        if (cantidadKg > getStockActualKg()){
            throw new IllegalArgumentException("Stock insuficiente en el silo " + identificadorSilo);
        }
        setStockActualKg(getStockActualKg() - cantidadKg);
    }

    public void reponerStock(double cantidadKg){
        if (cantidadKg + getStockActualKg() > getCapacidadMaximaKg()){
            throw new IllegalArgumentException("La cantidad supera a la capacidad del silo " + identificadorSilo);
        }
        setStockActualKg(getStockActualKg() + cantidadKg);
    }
}
