package bank.model;

import java.util.Date;

public class Windykacja {
	private long data;
	private Klient klient;
	

	public Windykacja(long data, Klient klient) {
		super();
		this.data = data;
		this.klient = klient;
	}

	public Klient getKlient() {
		return klient;
	}

	public void setKlient(Klient klient) {
		this.klient = klient;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	
	
	
}
