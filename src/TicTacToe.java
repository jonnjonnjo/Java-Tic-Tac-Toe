import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class TicTacToe implements ActionListener
{
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;



    TicTacToe()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,100,150));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(150,100,50));
        textField.setForeground(new Color(25,25,25));
        textField.setFont(new Font("Times New Roman", Font.BOLD,75));
        textField.setHorizontalAlignment((JLabel.CENTER));
        textField.setText("Tic-Tac-TOD");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(Color.red);

        for(int i = 0; i < 9;i++)
        {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Comic Sans",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener((this));

        }

        titlePanel.add(textField);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);


        firstTurn();

    }


    public void actionPerformed(ActionEvent e)
    {
        for(int i =0;i < 9;i++)
        {
            if(e.getSource() == buttons[i])
            {
                if(player1Turn)
                {
                    if(buttons[i].getText() == "")
                    {
                        buttons[i].setForeground((Color.blue));
                        buttons[i].setText("X");
                        player1Turn = !player1Turn;
                        textField.setText("O Turn");
                    }
                }else
                {
                    if(buttons[i].getText() == "")
                    {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("O");
                        player1Turn = !player1Turn;
                        textField.setText("X Turn");
                    }
                }
            }
        }

        check();

    }

    public void firstTurn()
    {
        try
        {
            Thread.sleep(1000);
        }catch (Exception e)
        {

        }

        if(random.nextInt(2) == 0)
        {
            player1Turn = true;
            textField.setText("X turn");
        }else
        {
            player1Turn = false;
            textField.setText("O turn");
        }
    }

    public void check()
    {
        // check horizontal 
        for(int i = 0;i < 9;i += 3)
        {
            if(buttons[i].getText() == buttons[i+1].getText() && buttons[i+1].getText() ==  buttons[i+2].getText() && buttons[i].getText() != "")
            {
                textField.setText(buttons[i].getText() + " Wins");
            }
        }

        // check vertical

        for(int i = 0;i < 3;i++)
        {
            if(buttons[i].getText() == buttons[i+3].getText() && buttons[i+3].getText() == buttons[i+6].getText()&& buttons[i].getText() != "")
            {
                textField.setText(buttons[i].getText() + " Wins");
            }
        }

        // check for the diagonal

        if(buttons[0].getText() == buttons[4].getText() && buttons[0].getText() == buttons[8].getText()&& buttons[0].getText() != "")
        {
            textField.setText(buttons[0].getText() + " Wins");
        }
    
        if(buttons[2].getText() == buttons[4].getText() && buttons[6].getText() == buttons[8].getText()&& buttons[2].getText() != "")
        {
            textField.setText(buttons[0].getText() + " Wins");
        }


    }

    public void xWins(int a,int b, int c)
    {

    }

    public void oWins(int a,int b, int c)
    {
        
    }



    
    
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
    }
}