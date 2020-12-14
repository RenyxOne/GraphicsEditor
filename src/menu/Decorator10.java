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

        new JOptionPaneTest(state);
    }

    class JOptionPaneTest extends JFrame{
        private JPanel  contents;
        private JButton btnMessage1;
        private JFormattedTextField rangeField;
        private JButton changeColor;
        private NumberFormat nums;

        State state;
        Color color;
        public JOptionPaneTest(State state)
        {
            super("Пример использования JOptionPane");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            this.state = state;
            this.color = state.getColor();

            contents = new JPanel();

            btnMessage1 = new JButton("Редактировать текущую фигуру");
            changeColor = new JButton("Изменить цвет");
            changeColor.setBackground(color);
            rangeField = new JFormattedTextField(nums);
            rangeField.setValue(new Integer(1));

            btnMessage1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShapeDecorator rs = state.getShape();
                    rs.setParametr(Integer.parseInt(rangeField.getText()));
                    //rs.setColor(color);
                    rs = new BorderDecorator(rs);
                    //rs.setColor(color);
                    state.setShape(rs);
                    //state.setColor(color);
                }
            });
            changeColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    color = JColorChooser.showDialog(null, "choose color", Color.BLACK);
                    changeColor.setBackground(color);
                }
            });


            // Размещение кнопок в интерфейсе
            contents.add(rangeField);
            contents.add(btnMessage1);
            contents.add(changeColor);

            setContentPane(contents);
            // Вывод окна на экран
            setSize(500, 140);
            setVisible(true);
        }
    }
}
