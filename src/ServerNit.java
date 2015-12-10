import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import yahooAPI.YahooCurrencyConverter;

/**
 * Klasa u kojoj je smestena cela logika servera. Nasledjuje klasu Thread sto
 * joj omogucava da se ponasa kao nit.
 * 
 * @author neverne bede
 * @version 1.0
 * 
 * 
 */

public class ServerNit extends Thread {

	YahooCurrencyConverter yahoo = new YahooCurrencyConverter();

	String regex = "[0-9]+";

	Socket soketZaKomunikaciju = null;
	BufferedReader ulazniTokOdKlijenta = null;
	PrintStream izlazniTokKaKlijentu = null;

	double resenje;

	public ServerNit(Socket klijentSocket) {
		this.soketZaKomunikaciju = klijentSocket;
	}

	/**
	 * Inicijalizuje tokove ka klijentu i poziva metodu logika
	 * 
	 * @throws IOException
	 *             u slucaju prekida komunikacije
	 */
	public void run() {
		try {

			ulazniTokOdKlijenta = new BufferedReader(new InputStreamReader(
					soketZaKomunikaciju.getInputStream()));
			izlazniTokKaKlijentu = new PrintStream(
					soketZaKomunikaciju.getOutputStream());

			logika();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Cita podatke od klijenta i poziva metodu konvertuj klase
	 * YahooCurrencyConverter preko objekta yahoo. Salje klijentu rezultat
	 * konverzije i handluje greske kao sto je unos slova umesto brojeva.
	 * 
	 * @throws IOException
	 *             u slucaju prekida komunikacije sa klijentom.
	 */
	public void logika() {
		try {

			String valuta = ulazniTokOdKlijenta.readLine();
			String valutaKonvert = ulazniTokOdKlijenta.readLine();
			String iznos = ulazniTokOdKlijenta.readLine();
			if (!iznos.matches(regex)) {
				izlazniTokKaKlijentu.println("Morate uneti brojeve!");
			}

			double vrednost = Double.parseDouble(iznos);

			for (int i = 0; i < Server.konverzije.size(); i++) {
				if (Server.konverzije.get(i).getNazivValute().equals(valuta)
						&& Server.konverzije.get(i)
								.getValutaUKojuSeKonvertuje()
								.equals(valutaKonvert)) {
					resenje = Server.konverzije.get(i).getIznosKonverzije()
							* vrednost;
					break;
				}
			}

			String rezultat = String.valueOf(resenje);
			if (rezultat.indexOf(".") == -1)
				izlazniTokKaKlijentu.println(rezultat);
			else {
				izlazniTokKaKlijentu.println(rezultat.substring(0,
						(rezultat.indexOf(".") + 3)));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
