import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import yahooAPI.YahooCurrencyConverter;

/**
 * Klasa Server koja inicijalizuje serverSocket i pokrece serversku nit Sadrzi
 * main metodu koja omogucava pokretanje servera
 * 
 * @author neverne bede
 * @version 1.0
 * 
 */

public class Server {

	static YahooCurrencyConverter converter = new YahooCurrencyConverter();
	static String[] nazivi = { "RSD", "EUR", "USD", "CAD", "GBP", "RUB", "BAM",
			"SEK", "AUD" };
	public static LinkedList<Konverzije> konverzije = new LinkedList<>();

	/**
	 * main metoda koja pokrece rad servera i inicira pokretanje niti
	 * 
	 * @exception - IOException koji se handluje ispisivanjem stack trace-a u
	 *            slucaju greske sa ulazno-izlaznim tokovima
	 */
	public static void main(String[] args) {

		for (int i = 0; i < nazivi.length; i++) {
			for (int j = 0; j < nazivi.length; j++) {
				Konverzije k = new Konverzije(nazivi[i], nazivi[j],
						converter.konvertuj(nazivi[i], nazivi[j]));
				konverzije.add(k);
			}
		}

		Socket klijentSocket = null;

		try {
			ServerSocket serverSoket = new ServerSocket(6969);

			while (true) {
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
