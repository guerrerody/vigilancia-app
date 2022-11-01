package edu.ucla.lab1.vigilancia.view;

import java.awt.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(MainView.class);

	static class UI {
		private static final String TITLE = "Sistema de Vigilancia";
		private static final int WIDTH = 700;
		private static final int HEIGHT = 600;
		private static final Color BG_COLOR = new Color(244, 244, 244);
		private static final Color BG_COLOR2 = new Color(44, 44, 44);
		private static final Color FG_COLOR = Color.BLACK;
	}

	private Container container;

	public MainView() {	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			logger.error("Error: ", ex);
		}
		setSize(UI.WIDTH, UI.HEIGHT);
		setTitle(UI.TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setComponents();
		setComponentUI();
		setComponentEvent();
		setComponentLayout();
		setVisible(true);
	}

	private void setComponents() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		var sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		sidePanel.setBackground(UI.BG_COLOR2);
		sidePanel.setSize(200, 200);
		
		var panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(UI.BG_COLOR2);

		var gc = new GridBagConstraints();
		gc.insets = new Insets(5, 5, 5, 5);
		gc.gridx = 0;
		gc.gridy = 0;
		
		var btn1 = new JButton("Menú");
		btn1.setBackground(UI.BG_COLOR2);
		btn1.setForeground(UI.FG_COLOR);
		panel.add(btn1, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		var btn2 = new JButton("Acerca de");
		btn2.setBackground(UI.BG_COLOR2);
		btn2.setForeground(UI.FG_COLOR);
		panel.add(btn2, gc);

		sidePanel.add(panel, BorderLayout.NORTH);
		container.add(sidePanel, BorderLayout.WEST);
	}

	private void setComponentUI() {
		container.setBackground(UI.BG_COLOR);
	}

	private void setComponentEvent() {

	}

	private void setComponentLayout() {

	}

}
