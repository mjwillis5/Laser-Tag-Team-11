package org.example;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
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
   JLabel t1FirstName = new JLabel("First Name");
   t1FirstName.setBounds(t1Part1.getX() - 100, t1Part1.getY(), 200, 25);
   t1FirstName.setFont(new Font("default", Font.BOLD, 16));
   t1FirstName.setForeground(Color.white);

   JTextField t1Part2 = new JTextField();
   t1Part2.setBounds(200, 250, 200, 30);
   JLabel t1LastName = new JLabel("Last Name");
   t1LastName.setBounds(t1Part2.getX() - 100, t1Part2.getY(), 200, 25);
   t1LastName.setFont(new Font("default", Font.BOLD, 16));
   t1LastName.setForeground(Color.white);

   JTextField t1Part3 = new JTextField();
   t1Part3.setBounds(200, 300, 200, 30);
   JLabel t1Codename = new JLabel("Codename");
   t1Codename.setBounds(t1Part3.getX() - 100, t1Part3.getY(), 200, 25);
   t1Codename.setFont(new Font("default", Font.BOLD, 16));
   t1Codename.setForeground(Color.white);


   //Set Team 2 participants
   JTextField t2Part1 = new JTextField();
   t2Part1.setBounds(1075, 200, 200, 30);
   JLabel t2FirstName = new JLabel("First Name");
   t2FirstName.setBounds(t2Part1.getX() - 100, t2Part1.getY(), 200, 25);
   t2FirstName.setFont(new Font("default", Font.BOLD, 16));
   t2FirstName.setForeground(Color.white);

   JTextField t2Part2 = new JTextField();
   t2Part2.setBounds(1075, 250, 200, 30);
   JLabel t2LastName = new JLabel("Last Name");
   t2LastName.setBounds(t2Part2.getX() - 100, t2Part2.getY(), 200, 25);
   t2LastName.setFont(new Font("default", Font.BOLD, 16));
   t2LastName.setForeground(Color.white);

   JTextField t2Part3 = new JTextField();
   t2Part3.setBounds(1075, 300, 200, 30);
   JLabel t2Codename = new JLabel("Codename");
   t2Codename.setBounds(t2Part3.getX() - 100, t2Part3.getY(), 200, 25);
   t2Codename.setFont(new Font("default", Font.BOLD, 16));
   t2Codename.setForeground(Color.white);

   JButton addButton1 = new JButton("Add Player");
   addButton1.setBounds(t1Part3.getX() + 50, t1Part3.getY() + 50, 100, 30);

   JButton addButton2 = new JButton("Add Player");
   addButton2.setBounds(t2Part3.getX() + 50, t2Part3.getY() + 50, 100, 30);

   //Set button to start game
   JButton startGame = new JButton("Start Game");
   startGame.setBounds(700, 650, 100, 30);
   //Add Action Listener to start play action 
   startGame.addActionListener(new ActionListener()
   {
       public void actionPerformed(ActionEvent ae)
       {
    	   f.dispose();
    	   PlayAction paFrame = new PlayAction();
       }
   });



   //add elements to the frame
   f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
   f.add(t1FirstName);
   f.add(t1LastName);
   f.add(t1Codename);
   f.add(t2FirstName);
   f.add(t2LastName);
   f.add(t2Codename);
   f.add(addButton1);
   f.add(addButton2);
   f.setLayout(null);
   f.setVisible(true);
   f.setResizable(true);
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);
   
   

  
  }
  
}

