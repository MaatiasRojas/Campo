package org.example.main.tambo.repositories.interfaces;

import org.example.main.tambo.entities.Establecimiento;

import java.util.ArrayList;
import java.util.Optional;

public interface RepositorioEstablecimiento extends RepositorioGenerico<Establecimiento, Integer>{
    Optional<Establecimiento> buscarPorCodRegistroGanadero(String codRegistroGanadero);

}
