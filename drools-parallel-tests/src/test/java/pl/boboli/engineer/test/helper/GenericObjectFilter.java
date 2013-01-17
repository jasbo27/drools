package pl.boboli.engineer.test.helper;

import org.drools.runtime.ObjectFilter;

public class GenericObjectFilter<T> implements ObjectFilter{
		
		private Class type;
		
		
		public GenericObjectFilter() {
			super();
		}


		public GenericObjectFilter(Class type) {
			this.type = type;
		}


		public boolean accept(Object object) {
			return object.getClass().equals(type) ;
		}
}
