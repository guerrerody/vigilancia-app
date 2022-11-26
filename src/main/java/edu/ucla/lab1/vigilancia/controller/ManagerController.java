package edu.ucla.lab1.vigilancia.controller;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import edu.ucla.lab1.vigilancia.view.ManagerPaneView;

public abstract class ManagerController {
    @SuppressWarnings("rawtypes")
	protected ManagerPaneView view = null;

    public ManagerController() {
    }

    public ManagerPaneView<?> getView() {
        return view;
    }

    public void setView(ManagerPaneView<?> view) {
        if (this.view != view) {
            this.view = view;
            addEvent();
        } else {
            this.view = view;
        }
    }

    public abstract void actionAdd();

    public abstract void actionDelete();

    public abstract void actionEdit();

    public abstract void updateData();
    
    public abstract void actionSearch();

    private void addEvent() {
        view.getTxtSearch().addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (view.getTxtSearch().getText().equals(ManagerPaneView.SEARCH_TEXT)) {
                    view.getTxtSearch().setText("");
                    view.getTxtSearch().setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (view.getTxtSearch().getText().equals("") || view.getTxtSearch().getText().equals(ManagerPaneView.SEARCH_TEXT)) {
                    view.getTxtSearch().setText(ManagerPaneView.SEARCH_TEXT);
                    view.getTxtSearch().setForeground(new Color(153, 153, 153));
                }
            }
        });
        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionSearch();
                }
            }
        });

        view.getBtnAdd().addActionListener(evt -> actionAdd());
        view.getBtnEdit().addActionListener(evt -> actionEdit());
        view.getBtnDelete().addActionListener(evt -> actionDelete());
        view.getBtnSync().addActionListener(evt -> {
        	view.getTxtSearch().setText("");
        	updateData();
        });
    }
}
