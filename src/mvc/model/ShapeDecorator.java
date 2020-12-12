package mvc.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


public interface ShapeDecorator {
    void draw(Graphics2D g);
    void setParametr(int p);
    void setColor(Color c);
    void setFb(MyShape.FillBehavior f);
    void setShape(RectangularShape r);
    void setFrame(Point2D [] p);
    boolean contains(Point2D p);

    RectangularShape getShape();
    ShapeDecorator clone();
}
