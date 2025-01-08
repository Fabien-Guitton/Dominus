package utilClass;

public enum Taxe {
	CURRENT_TAXES(20); // %
	
	private int taxe;

	Taxe(Integer taxe) {
		this.taxe = taxe;
	}

	public Integer getTaxe() {
		return taxe;
	}
}
