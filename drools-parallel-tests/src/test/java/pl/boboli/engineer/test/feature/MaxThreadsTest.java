package pl.boboli.engineer.test.feature;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.drools.common.InternalAgendaGroup;
import org.drools.runtime.rule.impl.InternalAgenda;
import org.drools.spi.AgendaGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;

public class MaxThreadsTest extends AbstractTest {
	final int MAX_THREADS = 12;
	CountDownLatch start = new CountDownLatch(MAX_THREADS);
	CountDownLatch stop = new CountDownLatch(MAX_THREADS);
	List<String> eventList;

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/nthreads.drl" };
		super.setUp();
		setParallismEnabled(true);
        setThreadCount(MAX_THREADS);
		ksession.setGlobal("start", start);
		ksession.setGlobal("stop", stop);

		ksession.getAgenda().getAgendaGroup("PARALLEL_GROUP").setFocus();
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
