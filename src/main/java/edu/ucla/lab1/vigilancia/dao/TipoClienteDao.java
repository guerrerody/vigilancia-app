package edu.ucla.lab1.vigilancia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.TipoCliente;

public class TipoClienteDao extends Dao<TipoCliente, Integer> {
	
	@Override
	public ArrayList<TipoCliente> getAll() throws SQLException {
        ArrayList<TipoCliente> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM tipo_cliente ORDER BY id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<TipoCliente> getById(Integer id) throws SQLException {
        var statement = conn.createStatement();
        
        var query = "SELECT * FROM tipo_cliente e WHERE e.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public TipoCliente save(TipoCliente entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Tipo Cliente está vacío");
        }
        var query = "INSERT INTO tipo_cliente(nombre) VALUES (?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, entity.getNombre());
        
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
	public void update(TipoCliente entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Tipo Cliente está vacío");
        }
        var query = "UPDATE tipo_cliente SET  nombre=?	WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setString(1, entity.getNombre());
        stmt.setInt(2, entity.getId());
        
        stmt.executeUpdate();
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM tipo_cliente WHERE id=?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(TipoCliente entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public TipoCliente toEntity(ResultSet rs) throws SQLException {
		TipoCliente entity = new TipoCliente();
		
        entity.setId(rs.getInt("id"));
        entity.setNombre(rs.getString("nombre"));
     
        return entity;
	}
	
    public ArrayList<TipoCliente> searchByKey(String key, String word) throws SQLException {
        ArrayList<TipoCliente> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM tipo_cliente WHERE " + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
}
