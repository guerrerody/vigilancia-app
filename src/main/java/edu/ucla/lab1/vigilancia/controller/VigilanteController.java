package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.VigilantePopupController;
import edu.ucla.lab1.vigilancia.dao.VigilanteDao;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.view.popup.VigilantePopupView;

public class VigilanteController extends ManagerController {
	private VigilanteDao vigDao = new VigilanteDao();

	VigilantePopupController popupController = new VigilantePopupController();
	VigilantePopupController vigilantePopupController = new VigilantePopupController();

	public VigilanteController() {
		super();
	}

	@Override
	public void actionAdd() {
		vigilantePopupController.add(new VigilantePopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Vigilantes",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					vigDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione el Vigilante para editar");
			} else {
				var v = vigDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El Vigilante que seleccionó NO es válido"));
				popupController.edit(new VigilantePopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(vigDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<Vigilante> vigilantes = vigDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(vigilantes);
		} catch (Exception e) {
			view.showError(e);
		}
	}
}
