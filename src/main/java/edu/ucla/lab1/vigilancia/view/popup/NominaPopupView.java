package edu.ucla.lab1.vigilancia.view.popup;

import java.awt.*;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.ErrorPopup;

public class NominaPopupView extends JFrame implements PopupView {
	private static final long serialVersionUID = 1L;
	
	private JLabel lbTitle;
    
	private JButton btnCancel;
	private JButton btnOK;
    
    private JButton btnNomVig;
    private JButton btnNomMen;
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    
	public NominaPopupView() {
 
    	initComponents();
    	
        initValidators();
        setLocationRelativeTo(null);
    }
	
	@Override
	public JButton getBtnOK() {
		return btnOK;
	}
	
	public JButton getBtnNomVig() {
		return btnNomVig;
	}
	
	public JButton getBtnNomMen() {
		return btnNomMen;
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
        
        btnNomVig = new JButton();
        btnNomMen = new JButton();
        
        btnCancel = new JButton();
        
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new Dimension(400, 50));
        jPanel1.setLayout(new GridBagLayout());

        lbTitle.setFont(new Font("Segoe UI", 1, 14));
        lbTitle.setText("Nueva Nomina");
        jPanel1.add(lbTitle, new GridBagConstraints());

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new Dimension(400, 75));
        jPanel2.setLayout(new GridBagLayout());

        
        btnCancel.setText("Cancelar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(btnCancel, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.PAGE_END);

        jPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new GridBagLayout());
        
        // Buttons
        
        btnNomVig.setText("Agregar Nomina Vigilante");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel3.add(btnNomVig, gridBagConstraints);
        
        btnNomMen.setText("Generar Nomina Mensual");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        jPanel3.add(btnNomMen, gridBagConstraints);
        
        getContentPane().add(jPanel3, BorderLayout.CENTER);

        pack();
    }
	
}
