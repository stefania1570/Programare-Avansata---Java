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
     *
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
        setNumberOfEdges();
        newGameButton = new JButton("New Game");
        add(dotsLabel);
        add(dotsField);
        add(linesLabel);
        add(linesField);
        add(newGameButton);
        newGameButton.addActionListener(this::createNewGame);
    }

    /**
     * Calculates the number of edges based on the number of vertices
     */
    private void setNumberOfEdges(){
        int numLines = this.getNumberOfDots() * (this.getNumberOfDots() - 1) / 2;
        String str = "";
        str += numLines;
        linesField.setText(str);
    }

    /**
     * Method that computes the number of vertices (dots)
     *
     * @return the number of vertices - int
     */
    public int getNumberOfDots() {
        int numberOfDots = 7; //DEFAULT
        try {
            numberOfDots = Integer.parseInt(dotsField.getText());
            if (numberOfDots <= 3) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            dotsField.setText("7");
            linesField.setText("21");
            System.out.println("Invalid input for number of dots.");
        } catch (IllegalArgumentException e) {
            dotsField.setText("7");
            linesField.setText("21");
            System.out.println("If the number of vertices is less than 4, there can be no winner to the game.\nPlease try another number.");
       }
        return numberOfDots;
    }

    /**
     * Action handler for creating a new game after the "new game" button is pressed
     *
     * @param e ActionEvent
     */
    public void createNewGame(ActionEvent e) {
        int numLines = this.getNumberOfDots() * (this.getNumberOfDots() - 1) / 2;
        String str = "";
        str += numLines;
        frame.configPanel.linesField.setText(str);
        frame.drawingPanel.setGameStarted(false);
        frame.drawingPanel.resetEndGameFlag();
        frame.drawingPanel.setTurn(1);
        frame.drawingPanel.resetLinesList();
        frame.drawingPanel.repaint();
    }

}
