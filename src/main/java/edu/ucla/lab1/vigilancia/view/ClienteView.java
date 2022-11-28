package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Cliente;

public class ClienteView extends ManagerPaneView<Cliente> {
	private static final long serialVersionUID = 1L;

	String[] fieldsSearch = { "nombre", "nombre_contac" };

	public ClienteView() {
        super();
        setTableModel();
        renderTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
		tableModel.addColumn("Nombre Cliente");
		tableModel.addColumn("Nombre Contacto");
		tableModel.addColumn("Teléfono Contacto");

		this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}

}
