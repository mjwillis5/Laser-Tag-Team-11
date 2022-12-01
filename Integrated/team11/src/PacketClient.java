package team11.src;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class PacketClient extends Thread {
    private byte[] sendBuffer;
    private DatagramSocket clientSocket;
    private InetAddress sendAddress;

    PacketClient()
    {
        try {
            clientSocket = new DatagramSocket(7500, InetAddress.getLocalHost());
            sendAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPacket(int shootKey, int hitKey) {
        //Sends said datagram packet to the server (basically simulates a client sending a packet)
        ByteBuffer sendByteBuffer = ByteBuffer.allocate(8);
        sendByteBuffer.asIntBuffer().put(new int[] {shootKey, hitKey});
        byte[] sendBuffer = sendByteBuffer.array();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, sendAddress, 7501);
        try {
            clientSocket.send(sendPacket);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeClient()
    {
        clientSocket.close();
    }
}
