package graphicsLAB6;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
    private JLabel messageLabel;

    public GameOverFrame(String winner) {
        super("Game Over");
        setPreferredSize(new Dimension(300, 100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        messageLabel = new JLabel(winner + " wins!");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}