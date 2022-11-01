package edu.ucla.lab1.vigilancia;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ucla.lab1.vigilancia.view.MainView;

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Inicializando la aplicación...");
		SwingUtilities.invokeLater(MainView::new);
	}

}
