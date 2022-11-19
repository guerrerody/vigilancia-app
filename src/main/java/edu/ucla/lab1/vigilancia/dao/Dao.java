package edu.ucla.lab1.vigilancia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T, ID> {

    protected Connection conn = Database.getInstance().getConnection();
    
    public abstract T toEntity(ResultSet rs) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    public abstract Optional<T> getById(ID id) throws SQLException;
    
    public abstract T save(T entity) throws SQLException;

    public abstract void update(T entity) throws SQLException;
    
    public abstract boolean deleteById(ID id) throws SQLException;

    public abstract boolean delete(T entity) throws SQLException;
}