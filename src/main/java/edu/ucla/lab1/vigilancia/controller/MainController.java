package edu.ucla.lab1.vigilancia.controller;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.IconManager;
import edu.ucla.lab1.vigilancia.view.HomeView;
import edu.ucla.lab1.vigilancia.view.MainView;
import edu.ucla.lab1.vigilancia.view.ManagerPaneView;
import edu.ucla.lab1.vigilancia.view.MenuItem;
import edu.ucla.lab1.vigilancia.view.VigilanteView;

public class MainController {
	// Vistas
	private MainView view;
	private HomeView homeView = new HomeView();
	private ManagerPaneView vigilanteView = new VigilanteView();
    
    private JPanel[] cards = { homeView, vigilanteView };
    
    // Controladores
    private SideBarController sideBarController = new SideBarController();
    private ManagerController vigilanteController = new VigilanteController();
    
    
	public MainController(MainView view) {
		this.view = view;
		sideBarController.setPanelSideBar(view.getPanelSideBar());
		view.setVisible(true);
		initMenu();
        addEvent();
        view.setCards(cards);
        view.setPanel(homeView);
	}
	
	public MainView getView() {
		return view;
	}
	
    public void setView(MainView view) {
        this.view = view;
        sideBarController.setPanelSideBar(view.getPanelSideBar());
    }
    
    private void initMenu() {
        IconManager im = new IconManager();
        MenuItem menuGDC = new MenuItem("GC", im.getIcon("settings_25px.png"), "Gestión de Clientes");
        menuGDC.addSubMenu(new MenuItem("GCTC", im.getIcon("settings_25px.png"), "Tipos de Cliente"));
        menuGDC.addSubMenu(new MenuItem("GCC", im.getIcon("settings_25px.png"), "Clientes"));
        MenuItem menuGV = new MenuItem("GV", im.getIcon("settings_25px.png"), "Gestión de Vigilantes");
        MenuItem menuGS = new MenuItem("GS", im.getIcon("settings_25px.png"), "Gestión de Servicios");
        menuGS.addSubMenu(new MenuItem("GSTA", im.getIcon("settings_25px.png"), "Tipos de Alquiler"));
        menuGS.addSubMenu(new MenuItem("GSS", im.getIcon("settings_25px.png"), "Servicios"));
        
        sideBarController.addMenu(menuGDC, menuGV, menuGS);
        sideBarController.addMenuEvent(this::onMenuChange);
    }
    
    private void addEvent() {
        view.getBtnClose().addActionListener(evt -> {
            var confirm = JOptionPane.showConfirmDialog(view, "¿Seguro qué quieres salir?");
            if (confirm == JOptionPane.YES_OPTION) {
            	view.dispose();
            }
        });
    }

    public void onMenuChange(MenuItem item) {
        switch (item.getId()) {
        	case "GCTC":
        		break;
        	case "GCC":
        		break;
            case "GV": // Gestión de Vigilantes
                view.setPanel(vigilanteView);
                vigilanteController.setView(vigilanteView);
                vigilanteController.updateData();
                break;
            case "GSTA":
                break;
            case "GSS":
                break;
            default:
                view.setPanel(homeView);
        }
    }
}
