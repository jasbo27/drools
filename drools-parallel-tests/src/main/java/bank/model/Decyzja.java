package bank.model;

public class Decyzja {
	Klient klient;
	String komentarz;
	String wynik;
	Weryfikacja weryfikacja;
	public Weryfikacja getWeryfikacja() {
		return weryfikacja;
	}
	public void setWeryfikacja(Weryfikacja weryfikacja) {
		this.weryfikacja = weryfikacja;
	}
	public Klient getKlient() {
		return klient;
	}
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	public String getKomentarz() {
		return komentarz;
	}
	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}
	public String getWynik() {
		return wynik;
	}
	public void setWynik(String wynik) {
		this.wynik = wynik;
	}


	
	
	
}
