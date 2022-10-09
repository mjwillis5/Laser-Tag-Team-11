package org.example;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;


public class LazerTag extends JFrame implements ActionListener{
	
  public static void main(String[] args) {
   JFrame f = new JFrame("Lazer Tag");
   
   //set size, location, and Background image of frame
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   f.setLocation(0, 0);
   try{
       f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("LTBackground.jpg")))));

   }catch(IOException e)
   {
       e.printStackTrace();
    
   }
   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //set look and feel
   f.setDefaultLookAndFeelDecorated(true);
   
   //Set Title, location, font, and foreground color
   JLabel title = new JLabel("Lazer Tag");
   title.setBounds(625, 30, 500, 90);
   title.setFont(new Font("default", Font.BOLD, 64));
   title.setForeground(Color.white);
   
   //Set Label properties for team 1
   JLabel team1 = new JLabel("Team 1");
   team1.setBounds(250, 150, 200, 30);
   team1.setFont(new Font("default", Font.BOLD, 24));
   team1.setForeground(Color.white);
   
   //Set Label properties for team 2
   JLabel team2 = new JLabel("Team 2");
   team2.setBounds(1130, 150, 200, 30);
   team2.setFont(new Font("default", Font.BOLD, 24));
   team2.setForeground(Color.white);
   
   //Set Team 1 participants 
   JTextField t1Part1 = new JTextField();
   t1Part1.setBounds(200, 200, 200, 30);
  
   JTextField t1Part2 = new JTextField();
   t1Part2.setBounds(200, 250, 200, 30);
   
   JTextField t1Part3 = new JTextField();
   t1Part3.setBounds(200, 300, 200, 30);
   
   
   //Set Team 2 participants 
   JTextField t2Part1 = new JTextField();
   t2Part1.setBounds(1075, 200, 200, 30);
  
   JTextField t2Part2 = new JTextField();
   t2Part2.setBounds(1075, 250, 200, 30);
   
   JTextField t2Part3 = new JTextField();
   t2Part3.setBounds(1075, 300, 200, 30);
   
   
   
   
   //Set button to start game
   JButton startGame = new JButton("Start Game");
   //startGame.setBounds(JFrame.MAXIMIZED_BOTH/2, JFrame.MAXIMIZED_BOTH/2, 100, 30);
   startGame.setBounds(700, 650, 100, 30);
   
   
   
   //add elements to the frame
   f.add(title);
   f.add(team1);
   f.add(team2);
   f.add(t1Part1);
   f.add(t1Part2);
   f.add(t1Part3);
   f.add(t2Part1);
   f.add(t2Part2);
   f.add(t2Part3);
   f.add(startGame);
   f.setLayout(null);
   f.setVisible(true);
   f.setResizable(true);
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   
  }
  
  
}
