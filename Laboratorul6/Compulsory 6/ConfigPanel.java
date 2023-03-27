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

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

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

    public int getNumberOfDots() {
        return Integer.parseInt(dotsField.getText());
    }
    private void createNewGame(ActionEvent e){
        int numLines = this.getNumberOfDots() * (this.getNumberOfDots() - 1) / 2;
        String str = "";
        str += numLines;
        frame.configPanel.linesField.setText(str);
        frame.drawingPanel.repaint();
    }

}
