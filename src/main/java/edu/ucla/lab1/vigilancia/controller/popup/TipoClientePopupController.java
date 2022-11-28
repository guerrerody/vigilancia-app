package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.TipoClienteDao;
import edu.ucla.lab1.vigilancia.model.TipoCliente;
import edu.ucla.lab1.vigilancia.view.popup.TipoClientePopupView;

public class TipoClientePopupController {
	TipoClienteDao tipoClienteDao = new TipoClienteDao();
    JFrame previousView;

    public void add(TipoClientePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().addActionListener(evt -> {
            try {
                addTipoCliente(view, new TipoCliente());
                view.dispose();
                view.showMessage("Tipo Cliente agregado de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void edit(TipoClientePopupView view, TipoCliente tc, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getLbTitle().setText("Editar Tipo Cliente");
        
        view.getTxtNombre().setText(tc.getNombre());
        
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editTipoCliente(view, tc);
                view.dispose();
                view.showMessage("Información del tipo de cliente actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void addTipoCliente(TipoClientePopupView view, TipoCliente tc) throws Exception {
    	loadFields(view, tc);
        tipoClienteDao.save(tc);
    }

    public void editTipoCliente(TipoClientePopupView view, TipoCliente tc) throws Exception {
    	loadFields(view, tc);
        tipoClienteDao.update(tc);
    }
    
    protected void loadFields(TipoClientePopupView view, TipoCliente tc) throws Exception {
        String nombre = view.getTxtNombre().getText().trim();
        if (nombre.isEmpty()) {
            throw new Exception("Se requiere introducir el nombre del Tipo de cliente.");
        }
        tc.setNombre(nombre);
    }

}
