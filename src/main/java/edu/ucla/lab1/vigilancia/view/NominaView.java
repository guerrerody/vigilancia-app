package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Nomina;

public class NominaView extends ManagerPaneView<Nomina>{
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = { "vigilante_id", "fecha" };

	public NominaView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Vigilante");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Dias Trabajados");
        tableModel.addColumn("Horas Extra");
        tableModel.addColumn("Faltas");
        tableModel.addColumn("Sueldo x Dia");
        tableModel.addColumn("Pago Extra");
        tableModel.addColumn("Deducción");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
	}
	
	

}
