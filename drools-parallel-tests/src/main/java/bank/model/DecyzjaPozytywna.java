package bank.model;

public class DecyzjaPozytywna extends Decyzja {

	KredytOdnawialny kredytOdnawialny;

	public DecyzjaPozytywna(Weryfikacja weryfikacja,
			KredytOdnawialny kredytOdnawialny) {
		super();
		this.weryfikacja = weryfikacja;
		this.klient = weryfikacja.getKlient();
		this.kredytOdnawialny = kredytOdnawialny;
		wynik = "POZYTYWNA";
	}

	public KredytOdnawialny getKredytOdnawialny() {
		return kredytOdnawialny;
	}

	public void setKredytOdnawialny(KredytOdnawialny kredytOdnawialny) {
		this.kredytOdnawialny = kredytOdnawialny;
	}

	@Override
	public String toString() {
		return wynik + ", kredyt odnawialny w wysokości "
				+ kredytOdnawialny.getWartosc();
	}

}
