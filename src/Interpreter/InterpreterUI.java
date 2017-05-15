package Interpreter;

/**
 * Created by illiant on 2017/5/15.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterpreterUI {
    public static void main(String[] args){
        JFrame F = new JMenuItem5();
        F.setSize(1000, 600);
        F.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });// end of addWindowListener
        F.setVisible(true);
    }


}
