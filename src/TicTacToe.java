import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.*;


public class TicTacToe implements ActionListener
{
    JFrame frame;
    JTextField textField;
    JButton[] button = new JButton[9];
    JPanel panel;

    Font myFont = new Font("Times New Roman",Font.BOLD,30);


    TicTacToe()
    {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,500);
        frame.setLayout(null); // we can take full control of the GUI

        textField = new JTextField();
        textField.setBounds(37,25,450,50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(Color.gray);

        for(int i = 0;i < 9;i++)
        {
            button[i] = new JButton();
            button[i].setFont(myFont);
            button[i].addActionListener(this);
        }

        panel = new JPanel();
        //panel.setBounds();
        panel.setBackground(Color.red);


        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {

    }
    public static void main(String[] args)
    {
        TicTacToe m = new TicTacToe();
    }
}