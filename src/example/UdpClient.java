package example;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public static void main(String[]args){

        try {
            DatagramSocket clientSocket=new DatagramSocket(0);
            InetAddress serverAddress=InetAddress.getByName("localhost");
            byte [] incomingData=new byte[1024];
            byte [] outgoingData=new byte[1024];
            clientSocket.setSoTimeout(3000);

            String sendData="Hello server!";
            outgoingData=sendData.getBytes();

            DatagramPacket sendPacket=new DatagramPacket(outgoingData, outgoingData.length,serverAddress,9090);
            clientSocket.send(sendPacket);

            DatagramPacket recievePacket=new DatagramPacket(incomingData, incomingData.length);
            clientSocket.receive(recievePacket);
            incomingData=recievePacket.getData();
            String recieveData=new String(incomingData);
            System.out.println("From server: "+recieveData);

            clientSocket.close();

        }catch (SocketTimeoutException e){
            System.out.println("Server is  unreachable, check your connection\n try again!!");
        }
        catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
