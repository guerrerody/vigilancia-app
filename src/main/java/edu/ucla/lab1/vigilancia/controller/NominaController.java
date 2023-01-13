package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.dao.NominaDao;
import edu.ucla.lab1.vigilancia.model.Nomina;
import edu.ucla.lab1.vigilancia.controller.popup.NominaPopupController;
import edu.ucla.lab1.vigilancia.view.popup.NominaPopupView;
import edu.ucla.lab1.vigilancia.view.popup.NominaVigPopupView;

public class NominaController extends ManagerController {
	private NominaDao nomDao = new NominaDao();
	
	NominaPopupController popupController = new NominaPopupController();
	NominaPopupController nominaPopupController = new NominaPopupController();
	
	public NominaController() {
		super();
	}
	
	@Override
	public void actionAdd() {
		nominaPopupController.AddMenu(new NominaPopupView(), new NominaVigPopupView(true), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Nomina",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					nomDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione la nomina para editar");
			} else {
				var v = nomDao.getById(selectedId)
						.orElseThrow(() -> new Exception("La nomina seleccionada NO es válida"));
				popupController.edit(new NominaVigPopupView(false), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(nomDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<Nomina> nomina = nomDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(nomina);
		} catch (Exception e) {
			view.showError(e);
		}
	}

}
