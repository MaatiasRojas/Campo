package org.example.main.tambo.repositories.interfaces.jdbc;

import org.example.main.tambo.config.ConexionDB;
import org.example.main.tambo.entities.*;
import org.example.main.tambo.repositories.interfaces.RepositorioAnimal;
import org.example.main.tambo.repositories.interfaces.RepositorioEstablecimiento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioAnimalJDBC implements RepositorioAnimal {
    private final RepositorioEstablecimiento repositorioEstablecimiento;

    public RepositorioAnimalJDBC(RepositorioEstablecimiento repositorioEstablecimiento) {
        this.repositorioEstablecimiento = repositorioEstablecimiento;
    }

    @Override
    public ArrayList<Animal> listarPorEstablecimiento(Long establecimientoId) {
        ArrayList<Animal> resultado = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE establecimiento_id = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setLong(1, establecimientoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) resultado.add(mapearFila(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public ArrayList<Animal> listarPorActivos() {
        ArrayList<Animal> resultado = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE activo = 1";
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
    public ArrayList<Animal> listarPorEspecie(String especie) {
        ArrayList<Animal> resultado = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE especie = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setString(1, especie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                resultado.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public Animal guardar(Animal entidad) {
        String tipo = obtenerTipo(entidad);
        String sql = entidad.getId() == 0 ?
                "INSERT INTO animal (tipo, especie, fecha_nacimiento, peso_actual, sexo, estado_salud, activo, establecimiento_id, estado_reproductivo, estado_lactancia, tambo_actual_id, es_reproductor_activo, peso_al_nacer, peso, madre_id, padre_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" :
                "UPDATE animal SET tipo=?, especie=?, fecha_nacimiento=?, peso_actual=?, sexo=?, estado_salud=?, activo=?, establecimiento_id=?, estado_reproductivo=?, estado_lactancia=?, tambo_actual_id=?, es_reproductor_activo=?, peso_al_nacer=?, peso=?, madre_id=?, padre_id=? WHERE id=?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tipo);
            ps.setString(2, entidad.getEspecie());
            ps.setString(3, entidad.getFechaNacimiento() != null ? entidad.getFechaNacimiento().toString() : null);
            ps.setDouble(4, entidad.getPesoActual());
            ps.setString(5, entidad.getSexo());
            ps.setString(6, entidad.getEstadoSalud());
            ps.setInt(7, Boolean.TRUE.equals(entidad.getActivo()) ? 1 : 0);
            ps.setInt(8, entidad.getEstablecimiento().getId());

            if (entidad instanceof Vaca vaca) {
                ps.setString(9, vaca.getEstadoReproductivo());
                ps.setString(10, vaca.getEstadoLactancia());
                if (vaca.getTamboActual() != null) ps.setInt(11, vaca.getTamboActual().getId());
                else ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.INTEGER);
                ps.setNull(13, Types.REAL);
                ps.setNull(14, Types.REAL);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.INTEGER);
            } else if (entidad instanceof Toro toro) {
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.VARCHAR);
                ps.setNull(11, Types.INTEGER);
                ps.setInt(12, toro.isEsReproductorActivo() ? 1 : 0);
                ps.setNull(13, Types.REAL);
                ps.setNull(14, Types.REAL);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.INTEGER);
            } else if (entidad instanceof Ternero ternero) {
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.VARCHAR);
                ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.INTEGER);
                ps.setDouble(13, ternero.getPesoAlNacer());
                ps.setDouble(14, ternero.getPeso());
                if (ternero.getMadre() != null) ps.setInt(15, ternero.getMadre().getId());
                else ps.setNull(15, Types.INTEGER);
                if (ternero.getPadre() != null) ps.setInt(16, ternero.getPadre().getId());
                else ps.setNull(16, Types.INTEGER);
            }

            if (entidad.getId() != 0) ps.setInt(17, entidad.getId());

            ps.executeUpdate();

            if (entidad.getId() == 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) entidad.setId(rs.getInt(1));
            }
            return entidad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Animal> buscarPorId(Long id) {
        String sql = "SELECT * FROM animal WHERE id = ?";
        try(PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Optional.of(mapearFila(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Animal> listarTodos() {
        List<Animal> resultado = new ArrayList<>();
        String sql = "SELECT * FROM animal";
        try (Statement stmt = ConexionDB.getConexion().createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String obtenerTipo(Animal a){
        if (a instanceof Vaca) {return "VACA";}
        else if (a instanceof Toro) {return "TORO";}
        else if (a instanceof Ternero) {return "TERNERO";}
        throw new IllegalArgumentException("Tipo de animal desconocido...");
    }

    private Animal mapearFila(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        Establecimiento establecimiento = repositorioEstablecimiento
                .buscarPorId(rs.getInt("establecimiento_id"))
                .orElse(null);

        int id = rs.getInt("id");
        String especie = rs.getString("especie");
        String fechaNacStr = rs.getString("fecha_nacimiento");
        LocalDate fechaNac = fechaNacStr != null ? LocalDate.parse(fechaNacStr) : null;
        double peso = rs.getDouble("peso_actual");
        String sexo = rs.getString("sexo");
        String estadoSalud = rs.getString("estado_salud");
        Boolean activo = rs.getBoolean("activo");

        return switch (tipo) {
            case "VACA" -> new Vaca(
                    id, especie, fechaNac, peso, sexo, estadoSalud, activo, establecimiento,
                    rs.getString("estado_reproductivo"),
                    rs.getString("estado_lactancia"),
                    null,
                    new ArrayList<>()
            );
            case "TORO" -> new Toro(
                    id, especie, fechaNac, peso, sexo, estadoSalud, activo, establecimiento,
                    rs.getInt("es_reproductor_activo") == 1
            );
            case "TERNERO" -> new Ternero(
                    id, especie, fechaNac, peso, sexo, estadoSalud, activo, establecimiento,
                    rs.getDouble("peso_al_nacer"),
                    rs.getDouble("peso"),
                    null,
                    null
            );
          default -> throw new IllegalAccessError("Tipo de animal desconocido...");
        };
    }
}
