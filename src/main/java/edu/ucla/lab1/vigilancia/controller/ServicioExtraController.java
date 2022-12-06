package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.ServicioExtraPopupController;
import edu.ucla.lab1.vigilancia.dao.ServicioExtraDao;
import edu.ucla.lab1.vigilancia.model.ServicioExtra;
import edu.ucla.lab1.vigilancia.view.popup.ServicioExtraPopupView;

public class ServicioExtraController extends ManagerController {
	private ServicioExtraDao servicioExtraDao = new ServicioExtraDao();
	private ServicioExtraPopupController popupController = new ServicioExtraPopupController();

	public ServicioExtraController() {

	}

	@Override
	public void actionAdd() {
		popupController.add(new ServicioExtraPopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?",
					"Eliminar servicios extras", ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					servicioExtraDao.deleteById(selectedIds[i]);
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
			int[] selectedId = view.getSelectedIdu();
			if (selectedId[0] < 0) {
				throw new Exception("Seleccione el servicio extra para editar");
			} else {
				var v = servicioExtraDao.getByIds(selectedId[0], selectedId[1])
						.orElseThrow(() -> new Exception("El servicio extra que seleccionó NO es válido"));
				popupController.edit(new ServicioExtraPopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateData() {
		try {
			view.setTableData(servicioExtraDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionSearch() {
		try {
			ArrayList<ServicioExtra> servicioextras = servicioExtraDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(servicioextras);
		} catch (Exception e) {
			view.showError(e);
		}
	}

}
