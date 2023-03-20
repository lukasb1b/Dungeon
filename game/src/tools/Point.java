package tools;

import level.tools.Coordinate;

/**
 * For easy handling of positions in the dungeon. <br>
 * No getter needed. All attributes are public. <br>
 * Point.x to get x <br>
 * Point.y to get y <br>
 */
public class Point {

    public float x;
    public float y;

    /**
     * A simple {@code float} point class.
     *
     * @param x the x value
     * @param y the y value
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /** Copies the point. */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Creates a Point with 0 for x and y
     *
     * <p>same as `new Point(0,0)`
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Convert Point to Coordinate by parsing float to int
     *
     * @return the converted point
     */
    public Coordinate toCoordinate() {
        return new Coordinate((int) x, (int) y);
    }

    /**
     * Creates the unit vector between point a and b
     *
     * @param a Point A
     * @param b Point B
     * @return the unit vector
     */
    public static Point getUnitDirectionalVector(Point b, Point a) {
        Point interactionDir = new Point(b);
        // (interactable - a) / len(interactable - a)
        interactionDir.x -= a.x;
        interactionDir.y -= a.y;
        double vecLength = calculateDistance(a, b);
        interactionDir.x /= vecLength;
        interactionDir.y /= vecLength;
        return interactionDir;
    }
    /**
     * calculates the distance between two points
     *
     * @param p1 Point A
     * @param p2 Point B
     * @return the Distance between the two points
     */
    public static float calculateDistance(Point p1, Point p2) {
        return new Point(p1).sub(p2).length();
    }

    /**
     * adds other to the point
     *
     * @param other point to add
     * @return the point where the values have changed
     */
    public Point add(Point other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * subtracts other from the point
     *
     * @param other point to subtract
     * @return the point where the values have changed
     */
    public Point sub(Point other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * @return the length of the point
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }
}
