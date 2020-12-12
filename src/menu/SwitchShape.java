package menu;

import mvc.controller.State;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class SwitchShape implements Command{
    State state;
    Shape rs;

    public SwitchShape(State state, Shape rs) {
        this.state = state;
        this.rs = rs;
    }

    @Override
    public void execute() {
        state.setRectangularShape(rs);
    }

}
