package org.example;

import java.io.IOException;
import java.util.random.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Trafficgenerator {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
	//Query for all users in Team tables
		int[] t1PlayerID = new int[15];
		int[] t2PlayerID = new int[15];
		String[] t1PlayerCN = new String[15];
		String[] t2PlayerCN = new String[15];
		
		for (int i=0; i<15; i++) {
			t1PlayerID[i]=0;
			t1PlayerCN[i]=null;
			
			t2PlayerID[i]=0;
			t2PlayerCN[i]=null;
		}
		
		 Connection c = null;
	      Statement stmt = null;
	      int count=0;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
	            "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
	            //System.out.println("success");
	         stmt = c.createStatement();

	         ResultSet rs = stmt.executeQuery( "select * from team1" );
	         System.out.printf("Team1\n");
	         while ( rs.next() ) {
	        		 t1PlayerID[count] = rs.getInt("id");
	    	         t1PlayerCN[count] = rs.getString("codename"); 
	    	         System.out.println(" Team 1 player info" + t1PlayerID[count]+ "  " + t1PlayerCN[count] );
	    	         count++;
	        	 }
	         count = 0;
	         
	         ResultSet rt = stmt.executeQuery( "select * from team2" );
	         //System.out.printf("Team2\n");
	         while ( rt.next() ) {
	        	 t2PlayerID[count] = rt.getInt("id");
    	         t2PlayerCN[count] = rt.getString("codename"); 
    	         System.out.println(" Team 2 player info" + t2PlayerID[count]+ "  " + t2PlayerCN[count] );
    	         count++;
	         } 
 	          rs.close();
 	          stmt.close(); 
 	          count=0;
	      }  catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }	

		// Create Datagram the socket object to send data.
		DatagramSocket ds = null;
		
		try {
			ds = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Error getting local address");
			e.printStackTrace();
		}
		byte buf[] = null;

		// loop while user not enters "bye"

		// counter number of events, random player and order send data
		int counter=100; 		//generates 100 traffic
		int t1Min=1;
		int t1Max=0;
		int t2Min=1;
		int t2Max = 0;
		for (int i=0; i<15; i++) {
				if(t1PlayerCN[i] != null) t1Max++;
				if(t2PlayerCN[i] != null) t2Max++;
		}

		System.out.println(t1Max + " " + t2Max);

		int i=0;
		String inp=null;
		while (i < counter)
		{
			int t1random = ThreadLocalRandom.current().nextInt(t1Min, t1Max+1);
			System.out.println("Random t1Player" + t1random);
			int t2random =ThreadLocalRandom.current().nextInt(t2Min, t2Max+1);
			System.out.println("Random t2Player" + t2random);
			
			int result = new Random().nextBoolean() ? t1random : t2random;
			if (result == t1random) {
				inp = t1PlayerCN[t1random-1] + " : " + t2PlayerCN[t2random-1];
			}
			else
				inp = t2PlayerCN[t2random-1] + " : " + t1PlayerCN[t1random-1];
			
			System.out.println("Sending data " + inp);
			

			// convert the String input into the byte array.
			buf = inp.getBytes();

			// Step 2 : Create the datagramPacket for sending
			// the data.
			DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);

			// Step 3 : invoke the send call to actually send
			// the data.
			try {
				ds.send(DpSend);
			} catch (IOException e) {
				System.out.println("Error sending datat");
				e.printStackTrace();
			}

			// break the loop if user enters "bye"
			if (inp.equals("bye"))
				break;
			i++;
		}
	}
}
