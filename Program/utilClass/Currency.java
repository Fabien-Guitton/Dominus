package utilClass;

public enum Currency {
	MAIN_CURRENCY("€");
	
	private String type;

	Currency(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
