package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class FacturaPopupView extends JFrame implements PopupView {
private static final long serialVersionUID = 1L;

	DefaultComboBoxModel<Servicio> servicioComboBoxModel = new DefaultComboBoxModel<>();
    
    private JLabel lbTitle;
    
	private JButton btnCancel;
    private JButton btnOK;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
    private JComboBox<Servicio> cboServicio;
    private JSpinner spnFechaPago;
    private JTextField txtDesc;
    private JTextField txtIva;
    private JTextField txtStatus;
    private JTextField txtSubtotal;
    private JTextField txtMontoTotal;

    public FacturaPopupView(String select) {
    	
    	if(select == "add") {
    		initAddComponents();
    	} else if(select == "view") {
    		initViewComponents();
    	} else {
    		initComponents();
    	}
    	
    	initValidators();
        setLocationRelativeTo(null);
		cboServicio.setModel(servicioComboBoxModel);
    }

	public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }

    public void showError(Exception e) {
        ErrorPopup.show(e);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnOK() {
        return btnOK;
    }

    public JLabel getLbTitle() {
        return lbTitle;
    }
    
    public DefaultComboBoxModel<Servicio> getServicioComboBoxModel() {
        return servicioComboBoxModel;
    }
	
    public JComboBox<Servicio> getCboServicio() {
        return cboServicio;
    }
    
    public JSpinner getSpnFechaPago() {
        return spnFechaPago;
    }
    
    public JTextField getTxtDesc() {
        return txtDesc;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }
    
    public JTextField getTxtStatus() {
        return txtStatus;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }
    
    public JTextField getTxtMontoTotal() {
        return txtMontoTotal;
    }
    
    private void initValidators() {
        /*txtNroFact.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();
                }
            }
         });*/
	}

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        lbTitle = new JLabel();
        
        btnCancel = new JButton();
        btnOK = new JButton();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        
        spnFechaPago = new JSpinner();
        txtDesc = new JTextField();
        txtIva = new JTextField();
        txtStatus = new JTextField();
        txtSubtotal = new JTextField();
        txtMontoTotal = new JTextField();
        cboServicio = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Factura");
        jPanel1.add(lbTitle, new GridBagConstraints());

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new Dimension(400, 75));
        jPanel2.setLayout(new GridBagLayout());

        btnOK.setText("Aceptar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnOK, gridBagConstraints);
        
        btnCancel.setText("Cancelar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnCancel, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.PAGE_END);

        jPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new GridBagLayout());
        
        // Labels
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel1.setText("Servicio ID:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha de Pago:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Descripcion:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel4.setText("IVA:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Status:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("SubTotal:");
        jPanel3.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel7.setText("Monto Total:");
        jPanel3.add(jLabel7, gridBagConstraints);
        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboServicio, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFechaPago.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaPago.setEditor(new JSpinner.DateEditor(spnFechaPago, model.toPattern()));
        jPanel3.add(spnFechaPago, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDesc, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtIva, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtStatus, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtSubtotal, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtMontoTotal, gridBagConstraints);
 
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
    
    private void initAddComponents() {
        GridBagConstraints gridBagConstraints;

        lbTitle = new JLabel();
        
        btnCancel = new JButton();
        btnOK = new JButton();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();

        cboServicio = new JComboBox<>();
        txtDesc = new JTextField();
        txtIva = new JTextField();
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Factura");
        jPanel1.add(lbTitle, new GridBagConstraints());

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new Dimension(400, 75));
        jPanel2.setLayout(new GridBagLayout());

        btnOK.setText("Aceptar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnOK, gridBagConstraints);
        
        btnCancel.setText("Cancelar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnCancel, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.PAGE_END);

        jPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new GridBagLayout());
        
        // Labels
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel1.setText("Servicio ID:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Descripcion:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("IVA:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboServicio, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDesc, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtIva, gridBagConstraints);
 
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
    
    private void initViewComponents() {
    	GridBagConstraints gridBagConstraints;

        lbTitle = new JLabel();
        
        btnCancel = new JButton();
        btnOK = new JButton();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        
        spnFechaPago = new JSpinner();
        txtDesc = new JTextField();
        txtIva = new JTextField();
        txtStatus = new JTextField();
        txtSubtotal = new JTextField();
        txtMontoTotal = new JTextField();
        cboServicio = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Factura");
        jPanel1.add(lbTitle, new GridBagConstraints());

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new Dimension(400, 75));
        jPanel2.setLayout(new GridBagLayout());

        btnOK.setText("Aceptar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnOK, gridBagConstraints);
        
        btnCancel.setText("Cancelar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnCancel, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.PAGE_END);

        jPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new GridBagLayout());
        
        // Labels
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel1.setText("Servicio ID:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha de Pago:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Descripcion:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel4.setText("IVA:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Status:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("SubTotal:");
        jPanel3.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel7.setText("Monto Total:");
        jPanel3.add(jLabel7, gridBagConstraints);
        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboServicio, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFechaPago.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaPago.setEditor(new JSpinner.DateEditor(spnFechaPago, model.toPattern()));
        jPanel3.add(spnFechaPago, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDesc, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtIva, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtStatus, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtSubtotal, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtMontoTotal, gridBagConstraints);
 
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
}
