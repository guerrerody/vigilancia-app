package edu.ucla.lab1.vigilancia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ucla.lab1.vigilancia.utils.LoadConfig;

public class Database {
	private static Logger logger = LoggerFactory.getLogger(Database.class);
	
	private static final LoadConfig cfg = LoadConfig.getIntanse();
    private static Database instance = null;
    private Connection conn = null;

    private Database() {
        try {
            var host = cfg.getProperty("database.host");
            var port = cfg.getProperty("database.port");
            var user = cfg.getProperty("database.username");
            var psw = cfg.getProperty("database.password");
            var name = cfg.getProperty("database.name");
            
            Class.forName(cfg.getProperty("database.driver"));
            
            var url = String.format("jdbc:%s://%s:%s/%s", 
        		cfg.getProperty("database.jdbc"), host, port, name);
            
            this.conn = DriverManager.getConnection(url, user, psw);
            
            logger.info("¡Conexión de base de datos exitosa!");
        } catch (ClassNotFoundException e) {
        	logger.error("¡El driver JDBC postgresql no está instalado!");
            System.exit(0);
        } catch (SQLException e) {
        	logger.error("La conexión a la base de datos falló:", e);
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
    
    public void closeConnection() {
        try {
            if (this.conn != null) {
            	conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cerrar la conexión con la base de datos", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
