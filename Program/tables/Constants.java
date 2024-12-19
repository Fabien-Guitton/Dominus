package tables;

public enum Constants {
	JAVA_USER("Java User");
	
	private String user;

	Constants(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
}
