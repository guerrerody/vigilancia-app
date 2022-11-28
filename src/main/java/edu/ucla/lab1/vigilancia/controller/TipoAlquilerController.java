package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.TipoAlquilerPopupController;
import edu.ucla.lab1.vigilancia.dao.TipoAlquilerDao;
import edu.ucla.lab1.vigilancia.model.TipoAlquiler;
import edu.ucla.lab1.vigilancia.view.popup.TipoAlquilerPopupView;

public class TipoAlquilerController extends ManagerController {
	private TipoAlquilerDao taDao = new TipoAlquilerDao();
	private TipoAlquilerPopupController popupController = new TipoAlquilerPopupController();

	public TipoAlquilerController() {
		
	}

	@Override
	public void actionAdd() {
		popupController.add(new TipoAlquilerPopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Tipos de Alquiler",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					taDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione el Tipo de Alquiler para editar");
			} else {
				var ta = taDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El Tipo de Alquiler que seleccionó NO existe"));
				popupController.edit(new TipoAlquilerPopupView(), ta, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateData() {
		try {
			view.setTableData(taDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionSearch() {
		try {
			ArrayList<TipoAlquiler> tas = taDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(tas);
		} catch (Exception e) {
			view.showError(e);
		}
	}	
	

}
