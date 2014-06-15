
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket soc;
	private BufferedReader br ;
    private DataOutputStream dos;
	
	private void createSocket() throws IOException {
		try {
            soc = new Socket(InetAddress.getLocalHost(), 5501);
            br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            dos = new DataOutputStream(soc.getOutputStream());
        } catch (UnknownHostException uhe) {
            System.out.println("Unknown Host");
            System.exit(0);
        }
	}
	
	private void chat() throws IOException{
		BufferedReader kyrd = new BufferedReader(new InputStreamReader(System.in));
		String str;
        System.out.println("To start the dialog type the message in this client window \n Type exit to end"); 
        boolean more = true;
        while (more) {
            str = kyrd.readLine();
            dos.writeBytes(str);
//            dos.write(13);
            dos.write(10);
            dos.flush();
            String s, s1;
            s = br.readLine();
            System.out.println("From server :" + s);
            if (s.equals("exit")) {
                break;
            }
        }
        br.close();
        dos.close();
        soc.close();
    }
	
	public static void main(String args[]) throws IOException {
	        
	        
	        Client client = new Client();
	        client.createSocket();
	        client.chat();
	}       
}
