package bank.model;

public class DecyzjaNegatywna extends Decyzja {
	
	public DecyzjaNegatywna(Weryfikacja weryfikacja, String komentarz) {
		super();
		this.weryfikacja = weryfikacja;
		this.klient = weryfikacja.getKlient();
		this.komentarz = komentarz;
		wynik = "NEGATYWNA";
	}
	@Override
	public String toString() {
		return wynik+", komentarz: " + komentarz;
	}
	
}
