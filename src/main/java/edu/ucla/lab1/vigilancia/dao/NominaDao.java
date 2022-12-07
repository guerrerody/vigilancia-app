package edu.ucla.lab1.vigilancia.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import edu.ucla.lab1.vigilancia.model.Nomina;
import edu.ucla.lab1.vigilancia.model.Vigilante;

public class NominaDao extends Dao<Nomina, Integer>{
	
	private static final String QUERY_SELECT_JOIN = "SELECT n.id AS id, n.vigilante_id AS vigilante_id,"
			+ " n.fecha AS fecha, n.descr AS descr, n.dias_trab AS dias_trab, n.horas_extra AS horas_extra,"
			+ " n.dias_falta AS dias_falta, n.sueldo_base AS sueldo_base, n.pago_extra AS pago_extra, n.deduccion AS deduccion,"
			+ " v.nombre AS nombre_vigilante"
			+ " FROM nomina n LEFT JOIN vigilante v ON n.vigilante_id = v.id";

	VigilanteDao vigDao = new VigilanteDao();
	
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
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " WHERE n." + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
	
	public void generarNominaMensual() {
		
		LocalDate currentDate = LocalDate.now();
		ArrayList <Vigilante> vig = vigDao.getAll();
		
		for(int i = 0; i < vig.size(); i++) {
			vigDao.getById(i);
			
			Nomina n = new Nomina();
			n.setVigilanteId(vig.get(i).getId());
			n.setFecha(currentDate);
			n.setDiasTrab(); //Obtengo de turno
			n.setHorasExtra(); //Obtengo de turno
			n.setDiasFalta(); //Obtengo de turno
			n.setSueldoBase(vig.get(i).getSueldoBase()); //Falta en vigilante
			n.calcDeduccion();
			this.save(n);
		}
	}
		
		public Nomina obtenerNominaSemanal(String vigilante) {
			LocalDate currentDate = LocalDate.now();
			ArrayList <Vigilante> vig = vigDao.getAll();
			
			for(int i = 0; i < vig.size(); i++) {
				vigDao.getById(i);
				
				Nomina n = new Nomina();
				n.setVigilanteId(vig.get(i).getId());
				n.setFecha(currentDate);
				n.setDiasTrab(); //Obtengo de turno
				n.setHorasExtra(); //Obtengo de turno
				n.setDiasFalta(); //Obtengo de turno
				n.setSueldoBase(vig.get(i).getSueldoBase()); //Falta en vigilante
				n.calcDeduccion();
				this.save(n);
			}

	}

}
