package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.view.popup.ServicioExtraPopupView;
import edu.ucla.lab1.vigilancia.view.popup.ServicioPopupView;
import edu.ucla.lab1.vigilancia.view.popup.TurnoPopupView;

import edu.ucla.lab1.vigilancia.dao.TurnoDao;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.dao.ServicioExtraDao;
import edu.ucla.lab1.vigilancia.dao.TipoAlquilerDao;
import edu.ucla.lab1.vigilancia.dao.VigilanteDao;

import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.ServicioExtra;
import edu.ucla.lab1.vigilancia.model.Vigilante;

public class TurnoPopupController {
	ServicioDao servicioDao = new ServicioDao();
	VigilanteDao vigilanteDao = new VigilanteDao();
	TurnoDao turnoDao = new TurnoDao();
	JFrame previousView;

	public void add(TurnoPopupView turnoPopupView, SuccessCallback sc, ErrorCallback ec2) {
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
				Turno t = addTurno(view, new Turno());
				view.dispose();
				view.showMessage("Turno agregado de forma exitosa");
				sc.onSuccess();
			} catch (Exception ex) {
				ec.onError(ex);
			}
		});
	}
	
	public void edit(TurnoPopupView view, Turno turno, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Turno");
        
        initComboBox(view);
        
        view.getCboServicio().setSelectedItem(servicio.getServicio());
        view.getCboVigilante().setSelectedItem(vigilante.getServicio());
        
        view.getSpnFechaIn().setValue(
				Date.from(turno.getFec_in().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        
        view.getSpnHorIn().setValue(
				Date.from(turno.getHor_in().atStartOfDay.atZone(ZoneId.systemDefault()).toInstant())); //ARREGLAR PARA OBT LA HORA
        
        view.getSpnFechaFin().setValue(
				Date.from(turno.getFec_fin().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        
        view.getSpnHorFin().setValue(
				Date.from(turno.getHor_fin().atStartOfDay.atZone(ZoneId.systemDefault()).toInstant())); //ARREGLAR PARA OBT LA HORA

        view.getrdbtnMarcarFaltado().setValue(); ///TERMINAR ESTA OPCION, RADIOBUTTON PARA MARCAR LA FALTA
       
        
        view.getTxtJust().setText(turno.getJust());
        
        view.getBtnOK().setText("Actualizar");
        
        view.getBtnOK().addActionListener(evt -> {
            try {
                editTurno(view, turno);
                view.dispose();
                view.showMessage("Información del turno actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
	
    public void addTurno(TurnoPopupView view, Turno t) throws Exception {
    	loadFields(view, t);
        TurnoDao.save(t);
        
        return t;
    }

    public void editTurno(TurnoPopupView view, Turno t) throws Exception {
    	loadFields(view, t);
        TurnoDao.update(t);
    }
    
    protected void loadFields(TurnoPopupView view, Turno t) throws Exception {
    	Vigilante vigilante = (Vigilante) view.getCboVigilante().getSelectedItem();
    	if (vigilante == null || vigilante.getId() == null) {
			throw new Exception("Se requiere introducir el Vigilante.");
		}
    	
    	Servicio servicio = (Servicio) view.getCboServicio().getSelectedItem();
		if (servicio == null || servicio.getId() == null) {
			throw new Exception("Se requiere introducir el Servicio.");
		}
		
		//TERMINAR
    }
    
    private void initComboBox(TurnoPopupView view) {
    	//FALTA
    }
}
	