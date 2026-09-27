package org.example.main.tambo.services.interfaces;

public interface ServicioTanque {
    void agregarLeche(int tanqueId, double litros);
    void vaciarTanque(int tanqueId);
    double obtenerCapacidadDisponible(int tanqueId);
}
