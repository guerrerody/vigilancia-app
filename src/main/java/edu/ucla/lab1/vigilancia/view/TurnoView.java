package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Turno;

public class TurnoView extends ManagerPaneView <Turno> {
	private static final long serialVersionUID = 1L;

	String[] fieldsSearch = { "id", "vigilante_id", "servicio_id"};

	public TurnoView() {
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
		tableModel.addColumn("Fecha inicio de turno");
		tableModel.addColumn("Hora inicio de turno");
		tableModel.addColumn("Fecha fin de turno");
		tableModel.addColumn("Hora fin de turno");
		tableModel.addColumn("Falta");
		tableModel.addColumn("¿Tiene justificativo?");
		
		this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}


}