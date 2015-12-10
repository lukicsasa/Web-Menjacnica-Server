import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import yahooAPI.YahooCurrencyConverter;


public class ServerNit extends Thread {
	
	YahooCurrencyConverter yahoo = new YahooCurrencyConverter();

	String regex = "[0-9]+";
	
	Socket soketZaKomunikaciju = null;
	BufferedReader ulazniTokOdKlijenta = null;
	PrintStream izlazniTokKaKlijentu = null;
	
	public ServerNit(Socket klijentSocket) {
		this.soketZaKomunikaciju = klijentSocket;
	}
	
	public void run() {
		try {
			
			ulazniTokOdKlijenta = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			izlazniTokKaKlijentu = new PrintStream(soketZaKomunikaciju.getOutputStream());
			
			logika();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logika() {
		try {
		
		String valuta = ulazniTokOdKlijenta.readLine();
		String valutaKonvert = ulazniTokOdKlijenta.readLine();
		String iznos = ulazniTokOdKlijenta.readLine();
		if(!iznos.matches(regex)) {
			izlazniTokKaKlijentu.println("Morate uneti brojeve!");
		}
		
		double vrednost = Double.parseDouble(iznos);
		
		double resenje = yahoo.convert(valuta, valutaKonvert)*vrednost;
		String rezultat = String.valueOf(resenje);
		izlazniTokKaKlijentu.println(rezultat);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
