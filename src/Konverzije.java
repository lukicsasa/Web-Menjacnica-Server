public class Konverzije {

	String nazivValute;
	String valutaUKojuSeKonvertuje;
	float iznosKonverzije;

	public Konverzije(String nazivValute, String valutaUKojuSeKonvertuje,
			float iznosKonverzije) {
		super();
		this.nazivValute = nazivValute;
		this.valutaUKojuSeKonvertuje = valutaUKojuSeKonvertuje;
		this.iznosKonverzije = iznosKonverzije;
	}

	public String getNazivValute() {
		return nazivValute;
	}

	public void setNazivValute(String nazivValute) {
		this.nazivValute = nazivValute;
	}

	public String getValutaUKojuSeKonvertuje() {
		return valutaUKojuSeKonvertuje;
	}

	public void setValutaUKojuSeKonvertuje(String valutaUKojuSeKonvertuje) {
		this.valutaUKojuSeKonvertuje = valutaUKojuSeKonvertuje;
	}

	public float getIznosKonverzije() {
		return iznosKonverzije;
	}

	public void setIznosKonverzije(float iznosKonverzije) {
		this.iznosKonverzije = iznosKonverzije;
	}

}
