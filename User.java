package webtest.model;

public class User {
	private static int idSeed = 1;
	private String name;
	private String password;
	private String email;
	private int ID;

	public User() {
	}

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.ID = idSeed++;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}