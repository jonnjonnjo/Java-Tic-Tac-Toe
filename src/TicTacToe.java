import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.imageio.*;
import java.awt.image.*;


public class TicTacToe implements ActionListener
{
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;
    boolean finished = false;
    Font myFont = null;
    AudioInputStream audioInputStream = null;
    Clip clip;

    BufferedImage buffButtonIcon = null;
    ImageIcon buttonIcon = null;


    TicTacToe()
    {
        try
        {
            myFont = Font.createFont(Font.TRUETYPE_FONT,new File("../font/ARCADECLASSIC.TTF"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myFont);
            audioInputStream = AudioSystem.getAudioInputStream(new File("../music/intro.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e)
        {
        }
        
;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,100,150));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(150,100,50));
        textField.setForeground(new Color(25,25,25));
        textField.setFont(myFont.deriveFont(Font.BOLD,75));
        textField.setHorizontalAlignment((JLabel.CENTER));
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(Color.red);

        try{
        buffButtonIcon = ImageIO.read(new File("../img/default.png"));
        buttonIcon = new ImageIcon(buffButtonIcon);
        } catch(Exception e)
        {

        }


        for(int i = 0; i < 9;i++)
        {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Comic Sans",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener((this));
           // buttons[i].setIcon(buttonIcon);
            
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
            Thread.sleep(2000 );
        }catch (Exception e)
        {

        }

        if(random.nextInt(2) == 0)
        {
            player1Turn = true;
            textField.setText("X   turn");
        }else
        {
            player1Turn = false;
            textField.setText("O   turn");
        }
    }

    public void check()
    {
        // check horizontal 
        for(int i = 0;i < 9;i += 3)
        {
            if(buttons[i].getText() == buttons[i+1].getText() && buttons[i+1].getText() ==  buttons[i+2].getText() && buttons[i].getText() != "")
            {
                textField.setText(buttons[i].getText() + "   Wins");
                if(buttons[i].getText() == "X")
                {   
                    xWins(i,i+1,i+2);
                }else oWins(i,i+1,i+2);
                finished = true;
            }
        }

        // check vertical

        for(int i = 0;i < 3;i++)
        {
            if(buttons[i].getText() == buttons[i+3].getText() && buttons[i+3].getText() == buttons[i+6].getText()&& buttons[i].getText() != "")
            {
                textField.setText(buttons[i].getText() + "   Wins");

                if(buttons[i].getText() == "X")
                {   
                    xWins(i,i+3,i+6);
                }else oWins(i,i+3,i+6);

                finished = true;
            }
        }

        // check for the diagonal

        if(buttons[0].getText() == buttons[4].getText() && buttons[0].getText() == buttons[8].getText()&& buttons[0].getText() != "")
        {
            textField.setText(buttons[0].getText() + "   Wins");
            if(buttons[0].getText() == "X")
            {   
                xWins(0,4,8);
            }else oWins(0,4,8);

            finished = true;
        }
    
        if(buttons[2].getText() == buttons[4].getText() && buttons[6].getText() == buttons[2].getText()&& buttons[2].getText() != "")
        {
            textField.setText(buttons[0].getText() + "   Wins");

            if(buttons[2].getText() == "X")
            {   
                xWins(2,4,6);
            }else oWins(2,4,6);

            finished = true;
        }

        int cnt = 0;
        for(int i =0;i < 9;i++)
        {
            if(buttons[i].getText() != "") cnt++;
        }

        if(cnt == 9 && !finished)
        {
            for(int i = 0;i < 9;i++) buttons[i].setEnabled(false);
            textField.setText("Draw");
        }


    }

    public void xWins(int a,int b, int c)
    {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for(int i = 0;i < 9;i++)
        {
            buttons[i].setEnabled(false);
        }

        textField.setText("X   Wins");

    }

    public void oWins(int a,int b, int c)
    {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for(int i = 0;i < 9;i++)
        {
            buttons[i].setEnabled(false);
        }

        textField.setText("O   Wins");       
        
    }



    
    
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
    }
}