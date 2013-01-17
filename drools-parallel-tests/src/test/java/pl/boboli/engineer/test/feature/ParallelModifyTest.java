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

public class ParallelModifyTest extends AbstractTest {
	final int THREADS = 11;
    final int RULE_COUNT = 11;
	final int ITERATIONS = 10000;
	final int SLEEP_TIME = 0;
    final int LATCH_SIZE = THREADS>RULE_COUNT?RULE_COUNT:THREADS;
	CountDownLatch start = new CountDownLatch(LATCH_SIZE);
	CountDownLatch stop = new CountDownLatch(LATCH_SIZE);

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/parallelModify.drl" };
		setParallismEnabled(true);
        setThreadCount(THREADS);
		super.setUp();
		ksession.setGlobal("start", start);
		ksession.setGlobal("stop", stop);
		ksession.setGlobal("ITERATIONS", ITERATIONS);
		ksession.setGlobal("SLEEP_TIME", SLEEP_TIME);
		ksession.getAgenda().getAgendaGroup("PARALLEL_GROUP").setFocus();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testNThreads() throws InterruptedException {
		System.out.println("Testing n threads...1/1");
		for (int i = 0; i < ITERATIONS; i++) {
			ksession.getAgenda().getAgendaGroup("PARALLEL_GROUP").setFocus();
			System.out.println("Iteration " +i+" started...");
			
			//sprawdzenie, czy sesja jest pusta
			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).isEmpty());
			//wstawienie obiektów do pamięci
			List<FactHandle> factHandles=insertObjects(ksession);
			//sprawdzenie, czy obiekty są w pamięci
			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).size()>0);
			//sprawdzenie czy wartości przed zmianą są zgodne z oczekiwaniami
			verifyObjectsBeforeChange(ksession);
			//uruchomienie reguł
			ksession.fireAllRules();
			ksession.getAgenda().getAgendaGroup("NON_PARALLEL_GROUP").setFocus();
			//uruchomienie reguł po raz drugi
			ksession.fireAllRules();
			//sprawdzenie, czy wartości zostały zmienione zgodnie z założeniami
			verifyObjectsAfterChange(ksession);
			//usunięcie obiektów z pamięci
			retractObjects(ksession,factHandles);
			//sprawdzenie, czy obiekty zostały usunięte
			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).isEmpty());
			System.out.println("Iteration " +i+" completed...");
		}
		System.out.println("n threads test completed succesfuly");
	}

	private List<FactHandle> insertObjects(StatefulKnowledgeSession ksession) {
		List<FactHandle> factHandles = new ArrayList<FactHandle>();
		for(int i=0;i<THREADS;i++){
			 factHandles.add(ksession.insert(new DummyObject("rule-"+i,i,i-1)));
		}	
		return factHandles;
	}
	
	private void retractObjects(StatefulKnowledgeSession ksession,List<FactHandle> factHandles  ){
		for(FactHandle factHandle:factHandles){
			ksession.retract(factHandle);
		}
	}
	private void verifyObjectsBeforeChange(StatefulKnowledgeSession ksession) {
		Collection<DummyObject> results = ObjectUtil.getObjects(ksession,DummyObject.class);
		SortedSet<DummyObject> set = new TreeSet<DummyObject>();
		set.addAll(results);
		for(DummyObject dummyObject:set){
			assertTrue(dummyObject.getI()!=dummyObject.getJ());
		}
	}
	private void verifyObjectsAfterChange(StatefulKnowledgeSession ksession) {
		Collection<DummyObject> results = ObjectUtil.getObjects(ksession,DummyObject.class);
		SortedSet<DummyObject> set = new TreeSet<DummyObject>();
		set.addAll(results);
		for(DummyObject dummyObject:set){
			assertTrue(dummyObject.getI()==dummyObject.getJ());
		}
	}

}
