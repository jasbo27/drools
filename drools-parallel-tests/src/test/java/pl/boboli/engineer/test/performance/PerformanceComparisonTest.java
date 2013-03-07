package pl.boboli.engineer.test.performance;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.AgendaGroup;
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
        ArrayList<PerformanceResult> performanceResultList = new ArrayList<PerformanceResult>();
		System.out.println("Testing n threads...1/1");
        for(int i = 0;i<10;i++){
            PerformanceResult result = new PerformanceResult();
            result.setParallelRunTime(fireInParallel());
            result.setSequentialRunTime(fireInOneThread());
            performanceResultList.add(result);
		System.out.println("Iteration:"+i+": Getting facts in parallel time:" + result.getParallelRunTime());
		System.out.println("Iteration:"+i+": Getting facts in sequential time:"
				+ result.getSequentialRunTime());
        }
        long avgSequentialRuntime = countAverageSequentialRunTime(performanceResultList);
        long  avgParallelRuntime = countAverageParallelRunTime(performanceResultList);
        System.out.println("Parallel average run time:" +  avgParallelRuntime);
        System.out.println("Sequential average run time:" +  avgSequentialRuntime);
	}

    private long countAverageSequentialRunTime(ArrayList<PerformanceResult> performanceResultList) {
        int count = performanceResultList.size();
        long sum = 0l;
        for(PerformanceResult performanceResult: performanceResultList){
            sum+=performanceResult.getSequentialRunTime();
        }
        return sum/count;  //To change body of created methods use File | Settings | File Templates.
    }
    private long countAverageParallelRunTime(ArrayList<PerformanceResult> performanceResultList) {
        int count = performanceResultList.size();
        long sum = 0l;
        for(PerformanceResult performanceResult: performanceResultList){
            sum+=performanceResult.getParallelRunTime();
        }
        return sum/count;  //To change body of created methods use File | Settings | File Templates.
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
		// test uruchomienia równoległego
		StatefulKnowledgeSession ksession = readSession();
		insertGlobalsToSession(ksession);
        org.drools.runtime.rule.AgendaGroup group =ksession.getAgenda().getAgendaGroup("P_GROUP");//.setFocus();
        group.setParallel(true);
        group.setMaxThreadCount(10);
        group.setFocus();

        group.setFocus();
		ksession.fireAllRules();
		ksession.dispose();
		return System.currentTimeMillis() - startTime;
	}



}
