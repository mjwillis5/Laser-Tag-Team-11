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


public class Main extends JFrame{
	
  public static void main(String[] args) {
   //Create frame
   JFrame f = new JFrame("Laser Tag");

   //Set size, location, look and feel of frame
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   f.setLocation(0, 0);
   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   f.setDefaultLookAndFeelDecorated(true);

   //[SPLASH SCREEN]
   //Load splash screen
   Image splashImage = null;
   try{
    splashImage = ImageIO.read(new File("graphics/logo.jpg"));

   }catch(IOException e) {
    e.printStackTrace();
   }

   //Resize image and draw it on the screen
   splashImage = splashImage.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
   f.setContentPane(new JLabel(new ImageIcon(splashImage)));

   //Let splash screen display for a bit
   f.setVisible(true);
   try {
    Thread.sleep(3000);
   } catch (InterruptedException e) {
    throw new RuntimeException(e);
   }

   //[PLAYER ENTRY SCREEN]
   //Set background image of frame
   try{
       f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("graphics/LTBackground.jpg")))));

   }catch(IOException e)
   {
       e.printStackTrace();

   }

   //Set Title, location, font, and foreground color
   JLabel title = new JLabel( "Laser Tag");
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
