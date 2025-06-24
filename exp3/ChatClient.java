import java.io.*; 
import java.net.*; 
import java.util.*; 

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost at port 6666
            Socket s = new Socket("localhost", 6666); 
            
            // Input stream to receive data from server
            DataInputStream din = new DataInputStream(s.getInputStream()); 
            
            // Output stream to send data to server
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()); 
            
            Scanner input = new Scanner(System.in); 
            String sendData = ""; 
            String receiveData = ""; 
            
            while (!sendData.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: "); 
                sendData = input.nextLine(); 
                dout.writeUTF(sendData); 
                dout.flush(); // ensure data is sent immediately

                // Break if client is exiting before trying to read
                if (sendData.equalsIgnoreCase("stop")) break;

                receiveData = din.readUTF(); 
                System.out.println("SERVER SAYS: " + receiveData); 
            }
            
            // Close all resources
            din.close(); 
            dout.close(); 
            s.close(); 
            input.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); 
        }
    }
}
