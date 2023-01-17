package edu.ucla.lab1.vigilancia.controller.popup;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.view.popup.TurnoPopupView;

import edu.ucla.lab1.vigilancia.dao.TurnoDao;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.dao.VigilanteDao;

import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.Vigilante;

public class TurnoPopupController {
	ServicioDao servicioDao = new ServicioDao();
	VigilanteDao vigilanteDao = new VigilanteDao();
	TurnoDao turnoDao = new TurnoDao();
	JFrame previousView;

	public void add(TurnoPopupView view, SuccessCallback sc, ErrorCallback ec) {
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
				addTurno(view, new Turno());
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
        
        view.getCboServicio().setSelectedItem(turno.getServicio());
        view.getCboVigilante().setSelectedItem(turno.getVigilante());
        
        view.getSpnFechaIn().setValue(
				Date.from(turno.getFec_in().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        
        view.getSpnHorIn().setValue(Time.valueOf(turno.getHor_in()));
        
        view.getSpnFechaFin().setValue(
				Date.from(turno.getFec_fin().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        view.getSpnHorFin().setValue(Time.valueOf(turno.getHor_fin()));
        
        view.getRdbtnMarcarFaltado().setSelected(turno.getFalta());
       
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
        turnoDao.save(t);
    }

    public void editTurno(TurnoPopupView view, Turno t) throws Exception {
    	loadFields(view, t);
        turnoDao.update(t);
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
		
		LocalDate fec_In = ((Date) view.getSpnFechaIn().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		LocalDate fec_Fin = ((Date) view.getSpnFechaFin().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		LocalTime hr_In = ((Date) view.getSpnHorIn().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		
		LocalTime hr_Fin = ((Date) view.getSpnHorFin().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		
		String just = view.getTxtJust().getText();
		
		boolean falta = view.getRdbtnMarcarFaltado().isSelected();
		
		t.setFec_in(fec_In);
		t.setHor_in(hr_In);
		t.setFec_fin(fec_Fin);
		t.setHor_fin(hr_Fin);
		t.setJust(just);
		t.setFalta(falta);
		t.setVigilante(vigilante);
		t.setServicio(servicio);

    }
    
    private void initComboBox(TurnoPopupView view) {
    	try {
			view.getVigilanteComboBoxModel().addElement(new Vigilante());
			for (Vigilante v : vigilanteDao.getAll()) {
				view.getVigilanteComboBoxModel().addElement(v);
			}
			view.getServicioComboBoxModel().addElement(new Servicio());
			for (Servicio s : servicioDao.getAll()) {
				view.getServicioComboBoxModel().addElement(s);
			}
		} catch (Exception e) {
		}
    }
}
	