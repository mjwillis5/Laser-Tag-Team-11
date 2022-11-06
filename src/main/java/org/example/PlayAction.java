package org.example;

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

public class PlayAction{
	
	//Create frame
  	JFrame f = new JFrame("Play Action");
  	
PlayAction (){


	   //[PLAY ACTION SCREEN]
	   //Set background image of frame
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
	   
	   //Set Text Window to display data 
	   JTextField tWin = new JTextField();
	   tWin.setBounds(200, 400, 1100, 400);
	   tWin.setForeground(Color.white);
	   
	   //add elements to the frame
	   f.add(paTitle);
	   f.add(team1);
	   f.add(team2);
	   f.add(tWin);
	   f.setLayout(null);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setVisible(true);
	   f.setSize(300,300);
	   f.setResizable(true);
	   f.setExtendedState(JFrame.MAXIMIZED_BOTH);

  }
		  
}
