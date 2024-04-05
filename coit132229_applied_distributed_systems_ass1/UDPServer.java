package coit132229_applied_distributed_systems_ass1;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class UDPServer {
    public static void main(String args[]) {
       
        int port = 2214; 
        try {
            // Create a socket to listen for requests from clients
            DatagramSocket serverDatagramSocket= new DatagramSocket(port);

            while (true) {
                byte[] receiveDatagramSocketData = new byte[1024];
                byte[] sendreceiveDatagramSocketData;

                // Create a packet to receive the request from the client
                DatagramPacket receiveDatagramPacket = new DatagramPacket(receiveDatagramSocketData, receiveDatagramSocketData.length);
                serverDatagramSocket.receive(receiveDatagramPacket);

                // Extract the request data
                String storeRequest = new String(receiveDatagramPacket.getData()).trim();
                System.out.println("Client Request: " + storeRequest);

                // Read the member list object from the file
                String mDetails = readMemberListObject(storeRequest);

                // Send the member details back to the client
                InetAddress clientIPAddress = receiveDatagramPacket.getAddress();
                int clientPort = receiveDatagramPacket.getPort();
                sendreceiveDatagramSocketData = mDetails.getBytes();
                DatagramPacket sendDatagramPacket = new DatagramPacket(sendreceiveDatagramSocketData, sendreceiveDatagramSocketData.length, clientIPAddress, clientPort);
                serverDatagramSocket.send(sendDatagramPacket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private static String readMemberListObject(String fileName)
    {
            StringBuilder sbDetails = new StringBuilder();
            sbDetails.append("|First Name\t|Last Name\t|Address\t\t\t|Phone Number\n");

            try {
                FileInputStream fis = new FileInputStream("D:\\Data\\memberlistObject.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fis);


                ArrayList<Member> ALMembers = (ArrayList<Member>) objectInputStream.readObject();

                for (Member mb : ALMembers) {
                    sbDetails.append("|")
                                  .append(mb.getdataFirstName())
                                  .append("\t\t|")
                                  .append(mb.getdataLastName())
                                  .append("\t\t|")
                                  .append(mb.getdataAddress())
                                  .append("\t\t|")
                                  .append(mb.getdataPhoneNumber())
                                  .append("\n");
                }

                objectInputStream.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return sbDetails.toString();
}
  
}
