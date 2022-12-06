package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.ServicioExtraDao;
import edu.ucla.lab1.vigilancia.dao.TipoAlquilerDao;
import edu.ucla.lab1.vigilancia.model.ServicioExtra;
import edu.ucla.lab1.vigilancia.model.TipoAlquiler;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.view.popup.ServicioExtraPopupView;

public class ServicioExtraPopupController {
	ServicioExtraDao servicioExtraDao = new ServicioExtraDao();
	TipoAlquilerDao tipoAlquilerDao = new TipoAlquilerDao();
	ServicioDao servDao = new ServicioDao();
	JFrame previousView;

	public void add(ServicioExtraPopupView view, SuccessCallback sc, ErrorCallback ec) {
		if (previousView != null && previousView.isDisplayable()) {
			previousView.requestFocus();
			return;
		}
		previousView = view;
		view.setVisible(true);
		initComboBox(view);
		view.getBtnCancel().addActionListener(evt -> view.dispose());
		view.getBtnOK().addActionListener(evt -> {
			try {
				addServicioExtra(view, new ServicioExtra());
				view.dispose();
				view.showMessage("Servicio Extra agregado de forma exitosa");
				sc.onSuccess();
			} catch (Exception ex) {
				ec.onError(ex);
			}
		});
	}

	public void edit(ServicioExtraPopupView view, ServicioExtra servicio_extra, SuccessCallback sc, ErrorCallback ec) {
		if (previousView != null && previousView.isDisplayable()) {
			previousView.requestFocus();
			return;
		}
		previousView = view;
		view.setVisible(true);
		view.getLbTitle().setText("Editar Servicio Extra");

		initComboBox(view);

		view.getTxtCantidad().setText(servicio_extra.getCant().toString());
		view.getCboTipoAlquiler().setSelectedItem(servicio_extra.getTipoAlquiler());
		view.getCboTipoAlquiler().setEnabled(false);
		view.getCboServicio().setSelectedItem(servicio_extra.getServicio());
		view.getCboServicio().setEnabled(false);
		
		view.getBtnCancel().addActionListener(evt -> view.dispose());
		view.getBtnOK().setText("Actualizar");
		view.getBtnOK().addActionListener(evt -> {
			try {
				editServicioExtra(view, servicio_extra);
				view.dispose();
				view.showMessage("Información del servicio extra actualizada con éxito");
				sc.onSuccess();
			} catch (Exception ex) {
				ec.onError(ex);
			}
		});
	}

	public void addServicioExtra(ServicioExtraPopupView view, ServicioExtra se) throws Exception {
		loadFields(view, se);
		servicioExtraDao.save(se);
	}

	public void editServicioExtra(ServicioExtraPopupView view, ServicioExtra se) throws Exception {
		loadFields(view, se);
		servicioExtraDao.update(se);
	}

	protected void loadFields(ServicioExtraPopupView view, ServicioExtra se) throws Exception {
		Servicio servicio = (Servicio) view.getCboServicio().getSelectedItem();
		if (servicio == null || servicio.getId() == null) {
			throw new Exception("Se requiere introducir el Servicio.");
		}
		
		TipoAlquiler tipoAlquiler = (TipoAlquiler) view.getCboTipoAlquiler().getSelectedItem();
		if (tipoAlquiler == null || tipoAlquiler.getId() == null) {
			throw new Exception("Se requiere introducir el Tipo de Alquiler.");
		}
		
        String cantidad = view.getTxtCantidad().getText();
        if (cantidad.isEmpty()) {
            throw new Exception("Se requiere ingresar el Status.");
        }
		
		se.setServicio(servicio);
		se.setTipoAlquiler(tipoAlquiler);
		se.setCant(Integer.parseInt(cantidad));
		}

	private void initComboBox(ServicioExtraPopupView view) { // Inicializar listas
		try {
			view.getTipoAlquilerComboBoxModel().addElement(new TipoAlquiler()); // Tipo alquiler vacío
			for (TipoAlquiler ta : tipoAlquilerDao.getAll()) {
				view.getTipoAlquilerComboBoxModel().addElement(ta);
			}
			view.getServicioComboBoxModel().addElement(new Servicio()); // Servicio vacío
			for (Servicio s : servDao.getAll()) {
				view.getServicioComboBoxModel().addElement(s);
			}
		} catch (Exception e) {
		}
	}
	
}
