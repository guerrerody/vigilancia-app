package edu.ucla.lab1.vigilancia.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.ucla.lab1.vigilancia.model.Factura;
import edu.ucla.lab1.vigilancia.utils.IconManager;

public class FacturaView extends ManagerPaneView<Factura>{
	private static final long serialVersionUID = 1L;
	
	String[] fieldsSearch = { "id", "fec_pago", "servicio_id" };
	
	JButton btnView;
	
	public JButton getBtnView() {
        return btnView;
    }

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

	public void initComponents() {
		super.initComponents();
		GridBagConstraints gridBagConstraints;
		btnView = new JButton();
        
		IconManager im = new IconManager();
		btnView.setIcon(im.getIcon("view_25px.png"));
        btnView.putClientProperty("JButton.buttonType", "roundRect");
		btnView.setFont(new Font("Segoe UI", 0, 14));
        btnView.setText("Ver");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new Insets(15, 5, 15, 5);
        jPanel1.add(btnView, gridBagConstraints);
        
	}
}








