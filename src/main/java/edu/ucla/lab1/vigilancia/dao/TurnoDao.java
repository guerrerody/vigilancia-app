package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Turno;

public class TurnoDao extends Dao<Turno, Integer> {

	@Override
	public Turno toEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turno> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Turno> getById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Turno save(Turno entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Turno entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Turno entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}