import java.io.*;
import java.net.*; 
import java.util.*; 

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Start the server on port 6666
            ServerSocket ss = new ServerSocket(6666); 
            System.out.println("Server is running... Waiting for client connection...");

            Socket s = ss.accept(); 
            System.out.println("Client connected!");

            // Input and Output Streams
            DataInputStream din = new DataInputStream(s.getInputStream()); 
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()); 
            Scanner input = new Scanner(System.in); 

            String sendData = ""; 
            String receiveData = ""; 

            while (!receiveData.equalsIgnoreCase("stop")) {
                receiveData = din.readUTF(); 
                System.out.println("CLIENT SAYS: " + receiveData); 

                // Break if client sent stop
                if (receiveData.equalsIgnoreCase("stop")) break;

                System.out.print("TO CLIENT: "); 
                sendData = input.nextLine(); 
                dout.writeUTF(sendData); 
                dout.flush(); 
            }

            // Close resources
            din.close(); 
            dout.close(); 
            s.close(); 
            ss.close(); 
            input.close();
            System.out.println("Server stopped.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); 
        }
    }
}
