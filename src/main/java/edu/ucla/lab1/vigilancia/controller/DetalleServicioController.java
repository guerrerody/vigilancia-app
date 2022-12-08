package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.controller.popup.DetalleServicioPopupController;
import edu.ucla.lab1.vigilancia.dao.DetalleServicioDao;
import edu.ucla.lab1.vigilancia.model.DetalleServicio;
import edu.ucla.lab1.vigilancia.view.popup.DetalleServicioPopupView;

public class DetalleServicioController extends ManagerController {
	private DetalleServicioDao detaDao = new DetalleServicioDao();

	DetalleServicioPopupController popupController = new DetalleServicioPopupController();
	DetalleServicioPopupController horarioPopupController = new DetalleServicioPopupController();
	
	public DetalleServicioController() {
		super();
	}

	@Override
	public void actionAdd() {
		popupController.add(new DetalleServicioPopupView(), this::updateData, view::showError);
		
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Asignacion",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					DetalleServicioDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione la Asignacion para editar");
			} else {
				var v = detaDao.getById(selectedId)
						.orElseThrow(() -> new Exception("La asignacion que seleccionó NO es válida"));
				popupController.edit(new DetalleServicioPopupView(), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
		
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(DetalleServicioDao.getAll());
		} catch (Exception e) {
			view.showError(e);
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<DetalleServicio> detalleservicio = detaDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(detalleservicio);
		} catch (Exception e) {
			view.showError(e);
		}
		
	}
	
}
	