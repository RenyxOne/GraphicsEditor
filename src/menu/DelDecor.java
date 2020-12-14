package menu;

import mvc.controller.State;
import mvc.model.MyShape;

public class DelDecor implements Command {
    State state;
    public DelDecor (State state){
        this.state = state;
    }
    @Override
    public void execute() {
        state.setShape(new MyShape(state.getColor(),state.getShape().getShape(), state.getShape().getFb()));
    }
}
