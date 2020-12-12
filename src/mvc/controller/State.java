package mvc.controller;

import mvc.model.Model;
import mvc.model.MyShape;
import mvc.model.activity.Activity;
import mvc.model.activity.Draw;
import mvc.model.ShapeDecorator;

import java.awt.*;
import java.awt.geom.RectangularShape;
public class State {
    //from controller
    Model model;
    // menu created
    ShapeDecorator shape;
    Color color;
    MyShape.FillBehavior fb;
    RectangularShape rectangularShape;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.activity.setModel(model);
    }

    public Activity getActivity() {
        return activity;
    }

    public State(Model model) {
        this.model = model;
        activity = new Draw(model);
    }

    public void setShape(ShapeDecorator shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
        shape.setColor(color);
        model.setSampleShape(shape);
    }

    public void setFb(MyShape.FillBehavior fb) {
        this.fb = fb;
        shape.setFb(fb);
        model.setSampleShape(shape);
    }

    public void setRectangularShape(Shape rectangularShape) {
        if (rectangularShape == null) return;
        this.rectangularShape = (RectangularShape)rectangularShape;
        shape.setShape((RectangularShape)rectangularShape);
        model.setSampleShape(shape);
    }

    public ShapeDecorator getShape() {
        return shape;
    }

    public Model getModel() {
        return model;
    }


}