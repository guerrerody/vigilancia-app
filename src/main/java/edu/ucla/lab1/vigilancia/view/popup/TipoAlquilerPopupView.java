package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class TipoAlquilerPopupView extends JFrame implements PopupView {
private static final long serialVersionUID = 1L;
	
	private JLabel lbTitle;
	
	private JButton btnCancel;
    private JButton btnOK;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
    private JTextField txtNombre;
    private JTextField txtCostoUso;
    private JTextField txtCostoMan;
    
    public TipoAlquilerPopupView() {
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
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }
    
    public JTextField getTxtCostoUso() {
        return txtCostoUso;
    }
    
    public JTextField getTxtCostoMan(){
        return txtCostoMan;
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
        
        txtNombre = new JTextField();
        txtCostoUso = new JTextField();
        txtCostoMan = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nuevo Tipo de Alquiler");
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
        jLabel1.setText("Nombre:");
        jPanel3.add(jLabel1, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel2.setText("Costo Uso:");
        jPanel3.add(jLabel2, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jLabel3.setText("Costo Mantenimiento:");
        jPanel3.add(jLabel3, gridBagConstraints);
        
        // Inputs
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtNombre, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCostoUso, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCostoMan, gridBagConstraints);
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }

}
