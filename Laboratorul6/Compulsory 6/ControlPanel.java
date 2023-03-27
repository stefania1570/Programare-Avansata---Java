package graphicsLAB6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    /**
     * Constructor for building the control panel
     * @param frame MainFrame
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Building the control panel
     */
    private void init() {
        setLayout(new GridLayout(1, 3));
        add(loadButton);
        add(saveButton);
        add(exitButton);
        exitButton.addActionListener(this::exitGame);
        //TODO action listener pt restul butoanelor
    }
    
     /**
     * Action handler for the exit button
     * @param e ActionEvent
     */
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
