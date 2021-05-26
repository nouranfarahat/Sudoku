/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class Board {

    static int milli = 0;
    static int second = 0;
    static int minute = 0;
    static int hour = 0;
    static boolean state = true;

    private JButton btnNew;
    private JButton btnSubmit;
    private JButton btnReset;
    private JButton btnHint;
    private JButton btnSolve;
    private JButton btnCheck;
    private JButton btnDone;
    private JPanel btnsPanel;

    static JFrame f;
    private Cell[][] original;
    //  private Cell[][] userGrid ;
    private Grid g;
    private Buttons button;

    private SodokuSolver solver;
    //   private List<Cell> list;
    private int currentMode;

    public Board() {
        f = new JFrame("Sodoku");
        g = new Grid(true);
        f.add(g);
        SetFrameDesign();

        Timer.hourLabel = new JLabel("00: ");
        Timer.minuteLabel = new JLabel("00: ");
        Timer.secondLabel = new JLabel("00: ");
        Timer.milliLabel = new JLabel("00");

        btnDone = new JButton("Done");
        btnReset = new JButton("Reset");
        btnHint = new JButton("Hint");
        btnCheck = new JButton("Check");
        btnSolve = new JButton("Solve");
        btnSubmit = new JButton("Submit");

        btnsPanel = new JPanel();

        btnsPanel.add(btnDone);

        btnSolve.setVisible(false);
        btnSubmit.setVisible(false);
        btnHint.setVisible(false);
        btnReset.setVisible(false);
        btnCheck.setVisible(false);

        btnDone.addActionListener(new btnDoneEvent());
        btnSolve.addActionListener(new btnSolveEvent());
        btnReset.addActionListener(new btnResetEvent());
        btnHint.addActionListener(new btnHintEvent());
        btnCheck.addActionListener(new btnCheckEvent());
        btnSubmit.addActionListener(new btnSubmitEvent());

        f.add(btnsPanel, BorderLayout.SOUTH);

        //solver = new SodokuSolver(original);
    }

    public Board(int mode) {
        f = new JFrame("Sodoku");
        g = new Grid(false);////
        currentMode = mode;

        new SudokuGenerator(Const.dimension, mode, g.getGrid());

        original = new Cell[Const.dimension][Const.dimension];
        for (int i = 0; i < Const.dimension; i++) {
            for (int j = 0; j < Const.dimension; j++) {
               original[i][j] = new Cell(g.getGrid()[i][j]);
               //  original[i][j] = g.getGrid()[i][j];
            }
        }
        f.add(g);

        solver = new SodokuSolver(original);

        SetFrameDesign();

        Timer.hourLabel = new JLabel("00: ");
        Timer.minuteLabel = new JLabel("00: ");
        Timer.secondLabel = new JLabel("00: ");
        Timer.milliLabel = new JLabel("00");

        btnNew = new JButton("New");
        btnReset = new JButton("Reset");
        btnHint = new JButton("Hint");
        btnCheck = new JButton("Check");
        btnSolve = new JButton("Solve");
        btnSubmit = new JButton("Submit");

        btnsPanel = new JPanel();

        btnsPanel.add(Timer.hourLabel);
        btnsPanel.add(Timer.minuteLabel);
        btnsPanel.add(Timer.secondLabel);
        btnsPanel.add(Timer.milliLabel);
        btnsPanel.add(btnNew);
        btnsPanel.add(btnHint);
        btnsPanel.add(btnCheck);
        btnsPanel.add(btnSolve);
        btnsPanel.add(btnReset);
        btnsPanel.add(btnSubmit);

        btnReset.addActionListener(new btnResetEvent());////
        btnSolve.addActionListener(new btnSolveEvent());
        btnHint.addActionListener(new btnHintEvent());
        btnNew.addActionListener(new btnNewEvent());
        btnCheck.addActionListener(new btnCheckEvent());
        btnSubmit.addActionListener(new btnSubmitEvent());

        f.add(btnsPanel, BorderLayout.SOUTH);
        Timer.make();////
        //  list=getAvailableCells(g.getGrid());  
    }

    private void SetFrameDesign() {
        f.setPreferredSize(new Dimension(600, 550));
        f.pack();////
        f.setResizable(false);////
        f.setVisible(true);
        f.setLocationRelativeTo(null);////
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);////
    }

    private class btnNewEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            button = new Buttons(original, g, f, currentMode);
            button.newEvent_func();
            Timer.make();
            btnHint.setEnabled(true);
            ExceedEntryException.hintClicks = 0;
            btnCheck.setEnabled(true);
            ExceedEntryException.checkClicks = 0;

        }
    }

    private class btnCheckEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ExceedEntryException.checkClicks++;
            try {
                if (ExceedEntryException.checkClicks > 3) {
                    btnCheck.setEnabled(false);
                    throw new ExceedEntryException("You exceeded number of usage Check button");

                } else {
                    button = new Buttons(original, g);
                    button.check_func();

                }
            } catch (ExceedEntryException IN) {
                
                JOptionPane.showMessageDialog(Board.f, IN.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class btnSubmitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            button = new Buttons(original, g);
            if (button.submit_func()) {
                JOptionPane.showMessageDialog(btnSubmit, "Congratulations,You Win!", "Message", JOptionPane.INFORMATION_MESSAGE);
                Timer.showThetime();
                if (!NewJFrame.MyBordSelected) {
                    button = new Buttons(original, g, f, currentMode);
                    button.newEvent_func();
                    Timer.make();
                    btnHint.setEnabled(true);
                    ExceedEntryException.hintClicks = 0;
                    btnCheck.setEnabled(true);
                    ExceedEntryException.checkClicks = 0;
                }
            } else {
                JOptionPane.showMessageDialog(Board.f, "You Lose,try again", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class btnSolveEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            button = new Buttons(original, g);
            button.solve_func();

        }
    }

    private class btnResetEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            button = new Buttons(g);
            button.reset_func();
        }
    }

    private class btnHintEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ExceedEntryException.hintClicks++;
            try {
                if (ExceedEntryException.hintClicks > 3) {
                    btnHint.setEnabled(false);
                    throw new ExceedEntryException("You exceeded number of usage Hint button");

                } else {
                    button = new Buttons(original, g);
                    button.hint_func();
                }
            } catch (ExceedEntryException IN) {

                JOptionPane.showMessageDialog(Board.f, IN.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private class btnDoneEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int cnt = 0;

            btnsPanel.add(Timer.hourLabel);
            btnsPanel.add(Timer.minuteLabel);
            btnsPanel.add(Timer.secondLabel);
            btnsPanel.add(Timer.milliLabel);

            btnsPanel.add(btnSolve);
            btnsPanel.add(btnHint);
            btnsPanel.add(btnCheck);
            btnsPanel.add(btnReset);
            btnsPanel.add(btnSubmit);
            
             button = new Buttons(g);
             button.done_func();

            original = new Cell[Const.dimension][Const.dimension];
            for (int i = 0; i < Const.dimension; i++) {
                for (int j = 0; j < Const.dimension; j++) {
                    original[i][j] = new Cell(g.getGrid()[i][j]);
                    if (g.getGrid()[i][j].getCell().getText().equals("")) {
                        cnt++;
                    }

                }
            }

            try {
                solver = new SodokuSolver(original);
                boolean x = solver.sudukuSolver();

                if (x == false) {
                    throw new InvalidDataException("Wrong Soduku");
                } else if (cnt == 81) {
                    throw new InvalidDataException("Empty Soduku");
                } else {
                   

                    btnDone.setVisible(false);
                    btnSolve.setVisible(true);
                    btnSubmit.setVisible(true);
                    btnHint.setVisible(true);
                    btnReset.setVisible(true);
                    btnCheck.setVisible(true);
                    f.add(btnsPanel, BorderLayout.SOUTH);
                    Timer.make();

                    //Timer.make();
                }
            } catch (InvalidDataException IN) {
                JOptionPane.showMessageDialog(Board.f, IN.getMessage(), "", JOptionPane.WARNING_MESSAGE);
                for (int i = 0; i < Const.dimension; i++) {
                    for (int j = 0; j < Const.dimension; j++) {
                        g.getGrid()[i][j].getCell().setEditable(true);
                    }
                }
            }

        }

    }

}
