package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;
import edu.ucla.lab1.vigilancia.model.Vigilante;

public class VigilanteView extends ManagerPaneView<Vigilante> {
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = { "cedula", "nombre", "apellido" };

	public VigilanteView() {
        super();
        setTableModel();
        renderTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Cédula");
        tableModel.addColumn("Nombre Completo");
        tableModel.addColumn("Fecha Nac.");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Fecha Ingreso");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}

}
