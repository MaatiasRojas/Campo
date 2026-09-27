package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.TanqueLeche;

import java.util.ArrayList;

public interface RepositorioTanqueLeche extends RepositorioGenerico<TanqueLeche, Integer>{
    ArrayList<TanqueLeche> listarPorTambo(int tamboId);
    ArrayList<TanqueLeche> listarEnServicio();
}
