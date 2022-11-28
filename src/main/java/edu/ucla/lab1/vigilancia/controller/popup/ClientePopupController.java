package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.ClienteDao;
import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.view.popup.ClientePopupView;

public class ClientePopupController {
	  ClienteDao clienteDao = new ClienteDao();
	    JFrame previousView;

	    public void add(ClientePopupView view, SuccessCallback sc, ErrorCallback ec) {
	        if (previousView != null && previousView.isDisplayable()) {
	            previousView.requestFocus();
	            return;
	        }
	        previousView = view;
	        view.setVisible(true);
	        view.getBtnCancel().addActionListener(evt -> view.dispose());
	        view.getBtnOK().addActionListener(evt -> {
	            try {
	                addCliente(view, new Cliente());
	                view.dispose();
	                view.showMessage("Cliente agregado de forma exitosa");
	                sc.onSuccess();
	            } catch (Exception ex) {
	                ec.onError(ex);
	            }
	        });
	    }

	    public void edit(ClientePopupView view, Cliente cliente, SuccessCallback sc, ErrorCallback ec) {
	        if (previousView != null && previousView.isDisplayable()) {
	            previousView.requestFocus();
	            return;
	        }
	        previousView = view;
	        view.setVisible(true);
	        view.getBtnCancel().addActionListener(evt -> view.dispose());
	        view.getLbTitle().setText("Editar Cliente");
	       
	        view.getTxtNombre().setText(cliente.getNombre());
	        view.getTxtLocalidad().setText(cliente.getLocalidad());
	        view.getTxtDireccion().setText(cliente.getDireccion());
	        view.getTxtNombreContac().setText(cliente.getNombreContac());
	        view.getTxtTelefonoContac().setText(cliente.getTelfContac());

	        view.getBtnOK().setText("Actualizar");
	        view.getBtnOK().addActionListener(evt -> {
	            try {
	                editCliente(view, cliente);
	                view.dispose();
	                view.showMessage("Información del cliente actualizada con éxito");
	                sc.onSuccess();
	            } catch (Exception ex) {
	                ec.onError(ex);
	            }
	        });
	    }

	    public void addCliente(ClientePopupView view, Cliente c) throws Exception {
	    	loadFields(view, c);
	        clienteDao.save(c);
	    }

	    public void editCliente(ClientePopupView view, Cliente c) throws Exception {
	    	loadFields(view, c);
	        clienteDao.update(c);
	    }
	    
	    protected void loadFields(ClientePopupView view, Cliente c) throws Exception {
	        String nombre = view.getTxtNombre().getText().trim();
	        if (nombre.isEmpty()) {
	            throw new Exception("Se requiere introducir el nombre del Cliente.");
	        }
	        String localidad = view.getTxtLocalidad().getText().trim();
	        String dirrecion = view.getTxtLocalidad().getText().trim();
	        String nombreContac = view.getTxtNombreContac().getText().trim();
	  	        if (nombreContac.isEmpty()) {
	  	            throw new Exception("Se requiere introducir el nombre del nombre de contacto.");
	        }
	        String telefonoContac = view.getTxtTelefonoContac().getText().trim();
	       
	        c.setNombre(nombre);
	        c.setLocalidad(localidad);
	        c.setDireccion(dirrecion);
	        c.setNombreContac(nombreContac);
	        c.setTelfContac(telefonoContac);
	    }
	

}
