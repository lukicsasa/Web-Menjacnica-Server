import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;




public class Server {

	public static void main(String[] args) {
		
		Socket klijentSocket = null;
		
		try {
			ServerSocket serverSoket = new ServerSocket(6969);
			
			while(true) {
				klijentSocket = serverSoket.accept();
				ServerNit serverskaNit = new ServerNit(klijentSocket);
				serverskaNit.start();
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
