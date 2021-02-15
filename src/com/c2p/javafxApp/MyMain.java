package com.c2p.javafxApp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {

	Controller controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		System.out.println("Initialization successful.");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();
		controller = loader.getController();
		MenuBar menuBar = createMenu();

		rootNode.getChildren().add(0, menuBar);

		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Conversion");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private MenuBar createMenu() {

		// file menu
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");

		newMenuItem.setOnAction(event -> {  // LAMBDA to reduce no. of lines
			controller.newConversion();
		});

		MenuItem quitMenuItem = new MenuItem("Quit");

		quitMenuItem.setOnAction(event -> { // LAMBDA to reduce no. of lines
			Platform.exit();
			System.exit(0);
		});

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		fileMenu.getItems().addAll(newMenuItem, separatorMenuItem, quitMenuItem);

		// help menu
		Menu helpMenu = new Menu("Help");
		MenuItem aboutApp = new MenuItem("About");

		aboutApp.setOnAction(event -> aboutApp());

		helpMenu.getItems().addAll(aboutApp);

		// Menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);

		return menuBar;
	}

	private void aboutApp() {

		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("Temperature Conversion Tool");
		alertDialog.setHeaderText("Simple Temperature Converter");
		alertDialog.setContentText("This is a JavaFX app to help quickly convert\n" +
				"celsius to fahrenheit and vice-versa.");

		ButtonType yesBtn = new ButtonType("Yes");
		ButtonType noBtn = new ButtonType("No");

		alertDialog.getButtonTypes().setAll(yesBtn, noBtn);

		Optional<ButtonType> clickedBtn = alertDialog.showAndWait();

		if ( clickedBtn.isPresent() && clickedBtn.get() == yesBtn ) {
			System.out.println("Yes Button Clicked! ");
		} else {
			System.out.println("No Button Clicked!");
		}

	}

	@Override
	public void stop() throws Exception {
		super.stop();
		System.out.println("Exit successful.");
	}
}
