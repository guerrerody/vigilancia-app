package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Servicio;

public class ServicioView extends ManagerPaneView<Servicio> {
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = { "id", "client_id", "costo"};

	public ServicioView() {
        super();
        setTableModel();
        renderTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Fecha Inicio");
        tableModel.addColumn("Fecha Fin");
        tableModel.addColumn("Descripcion");
        tableModel.addColumn("Costo Base Bs.");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}
}
