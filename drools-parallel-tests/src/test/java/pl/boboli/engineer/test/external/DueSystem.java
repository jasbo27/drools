package pl.boboli.engineer.test.external;

import java.util.ArrayList;
import java.util.List;

import pl.boboli.engineer.test.external.model.DueFact;

public class DueSystem {
	private static final long SLEEP_TIME = 8000;

	private static DueSystem instance;

	public static DueSystem getInstance() {
		if (instance == null)
			instance = new DueSystem();
		return instance;
	}

	public List<DueFact> getFacts() throws InterruptedException {
		Thread.sleep(SLEEP_TIME);
		List<DueFact> dueFacts = new ArrayList<DueFact>();
		return dueFacts;
	}

}
