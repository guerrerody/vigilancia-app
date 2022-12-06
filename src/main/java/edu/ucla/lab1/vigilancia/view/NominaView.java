package edu.ucla.lab1.vigilancia.view;

import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Nomina;

public class NominaView extends ManagerPaneView<Nomina>{
	private static final long serialVersionUID = 1L;
	
	private JRadioButton radioBtnMensual;
	private JRadioButton radioBtnSemanal;
	
    public JRadioButton getRadioBtnMensual() {
        return radioBtnMensual;
    }
    public JRadioButton getRadioBtnSemanal() {
        return radioBtnSemanal;
    }
	
	String[] fieldsSearch = { "cedula", "nombre", "fecha" };

	public NominaView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Vigilante Id");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Dias Trabajados");
        tableModel.addColumn("Horas Extra");
        tableModel.addColumn("Faltas");
        tableModel.addColumn("Sueldo Base");
        tableModel.addColumn("Pago Extra");
        tableModel.addColumn("Deducción");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
        this.getRadioBtnMensual().setText("Mensual");
        this.getRadioBtnSemanal().setText("Semanal");
	}

}
