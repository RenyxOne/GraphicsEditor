package mvc.model.myLine;

import java.awt.geom.*;
import java.io.Serializable;

public abstract class MyLine extends RectangularShape {

    /**
     * The <code>Float</code> class defines an ellipse specified
     * in <code>float</code> precision.
     * @since 1.2
     */
    public static class Float extends Ellipse2D implements Serializable {
        /**
         * The X coordinate of the upper-left corner of the
         * framing rectangle of this {@code Ellipse2D}.
         * @since 1.2
         * @serial
         */
        public float x;

        /**
         * The Y coordinate of the upper-left corner of the
         * framing rectangle of this {@code Ellipse2D}.
         * @since 1.2
         * @serial
         */
        public float y;

        /**
         * The overall width of this <code>Ellipse2D</code>.
         * @since 1.2
         * @serial
         */
        public float width;

        /**
         * The overall height of this <code>Ellipse2D</code>.
         * @since 1.2
         * @serial
         */
        public float height;

        /**
         * Constructs a new <code>Ellipse2D</code>, initialized to
         * location (0,&nbsp;0) and size (0,&nbsp;0).
         * @since 1.2
         */
        public Float() {
        }

        /**
         * Constructs and initializes an <code>Ellipse2D</code> from the
         * specified coordinates.
         *
         * @param x the X coordinate of the upper-left corner
         *          of the framing rectangle
         * @param y the Y coordinate of the upper-left corner
         *          of the framing rectangle
         * @param w the width of the framing rectangle
         * @param h the height of the framing rectangle
         * @since 1.2
         */
        public Float(float x, float y, float w, float h) {
            setFrame(x, y, w, h);
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getX() {
            return (double) x;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getY() {
            return (double) y;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getWidth() {
            return (double) width;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public double getHeight() {
            return (double) height;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public boolean isEmpty() {
            return (width <= 0.0 || height <= 0.0);
        }

        /**
         * Sets the location and size of the framing rectangle of this
         * <code>Shape</code> to the specified rectangular values.
         *
         * @param x the X coordinate of the upper-left corner of the
         *              specified rectangular shape
         * @param y the Y coordinate of the upper-left corner of the
         *              specified rectangular shape
         * @param w the width of the specified rectangular shape
         * @param h the height of the specified rectangular shape
         * @since 1.2
         */
        public void setFrame(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public void setFrame(double x, double y, double w, double h) {
            this.x = (float) x;
            this.y = (float) y;
            this.width = (float) w;
            this.height = (float) h;
        }

        /**
         * {@inheritDoc}
         * @since 1.2
         */
        public Rectangle2D getBounds2D() {
            return new Rectangle2D.Float(x, y, width, height);
        }

        /*
         * JDK 1.6 serialVersionUID
         */
        private static final long serialVersionUID = -6633761252372475977L;
    }

    /**
     * The <code>Double</code> class defines an ellipse specified
     * in <code>double</code> precision.
     * @since 1.2
     */
    public static class Double extends MyLine implements Serializable {

        public double x1,x2,y1,y2;

        public Double() {
        }

        public double getX(){
            return x1;
        }

        public double getY(){
            return y1;
        }

        public double getHeight(){
            if (y1 > y2) return y1-y2;
            else return y2-y1;
        }

        public double getWidth(){
            if (x1 > x2) return x1-x2;
            else return x2-x1;
        }

        public void setFrame(double x1, double y1,  double x2,  double y2) {
            setLine(x1, y1, x2, y2);
        }

        public Double(double x1, double y1, double x2, double y2) {
            setLine(x1, y1, x2, y2);
        }

        public boolean isEmpty() {
            //return (width <= 0.0 || height <= 0.0);
            return (x1 == x2 && y1 == y2);
        }

        public void setLine(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public void setLine(Point2D p1, Point2D p2) {
            setLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }

        public Rectangle2D getBounds2D() {
            double x, y, w, h;
            if (x1 < x2) {
                x = x1;
                w = x2 - x1;
            } else {
                x = x2;
                w = x1 - x2;
            }
            if (y1 < y2) {
                y = y1;
                h = y2 - y1;
            } else {
                y = y2;
                h = y1 - y2;
            }
            return new Rectangle2D.Double(x, y, w, h);
        }

        /*
         * JDK 1.6 serialVersionUID
         */
        private static final long serialVersionUID = 5555464816372320683L;
    }

    public boolean contains(double x, double y) {
        // Normalize the coordinates compared to the ellipse
        // having a center at 0,0 and a radius of 0.5.
        double ellw = getWidth();
        if (ellw <= 0.0) {
            return false;
        }
        double normx = (x - getX()) / ellw - 0.5;
        double ellh = getHeight();
        if (ellh <= 0.0) {
            return false;
        }
        double normy = (y - getY()) / ellh - 0.5;
        return (normx * normx + normy * normy) < 0.25;

    }

    public boolean intersects(double x, double y, double w, double h) {
        /*//if (w <= 0.0 || h <= 0.0) {
        //    return false;
        // }
        // Normalize the rectangular coordinates compared to the ellipse
        // having a center at 0,0 and a radius of 0.5.
        double ellw = getWidth();
        if (ellw <= 0.0) {
            return false;
        }
        double normx0 = (x - getX()) / ellw - 0.5;
        double normx1 = normx0 + w / ellw;
        double ellh = getHeight();
        if (ellh <= 0.0) {
            return false;
        }
        double normy0 = (y - getY()) / ellh - 0.5;
        double normy1 = normy0 + h / ellh;
        // find nearest x (left edge, right edge, 0.0)
        // find nearest y (top edge, bottom edge, 0.0)
        // if nearest x,y is inside circle of radius 0.5, then intersects
        double nearx, neary;
        if (normx0 > 0.0) {
            // center to left of X extents
            nearx = normx0;
        } else if (normx1 < 0.0) {
            // center to right of X extents
            nearx = normx1;
        } else {
            nearx = 0.0;
        }
        if (normy0 > 0.0) {
            // center above Y extents
            neary = normy0;
        } else if (normy1 < 0.0) {
            // center below Y extents
            neary = normy1;
        } else {
            neary = 0.0;
        }
        return (nearx * nearx + neary * neary) < 0.25;*/
        return true;
    }

    public boolean contains(double x, double y, double w, double h) {
        return (contains(x, y) &&
                contains(x + w, y) &&
                contains(x, y + h) &&
                contains(x + w, y + h));
    }

    public PathIterator getPathIterator(AffineTransform at) {
        return new MyLineIterator(this, at);
    }

    /*public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(getX());
        bits += java.lang.Double.doubleToLongBits(getY()) * 37;
        bits += java.lang.Double.doubleToLongBits(getWidth()) * 43;
        bits += java.lang.Double.doubleToLongBits(getHeight()) * 47;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Ellipse2D) {
            Ellipse2D e2d = (Ellipse2D) obj;
            return ((getX() == e2d.getX()) &&
                    (getY() == e2d.getY()) &&
                    (getWidth() == e2d.getWidth()) &&
                    (getHeight() == e2d.getHeight()));
        }
        return false;
    }*/
}
