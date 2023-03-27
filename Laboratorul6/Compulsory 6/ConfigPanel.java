package graphicsLAB6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JLabel dotsLabel;
    private JTextField dotsField;
    private JLabel linesLabel;
    private JTextField linesField;
    private JButton newGameButton;

    /**
     * Constructor for the configuration panel
     * @param frame MainFrame
     */
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Initializes the configuration panel
     * Sets the default number of vertices (dots) as 7 and the default number of edges (lines) for a complete graph
     * If the new game button is pressed, a new game starts with the given configuration 
     */
    private void init() {
        setLayout(new FlowLayout());
        dotsLabel = new JLabel("Number of dots:");
        dotsField = new JTextField(4);
        dotsField.setText("7");
        linesLabel = new JLabel("Number of lines:");
        linesField = new JTextField(4);
        int numLines = this.getNumberOfDots() * (this.getNumberOfDots() - 1) / 2;
        String str = "";
        str += numLines;
        linesField.setText(str);
        newGameButton = new JButton("New Game");
        add(dotsLabel);
        add(dotsField);
        add(linesLabel);
        add(linesField);
        add(newGameButton);
        newGameButton.addActionListener(this::createNewGame);
    }

    /**
     * Method that computes the number of vertices (dots)
     * @return
     */
    public int getNumberOfDots() {
        return Integer.parseInt(dotsField.getText());
    }

    /**
     * Action handler for creating a new game after the "new game" button is pressed
     * @param e
     */
    private void createNewGame(ActionEvent e){
        int numLines = this.getNumberOfDots() * (this.getNumberOfDots() - 1) / 2;
        String str = "";
        str += numLines;
        frame.configPanel.linesField.setText(str);
        frame.drawingPanel.repaint();
    }

}
