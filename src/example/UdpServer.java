package example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {
    public static void main(String[] args){
        try {
            DatagramSocket socket=new DatagramSocket(9090);
            byte [] incomingData=new byte[1024];
            byte [] outgoingData=new byte[1024];

            while (true){
                DatagramPacket recievePacket=new DatagramPacket(incomingData, incomingData.length);
                socket.receive(recievePacket);
                String recievedData=new String(recievePacket.getData());
                System.out.println("RECEIVED : "+recievedData);

                String response="Hello client!";
                outgoingData=response.getBytes();

                InetAddress clientIp=recievePacket.getAddress();
                int clientPort=recievePacket.getPort();
                DatagramPacket sendPacket=new DatagramPacket(outgoingData, outgoingData.length,clientIp,clientPort);
                socket.send(sendPacket);

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
