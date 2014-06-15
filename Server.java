
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss;
	
	private void createServerSocket() {
		try {
            ss = new ServerSocket(5501);
        } catch (IOException ioe) {
            System.out.println("Error finding port");
            System.exit(1);
        }
	}
	
	private void serve() throws IOException{
		
        Socket soc = null;
        try {
            soc = ss.accept();
            System.out.println("Connection accepted at :" + soc);
        } catch (IOException ioe) {
            System.out.println("Server failed to accept");
            System.exit(1);
        }
        DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        String s;
        System.out.println("Server waiting for message from the client");
        boolean quit = false;
        do {
            String msg = "";
            s = br.readLine();
            int len = s.length();
            if (s.equals("exit")) {
                quit = true;
            }
            for (int i = 0; i < len; i++) {
                msg = msg + s.charAt(i);
                dos.write((byte) s.charAt(i));
            }
 
            System.out.println("From client :" + msg);
//            dos.write(13);
            dos.write(10);
            dos.flush();
            
        } while (!quit);
        dos.close();
        soc.close();
 
 
	}
	
	public static void main(String args[]) throws IOException  {
		Server server = new Server();
        server.createServerSocket();
        server.serve();
    }
}

