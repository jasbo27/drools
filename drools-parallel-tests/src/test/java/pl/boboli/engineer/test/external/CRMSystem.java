package pl.boboli.engineer.test.external;

import pl.boboli.engineer.test.external.model.CustomerFact;

public class CRMSystem {
	
	private static final long SLEEP_TIME = 10000;
	private static CRMSystem instance;
	
	public static CRMSystem getInstance(){
		if(instance==null)
			instance=new CRMSystem();
		return instance;
	}

	public CustomerFact getFact() throws InterruptedException{
		Thread.sleep(SLEEP_TIME);
		return new CustomerFact();
	}

}
