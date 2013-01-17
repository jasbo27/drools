package bank.model;

public class Klient {
	private RynekKlienta rynek;
	private String imie;
	private String nazwisko;
	
	
	
	public Klient(RynekKlienta rynek, String imie, String nazwisko) {
		super();
		this.rynek = rynek;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
	@Override
	public String toString() {
		return imie + " " + nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public RynekKlienta getRynek() {
		return rynek;
	}

	public void setRynek(RynekKlienta rynek) {
		this.rynek = rynek;
	}



	
	
	

}
