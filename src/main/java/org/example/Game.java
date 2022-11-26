package org.example;//package org.example;

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
   static int count1;
   static int count2;
 //Declaring index for player count
   static int team1Count;
   static int team2Count;
   
   //Declaring variables to store Participants codeNames
   static String[] team1Player = new String[15];
   static String[] team2Player = new String[15];
   
   //Declaring Label array to display codenames
   static JLabel[] team1PlayerL = new JLabel[15];
   static JLabel[] team2PlayerL = new JLabel[15];
   static int yValue;								//Set Y-Value to display codenames
   
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
   
   //Set Label properties for ID & CodeName for team 1
   JLabel t1Title = new JLabel("ID       Code Name");
   t1Title.setBounds(230, 175, 200, 30);
   t1Title.setFont(new Font("default", Font.BOLD, 16));
   t1Title.setForeground(Color.white);

   //Set Label properties for ID & CodeName for team 2
   JLabel t2Title = new JLabel("ID       Code Name");
   t2Title.setBounds(1110, 175, 200, 30);
   t2Title.setFont(new Font("default", Font.BOLD, 16));
   t2Title.setForeground(Color.white);
  
   //Setting Initial Counter Value
   team1Count=0;
   team2Count=0;
   yValue =175;
   
   //Initialize JLabels
   for(int i = 0; i < 15; i++)
   {
	 team1Player[i]="";  
	 yValue = yValue+25;
	 team1Player[i]=null;
     team1PlayerL[i] = new JLabel("");
	 team1PlayerL[i].setBounds(230, yValue, 200, 30);
	 team1PlayerL[i].setFont(new Font("default", Font.BOLD, 16));
	 team1PlayerL[i].setForeground(Color.white);
	 
	 team2Player[i]=null;
	 team2PlayerL[i] = new JLabel("");
	 team2PlayerL[i].setBounds(1110, yValue, 200, 30);
	 team2PlayerL[i].setFont(new Font("default", Font.BOLD, 16));
	 team2PlayerL[i].setForeground(Color.white);
	 }
   
 //Add player to teams
   JLabel cNameHeading = new JLabel("Create new Code Name");
   cNameHeading.setBounds(675, 350, 450, 30);
   cNameHeading.setFont(new Font("default", Font.BOLD, 24));
   cNameHeading.setForeground(Color.white);
   
   //Declaring Radio Button for each Team
   JRadioButton team1AButton = new JRadioButton("Team 1");
   JRadioButton team2AButton = new JRadioButton("Team 2");
   team1AButton.setBounds(675, 400, 75, 30);
   team2AButton.setBounds(825, 400, 75, 30);
   ButtonGroup tName = new ButtonGroup();
   tName.add(team1AButton);
   tName.add(team2AButton);
   
   
   //Create display label
   JLabel cNameLabel = new JLabel("Code Name");
   cNameLabel.setBounds(675, 450, 100, 30);
   cNameLabel.setFont(new Font("default", Font.BOLD, 16));
   cNameLabel.setForeground(Color.white);
   
   //Capture Code Name
   JTextField cName = new JTextField();
   cName.setBounds(825, 450, 150, 30);
   cName.setText(null);
   
   //Display and OK Button
   JButton addButton = new JButton("OK");
   addButton.setBounds(775, 500, 75, 30);
   
   //Add player to teams
   JLabel codeName = new JLabel("Enter Player ID");
   codeName.setBounds(700, 150, 450, 30);
   codeName.setFont(new Font("default", Font.BOLD, 24));
   codeName.setForeground(Color.white);
   
   //Declaring Radio Button for each Team
   JRadioButton team1RButton = new JRadioButton("Team 1");
   JRadioButton team2RButton = new JRadioButton("Team 2");
   team1RButton.setBounds(675, 200, 75, 30);
   team2RButton.setBounds(825, 200, 75, 30);
   ButtonGroup tAName = new ButtonGroup();
   tAName.add(team1RButton);
   tAName.add(team2RButton);
   
   
   //Capture Code Name
   JTextField idField = new JTextField();
   idField.setBounds(675, 250, 100, 30);
   idField.setText(null);
   
   //Display and OK Button
   JButton okButton = new JButton("OK");
   okButton.setBounds(800, 250, 75, 30);
   
   //Label to Display Message
   JLabel eMessage = new JLabel("");
   eMessage.setBounds(650, 300, 450, 30);
   eMessage.setFont(new Font("default", Font.BOLD, 16));
   eMessage.setForeground(Color.white);
   okButton.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e) {
	   eMessage.setText("");
	   if(team1RButton.isSelected() == team2RButton.isSelected()) {
		   eMessage.setText("No team selected. Please select a Team");
		   System.out.println("No team selected.");
	   }
	   else
	   if(idField.getText().isEmpty()) {
		   eMessage.setText("No ID entered. Please enter an ID");
		   System.out.println("ID is Empty");
	   }
	   else       
			try {
			    Integer.parseInt(idField.getText());
			    		System.out.println("is a number");
			} catch (Exception e1) {
			    System.out.println("ID is not a valid number");
			    eMessage.setText("ID is not a valid number. Enter a number");
			}
	   //Capture Table name based on radio button selected.  
	   String tbName = "";
	   if(team1RButton.isSelected()) {
		   tbName = "team1"; 
	   }
	   else if(team2RButton.isSelected()) {
		   tbName = "team2";
	   }

	   System.out.println("Database = " + tbName);
	   int ID = Integer.parseInt(idField.getText());
	   System.out.println("codeName = " + codeName);
	   
  
     try{
         Connection c = null;
         Statement stmt = null;
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
            "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
            System.out.println("success");
         stmt = c.createStatement();
         String query= "Select id, codename from " + tbName + " where id = ?";
         PreparedStatement myStmt= c.prepareStatement(query);
         myStmt.setInt(1, ID);
         System.out.println("Query =  = " + myStmt);
  
     // Execute SQL query
         String codeName = "";
         ResultSet rs = myStmt.executeQuery();
         while (rs.next()) {     
        	 codeName = rs.getString("ID") + "          "
        	 		+ "" + rs.getString("codename");
          } 
         if (codeName == "") {
        	 eMessage.setText("ID " + ID + " does not exist. Please create an ID");
  		   		System.out.println("ID " + ID + " does not exist.");
         }
         else {
	         System.out.println( "codename = " + codeName);
	         if (tbName == "team1") {
	        	 team1Player[team1Count] = codeName;
	        	 team1PlayerL[team1Count].setText(codeName);
	        	 team1Count++;
	        	 //System.out.println("Count " + team1Count);
	         }
	         else if (tbName == "team2") {
	        	 team2Player[team2Count] = codeName;
	        	 team2PlayerL[team2Count].setText(codeName);
	        	 team2Count++;
	         }
         }
          rs.close();
          myStmt.close();  
      } catch (Exception x) {
         x.printStackTrace();
         System.err.println(x.getClass().getName()+": "+x.getMessage());
         System.exit(0);
      }
      
   }
});
   //Code to add new Codename
   JLabel errMessage = new JLabel("");
   errMessage.setBounds(650, 550, 450, 30);
   errMessage.setFont(new Font("default", Font.BOLD, 16));
   errMessage.setForeground(Color.white);
   addButton.addActionListener(new ActionListener(){
	      @Override
	      public void actionPerformed(ActionEvent e) {
	    	  errMessage.setText("");
	    	  if(team1RButton.isSelected() == team2RButton.isSelected()) {
	   		   errMessage.setText("No team selected. Please select a Team");
	   		   System.out.println("No team selected.");
	   	   }
	   	   else
	   	   if(idField.getText().isEmpty()) {
	   		   errMessage.setText("No Codename entered. Please enter a Codename");
	   		   System.out.println("Codename is Empty");
	   	   }
	   	   //Capture Table Name based on radio button selected.  
	   	   String tbName = "";
	   	   if(team1AButton.isSelected()) {
	   		   tbName = "team1"; 
	   	   }
	   	   else if(team2AButton.isSelected()) {
	   		   tbName = "team2";
	   	   }
		   String newCodeName = cName.getText();
		   System.out.println("codeName = " + codeName);
	    	  
	    
	         try{
	            Connection c = null;
	            Statement stmt = null;
	            Class.forName("org.postgresql.Driver");
	            c = DriverManager
	               .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
	               "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
	               System.out.println("success");
	            stmt = c.createStatement();
	            String query= "INSERT INTO " + tbName + " VALUES(?,?,?,?)";
	            PreparedStatement myStmt= c.prepareStatement(query);
	            myStmt.setInt(1, count1+1);
	            myStmt.setString(2, null);
	            myStmt.setString(3, null);
	            myStmt.setString(4, newCodeName);
	            count1++;
	        // Execute SQL query
	            myStmt.executeUpdate();
	            System.out.println("add");
	         } catch (Exception x) {
	            x.printStackTrace();
	            System.err.println(x.getClass().getName()+": "+x.getMessage());
	            System.exit(0);
	         }
	         try{
		            Connection c = null;
		            Statement stmt = null;
		            Class.forName("org.postgresql.Driver");
		            c = DriverManager
		               .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
		               "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
		               System.out.println("success");
		            stmt = c.createStatement();
	         String query= "Select ID from " + tbName + " WHERE codename =?" ;
	         PreparedStatement myStmt= c.prepareStatement(query);
	         myStmt.setString(1, newCodeName);
	         System.out.println("Query =  = " + myStmt);
	         
	         // Execute SQL query
	         int ID = -1;
	         ResultSet rs = myStmt.executeQuery();
	         while (rs.next()) {     
	        	 ID = rs.getInt("ID");
	          } 

        	 errMessage.setText(" New ID " + ID + " was created for Codename " + newCodeName);
        	 cName.setText(null);
  		   		System.out.println("ID + Codename  " + ID + "  " +  newCodeName);
  	          rs.close();
  	          myStmt.close(); 
  	          
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
   startGame.setBounds(750, 650, 100, 30);
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
/*
   for (int i=0; i < 15;  i++) {
	   if(team1PlayerL[i] != null) 
		   System.out.println("team1 player" + team1Player[i]);
	}
   for (int i=0; i < 15;  i++) {
	   if(team1PlayerL[i] != null) 
		   System.out.println("team2 player" + team2Player[i]);
	}*/


   //add elements to the frame
   f.add(title);
   f.add(team1);
   f.add(team2);
   f.add(t1Title);
   f.add(t2Title);
   f.add(idField);
   f.add(codeName);
   f.add(okButton);
   f.add(eMessage);
   f.add(team1RButton);
   f.add(team2RButton);
  for (int i=0; i < 15;  i++) {
	  if(team1PlayerL[i].getText() != null) {
	   f.add(team1PlayerL[i]);
   		}
	  if(team2PlayerL[i].getText() != null) {
		   f.add(team2PlayerL[i]);
	   		}
   }
  f.add(cNameHeading);
  f.add(team1AButton);
  f.add(team2AButton);
  f.add(cNameLabel);
  f.add(cName);
  f.add(addButton);
  f.add(errMessage);
   f.add(startGame);
   f.add(countdownTimer);
   f.setLayout(null);
   f.setVisible(true);
   f.setResizable(true);
   f.setExtendedState(JFrame.MAXIMIZED_BOTH);

  }
   }
   

   

   

   
