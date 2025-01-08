package utilClass;

public enum Command {
	REFRESH("REFRESH-"),
	ADD_CONNECTED_EMPLOYEE("ADD_CONNECTED_EMPLOYEE-"),
	RM_CONNECTED_EMPLOYEE("RM_CONNECTED_EMPLOYEE-");
	
	private String command;

	Command(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}
}
