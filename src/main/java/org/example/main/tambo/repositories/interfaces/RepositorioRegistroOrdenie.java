package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.RegistroOrdenie;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface RepositorioRegistroOrdenie extends RepositorioGenerico<RegistroOrdenie, Integer>{
    ArrayList<RegistroOrdenie> listarPorVaca(int vacaId);
    ArrayList<RegistroOrdenie> listarPorTambo(int tamboId);
    ArrayList<RegistroOrdenie> listarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
