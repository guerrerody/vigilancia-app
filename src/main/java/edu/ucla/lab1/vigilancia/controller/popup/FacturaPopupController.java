package edu.ucla.lab1.vigilancia.controller.popup;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;

import edu.ucla.lab1.vigilancia.dao.FacturaDao;
import edu.ucla.lab1.vigilancia.dao.ServicioDao;
import edu.ucla.lab1.vigilancia.model.Factura;
import edu.ucla.lab1.vigilancia.model.Servicio;
import edu.ucla.lab1.vigilancia.view.popup.FacturaPopupView;

public class FacturaPopupController {
	FacturaDao facDao = new FacturaDao();
	ServicioDao servDao = new ServicioDao();
	JFrame previousView;
	
	public void add(FacturaPopupView view, SuccessCallback sc, ErrorCallback ec) {
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
                addFactura(view, new Factura());
                view.dispose();
                view.showMessage("Factura agregada de forma exitosa");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }
	
	public void edit(FacturaPopupView view, Factura factura, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(evt -> view.dispose());
        view.getLbTitle().setText("Editar Factura");
        
        initComboBox(view);
        
        view.getCboServicio().setSelectedItem(factura.getServicio());
		view.getCboServicio().setEnabled(false);
        view.getSpnFechaPago().setValue(
				Date.from(factura.getFechaPago().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        view.getTxtDesc().setText(factura.getDesc());
        view.getTxtIva().setText(factura.getIva().toString());
        view.getTxtStatus().setText(factura.getStatus().toString());
        view.getTxtSubtotal().setText(factura.getSubtotal().toString());
        view.getTxtMontoTotal().setText(factura.getMontoTotal().toString());
        

        view.getBtnOK().setText("Actualizar");
        view.getBtnOK().addActionListener(evt -> {
            try {
                editFactura(view, factura);
                view.dispose();
                view.showMessage("Información de factura actualizada con éxito");
                sc.onSuccess();
            } catch (Exception ex) {
                ec.onError(ex);
            }
        });
    }

    public void addFactura(FacturaPopupView view, Factura f) throws Exception {
    	loadFieldsAdd(view, f);
        facDao.save(f);
    }

    public void editFactura(FacturaPopupView view, Factura f) throws Exception {
    	loadFields(view, f);
        facDao.update(f);
    }
    
    protected void loadFields(FacturaPopupView view, Factura f) throws Exception {
    	Servicio servicio = (Servicio) view.getCboServicio().getSelectedItem();
		if (servicio == null || servicio.getId() == null) {
			throw new Exception("Se requiere introducir el Servicio.");
		}

        LocalDate fechaPago = ((Date) view.getSpnFechaPago().getValue())
        		.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        String iva = view.getTxtIva().getText();
        if (iva.isEmpty()) {
            throw new Exception("Se requiere ingresar el IVA.");
        }
        
        String status = view.getTxtStatus().getText();
        if (status.isEmpty()) {
            throw new Exception("Se requiere ingresar el Status.");
        }
        
        String subtotal = view.getTxtSubtotal().getText();
        if (subtotal.isEmpty()) {
            throw new Exception("Se requiere ingresar el subtotal.");
        }
        
        String montoTotal = view.getTxtMontoTotal().getText();
        if (montoTotal.isEmpty()) {
            throw new Exception("Se requiere ingresar el monto total.");
        }
        
        String desc = view.getTxtDesc().getText().trim();
        
        f.setServicio(servicio);
        f.setFechaPago(fechaPago);
        f.setDesc(desc);
        f.setIva(Double.parseDouble(iva));
        f.setStatus(Integer.parseInt(status));
        f.setSubtotal(Double.parseDouble(subtotal));
        f.setMontoTotal(Double.parseDouble(montoTotal));
    }
    
    protected void loadFieldsAdd(FacturaPopupView view, Factura f) throws Exception {
    	Servicio servicio = (Servicio) view.getCboServicio().getSelectedItem();
		if (servicio == null || servicio.getId() == null) {
			throw new Exception("Se requiere introducir el Servicio.");
		}
		Double iva;
		try {
        	iva = Double.parseDouble(view.getTxtIva().getText().trim());
        } catch(Exception e) {
        	throw new Exception("Se requiere ingresar el IVA.");
        }

        
        int status = 1;

        String desc = view.getTxtDesc().getText().trim();
        
        f.setServicio(servicio);
        f.setDesc(desc);
        f.setIva(iva);
        f.setStatus(status);
		f.setFechaPago(LocalDate.now());
		f.calcMontos();
		f.calcMontoTotal();
    }
    
    private void initComboBox(FacturaPopupView view) { // Inicializar lista
		try {
			view.getServicioComboBoxModel().addElement(new Servicio()); // Servicio vacío
			for (Servicio s : servDao.getAll()) {
				view.getServicioComboBoxModel().addElement(s);
			}
		} catch (Exception e) {
		}
	}
}
