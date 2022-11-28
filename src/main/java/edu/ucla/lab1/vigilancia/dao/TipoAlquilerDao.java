package edu.ucla.lab1.vigilancia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.TipoAlquiler;

public class TipoAlquilerDao extends Dao<TipoAlquiler, Integer> {
	@Override
	public ArrayList<TipoAlquiler> getAll() throws SQLException {
		ArrayList<TipoAlquiler> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = "SELECT * FROM tipo_alquiler ORDER BY id;";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}

	@Override
	public Optional<TipoAlquiler> getById(Integer id) throws SQLException {
		var statement = conn.createStatement();

		var query = "SELECT * FROM tipo_alquiler e WHERE e.id = " + id.toString();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			return Optional.ofNullable(toEntity(rs));
		}
		return Optional.empty();
	}

	@Override
	public TipoAlquiler save(TipoAlquiler entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El Tipo Alquiler está vacío");
		}
		var query = "INSERT INTO tipo_alquiler(nombre, costo_uso, costo_mant)" + "VALUES (?,?,?)";

		var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, entity.getNombre());
		stmt.setDouble(2, entity.getCostoUso());
		stmt.setDouble(3, entity.getCostoMant());

		if (stmt.executeUpdate() == 1) {
			var rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				entity.setId(rs.getInt("id"));
				return entity;
			}
		}
		return null;
	}

	@Override
	public void update(TipoAlquiler entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El Tipo Alquiler está vacío");
		}
		var query = "UPDATE tipo_alquiler SET nombre=?, costo_uso=?, costo_mant=? WHERE id=?";

		var stmt = conn.prepareStatement(query);
		stmt.setString(1, entity.getNombre());
		stmt.setDouble(2, entity.getCostoUso());
		stmt.setDouble(3, entity.getCostoMant());
		stmt.setInt(4, entity.getId());

		stmt.executeUpdate();
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		var stmt = conn.prepareStatement("DELETE FROM  tipo_alquiler WHERE id=?");
		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(TipoAlquiler entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public TipoAlquiler toEntity(ResultSet rs) throws SQLException {
		TipoAlquiler entity = new TipoAlquiler();

		entity.setId(rs.getInt("id"));
		entity.setNombre(rs.getString("nombre"));
		entity.setCostoUso(rs.getDouble("costo_uso"));
		entity.setCostoMant(rs.getDouble("costo_mant"));

		return entity;
	}

	public ArrayList<TipoAlquiler> searchByKey(String key, String word) throws SQLException {
		ArrayList<TipoAlquiler> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = "SELECT * FROM tipo_alquiler WHERE " + key + " ILIKE '%" + word + "%';";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}
}
