package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.model.Turno;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.model.DetalleServicio;

import edu.ucla.lab1.vigilancia.dao.VigilanteDao;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.dao.TurnoDao;
import edu.ucla.lab1.vigilancia.dao.DetalleServicio;

import edu.ucla.lab1.vigilancia.view.popup.VigilantePopupView;
import edu.ucla.lab1.vigilancia.view.popup.ServicioPopupView;
import edu.ucla.lab1.vigilancia.view.popup.TurnoPopupView;

public class DetalleServicioPopupController {
	DetalleServicioDao detaDao = new DetalleServicioDao();
	VigilanteDao vigilanteDao = new VigilanteDao();
	JFrame previousView;
	
	public void add(DetalleServicioPopupView view, SuccessCallback sc, ErrorCallback ec) {
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
                DetalleServicio ds = addDetalleServicio(view, new DetalleServicio());
                
                view.dispose();
                view.showMessage("Asignacion agregada de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    
   public void edit(DetalleServicioPopupView view, DetalleServicio detalleservicio, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
             previousView.requestFocus();
             return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Asignacion");
            
        initComboBox(view);
            
		view.getCboServicio().setSelectedItem(detalleservicio.getServicio());
		view.getCboServicio().setEnabled(false);
		view.getCboTurno().setSelectedItem(detalleservicio.getTurno());
		view.getCboTurno().setEnabled(false);
		view.getCboVigilante().setSelectedItem(detalleservicio.getVigilante());
		view.getCboVigilante().setEnabled(false);
            
        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
                try {
                    editDetalleServicio(view, horario);
                    view.dispose();
                    view.showMessage("Información actualizada con éxito");
                    sc.onSuccess();
                } catch (Exception ex) {
                    ec.onError(ex);
                }
            });
        } 
       
    public void addDetalleServicio(DetalleServicioPopupView view, DetalleServicio ds) throws Exception {
        loadFields(view, ds);
        detaDao.save(ds);
            
        return ds;
        }

    public void editHorario(DetalleServicioPopupView view, DetalleServicio ds) throws Exception {
        loadFields(view, ds);
        detaDao.update(ds);
    }
    
    protected void loadFields(DetalleServicioPopupView view, DetalleServicio ds) throws Exception {
    	Vigilante vigilante = (Vigilante) view.getCboVigilante().getSelectedItem();
    	if (vigilante == null || vigilante.getId() == null) {
			throw new Exception("Se requiere introducir el Vigilante.");
		}
    	Servicio servicio = (Servicio) view.getCboServicio().getSelectedItem();
		if (servicio == null || servicio.getId() == null) {
			throw new Exception("Se requiere introducir el Servicio.");
		}
		Turno turno = (Turno) view.getCboTurno().getSelectedItem();
		if (turno == null || turno.getId() == null) {
			throw new Exception("Se requiere introducir el Turno.");
		}
		ds.setServicio(servicio);
		ds.setVigilante(vigilante);
		ds.setTurno(turno);

    }
        
    private void initComboBox(DetalleServicioPopupView view) { 
        try {
        	view.getVigilanteComboBoxModel().addElement(new Vigilante()); 
            for (Vigilante vi : VigilanteDao.getAll()) {
                view.getVigilanteComboBoxModel().addElement(vi);
            }
            view.getServicioComboBoxModel().addElement(new Servicio()); 
			for (Servicio s : ServicioDao.getAll()) {
				view.getServicioComboBoxModel().addElement(s);
			}
			view.getTurnoComboBoxModel().addElement(new Turno()); 
			for (Turno t : TurnoDao.getAll()) {
				view.getServicioComboBoxModel().addElement(s);
			}
        } catch (Exception e) {
        }
    }    
	
}