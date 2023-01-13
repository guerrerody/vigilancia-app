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

import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TurnoPopupView  extends JFrame implements PopupView {
	private static final long serialVersionUID = 1L;
	
	DefaultComboBoxModel<Servicio> servicioComboBoxModel = new DefaultComboBoxModel<>();
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

	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	
	private JComboBox<Servicio> cboServicio;
	private JComboBox<Vigilante> cboVigilante;
    private JSpinner spnFechaIn;
    private JSpinner spnHorIn;
    private JSpinner spnFechaFin;
    private JSpinner spnHorFin;
    private JTextField Falta;
	private JTextField txtJust;
	private JRadioButton rdbtnMarcarFaltado;
	
	public TurnoPopupView() {
        initComponents();
        initValidators();
        setLocationRelativeTo(null);
        cboServicio.setModel(servicioComboBoxModel);
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
	
    public DefaultComboBoxModel<Servicio> getServicioComboBoxModel() {
        return servicioComboBoxModel;
    }
	
    public JComboBox<Servicio> getCboServicio() {
        return cboServicio;
    }
	
    public DefaultComboBoxModel<Vigilante> getVigilanteComboBoxModel() {
        return vigilanteComboBoxModel;
    }
	
    public JComboBox<Vigilante> getCboVigilante() {
        return cboVigilante;
    }
    
	public JSpinner getSpnFechaIn() {
		return spnFechaIn;
	}


	public JSpinner getSpnHorIn() {
		return spnHorIn;
	}

	public JSpinner getSpnFechaFin() {
		return spnFechaFin;
	}
	
	public JSpinner getSpnHorFin() {
		return spnHorFin;
	}
    
	 public JTextField getFalta() {
			return Falta;
	}
	 
    public JTextField getTxtJust() {
		return txtJust;
	}
    
    public JRadioButton getRdbtnMarcarFaltado() {
		return rdbtnMarcarFaltado;
	}

	public void setRdbtnMarcarFaltado(JRadioButton rdbtnMarcarFaltado) {
		this.rdbtnMarcarFaltado = rdbtnMarcarFaltado;
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
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();

		cboServicio = new JComboBox<>();
		cboVigilante = new JComboBox<>();
		spnFechaIn = new JSpinner();
		spnFechaFin = new JSpinner();
		spnHorIn = new JSpinner();
		spnHorFin = new JSpinner();
		Falta = new JTextField();
		txtJust = new JTextField();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setPreferredSize(new Dimension(400, 50));
		jPanel1.setLayout(new GridBagLayout());

		lbTitle.setFont(new Font("Segoe UI", 1, 14));
		lbTitle.setText("Turno del Vigilante");
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
		jLabel1.setText("Servicio:");
		jPanel3.add(jLabel1, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		jLabel2.setText("Vigilante:");
		jPanel3.add(jLabel2, gridBagConstraints);
		
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Fecha de Inicio de Turno:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel4.setText("Hora de Inicio de Turno:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Fecha de Fin de Turno:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("Hora de Fin de Turno:");
        jPanel3.add(jLabel6, gridBagConstraints);
		
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel7.setText("Día faltado:");
        jPanel3.add(jLabel7, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		jLabel8.setText("¿Tiene justificativo?");
		jPanel3.add(jLabel8, gridBagConstraints);
		
		// Inputs
		
		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        jPanel3.add(cboServicio, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 0);
		jPanel3.add(cboVigilante, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnFechaIn.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaIn.setEditor(new JSpinner.DateEditor(spnFechaIn, model.toPattern()));
        
        jPanel3.add(spnFechaIn, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnHorIn.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        SimpleDateFormat model1 = new SimpleDateFormat("HH:mm:ss");
        spnHorIn.setEditor(new JSpinner.DateEditor(spnHorIn, model1.toPattern()));
        
        jPanel3.add(spnHorIn, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnFechaFin.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        SimpleDateFormat model2 = new SimpleDateFormat("dd/MM/yyyy");
        spnFechaFin.setEditor(new JSpinner.DateEditor(spnFechaFin, model2.toPattern()));
        
        jPanel3.add(spnFechaFin, gridBagConstraints);
        
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 0);
        spnHorFin.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        SimpleDateFormat model3 = new SimpleDateFormat("HH:mm:ss");
        spnHorFin.setEditor(new JSpinner.DateEditor(spnHorFin, model3.toPattern()));
        
        jPanel3.add(spnHorFin, gridBagConstraints);
        
		rdbtnMarcarFaltado = new JRadioButton("Marcar como faltado");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 6;
		jPanel3.add(rdbtnMarcarFaltado, gbc_rdbtnNewRadioButton);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new Insets(5, 5, 0, 0);
		jPanel3.add(txtJust, gridBagConstraints);

		getContentPane().add(jPanel3, BorderLayout.CENTER);

		pack();
    	
	
    }

}