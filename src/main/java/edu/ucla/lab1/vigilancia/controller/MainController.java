package edu.ucla.lab1.vigilancia.controller;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.IconManager;
import edu.ucla.lab1.vigilancia.view.ClienteView;
import edu.ucla.lab1.vigilancia.view.HomeView;
import edu.ucla.lab1.vigilancia.view.MainView;
import edu.ucla.lab1.vigilancia.view.ManagerPaneView;
import edu.ucla.lab1.vigilancia.view.MenuItem;
import edu.ucla.lab1.vigilancia.view.TipoAlquilerView;
import edu.ucla.lab1.vigilancia.view.TipoClienteView;
import edu.ucla.lab1.vigilancia.view.VigilanteView;
import edu.ucla.lab1.vigilancia.view.ServicioView;
import edu.ucla.lab1.vigilancia.view.ServicioExtraView;
import edu.ucla.lab1.vigilancia.view.FacturaView;
import edu.ucla.lab1.vigilancia.view.NominaView;
import edu.ucla.lab1.vigilancia.view.TurnoView;

public class MainController {
	// Vistas
	private MainView view;
	private HomeView homeView = new HomeView();
	private ManagerPaneView<?> vigilanteView = new VigilanteView();
	private ManagerPaneView<?> tipoClienteView = new TipoClienteView();
	private ManagerPaneView<?> tipoAlquilerView = new TipoAlquilerView();
	private ManagerPaneView<?> clienteView = new ClienteView();
	private ManagerPaneView<?> servicioView = new ServicioView();
	private ManagerPaneView<?> servicioExtraView = new ServicioExtraView();
	private ManagerPaneView<?> facturaView = new FacturaView();	
	private ManagerPaneView<?> nominaView = new NominaView();
	private ManagerPaneView<?> turnoView = new TurnoView();
	
    
    private JPanel[] cards = { homeView, vigilanteView, tipoClienteView, tipoAlquilerView, clienteView, servicioView, servicioExtraView, facturaView, nominaView, turnoView };
    
    // Controladores
    private SideBarController sideBarController = new SideBarController();
    private ManagerController vigilanteController = new VigilanteController();
    private ManagerController tipoClienteController = new TipoClienteController();
    private ManagerController tipoAlquilerController = new TipoAlquilerController();
    private ManagerController clienteController = new ClienteController();
    private ManagerController servicioController = new ServicioController();
    private ManagerController servicioExtraController = new ServicioExtraController();
    private ManagerController facturaController = new FacturaController();
    private ManagerController nominaController = new NominaController();
    private ManagerController turnoController = new TurnoController();
    
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
        menuGV.addSubMenu(new MenuItem("VN", im.getIcon("settings_25px.png"), "Nomina"));
        MenuItem menuGS = new MenuItem("GS", im.getIcon("settings_25px.png"), "Gestión de Servicios");
        menuGS.addSubMenu(new MenuItem("GSTA", im.getIcon("settings_25px.png"), "Tipos de Alquiler"));
        menuGS.addSubMenu(new MenuItem("GSS", im.getIcon("settings_25px.png"), "Servicios"));
        menuGS.addSubMenu(new MenuItem("SE", im.getIcon("settings_25px.png"), "Servicio Extra"));
        menuGS.addSubMenu(new MenuItem("ST", im.getIcon("settings_25px.png"), "Turnos"));
        menuGS.addSubMenu(new MenuItem("F", im.getIcon("settings_25px.png"), "Facturas"));
        
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
        	case "GCTC": // Gestión de tipos de cliente
        		view.setPanel(tipoClienteView);
                tipoClienteController.setView(tipoClienteView);
                tipoClienteController.updateData();
        		break;
        	case "GCC": // Gestión de clientes
                view.setPanel(clienteView);
                clienteController.setView(clienteView);
                clienteController.updateData();      		
        		break;
            case "GV": // Gestión de vigilantes
                view.setPanel(vigilanteView);
                vigilanteController.setView(vigilanteView);
                vigilanteController.updateData();
                break;
            case "VN": // Nomina
            	view.setPanel(nominaView);
            	nominaController.setView(nominaView);
            	nominaController.updateData();
            	break;
            case "GSTA": // Gestión de tipos de alquiler
        		view.setPanel(tipoAlquilerView);
                tipoAlquilerController.setView(tipoAlquilerView);
                tipoAlquilerController.updateData();
                break;
            case "GSS": // Servicio
            	view.setPanel(servicioView);
            	servicioController.setView(servicioView);
            	servicioController.updateData();
                break;
            case "SE": // Servicio Extra
            	view.setPanel(servicioExtraView);
            	servicioExtraController.setView(servicioExtraView);
            	servicioExtraController.updateData();
                break;
            case "ST": // Turnos
            	view.setPanel(turnoView);
            	turnoController.setView(turnoView);
            	turnoController.updateData();
                break;
            case "F": // Facturas
            	view.setPanel(facturaView);
            	facturaController.setView(facturaView);
            	facturaController.updateData();
                break;
            default:
                view.setPanel(homeView);
        }
    }
}
