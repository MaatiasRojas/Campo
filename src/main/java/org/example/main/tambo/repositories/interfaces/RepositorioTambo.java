package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.Tambo;

import java.util.ArrayList;

public interface RepositorioTambo extends  RepositorioGenerico<Tambo, Integer>{
    ArrayList<Tambo> listarPorEstablecimiento(int establecimientoId);
    ArrayList<Tambo> listarEnOperacion();
}
