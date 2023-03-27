package graphicsLAB6;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    //private double edgeProbability;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image

    /**
     * Constructor for the drawing frame
     * @param frame MainFrame
     */
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    /**
     * Initializer for the drawing panel
     */
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * Creates off screen image
     */
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    /**
     * Creates the board
     */
    final void createBoard() {
        //edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        createOffscreenImage();
        paintComponent(graphics);
    }

    @Override
    public void update(Graphics g) { } //No need for update

    /**
     * Draws the complete graph given by the parameters from the configuration panel
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int numOfDots = frame.configPanel.getNumberOfDots();

        int x0 = W / 2; int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numOfDots; // the angle
        x = new int[numOfDots];
        y = new int[numOfDots];
        for (int i = 0; i < numOfDots; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            g.fillOval(x[i] - 5 , y[i] - 5, 10, 10);
        }

        g.setColor(Color.GRAY);
        for (int i=0; i<numOfDots;i++)
            for (int j = 0; j < numOfDots; j++){
                g.drawLine(x[i], y[i], x[j], y[j]);
            }

    }
}
