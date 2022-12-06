package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.ServicioExtra;

public class ServicioExtraView extends ManagerPaneView <ServicioExtra> {
	private static final long serialVersionUID = 1L;

	String[] fieldsSearch = { "servicio_id", "cant" };

	public ServicioExtraView() {
        super();
        setTableModel();
        renderTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("Servicio ID");
		tableModel.addColumn("Alquiler ID");
		tableModel.addColumn("Tipo de Alquiler");
		tableModel.addColumn("Cantidad Bicicletas/Radios");

		this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}


}
