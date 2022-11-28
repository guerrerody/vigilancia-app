package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.ClientePopupController;
import edu.ucla.lab1.vigilancia.dao.ClienteDao;
import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.view.popup.ClientePopupView;

public class ClienteController extends ManagerController {
	private ClienteDao clienteDao = new ClienteDao();
	private ClientePopupController popupController = new ClientePopupController();

	public ClienteController() {

	}

	@Override
	public void actionAdd() {
		popupController.add(new ClientePopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?",
					"Eliminar Clientes", ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					clienteDao.deleteById(selectedIds[i]);
				}
				updateData();
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void actionEdit() {
		try {
			int selectedId = view.getSelectedId();
			if (selectedId < 0) {
				throw new Exception("Seleccione el cliente para editar");
			} else {
				var v = clienteDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El cliente que seleccionó NO es válido"));
				popupController.edit(new ClientePopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateData() {
		try {
			view.setTableData(clienteDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionSearch() {
		try {
			ArrayList<Cliente> clientes = clienteDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(clientes);
		} catch (Exception e) {
			view.showError(e);
		}
	}

}
