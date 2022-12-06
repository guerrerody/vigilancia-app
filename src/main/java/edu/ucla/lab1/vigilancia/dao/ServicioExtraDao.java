package edu.ucla.lab1.vigilancia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.ServicioExtra;

public class ServicioExtraDao extends Dao<ServicioExtra, Integer> {
	
	private static final String QUERY_SELECT_JOIN = "SELECT se.servicio_id AS servicio_id, se.tipo_alquiler_id AS tipo_alquiler_id,"
			+ "se.cant AS cant, ta.nombre AS nombre_alquiler"
			+ " FROM servicio_extra se"
			+ " LEFT JOIN tipo_Alquiler ta ON se.tipo_alquiler_id = ta.id"
			+ " LEFT JOIN servicio s ON se.servicio_id = s.id";
	
	@Override
	public ArrayList<ServicioExtra> getAll() throws SQLException {
		ArrayList<ServicioExtra> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = QUERY_SELECT_JOIN + " ORDER BY se.servicio_id;";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}

	@Override
	public Optional<ServicioExtra> getById(Integer id) throws SQLException {
		var statement = conn.createStatement();

		var query = QUERY_SELECT_JOIN + " WHERE se.servicio_id = " + id.toString();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			return Optional.ofNullable(toEntity(rs));
		}
		return Optional.empty();
	}
	
	public Optional<ServicioExtra> getByIds(Integer serv_id, Integer al_id) throws SQLException {
		var statement = conn.createStatement();

		var query = QUERY_SELECT_JOIN + " WHERE se.servicio_id = " + serv_id.toString() + " AND se.tipo_alquiler_id = " + al_id.toString();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			return Optional.ofNullable(toEntity(rs));
		}
		return Optional.empty();
	}

	@Override
	public ServicioExtra save(ServicioExtra entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El servicio extra está vacío");
		}
		var query = "INSERT INTO servicio_extra(servicio_id, tipo_alquiler_id, cant)"
				+ " VALUES (?, ?, ?)";

		var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, entity.getServicio().getId());
		stmt.setInt(2, entity.getTipoAlquiler().getId());
		stmt.setInt(3, entity.getCant());

		if (stmt.executeUpdate() == 1) {
			var rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				entity.setId(rs.getInt("servicio_id"));
				return entity;
			}
		}
		return null;
	}

	@Override
	public void update(ServicioExtra entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El servicio extra está vacío");
		}
		var query = "UPDATE servicio_extra SET tipo_alquiler_id=?, cant=? WHERE servicio_id=?";

		var stmt = conn.prepareStatement(query);
		stmt.setInt(1, entity.getTipoAlquiler().getId());
		stmt.setInt(2, entity.getCant());
		stmt.setInt(3, entity.getServicio().getId());

		stmt.executeUpdate();
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		var stmt = conn.prepareStatement("DELETE FROM servicio_extra WHERE servicio_id=?");
		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(ServicioExtra entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public ServicioExtra toEntity(ResultSet rs) throws SQLException {
		ServicioExtra entity = new ServicioExtra();
		
		entity.setCant(rs.getInt("cant"));
		
		var s = entity.getServicio();
		s.setId(rs.getInt("servicio_id"));
		
		var ta = entity.getTipoAlquiler();
		ta.setId(rs.getInt("tipo_alquiler_id"));
		ta.setNombre(rs.getString("nombre_alquiler"));

		return entity;
	}

	public ArrayList<ServicioExtra> searchByKey(String key, String word) throws SQLException {
		ArrayList<ServicioExtra> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = QUERY_SELECT_JOIN + " WHERE se." + key + " ILIKE '%" + word + "%';";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}

}
