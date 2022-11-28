package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Cliente;

public class ClienteDao extends Dao<Cliente, Integer> {
	@Override
	public ArrayList<Cliente> getAll() throws SQLException {
		ArrayList<Cliente> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = "SELECT c.*, tc.nombre AS nombre_tipo_cliente FROM cliente c"
				+ " LEFT JOIN tipo_cliente tc ON c.tipo_cliente_id = tc.id ORDER BY c.id;";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}

	@Override
	public Optional<Cliente> getById(Integer id) throws SQLException {
		var statement = conn.createStatement();

		var query = "SELECT c.*, tc.nombre AS nombre_tipo_cliente FROM cliente c"
				+ " LEFT JOIN tipo_cliente tc ON c.tipo_cliente_id = tc.id WHERE c.id = " + id.toString();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			return Optional.ofNullable(toEntity(rs));
		}
		return Optional.empty();
	}

	@Override
	public Cliente save(Cliente entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El cliente está vacío");
		}
		var query = "INSERT INTO cliente(nombre, localidad, direccion, nombre_contac, telf_contac, status, tipo_cliente_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, entity.getNombre());
		stmt.setString(2, entity.getLocalidad());
		stmt.setString(3, entity.getDireccion());
		stmt.setString(4, entity.getNombreContac());
		stmt.setString(5, entity.getTelfContac());
		stmt.setInt(6, entity.getStatus());
		stmt.setInt(7, entity.getTipoCliente().getId());

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
	public void update(Cliente entity) throws SQLException {
		if (entity == null) {
			throw new SQLException("El cliente está vacío");
		}
		var query = "UPDATE cliente SET nombre=?, localidad=?, direccion=?, nombre_contac=?, telf_contac=?, status=?, tipo_cliente_id=? WHERE id=?";

		var stmt = conn.prepareStatement(query);
		stmt.setString(1, entity.getNombre());
		stmt.setString(2, entity.getLocalidad());
		stmt.setString(3, entity.getDireccion());
		stmt.setString(4, entity.getNombreContac());
		stmt.setString(5, entity.getTelfContac());
		stmt.setInt(6, entity.getStatus());
		stmt.setInt(7, entity.getTipoCliente().getId());
		stmt.setInt(8, entity.getId());

		stmt.executeUpdate();
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		var stmt = conn.prepareStatement("DELETE FROM cliente WHERE id=?");
		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Cliente entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public Cliente toEntity(ResultSet rs) throws SQLException {
		Cliente entity = new Cliente();

		entity.setId(rs.getInt("id"));
		entity.setNombre(rs.getString("nombre"));
		entity.setLocalidad(rs.getString("localidad"));
		entity.setDireccion(rs.getString("dirrecion"));
		entity.setNombreContac(rs.getString("nombre_contac"));
		entity.setTelfContac(rs.getString("telf_contac"));
		entity.setStatus(rs.getInt("status"));
		
		var tc = entity.getTipoCliente();
		tc.setId(rs.getInt("tipo_cliente_id"));
		tc.setNombre(rs.getString("nombre_tipo_cliente"));

		return entity;
	}

	public ArrayList<Cliente> searchByKey(String key, String word) throws SQLException {
		ArrayList<Cliente> entities = new ArrayList<>();

		var statement = conn.createStatement();
		var query = "SELECT * FROM cliente WHERE " + key + " ILIKE '%" + word + "%';";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			entities.add(toEntity(rs));
		}
		return entities;
	}

}
