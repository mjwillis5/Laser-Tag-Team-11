package team11.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Hashtable;

public class PacketServer extends Thread {
    //Creates a buffer to be used with datagram socket
    private DatagramSocket serverSocket;

    PacketServer()
    {
        try {
            serverSocket = new DatagramSocket(7501);
            serverSocket.setBroadcast(true);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void receivePacket(Hashtable<String, Integer> teamTrackHash, Hashtable<Integer, String> teamCodeHash)
    {
        byte[] receiveBuffer = new byte[256];
        DatagramPacket dataPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            serverSocket.receive(dataPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Uses the received packet's data to attempt to store said data into an integer
        DataInputStream dataInput = new DataInputStream(new ByteArrayInputStream(dataPacket.getData()));
        int playerHit;
        int playerShoot;
        try {
            playerShoot = new Integer(dataInput.readInt());
            //playerHit = new Integer(dataInput.readInt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Updates hashtable with new values
        String key = teamCodeHash.get(playerShoot);
        teamTrackHash.replace(key, teamTrackHash.get(key) + 10);
    }

    public void closeServer()
    {
        serverSocket.close();
    }
}
