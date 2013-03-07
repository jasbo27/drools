package pl.boboli.engineer.test.regression;

import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.boboli.engineer.test.AbstractTest;
import pl.boboli.engineer.test.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GroupSequenceTest extends AbstractTest{
		List<String> eventList;
		FactHandle handle;
		@Before
		public void setUp() throws Exception {
			eventList = new ArrayList<String>();
			ruleLocations = new String[] {"rules/regression/groupSequence.drl"};
			super.setUp();
			handle = ksession.insert(eventList);
		}

		@After
		public void tearDown() throws Exception {
			super.tearDown();
		}
		

		@Test
		public void testGroupSequence() {
			System.out.println("Testing group sequence test...1/1");
			//przed uruchomieniem lista reguł, które zostały uruchomione powinna być pusta
			assertTrue(ObjectUtil.getObject(ksession,handle,List.class).isEmpty());

            for(int i=0;i<4;i++)
            {
                ksession.getAgenda().getAgendaGroup("GROUP-"+(i+1)).setFocus();
                ksession.fireAllRules();
            }

			List list = ObjectUtil.getObject(ksession,handle,List.class);
			assertTrue(list.size()==4);
			for(int i=0;i<4;i++)
			{
				assertTrue(list.get(i).equals("GROUP-"+(i+1)));
			}
			
			System.out.println("Group sequence test completed succesfuly");
		}
		


	}
