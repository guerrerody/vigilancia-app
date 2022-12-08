package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.WindowConstants;
import javax.swing.JRadioButton;

import edu.ucla.lab1.vigilancia.model.Horario;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class HorarioPopupView  extends JFrame implements PopupView {
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
	
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	
	private JComboBox<Vigilante> cboVigilante;
    private JSpinner spnFechaEn;
    private JSpinner spnFechaSal;
    
    public HorarioPopupView() {
        initComponents();
        initValidators();
        setLocationRelativeTo(null);
        cboVigilante.setModel(vigilanteComboBoxModel);
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
	
    public DefaultComboBoxModel<Vigilante> getVigilanteComboBoxModel() {
        return vigilanteComboBoxModel;
    }
	
    public JComboBox<Vigilante> getCboVigilante() {
        return cboVigilante;
    }
    
    public JSpinner getSpnFechaEn() {
		return spnFechaEn;
	}
    public JSpinner getSpnFechaSal() {
		return spnFechaSal;
	}
    
    private void initValidators() {   
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

		cboVigilante = new JComboBox<>();
		spnFechaEn = new JSpinner();
		spnFechaSal = new JSpinner();

		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setPreferredSize(new Dimension(400, 50));
		jPanel1.setLayout(new GridBagLayout());

		lbTitle.setFont(new Font("Segoe UI", 1, 14));
		lbTitle.setText("Horario del Vigilante");
		jPanel1.add(lbTitle, new GridBagConstraints());

		getContentPane().add(jPanel1, BorderLayout.PAGE_START);
		
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
		jLabel1.setText("Vigilante:");
		jPanel3.add(jLabel1, gridBagConstraints);
		
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Fecha de Entrada:");
        jPanel3.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Fecha de Salida:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
     // Inputs
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 0);
		jPanel3.add(cboVigilante, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnFechaEn.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaEn.setEditor(new JSpinner.DateEditor(spnFechaEn, model.toPattern()));
        
        jPanel3.add(spnFechaEn, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnFechaSal.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model1 = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaSal.setEditor(new JSpinner.DateEditor(spnFechaSal, model1.toPattern()));
        
        jPanel3.add(spnFechaSal, gridBagConstraints);
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

		pack();
    }
    
    
}