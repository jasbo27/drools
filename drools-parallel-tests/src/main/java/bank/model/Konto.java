package bank.model;

public class Konto {
	private StatusKonta status;
	private Klient klient;
	private double sredniRachunek;
	
	

	public Konto(StatusKonta status, Klient klient, Double sredniRachunek) {
		super();
		this.status = status;
		this.klient = klient;
		this.sredniRachunek = sredniRachunek;
	}


	public double getSredniRachunek() {
		return sredniRachunek;
	}



	public void setSredniRachunek(double sredniRachunek) {
		this.sredniRachunek = sredniRachunek;
	}



	public Klient getKlient() {
		return klient;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	public StatusKonta getStatus() {
		return status;
	}

	public void setStatus(StatusKonta status) {
		this.status = status;
	}
	
}
