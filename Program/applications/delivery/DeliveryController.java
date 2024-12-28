package applications.delivery;

import java.net.URL;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import dao.EmployeesDAO;
import dao.LineBasketDAO;
import dao.OrdersDAO;
import dao.SingleConnection;
import dao.TakeResponsabilityForDAO;
import interfaces.ControllerMustHave;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import tables.Employees;
import tables.LineBasket;
import tables.Orders;
import javafx.util.Duration;
import tables.TakeResponsabilityFor;
import utilClass.Command;
import utilClass.IdsMetaData;
import utilClass.ScenesMap;
import utilClass.UDPMultiCastApp;

public class DeliveryController implements Initializable, ControllerMustHave{
	
	@FXML
	private Label currentDate;
	@FXML
	private ComboBox<String> menuContainer;
	@FXML
	private ComboBox<Employees> deliveryManContainer;
	@FXML
	private VBox deliveryContainer;
	@FXML
	private ScrollPane commandesContainer; // Main menu to set visible or not visible
	@FXML
	private ScrollPane enRouteContainer; // Main menu to set visible or not visible
	@FXML
	private VBox goDeliveryContainer; // To set visible or not visible
	@FXML
	private VBox statDeliveryContainer; // To set visible or not visible
	@FXML
	private VBox presentEmployeeContainer; // To add or remove employee
	@FXML
	private VBox inDeliveryEmployeeContainer; // To add or remove employee
	@FXML
	private VBox takenDeliveryContainer; // To add or remove orders selected to delivery
	@FXML
	private VBox inDeliveryEmployeesDetailContainer; // To add or remove orders selected to delivery
	@FXML
	private Button goInDeliveryButton; // To add or remove orders selected to delivery
	@FXML
	private PasswordField passContainer;
	@FXML
	private FlowPane digitContainer;
	@FXML
	private Button sendDigit;
	@FXML
	private VBox resumeContainer;
	@FXML
	private VBox orderResumeContainer;
	@FXML
	private Button attributeButton;
	@FXML
	private Pane cachePane;
	@FXML
	private VBox errorContainer;
	@FXML
	private Text errorMessage;
	@FXML
	private Text resumeMessage;
	@FXML
	private VBox resumeEndDelivery;
	
	private Long lastMaxId;
	
	private HBox createDeliveryLine(Orders ord, Long lastMaxId) {
		// Créer la HBox principale
        HBox hbox = new HBox(10);
        Long realId = ord.getIdOrder();
        Long todayId = ord.getIdOrder() - lastMaxId;
        if (todayId <= 0) {
        	todayId = -1L;
        }
        IdsMetaData meta = new IdsMetaData(realId, todayId);
        hbox.setUserData(meta);
        hbox.getStyleClass().add("tableLine");
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(948);

        // Label "id commande du jour"
        Label idLabel = new Label(Long.toString(meta.getTodayId()));
        idLabel.getStyleClass().add("label-id");

        // Séparateur vertical avec marges
        Separator separator1 = new Separator(Orientation.VERTICAL);
        separator1.getStyleClass().add("separator-vertical");
        HBox.setMargin(separator1, new Insets(0, 15, 0, 15));

        // VBox pour l'adresse et le téléphone
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER_LEFT);
        vbox1.setMinWidth(421);

        String adress = ord.getIdCustomer().getStreetNumberCst() + " " + ord.getIdCustomer().getStreetNameCst() + " " + ord.getIdCustomer().getPostcodeCst();
        Label addressLabel = new Label(adress);
        addressLabel.getStyleClass().add("label-address-phone");
        
        Label phoneLabel = new Label(ord.getIdCustomer().getTelCst());
        phoneLabel.getStyleClass().add("label-address-phone");

        vbox1.getChildren().addAll(addressLabel, phoneLabel);

        // Deuxième séparateur vertical
        Separator separator2 = new Separator(Orientation.VERTICAL);
        separator2.getStyleClass().add("separator-full-height");
        HBox.setMargin(separator2, new Insets(0, 15, 0, 15));

        // VBox pour les informations sur la commande
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER_LEFT);

        // Calcul de la différence de temps entre l'heure actuelle et le timestamp
     	java.time.Duration duration = java.time.Duration.between(ord.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
        // Extraire les heures, minutes et secondes
        long hours = Math.abs(duration.toHours()); // Total des heures
        long minutes = Math.abs(duration.toMinutes() % 60); // Restant des minutes après les heures
        long seconds = Math.abs(duration.getSeconds() % 60); // Restant des secondes après les minutes
        // Formatage de la différence en HH:mm:ss
        String formattedTimer = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        
        Label orderedLabel = new Label("Commandé " + formattedTimer);
        orderedLabel.getStyleClass().add("label-command-info");
        
        Label ovenLabel = new Label();
        ovenLabel.getStyleClass().add("label-command-info");
        
        Label statusLabel = new Label();
        statusLabel.getStyleClass().add("label-status");

        // Calcul de la différence de temps entre l'heure actuelle et le timestamp
     	java.time.Duration duration_ready = java.time.Duration.between(ord.getReadyDateOrd().toLocalDateTime(), LocalDateTime.now());
        // Extraire les heures, minutes et secondes
        long hours_ready = Math.abs(duration_ready.toHours()); // Total des heures
        long minutes_ready = Math.abs(duration_ready.toMinutes() % 60); // Restant des minutes après les heures
        long seconds_ready = Math.abs(duration_ready.getSeconds() % 60); // Restant des secondes après les minutes
        // Formatage de la différence en HH:mm:ss
        String formattedTimer_ready = String.format("%02d:%02d:%02d", hours_ready, minutes_ready, seconds_ready);
        String stateOfOrd = "";
        
        if (ord.getReadyDateOrd().toLocalDateTime().isAfter(LocalDateTime.now())) { // La requête renvoie uniquement les commandes au four donc à T+7, ici on regarde juste si elles sont pas encore prêtes	
        	stateOfOrd = "FOUR";
        }else { // La commande est déjà prête depuis x temps
        	stateOfOrd = "ATTENTE";
        }
        
        if (ord.getReadyDateOrd().toLocalDateTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
        	ovenLabel.setTextFill(Color.RED);
        }
        
        ovenLabel.setText(stateOfOrd + " " + formattedTimer_ready);
        statusLabel.setText(stateOfOrd);
        
        Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        	java.time.Duration durationAnim = java.time.Duration.between(ord.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
        	long hoursAnim = Math.abs(durationAnim.toHours()); // Total des heures
            long minutesAnim = Math.abs(durationAnim.toMinutes() % 60); // Restant des minutes après les heures
            long secondsAnim = Math.abs(durationAnim.getSeconds() % 60); // Restant des secondes après les minutes
            String formattedTimerAnim = String.format("%02d:%02d:%02d", hoursAnim, minutesAnim, secondsAnim);
            orderedLabel.setText("Commandé " + formattedTimerAnim);
            
            // Calcul de la différence de temps entre l'heure actuelle et le timestamp
         	java.time.Duration duration_readyAnim = java.time.Duration.between(ord.getReadyDateOrd().toLocalDateTime(), LocalDateTime.now());
            // Extraire les heures, minutes et secondes
            long hours_readyAnim = Math.abs(duration_readyAnim.toHours()); // Total des heures
            long minutes_readyAnim = Math.abs(duration_readyAnim.toMinutes() % 60); // Restant des minutes après les heures
            long seconds_readyAnim = Math.abs(duration_readyAnim.getSeconds() % 60); // Restant des secondes après les minutes
            // Formatage de la différence en HH:mm:ss
            String formattedTimer_readyAnim = String.format("%02d:%02d:%02d", hours_readyAnim, minutes_readyAnim, seconds_readyAnim);
            
            String stateOfOrdAnim = "";
            if (ord.getReadyDateOrd().toLocalDateTime().isAfter(LocalDateTime.now())) { // La requête renvoie uniquement les commandes au four donc à T+7, ici on regarde juste si elles sont pas encore prêtes	
            	stateOfOrdAnim = "FOUR";
            }else { // La commande est déjà prête depuis x temps
            	stateOfOrdAnim = "ATTENTE";
            }
            
            if (ord.getReadyDateOrd().toLocalDateTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
            	ovenLabel.setTextFill(Color.RED);
            }
            
            ovenLabel.setText(stateOfOrdAnim + " " + formattedTimer_readyAnim);
            statusLabel.setText(stateOfOrdAnim);
        }));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();

        vbox2.getChildren().addAll(orderedLabel, ovenLabel, statusLabel);

        // Ajouter tous les enfants à la HBox principale
        hbox.getChildren().addAll(idLabel, separator1, vbox1, separator2, vbox2);
        
        HBox.setHgrow(vbox1, Priority.ALWAYS);
        HBox.setHgrow(vbox2, Priority.ALWAYS);
        
        hbox.setOnMouseClicked(event -> selectOrderToDelivery(event));

        return hbox;
    }
	
	private HBox createTakeDeliveryLine(Orders ord, Long lastMaxId) { // Commande selectionner pour partir en livraison
		// Create the main HBox
        HBox hbox = new HBox();
        Long realId = ord.getIdOrder();
        Long todayId = ord.getIdOrder() - lastMaxId;
        if (todayId <= 0) {
        	todayId = -1L;
        }
        IdsMetaData meta = new IdsMetaData(realId, todayId);
        hbox.setUserData(meta);
        hbox.setAlignment(Pos.CENTER);
        hbox.setFillHeight(false);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.getStyleClass().add("tableLine");

        // Create the first Label
        Label label1 = new Label(Long.toString(meta.getTodayId()));
        label1.setAlignment(Pos.CENTER);
        label1.setMinWidth(55.0);
        label1.setWrapText(true);
        label1.setFont(Font.font("System", FontWeight.BOLD, 30.0));
        
        // Create the Separator
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(75.0);
        HBox.setMargin(separator, new Insets(0, 5.0, 0, 5.0));

        // Create the VBox
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(vbox, javafx.scene.layout.Priority.ALWAYS);

        String adress = ord.getIdCustomer().getStreetNumberCst() + " " + ord.getIdCustomer().getStreetNameCst() + " " + ord.getIdCustomer().getPostcodeCst();
        // Create children for VBox
        Label addressLabel = new Label(adress);
        addressLabel.setMaxWidth(200.0);
        addressLabel.setPrefWidth(200.0);
        addressLabel.setPrefHeight(75.0);
        addressLabel.setWrapText(true);
        addressLabel.setFont(Font.font("System Italic", 16.0));

        Label phoneLabel = new Label(ord.getIdCustomer().getTelCst());
        phoneLabel.setFont(Font.font("System Italic", 16.0));
        
        // Calcul de la différence de temps entre l'heure actuelle et le timestamp
     	java.time.Duration duration = java.time.Duration.between(ord.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
        // Extraire les heures, minutes et secondes
        long hours = Math.abs(duration.toHours()); // Total des heures
        long minutes = Math.abs(duration.toMinutes() % 60); // Restant des minutes après les heures
        long seconds = Math.abs(duration.getSeconds() % 60); // Restant des secondes après les minutes
        // Formatage de la différence en HH:mm:ss
        String formattedTimer = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        Label orderTimeLabel = new Label("Commandé " + formattedTimer);
        orderTimeLabel.setFont(Font.font("System", 16.0));
        
        Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        	java.time.Duration durationAnim = java.time.Duration.between(ord.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
        	long hoursAnim = Math.abs(durationAnim.toHours()); // Total des heures
            long minutesAnim = Math.abs(durationAnim.toMinutes() % 60); // Restant des minutes après les heures
            long secondsAnim = Math.abs(durationAnim.getSeconds() % 60); // Restant des secondes après les minutes
            String formattedTimerAnim = String.format("%02d:%02d:%02d", hoursAnim, minutesAnim, secondsAnim);
            orderTimeLabel.setText("Commandé " + formattedTimerAnim);
        }));
        timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();

        // Add children to VBox
        vbox.getChildren().addAll(addressLabel, phoneLabel, orderTimeLabel);

        // Add all elements to HBox
        hbox.getChildren().addAll(label1, separator, vbox);
        
        hbox.setOnMouseClicked(event -> unSelectOrderToDelivery(event));

        return hbox;
	}
	
	private Label createPresentEmployees(Employees emp) { // Employee disponible
		Label employee = new Label(emp.getNameEmp());
		employee.getStyleClass().add("label-present-employee");
		employee.setMaxWidth(Double.POSITIVE_INFINITY);
		return employee;
	}
	
	private HBox createInDeliveryEmployees(TakeResponsabilityFor trf) { // Employee en route
		Employees emp = trf.getIdEmployee();
		// Créer la HBox principale
        HBox hbox = new HBox();
        hbox.setMaxWidth(Double.POSITIVE_INFINITY);
        
        Label employee = new Label(emp.getNameEmp());
		employee.getStyleClass().add("label-present-employee");
		employee.setMaxWidth(Double.POSITIVE_INFINITY);
		
        // Calcul de la différence de temps entre l'heure actuelle et le timestamp
		java.time.Duration duration = java.time.Duration.between(trf.getStartDateTake().toLocalDateTime(), LocalDateTime.now());
        // Extraire les heures, minutes et secondes
        long hours = duration.toHours(); // Total des heures
        long minutes = duration.toMinutes() % 60; // Restant des minutes après les heures
        long seconds = duration.getSeconds() % 60; // Restant des secondes après les minutes
        // Formatage de la différence en HH:mm:ss
        String formattedTimer = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        
        Label timer = new Label(formattedTimer);
        timer.getStyleClass().add("label-present-employee");
        timer.setMaxWidth(Double.POSITIVE_INFINITY);
        timer.setAlignment(Pos.CENTER_RIGHT);
        
        Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        	java.time.Duration durationAnim = java.time.Duration.between(trf.getStartDateTake().toLocalDateTime(), LocalDateTime.now());
        	long hoursAnim = durationAnim.toHours(); // Total des heures
            long minutesAnim = durationAnim.toMinutes() % 60; // Restant des minutes après les heures
            long secondsAnim = durationAnim.getSeconds() % 60; // Restant des secondes après les minutes
            String formattedTimerAnim = String.format("%02d:%02d:%02d", hoursAnim, minutesAnim, secondsAnim);
            timer.setText(formattedTimerAnim);
        }));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
        
        HBox.setHgrow(employee, Priority.ALWAYS);
        HBox.setHgrow(timer, Priority.ALWAYS);
        
        hbox.getChildren().addAll(employee, timer);
        
		return hbox;
	}
	
	private HBox createInDeliveryEmployeesDetail(Employees emp, ArrayList<Orders> ordList, long lastMaxId) {
		// Créer la HBox principale
        HBox hbox = new HBox();
        hbox.getStyleClass().add("tableLine");
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(948);
        
        // VBox pour l'adresse et le téléphone
        VBox vbox0 = new VBox();
        vbox0.setAlignment(Pos.CENTER);

        // Séparateur vertical avec marges
        Separator separator1 = new Separator(Orientation.VERTICAL);
        separator1.getStyleClass().add("separator-vertical");
        HBox.setMargin(separator1, new Insets(0, 15, 0, 15));

        // VBox pour l'adresse et le téléphone
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER_LEFT);
        vbox1.setMinWidth(421);
        
        // Deuxième séparateur vertical
        Separator separator2 = new Separator(Orientation.VERTICAL);
        separator2.getStyleClass().add("separator-full-height");
        HBox.setMargin(separator2, new Insets(0, 15, 0, 15));

        // VBox pour les informations sur la commande
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER_LEFT);
        
        ordList.stream().forEach(order -> {
        	String idOrd = Long.toString(order.getIdOrder() - lastMaxId);
        	if (order.getIdOrder() - lastMaxId <= 0) {
        		idOrd = "-1";
            }
            Label idLabel = new Label(idOrd);
            idLabel.getStyleClass().add("label-id");
            vbox0.getChildren().add(idLabel);
            
            String adress = order.getIdCustomer().getStreetNumberCst() + " " + order.getIdCustomer().getStreetNameCst() + " " + order.getIdCustomer().getPostcodeCst();
            Label addressLabel = new Label(adress);
            addressLabel.getStyleClass().add("label-address-phone");
            Label phoneLabel = new Label(order.getIdCustomer().getTelCst());
            phoneLabel.getStyleClass().add("label-address-phone");
            
            vbox1.getChildren().addAll(addressLabel, phoneLabel);
            
            // Calcul de la différence de temps entre l'heure actuelle et le timestamp
         	java.time.Duration duration = java.time.Duration.between(order.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
            // Extraire les heures, minutes et secondes
            long hours = Math.abs(duration.toHours()); // Total des heures
            long minutes = Math.abs(duration.toMinutes() % 60); // Restant des minutes après les heures
            long seconds = Math.abs(duration.getSeconds() % 60); // Restant des secondes après les minutes
            // Formatage de la différence en HH:mm:ss
            String formattedTimer = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            
            Label orderedLabel = new Label("Commandé " + formattedTimer);
            orderedLabel.getStyleClass().add("label-command-info");
            
            Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            	java.time.Duration durationAnim = java.time.Duration.between(order.getTakingDateOrd().toLocalDateTime(), LocalDateTime.now());
            	long hoursAnim = durationAnim.toHours(); // Total des heures
                long minutesAnim = durationAnim.toMinutes() % 60; // Restant des minutes après les heures
                long secondsAnim = durationAnim.getSeconds() % 60; // Restant des secondes après les minutes
                String formattedTimerAnim = String.format("%02d:%02d:%02d", hoursAnim, minutesAnim, secondsAnim);
                orderedLabel.setText("Commandé " + formattedTimerAnim);
            }));
        	timeUpdate.setCycleCount(Timeline.INDEFINITE);
        	timeUpdate.play();
            
            vbox2.getChildren().add(orderedLabel);
        });

        Label statusLabel = new Label(emp.getNameEmp() + " | " + emp.getCodeEmp());
        statusLabel.getStyleClass().add("label-status");

        vbox2.getChildren().add(statusLabel);

        // Ajouter tous les enfants à la HBox principale
        hbox.getChildren().addAll(vbox0, separator1, vbox1, separator2, vbox2);
        
        HBox.setHgrow(vbox1, Priority.ALWAYS);
        HBox.setHgrow(vbox2, Priority.ALWAYS);

        return hbox;
	}
	
	private VBox createResumeLine(ArrayList<LineBasket> lbList, Long idOrder) {
		// Création de la VBox
	    VBox vbox = new VBox();
	    vbox.setMaxWidth(Double.MAX_VALUE);
	    vbox.setSpacing(3.0);
	    vbox.setStyle("-fx-border-color: white; -fx-border-width: 2 0 0 0;");
	    
	    Label label1 = new Label("Commande " + Long.toString(idOrder));
	    label1.setMaxWidth(Double.MAX_VALUE);
	    label1.setTextFill(Color.WHITE);
	    label1.setFont(Font.font("System", FontWeight.BOLD, 20.0));
	    
	    vbox.getChildren().add(label1);
	    
	    lbList.stream().forEach(linebasket -> { // Linebasket List déjà filtrer par idOrder donc pas besoin de regarder ça juste besoin de prendre les infos
	    	int qte = linebasket.getQtyProductLB();
	    	String size = linebasket.getIdProduct().getSizeProduct();
	    	String category = linebasket.getIdProduct().getCategoryProduct();
	    	String name = linebasket.getIdProduct().getNameProduct();
	    	Label label = new Label("(" + qte +") " + size + " " + category + " " + name);
	    	label.setMaxWidth(Double.MAX_VALUE);
	    	label.setTextFill(Color.WHITE);
	    	label.setFont(Font.font("System", 20.0));
	    	
	    	vbox.getChildren().add(label);
	    });

	    return vbox;
	}
	
	@FXML
	private void changeMainDeliveryMenu(ActionEvent event) {
		deliveryContainer.getChildren().clear();
		refreshData();
		String choosenMenu = menuContainer.getValue();
		if (choosenMenu == "Commandes") {
			commandesContainer.setVisible(true);
			enRouteContainer.setVisible(false);
		}else if (choosenMenu == "En route"){
			commandesContainer.setVisible(false);
			enRouteContainer.setVisible(true);
		}
	}
	
	@FXML
	private void selectOrderToDelivery(MouseEvent event) {
		Node order = (Node) event.getTarget();
		
		while (!(order instanceof HBox)) { // Possible uniquement si il y a un seul HBox parent qui contient tout
			order = order.getParent(); 	   // sinon il faut faire d'autre vérif pour être sûr que c'est le conteneur
		}
		
		Long orderId = ((IdsMetaData)order.getUserData()).getRealId();
		
		// Requête pour récupérer les infos de la commandes avec l'id, vérifier si elle est toujours disponible après :
		OrdersDAO ordDAO = new OrdersDAO();
    	Orders ord = ordDAO.readDeliveryOrderReady(orderId);
    	
    	if (ord != null) { // Vérif si aucun résultat, la commande n'est plus disponible pour X raison
    		lastMaxId = ordDAO.orderNumberStarting();
    		takenDeliveryContainer.getChildren().add(createTakeDeliveryLine(ord, lastMaxId));
    		refreshData();
    	}
	}
	
	@FXML
	private void unSelectOrderToDelivery(MouseEvent event) {
		Node order = (Node) event.getTarget();
		
		while (!(order instanceof HBox)) { // Possible uniquement si il y a un seul HBox parent qui contient tout
			order = order.getParent(); 	   // sinon il faut faire d'autre vérif pour être sûr que c'est le conteneur
		}
		
		takenDeliveryContainer.getChildren().remove(order);
		
		refreshData();
	}
	
	@FXML
	private void showDigit(ActionEvent event) {
		Node clickedButton = (Node) event.getTarget();
		String id = clickedButton.getId();
		
		if (id.equals("retour")) {
			sendDigit.setOnAction(newevent -> comeBackDelivery(newevent));
		}else if (id.equals("attribuer")) {
			sendDigit.setOnAction(newevent -> attributeDelivery(newevent));
		}
		
		digitContainer.setVisible(true);
		cachePane.setVisible(true);
		
		passContainer.requestFocus();
	}
	
	@FXML
    private void digitInput(ActionEvent event) {
    	int length = passContainer.getLength();
    	passContainer.requestFocus();
    	passContainer.selectRange(length, length);
    	Node clickedButton = (Node) event.getTarget();
    	String[] digitId = clickedButton.getId().split("-");
    	String digit = digitId[1];
    	if (!digit.equals("del") && length < 4) {
    		passContainer.appendText(digit);
    	}else if (digit.equals("del") && length > 0){
    		passContainer.deleteText(length - 1, length);
    	}
    }
	
	@FXML
	private void hideDigit(ActionEvent event) {
		passContainer.clear();
		digitContainer.setVisible(false);
		cachePane.setVisible(false);
	}
	
	@FXML
	private void mainPane(ActionEvent event) {
		passContainer.clear();
		digitContainer.setVisible(false);
		resumeContainer.setVisible(false);
	}
	
	private void showResumeEndDelivery(String message) {
		resumeMessage.setText(message);
		resumeEndDelivery.setVisible(true);
		digitContainer.setVisible(false);
		cachePane.setVisible(true);
	}
	
	@FXML
	private void okResumeEndDelivery(ActionEvent event) {
		resumeEndDelivery.setVisible(false);
		cachePane.setVisible(false);

		refreshData();
	}
	
	@FXML
	private void attributeDelivery(ActionEvent event) {
		if (passContainer.getLength() == 4) {
			//System.out.println("I choose this deliver man : " + passContainer.getText());
			String codeEmp = passContainer.getText();
			EmployeesDAO empDAO = new EmployeesDAO();
			Employees emp = empDAO.readDisponibleDeliveryMan(codeEmp);
			
			if (emp != null) { // Employée existant
				digitContainer.setVisible(false);
				deliveryManContainer.setValue(emp);
			}else {
				throwErrorMessage("Le code saisi est incorrecte OU l'employé n'est pas disponible.");
			}
			passContainer.clear();
			cachePane.setVisible(false);
		}
	}
	
	@FXML
	private void goOnDelivery(ActionEvent event) {
		Employees emp = deliveryManContainer.getValue();
		if (emp != null) {
			resumeContainer.setVisible(true);
			
			goInDeliveryButton.setVisible(false);
			goInDeliveryButton.setMinHeight(0);
			deliveryManContainer.setDisable(true);
			
			takenDeliveryContainer.getChildren().stream().forEach(order -> {
				order.setDisable(true);
				Long realId = ((IdsMetaData) order.getUserData()).getRealId();
				// Faire une requête sur linebasket(long idOrder) pour avoir tout les produits de la commandes
				LineBasketDAO lbDAO = new LineBasketDAO();
				ArrayList<LineBasket> lbList = lbDAO.readLBInOrder(realId);
				orderResumeContainer.getChildren().add(createResumeLine(lbList, ((IdsMetaData) order.getUserData()).getTodayId()));
			});
			attributeButton.setDisable(true);
			attributeButton.getStyleClass().remove("clickableButton");
		}
		
	}
	
	@FXML
	private void comeBackDelivery(ActionEvent event) {
		if (passContainer.getLength() == 4) {
			//System.out.println("I came back : " + passContainer.getText());
			String codeEmp = passContainer.getText();
			
			TakeResponsabilityForDAO trfDAO = new TakeResponsabilityForDAO();
			ArrayList<TakeResponsabilityFor> trfList = trfDAO.readInDeliveryEmployee(codeEmp);
			
			if (!trfList.isEmpty()) { // L'employé est bien en livraison
				OrdersDAO ordDAO = new OrdersDAO();
				Employees emp = trfList.getFirst().getIdEmployee();
				
				Timestamp endDateTake = new Timestamp(System.currentTimeMillis());
				
				// REQUETE EN MODE BATCH
				SingleConnection.setAutoCommit(false);
				// Requêtes
				ArrayList<Orders> ordToUpdate = new ArrayList<Orders>();
				ArrayList<TakeResponsabilityFor> trfToUpdate = new ArrayList<TakeResponsabilityFor>();
				trfList.stream().forEach(trf -> {
					trf.setEndDateTake(endDateTake);
					if (trf.isPaymentTakeON()) {
						trf.getIdOrder().setPayOrdON(true);
						ordToUpdate.add(trf.getIdOrder());
					}
					trfToUpdate.add(trf);
				});
				// Execution des requêtes
				ordDAO.update(ordToUpdate);
				trfDAO.update(trfToUpdate);
				SingleConnection.setAutoCommit(true);
				ordDAO.clearBatch();
				trfDAO.clearBatch();
				
				// Calcul de la différence de temps entre l'heure actuelle et le timestamp
	         	java.time.Duration duration = java.time.Duration.between(trfList.getFirst().getStartDateTake().toLocalDateTime(), LocalDateTime.now());
	            // Extraire les heures, minutes et secondes
	            long hours = Math.abs(duration.toHours()); // Total des heures
	            long minutes = Math.abs(duration.toMinutes() % 60); // Restant des minutes après les heures
	            long seconds = Math.abs(duration.getSeconds() % 60); // Restant des secondes après les minutes
	            // Formatage de la différence en HH:mm:ss
	            String formattedTimer = String.format("%02d:%02d:%02d", hours, minutes, seconds);
				String resumeMsg = "TEMPS : " + formattedTimer + " | " + emp.getNameEmp();
				showResumeEndDelivery(resumeMsg);
				refreshAllApp();
			}else {
				throwErrorMessage("Le code saisi est incorrect OU l'employé n'est pas en livraison.");
			}
			
			passContainer.clear();
			refreshData();
		}
	}
	
	@FXML
	private void acceptDelivery(ActionEvent event) {
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		if (deliveryManContainer.getValue() != null) {
			emp = empDAO.read(deliveryManContainer.getValue().getIdEmployee());
		}
		if (emp != null) {
			final Employees deliveryMan = emp;
			Timestamp startDateTake = new Timestamp(System.currentTimeMillis());
			//System.out.println("Commande accépté par : " + emp.toString());
			
			ArrayList<TakeResponsabilityFor> trfToInsert = new ArrayList<TakeResponsabilityFor>();
			OrdersDAO ordDAO = new OrdersDAO();
			takenDeliveryContainer.getChildren().stream().forEach(order -> {
				Long idOrder = ((IdsMetaData)order.getUserData()).getRealId();
				Orders ord = ordDAO.read(idOrder);
				if (ord != null) {
					TakeResponsabilityFor trf = new TakeResponsabilityFor(ord, deliveryMan, true, !ord.isPayOrdON(), startDateTake, null);
					trfToInsert.add(trf);
				}
			});
			
			// SQL BATCH
			SingleConnection.setAutoCommit(false);
			TakeResponsabilityForDAO trfDAO = new TakeResponsabilityForDAO();
			trfDAO.create(trfToInsert);
			SingleConnection.setAutoCommit(true);
			
			takenDeliveryContainer.getChildren().clear();
			
			mainPane(event);
			
			deliveryManContainer.setDisable(false);
			
			orderResumeContainer.getChildren().clear();
			
			refreshAllApp();
		}
	}
	
	@FXML
	private void declineDelivery(ActionEvent event) {
		mainPane(event);
		deliveryManContainer.setDisable(false);
		goInDeliveryButton.setVisible(true);
		goInDeliveryButton.setMinHeight(70);
		takenDeliveryContainer.getChildren().stream().forEach(order -> {
			order.setDisable(false);
		});
		orderResumeContainer.getChildren().clear();
		refreshData();
	}
	
	private void throwErrorMessage(String message) {
		digitContainer.setVisible(false);
		passContainer.clear();
		errorMessage.setText(message);
		errorContainer.setVisible(true);
		cachePane.setVisible(true);
	}
	
	@FXML
	private void okError(ActionEvent event) {
		errorContainer.setVisible(false);
		cachePane.setVisible(false);
	}
	
	private void refreshDeliveryManSelector(Boolean reset) {
		EmployeesDAO empDAO = new EmployeesDAO();
    	ArrayList<Employees> empDisponibleList = empDAO.readDisponibleDeliveryMan();
    	Employees selectedEmp = deliveryManContainer.getValue();
    	Boolean tester = false;
    	
    	if (deliveryManContainer.getValue() != null && !reset) {
    		tester = true;
    	}
    	deliveryManContainer.getItems().clear();
    	deliveryManContainer.getItems().addAll(empDisponibleList);
    	//empDisponibleList.stream().forEach(emp -> deliveryManContainer.getItems().add(emp));
		
    	if (tester) {
    		deliveryManContainer.setValue(selectedEmp);
		}else {
			deliveryManContainer.setValue(null);
			deliveryManContainer.getSelectionModel().clearSelection();
		}
	}
	
    @Override
    public void refreshData() {
    	System.out.println("Refresh Delivery");
    	
    	EmployeesDAO empDAO = new EmployeesDAO();
    	ArrayList<Employees> empDisponibleList = empDAO.readDisponibleDeliveryMan();
    	presentEmployeeContainer.getChildren().clear();
    	empDisponibleList.stream().map(employee -> createPresentEmployees(employee)).forEach(Label -> presentEmployeeContainer.getChildren().add(Label));;
    	
    	TakeResponsabilityForDAO trfDAO = new TakeResponsabilityForDAO();
    	ArrayList<TakeResponsabilityFor> trfList = trfDAO.readInDeliveryEmployee();
    	inDeliveryEmployeeContainer.getChildren().clear();
    	trfList.stream().map(trf -> createInDeliveryEmployees(trf)).forEach(HBox -> inDeliveryEmployeeContainer.getChildren().add(HBox));
    	
    	OrdersDAO ordDAO = new OrdersDAO();
    	lastMaxId = ordDAO.orderNumberStarting();
    	ArrayList<Orders> ordList = ordDAO.readDeliveryOrderReady();
    	deliveryContainer.getChildren().clear();
    	ordList.stream().map(order -> createDeliveryLine(order, lastMaxId)).forEach(HBox -> { // Pour chaque commande en livraison
    		
    		if (takenDeliveryContainer.getChildren().size() > 0) {
    			Boolean tester = false;
    			for (Node orderSelected : takenDeliveryContainer.getChildren()) {
    				if (((IdsMetaData) orderSelected.getUserData()).getRealId() == ((IdsMetaData) HBox.getUserData()).getRealId() && !tester) {
    					tester = true;
        			}
    			}
    			
    			if (!tester) {
    				deliveryContainer.getChildren().add(HBox);
    			}
    			
    		}else {
    			deliveryContainer.getChildren().add(HBox);
    		}		
    		
    	});;
    	
    	ArrayList<TakeResponsabilityFor> ordInDeliveryList = trfDAO.readInDeliveryEmployee();
    	ordInDeliveryList.stream().forEach(trf -> {
    		Boolean tester = false;
    		for (Node orderSelected : takenDeliveryContainer.getChildren()) {
				if (((IdsMetaData) orderSelected.getUserData()).getRealId() == trf.getIdOrder().getIdOrder() && !tester) {
					tester = true;
					Platform.runLater(() -> {
						takenDeliveryContainer.getChildren().remove(orderSelected);
						if (takenDeliveryContainer.getChildren().size() > 0) {
				    		refreshDeliveryManSelector(false);
				    	}else {
				    		refreshDeliveryManSelector(true);
				    	}
						refreshInterface();
					});
    			}
			}
    	});
    	
    	
    	HashMap<Employees, ArrayList<Orders>> ordersInDelivery = trfDAO.readInDeliveryOrders();
    	inDeliveryEmployeesDetailContainer.getChildren().clear();
    	ordersInDelivery.forEach((emp, ordersList) -> {
    		inDeliveryEmployeesDetailContainer.getChildren().add(createInDeliveryEmployeesDetail(emp, ordersList, lastMaxId));
    	});
    	
    	if (takenDeliveryContainer.getChildren().size() > 0) {
    		refreshDeliveryManSelector(false);
    	}else {
    		refreshDeliveryManSelector(true);
    	}
    	
    	refreshInterface();
	}
    
    private void refreshAllApp() { // App to refresh -> HISTORICAL_HISTORICAL, HISTORICAL_PAYMENT, DELIVERY
    	UDPMultiCastApp.sendCommand(Command.REFRESH.getCommand() + ScenesMap.HISTORICAL_HISTORICAL.name() + "," + ScenesMap.HISTORICAL_PAYMENT.name() + "," + ScenesMap.DELIVERY.name());
    }
    
    @FXML
    private void refreshInterface() {
    	if (takenDeliveryContainer.getChildren().size() > 0 && deliveryManContainer.getValue() != null) { // Au moin un élément selectionner et un livreur aussi
			goInDeliveryButton.setVisible(true);
			goInDeliveryButton.setMinHeight(70);
			
			attributeButton.setDisable(false);
			if (!attributeButton.getStyleClass().contains("clickableButton")) {
				attributeButton.getStyleClass().add("clickableButton");
			}
			
			//refreshDeliveryManSelector(false);
			
			statDeliveryContainer.setVisible(false);
			
			goDeliveryContainer.setVisible(true);
			
			menuContainer.setDisable(true);
		}else if (takenDeliveryContainer.getChildren().size() > 0){ // Au moin un élément selectionner
			goInDeliveryButton.setVisible(false);
			goInDeliveryButton.setMinHeight(0);
			
			attributeButton.setDisable(false);
			if (!attributeButton.getStyleClass().contains("clickableButton")) {
				attributeButton.getStyleClass().add("clickableButton");
			}

			//refreshDeliveryManSelector(false);
			
			statDeliveryContainer.setVisible(false);
			
			goDeliveryContainer.setVisible(true);
			
			menuContainer.setDisable(true);
		}else { // Aucun élément selectionner
			goInDeliveryButton.setVisible(false);
			goInDeliveryButton.setMinHeight(0);
			
			attributeButton.setDisable(true);
			attributeButton.getStyleClass().remove("clickableButton");
			
			//refreshDeliveryManSelector(true);
			
			statDeliveryContainer.setVisible(true);
			
			goDeliveryContainer.setVisible(false);
			
			menuContainer.setDisable(false);
		}
    	
    	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Init Delivery");
    	
    	passContainer.addEventFilter(KeyEvent.KEY_TYPED, e -> {
    	    if (!e.getCharacter().matches("[0-9]") || passContainer.getLength() >= 4) {
    	        e.consume();  // Empêche l'entrée si c'est pas un chiffre
    	    }

            if (e.getCharacter().equals("\r")) {
                // Si la touche Entrée est pressée, appeler la méthode
                sendDigit.fire();
            }
    	});
    	
    	menuContainer.getItems().addAll("Commandes", "En route");
    	menuContainer.getSelectionModel().selectFirst();
    	
    	refreshData();
    	cachePane.setVisible(false);
    	mainPane(null);
    	currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")));
    	Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> currentDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss")))));
    	timeUpdate.setCycleCount(Timeline.INDEFINITE);
    	timeUpdate.play();
    }   

    
}
