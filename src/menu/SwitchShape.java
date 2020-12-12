package menu;

import mvc.controller.State;
import mvc.model.BorderDecorator;
import mvc.model.ShapeDecorator;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class SwitchShape implements Command{
    State state;
    Shape rs;

    public SwitchShape(State state, Shape rs) {
        this.state = state;
        this.rs = rs;
    }

    /*public SwitchShape(State state, ShapeDecorator rs) {
        this.state = state;
        rs.setParametr(10);
        rs = new BorderDecorator(rs);
        state.setShape(rs);
    }*/

    @Override
    public void execute() {
        state.setRectangularShape(rs);
    }

}
