package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.NominaDao;
import edu.ucla.lab1.vigilancia.model.Nomina;
import edu.ucla.lab1.vigilancia.view.popup.NominaPopupView;

public class NominaPopupController {
	NominaDao nomDao = new NominaDao();
	JFrame previousView;
	
	public void edit(NominaPopupView view, Nomina nomina, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Nomina");
        
        view.getSpnFecha().setValue(Date.from(nomina.getFecha().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
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
	
	public void editNomina(NominaPopupView view, Nomina v) throws Exception {
    	loadFields(view, v);
        nomDao.update(v);
    }
	
	protected void loadFields(NominaPopupView view, Nomina n) throws Exception {
        
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
}
