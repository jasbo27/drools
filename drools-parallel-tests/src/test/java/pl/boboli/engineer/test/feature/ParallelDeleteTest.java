package pl.boboli.engineer.test.feature;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

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

public class ParallelDeleteTest extends AbstractTest {
	final int THREADS = 11;
	final int ITERATIONS = 10000;
	final int SLEEP_TIME = 0;
	CountDownLatch start = new CountDownLatch(THREADS);
	CountDownLatch stop = new CountDownLatch(THREADS);

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/parallelDelete.drl" };
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
		for (int i = 0; i < ITERATIONS; i++) {
            org.drools.runtime.rule.AgendaGroup group =ksession.getAgenda().getAgendaGroup("P_GROUP");//.setFocus();
            group.setParallel(true);
            group.setFocus();
			System.out.println("Iteration " +i+" started...");
			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).isEmpty());
			insertObjects(ksession);
			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).size()>0);
			
			ksession.fireAllRules();

			assertTrue(ObjectUtil.getObjects(ksession,
					DummyObject.class).isEmpty());
			System.out.println("Iteration " +i+" completed...");
		}
		System.out.println("n threads test completed succesfuly");
	}

	private void insertObjects(StatefulKnowledgeSession ksession) {
		for(int i=0;i<THREADS;i++){
			ksession.insert(new DummyObject("rule-"+i,i,0));
		}	
	}
	
	

}
