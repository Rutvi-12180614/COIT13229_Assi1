package coit132229_applied_distributed_systems_ass1;
import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {
       
        int port = 2214; 

        try {
            // Create a socket to communicate with the server
            DatagramSocket oDatagramSocket = new DatagramSocket();

            // Specify the IP address and port of the server
            InetAddress oInetAddress = InetAddress.getByName("localhost");

            // Prepare the request data
            byte[] DataMember = "memberlistObject".getBytes();
            byte[] getBufferData = new byte[1024];

            // Create a packet to send the request to the server
            DatagramPacket sendDatagramPacket = new DatagramPacket(DataMember, DataMember.length, oInetAddress, port);
            oDatagramSocket.send(sendDatagramPacket);

            // Create a packet to receive the response from the server
            DatagramPacket receiveDatagramPacket = new DatagramPacket(getBufferData, getBufferData.length);
            oDatagramSocket.receive(receiveDatagramPacket);

            // Extract the response data and display it
            String serverResponse = new String(receiveDatagramPacket.getData());
            System.out.println("Server Response:\n" + serverResponse.trim());

            // Close the socket
            oDatagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
