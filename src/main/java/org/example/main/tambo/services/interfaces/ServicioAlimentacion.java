package org.example.main.tambo.services.interfaces;

import org.example.main.tambo.entities.RegistroConsumo;
import org.example.main.tambo.entities.Silo;

import java.util.ArrayList;

public interface ServicioAlimentacion {
    RegistroConsumo registrarConsumo(int siloId, double cantidadKg);
    void reponerStock(int siloId, double cantidadKg);
    ArrayList<Silo> obtenerAlertasStockBajo(double umbralPorcentaje);
}
