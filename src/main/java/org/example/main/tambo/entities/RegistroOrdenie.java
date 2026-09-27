package org.example.main.tambo.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroOrdenie {
    private int id;
    private LocalDateTime fechaHora;
    private String turnoOrdenie;
    private int litrosObtenidos;
    private Tambo tambo;
    private Vaca vaca;
    private TanqueLeche tanqueDestino;
    private double porcentajeGrasa;
    private double porcentajeProteinas;
    private String observacionesVaca;

    public RegistroOrdenie(int id, LocalDateTime fechaHora, String turnoOrdenie, int litrosObtenidos, Tambo tambo, Vaca vaca, TanqueLeche tanqueDestino, double porcentajeGrasa, double porcentajeProteinas, String observacionesVaca) {
        setId(id);
        setFechaHora(fechaHora);
        setTurnoOrdenie(turnoOrdenie);
        setLitrosObtenidos(litrosObtenidos);
        setTambo(tambo);
        setVaca(vaca);
        setTanqueDestino(tanqueDestino);
        setPorcentajeGrasa(porcentajeGrasa);
        setPorcentajeProteinas(porcentajeProteinas);
        setObservacionesVaca(observacionesVaca);
    }

    }
