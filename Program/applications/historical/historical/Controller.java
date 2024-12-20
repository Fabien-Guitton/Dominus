package applications.historical.historical;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import dao.OrdersDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tables.Orders;

public class Controller implements Initializable{
	
	@FXML
	private VBox orderContainer;
	@FXML
	private Button showOrder;
	@FXML
	private Label currentDate;
	
	private Node selected;
	/**
     * Creates a Pane for a User object
     */
    private HBox createLine(Orders ord, Long lastMaxId) {
    	// Créer une instance de HBox
        HBox hbox = new HBox(13);  // Spacing = 13.0
        hbox.setId(Long.toString(ord.getIdOrder()));
        hbox.setOnMouseClicked(event -> selectLine(event));
        hbox.setMaxWidth(Double.POSITIVE_INFINITY);
        hbox.getStyleClass().add("tableLine"); // Ajouter la classe tableLine pour appliquer le style

        // Créer les Labels avec les classes CSS appropriées
        Label label1 = new Label(Long.toString(ord.getIdOrder() - lastMaxId));
        label1.getStyleClass().addAll("labelStyle", "bold", "centered");
        label1.setPrefHeight(80);
        label1.setPrefWidth(178);
        label1.setMaxWidth(Double.POSITIVE_INFINITY);

        String name = "";
        String num = "";
        if (ord.getIdCustomer() != null) {
        	name = ord.getIdCustomer().getNameCst();
        	num = ord.getIdCustomer().getTelCst();
        }else {
        	name = ord.getNameOrd();
        }
        
        Label label2 = new Label(name);
        label2.getStyleClass().addAll("labelStyle", "normal", "wrapText");
        label2.setPrefHeight(80);
        label2.setPrefWidth(291);
        label2.setMaxWidth(Double.POSITIVE_INFINITY);

        Label label3 = new Label(ord.getTypeOrd());
        label3.getStyleClass().addAll("labelStyle", "large");
        label3.setPrefHeight(80);
        label3.setPrefWidth(148);
        label3.setMaxWidth(Double.POSITIVE_INFINITY);

        LocalDateTime t = ord.getTakingDateOrd().toLocalDateTime();
        Label label4 = new Label(t.getHour() + ":" + t.getMinute());
        label4.getStyleClass().addAll("labelStyle", "large");
        label4.setPrefHeight(80);
        label4.setPrefWidth(114);
        label4.setMaxWidth(Double.POSITIVE_INFINITY);

        // Créer un format monétaire pour la locale française (France)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        String formattedAmount = currencyFormat.format(ord.getPriceITOrd());
        Label label5 = new Label(formattedAmount);
        label5.getStyleClass().addAll("labelStyle", "large", "wrapText");
        label5.setPrefHeight(80);
        label5.setPrefWidth(123);
        label5.setMaxWidth(Double.POSITIVE_INFINITY);

        Label label6 = new Label(num);
        label6.getStyleClass().addAll("labelStyle", "large");
        label6.setPrefHeight(80);
        label6.setPrefWidth(148);
        label6.setMaxWidth(Double.POSITIVE_INFINITY);
        
        HBox.setHgrow(label1, Priority.ALWAYS);
        HBox.setHgrow(label2, Priority.ALWAYS);
        HBox.setHgrow(label3, Priority.ALWAYS);
        HBox.setHgrow(label4, Priority.ALWAYS);
        HBox.setHgrow(label5, Priority.ALWAYS);
        HBox.setHgrow(label6, Priority.ALWAYS);

        // Ajouter les labels à l'HBox
        hbox.getChildren().addAll(label1, label2, label3, label4, label5, label6);

        // Retourner l'HBox
        return hbox;
    }
    
    @FXML
    public void selectLine(MouseEvent event) {
    	showOrder.getStyleClass().add("clickableButton");
    	Node clickedItem = (Node) event.getTarget();
    	Parent line = null;
    	
    	if (clickedItem instanceof Label) {
    		line = clickedItem.getParent();
    	}else if (clickedItem instanceof Text) {
    		line = clickedItem.getParent().getParent();
    	}else {
    		line = (Parent) clickedItem;
    	}
    	
    	if(selected == null) {
    		selected = line;
    	}
    	
    	selected.getStyleClass().remove("selectLine");
    	line.getStyleClass().add("selectLine");
    	selected = line;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    	OrdersDAO ordDAO = new OrdersDAO();
    	ArrayList<Orders> orders = ordDAO.readToday();
        Long lastMaxId = ordDAO.orderNumberStarting();
        
        orders.stream().map(order -> createLine(order, lastMaxId)).forEach(HBox -> orderContainer.getChildren().add(HBox));
        
    }    

    
}
