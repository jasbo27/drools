package pl.boboli.engineer.test.feature;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.boboli.engineer.test.AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

public class FlowMaxThreadsTest extends AbstractTest {
	final int MAX_THREADS = 10;
	CountDownLatch start = new CountDownLatch(MAX_THREADS);
	CountDownLatch stop = new CountDownLatch(MAX_THREADS);
	List<String> eventList;

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/nthreadsflow.drl" , "rules/feature/nthreads.rf"};
		super.setUp();
		setParallismEnabled(true);

		ksession.setGlobal("start", start);
		ksession.setGlobal("stop", stop);


		eventList = new ArrayList<String>();
		ksession.insert(eventList);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testNThreads() throws InterruptedException {
		System.out.println("Testing n threads...1/1");
		//uruchomienie reguł w osobnym wątku
		Thread thread = new Thread() {
			public void run() {
                ksession.startProcess("dummyProcess");
				ksession.fireAllRules();
			}
		};
		thread.start();
		// po zadanym czasie lista powinna byc pusta ( reguły zawisły)
		Thread.sleep(5000);
		assertTrue(eventList.isEmpty());
		System.out.println("n threads test completed succesfuly");
	}

}
