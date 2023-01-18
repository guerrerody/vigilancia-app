package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.Turno;

public class ServicioDao extends Dao<Servicio, Integer> {
	
	private static final String QUERY_SELECT_JOIN = "SELECT s.id AS id, s.client_id AS client_id,"
			+ " s.fec_in AS fec_in, s.fec_fin AS fec_fin, s.descr AS descr, s.costo AS costo, s.status AS status,"
			+ " c.nombre AS nombre_cliente"
			+ " FROM servicio s LEFT JOIN cliente c ON s.client_id = c.id";
	
	TurnoDao turDao= new TurnoDao();
	
	public ArrayList<Servicio> getAll() throws SQLException {
        ArrayList<Servicio> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " ORDER BY s.id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<Servicio> getById(Integer id) throws SQLException {
        var statement = conn.createStatement();
        
        var query = QUERY_SELECT_JOIN + " WHERE s.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public Servicio save(Servicio entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Servicio está vacío");
        }
        var query = "INSERT INTO servicio(client_id, fec_in, fec_fin, descr, costo, status)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entity.getCliente().getId());
        stmt.setDate(2, Date.valueOf(entity.getFechaIn()));
        stmt.setDate(3, Date.valueOf(entity.getFechaFin()));
        stmt.setString(4, entity.getDescr());
        stmt.setDouble(5, entity.getCosto());
        stmt.setInt(6, entity.getStatus());
        
        if (stmt.executeUpdate() == 1) {
        	var rs = stmt.getGeneratedKeys();
        	
        	if (rs.next()) {
        		entity.setId(rs.getInt("id"));
        		
        		/*LocalDate fec_in = entity.getFechaIn();
        		LocalTime hor_in;
        		LocalTime hor_fin;
        		if(entity.getCliente().getTipoCliente().getNombre() != "Tienda") {
        			hor_in = new LocalTime().now();
        		}
            	for(int i = 0; i < entity.cantidadTurnos(); i++) {
                	Turno t = new Turno();
                	
                	t.setServicio(entity);
                	t.setFec_in(fec_in);
                	t.setHor_in(null);
                	t.setFec_fin(fec_in);
                	t.setHor_fin(null);
                	t.setFalta(false);
                	t.setJust(null);
                	turDao.save(t);
                	
                	fec_in.plusDays(1);
            	}*/
        		
        		return entity;
        	}
        }
        return null;
	}

	@Override
	public void update(Servicio entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Servicio está vacío");
        }
        var query = "UPDATE servicio SET client_id=?, fec_in=?, fec_fin=?, descr=?, costo=?, status=?	WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setInt(1, entity.getCliente().getId());
        stmt.setDate(2, Date.valueOf(entity.getFechaIn()));
        stmt.setDate(3, Date.valueOf(entity.getFechaFin()));
        stmt.setString(4, entity.getDescr());
        stmt.setDouble(5, entity.getCosto());
        stmt.setInt(6, entity.getStatus());
        stmt.setInt(7, entity.getId());
        
        stmt.executeUpdate();
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM servicio WHERE id=?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Servicio entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public Servicio toEntity(ResultSet rs) throws SQLException {
		Servicio entity = new Servicio();
		
        entity.setId(rs.getInt("id"));     
        entity.setFechaIn(rs.getDate("fec_in").toLocalDate());
        entity.setFechaFin(rs.getDate("fec_fin").toLocalDate());
        entity.setDescr(rs.getString("descr"));
        entity.setCosto(rs.getDouble("costo"));
        entity.setStatus(rs.getInt("status"));
        
        var c = entity.getCliente();
        c.setId(rs.getInt("client_id"));
        c.setNombre(rs.getString("nombre_cliente"));
        
        return entity;
	}
	
    public ArrayList<Servicio> searchByKey(String key, String word) throws SQLException {
        ArrayList<Servicio> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " WHERE s." + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
}
