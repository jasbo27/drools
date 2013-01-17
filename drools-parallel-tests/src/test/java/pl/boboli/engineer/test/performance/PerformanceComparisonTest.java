package pl.boboli.engineer.test.performance;

import java.util.concurrent.CountDownLatch;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.external.BillingSystem;
import pl.boboli.engineer.test.external.CRMSystem;
import pl.boboli.engineer.test.external.DueSystem;
import pl.boboli.engineer.test.external.FeasibilitySystem;

public class PerformanceComparisonTest extends AbstractTest {

    @Before
    public void setUp() throws Exception {
        ruleLocations = new String[]{"rules/performance/getFacts.drl"};
        super.setUp();
    }

	private void insertGlobalsToSession(StatefulKnowledgeSession ksession) {
		ksession.setGlobal("billingSystem", BillingSystem.getInstance());
		ksession.setGlobal("crmSystem", CRMSystem.getInstance());
		ksession.setGlobal("dueSystem", DueSystem.getInstance());
		ksession.setGlobal("feasibilitySystem", FeasibilitySystem.getInstance());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParallel() throws Exception {
		System.out.println("Testing n threads...1/1");
		long parallelRunTime = fireInParallel();

		long sequentialRunTime = fireInOneThread();

		System.out.println("Getting facts in parallel time:" + parallelRunTime);
		System.out.println("Getting facts in sequential time:"
				+ sequentialRunTime);
	}

	private long fireInOneThread() throws Exception {
		long startTime = System.currentTimeMillis();
		setParallismEnabled(false);
		// test uruchomienia sekwencyjnego
		StatefulKnowledgeSession ksession = readSession();
		insertGlobalsToSession(ksession);
		ksession.getAgenda().getAgendaGroup("SEQUENTIAL_GROUP").setFocus();
		ksession.fireAllRules();
		ksession.dispose();
		return System.currentTimeMillis() - startTime;
	}

	private long fireInParallel() throws Exception {
		long startTime = System.currentTimeMillis();
		setParallismEnabled(true);
        setThreadCount(10);
		// test uruchomienia równoległego
		StatefulKnowledgeSession ksession = readSession();
		insertGlobalsToSession(ksession);
		ksession.getAgenda().getAgendaGroup("PARALLEL_GROUP").setFocus();
		ksession.fireAllRules();
		ksession.dispose();
		return System.currentTimeMillis() - startTime;
	}



}
