package mvc.model.line;

import java.awt.geom.*;
import java.io.Serializable;

public abstract class MyLine extends RectangularShape {

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

        @Override
        public double getMinX() {
            return getX();
        }

        @Override
        public double getMinY() {
            return getY();
        }

        @Override
        public double getMaxX() {
            return getWidth();
        }

        @Override
        public double getMaxY() {
            return getHeight();
        }

        public double getHeight(){
            //if (y1 > y2) return y1-y2;
            //else return y2-y1;
            return y2;
        }

        public double getWidth(){
            //if (x1 > x2) return x1-x2;
           // else return x2-x1;
            return x2;
        }

        public void setFrame(double x1, double y1,  double x2,  double y2) {
            setLine(x1, y1, x2, y2);
        }

        public Double(double x1, double y1, double x2, double y2) {
            setLine(x1, y1, x2, y2);
        }

        @Override
        public void setFrameFromDiagonal(double x1, double y1, double x2, double y2) {
            setLine(x1, y1, x2, y2);
        }
        @Override
        public void setFrameFromDiagonal(Point2D d, Point2D p) {
            setLine(d,p);
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