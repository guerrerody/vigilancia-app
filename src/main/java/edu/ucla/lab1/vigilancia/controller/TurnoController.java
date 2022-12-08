package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.TurnoPopupController;
import edu.ucla.lab1.vigilancia.dao.TurnoDao;
import edu.ucla.lab1.vigilancia.model.ServicioExtra;
import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.view.popup.ServicioExtraPopupView;
import edu.ucla.lab1.vigilancia.view.popup.TurnoPopupView;

public class TurnoController extends ManagerController {
	private TurnoDao TurnoDao = new TurnoDao();
	private TurnoPopupController popupController = new TurnoPopupController();
	
	public TurnoController() {
	}

	@Override
	public void actionAdd() {
		popupController.add(new TurnoPopupView(), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?",
					"Eliminar turno", ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					TurnoDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione el Turno para editar");
			} else {
				var v = TurnoDao.getByIds(selectedId[0], selectedId[1])
						.orElseThrow(() -> new Exception("El Turno que seleccionó NO es válido"));
				popupController.edit(new TurnoPopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(TurnoDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
		
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<Turno> turno = TurnoDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(turno);
		} catch (Exception e) {
			view.showError(e);
		}
	}
}