package pl.boboli.engineer.test.feature;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.drools.common.InternalAgendaGroup;
import org.drools.runtime.rule.impl.InternalAgenda;
import org.drools.spi.AgendaGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.model.DummyObject;
import pl.boboli.engineer.test.utils.ObjectUtil;

public class ParallelInsertTest extends AbstractTest {
	final int THREADS = 11;
    final int RULE_COUNT = 11;
    final int LATCH_SIZE = THREADS>RULE_COUNT?RULE_COUNT:THREADS;
	final int ITERATIONS = 10000;
	final int SLEEP_TIME = 0;
	CountDownLatch start = new CountDownLatch(LATCH_SIZE);
	CountDownLatch stop = new CountDownLatch(LATCH_SIZE);
	List<String> eventList;

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/parallelInsert.drl" };
		setParallismEnabled(true);
		super.setUp();
		ksession.setGlobal("start", start);
		ksession.setGlobal("stop", stop);
		ksession.setGlobal("ITERATIONS", ITERATIONS);
		ksession.setGlobal("SLEEP_TIME", SLEEP_TIME);
		org.drools.runtime.rule.AgendaGroup group =ksession.getAgenda().getAgendaGroup("P_GROUP");//.setFocus();
        group.setParallel(true);
        group.setMaxThreadCount(THREADS);
        group.setFocus();
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
		// uruchomienie reguł w osobnym wątku
		ksession.fireAllRules();
		Collection<DummyObject> results = ObjectUtil.getObjects(ksession,DummyObject.class);
		SortedSet<DummyObject> set = new TreeSet<DummyObject>();
		set.addAll(results);
		//Thread.sleep(ITERATIONS * (SLEEP_TIME + 100));
		for (int i = 0; i < RULE_COUNT; i++)
			for (int j = 0; j < ITERATIONS; j++) {
				assertTrue(set
						.contains(new DummyObject("rule-" + i, i, j)));
			}
		assertTrue(set.size()==(RULE_COUNT*ITERATIONS));
		System.out.println("n threads test completed succesfuly");
	}

}
