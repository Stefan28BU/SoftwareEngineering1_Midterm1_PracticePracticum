package mid2prac;

public enum Attributes {
	name ("Name"),
	age ("Age"),
	gender ("Gender"),
	salary ("Salary"),
	email ("Email");
	
	private final String str;
	private Attributes(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
}
