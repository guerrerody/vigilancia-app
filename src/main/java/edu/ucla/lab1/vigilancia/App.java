package edu.ucla.lab1.vigilancia;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ucla.lab1.vigilancia.controller.MainController;
import edu.ucla.lab1.vigilancia.view.MainView;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Inicializando la aplicación...");
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
		} catch (Exception ex) {
			logger.error("¡Falló la inicialización de la apariencia!: ", ex);
		}
		new MainController(new MainView());
	}
}
