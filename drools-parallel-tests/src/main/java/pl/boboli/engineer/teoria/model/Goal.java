package pl.boboli.engineer.teoria.model;

public class Goal {
	String status;
	String type;
	String object;
	

	public Goal() {
		super();
	}
	

	public Goal(String status, String type, String object) {
		super();
		this.status = status;
		this.type = type;
		this.object = object;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getObject() {
		return object;
	}


	public void setObject(String object) {
		this.object = object;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
