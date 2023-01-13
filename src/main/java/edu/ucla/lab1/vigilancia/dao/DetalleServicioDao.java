package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.DetalleServicio;

public class DetalleServicioDao extends Dao<DetalleServicio, Integer> {
	
	public ArrayList<DetalleServicio> getAll() throws SQLException {
        ArrayList<DetalleServicio> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM detalle_servicio";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<DetalleServicio> getById(Integer id) throws SQLException {
        var statement = conn.createStatement();
        
        var query = "SELECT * FROM detalle_servicio WHERE turno_id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public DetalleServicio save(DetalleServicio entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El DetalleServicio está vacío");
        }
        var query = "INSERT INTO detalle_servicio( vigilante_id, servicio_id, turno_id )"
                + " VALUES (?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entity.getVigilante().getId());
        stmt.setInt(2, entity.getServicio().getId());
        stmt.setInt(3, entity.getTurno().getId());
        
        
        if (stmt.executeUpdate() == 1) {
        	var rs = stmt.getGeneratedKeys();
        	if (rs.next()) {
        	
        	}
        }
        return null;
	}

	@Override
	public void update(DetalleServicio entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El DetalleServicio está vacío");
        }
        var query = "UPDATE detalle_servicio SET vigilante_id=?, servicio_id=?, turno_id=?	WHERE turno_id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setInt(1, entity.getVigilante().getId());
        stmt.setInt(2, entity.getServicio().getId());
        stmt.setInt(3, entity.getTurno().getId());
        stmt.setInt(4, entity.getTurno().getId());
        
        stmt.executeUpdate();
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM detalle_servicio WHERE turno_id=?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(DetalleServicio entity) throws SQLException {
		return deleteById(entity.getTurno().getId());
	}

	@Override
	public DetalleServicio toEntity(ResultSet rs) throws SQLException {
		DetalleServicio entity = new DetalleServicio();
        
        var s = entity.getServicio();
        s.setId(rs.getInt("servicio_id"));

        var v = entity.getVigilante();
        v.setId(rs.getInt("vigilante_id"));
        
        var t = entity.getTurno();
        t.setId(rs.getInt("turno_id"));
        
        return entity;
	}
	
    public ArrayList<DetalleServicio> searchByKey(String key, String word) throws SQLException {
        ArrayList<DetalleServicio> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM detalle_servicio WHERE " + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
	
}