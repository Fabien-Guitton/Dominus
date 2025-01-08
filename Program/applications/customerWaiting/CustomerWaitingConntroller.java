package applications.customerWaiting;

import javafx.animation.KeyFrame;


import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import tables.Orders;
import dao.OrdersDAO;

public class CustomerWaitingConntroller {
	
	@FXML
	private Label currentDate;

    @FXML
    private GridPane gridPane;

    private final OrdersDAO orderDAO; // Remplace par ton DAO réel

    public CustomerWaitingConntroller() {
    	// Supprimez uniquement les lignes après les en-têtes
//        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) > 0);
        // Initialiser le DAO (ou injecter s'il utilise un framework comme Spring)
        this.orderDAO = new OrdersDAO();
    }
    
 // Méthode utilitaire pour ajouter un label au GridPane
    private void addLabelToGrid(String text, int rowIndex, int colIndex) {
        Label label = new Label(text);
        label.getStyleClass().add("gridpane-label");
        GridPane.setRowIndex(label, rowIndex);
        GridPane.setColumnIndex(label, colIndex);
        GridPane.setHalignment(label, HPos.CENTER); // Alignement horizontal
        GridPane.setValignment(label, VPos.CENTER); // Alignement vertical
        gridPane.getChildren().add(label);
    }

    private void refreshGrid() {
        // Vider le GridPane avant de recharger
    	gridPane.getChildren().clear();

        // Récupérer les commandes non validées
        List<Orders> orders = orderDAO.PendingOrders();
        addLabelToGrid("Commande", 0, 0);
        addLabelToGrid("Nom", 0, 1);
        addLabelToGrid("Etat", 0, 2);
        addLabelToGrid("Pret dans", 0, 3);
        String status = "";
        String ready = "";

        System.out.println(orders);
        for (int i = 0; i < orders.size(); i++) {
            Orders order = orders.get(i);
            ready = Long.toString((order.getReadyDateOrd().getTime() - new Timestamp(System.currentTimeMillis()).getTime()) / (1000 * 60)) + " min";
            if(order.getReadyDateOrd().getTime() - new Timestamp(System.currentTimeMillis()).getTime() <= 3600) {
            	status = "Prêt"; 
            	ready = "0 min";
            } else {
            	status = "En préparation";
            }

            // Ajouter les éléments dans le GridPane
            addLabelToGrid(Long.toString(order.getIdOrder()), i + 1, 0); // Ligne commence à 1 pour éviter l'en-tête
            addLabelToGrid(order.getNameOrd(), i + 1, 1);
            addLabelToGrid(status, i + 1, 2);
            addLabelToGrid(ready, i + 1, 3);
        }
        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);  // Rétablit l'affichage
    }
    
    @FXML
    public void initialize() {
        // Charger les commandes au démarrage
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    	
        Runnable task = () -> {
        	while(true) {
        		try {
        			Platform.runLater(() -> {
        				refreshGrid();
        			});
        			Thread.sleep(60000);
        		} catch(InterruptedException e) {
        			System.out.println("Le thread a été interrompu.");
        			break; // Quitter la boucle
        		}
        	}
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true); // Pour mettre le thread en fond
        thread.start();
    }
}
