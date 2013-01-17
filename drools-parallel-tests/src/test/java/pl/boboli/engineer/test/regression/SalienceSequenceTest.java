package pl.boboli.engineer.test.regression;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.utils.ObjectUtil;

public class SalienceSequenceTest extends AbstractTest{
		List<String> eventList;
		FactHandle handle;
		@Before
		public void setUp() throws Exception {
			eventList = new ArrayList<String>();
			ruleLocations = new String[] {"rules/regression/salienceSequence.drl"};
			super.setUp();
			handle = ksession.insert(eventList);
		}

		@After
		public void tearDown() throws Exception {
			super.tearDown();
		}
		

		@Test
		public void testSalienceSequence() {
			System.out.println("Testing salience sequence ...1/1");
			//przed uruchomieniem lista reguł, które zostały uruchomione powinna być pusta
			assertTrue(ObjectUtil.getObject(ksession,handle,List.class).isEmpty());
			ksession.fireAllRules();
			List list = ObjectUtil.getObject(ksession,handle,List.class);
			assertTrue(list.size()==4);
			for(int i=0;i<4;i++)
			{
				assertTrue(list.get(i).equals("RULE-"+(i+1)));
			}
			
			System.out.println("Salience sequence test completed succesfuly");
		}
		


	}
