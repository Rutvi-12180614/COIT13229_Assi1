package coit132229_applied_distributed_systems_ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 1114)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            int memberNumber = 1;
            while (true) {
                System.out.println("Enter member details for member number: " + memberNumber);

                Member member = new Member("", "", "", "");
                System.out.print("Enter First Name: ");
                member.setFirstName(scanner.next());
                System.out.print("Enter Last Name: ");
                member.setLastName(scanner.next());
                System.out.print("Enter Address: ");
                member.setAddress(scanner.next());
                System.out.print("Enter Phone Number: ");
                member.setPhoneNumber(scanner.next());

                String memberDetails = member.getdataFirstName() + ":" + member.getdataLastName() + ":" + member.getdataAddress() + ":" + member.getdataPhoneNumber();
                System.out.println("Sending data to server..........");
                System.out.println(memberDetails);
                outputStream.writeUTF(memberDetails); // Send the member details
               
                
                try{
                    String response = inputStream.readUTF();
                    System.out.println("Server Response: " + response + memberNumber);

                } catch (IOException ex) {
                  
                }
                memberNumber++;
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }
}
