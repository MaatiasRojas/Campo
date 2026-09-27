package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.Silo;

import java.util.ArrayList;

public interface RepositorioSilo extends  RepositorioGenerico<Silo, Integer>{
    ArrayList<Silo> listarPorEstablecimiento(int establecimientoId);
    ArrayList<Silo> listarActivos();
    ArrayList<Silo> listarConStockBajo(double umbralKg);
}
