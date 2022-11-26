package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.VigilanteDao;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.view.popup.VigilantePopupView;

public class VigilantePopupController {

    VigilanteDao vigilanteDao = new VigilanteDao();
    JFrame previousView;

    public void add(VigilantePopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnOK().addActionListener(evt -> {
            try {
                addVigilante(view, new Vigilante());
                view.dispose();
                view.showMessage("Vigilante agregado de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void edit(VigilantePopupView view, Vigilante vigilante, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Vigilante - " + vigilante.getCedula());
        
        view.getTxtCedula().setText(vigilante.getCedula());
        view.getTxtCedula().setEnabled(false);
        view.getTxtNombre().setText(vigilante.getNombre());
        view.getTxtApellido().setText(vigilante.getApellido());
        view.getSpnFecNac().setValue(
				Date.from(vigilante.getFecNac().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        view.getTxtCorreo().setText(vigilante.getCorreo());
        view.getTxtTelefono().setText(vigilante.getTelf());

        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editVigilante(view, vigilante);
                view.dispose();
                view.showMessage("Información del Vigilante actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void addVigilante(VigilantePopupView view, Vigilante v) throws Exception {
    	loadFields(view, v);
        vigilanteDao.save(v);
    }

    public void editVigilante(VigilantePopupView view, Vigilante v) throws Exception {
    	loadFields(view, v);
        vigilanteDao.update(v);
    }
    
    protected void loadFields(VigilantePopupView view, Vigilante v) throws Exception {
    	String cedula = view.getTxtCedula().getText();
        if (cedula.isEmpty()) {
            throw new Exception("Se requiere introducir el Número de Cédula del Vigilante.");
        }
        String nombre = view.getTxtNombre().getText().trim();
        if (nombre.isEmpty()) {
            throw new Exception("Se requiere introducir el nombre del Vigilante.");
        }
        String apellido = view.getTxtApellido().getText().trim();
        if (apellido.isEmpty()) {
            throw new Exception("Se requiere introducir el apellido del Vigilante.");
        }
        LocalDate fecNac = ((Date) view.getSpnFecNac().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (Period.between(fecNac, LocalDate.now()).getYears() <= 18) {
            throw new Exception("Se requiere introducir una fecha de nacimiento válida (mayor a 18 años).");
        }
        String correo = view.getTxtCorreo().getText().trim();
        String telefono = view.getTxtTelefono().getText().trim();
        
        v.setCedula(cedula);
        v.setNombre(nombre);
        v.setApellido(apellido);
        v.setFecNac(fecNac);
        v.setCorreo(correo);
        v.setTelf(telefono);
    }
}
