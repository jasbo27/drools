package bank.model;

public class KredytOdnawialny {
	private Klient klient;
	private Double wartosc;
	
	public KredytOdnawialny() {
		super();
	}
	
	public KredytOdnawialny(Klient klient, Double wartosc) {
		super();
		this.klient = klient;
		this.wartosc = wartosc;
	}

	public Klient getKlient() {
		return klient;
	}
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	public Double getWartosc() {
		return wartosc;
	}
	public void setWartosc(Double wartosc) {
		this.wartosc = wartosc;
	}
	
	
}
