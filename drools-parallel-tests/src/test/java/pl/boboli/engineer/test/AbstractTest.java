package pl.boboli.engineer.test;

import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.SessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;

import pl.boboli.engineer.KnowledgeBaseReader;

public class AbstractTest extends KnowledgeBaseReader {
	protected StatefulKnowledgeSession ksession;
	protected String[] ruleLocations;
	protected String[] processesLocations ;
	protected boolean parallismEnabled;
    protected int threadCount;
	@Before
	public void setUp() throws Exception {
		ksession = readSession();
	}

    protected StatefulKnowledgeSession readSession() throws Exception {
        KnowledgeBase kbase = readKnowledgeBase(ruleLocations);

        SessionConfiguration sessionConfiguration = SessionConfiguration.getDefaultInstance();
        sessionConfiguration.setParallelismEnabled(parallismEnabled);
        sessionConfiguration.setThreadCount(threadCount);
        StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession(sessionConfiguration, null);

        return session;
    }

    @After
	public void tearDown() throws Exception {
		if(ksession!=null)
		{
			ksession.dispose();
			ksession = null;
		}
	}
	protected boolean isParallismEnabled() {
		return parallismEnabled;
	}
	protected void setParallismEnabled(boolean parallismEnabled) {
		this.parallismEnabled = parallismEnabled;
	}

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
