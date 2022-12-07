package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class NominaPopupView extends JFrame implements PopupView {
	private static final long serialVersionUID = 1L;
	
	DefaultComboBoxModel<Vigilante> vigilanteComboBoxModel = new DefaultComboBoxModel<>();
	
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
    private JLabel jLabel8;
    private JLabel jLabel9;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
    private JComboBox<Vigilante> cboVigilante;
    private JSpinner spnFecha;
    private JTextField txtDesc;
    private JTextField txtDiasTrab;
    private JTextField txtHorasExtras;
    private JTextField txtDiasFalta;
    private JTextField txtSueldoBase;
    private JTextField txtPagoExtra;
    private JTextField txtDeduccion;

	public NominaPopupView(boolean add) {
		
		if(add == true) {
    		initAddComponents();
    	} else {
    		initComponents();
    	}
        initValidators();
        setLocationRelativeTo(null);
    }
	
	@Override
	public JButton getBtnOK() {
		return btnOK;
	}

	@Override
	public JButton getBtnCancel() {
		return btnCancel;
	}

	@Override
	public JLabel getLbTitle() {
		return lbTitle;
	}

	@Override
	public void showError(String message) {
		ErrorPopup.show(new Exception(message));
	}

	@Override
	public void showError(Exception e) {
		ErrorPopup.show(e);
	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public DefaultComboBoxModel<Vigilante> getVigilanteComboBoxModel() {
        return vigilanteComboBoxModel;
    }
	
    public JComboBox<Vigilante> getCboVigilante() {
        return cboVigilante;
    }

	public JSpinner getSpnFecha() {
		return spnFecha;
	}

	public void setSpnFecha(JSpinner spnFecha) {
		this.spnFecha = spnFecha;
	}

	public JTextField getTxtDesc() {
		return txtDesc;
	}

	public void setTxtDesc(JTextField txtDesc) {
		this.txtDesc = txtDesc;
	}

	public JTextField getTxtDiasTrab() {
		return txtDiasTrab;
	}

	public void setTxtDiasTrab(JTextField txtDiasTrab) {
		this.txtDiasTrab = txtDiasTrab;
	}

	public JTextField getTxtHorasExtras() {
		return txtHorasExtras;
	}

	public void setTxtHorasExtras(JTextField txtHorasExtras) {
		this.txtHorasExtras = txtHorasExtras;
	}

	public JTextField getTxtDiasFalta() {
		return txtDiasFalta;
	}

	public void setTxtDiasFalta(JTextField txtDiasFalta) {
		this.txtDiasFalta = txtDiasFalta;
	}

	public JTextField getTxtSueldoBase() {
		return txtSueldoBase;
	}

	public void setTxtSueldoBase(JTextField txtSueldoBase) {
		this.txtSueldoBase = txtSueldoBase;
	}

	public JTextField getTxtPagoExtra() {
		return txtPagoExtra;
	}

	public void setTxtPagoExtra(JTextField txtPagoExtra) {
		this.txtPagoExtra = txtPagoExtra;
	}

	public JTextField getTxtDeduccion() {
		return txtDeduccion;
	}

	public void setTxtDeduccion(JTextField txtDeduccion) {
		this.txtDeduccion = txtDeduccion;
	}
	
	private void initValidators() {
		
		/*txtDiasTrab.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
		
		txtHorasExtras.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
		
		txtDiasFalta.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
    	
        txtSueldoBase.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
        txtPagoExtra.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
        txtDeduccion.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
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
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        
        spnFecha = new JSpinner();
        txtDesc = new JTextField();
        txtDiasTrab = new JTextField();
        txtHorasExtras = new JTextField();
        txtDiasFalta = new JTextField();
        txtSueldoBase = new JTextField();
        txtPagoExtra = new JTextField();
        txtDeduccion = new JTextField();
        cboVigilante = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Nomina");
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
        jLabel1.setText("Vigilante ID:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha:");
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
        jLabel4.setText("Dias trabajados:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Horas Extra:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("Dias Faltos:");
        jPanel3.add(jLabel6, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel7.setText("Sueldo Base:");
        jPanel3.add(jLabel7, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel8.setText("Pago Extra:");
        jPanel3.add(jLabel8, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel9.setText("Deducción:");
        jPanel3.add(jLabel9, gridBagConstraints);
        
        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboVigilante, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFecha.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFecha.setEditor(new JSpinner.DateEditor(spnFecha, model.toPattern()));
        jPanel3.add(spnFecha, gridBagConstraints);
        
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
        jPanel3.add(txtDiasTrab, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtHorasExtras, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDiasFalta, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtSueldoBase, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtPagoExtra, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDeduccion, gridBagConstraints);
        
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
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        
        spnFecha = new JSpinner();
        txtDesc = new JTextField();
        txtDiasTrab = new JTextField();
        txtHorasExtras = new JTextField();
        txtDiasFalta = new JTextField();
        txtSueldoBase = new JTextField();
        txtPagoExtra = new JTextField();
        txtDeduccion = new JTextField();
        cboVigilante = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Nomina");
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
        jLabel1.setText("Vigilante ID:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha:");
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
        jLabel4.setText("Dias trabajados:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Horas Extra:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("Dias Faltos:");
        jPanel3.add(jLabel6, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel7.setText("Sueldo Base:");
        jPanel3.add(jLabel7, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel8.setText("Pago Extra:");
        jPanel3.add(jLabel8, gridBagConstraints);
        
        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboVigilante, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFecha.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFecha.setEditor(new JSpinner.DateEditor(spnFecha, model.toPattern()));
        jPanel3.add(spnFecha, gridBagConstraints);
        
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
        jPanel3.add(txtDiasTrab, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtHorasExtras, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDiasFalta, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtSueldoBase, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtPagoExtra, gridBagConstraints);
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
	
	
}
