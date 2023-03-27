package graphicsLAB6;

import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DrawingPanel drawingPanel;
    ConfigPanel configPanel;

    /**
     * Constructor for initializing the main frame once it is created
     */
    public MainFrame() {
        super("Joc Laboratorul 6");
        init();
    }

    /**
     * Method used for initialization
     */
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); //il pune in mijlocul ecranului
    }

}
