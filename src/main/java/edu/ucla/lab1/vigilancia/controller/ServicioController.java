package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.ServicioPopupController;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.view.popup.ServicioPopupView;

public class ServicioController extends ManagerController {
	private ServicioDao servDao = new ServicioDao();

	ServicioPopupController popupController = new ServicioPopupController();
	ServicioPopupController servicioPopupController = new ServicioPopupController();

	public ServicioController() {
		super();
	}

	@Override
	public void actionAdd() {
		servicioPopupController.add(new ServicioPopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Servicios",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					servDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione el Servicio para editar");
			} else {
				var v = servDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El Servicio que seleccionó NO es válido"));
				popupController.edit(new ServicioPopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(servDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<Servicio> servicios = servDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(servicios);
		} catch (Exception e) {
			view.showError(e);
		}
	}
}
