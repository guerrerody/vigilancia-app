package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Vigilante;

public class VigilanteDao extends Dao<Vigilante, Integer> {

	@Override
	public ArrayList<Vigilante> getAll() throws SQLException {
        ArrayList<Vigilante> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM vigilante ORDER BY id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<Vigilante> getById(Integer id) throws SQLException {
        var statement = conn.createStatement();
        
        var query = "SELECT * FROM vigilante e WHERE e.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public Vigilante save(Vigilante entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Vigilante está vacío");
        }
        var query = "INSERT INTO vigilante(cedula, nombre, apellido, fec_nac, correo, telf, sueldo_b, fec_ing, status)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, entity.getCedula());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setDate(4, Date.valueOf(entity.getFecNac()));
        stmt.setString(5, entity.getCorreo());
        stmt.setString(6, entity.getTelf());
        stmt.setDouble(7, entity.getSueldoBase());
        stmt.setDate(8, Date.valueOf(entity.getFecIng()));
        stmt.setInt(9, entity.getStatus());
        
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
	public void update(Vigilante entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("El Vigilante está vacío");
        }
        var query = "UPDATE vigilante SET cedula=?, nombre=?, apellido=?, fec_nac=?, correo=?, telf=?, sueldo_b=?, fec_ing=?, status=?	WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setString(1, entity.getCedula());
        stmt.setString(2, entity.getNombre());
        stmt.setString(3, entity.getApellido());
        stmt.setDate(4, Date.valueOf(entity.getFecNac()));
        stmt.setString(5, entity.getCorreo());
        stmt.setString(6, entity.getTelf());
        stmt.setDouble(7, entity.getSueldoBase());
        stmt.setDate(8, Date.valueOf(entity.getFecIng()));
        stmt.setInt(9, entity.getStatus());
        stmt.setInt(10, entity.getId());
        
        stmt.executeUpdate();
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM vigilante WHERE id=?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Vigilante entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public Vigilante toEntity(ResultSet rs) throws SQLException {
		Vigilante entity = new Vigilante();
		
        entity.setId(rs.getInt("id"));
        entity.setCedula(rs.getString("cedula"));
        entity.setNombre(rs.getString("nombre"));
        entity.setApellido(rs.getString("apellido"));       
        entity.setFecNac(rs.getDate("fec_nac").toLocalDate());
        entity.setCorreo(rs.getString("correo"));
        entity.setTelf(rs.getString("telf"));
        entity.setSueldoBase(rs.getDouble("sueldo_b"));
        entity.setFecIng(rs.getDate("fec_ing").toLocalDate());
        entity.setStatus(rs.getInt("status"));
        
        return entity;
	}
	
    public ArrayList<Vigilante> searchByKey(String key, String word) throws SQLException {
        ArrayList<Vigilante> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = "SELECT * FROM vigilante WHERE " + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
}
