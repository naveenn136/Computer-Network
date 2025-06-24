import java.io.*; 
import java.net.*; 

public class FTPClient {
    public static void main(String[] args) throws Exception {
        // Connect to localhost on port 1024
        InetAddress ia = InetAddress.getLocalHost(); 
        Socket s = new Socket(ia, 1024); 

        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.print("Enter a new File Name to save received data: "); 
        String fname = consoleInput.readLine(); 

        // Create a file to write the received data
        PrintWriter fileWriter = new PrintWriter(new FileWriter(fname)); 
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(s.getInputStream())); 

        String str;
        while ((str = serverInput.readLine()) != null) {
            fileWriter.println(str);
        }

        // Clean up
        fileWriter.close(); 
        serverInput.close(); 
        s.close(); 
        System.out.println("File received and saved as '" + fname + "'.");
    }
}
    
