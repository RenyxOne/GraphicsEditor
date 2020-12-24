package menu;


import mvc.controller.State;

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
