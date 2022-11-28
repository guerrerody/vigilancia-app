package edu.ucla.lab1.vigilancia.controller.popup;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.ClienteDao;
import edu.ucla.lab1.vigilancia.dao.TipoClienteDao;
import edu.ucla.lab1.vigilancia.model.Cliente;
import edu.ucla.lab1.vigilancia.model.TipoCliente;
import edu.ucla.lab1.vigilancia.view.popup.ClientePopupView;

public class ClientePopupController {
	ClienteDao clienteDao = new ClienteDao();
	TipoClienteDao tipoClienteDao = new TipoClienteDao();
	JFrame previousView;

	public void add(ClientePopupView view, SuccessCallback sc, ErrorCallback ec) {
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
		view.getLbTitle().setText("Editar Cliente");
		
		initComboBox(view);
		
		view.getTxtNombre().setText(cliente.getNombre());
		view.getTxtLocalidad().setText(cliente.getLocalidad());
		view.getTxtDireccion().setText(cliente.getDireccion());
		view.getTxtNombreContac().setText(cliente.getNombreContac());
		view.getTxtTelfContac().setText(cliente.getTelfContac());
		view.getCboTipoCliente().setSelectedItem(cliente.getTipoCliente());

		view.getBtnCancel().addActionListener(evt -> view.dispose());
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
		TipoCliente tipoCliente = (TipoCliente) view.getCboTipoCliente().getSelectedItem();
		if (tipoCliente == null || tipoCliente.getId() == null) {
			throw new Exception("Se requiere introducir el Tipo de Cliente.");
		}
		String nombre = view.getTxtNombre().getText().trim();
		if (nombre.isEmpty()) {
			throw new Exception("Se requiere introducir el nombre del Cliente.");
		}
		String localidad = view.getTxtLocalidad().getText().trim();
		String direcion = view.getTxtDireccion().getText().trim();
		String nombreContac = view.getTxtNombreContac().getText().trim();
		if (nombreContac.isEmpty()) {
			throw new Exception("Se requiere introducir el nombre de contacto del Cliente.");
		}
		String telefonoContac = view.getTxtTelfContac().getText().trim();
		if (telefonoContac.isEmpty()) {
			throw new Exception("Se requiere introducir el teléfono de contacto del Cliente.");
		}

		c.setTipoCliente(tipoCliente);
		c.setNombre(nombre);
		c.setLocalidad(localidad);
		c.setDireccion(direcion);
		c.setNombreContac(nombreContac);
		c.setTelfContac(telefonoContac);
	}

    private void initComboBox(ClientePopupView view) { // Inicializar la lista de Tipos de Cliente
        try {
        	view.getTipoClienteComboBoxModel().addElement(new TipoCliente()); // Tipo cliente vacío
            for (TipoCliente tc : tipoClienteDao.getAll()) {
                view.getTipoClienteComboBoxModel().addElement(tc);
            }
        } catch (Exception e) {
        }
    }
}
