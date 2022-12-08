package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.DetalleServicio;

public class TurnoDao extends Dao<Turno, Integer> {
	DetalleServicioDao detserDao = new DetalleServicioDao();

	private static final String QUERY_SELECT_JOIN = "SELECT t.id AS id, t.fec_in AS fec_in,"
			+ " t.hor_in AS hor_in, t.fec_fin AS fec_fin, t.hor_fin AS hor_fin, t.falta AS falta, "
			+ "t.just AS just, ds.servicio_id AS servicio_id, ds.vigilante_id AS vigilante_id"
			+ " FROM turno t LEFT JOIN detalle_servicio ds ON t.id = ds.turno_id";
	
	public ArrayList<Turno> getAll() throws SQLException {
        ArrayList<Turno> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " ORDER BY t.id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<Turno> getById(Integer id) throws SQLException {
        var statement = conn.createStatement();
        
        var query = QUERY_SELECT_JOIN + " WHERE t.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public Turno save(Turno entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Turno está vacío");
        }
        var query = "INSERT INTO turno( fec_in, hor_in, fec_fin, hor_fin, falta, just )"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setDate(1, Date.valueOf(entity.getFec_in()));
        stmt.setTime(2, Time.valueOf(entity.getHor_in()));
        stmt.setDate(3, Date.valueOf(entity.getFec_fin()));
        stmt.setTime(4, Time.valueOf(entity.getHor_fin()));
        stmt.setBoolean(5, entity.getFalta());
        stmt.setString(6, entity.getJust());
        
        if (stmt.executeUpdate() == 1) {
        	var rs = stmt.getGeneratedKeys();
        	
        	DetalleServicio ds = new DetalleServicio();
        	ds.setVigilante(entity.getVigilante());
        	ds.setServicio(entity.getServicio());
        	ds.setTurno(entity);
        	
        	detserDao.save(ds);
        	
        	if (rs.next()) {
        		entity.setId(rs.getInt("id"));
        		return entity;
        	}
        }
        return null;
	}

	@Override
	public void update(Turno entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Turno está vacío");
        }
        var query = "UPDATE turno SET fec_in=?, hor_in=?, fec_fin=?, hor_fin=?, falta=?, just=?	WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setDate(1, Date.valueOf(entity.getFec_in()));
        stmt.setTime(2, Time.valueOf(entity.getHor_in()));
        stmt.setDate(3, Date.valueOf(entity.getFec_fin()));
        stmt.setTime(4, Time.valueOf(entity.getHor_fin()));
        stmt.setBoolean(5, entity.getFalta());
        stmt.setString(6, entity.getJust());
        stmt.setInt(7, entity.getId());
        
        stmt.executeUpdate();
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM turno WHERE id=?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Turno entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public Turno toEntity(ResultSet rs) throws SQLException {
		Turno entity = new Turno();
		
        entity.setId(rs.getInt("id"));     
        entity.setFec_in(rs.getDate("fec_in").toLocalDate());
        entity.setHor_in(rs.getTime("hor_in").toLocalTime());
        entity.setFec_fin(rs.getDate("fec_fin").toLocalDate());
        entity.setHor_fin(rs.getTime("hor_fin").toLocalTime());
        entity.setFalta(rs.getBoolean("falta"));
        entity.setJust(rs.getString("just"));
        
        var s = entity.getServicio();
        s.setId(rs.getInt("servicio_id"));

        var v = entity.getVigilante();
        v.setId(rs.getInt("vigilante_id"));
        
        return entity;
	}
	
    public ArrayList<Turno> searchByKey(String key, String word) throws SQLException {
        ArrayList<Turno> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " WHERE t." + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
	
}