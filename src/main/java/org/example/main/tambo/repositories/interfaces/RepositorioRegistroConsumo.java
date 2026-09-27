package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.RegistroConsumo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface RepositorioRegistroConsumo extends RepositorioGenerico<RegistroConsumo, Integer>{
    ArrayList<RegistroConsumo> listarPorSilo(int siloId);
    ArrayList<RegistroConsumo> listarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
