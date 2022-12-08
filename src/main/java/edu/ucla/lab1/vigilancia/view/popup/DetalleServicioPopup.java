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

import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class DetalleServicioPopup  extends JFrame implements PopupView {
	private static final long serialVersionUID = 1L;
	
	DefaultComboBoxModel<Vigilante> vigilanteComboBoxModel = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Servicio> servicioComboBoxModel = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<Turno> turnoComboBoxModel = new DefaultComboBoxModel<>();
	
	private JLabel lbTitle;

	private JButton btnCancel;
	private JButton btnOK;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	
	private JComboBox<Vigilante> cboVigilante;
	private JComboBox<Servicio> cboServicio;
	private JComboBox<Turno> cboTurno;
	private JTextField txtResumen;
	
    public DetalleServicioPopup() {
        initComponents();
        initValidators();
        setLocationRelativeTo(null);
        cboVigilante.setModel(vigilanteComboBoxModel);
        cboServicio.setModel(servicioComboBoxModel);
        cboTurno.setModel(turnoComboBoxModel);
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
    
    public DefaultComboBoxModel<Servicio> getServicioComboBoxModel() {
        return servicioComboBoxModel;
    }
	
    public JComboBox<Servicio> getCboServicio() {
        return cboServicio;
    }
    
    public DefaultComboBoxModel<Turno> getTurnoComboBoxModel() {
        return turnoComboBoxModel;
    }
	
    public JComboBox<Turno> getCboTurno() {
        return cboTurno;
    }
    
	public JTextField getTxtResumen() {
		return txtResumen;
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

		txtResumen = new JTextField();
		cboVigilante = new JComboBox<>();
		cboServicio = new JComboBox<>();
		cboTurno = new JComboBox<>();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setPreferredSize(new Dimension(400, 50));
		jPanel1.setLayout(new GridBagLayout());

		lbTitle.setFont(new Font("Segoe UI", 1, 14));
		lbTitle.setText("Detalles del Servicio");
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
		jLabel3.setText("Turno:");
		jPanel3.add(jLabel3, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		jLabel4.setText("Resumen:");
		jPanel3.add(jLabel4, gridBagConstraints);
		
		// Inputs
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(cboServicio, gridBagConstraints);
	
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(cboVigilante, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		jPanel3.add(cboTurno, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		jPanel3.add(txtResumen, gridBagConstraints);

		getContentPane().add(jPanel3, BorderLayout.CENTER);

		pack();
		
	}
    
}