package edu.ucla.lab1.vigilancia.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import edu.ucla.lab1.vigilancia.model.Nomina;
import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.Vigilante;

public class NominaDao extends Dao<Nomina, Integer>{
	
	private static final String QUERY_SELECT_JOIN = "SELECT n.id AS id, n.vigilante_id AS vigilante_id,"
			+ " n.fecha AS fecha, n.descr AS descr, n.dias_trab AS dias_trab, n.horas_extra AS horas_extra,"
			+ " n.dias_falta AS dias_falta, n.sueldo_base AS sueldo_base, n.pago_extra AS pago_extra, n.deduccion AS deduccion,"
			+ " v.nombre AS nombre_vigilante, v.cedula AS cedula_vigilante"
			+ " FROM nomina n LEFT JOIN vigilante v ON n.vigilante_id = v.id";

	//VigilanteDao vigDao = new VigilanteDao();
	//TurnoDao turDao= new TurnoDao(); 
	
	@Override
	public Nomina toEntity(ResultSet rs) throws SQLException {
		Nomina entity = new Nomina();
		
		entity.setId(rs.getInt("id"));
        entity.setFecha(rs.getDate("fecha").toLocalDate());
        entity.setDesc(rs.getString("descr"));
        entity.setDiasTrab(rs.getInt("dias_trab"));
        entity.setHorasExtra(rs.getInt("horas_extra"));
        entity.setDiasFalta(rs.getInt("dias_falta"));
        entity.setSueldoBase(rs.getDouble("sueldo_base"));
        entity.setPagoExtra(rs.getDouble("pago_extra"));
        entity.setDeduccion(rs.getDouble("deduccion"));
        
        var v = entity.getVigilante();
        v.setId(rs.getInt("vigilante_id"));
        v.setNombre(rs.getString("nombre_vigilante"));
        v.setCedula(rs.getString("cedula_vigilante"));
        
        return entity;
		
	}

	@Override
	public ArrayList<Nomina> getAll() throws SQLException {
		ArrayList<Nomina> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " ORDER BY n.id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<Nomina> getById(Integer id) throws SQLException {
		var statement = conn.createStatement();
        
        var query = QUERY_SELECT_JOIN + " WHERE n.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public Nomina save(Nomina entity) throws SQLException {
		
		if (entity == null) {
            throw new SQLException("La nomina está vacía");
        }
        var query = "INSERT INTO nomina(vigilante_id, fecha, descr, dias_trab, horas_extra, dias_falta, sueldo_base, pago_extra, deduccion)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entity.getVigilante().getId());
        stmt.setDate(2, Date.valueOf(entity.getFecha()));
        stmt.setString(3, entity.getDesc());
        stmt.setInt(4, entity.getDiasTrab());
        stmt.setInt(5, entity.getHorasExtra());
        stmt.setInt(6, entity.getDiasFalta());
        stmt.setDouble(7, entity.getSueldoBase());
        stmt.setDouble(8, entity.getPagoExtra());
        stmt.setDouble(9, entity.getDeduccion());
        
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
	public void update(Nomina entity) throws SQLException {
		if (entity == null) {
            throw new SQLException("La nomina está vacía");
        }
        var query = "UPDATE nomina SET descr=?, dias_trab=?, horas_extra=?, dias_falta=?, sueldo_base=?, pago_extra=?, deduccion=? WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setString(1, entity.getDesc());
        stmt.setInt(2, entity.getDiasTrab());
        stmt.setInt(3, entity.getHorasExtra());
        stmt.setInt(4, entity.getDiasFalta());
        stmt.setDouble(5, entity.getSueldoBase());
        stmt.setDouble(6, entity.getPagoExtra());
        stmt.setDouble(7, entity.getDeduccion());
        
        stmt.setInt(8, entity.getId());
        
        stmt.executeUpdate();	
		
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		 var stmt = conn.prepareStatement("DELETE FROM nomina WHERE id=?");
	     stmt.setInt(1, id);
	     return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Nomina entity) throws SQLException {
		return deleteById(entity.getId());
	}
	
	public ArrayList<Nomina> searchByKey(String key, String word) throws SQLException {
		
		ArrayList<Nomina> entities = new ArrayList<>();
		
		if(key == "mensual") {
			entities = getAll();
		} else if(key == "semanal") {
			entities = obtenerNominaSemanal();
		} else {     
	        var statement = conn.createStatement();
	        var query = QUERY_SELECT_JOIN + " WHERE n." + key + " ILIKE '%" + word + "%';";
	        ResultSet rs = statement.executeQuery(query);
	        while (rs.next()) {
	            entities.add(toEntity(rs));
	        }
        }
		
        return entities;
    }
	
	public void generarNominaMensual() {
		
		MediatorDao medDao = new MediatorDao(new TurnoDao(), new VigilanteDao(), null);
		
		LocalDate currentDate = LocalDate.now().minusMonths(1);
		LocalDate nextDate = currentDate.plusMonths(1);
		try {
			ArrayList <Vigilante> vig = (ArrayList<Vigilante>) medDao.getNominaData("vigilantes", null);
			
			//ArrayList <Vigilante> vig = vigDao.getAll();
			
			for(int i = 0; i < vig.size(); i++) {
				int diasFalta = 0;
				Nomina n = new Nomina();
				
				n.setVigilante(vig.get(i));
				n.setFecha(currentDate);
				
				String[] params = {n.getVigilante().getId().toString(), currentDate.toString(), nextDate.toString()};
				ArrayList<Turno> t = (ArrayList<Turno>) medDao.getNominaData("turnosVig", params);
				
				//ArrayList<Turno> t = turDao.getDiasTrabVigilante(n.getVigilante().getId(), currentDate.toString(), nextDate.toString());
				for(int j = 0; j < t.size(); j++) {
					if(t.get(j).getFalta() == true) {
						if(t.get(j).getJust().isEmpty() || t.get(j).getJust().isBlank()) {
							diasFalta += 1;
						}
					}
				}
				n.setDiasTrab(t.size());
				n.setHorasExtra(5*t.size());
				n.setDiasFalta(diasFalta);
				n.setSueldoBase(n.getVigilante().getSueldoBase());
				n.calcDeduccion();
				this.save(n);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}
		
	public ArrayList<Nomina> obtenerNominaSemanal() {
		
		MediatorDao medDao = new MediatorDao(new TurnoDao(), new VigilanteDao(), null);
		
		LocalDate currentDate = LocalDate.now().minusWeeks(1);
		LocalDate nextDate = currentDate.plusWeeks(1);
		
		ArrayList <Nomina> nominaSem = new ArrayList<Nomina>();
		try {
			ArrayList <Vigilante> vig = (ArrayList<Vigilante>) medDao.getNominaData("vigilantes", null);
			//ArrayList <Vigilante> vig = vigDao.getAll();
				
			for(int i = 0; i < vig.size(); i++) {
				int diasFalta = 0;
				Nomina n = new Nomina();
				
				n.setVigilante(vig.get(i));
				n.setFecha(currentDate);
				
				String[] params = {n.getVigilante().getId().toString(), currentDate.toString(), nextDate.toString()};
				ArrayList<Turno> t = (ArrayList<Turno>) medDao.getNominaData("turnosVig", params);
				
				//ArrayList<Turno> t = turDao.getDiasTrabVigilante(n.getVigilante().getId().toString(), currentDate.toString(), nextDate.toString());
				for(int j = 0; j < t.size(); j++) {
					if(t.get(j).getFalta() == true) {
						if(t.get(j).getJust().isEmpty() || t.get(j).getJust().isBlank()) {
							diasFalta += 1;
						}
					}
				}
				n.setDiasTrab(t.size());
				n.setHorasExtra(5*t.size());
				n.setDiasFalta(diasFalta);
				n.setSueldoBase(vig.get(i).getSueldoBase());
				n.calcDeduccion();
				nominaSem.add(n);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return nominaSem;
	}

}
