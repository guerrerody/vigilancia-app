package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.ClienteDao;
import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.view.popup.ServicioPopupView;

public class ServicioPopupController {
	ServicioDao servDao = new ServicioDao();
	ClienteDao clienteDao = new ClienteDao();
	JFrame previousView;
	
	public void add(ServicioPopupView view, SuccessCallback sc, ErrorCallback ec) {
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
                addServicio(view, new Servicio());
                view.dispose();
                view.showMessage("Servicio agregado de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
	
	public void edit(ServicioPopupView view, Servicio servicio, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Servicio");
        
        initComboBox(view);
        
        view.getCboCliente().setSelectedItem(servicio.getCliente());
        view.getSpnFechaIn().setValue(
				Date.from(servicio.getFechaIn().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        view.getSpnFechaFin().setValue(
				Date.from(servicio.getFechaFin().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        view.getTxtDescr().setText(servicio.getDescr());
        view.getTxtCosto().setText(servicio.getCosto().toString());
        view.getTxtStatus().setText(servicio.getStatus().toString());
        
        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editServicio(view, servicio);
                view.dispose();
                view.showMessage("Información de servicio actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void addServicio(ServicioPopupView view, Servicio s) throws Exception {
    	loadFields(view, s);
        servDao.save(s);
    }

    public void editServicio(ServicioPopupView view, Servicio s) throws Exception {
    	loadFields(view, s);
        servDao.update(s);
    }
    
    protected void loadFields(ServicioPopupView view, Servicio s) throws Exception {
    	Cliente cliente = (Cliente) view.getCboCliente().getSelectedItem();
    	if (cliente == null || cliente.getId() == null) {
			throw new Exception("Se requiere introducir el Cliente.");
		}
        LocalDate fechaIn = ((Date) view.getSpnFechaIn().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaFin = ((Date) view.getSpnFechaFin().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        String descr = view.getTxtDescr().getText();
        
        String costo = view.getTxtCosto().getText();
        if (costo.isEmpty()) {
            throw new Exception("Se requiere ingresar el costo.");
        }
        
        String status = view.getTxtStatus().getText();
        if (status.isEmpty()) {
            throw new Exception("Se requiere ingresar el Status.");
        }
        
        s.setCliente(cliente);
        s.setFechaIn(fechaIn);
        s.setFechaFin(fechaFin);
        s.setDescr(descr);
        s.setCosto(Double.parseDouble(costo));
        s.setStatus(Integer.parseInt(status));
    }
    
    private void initComboBox(ServicioPopupView view) { // Inicializar la lista de Clientes
        try {
        	view.getClienteComboBoxModel().addElement(new Cliente()); //cliente vacío
            for (Cliente c : clienteDao.getAll()) {
                view.getClienteComboBoxModel().addElement(c);
            }
        } catch (Exception e) {
        }
    }
}
