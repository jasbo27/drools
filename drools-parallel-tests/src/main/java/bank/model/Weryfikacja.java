package bank.model;

import java.util.Date;

import bank.utils.DateUtils;

public class Weryfikacja {
	long dataWeryfikacji;
	Klient klient;
	
	
	public Weryfikacja() {
		super();
	}

	

	public Weryfikacja(long dataWeryfikacji, Klient klient) {
		super();
		this.dataWeryfikacji = dataWeryfikacji;
		this.klient = klient;
	}



	public long getDataWeryfikacji() {
		return dataWeryfikacji;
	}

	public void setDataWeryfikacji(long dataWeryfikacji) {
		this.dataWeryfikacji = dataWeryfikacji;
	}

	public Klient getKlient() {
		return klient;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	
	
}
