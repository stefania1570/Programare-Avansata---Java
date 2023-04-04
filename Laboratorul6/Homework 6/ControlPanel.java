package graphicsLAB6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    /**
     * Constructor for building the control panel
     *
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
        saveButton.addActionListener(e -> {
            try {
                save(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loadButton.addActionListener(e -> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Action handler for the exit button
     *
     * @param e ActionEvent
     */
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    /**
     * Action handler for the save button
     *
     * @param e ActionEvent
     */
    public void save(ActionEvent e) throws IOException {
        ImageIO.write(frame.drawingPanel.image, "PNG", new File("lab6.png"));
    }

    /**
     * Action handler for the load button
     */
    public void load() throws IOException {
        frame.drawingPanel.image = ImageIO.read(new File(".\\lab6.png"));
//        frame.drawingPanel.image.createGraphics();
//        //System.out.println("a intrat");
//        frame.drawingPanel.repaint();
    }
}


