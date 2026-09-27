package org.example.main.tambo.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class Vaca extends Animal{
    private String estadoReproductivo;
    private String estadoLactancia;
    private Tambo tamboActual;
    private ArrayList<RegistroOrdenie> registroOrdenie;
    public Vaca(int id, String especie, LocalDate fechaNacimiento, double pesoActual, String sexo, String estadoSalud, Boolean activo, Establecimiento establecimiento, String estadoReproductivo, String estadoLactancia, Tambo tamboActual, ArrayList<RegistroOrdenie> registroOrdenie) {
        super(id, especie, fechaNacimiento, pesoActual, "Hembra", estadoSalud, activo, establecimiento);
        setEstadoReproductivo(estadoReproductivo);
        setEstadoLactancia(estadoLactancia);
        setTamboActual(tamboActual);
        setRegistroOrdenie(registroOrdenie);
    }

    public double calcularPromedioDiarioLeche(int diasAtras){
        if (registroOrdenie.isEmpty()){return 0.0;}

        LocalDate fechaLimite = LocalDate.now().minusDays(diasAtras);

        return registroOrdenie.stream()
                .filter(reg -> reg.getFechaHora().toLocalDate()
                .isAfter(fechaLimite))
                .mapToDouble(RegistroOrdenie::getLitrosObtenidos)
                .average()
                .orElse(0.0);
    }
}
