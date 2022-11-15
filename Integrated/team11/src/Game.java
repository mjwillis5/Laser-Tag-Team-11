//package org.example;//package org.example;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Game {
   static int count4 = 1;
   static int count3=1;
   static int count1;
   static int count2;
   static JLabel countdownTimer = new JLabel();
   public static void main(String args[]) {
      JFrame f = new JFrame("Laser Tag");
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
            "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
            System.out.println("success");
         stmt = c.createStatement();

         ResultSet rs = stmt.executeQuery( "select * from team1" );
         System.out.printf("Team1\n");
         while ( rs.next() ) {

         int id = rs.getInt("id");

         String  first_name = rs.getString("first_name");

         String  last_name = rs.getString("last_name");

         String  codename = rs.getString("codename"); 

         System.out.printf( "id = %s , first_name = %s, last_name = %s, codename = %s ", id,first_name, last_name, codename );

         System.out.println();
         count1 = id;
         }
         ResultSet rt = stmt.executeQuery( "select * from team2" );
         System.out.printf("Team2\n");
         while ( rt.next() ) {

         int id = rt.getInt("id");

         String  first_name = rt.getString("first_name");

         String  last_name = rt.getString("last_name");

         String  codename = rt.getString("codename"); 

         System.out.printf( "id = %s , first_name = %s, last_name = %s, codename = %s ", id,first_name, last_name, codename );

         System.out.println();
         count2 = id;
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
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
   addButton1.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
         try{
            Connection c = null;
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
               "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
               System.out.println("success");
            stmt = c.createStatement();
            String query1= "DELETE FROM team1";
            String query= "INSERT INTO team1 " + "VALUES(?,?,?,?)";
            PreparedStatement myStmt1= c.prepareStatement(query1);
            PreparedStatement myStmt= c.prepareStatement(query);
            myStmt.setInt(1, count1+1);
            myStmt.setString(2, t1Part1.getText());
            myStmt.setString(3, t1Part2.getText());
            myStmt.setString(4, t1Part3.getText());
            count1++;
        // Execute SQL query
            count4--;
            if(count4>=0){
             myStmt1.executeUpdate();
            }
            myStmt.executeUpdate();
            System.out.println("add");
         } catch (Exception x) {
            x.printStackTrace();
            System.err.println(x.getClass().getName()+": "+x.getMessage());
            System.exit(0);
         }
         
      }
   });
   JButton addButton2 = new JButton("Add Player");
   addButton2.setBounds(t2Part3.getX() + 50, t2Part3.getY() + 50, 100, 30);
   addButton2.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
         try{
            Connection c = null;
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
               "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
               System.out.println("success");
            stmt = c.createStatement();
            String query1= "DELETE FROM team2";
            String query= "INSERT INTO team2 " + "VALUES(?,?,?,?)";
            PreparedStatement myStmt1= c.prepareStatement(query1);
            PreparedStatement myStmt= c.prepareStatement(query);
            myStmt.setInt(1, count2+1);
            myStmt.setString(2, t2Part1.getText());
            myStmt.setString(3, t2Part2.getText());
            myStmt.setString(4, t2Part3.getText());
            count2++;
        // Execute SQL query
            count3--;
            if(count3>=0){
             myStmt1.executeUpdate();
            }
            myStmt.executeUpdate();
            System.out.println("add");
         } catch (Exception x) {
            x.printStackTrace();
            System.err.println(x.getClass().getName()+": "+x.getMessage());
            System.exit(0);
         }
         
      }
   });

   //Set up countdown timer
   countdownTimer.setText("");
   countdownTimer.setBounds(550, 0, 1000, 1000);
   countdownTimer.setFont(new Font("default", Font.BOLD, 800));
   countdownTimer.setForeground(Color.white);
   countdownTimer.setVisible(false);

   //Set button to start game
   JButton startGame = new JButton("Start Game");
   //startGame.setBounds(JFrame.MAXIMIZED_BOTH/2, JFrame.MAXIMIZED_BOTH/2, 100, 30);
   startGame.setBounds(700, 650, 100, 30);
   startGame.addActionListener(new ActionListener()
   {
       public void actionPerformed(ActionEvent ae)
       {
          //Countdown timer
          startGame.setVisible(false);
          countdownTimer.setVisible(true);
          countdownTimer.setText("3");
          countdownTimer.paintImmediately(countdownTimer.getVisibleRect());
          f.repaint();
          try {
             Thread.sleep(1000);
          } catch (InterruptedException e) {
             throw new RuntimeException(e);
          }
          countdownTimer.setText("2");
          countdownTimer.paintImmediately(countdownTimer.getVisibleRect());
          f.repaint();
          try {
             Thread.sleep(1000);
          } catch (InterruptedException e) {
             throw new RuntimeException(e);
          }
          countdownTimer.setText("1");
          countdownTimer.paintImmediately(countdownTimer.getVisibleRect());
          f.repaint();
          try {
             Thread.sleep(1000);
          } catch (InterruptedException e) {
             throw new RuntimeException(e);
          }

    	   f.dispose();
    	   PlayAction paFrame = new PlayAction();
       }
   });


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
   f.add(t1FirstName);
   f.add(t1LastName);
   f.add(t1Codename);
   f.add(t2FirstName);
   f.add(t2LastName);
   f.add(t2Codename);
   f.add(addButton1);
   f.add(addButton2);
   f.add(countdownTimer);
   f.setLayout(null);
   f.setVisible(true);
   f.setResizable(true);
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);

  }
   }
   

   

   

   
