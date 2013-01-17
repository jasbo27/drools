package pl.boboli.engineer.test.regression;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.boboli.engineer.KnowledgeBaseReader;
import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.helper.GenericObjectFilter;
import pl.boboli.engineer.test.model.DummyObject;
import pl.boboli.engineer.test.utils.ObjectUtil;

public class InsertTest extends AbstractTest{

	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] {"rules/regression/insert.drl"};
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	

	@Test
	public void testInsert() {
		System.out.println("Testing insert...1/1");
		//przed dodaniem obiektu pamięć powinna być pusta
		assertTrue(ObjectUtil.getObjects(ksession,DummyObject.class).isEmpty()	);
		ksession.fireAllRules();
		//po dodaniu obiektu pamięć powinna zawierać przynajmniej jeden obiekt danej klasy
		assertFalse(ObjectUtil.getObjects(ksession,DummyObject.class).isEmpty()	);
		System.out.println("Insert test completed succesfuly");
	}
	


}
