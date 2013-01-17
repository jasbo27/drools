package pl.boboli.engineer.test.external;

import java.util.ArrayList;
import java.util.List;

import pl.boboli.engineer.test.external.model.BillingHistoryFact;

public class BillingSystem {
	private static final long SLEEP_TIME = 15000;
	
	private static BillingSystem instance;

	public static BillingSystem getInstance() {
		if (instance == null)
			instance = new BillingSystem();
		return instance;
	}

	public List<BillingHistoryFact> getFacts() throws InterruptedException{
		Thread.sleep(SLEEP_TIME);
		List<BillingHistoryFact> list  = new ArrayList<BillingHistoryFact>();
		list.add(new BillingHistoryFact());
		return list;
	}

}
