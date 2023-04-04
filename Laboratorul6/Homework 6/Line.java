package graphicsLAB6;

import java.awt.*;

public class Line {
    private Point start;
    private Point end;
    private Color color;
    private boolean isColored;

    Line() {

    }

    /**
     * Constructor for a new line
     *
     * @param x1 start.x coordinate
     * @param y1 start.y coordinate
     * @param x2 end.x coordinate
     * @param y2 end.y coordinate
     */
    Line(int x1, int y1, int x2, int y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        isColored = false;
        color = Color.GRAY;
    }

    /**
     * Getter for the starting point
     *
     * @return start
     */
    public Point getStart() {
        return start;
    }

    /**
     * Getter for the ending point
     *
     * @return end
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Getter for isColored
     *
     * @return true/false
     */
    public boolean isColored() {
        return isColored;
    }

    /**
     * Setter for isColored
     *
     * @param colored boolean
     */
    public void setColored(boolean colored) {
        this.isColored = colored;
    }

    /**
     * Getter for the color of the line
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for the color of the line
     *
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if 2 lines are the same
     *
     * @param line Line
     * @return true/false
     */
    public boolean equals(Line line) {
        if ((line.getStart().equals(this.start) && line.getEnd().equals(this.end)) || (line.getStart().equals(this.start) && line.getEnd().equals(this.start))) {
            return true;
        } else {
            return false;
        }
    }
}