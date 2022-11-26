package edu.ucla.lab1.vigilancia.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ucla.lab1.vigilancia.utils.RandomColor;

public class HomeView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = LoggerFactory.getLogger(HomeView.class);

	private boolean isShowLed = false;
    
    private JLabel jLabel1;
    private JLabel labLogo;

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
            	logger.error("Error: ", e);
            }
        }).start();
    }
    
    public HomeView() {
        initComponents();
    }
    
    private void initComponents() {
        labLogo = new JLabel();
        jLabel1 = new JLabel();

        setBackground(new Color(153, 255, 255));
        setMaximumSize(new Dimension(MainView.UI.WIDTH_CONTENT, MainView.UI.HEIGHT));
        setMinimumSize(new Dimension(MainView.UI.WIDTH_CONTENT, MainView.UI.HEIGHT));
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(null);

        labLogo.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
        labLogo.setPreferredSize(new Dimension(400, 400));
        add(labLogo);
        labLogo.setBounds(770, 10, 200, 190);

        jLabel1.setFont(new Font("Segoe UI", 0, 24));
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/images/home-background.png")));
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new Dimension(MainView.UI.WIDTH_CONTENT, MainView.UI.HEIGHT));
        add(jLabel1);
        jLabel1.setBounds(0, 0, MainView.UI.WIDTH_CONTENT, MainView.UI.HEIGHT);
    }
    
    private void formMousePressed(MouseEvent evt) {
        isShowLed = !isShowLed;
        makeLed();
    }
    
    private void makeLed() {
        if (isShowLed) {
	        setBackground(RandomColor.getColor());
	        jLabel1.setForeground(RandomColor.getContrastColor(getBackground()));
	        setTimeout(this::makeLed, 200);
        }
    }
}
