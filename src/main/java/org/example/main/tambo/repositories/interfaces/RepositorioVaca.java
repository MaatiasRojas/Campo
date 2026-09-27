package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.Vaca;

import java.util.ArrayList;

public interface RepositorioVaca extends RepositorioGenerico<Vaca, Integer>{
    ArrayList<Vaca> listarPorTambo(int tamboId);
    ArrayList<Vaca> listarPorEstadoLactancia(String estadoLactancia);
}
