package pl.boboli.engineer.test.feature;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.drools.runtime.rule.FactHandle;
import org.drools.common.InternalAgendaGroup;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.impl.InternalAgenda;
import org.drools.spi.AgendaGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.model.DummyObject;
import pl.boboli.engineer.test.utils.ObjectUtil;

public class ParallelModifyTest1 extends AbstractTest {
	final int THREADS = 2;
	final int ITERATIONS = 10000;
	final int SLEEP_TIME = 0;
	CountDownLatch start = new CountDownLatch(THREADS);
	CountDownLatch stop = new CountDownLatch(THREADS);

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/parallelModify1.drl" };
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
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testNThreads() throws InterruptedException {
		System.out.println("Testing n threads...1/1");
			
			// sprawdzenie, czy sesja jest pusta
			assertTrue(ObjectUtil.getObjects(ksession, DummyObject.class)
					.isEmpty());
			// wstawienie obiektów do pamięci
			List<FactHandle> factHandles = insertObjects(ksession);
			// sprawdzenie, czy obiekty są w pamięci
			assertTrue(ObjectUtil.getObjects(ksession, DummyObject.class)
					.size() > 0);
			// sprawdzenie czy wartości przed zmianą są zgodne z oczekiwaniami
			verifyObjectsBeforeChange(ksession);
			// uruchomienie reguł równoległych
			ksession.getAgenda().getAgendaGroup("P_GROUP").setFocus();
			ksession.fireAllRules();
			System.out.println("Running rules for the second time");
			// uruchomienie pozostałych reguł
			ksession.getAgenda().getAgendaGroup("NON_PARALLEL_GROUP").setFocus();
			ksession.fireAllRules();
			// sprawdzenie, czy wartości zostały zmienione zgodnie z założeniami
			verifyObjectsAfterChange(ksession);
			// usunięcie obiektów z pamięci
			retractObjects(ksession, factHandles);
			// sprawdzenie, czy obiekty zostały usunięte
			assertTrue(ObjectUtil.getObjects(ksession, DummyObject.class)
					.isEmpty());
		System.out.println("n threads test completed succesfuly");
	}

	private List<FactHandle> insertObjects(StatefulKnowledgeSession ksession) {
		List<FactHandle> factHandles = new ArrayList<FactHandle>();
		factHandles.add(ksession.insert(new DummyObject("shared", 0, 1, 2)));
		return factHandles;
	}

	private void retractObjects(StatefulKnowledgeSession ksession,
			List<FactHandle> factHandles) {
		for (FactHandle factHandle : factHandles) {
			ksession.retract(factHandle);
		}
	}

	private void verifyObjectsBeforeChange(StatefulKnowledgeSession ksession) {
		Collection<DummyObject> results = ObjectUtil.getObjects(ksession,
				DummyObject.class);
		SortedSet<DummyObject> set = new TreeSet<DummyObject>();
		set.addAll(results);
		for (DummyObject dummyObject : set) {
			assertTrue(dummyObject.getName().equals("shared"));
			assertTrue(dummyObject.getJ() == 1);
			assertTrue(dummyObject.getK() == 2);
		}
	}

	private void verifyObjectsAfterChange(StatefulKnowledgeSession ksession) {
		Collection<DummyObject> results = ObjectUtil.getObjects(ksession,
				DummyObject.class);
		SortedSet<DummyObject> set = new TreeSet<DummyObject>();
		set.addAll(results);
		for (DummyObject dummyObject : set) {
			assertTrue(dummyObject.getJ() == ITERATIONS -1);
			assertTrue(dummyObject.getK() == ITERATIONS -1 + 100);
		}
	}

}
