import java.awt.*;
import javax.swing.*;
import javax.swing.text.TextAction;

import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.imageio.*;
import java.awt.image.*;


public class TicTacToe implements ActionListener
{
    Random random = new Random();
    JFrame frame = new JFrame("Jon's TicTacToe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    ColorfulIconButton [] buttons = new ColorfulIconButton[9];
    boolean player1Turn;
    boolean finished = false;
    Font myFont = null;
    AudioInputStream audioInputStream = null;
    Clip clip;

    char[] text = new char[9];



    BufferedImage[] bufferedImage = new BufferedImage[5];
    ImageIcon[] buttonIcon = new ImageIcon[5];


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


            bufferedImage[0] = ImageIO.read(new File("../img/blueo.png"));
            buttonIcon[0] = new ImageIcon(bufferedImage[0]);
            bufferedImage[1] = ImageIO.read(new File("../img/bluex.PNG"));
            buttonIcon[1] = new ImageIcon(bufferedImage[1]);
            bufferedImage[2] = ImageIO.read(new File("../img/default.PNG"));
            buttonIcon[2] = new ImageIcon(bufferedImage[2]);
            bufferedImage[3] = ImageIO.read(new File("../img/greeno.PNG"));
            buttonIcon[3] = new ImageIcon(bufferedImage[3]);
            bufferedImage[4] = ImageIO.read(new File("../img/greenx.PNG"));
            buttonIcon[4] = new ImageIcon(bufferedImage[4]);
        }catch(Exception e)
        {
        }
        
        
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




        for(int i = 0; i < 9;i++)
        {
            buttons[i] = new ColorfulIconButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Comic Sans",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener((this));
            buttons[i].setEnabled(false);
            text[i] = ' ';
            
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
                    if(text[i]== ' ')
                    {
                       // buttons[i].setText("X");
                        text[i] = 'X';
                        int buttonWidth = buttons[i].getWidth();
                        int buttonHeight = buttons[i].getHeight();
                        Image temp = buttonIcon[1].getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
                        ImageIcon temp2 = new ImageIcon(temp);
                        buttons[i].setForeground(null);
                        buttons[i].setIcon(temp2);
                        player1Turn = !player1Turn;
                        textField.setText("O Turn");
                    }
                }else
                {
                    if(text[i] == ' ')
                    {
                        text[i] = 'O';
                        int buttonWidth = buttons[i].getWidth();
                        int buttonHeight = buttons[i].getHeight();
                        Image temp = buttonIcon[0].getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
                        ImageIcon temp2 = new ImageIcon(temp);
                        buttons[i].setForeground(null);
                        buttons[i].setIcon(temp2);
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

        for(int i =0;i< 9;i++) buttons[i].setEnabled(true);
    }

    public void check()
    {
        // check horizontal 
        for(int i = 0;i < 9;i += 3)
        {
            if(text[i] == text[i+1] && text[i+1] ==  text[i+2]&& text[i] != ' ')
            {
                textField.setText(text[i]+ "   Wins");
                if(text[i]== 'X')
                {   
                    xWins(i,i+1,i+2);
                }else oWins(i,i+1,i+2);
                finished = true;
            }
        }

        // check vertical

        for(int i = 0;i < 3;i++)
        {
            if(text[i] == text[i+3] && text[i+3] == text[i+6]&& text[i] != ' ')
            {
                textField.setText(text[i] + "   Wins");

                if(text[i] == 'X')
                {   
                    xWins(i,i+3,i+6);
                }else oWins(i,i+3,i+6);

                finished = true;
            }
        }

        // check for the diagonal

        if(text[0] == text[4] && text[0] == text[8]&& text[0] != ' ')
        {
            textField.setText(text[0] + "   Wins");
            if(text[0]== 'X')
            {   
                xWins(0,4,8);
            }else oWins(0,4,8);

            finished = true;
        }
    
        if(text[2] == text[4] && text[6] == text[2]&& text[2] != ' ')
        {
            textField.setText(text[0] + "   Wins");

            if(text[2] == 'X')
            {   
                xWins(2,4,6);
            }else oWins(2,4,6);

            finished = true;
        }

        int cnt = 0;
        for(int i =0;i < 9;i++)
        {
            if(text[i] != ' ') cnt++;
        }

        if(cnt == 9 && !finished)
        {
            for(int i = 0;i < 9;i++) buttons[i].setEnabled(false);
            textField.setText("Draw");
        }


    }

    public void xWins(int a,int b, int c)
    {
        int buttonWidth = buttons[a].getWidth();
        int buttonHeight = buttons[a].getHeight();
        Image temp = buttonIcon[4].getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon temp2 = new ImageIcon(temp);
        buttons[a].setForeground(null);
        buttons[a].setIcon(temp2);
        buttons[b].setIcon(temp2);
        buttons[c].setIcon(temp2);
        textField.setText("X   Wins");


        for(int i = 0;i < 9;i++)
        {
            buttons[i].setEnabled(false);
        }


    }

    public void oWins(int a,int b, int c)
    {

        for(int i = 0;i < 9;i++)
        {
            buttons[i].setEnabled(false);
        }
        int buttonWidth = buttons[a].getWidth();
        int buttonHeight = buttons[a].getHeight();
        Image temp = buttonIcon[3].getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon temp2 = new ImageIcon(temp);
        buttons[a].setForeground(null);
        buttons[a].setIcon(temp2);
        buttons[b].setIcon(temp2);
        buttons[c].setIcon(temp2);
        textField.setText("O   Wins");       
        
    }



    
    
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
    }
}