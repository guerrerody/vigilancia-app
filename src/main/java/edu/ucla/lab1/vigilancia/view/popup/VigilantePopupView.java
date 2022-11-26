package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class VigilantePopupView extends JFrame implements PopupView {
	
    private static final long serialVersionUID = 1L;
    
    private JLabel lbTitle;
    
	private JButton btnCancel;
    private JButton btnOK;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JSpinner spnFecNac;
    private JTextField txtCorreo;
    private JTextField txtTelefono;

    public VigilantePopupView() {
        initComponents();
        initValidators();
        setLocationRelativeTo(null);
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
    
    public JTextField getTxtCedula() {
        return txtCedula;
    }
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JSpinner getSpnFecNac() {
        return spnFecNac;
    }
    
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }
    
    private void initValidators() {
    	// Cédula debe aceptar solo dígitos
        txtCedula.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // Si no es un número, ignora el evento
                }
            }
         });
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
        
        txtCedula = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        spnFecNac = new JSpinner();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nuevo Vigilante");
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
        jLabel1.setText("Cédula:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Apellido:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel4.setText("Fecha de Nacimiento:");
        jPanel3.add(jLabel4, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel5.setText("Correo Electrónico:");
        jPanel3.add(jLabel5, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel6.setText("Número de teléfono:");
        jPanel3.add(jLabel6, gridBagConstraints);
        
        // Inputs
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCedula, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtNombre, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtApellido, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        spnFecNac.setModel(new SpinnerDateModel(new Date(), null, new Date(), Calendar.DAY_OF_MONTH));
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        spnFecNac.setEditor(new JSpinner.DateEditor(spnFecNac, model.toPattern()));
        
        jPanel3.add(spnFecNac, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCorreo, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtTelefono, gridBagConstraints);
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
}
