package pl.boboli.engineer.test.utils;

import java.util.Collection;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import pl.boboli.engineer.test.helper.GenericObjectFilter;
import pl.boboli.engineer.test.model.DummyObject;

public class ObjectUtil {

	public static <T> Collection<T> getObjects(
			StatefulKnowledgeSession ksession, Class<T> clazz) {
		return (Collection<T>) ksession.getObjects(new GenericObjectFilter<T>(clazz));
	}
	
	public static <T> T getObject(
			StatefulKnowledgeSession ksession, FactHandle handle, Class<T> clazz) {
		return (T) ksession.getObject(handle);
	}

}
