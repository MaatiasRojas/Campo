package org.example.main.tambo.repositories.interfaces.jdbc;

import org.example.main.tambo.config.ConexionDB;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.entities.Silo;
import org.example.main.tambo.repositories.interfaces.RepositorioEstablecimiento;
import org.example.main.tambo.repositories.interfaces.RepositorioSilo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioSiloJDBC implements RepositorioSilo {
    private final RepositorioEstablecimiento repositorioEstablecimiento;

    public RepositorioSiloJDBC(RepositorioEstablecimiento repositorioEstablecimiento) {
        this.repositorioEstablecimiento = repositorioEstablecimiento;
    }

    @Override
    public Silo guardar(Silo entidad){
        String sql = entidad.getId() == 0
                ? "INSERT INTO silo (identificador_silo, tipo_alimento, capacidad_maxima_kg, stock_actual_kg, costo_alimento_kg, fecha_llenado, esta_activo, establecimiento_id) VALUES (?,?,?,?,?,?,?,?))"
                : "UPDATE silo SET identificador_silo=?, tipo_alimento=?, capacidad_maxima_kg=?, stock_actual_kg=?, costo_alimento_kg=?, fecha_llenado=?, esta_activo=?, establecimiento_id=? WHERE id=?";

        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entidad.getIdentificadorSilo());
            ps.setString(2, entidad.getTipoAlimento());
            ps.setDouble(3, entidad.getCapacidadMaximaKg());
            ps.setDouble(4, entidad.getStockActualKg());
            ps.setDouble(5, entidad.getCostoAlimentoKg());
            ps.setString(6, entidad.getFechaLlenado().toString());
            ps.setBoolean(7, entidad.isEstaActivo()? true: false);
            ps.setInt(8, entidad.getId());

            if (entidad.getId() != 0) ps.setInt(9, entidad.getId());
            ps.executeUpdate();

            if (entidad.getId() == 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) entidad.setId(rs.getInt(1));
            }
            return entidad;
        } catch (SQLException e){
            throw new RuntimeException("Error al guardar el silo", e);
        }
    }

    @Override
    public Optional<Silo> buscarPorId(Integer id){
        String sql = "SELECT * FROM silo WHERE id=?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return Optional.of(mapearFila(rs));
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Silo> listarTodos(){
        ArrayList<Silo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM silo";
        try (Statement stmt = ConexionDB.getConexion().createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar silos", e);
        }
        return resultado;
    }

    @Override
    public void eliminar(Integer id){
        String sql = "DELETE FROM silo WHERE id=?";
        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar un silo", e);
        }
    }

    @Override
    public ArrayList<Silo> listarPorEstablecimiento(int establecimientoId){
        ArrayList<Silo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM silo WHERE establecimiento_id=?";
        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setInt(1, establecimientoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                resultado.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar silos por establecimiento", e);
        }
        return resultado;
    }

    @Override
    public ArrayList<Silo> listarActivos(){
        ArrayList<Silo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM silo WHERE esta_activo=1";
        try (Statement stmt = ConexionDB.getConexion().createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                resultado.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public ArrayList<Silo> listarConStockBajo(double umbralKg){
        ArrayList<Silo> resultado = new ArrayList<>();
        String sql = "SELECT * FROM silo WHERE stock_actual_kg = ?";
        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setDouble(1, umbralKg);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                resultado.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    private Silo mapearFila(ResultSet rs) throws SQLException{
        Establecimiento establecimiento = repositorioEstablecimiento
                .buscarPorId(rs.getInt("establecimiento_id"))
                .orElse(null);

        return new Silo(
                rs.getInt("id"),
                rs.getString("identificador_silo"),
                rs.getString("tipo_alimento"),
                rs.getDouble("capacidad_maxima_kg"),
                rs.getDouble("stock_actual_kg"),
                rs.getDouble("costo_alimento_kg"),
                LocalDate.parse(rs.getString("fecha_llenado")),
                establecimiento,
                rs.getBoolean("esta_activo")
        );
    }


}
