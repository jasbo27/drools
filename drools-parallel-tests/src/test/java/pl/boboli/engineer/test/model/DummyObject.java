package pl.boboli.engineer.test.model;

public class DummyObject implements Comparable{
	String name;
	int i;
	int j;
	int k;
	boolean modifiedJ = false;
	boolean modifiedK = false;
	
	public DummyObject() {
		super();
	}
	

	public DummyObject(String name, int i, int j) {
		super();
		this.name = name;
		this.i = i;
		this.j = j;
	}
	


	public DummyObject(String name, int i, int j, int k) {
		super();
		this.name = name;
		this.i = i;
		this.j = j;
		this.k = k;
	}


	public DummyObject(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	


	


	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}


	public int getJ() {
		return j;
	}


	public void setJ(int j) {
		this.j = j;
	}


	public int getK() {
		return k;
	}


	public void setK(int k) {
		this.k = k;
	}
	
	

	public boolean isModifiedJ() {
		return modifiedJ;
	}


	public void setModifiedJ(boolean modifiedJ) {
		this.modifiedJ = modifiedJ;
	}


	public boolean isModifiedK() {
		return modifiedK;
	}


	public void setModifiedK(boolean modifiedK) {
		this.modifiedK = modifiedK;
	}
	


	@Override
	public String toString() {
		return "DummyObject [name=" + name + ", i=" + i + ", j=" + j + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DummyObject other = (DummyObject) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public int compareTo(Object arg0) {
		DummyObject obj = (DummyObject)arg0;
		if(obj.i>this.i )
			return -1;
		else if(obj.i<this.i)
			return 1;
		else if(obj.i==this.i)
		{
			if(obj.j==this.j)
				return 0;
			else if(obj.j>this.j)
				return -1;
			else if(obj.j<this.j)
				return 1;
		}
		throw ( new RuntimeException("this="+this+"\tobj="+obj));

	}






	
	
	
	
}
