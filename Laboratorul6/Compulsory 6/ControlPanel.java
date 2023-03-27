package graphicsLAB6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));
        add(loadButton);
        add(saveButton);
        add(exitButton);
        exitButton.addActionListener(this::exitGame);
        //TODO action listener pt restul butoanelor
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

//    public void save() throws IOException {
//        ImageIO.write(ceva rendered Image ?, "PNG", new File("lab6.png"));
//    }

//    public void load() throws IOException {
//        drawArea.setImage(ImageIO.read(new File("lab6.png")));
//        repaint();
//    }

}
