package utilClass;

public enum ScenesMap {
	MENU("/applications/menu/menu.fxml"),
	HISTORICAL_HISTORICAL("/applications/historical/historical/historicalHistorical.fxml"),
	DELIVERY("/applications/delivery/delivery.fxml"),
	INIT("/init/init.fxml"),
	HISTORICAL_PAYMENT("/applications/historical/payment/historicalPayment.fxml"),
	CHECKOUT_ORDER("/applications/checkout/order/checkoutOrder.fxml"),
	LOGIN("/applications/login/login.fxml");
	private String fxmlPath;

	ScenesMap(String fxmlPath) {
		this.fxmlPath = fxmlPath;
	}

	public String getFXML() {
		return fxmlPath;
	}
	
}
