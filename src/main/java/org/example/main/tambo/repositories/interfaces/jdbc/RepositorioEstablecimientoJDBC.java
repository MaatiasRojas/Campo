package org.example.main.tambo.repositories.interfaces.jdbc;

import org.example.main.tambo.config.ConexionDB;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.repositories.interfaces.RepositorioEstablecimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioEstablecimientoJDBC implements RepositorioEstablecimiento {

    @Override
    public Establecimiento guardar(Establecimiento entidad) {
        String sql = entidad.getId() == 0
                ? "INSERT INTO establecimientos (nombre, razon_social, codigo_registro_ganadero, superficie_total_hectareas) VALUES(?,?,?,?) "
                : "UPDATE establecimientos SET nombre=?, razon_social=?, codigo_registro_ganadero=?, superficie_total_hectareas=? WHERE id=?";

        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getRazonSocial());
            ps.setString(3, entidad.getCodRegistroGanadero());
            ps.setDouble(4, entidad.getSuperficieTotalHectareas());

            if (entidad.getId() != 0) ps.setInt(5, entidad.getId());
            ps.executeUpdate();

            if (entidad.getId() == 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    entidad.setId(rs.getInt(1));
                }
            }
            return entidad;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el silo", e);
        }
    }

    @Override
    public Optional<Establecimiento>buscarPorId(Integer id) {
        String sql = "SELECT * FROM establecimientos WHERE id = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()){
                return Optional.of(mapearFila(resultado));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el establecimiento", e);
        }
    }

    @Override
    public List<Establecimiento> listarTodos(){
        List<Establecimiento> establecimientos = new ArrayList<>();
        String sql = "SELECT * FROM establecimientos";
        try (Statement stmt = ConexionDB.getConexion().createStatement()){
            ResultSet resultado = stmt.executeQuery(sql);
            while (resultado.next()){
                establecimientos.add(mapearFila(resultado));
            }
        } catch (SQLException e){
            throw new RuntimeException("Error al listar los establecimientos", e);
        }
        return establecimientos;
    }

    @Override
    public void eliminar(Integer id){
        String sql = "DELETE FROM establecimientos WHERE id = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new  RuntimeException("Error al eliminar el establecimiento", e);
        }
    }

    @Override
    public Optional<Establecimiento> buscarPorCodRegistroGanadero(String codRegistroGanadero){
        String sql = "SELECT * FROM establecimientos WHERE codigo_registro_ganadero = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setString(1, codRegistroGanadero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Optional.of(mapearFila(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el establecimiento", e);
        }
    }

    private Establecimiento mapearFila(ResultSet rs) throws SQLException {
        return new Establecimiento(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("razon_social"),
                rs.getString("codigo_registro_ganadero"),
                rs.getDouble("superficie_total_hectareas"),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
