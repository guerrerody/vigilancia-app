package edu.ucla.lab1.vigilancia.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.ErrorPopup;


public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	static class UI {
		public static final String TITLE = "Sistema de Vigilancia";
		public static final int WIDTH = 1208;
		public static final int HEIGHT = 680;
		public static final int WIDTH_MENU = 250;
		public static final int WIDTH_CONTENT = WIDTH - WIDTH_MENU;
	}
	
	JPanel[] cards;
	ArrayList<MenuItem> menuItems = new ArrayList<>();
	
	private JButton btnClose;
	private JLabel lbName;
	private JPanel panelHeader;
	private JPanel panelLayout;
	private JPanel panelLeft;
	private JPanel panelSideBar;

	public MainView() {
		initComponents();
		setSize(UI.WIDTH, UI.HEIGHT);
		setTitle(UI.TITLE);
		setLocationRelativeTo(null);
		btnClose.putClientProperty("JButton.buttonType", "roundRect");
	}
	
	public void showError(String message) {
		ErrorPopup.show(new Exception(message));
	}

	public void showError(Exception e) {
		ErrorPopup.show(e);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public JButton getBtnClose() {
		return btnClose;
	}

	public void addMenu(MenuItem... menu) {
		for (int i = 0; i < menu.length; i++) {
			MenuItem item = menu[i];
			menuItems.add(item);
			panelSideBar.add(item);
			ArrayList<MenuItem> subMenus = item.getSubMenu();
			for (MenuItem subMenu : subMenus) {
				addMenu(subMenu);
				subMenu.setVisible(false);
			}
		}
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public void setCards(JPanel[] cards) {
		this.cards = cards;
		initLayout();
	}

	public void initLayout() {
		panelLayout.removeAll();
		for (int i = 0; i < cards.length; i++) {
			panelLayout.add(cards[i]);
		}
		panelLayout.updateUI();
	}
	
	public JPanel getPanelLayout() {
		return panelLayout;
	}

	public JPanel getPanelSideBar() {
		return panelSideBar;
	}

	public void setPanel(JPanel panel) {
		for (JPanel card : cards) {
			card.setVisible(false);
		}
		panel.setVisible(true);
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		panelLeft = new JPanel();
		panelHeader = new JPanel();
		lbName = new JLabel();
		btnClose = new JButton();
		panelSideBar = new JPanel();
		panelLayout = new JPanel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(UI.WIDTH, UI.HEIGHT));
		setResizable(false);

		panelLeft.setMinimumSize(new Dimension(UI.WIDTH_MENU, UI.HEIGHT));
		panelLeft.setPreferredSize(new Dimension(UI.WIDTH_MENU, UI.HEIGHT));
		panelLeft.setLayout(new BorderLayout());

		panelHeader.setBackground(new Color(34, 153, 84));
		panelHeader.setPreferredSize(new Dimension(UI.WIDTH_MENU, 50));
		panelHeader.setLayout(new GridBagLayout());

		lbName.setFont(new Font("Segoe UI", 1, 14));
		lbName.setForeground(new Color(255, 255, 255));
		lbName.setText("LAB I");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.BASELINE;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new Insets(0, 5, 0, 0);
		panelHeader.add(lbName, gridBagConstraints);

		btnClose.setText("Salir");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.BASELINE;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		panelHeader.add(btnClose, gridBagConstraints);

		panelLeft.add(panelHeader, BorderLayout.PAGE_START);

		panelSideBar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelLeft.add(panelSideBar, BorderLayout.CENTER);

		getContentPane().add(panelLeft, BorderLayout.LINE_START);

		panelLayout.setMaximumSize(new Dimension(UI.WIDTH_CONTENT, UI.HEIGHT));
		panelLayout.setMinimumSize(new Dimension(UI.WIDTH_CONTENT, UI.HEIGHT));
		panelLayout.setPreferredSize(new Dimension(UI.WIDTH_CONTENT, UI.HEIGHT));
		panelLayout.setLayout(new CardLayout());
		getContentPane().add(panelLayout, BorderLayout.CENTER);
		
		pack();
	}
}
