package menu;


import mvc.controller.State;
import mvc.model.BorderDecorator;
import mvc.model.ShapeDecorator;

public class Decorator10 implements Command{
    State state;

    public Decorator10 (State state){
        this.state = state;
    }
    @Override
    public void execute() {
        ShapeDecorator rs = state.getShape().clone();
        rs.setParametr(10);
        rs = new BorderDecorator(rs);
        state.setShape(rs);
    }
}
