package org.example.main.tambo.services.interfaces;

import org.example.main.tambo.entities.RegistroOrdenie;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ServicioProduccion {
    RegistroOrdenie registrarOrdenie(int vacaId, int TamboId, int tanqueId, int litros, double porcentajeGrasa, double porcentajeProteinas, String turno, String observaciones);
    double calcularPromedioDiarioLeche(int vacaId, int diasAtras);
    ArrayList<RegistroOrdenie> obtenerHistorialPorVaca(int vacaId);
    double calcularProduccionTotalTambo(int tamboId, LocalDate fecha);
}
