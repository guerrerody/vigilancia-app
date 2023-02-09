package edu.ucla.lab1.vigilancia.controller;

import edu.ucla.lab1.vigilancia.controller.popup.HorarioPopupController;
import edu.ucla.lab1.vigilancia.dao.HorarioDao;

public class HorarioController extends ManagerController {
	private HorarioDao horDao = new HorarioDao();

	HorarioPopupController popupController = new HorarioPopupController();
	HorarioPopupController horarioPopupController = new HorarioPopupController();
	
	public HorarioController() {
		super();
	}

	@Override
	public void actionAdd() {
		/*
		popupController.add(new HorarioPopupView(), this::updateData, view::showError);
		*/
		
	}

	@Override
	public void actionDelete() {
		/*
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Horarios",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					horDao.deleteById(selectedIds[i]);
					updateData();
				}
			}
		} catch (Exception e) {
			view.showError(e);
		}
		*/
	}

	@Override
	public void actionEdit() {
		/*
		try {
			int selectedId = view.getSelectedId();
			if (selectedId < 0) {
				throw new Exception("Seleccione el Horario para editar");
			} else {
				var v = horDao.getById(selectedId)
						.orElseThrow(() -> new Exception("El Horario que seleccionó NO es válido"));
				popupController.edit(new ServicioPopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
		*/
	}

	@Override
	public void updateData() {
		/*
		try {
			view.setTableData(horDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
		*/
	}

	@Override
	public void actionSearch() {
		/*
		try {
			ArrayList<Horario> horario = horDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(horario);
		} catch (Exception e) {
			view.showError(e);
		}
		*/
	}
}