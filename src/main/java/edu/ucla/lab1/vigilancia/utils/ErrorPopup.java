package edu.ucla.lab1.vigilancia.utils;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorPopup {
	private static Logger logger = LoggerFactory.getLogger(ErrorPopup.class);

	public static void show(Exception e) {
		try {
			logger.error("Error: ", e);

			var val = JOptionPane.showConfirmDialog(null, e.getMessage() + ".\n¿Ver detalles del error?",
					"Ocurrió un error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (val == JOptionPane.YES_OPTION) {
				String errorSummary = "";
				int numElement = 0;
				for (StackTraceElement stackTraceElement : e.getStackTrace()) {
					if (numElement >= 20) {
						errorSummary += "\t...  más";
					} else {
						errorSummary += "\tat " + stackTraceElement;
						errorSummary += "\n";
					}
				}
				JOptionPane.showMessageDialog(null, errorSummary, "Ocurrió un error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {

		}
	}
}
