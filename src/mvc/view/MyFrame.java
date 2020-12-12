package mvc.view;

import menu.*;
import mvc.model.*;
import mvc.model.activity.*;
import mvc.controller.*;
import mvc.model.line.MyLine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class MyFrame extends JFrame {

    MyPanel panel;
    State state;
    UndoMachine undoMachine;

    public MyFrame(State state, UndoMachine machine) {
        this.state = state;
        this.undoMachine = machine;

        JMenuBar bar;
        bar = new JMenuBar();
        this.setJMenuBar(bar);
        ArrayList<Action> menuItems = new ArrayList<>();
        menuItems.add(new SwitchState("открыть файл",new ImageIcon(getClass().getResource("images/open.gif")),
                new OpenFile(state)));
        menuItems.add(new SwitchState("создать файл",new ImageIcon(getClass().getResource("images/save.gif")),
                new SaveFile(state)));
        menuItems.add(new SwitchState("Line", new ImageIcon(getClass().getResource("images/Line.gif")),
                new SwitchShape(state, new MyLine.Double())));
        menuItems.add(new SwitchState("прямоугольник", new ImageIcon(getClass().getResource("images/rectangle.gif")),
                new SwitchShape(state, new Rectangle2D.Double())));
        menuItems.add(new SwitchState("овал", new ImageIcon(getClass().getResource("images/ellipse.gif")),
                new SwitchShape(state, new Ellipse2D.Double())));
        menuItems.add(new SwitchState("Бордюр", new ImageIcon(getClass().getResource("images/Border.gif")),
                new Decorator10(state)));
        menuItems.add(new SwitchState("незалитый", new ImageIcon(getClass().getResource("images/nofill.gif")),
                new SwitchFill(state, MyShape.FillBehavior.NO_FILL)));
        menuItems.add(new SwitchState("залитый", new ImageIcon(getClass().getResource("images/fill.gif")),
                new SwitchFill(state, MyShape.FillBehavior.FILL)));
        menuItems.add(new SwitchState("рисовать", new ImageIcon(getClass().getResource("images/draw.gif")),
                new SwitchActivity(state, new Draw())));
        menuItems.add(new SwitchState("двигать", new ImageIcon(getClass().getResource("images/move.gif")),
                new SwitchActivity(state, new Move())));
        menuItems.add(new SwitchUndo("undo",new ImageIcon(getClass().getResource("images/undo.gif")),undoMachine));
        menuItems.add(new SwitchRedo("redo",new ImageIcon(getClass().getResource("images/redo.gif")),undoMachine));
        menuItems.add(new SwitchState("выбор цвета", new ImageIcon(getClass().getResource("images/colors.gif")),
                new SwitchColor(state)));
        undoMachine.addObserver((SwitchUndo)menuItems.get(menuItems.size()-3));
        undoMachine.addObserver((SwitchRedo)menuItems.get(menuItems.size()-2));
        undoMachine.notifyMenu();

        ArrayList<JMenu> menus = new ArrayList<>();
        menus.add(new JMenu("Файл"));
        menus.add(new JMenu("Фигура"));
        menus.add(new JMenu("Заливка"));
        menus.add(new JMenu("Действие"));
        menus.add(new JMenu("Undo/redo"));
        menus.add(new JMenu("Цвет"));
        int i = 0;
        int k = menuItems.size()-2;
        for (JMenu m : menus) {
            bar.add(m);

            if (i < k) {
                m.add(menuItems.get(i));
                m.add(menuItems.get(i + 1));
                i += 2;
            } else {
                m.add(menuItems.get(i));
            }
        }
        JToolBar toolBar = new JToolBar();
        for (Action x : menuItems) {
            toolBar.add(x);
        }

        add(toolBar, BorderLayout.NORTH);




//
        /////////////////////////////////////////////////////////////////
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
        add(panel);
    }

}