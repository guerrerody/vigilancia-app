package edu.ucla.lab1.vigilancia.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.ucla.lab1.vigilancia.dao.VigilanteDao;

public class VigilanteController implements ActionListener {
	
	private VigilanteDao vigDao;
	
    public VigilanteController() {
    	vigDao = new VigilanteDao();
    }


	@Override
	public void actionPerformed(ActionEvent e) {

		
	}

}
