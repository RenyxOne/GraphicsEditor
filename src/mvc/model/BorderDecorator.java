package mvc.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class BorderDecorator implements ShapeDecorator{
    ShapeDecorator shape;
    int borderWidth;
    public BorderDecorator (ShapeDecorator shape){
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        RectangularShape border = (RectangularShape) shape.getShape().clone();
        Point2D leftUp = new Point2D.Double(border.getMinX()-borderWidth, border.getMaxY() + borderWidth);
        Point2D rightDown = new Point2D.Double(border.getMaxX()+borderWidth, border.getMinY() - borderWidth);
        border.setFrameFromDiagonal(leftUp, rightDown);
        shape.draw(g);
        g.draw(border);
    }

    @Override
    public void setParametr(int p) {
        borderWidth = p;
    }

    @Override
    public void setColor(Color c) {
        shape.setColor(c);
    }

    @Override
    public void setFb(MyShape.FillBehavior f) {
        shape.setFb(f);
    }

    @Override
    public void setShape(RectangularShape r) {
        shape.setShape(r);
    }

    @Override
    public void setFrame(Point2D[] p) {
        shape.setFrame(p);
    }

    @Override
    public boolean contains(Point2D p) {
        return shape.contains(p);
    }

    @Override
    public RectangularShape getShape() {
        return shape.getShape();
    }

    @Override
    public ShapeDecorator clone() {
        BorderDecorator copy = new BorderDecorator(shape.clone());
        copy.borderWidth = borderWidth;
        return copy;
    }
}
