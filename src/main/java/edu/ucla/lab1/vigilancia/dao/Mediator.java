package edu.ucla.lab1.vigilancia.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucla.lab1.vigilancia.model.DetalleServicio;

public abstract class Mediator {

	public abstract ArrayList<?> getNominaData(String dec, String[] params) throws SQLException;
	
	public abstract void saveData(DetalleServicio ds) throws SQLException;
	
}
