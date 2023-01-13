package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.NominaDao;
import edu.ucla.lab1.vigilancia.dao.VigilanteDao;
import edu.ucla.lab1.vigilancia.model.Vigilante;
import edu.ucla.lab1.vigilancia.model.Nomina;
import edu.ucla.lab1.vigilancia.view.popup.NominaPopupView;
import edu.ucla.lab1.vigilancia.view.popup.NominaVigPopupView;

public class NominaPopupController {
	NominaDao nomDao = new NominaDao();
	VigilanteDao vigDao = new VigilanteDao();
	JFrame previousView;
	
	public void add(NominaVigPopupView view, SuccessCallback sc, ErrorCallback ec) {
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
                Nomina n = addNomina(view, new Nomina());
                
                view.dispose();
                view.showMessage("Nomina agregada de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
        
    }
	
	public void AddMenu(NominaPopupView view, NominaVigPopupView viewV, SuccessCallback sc, ErrorCallback ec) {
		if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getBtnNomVig().addActionListener(evt -> {
        	view.dispose();
        	add(viewV,sc,ec);
        });
        view.getBtnNomMen().addActionListener(evt -> {
        	nomDao.generarNominaMensual();
        	
        	view.dispose();
            view.showMessage("Nomina mensual creada");
            sc.onSuccess();
        });
        view.getBtnNomSem().addActionListener(evt -> {
        	//ArrayList<Nomina> n = nomDao.obtenerNominaSemanal();
        });
	};
	
	public void edit(NominaVigPopupView view, Nomina nomina, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Nomina");
        
        view.getCboVigilante().setSelectedItem(nomina.getVigilante().toString());
		view.getCboVigilante().setEnabled(false);
        view.getSpnFecha().setValue(Date.from(nomina.getFecha().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        view.getSpnFecha().setEnabled(false);
        view.getTxtDesc().setText(nomina.getDesc());
        view.getTxtDiasTrab().setText(nomina.getDiasTrab().toString());
        view.getTxtHorasExtras().setText(nomina.getHorasExtra().toString());
        view.getTxtDiasFalta().setText(nomina.getDiasFalta().toString());
        view.getTxtSueldoBase().setText(nomina.getSueldoBase().toString());
        view.getTxtPagoExtra().setText(nomina.getPagoExtra().toString());
        view.getTxtDeduccion().setText(nomina.getDeduccion().toString());

        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editNomina(view, nomina);
                view.dispose();
                view.showMessage("Información de nomina de vigilante actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
	
	public Nomina addNomina(NominaVigPopupView view, Nomina n) throws Exception {
    	loadAddFields(view, n);
        nomDao.save(n);
        
        return n;
    }
	
	public void editNomina(NominaVigPopupView view, Nomina v) throws Exception {
    	loadFields(view, v);
        nomDao.update(v);
    }
	
	protected void loadFields(NominaVigPopupView view, Nomina n) throws Exception {
        
    	String desc = view.getTxtDesc().getText();
        if (desc.isEmpty()) {
            throw new Exception("Se requiere introducir una Descripcion.");
        }
        
        Integer diasTrab = 0;
        try {
        	diasTrab = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        
        Integer horasExtra = 0;
        try {
        	horasExtra = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }

        Integer diasFalta = 0;
        try {
        	diasFalta = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        
        Double sueldoBase = 0.0;
        try {
        	sueldoBase = Double.parseDouble( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        Double pagoExtra = 0.0;
        try {
        	pagoExtra = Double.parseDouble( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        Double deduccion = 0.0;
        try {
        	deduccion = Double.parseDouble( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        LocalDate fecha = ((Date) view.getSpnFecha().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        		
        n.setFecha(fecha);
        n.setDesc(desc);
        n.setDiasTrab(diasTrab);
        n.setHorasExtra(horasExtra);
        n.setDiasFalta(diasFalta);
        n.setSueldoBase(sueldoBase);
        n.setPagoExtra(pagoExtra);
        n.setDeduccion(deduccion);
    }
	
	protected void loadAddFields(NominaVigPopupView view, Nomina n) throws Exception {
        
		Vigilante vigilante = (Vigilante) view.getCboVigilante().getSelectedItem();
    	if (vigilante == null || vigilante.getId() == null) {
			throw new Exception("Se requiere introducir el Vigilante.");
		}
		
    	String desc = view.getTxtDesc().getText();
        if (desc.isEmpty()) {
            throw new Exception("Se requiere introducir una Descripcion.");
        }
        
        Integer diasTrab = 0;
        try {
        	diasTrab = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        
        Integer horasExtra = 0;
        try {
        	horasExtra = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }

        Integer diasFalta = 0;
        try {
        	diasFalta = Integer.parseInt( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        
        Double sueldoBase = 0.0;
        try {
        	sueldoBase = Double.parseDouble( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        Double pagoExtra = 0.0;
        try {
        	pagoExtra = Double.parseDouble( view.getTxtDiasTrab().getText() );
        } catch ( NumberFormatException exc ) {
        	throw new Exception("Se requiere introducir una cantidad válida.");
        }
        LocalDate fecha = ((Date) view.getSpnFecha().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		n.setVigilante(vigilante);
        n.setFecha(fecha);
        n.setDesc(desc);
        n.setDiasTrab(diasTrab);
        n.setHorasExtra(horasExtra);
        n.setDiasFalta(diasFalta);
        n.setSueldoBase(sueldoBase);
        n.setPagoExtra(pagoExtra);
        n.calcDeduccion();
    }
	
    private void initComboBox(NominaVigPopupView view) {
        try {
        	view.getVigilanteComboBoxModel().addElement(new Vigilante()); 
            for (Vigilante v : vigDao.getAll()) {
                view.getVigilanteComboBoxModel().addElement(v);
            }
        } catch (Exception e) {
        }
    }
}
