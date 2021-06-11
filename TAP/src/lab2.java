import java.awt.*;
import java.awt.event.*;
import java.lang.annotation.*;
import java.util.*;
import javax.annotation.processing.Generated;
import javax.swing.*;

public class lab2 {
    public static void main(String[] arg){
        MyFrame ff = new MyFrame();
        ff.setVisible(true);
    }}
class MyFile implements Generated {
    public MyFile(){
        this.setFile();
    }
    public MyFile(String cCom, String uUser){
        this.setFile(cCom);
    }
    public void setFile(){
        com = "Indicati un comentariul";

        dat = new Date();
    }
    public void setFile(String cCom){
        com = cCom;
        dat = new Date();
    }
    public String date() {
        return dat.toString();
    }
    public String comments() {
        return com;
    }

    public String getFile(){
        String output = null;
        output += "Text: " + com + "\n";
        output += "Data: " + date() + "\n";
        output += "==========================\n";
        return output;
    }
    private String com;
    private Date dat;

    public Class <? extends Annotation>  annotationType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String[] value() {
        throw new UnsupportedOperationException(" Not supported yet.");
    }}
class MyFrame extends JFrame {
    public MyFrame(){
        this.setTitle("LBJ2");
        this.setSize(700, 400);
        this.setLocation(300, 300);        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        ts = new MyFile();

        left = new JPanel();
        Box b1 = Box.createVerticalBox();
        JButton but = new JButton("Submit");
        but.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.out.println("aa");                ts.setFile(com.getText());
                        dat.setText("Data: "+ts.date());                history.setText(history.getText()+
                                ts.getFile());
                    }  });
        dat = new JLabel("Data: "+ts.date());
        b1.add(dat);
        b1.add(but);
        left.add(b1);
        this.add(left,BorderLayout.WEST);
        center = new JPanel();
        com = new JTextArea(ts.comments(),20,20);
        center.add(com);
        center.setBackground(Color.red);        this.add(center,BorderLayout.CENTER);
        right = new JPanel();
        history = new JTextArea(ts.getFile(),20,20);
        right.add(history);
        this.add(right,BorderLayout.EAST);
    }
    private JTextArea history;
    private JPanel left, center, right;
    private JTextField user;
    private JTextArea com;
    private JLabel dat;
    private MyFile ts;
}
