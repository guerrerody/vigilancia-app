package edu.ucla.lab1.vigilancia.view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.ucla.lab1.vigilancia.model.Model;
import edu.ucla.lab1.vigilancia.utils.ErrorPopup;
import edu.ucla.lab1.vigilancia.utils.IconManager;

public abstract class ManagerPaneView<T extends Model> extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final String SEARCH_TEXT = "Buscar";
	
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnSync;
    private JComboBox<String> cboSearchField;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JTable tblData;
    private JTextField txtSearch;
	
    protected DefaultTableModel tableModel = new DefaultTableModel();
    private IconManager im = new IconManager();
    private ArrayList<T> tableData = new ArrayList<>();

    public ManagerPaneView() {
        initComponents();
        tblData.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblData.getTableHeader().setOpaque(false);
        tblData.getTableHeader().setBackground(new Color(51, 175, 255));
        tblData.getTableHeader().setForeground(new Color(255, 255, 255));
        ((DefaultTableCellRenderer) tblData.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        tblData.setAutoCreateRowSorter(true);
        btnAdd.setIcon(im.getIcon("add_25px.png"));
        btnEdit.setIcon(im.getIcon("edit_25px.png"));
        btnDelete.setIcon(im.getIcon("delete_25px.png"));
        btnSync.setIcon(im.getIcon("sync_25px.png"));
        tblData.setModel(tableModel);
        btnAdd.putClientProperty("JButton.buttonType", "roundRect");
        btnDelete.putClientProperty("JButton.buttonType", "roundRect");
        btnEdit.putClientProperty("JButton.buttonType", "roundRect");
        btnSync.putClientProperty("JButton.buttonType", "roundRect");
    }

    public JComboBox<String> getCboSearchField() {
        return cboSearchField;
    }

    public void setCboSearchField(JComboBox<String> cboSearchField) {
        this.cboSearchField = cboSearchField;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(JTextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public DefaultTableModel getTableModel() {
        return this.tableModel;
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

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnSync() {
        return btnSync;
    }

    public ArrayList<T> getTableData() {
        return tableData;
    }

    public void setTableData(ArrayList<T> tableData) {
        this.tableData = tableData;
        renderTable();
    }

    public JTable getTblData() {
        return tblData;
    }

    public int[] getSelectedIds() {
        int selectedRows[] = tblData.getSelectedRows();
        int selectedIds[] = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int selectedRow = selectedRows[i];
            int id = (int) tblData.getValueAt(selectedRow, 0);
            selectedIds[i] = id;
        }
        return selectedIds;
    }

    public int getSelectedId() {
        int selectedRow = tblData.getSelectedRow();
        if (selectedRow < 0) {
            return -1;
        }
        int id = (int) tblData.getValueAt(selectedRow, 0);
        return id;
    }

    public void renderTable() {
        tableModel.setNumRows(0);
        try {
            for (T item : tableData) {
                tableModel.addRow(item.toRowTable());
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    public abstract void setTableModel();

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jScrollPane1 = new JScrollPane();
        tblData = new JTable() {
        	private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false; // Desactivar la edición de la Tabla
		}};
        jPanel1 = new JPanel();
        btnDelete = new JButton();
        btnSync = new JButton();
        btnEdit = new JButton();
        btnAdd = new JButton();
        jPanel2 = new JPanel();
        txtSearch = new JTextField();
        cboSearchField = new JComboBox<>();

        setBackground(new Color(118, 215, 196));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(new Dimension(MainView.UI.WIDTH_CONTENT, MainView.UI.HEIGHT));
        setLayout(new BorderLayout());

        jScrollPane1.setOpaque(false);

        tblData.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Titulo 1", "Titulo 2", "Titulo 3", "Titulo 4"
            }
        ));
        tblData.setFocusable(false);
        tblData.setRowHeight(30);
        tblData.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblData);

        add(jScrollPane1, BorderLayout.CENTER);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new GridBagLayout());

        btnDelete.setFont(new Font("Segoe UI", 0, 14));
        btnDelete.setText("Eliminar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new Insets(15, 5, 15, 5);
        jPanel1.add(btnDelete, gridBagConstraints);

        btnSync.setFont(new Font("Segoe UI", 0, 14));
        btnSync.setText("Refrescar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new Insets(15, 5, 15, 5);
        jPanel1.add(btnSync, gridBagConstraints);

        btnEdit.setFont(new Font("Segoe UI", 0, 14));
        btnEdit.setText("Editar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new Insets(15, 5, 15, 5);
        jPanel1.add(btnEdit, gridBagConstraints);

        btnAdd.setFont(new Font("Segoe UI", 0, 14));
        btnAdd.setText("Agregar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.insets = new Insets(15, 5, 15, 5);
        jPanel1.add(btnAdd, gridBagConstraints);

        add(jPanel1, BorderLayout.LINE_END);

        jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new Dimension(1008, 40));
        jPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        txtSearch.setForeground(new Color(153, 153, 153));
        txtSearch.setText(SEARCH_TEXT);
        txtSearch.setPreferredSize(new Dimension(200, 25));
        jPanel2.add(txtSearch);

        cboSearchField.setMinimumSize(new Dimension(100, 25));
        cboSearchField.setPreferredSize(new Dimension(100, 25));
        jPanel2.add(cboSearchField);

        add(jPanel2, BorderLayout.PAGE_START);
    }
}
