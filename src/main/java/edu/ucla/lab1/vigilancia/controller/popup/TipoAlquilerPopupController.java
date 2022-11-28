package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.TipoAlquilerDao;
import edu.ucla.lab1.vigilancia.model.TipoAlquiler;
import edu.ucla.lab1.vigilancia.view.popup.TipoAlquilerPopupView;

public class TipoAlquilerPopupController {
	TipoAlquilerDao tipoClienteDao = new TipoAlquilerDao();
    JFrame previousView;

    public void add(TipoAlquilerPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().addActionListener(evt -> {
            try {
                addTipoAlquiler(view, new TipoAlquiler());
                view.dispose();
                view.showMessage("Tipo Alquiler agregado de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void edit(TipoAlquilerPopupView view, TipoAlquiler ta, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getLbTitle().setText("Editar Tipo Alquiler");
        
        view.getTxtNombre().setText(ta.getNombre());
        view.getTxtCostoUso().setText(ta.getCostoUso().toString());
        view.getTxtCostoMan().setText(ta.getCostoMant().toString());
        
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editTipoAlquier(view, ta);
                view.dispose();
                view.showMessage("Información del tipo de alquiler actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void addTipoAlquiler(TipoAlquilerPopupView view, TipoAlquiler ta) throws Exception {
    	loadFields(view, ta);
        tipoClienteDao.save(ta);
    }

    public void editTipoAlquier(TipoAlquilerPopupView view, TipoAlquiler ta) throws Exception {
    	loadFields(view, ta);
        tipoClienteDao.update(ta);
    }
    
    protected void loadFields(TipoAlquilerPopupView view, TipoAlquiler ta) throws Exception {
        String nombre = view.getTxtNombre().getText().trim();
        if (nombre.isEmpty()) {
            throw new Exception("Se requiere introducir el nombre del tipo de alquiler.");
        }
        Double costoUso, costoMan;
        try {
        	costoUso = Double.parseDouble(view.getTxtCostoUso().getText().trim());
        } catch(Exception e) {
        	throw new Exception("Se requiere introducir un costo de uso válido.");
        }
        try {
        	costoMan = Double.parseDouble(view.getTxtCostoMan().getText().trim());
        } catch(Exception e) {
        	throw new Exception("Se requiere introducir un costo de mantenimiento válido.");
        }
        
        ta.setNombre(nombre);
        ta.setCostoUso(costoUso);
        ta.setCostoMant(costoMan);
    }
}
