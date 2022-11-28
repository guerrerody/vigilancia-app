package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.TipoClientePopupController;
import edu.ucla.lab1.vigilancia.dao.TipoClienteDao;
import edu.ucla.lab1.vigilancia.model.TipoCliente;
import edu.ucla.lab1.vigilancia.view.popup.TipoClientePopupView;

public class TipoClienteController extends ManagerController {
	private TipoClienteDao tcDao = new TipoClienteDao();
	private TipoClientePopupController popupController = new TipoClientePopupController();

	public TipoClienteController() {

	}

	@Override
	public void actionAdd() {
		popupController.add(new TipoClientePopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Tipos de Cliente",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					tcDao.deleteById(selectedIds[i]);
					updateData();
				}
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
				throw new Exception("Seleccione el Tipo de Cliente para editar");
			} else {
				var tc = tcDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El Tipo de Cliente que seleccionó NO existe"));
				popupController.edit(new TipoClientePopupView(), tc, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateData() {
		try {
			view.setTableData(tcDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionSearch() {
		try {
			ArrayList<TipoCliente> tcs = tcDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(tcs);
		} catch (Exception e) {
			view.showError(e);
		}
	}	
	

}
