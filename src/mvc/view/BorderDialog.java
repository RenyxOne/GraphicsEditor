package mvc.view;

import mvc.controller.State;
import mvc.model.BorderDecorator;
import mvc.model.ShapeDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class BorderDialog extends JFrame {
        private JPanel  contents;
        private JButton btnMessage1;
        private JFormattedTextField rangeField;
        private JButton changeColor;
        private JLabel range;
        private NumberFormat nums;

        State state;
        Color color;
        public BorderDialog(State state)
        {
            super("Настройка нового бордюра");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            this.state = state;
            this.color = state.getColor();

            contents = new JPanel();

            btnMessage1 = new JButton("Добавить бордюр");
            changeColor = new JButton("Изменить цвет");
            range = new JLabel("Ширина бордюра:");
            changeColor.setBackground(color);
            rangeField = new JFormattedTextField(nums);
            rangeField.setValue(new Integer(1));

            btnMessage1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShapeDecorator rs = state.getShape();
                    rs = new BorderDecorator(rs);
                    rs.setParametr(Integer.parseInt(rangeField.getText()), color);
                    state.setShape(rs);
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
            contents.setLayout(null);

            rangeField.setBounds(35, 30, 150, 20);
            contents.add(rangeField);

            range.setBounds(35, 10, 150,20 );
            contents.add(range);

            btnMessage1.setBounds(215, 10, 250,40);
            contents.add(btnMessage1);

            changeColor.setBounds(35, 60, 430,40);
            contents.add(changeColor);
            setContentPane(contents);

            // Вывод окна на экран
            setSize(500, 140);
            setVisible(true);
        }
    }
