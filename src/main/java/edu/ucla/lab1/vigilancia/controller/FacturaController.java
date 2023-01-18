package edu.ucla.lab1.vigilancia.controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.ucla.lab1.vigilancia.dao.FacturaDao;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.model.Factura;
import edu.ucla.lab1.vigilancia.controller.popup.FacturaPopupController;
import edu.ucla.lab1.vigilancia.view.FacturaView;
import edu.ucla.lab1.vigilancia.view.popup.FacturaPopupView;

public class FacturaController extends ManagerController {
	private FacturaDao facDao = new FacturaDao();
	
	FacturaPopupController popupController = new FacturaPopupController();
	FacturaPopupController facturaPopupController = new FacturaPopupController();
	
	public FacturaController() {
		super();
	}

	@Override
	public void actionAdd() {
		facturaPopupController.add( new FacturaPopupView(true), this::updateData, view::showError);
	}

	@Override
	public void actionDelete() {
		int selectedIds[] = view.getSelectedIds();
		try {
			if (JOptionPane.showConfirmDialog(null, "¿Confirmación de eliminación de selecionados?", "Eliminar Factura",
					ERROR_MESSAGE) == YES_OPTION) {
				for (int i = 0; i < selectedIds.length; i++) {
					facDao.deleteById(selectedIds[i]);
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
				throw new Exception("Seleccione la factura para editar");
			} else {
				var v = facDao.getById(selectedId)
						.orElseThrow(() -> new Exception("La factura seleccionada NO es válida"));
				popupController.edit(new FacturaPopupView(false), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
		
	}

	@Override
	public void updateData() {
		try {
			view.setTableData(facDao.getAll());
		} catch (Exception e) {
			view.showError(e);
		}
		
	}

	@Override
	public void actionSearch() {
		try {
			ArrayList<Factura> factura = facDao.searchByKey(view.getCboSearchField().getSelectedItem().toString(),
					String.valueOf(view.getTxtSearch().getText()));
			view.setTableData(factura);
		} catch (Exception e) {
			view.showError(e);
		}
	}
	
	public void actionView() {
		try {
			int selectedId = view.getSelectedId();
			if (selectedId < 0) {
				throw new Exception("Seleccione la factura para ver");
			} else {
				var v = facDao.getById(selectedId)
						.orElseThrow(() -> new Exception("La factura seleccionada NO es válida"));
				popupController.view(new FacturaPopupView(false), v, this::updateData, view::showError);
			}
		} catch (Exception e) {
			view.showError(e);
		}
	}

	protected void addEvent() {
		super.addEvent();
		view.getBtnView().addActionListener(evt -> actionView());
	}
}
