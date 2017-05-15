package Interpreter;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
/**
 * Created by illiant on 2017/5/15.
 */
public class JMenuItem5 extends JFrame{
    JTextArea theArea = null;
    JTextArea inputSection = null;
    JTextArea outputSection = null;

    public JMenuItem5() {
        super("Interpreter");
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upPanel.setBackground(Color.WHITE);
        theArea = new JTextArea();
        theArea.setEditable(true);
        inputSection = new JTextArea();
        inputSection.setEditable(true);
        outputSection = new JTextArea();
        outputSection.setEditable(false);
        ScrollPane sp1 = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        sp1.add(theArea);
        this.add(sp1, BorderLayout.CENTER);

        upPanel.add(inputSection);
        this.add(upPanel, BorderLayout.EAST);
//		getContentPane().add(new JScrollPane(theArea));
//		getContentPane().add(new JScrollPane(inputSection));
        JMenuBar MBar = new JMenuBar();
        MBar.setOpaque(true);

        JMenu mfile = buildFileMenu();
        JMenu mRun = buildRunMenu();
        JMenu mVersion = buildVersionMenu();
        MBar.add(mfile);
        MBar.add(mRun);
        MBar.add(mVersion);
        setJMenuBar(MBar);
    }

    private String getInput(){
        String ret = theArea.getText();
        return ret;
    }

    private void saveFile(){
        FileDialog fd = new FileDialog(this, "Save File");
        //设置后缀
        fd.setFile("untitled.txt");
        //设置保存模式
        fd.setMode(FileDialog.SAVE);
        fd.setVisible(true);

        //获取文件名
        String fileName = fd.getFile();

        //获取对话框的当前目录
        String dir = fd.getDirectory();

        //创建要保存的目标文件
        File newFile = new File(dir + File.separator + fileName);
        //出于跨平台的考虑
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile)));

            String str = theArea.getText();
            pw.println(str);
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            pw.close();
        }
    }

    public JMenu buildFileMenu(){
        JMenu thefile = new JMenu("Flie");
        thefile.setMnemonic('F');

        JMenuItem newf = new JMenuItem("New", null);
        JMenuItem open = new JMenuItem("Open", null);
        JMenuItem save = new JMenuItem("Save", null);
        JMenuItem exit = new JMenuItem("Exit", null);

        newf.setMnemonic('N');
        open.setMnemonic('O');
        save.setMnemonic('L');
        exit.setMnemonic('X');

        newf.setAccelerator(KeyStroke.getKeyStroke('N',java.awt.Event.CTRL_MASK, false));
        open.setAccelerator(KeyStroke.getKeyStroke('O',java.awt.Event.CTRL_MASK, false));
        save.setAccelerator(KeyStroke.getKeyStroke('L',java.awt.Event.CTRL_MASK, false));
        exit.setAccelerator(KeyStroke.getKeyStroke('X',java.awt.Event.CTRL_MASK, false));

        newf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theArea.append("- MenuItem New Performed -\n");
            }
        });

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theArea.append("- MenuItem Open Performed -\n");
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theArea.append("- MenuItem Close Performed -\n");
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        thefile.add(newf);
        thefile.add(open);
        thefile.add(save);
        thefile.addSeparator();
        thefile.add(exit);

        return thefile;
    }

    public JMenu buildRunMenu(){
        JMenu run = new JMenu("Run");
        run.setMnemonic('R');

        JMenuItem execute = new JMenuItem("Execute", null);
        execute.setMnemonic('C');
        execute.setAccelerator(KeyStroke.getKeyStroke('C',java.awt.Event.CTRL_MASK, false));

        execute.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                theArea.append("- MenuItem Execute Performed -\n");
            }
        });

        run.add(execute);

        return run;
    }

    public JMenu buildVersionMenu(){
        JMenu version = new JMenu("Version");
        version.setMnemonic('V');

        return version;
    }

    public static void main(String[] args) {

        JFrame F = new JMenuItem5();
        F.setSize(400, 200);
        F.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });// end of addWindowListener
        F.setVisible(true);
    } // end of main
}
