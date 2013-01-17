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

public class DeleteTest extends AbstractTest{
	DummyObject obj = new DummyObject("hippo");
	@Before
	public void setUp() throws Exception {
		ruleLocations = new String[] {"rules/regression/delete.drl"};
		super.setUp();
		ksession.insert(obj);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	

	@Test
	public void testDelete() {
		System.out.println("Testing delete...1/1");
		//przed dodaniem obiektu pamięć powinna być pusta
		assertFalse(ObjectUtil.getObjects(ksession,DummyObject.class).isEmpty()	);
		assertTrue(ObjectUtil.getObjects(ksession,DummyObject.class).contains(obj));
		ksession.fireAllRules();
		//po dodaniu obiektu pamięć powinna zawierać przynajmniej jeden obiekt danej klasy
		assertTrue(ObjectUtil.getObjects(ksession,DummyObject.class).isEmpty()	);
		assertFalse(ObjectUtil.getObjects(ksession,DummyObject.class).contains(obj));
		System.out.println("Delete test completed succesfuly");
	}
	


}
