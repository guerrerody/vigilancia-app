package edu.ucla.lab1.vigilancia.controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import edu.ucla.lab1.vigilancia.view.MenuItem;


public class SideBarController {
    JPanel panelSideBar;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private MenuItem activeMenuItem = null;

    interface MenuBarEvent {

        public abstract void onSelectMenuItem(MenuItem item);
    }

    public SideBarController() {
    }

    public SideBarController(JPanel panelSideBar) {
        this.panelSideBar = panelSideBar;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public JPanel getPanelSideBar() {
        return panelSideBar;
    }

    public void setPanelSideBar(JPanel panelSideBar) {
        this.panelSideBar = panelSideBar;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenuItem item = menu[i];
            menuItems.add(item);
            item.setActive(false);
            panelSideBar.add(item);
            ArrayList<MenuItem> subMenus = item.getSubMenu();
            for (MenuItem subMenu : subMenus) {
                addMenu(subMenu);
                subMenu.setVisible(false);
            }
        }
    }

    public void addMenuEvent(MenuBarEvent mbe) { 
        for (MenuItem menuItem : menuItems) {
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!menuItem.equals(activeMenuItem)) {
                        mbe.onSelectMenuItem(menuItem);
                    }
                    setMenu(menuItem);
                }
            });
        }
    }

    public void renderMenu() {
        for (MenuItem menuItem : menuItems) {
            MenuItem parent = menuItem.getParentMenu();
            if (parent == null) { // Si es el menú principal superior
                menuItem.setVisible(true);
            } else if (parent.isActive()) {
                menuItem.setVisible(true);
            } else if (menuItem.isActive()) {
                menuItem.setVisible(true);
            } else {
                menuItem.setVisible(false);
            }
        }
    }

    private void closePreviosMenu(MenuItem previousItem) {
        MenuItem parrent = previousItem.getParentMenu();
        previousItem.setActive(false);
        while (parrent != null) {
            parrent.setActive(false);
            parrent = parrent.getParentMenu();
        }
    }

    public void setMenu(MenuItem item) {
        boolean isActive = item.isActive();
        if (activeMenuItem != null) {
            closePreviosMenu(activeMenuItem);
        }
        MenuItem parent = item.getParentMenu();
        while (parent != null) {
            parent.setActive(true);
            parent = parent.getParentMenu();
        }
        item.setActive(!isActive);
        activeMenuItem = item;
        renderMenu();
    }
}
