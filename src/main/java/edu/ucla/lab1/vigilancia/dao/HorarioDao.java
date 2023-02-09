package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Horario;

public class HorarioDao extends Dao<Horario, Integer> {

	@Override
	public Horario toEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Horario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Horario> getById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Horario save(Horario entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Horario entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Horario entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}