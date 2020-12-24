package menu;


import mvc.controller.State;
import mvc.model.BorderDecorator;
import mvc.model.MyShape;
import mvc.model.ShapeDecorator;
import mvc.model.shapes.MyRectangle;
import mvc.model.shapes.ShapeInterface;

import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.text.NumberFormat;

public class Decorator10 implements Command{
    State state;

    public Decorator10 (State state){
        this.state = state;
    }
    @Override
    public void execute() {
        state.initBorderDialog();
    }
}
