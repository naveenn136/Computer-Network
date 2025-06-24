import java.io.*; 
import java.net.*; 
import java.util.*; 

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost and port 6666
            Socket s = new Socket("localhost", 6666); 
            
            // Input and output streams
            DataInputStream din = new DataInputStream(s.getInputStream()); 
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()); 
            Scanner input = new Scanner(System.in); 

            String sendData = ""; 
            String receiveData = ""; 

            // Chat loop
            while (!sendData.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: "); 
                sendData = input.nextLine(); 
                dout.writeUTF(sendData); 
                dout.flush(); // ensure data is sent immediately

                if (sendData.equalsIgnoreCase("stop")) {
                    break;
                }

                receiveData = din.readUTF(); 
                System.out.println("SERVER SAYS: " + receiveData); 
            }

            // Clean up
            input.close();
            din.close(); 
            dout.close(); 
            s.close(); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); 
        }
    }
}
