package org.example.main.tambo.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroConsumo {
    private int id;
    private LocalDateTime fechaHora;        //Momento de la entrega del alimento
    private double cantidadConsumidaKg;
    private double costoCalculado;          // Cantidad Kg * costoPorKg del Silo (calculado automáticamente)
    private Silo siloOrigen;

    public RegistroConsumo(int id, LocalDateTime fechaHora, double cantidadConsumidaKg, double costoCalculado, Silo siloOrigen) {
        setId(id);
        setFechaHora(fechaHora);
        setCantidadConsumidaKg(cantidadConsumidaKg);
        setCostoCalculado(costoCalculado);
        setSiloOrigen(siloOrigen);
        setSiloOrigen(siloOrigen);
    }
}
