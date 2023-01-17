package edu.ucla.lab1.vigilancia.dao;

import java.util.ArrayList;

import java.sql.SQLException;

import edu.ucla.lab1.vigilancia.model.DetalleServicio;

public class MediatorDao {
	
	private TurnoDao turDaoComp;
	private VigilanteDao vigDaoComp;
	private DetalleServicioDao detServDaoComp;

	MediatorDao(TurnoDao b, VigilanteDao c, DetalleServicioDao s){

		turDaoComp = b;
		vigDaoComp = c;
		detServDaoComp = s;
	}
	
	public ArrayList<?> getNominaData(String dec, String[] params) throws SQLException {
		ArrayList<?> data = null;
		
		if(dec == "turnosVig")
			data = turDaoComp.getDiasTrabVigilante(params[0], params[1], params[2]);
			
		if(dec == "vigilantes")
			data = vigDaoComp.getAll();
		
		return data;
	}	
	
	public void saveData(DetalleServicio ds) throws SQLException {
		detServDaoComp.save(ds);
	}
	
}
