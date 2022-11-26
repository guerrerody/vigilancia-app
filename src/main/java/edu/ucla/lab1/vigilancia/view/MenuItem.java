package edu.ucla.lab1.vigilancia.view;

import java.util.ArrayList;
import java.util.Objects;

import java.awt.*;
import javax.swing.*;

import edu.ucla.lab1.vigilancia.utils.IconManager;
import edu.ucla.lab1.vigilancia.utils.SidebarColor;

public class MenuItem extends JPanel {
	private static final long serialVersionUID = 1L;

    private ArrayList<MenuItem> subMenu = new ArrayList<>();
    private MenuItem parentMenu = null;
    private String id;
    private int level;
    public boolean active;
    IconManager im = new IconManager();
    
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JLabel lbIcon;
    private JLabel lbMenuName;
    private JLabel lbOpen;

    public MenuItem(String id, Icon icon, String menuName, MenuItem... subMenu) {
        initComponents();
        this.id = id;
        lbIcon.setIcon(icon);
        lbMenuName.setText(menuName);
    }
    
    public ArrayList<MenuItem> getSubMenu() {
        return subMenu;
    }

    public void addSubMenu(MenuItem item) {
        item.setParentMenu(this);
        item.setLevel(this.level + 1);
        this.subMenu.add(item);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuItem getParentMenu() {
        return parentMenu;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setParentMenu(MenuItem parentMenu) {
        this.parentMenu = parentMenu;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            setBackground(SidebarColor.getActiveColor(level));
            if (hasSubMenu()) {
                lbOpen.setIcon(im.getIcon("opened_menu_25px.png"));
            }
        } else {
            setBackground(SidebarColor.getInactiveColor(level));
            if (hasSubMenu()) {
                lbOpen.setIcon(im.getIcon("closed_menu_25px.png"));
            }
        }
        if (!hasSubMenu()) {
            lbOpen.setVisible(false);
            this.updateUI();
        }
    }

    public boolean hasSubMenu() {
        return !subMenu.isEmpty();
    }

    public boolean equals(MenuItem obj) {
        return this == obj;
    }

    public boolean hasChild(MenuItem obj) {
        if (obj == null) {
            return false;
        }
        if (obj.equals(this)) {
            return true;
        }
        return hasChild(obj.getParentMenu());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id + ", active=" + active + '}';
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        lbIcon = new JLabel();
        jPanel3 = new JPanel();
        lbOpen = new JLabel();
        jPanel2 = new JPanel();
        lbMenuName = new JLabel();

        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(MainView.UI.WIDTH_MENU, 45));
        setLayout(new BorderLayout());

        jPanel1.setMaximumSize(new Dimension(45, 45));
        jPanel1.setMinimumSize(new Dimension(45, 45));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new Dimension(45, 45));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        add(jPanel1, BorderLayout.WEST);

        jPanel3.setMaximumSize(new Dimension(45, 45));
        jPanel3.setMinimumSize(new Dimension(45, 45));
        jPanel3.setOpaque(false);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        add(jPanel3, BorderLayout.EAST);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new Dimension(155, 45));

        lbMenuName.setFont(new Font("Segoe UI", 0, 14));
        lbMenuName.setText("Menu Name");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbMenuName)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbMenuName)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        add(jPanel2, BorderLayout.CENTER);
    }
}
