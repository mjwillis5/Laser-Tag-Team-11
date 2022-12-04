//package org.example;
package team11.src;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Hashtable;

public class PlayAction extends Thread{
	//Create frame
	  JScrollPane scroll1;
		JScrollPane scroll2;
		Hashtable<String, Integer> team1_track = new Hashtable<>();
		Hashtable<String, Integer> team2_track = new Hashtable<>();
		boolean update = true;
  	JFrame f = new JFrame("Play Action");
  	int x = 0;
		Statement stmt = null;
		Hashtable<Integer, String> codeAndIndexFor1 = new Hashtable<>();
    Hashtable<Integer, String> codeAndIndexFor2 = new Hashtable<>();
		JLabel labels1[] = new JLabel[15];
		JLabel labels2[] = new JLabel[15];
		int o1= 0;
		int o2=0;
PlayAction (){
	   //[PLAY ACTION SCREEN]
	   //Set background image of frame
		 /*final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		 JTextArea timer = new JTextArea();
		 timer.setBounds(735, 300, 30, 20);
		 timer.setForeground(Color.white);
		 timer.setForeground(Color.black);
        final Runnable runnable = new Runnable() {
				    int preparation = 30;
            int countdownStarter = 360;
            
            public void run() {
							 if(preparation>=0){
                System.out.println(preparation);
								preparation--;
								String ss= String.valueOf(preparation);
								timer.setText(ss);
							 }
                if(preparation<0){
									System.out.println(countdownStarter);
									countdownStarter--;
									String s=String.valueOf(countdownStarter);
									timer.setText(s);
									if (countdownStarter <= 0) {
											System.out.println("Timer Over!");
											update = false;
											scheduler.shutdown();
									}
								}
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);*/
				
				
		 Connection c = null;
		
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
	   team1.setBounds(250, 20, 200, 30);
	   team1.setFont(new Font("default", Font.BOLD, 24));
	   team1.setForeground(Color.white);
		 
	   //Set Label properties for team 2
	   JLabel team2 = new JLabel("Team 2");
	   team2.setBounds(1130, 20, 200, 30);
	   team2.setFont(new Font("default", Font.BOLD, 24));
	   team2.setForeground(Color.white);
	   
	   //Display code name and Score for player 1 in team 1
		 try{
	    ResultSet rs = stmt.executeQuery( "select * from team1" );
			//System.out.printf("Team1\n");
			while ( rs.next() ) {

				//int id = rs.getInt("id");

				String  code_name = rs.getString("codename");
				team1_track.put(code_name, 0);
				codeAndIndexFor1.put(o1,code_name);
				JLabel t1Codename = new JLabel(code_name);
				t1Codename.setBounds(200, 60+x, 200, 30);
				t1Codename.setFont(new Font("default", Font.BOLD, 16));
				t1Codename.setForeground(Color.white);
				
				f.add(t1Codename);
				JLabel t1P1Score = new JLabel(String.valueOf(team1_track.get(code_name)));
				t1P1Score.setBounds(t1Codename.getX()+150, 60+x, 50, 30);
				t1P1Score.setFont(new Font("default", Font.BOLD, 16));
		    t1P1Score.setForeground(Color.white);
				labels1[o1] = t1P1Score;
				f.add(t1P1Score);
				o1++;
				x+=35;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
	  JLabel TotalForTeam1 = new JLabel("Total Point");	
    TotalForTeam1.setBounds(250, 60+x, 200, 30);
    TotalForTeam1.setFont(new Font("default", Font.BOLD, 16));
		TotalForTeam1.setForeground(Color.white);
		f.add(TotalForTeam1);
		JLabel TotalScore = new JLabel(String.valueOf(team1_track.values().stream().mapToInt(Integer::intValue).sum()));
		TotalScore.setBounds(350, 60+x, 100, 30);
		TotalScore.setFont(new Font("default", Font.BOLD, 16));
		TotalScore.setForeground(Color.white);
		f.add(TotalScore);
		x=0;
	   /*JLabel t1Codename = new JLabel("Codename");
	   t1Codename.setBounds(200, 200, 200, 30);
	   t1Codename.setFont(new Font("default", Font.BOLD, 16));
	   t1Codename.setForeground(Color.white);
	   JTextField t1P1Score = new JTextField();
	   t1P1Score.setBounds(t1Codename.getX()+150, t1Codename.getY(), 50, 30);*/
	   
	 //Display code name and Score for player 1 in team 2
	   /*JLabel t2Codename = new JLabel("Codename");
	   t2Codename.setBounds(1075, 200, 200, 30);
	   t2Codename.setFont(new Font("default", Font.BOLD, 16));
	   t2Codename.setForeground(Color.white);
	   JTextField t2P1Score = new JTextField();
	   t2P1Score.setBounds(t2Codename.getX()+150, t2Codename.getY(), 50, 30);*/
	   
		 try{
	    ResultSet rs = stmt.executeQuery( "select * from team2" );
			//System.out.printf("Team1\n");
			while ( rs.next() ) {

				//int id = rs.getInt("id");
        
				String  code_name = rs.getString("codename");
				JLabel t2Codename = new JLabel(code_name);
				team2_track.put(code_name, 0);
        codeAndIndexFor2.put(o2, code_name);
				t2Codename.setBounds(1075, 60+x, 200, 30);
				t2Codename.setFont(new Font("default", Font.BOLD, 16));
				t2Codename.setForeground(Color.white);
				f.add(t2Codename);
				JLabel t2P1Score = new JLabel(String.valueOf(team2_track.get(code_name)));
				t2P1Score.setBounds(t2Codename.getX()+150, 60+x, 100, 30);
				t2P1Score.setFont(new Font("default", Font.BOLD, 16));
				t2P1Score.setForeground(Color.white);
				f.add(t2P1Score);
				labels2[o2] = t2P1Score;
				o2++;
				x+=35;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		JLabel TotalForTeam2 = new JLabel("Total Point");	
    TotalForTeam2.setBounds(1125, 60+x, 200, 30);
    TotalForTeam2.setFont(new Font("default", Font.BOLD, 16));
		TotalForTeam2.setForeground(Color.white);
		f.add(TotalForTeam2);
		JLabel TotalScore2 = new JLabel(String.valueOf(team2_track.values().stream().mapToInt(Integer::intValue).sum()));
		TotalScore2.setBounds(1225, 60+x, 100, 30);
		TotalScore2.setFont(new Font("default", Font.BOLD, 16));
		TotalScore2.setForeground(Color.white);
		f.add(TotalScore2);
	   
	   //Set Text Window to display data 
	   JTextArea tWin1 = new JTextArea();
		 tWin1.setEditable(false);
		 
     //scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	   //tWin1.setBounds(200, 600, 500, 200);
	   tWin1.setForeground(Color.black);
		 scroll1 = new JScrollPane ( tWin1 );
		    scroll1.setBounds(200, 650, 500, 200);
		 String t1 = "";
		 /*try{
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
		}*/
	  /*JTextArea tWin2 = new JTextArea();
	   tWin2.setBounds(800, 600, 500, 200);
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
		}	*/
		JTextArea tWin2 = new JTextArea();
		 tWin2.setEditable(false);
		 
     //scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	   //tWin1.setBounds(200, 600, 500, 200);
	   tWin2.setForeground(Color.black);
		 scroll2 = new JScrollPane ( tWin2 );
		 scroll2.setBounds(800, 650, 500, 200);
		 /*String t2 = "";
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
				scroll2 = new JScrollPane ( tWin2 );
		    scroll2.setBounds(800, 650, 500, 200);
		} catch(SQLException e){
			e.printStackTrace();
		}*/
	   
	   //add elements to the frame
	   f.add(paTitle);
	   f.add(team1);
	   f.add(team2);
	   //f.add(t1Codename);
	   //f.add(t1P1Score);
	   //f.add(t2Codename);
	   //f.add(t2P1Score);
		 f.add(scroll1);
		 f.add(scroll2);
	   //f.add(tWin1);
		 //f.add(tWin2);
		 
	   f.setLayout(null);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setVisible(true);
	   f.setSize(300,300);
	   f.setResizable(true);
	   f.setExtendedState(JFrame.MAXIMIZED_BOTH);
     f.setLocationRelativeTo ( null );
		 final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		 JTextArea timer = new JTextArea();
		 timer.setBounds(735, 300, 30, 20);
		 timer.setForeground(Color.white);
		 timer.setForeground(Color.black);
        final Runnable runnable = new Runnable() {
			int preparation = 30;
            int countdownStarter = 360;

			//Attempts to create a datagram socket at port 7501
			PacketClient packetClient = new PacketClient();
			PacketServer packetServer = new PacketServer();

			public void run() {
							 if(preparation>=0){
                System.out.println(preparation);
								preparation--;
								String ss= String.valueOf(preparation);
								timer.setText(ss);
							 }
                if(preparation<0){
					System.out.println(countdownStarter);
					countdownStarter--;
					String s=String.valueOf(countdownStarter);
					timer.setText(s);
					//team2_track.put("LLL", countdownStarter);
					System.out.println("test1");
					for(int i= 0; i < o1 ; i++){
						labels1[i].setText(String.valueOf(team1_track.get(codeAndIndexFor1.get(i))));
						System.out.println("test2");
					}
					for(int i= 0; i < o2 ; i++){
						labels2[i].setText(String.valueOf(team2_track.get(codeAndIndexFor2.get(i))));
					}
					TotalScore.setText(String.valueOf(team1_track.values().stream().mapToInt(Integer::intValue).sum()));
					TotalScore2.setText(String.valueOf(team2_track.values().stream().mapToInt(Integer::intValue).sum()));
					if(team1_track.values().stream().mapToInt(Integer::intValue).sum() > team2_track.values().stream().mapToInt(Integer::intValue).sum()){
						TotalScore.setText("");
						try{
						Thread.sleep(500);
						} catch(InterruptedException e)
						{
								 // this part is executed when an exception (in this example InterruptedException) occurs
								 System.out.println("error");
						}
						TotalScore.setText(String.valueOf(team1_track.values().stream().mapToInt(Integer::intValue).sum()));
					}else{
					  TotalScore2.setText("");
						try{
						Thread.sleep(500);
						} catch(InterruptedException e)
						{
								 // this part is executed when an exception (in this example InterruptedException) occurs
								 System.out.println("error");
						}
						TotalScore2.setText(String.valueOf(team2_track.values().stream().mapToInt(Integer::intValue).sum()));
					}

					//Creates a simulated datagram packet based on random players in the hashtables
					int shootId;
					int hitId;
					Integer[] teamHash1 = codeAndIndexFor1.keySet().toArray(new Integer[codeAndIndexFor1.size()]);
					Integer[] teamHash2 = codeAndIndexFor2.keySet().toArray(new Integer[codeAndIndexFor2.size()]);
					Hashtable<String, Integer> teamTrackHash;
					Hashtable<Integer, String> teamCodeHash;
					boolean randNum = Math.random() < 0.5;
					if(randNum)
					{
						shootId = teamHash1[(int)(Math.random() * codeAndIndexFor1.size())]; //Index of player sending packet
						hitId = teamHash2[(int)(Math.random() * codeAndIndexFor2.size())]; //Index of player hit by sender
						tWin1.append(codeAndIndexFor1.get(shootId) + " hit " + codeAndIndexFor2.get(hitId) + "\n");
						teamTrackHash = team1_track;
						teamCodeHash = codeAndIndexFor1;
					}
					else
					{
						shootId = teamHash2[(int)(Math.random() * codeAndIndexFor2.size())]; //Index of player sending packet
						hitId = teamHash1[(int)(Math.random() * codeAndIndexFor1.size())]; //Index of player hit by sender
						tWin2.append(codeAndIndexFor2.get(shootId) + " hit " + codeAndIndexFor1.get(hitId) + "\n");
						teamTrackHash = team2_track;
						teamCodeHash = codeAndIndexFor2;
					}

					packetClient.sendPacket(shootId, hitId);

					//Attempts to receive a datagram packet from the client (currently simulated)
					packetServer.receivePacket(teamTrackHash, teamCodeHash);

					/*//Attempts to send a response packet back to the client
					try {
						serverSocket.send(dataPacket);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}*/

					if (countdownStarter <= 0) {
						System.out.println("Timer Over!");
						update = false;
						scheduler.shutdown();
					}
				}
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
				f.add(timer);
  }
}
