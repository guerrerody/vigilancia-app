package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.TipoCliente;

public class TipoClienteView extends ManagerPaneView<TipoCliente> {
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = {"nombre"};

	public TipoClienteView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Nombre del Tipo");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}

}
