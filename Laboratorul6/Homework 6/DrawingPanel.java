package graphicsLAB6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    final static int W = 800, H = 600;
    private static int turn = 1;
    private static Line selectedLine;
    final MainFrame frame;
    Color currentColor = Color.GRAY; //1 = red; 2 = blue
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    private int[] x, y;
    private boolean endGame = false;
    private boolean gameStarted;
    private List<Line> lines;
    private List<Line> player1LinesList;
    private List<Line> player2LinesList;

    /**
     * Constructor for the drawing frame
     *
     * @param frame MainFrame
     */
    public DrawingPanel(MainFrame frame) {
        gameStarted = false; //the game starts when the user clicks on a line
        selectedLine = null;
        this.frame = frame;
        lines = new ArrayList<>();
        player1LinesList = new ArrayList<>();
        player2LinesList = new ArrayList<>();
        createOffscreenImage();
        initPanel();
    }

    /**
     * Checks if point C in on the line formed by points A and B
     *
     * @param A         Point
     * @param B         Point
     * @param C         Point
     * @param tolerance double (Used to avoid a / by 0 exception and to minimize accuracy from the mouse click)
     * @return true/false
     */
    public static boolean isOnTheLine(Point A, Point B, Point C, double tolerance) {
        double minX = Math.min(A.getX(), B.getX()) - tolerance;
        double maxX = Math.max(A.getX(), B.getX()) + tolerance;
        double minY = Math.min(A.getY(), B.getY()) - tolerance;
        double maxY = Math.max(A.getY(), B.getY()) + tolerance;

        //Check C is within the bounds of the line
        if (C.getX() >= maxX || C.getX() <= minX || C.getY() <= minY || C.getY() >= maxY) {
            return false;
        }

        // Check for when AB is vertical
        if (A.getX() == B.getX()) {
            if (Math.abs(A.getX() - C.getX()) >= tolerance) {
                return false;
            }
            return true;
        }
        // Check for when AB is horizontal
        if (A.getY() == B.getY()) {
            if (Math.abs(A.getY() - C.getY()) >= tolerance) {
                return false;
            }
            return true;
        }

        // Check instance of the point form the line
        double distFromLine = Math.abs(((B.getX() - A.getX()) * (A.getY() - C.getY())) - ((A.getX() - C.getX()) * (B.getY() - A.getY()))) / Math.sqrt((B.getX() - A.getX()) * (B.getX() - A.getX()) + (B.getY() - A.getY()) * (B.getY() - A.getY()));

        if (distFromLine >= tolerance) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Setter for gameStarted
     *
     * @param gameStarted boolean
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Setter for the turn
     *
     * @param turn int
     */
    public void setTurn(int turn) {
        DrawingPanel.turn = turn;
    }

    /**
     * Resets the lines list for a new game
     */
    public void resetLinesList() {
        lines = new ArrayList<>();
        player1LinesList = new ArrayList<>();
        player2LinesList = new ArrayList<>();
    }

    /**
     * resets the endGame flag for a new game to false
     */
    public void resetEndGameFlag() {
        endGame = false;
    }

    /**
     * Initializer for the drawing panel
     */
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Accepts mouse input only if the game is not finished yet
                if (!endGame) {
                    Point clickPoint = e.getPoint();
                    for (int i = 0; i < lines.size(); i++) {
                        Point p1 = lines.get(i).getStart();
                        Point p2 = lines.get(i).getEnd();
                        if (isOnTheLine(p1, p2, clickPoint, 5)) {
                            try {
                                if (lines.get(i).isColored()) {
                                    throw new IllegalArgumentException("This line has already been chosen. Please select another line.");
                                }
                                selectedLine = lines.get(i);
                                checkWinner(selectedLine);
                                if (!gameStarted) {
                                    gameStarted = true;
                                }
                                if (turn == 1) {
                                    currentColor = Color.RED;
                                    lines.get(i).setColor(currentColor);
                                    lines.get(i).setColored(true);
                                    player1LinesList.add(selectedLine);
                                    turn = 2;
                                } else if (turn == 2) {
                                    currentColor = Color.BLUE;
                                    lines.get(i).setColor(currentColor);
                                    lines.get(i).setColored(true);
                                    player2LinesList.add(selectedLine);
                                    turn = 1;
                                }
                                repaint();
                                break;
                            } catch (IllegalArgumentException ex) {
                                System.out.println(ex.getMessage());
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Creates off-screen image
     */
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    @Override
    public void update(Graphics g) {
    } //No need for update

    /**
     * Draws the complete graph given by the parameters from the configuration panel
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        super.paintComponent(g2d);

        int numOfDots = frame.configPanel.getNumberOfDots();
        if (!gameStarted) {
            int x0 = W / 2;
            int y0 = H / 2; //middle of the board
            int radius = H / 2 - 10; //board radius
            double alpha = 2 * Math.PI / numOfDots; // the angle
            x = new int[numOfDots];
            y = new int[numOfDots];
            for (int i = 0; i < numOfDots; i++) {
                x[i] = x0 + (int) (radius * Math.cos(alpha * i));
                y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            }

            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(5));
            for (int i = 0; i < numOfDots - 1; i++)
                for (int j = i + 1; j < numOfDots; j++) {
                    g2d.drawLine(x[i], y[i], x[j], y[j]);
                    lines.add(new Line(x[i], y[i], x[j], y[j]));
                }
        }

        for (Line line : lines) {
            if (line.isColored()) {
                g2d.setColor(line.getColor());
            } else {
                g2d.setColor(Color.GRAY);
            }
            g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
            if (line == selectedLine && !line.isColored()) {
                g2d.setColor(currentColor);
                g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(),
                        (int) line.getEnd().getX(), (int) line.getEnd().getY());
            }
        }
        g2d.setStroke(new BasicStroke(5));
        if (selectedLine != null) {
            g2d.setColor(currentColor);
            g2d.drawLine((int) selectedLine.getStart().getX(), (int) selectedLine.getStart().getY(), (int) selectedLine.getEnd().getX(), (int) selectedLine.getEnd().getY());
            selectedLine = null;
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        for (int i = 0; i < numOfDots; i++) {
            g2d.fillOval(x[i] - 10, y[i] - 10, 20, 20);
        }
        checkIfLinesLeft();
        graphics.drawImage(image, 0, 0, this);
    }

    /**
     * Checks if 2 lines have a common point
     *
     * @param a Line
     * @param b Line
     * @return true/false
     */
    public boolean checkCommonPoint(Line a, Line b) {
        if ((a.getStart().equals(b.getStart()) || a.getStart().equals(b.getEnd()) ||
                a.getEnd().equals(b.getStart()) || a.getEnd().equals(b.getEnd())) && !a.equals(b)) {
            return true;
        } else return false;
    }

    /**
     * Checks if a triangle has been formed
     *
     * @param line Line
     * @param list List of lines
     * @return true/false
     */
    public boolean checkTriangle(Line line, List<Line> list) {
        for (Line i : list) {
            for (Line j : list) {
                if (checkCommonPoint(i, j) && checkCommonPoint(i, line) && checkCommonPoint(j, line)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a winner after a line in chosen
     *
     * @param line Line
     */
    public void checkWinner(Line line) {
        if (turn == 1) {
            if (checkTriangle(line, player1LinesList)) {
                endGame = true;
                GameOverFrame gameOver = new GameOverFrame("Player1");
                System.out.println("Winner is Player1!*****************************************************************");
            }
        } else if (turn == 2) {
            if (checkTriangle(line, player2LinesList)) {
                endGame = true;
                GameOverFrame gameOver = new GameOverFrame("Player2");
                System.out.println("Winner is Player2!*****************************************************************");
            }
        }
    }

    /**
     * Checks if there are lines left to choose
     */
    public void checkIfLinesLeft() {
        boolean allLinesColored = true;  // flag to check if all lines are colored
        for (Line line2 : lines) {
            if (!line2.isColored()) {
                allLinesColored = false;
                break;
            }
        }
        if (allLinesColored) {
            endGame = true;
            GameOverFrame gameOver = new GameOverFrame("No one");
            System.out.println("No one won!*****************************************************************");
        }
    }
}
