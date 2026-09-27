package org.example.main.tambo.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface RepositorioGenerico<T, ID> {
    T guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void eliminar(ID id);
}
