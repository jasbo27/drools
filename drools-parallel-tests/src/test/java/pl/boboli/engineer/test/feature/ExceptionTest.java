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

public class ExceptionTest extends AbstractTest {
	final int THREADS = 2;
	final int ITERATIONS = 2;
	final int SLEEP_TIME = 0;
	CountDownLatch start = new CountDownLatch(THREADS);
	CountDownLatch stop = new CountDownLatch(THREADS);

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] { "rules/feature/exception.drl" };
		setParallismEnabled(true);
        setThreadCount(THREADS);
		super.setUp();
		ksession.setGlobal("start", start);
		ksession.setGlobal("stop", stop);
		ksession.setGlobal("ITERATIONS", ITERATIONS);
		ksession.setGlobal("SLEEP_TIME", SLEEP_TIME);
		ksession.getAgenda().getAgendaGroup("PARALLEL_GROUP").setFocus();
		ksession.insert(new DummyObject());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSelf() throws InterruptedException {
			
			// sprawdzenie, czy sesja jest pusta
			assertFalse(ObjectUtil.getObjects(ksession, DummyObject.class)
					.isEmpty());
			//uruchomienie reguł równoległych
			try {
				ksession.fireAllRules();
				//do tego 
				fail("we shouldn't reach this point ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

	

}
