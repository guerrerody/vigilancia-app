package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.TipoAlquiler;

public class TipoAlquilerView  extends ManagerPaneView<TipoAlquiler> {
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = {"nombre"};

	public TipoAlquilerView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Nombre del Tipo");
        tableModel.addColumn("Costo Uso");
        tableModel.addColumn("Costo Mantenimiento");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}
	
	

}
