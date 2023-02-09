package edu.ucla.lab1.vigilancia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.ucla.lab1.vigilancia.model.Factura;

public class FacturaDao extends Dao<Factura, Integer>{
	
	private static final String QUERY_SELECT_JOIN = "SELECT f.id AS id,"
			+ " f.fec_pago AS fec_pago, f.descr AS descr, f.iva AS iva, f.status AS status,"
			+ " f.subtotal AS subtotal, f.monto_total AS monto_total, "
			+ " s.id AS servicio_id"
			+ " FROM factura f LEFT JOIN servicio s ON f.servicio_id = s.id";
	
	ServicioExtraDao servExDao = new ServicioExtraDao();
	ServicioDao servDao = new ServicioDao();

	@Override
	public Factura toEntity(ResultSet rs) throws SQLException {
		Factura entity = new Factura();
		
		entity.setId(rs.getInt("id"));
        entity.setFechaPago(rs.getDate("fec_pago").toLocalDate());
        entity.setDesc(rs.getString("descr"));
        entity.setIva(rs.getDouble("iva"));
        entity.setStatus(rs.getInt("status"));
        entity.setSubtotal(rs.getDouble("subtotal"));
        entity.setMontoTotal(rs.getDouble("monto_total"));
        
        var s = servDao.getById(rs.getInt("servicio_id"));
        if(s.isPresent()) {
        	entity.setServicio(s.get());
        	entity.setAllServicioExtra(servExDao.getAllById(entity.getServicio().getId()));
        }
        
        return entity;
	}

	@Override
	public ArrayList<Factura> getAll() throws SQLException {
		ArrayList<Factura> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " ORDER BY f.id;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
	}

	@Override
	public Optional<Factura> getById(Integer id) throws SQLException {
		var statement = conn.createStatement();
        
        var query = QUERY_SELECT_JOIN + " WHERE f.id = " + id.toString();
        ResultSet rs = statement.executeQuery(query);
        
        if (rs.next()) {
        	return Optional.ofNullable(toEntity(rs));
        }
        return Optional.empty();
	}

	@Override
	public Factura save(Factura entity) throws SQLException {
		if (entity == null) {
            throw new SQLException("La factura está vacía");
        }
        var query = "INSERT INTO factura(servicio_id, fec_pago, descr, iva, status, subtotal, monto_total)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        var stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, entity.getServicio().getId());
        stmt.setDate(2, Date.valueOf(entity.getFechaPago()));
        stmt.setString(3, entity.getDesc());
        stmt.setDouble(4, entity.getIva());
        stmt.setInt(5, entity.getStatus());
        stmt.setDouble(6, entity.getSubtotal());
        stmt.setDouble(7, entity.getMontoTotal());
        
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
	public void update(Factura entity) throws SQLException {
		if (entity == null) {
            throw new SQLException("La factura está vacía");
        }
        var query = "UPDATE factura SET servicio_id=?, fec_pago=?, descr=?, iva=?, status=?, subtotal=?, monto_total=? WHERE id=?";
        
        var stmt = conn.prepareStatement(query);
        stmt.setInt(1, entity.getServicio().getId());
        stmt.setDate(2, Date.valueOf(entity.getFechaPago()));
        stmt.setString(3, entity.getDesc());
        stmt.setDouble(4, entity.getIva());
        stmt.setInt(5, entity.getStatus());
        stmt.setDouble(6, entity.getSubtotal());
        stmt.setDouble(7, entity.getMontoTotal());
        
        stmt.setInt(8, entity.getId());
        
        stmt.executeUpdate();	

	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		var stmt = conn.prepareStatement("DELETE FROM factura WHERE id=?");
		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Factura entity) throws SQLException {
		return deleteById(entity.getId());
	}
	
	public ArrayList<Factura> searchByKey(String key, String word) throws SQLException {
        ArrayList<Factura> entities = new ArrayList<>();
        
        var statement = conn.createStatement();
        var query = QUERY_SELECT_JOIN + " WHERE f." + key + " ILIKE '%" + word + "%';";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            entities.add(toEntity(rs));
        }
        return entities;
    }
}
