import java.io.*; 
import java.net.*; 

public class FTPServer {
    public static void main(String[] args) throws Exception {
        // Create a ServerSocket on port 1024
        ServerSocket ss = new ServerSocket(1024); 
        System.out.println("ServerSocket Generated. Waiting for client...");

        // Accept client connection
        Socket s = ss.accept(); 
        System.out.println("Client connected.");

        // Read filename from server's console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        PrintStream p = new PrintStream(s.getOutputStream()); 

        System.out.print("Enter a File Name: "); 
        String fname = br.readLine(); 
        File f1 = new File(fname); 

        if (f1.exists()) {
            BufferedReader br1 = new BufferedReader(new FileReader(fname)); 
            String str;
            while ((str = br1.readLine()) != null) {
                p.println(str); 
            }
            br1.close(); // close file reader
            System.out.println("File sent successfully.");
        } else {
            p.println("ERROR: File not found.");
            System.out.println("File not found.");
        }

        // Close all resources
        p.close(); 
        s.close(); 
        ss.close(); 
    }
}
