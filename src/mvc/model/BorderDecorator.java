package mvc.model;

import mvc.model.shapes.ShapeInterface;

import java.awt.*;
import java.awt.geom.Point2D;

public class BorderDecorator implements ShapeDecorator{
    ShapeDecorator shape;
    int borderWidth;
    Color borderColor;

    public BorderDecorator (ShapeDecorator shape){
        this.shape = shape;
        borderColor = Color.BLACK;
    }

    @Override
    public void draw(Graphics2D g) {
        ShapeInterface border = shape.getShape().clone();
        Point2D left = new Point2D.Double(border.getMinX()-borderWidth, border.getMinY() - borderWidth);
        Point2D right = new Point2D.Double(border.getMaxX()+borderWidth, border.getMaxY() + borderWidth);
        border.setShapeByTwoPoint(left, right);
        g.setColor(borderColor);
        g.draw(border);
        shape.draw(g);
    }

    @Override
    public void setParametr(int p, Color c) {
        this.borderWidth = p;
        this.borderColor = c;
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
    public void setShape(ShapeInterface r) {
        shape.setShape(r);
    }

    @Override
    public void setFrame(Point2D[] p) {
        shape.setFrame(p);
    }

    @Override
    public MyShape.FillBehavior getFb(){
        return shape.getFb();
    }

    @Override
    public boolean contains(Point2D p) {
        return shape.contains(p);
    }

    @Override
    public ShapeInterface getShape() {
        return shape.getShape();
    }

    @Override
    public ShapeDecorator clone() {
        BorderDecorator copy = new BorderDecorator(shape.clone());
        copy.borderWidth = borderWidth;
        copy.borderColor = borderColor;
        return copy;
    }
}
