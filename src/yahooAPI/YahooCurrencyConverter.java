package yahooAPI;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Klasa koja omogucava komunikaciju sa yahooCurrency APIjem i omogucava
 * konverziju valuta iz jedne u drugu. Sadrzi metodu konvertuj koja se poziva od
 * strane serverske niti Zasniva se na koriscenju biblioteke org.apache.http
 * 
 * @author neverne bede
 * @version 1.0
 */

public class YahooCurrencyConverter {

	/**
	 * Metoda prima dve valute, menja vrednost jedne za vrednost druge i vraca
	 * vrednost koja se prosledjuje serverskoj niti. Pravi HttpClient i HttpGet
	 * objekte preko kojih omogucava komunikaciju sa APIjem objekat
	 * ResponseHandler omogucava handlovanje odgovora od strane HttpResponse
	 * objekta i smesta ga u String responseBody
	 * 
	 * @param valuta
	 *            - koju zelimo da konvertujemo
	 * @param valutaKonvert
	 *            - valuta u koju zelimo da konvertujemo
	 * @return float vrednost konverzije
	 * @throws ClientProtocolException
	 *             u slucaju HTTP protokol greske
	 * @throws IOException
	 *             u slucaju prekida ili problema sa komunikacijom
	 */
	public float konvertuj(String valuta, String valutaKonvert) {
		HttpClient httpKlijent = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://quote.yahoo.com/d/quotes.csv?s="
				+ valuta + valutaKonvert + "=X&f=l1&e=.csv");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = httpKlijent.execute(httpGet, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpKlijent.getConnectionManager().shutdown();
		return Float.parseFloat(responseBody);
	}

}