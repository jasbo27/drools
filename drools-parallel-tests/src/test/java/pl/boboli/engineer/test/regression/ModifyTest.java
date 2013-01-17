package pl.boboli.engineer.test.regression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.model.DummyObject;
import pl.boboli.engineer.test.utils.ObjectUtil;

public class ModifyTest extends AbstractTest{
	DummyObject obj;
	FactHandle handle;
	@Before
	public void setUp() throws Exception {
		obj = new DummyObject("hippo");
        setParallismEnabled(true);
		ruleLocations = new String[] {"rules/regression/modify.drl"};
		super.setUp();
		handle = ksession.insert(obj);

				
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	

	@Test
	public void testModify() {
		System.out.println("Testing modify...1/1");
		//przed uruchomieniem reguł  pamięć powinna zawierać obiekt typu DummyObject
		assertFalse(ObjectUtil.getObjects(ksession,DummyObject.class).isEmpty()	);
		//przed uruchomieniem reguł pole obiektu umieszczonego w pamięci powinno mieć wartośc "hippo"
		assertTrue("hippo".equals(ObjectUtil.getObject(ksession,handle,DummyObject.class).getName()));
		ksession.fireAllRules();
		//po uruhomieniu reguł pole obiektu umieszczonego w pamięci powinno mieć wartośc "parrot"
		assertTrue("parrot".equals(ObjectUtil.getObject(ksession,handle,DummyObject.class).getName()));
		System.out.println("Modify test completed succesfuly");
	}
	


}
