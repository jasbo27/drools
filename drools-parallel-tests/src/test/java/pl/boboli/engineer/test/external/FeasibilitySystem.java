package pl.boboli.engineer.test.external;

import pl.boboli.engineer.test.external.model.FeasibilityFact;

public class FeasibilitySystem {
	private static final long SLEEP_TIME = 20000;
	
	private static FeasibilitySystem instance;

	public static FeasibilitySystem getInstance() {
		if (instance == null)
			instance = new FeasibilitySystem();
		return instance;
	}

	public FeasibilityFact getFact() throws InterruptedException{
		Thread.sleep(SLEEP_TIME);
		return new FeasibilityFact();
	}
}
