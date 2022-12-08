package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Horario;

public class HorarioView extends ManagerPaneView <Horario> {
	private static final long serialVersionUID = 1L;

	String[] fieldsSearch = { "id", "vigilante_id"};
	
	public HorarioView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("Horario ID");
		tableModel.addColumn("Vigilante ID");
		tableModel.addColumn("Fecha Entrada");
		tableModel.addColumn("Fecha Salida");
		
		this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}
	
}