package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.DetalleServicio;

public class DetalleServicioView extends ManagerPaneView <DetalleServicio> {
	private static final long serialVersionUID = 1L;

	String[] fieldsSearch = {"servicio_id", "vigilante_id","turno_id"};

	public DetalleServicioView() {
        super();
        setTableModel();
        renderTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("Turno ID");
		tableModel.addColumn("Vigilante ID");
		tableModel.addColumn("Servicio ID");
		tableModel.addColumn("Resumen");

		
		this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}
}


