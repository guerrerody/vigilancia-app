package edu.ucla.lab1.vigilancia.view;

import javax.swing.DefaultComboBoxModel;

import edu.ucla.lab1.vigilancia.model.Factura;

public class FacturaView extends ManagerPaneView<Factura>{
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = { "id", "fec_pago", "servicio_id" };

	public FacturaView() {
        super();
        setTableModel();
        renderTable();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setTableModel() {
		tableModel.addColumn("ID");
        tableModel.addColumn("Fecha pago");
        tableModel.addColumn("Servicio");
        tableModel.addColumn("IVA");
        tableModel.addColumn("Subtotal");
        tableModel.addColumn("Total");
        
        this.getCboSearchField().setModel(new DefaultComboBoxModel(fieldsSearch));
		
	}
}
