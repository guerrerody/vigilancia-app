package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class ServicioPopupView extends JFrame implements PopupView{
	private static final long serialVersionUID = 1L;
	
	DefaultComboBoxModel<Cliente> clienteComboBoxModel = new DefaultComboBoxModel<>();
    
    private JLabel lbTitle;
    
	private JButton btnCancel;
    private JButton btnOK;
    private JButton btnAddExtra;
    private JButton btnAddVig;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
    private JComboBox<Cliente> cboCliente;
    private JSpinner spnFechaIn;
    private JSpinner spnFechaFin;
    private JTextField txtDescr;
    private JTextField txtCosto;
    private JTextField txtStatus;

    public ServicioPopupView() {
        initComponents();
        initValidators();
        setLocationRelativeTo(null);
        cboCliente.setModel(clienteComboBoxModel);
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
    
    public DefaultComboBoxModel<Cliente> getClienteComboBoxModel() {
        return clienteComboBoxModel;
    }
	
    public JComboBox<Cliente> getCboCliente() {
        return cboCliente;
    }

	public JSpinner getSpnFechaIn() {
		return spnFechaIn;
	}

	public void setSpnFechaIn(JSpinner spnFechaIn) {
		this.spnFechaIn = spnFechaIn;
	}

	public JSpinner getSpnFechaFin() {
		return spnFechaFin;
	}

	public void setSpnFechaFin(JSpinner spnFechaFin) {
		this.spnFechaFin = spnFechaFin;
	}

	public JTextField getTxtDescr() {
		return txtDescr;
	}

	public void setTxtDescr(JTextField txtDescr) {
		this.txtDescr = txtDescr;
	}
	
	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JTextField getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(JTextField txtStatus) {
		this.txtStatus = txtStatus;
	}

	private void initValidators() {
        /*txtServId.addKeyListener(new KeyAdapter() {
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
        btnAddExtra = new JButton();
        btnAddVig = new JButton();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        
        spnFechaIn = new JSpinner();
        spnFechaFin = new JSpinner();
        txtDescr = new JTextField();
        txtCosto = new JTextField();
        txtStatus = new JTextField();
        cboCliente = new JComboBox<>();


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nuevo Servicio");
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
		jLabel1.setText("Cliente:");
		jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha Inicio:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Fecha Fin:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel4.setText("Descripcion:");
        jPanel3.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Costo Base Bs.:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        /*gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("Status:");
        jPanel3.add(jLabel6, gridBagConstraints);*/

        // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cboCliente, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFechaIn.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        SimpleDateFormat model1 = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaIn.setEditor(new JSpinner.DateEditor(spnFechaIn, model1.toPattern()));
        jPanel3.add(spnFechaIn, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFechaFin.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        SimpleDateFormat model2 = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaFin.setEditor(new JSpinner.DateEditor(spnFechaFin, model2.toPattern()));
        jPanel3.add(spnFechaFin, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtDescr, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCosto, gridBagConstraints);
        
        /*gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtStatus, gridBagConstraints);
        
        btnAddExtra.setText("Agregar Alquiler");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.1;
        jPanel3.add(btnAddExtra, gridBagConstraints);
        
        btnAddVig.setText("Agregar Vigilantes");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.1;
        jPanel3.add(btnAddVig, gridBagConstraints);*/
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
}
