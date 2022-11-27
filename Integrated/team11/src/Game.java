//package org.example;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PlayAction{
	
	//Create frame
  	JFrame f = new JFrame("Play Action");
  	
PlayAction (){


	   //[PLAY ACTION SCREEN]
	   //Set background image of frame
		 final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		 JTextArea timer = new JTextArea();
		 timer.setBounds(735, 300, 30, 20);
		 timer.setForeground(Color.white);
		 timer.setForeground(Color.black);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 360;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;
								String s=String.valueOf(countdownStarter);
                timer.setText(s);
                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
				
				
		 Connection c = null;
		 Statement stmt = null;
		 try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
				 .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
				 "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
				 System.out.println("success");
			stmt = c.createStatement();

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName()+": "+e.getMessage());
				System.exit(0);

		 }
	   try{
	       f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("graphics/LTBackground.jpg")))));
	
	   }catch(IOException e)
	   {
	       e.printStackTrace();
	
	   }
	   
	 //Set Title, location, font, and foreground color
	   JLabel paTitle = new JLabel( "Scores");
	   paTitle.setBounds(625, 30, 500, 90);
	   paTitle.setFont(new Font("default", Font.BOLD, 64));
	   paTitle.setForeground(Color.white);
	
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
	   
	   //Display code name and Score for player 1 in team 1
	   JLabel t1Codename = new JLabel("Codename");
	   t1Codename.setBounds(200, 200, 200, 30);
	   t1Codename.setFont(new Font("default", Font.BOLD, 16));
	   t1Codename.setForeground(Color.white);
	   JTextField t1P1Score = new JTextField();
	   t1P1Score.setBounds(t1Codename.getX()+150, t1Codename.getY(), 50, 30);
	   
	 //Display code name and Score for player 1 in team 2
	   JLabel t2Codename = new JLabel("Codename");
	   t2Codename.setBounds(1075, 200, 200, 30);
	   t2Codename.setFont(new Font("default", Font.BOLD, 16));
	   t2Codename.setForeground(Color.white);
	   JTextField t2P1Score = new JTextField();
	   t2P1Score.setBounds(t2Codename.getX()+150, t2Codename.getY(), 50, 30);
	   
	   
	   //Set Text Window to display data 
	   JTextArea tWin1 = new JTextArea();
	   tWin1.setBounds(200, 400, 500, 400);
	   tWin1.setForeground(Color.black);
		 String t1 = "";
		 try{
	    ResultSet rs = stmt.executeQuery( "select * from team1" );
			//System.out.printf("Team1\n");
			while ( rs.next() ) {

				//int id = rs.getInt("id");

				String  first_name = rs.getString("first_name");

				String  last_name = rs.getString("last_name");
				t1 += first_name + " " + last_name + "\n";
				}
				tWin1.setText(t1);
		} catch(SQLException e){
			e.printStackTrace();
		}
	  JTextArea tWin2 = new JTextArea();
	   tWin2.setBounds(800, 400, 500, 400);
	   tWin2.setForeground(Color.black);
		 String t2 = "";
		 try{
	    ResultSet rs = stmt.executeQuery( "select * from team2" );
			//System.out.printf("Team1\n");
			while ( rs.next() ) {

				//int id = rs.getInt("id");

				String  first_name = rs.getString("first_name");

				String  last_name = rs.getString("last_name");
				t2 += first_name + " " + last_name + "\n";
				}
				tWin2.setText(t2);
		} catch(SQLException e){
			e.printStackTrace();
		}	
	   
	   //add elements to the frame
	   f.add(paTitle);
	   f.add(team1);
	   f.add(team2);
	   f.add(t1Codename);
	   f.add(t1P1Score);
	   f.add(t2Codename);
	   f.add(t2P1Score);
	   f.add(tWin1);
		 f.add(tWin2);
		 f.add(timer);
	   f.setLayout(null);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setVisible(true);
	   f.setSize(300,300);
	   f.setResizable(true);
	   f.setExtendedState(JFrame.MAXIMIZED_BOTH);

  }
		  
}
