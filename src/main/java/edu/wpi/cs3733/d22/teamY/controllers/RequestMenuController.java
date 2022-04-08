package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RequestMenuController {
	@FXML
	Pane sidebarPane;
	@FXML
	private JFXButton closeSidebarHiddenButton;
	@FXML
	private JFXHamburger sidebarHamburger;

	AnchorPane sidebar = null;

	public RequestMenuController() throws IOException {
	}

	@FXML
	void initialize() throws IOException {
		// Show sidebar
		sidebar = SidebarUtil.initializeSidebar(sidebarPane);
		openSidebarLayout();
	}

	// All below methods call corresponding scene setting methods in an instance of app
	// These are called by the corresponding button in the Request Menu (RequestMenu.fxml)

	@FXML
	void mainMenu() throws IOException {
		SceneLoading.loadScene("views/Map.fxml");
	}

	@FXML
	void securityServices() throws IOException {
		SceneLoading.loadScene("views/requestTypes/SecurityServicesRequest.fxml");
	}

	@FXML
	void medicalEquipment() throws IOException {
		SceneLoading.loadScene("views/requestTypes/MedicalEquipmentRequest.fxml");
	}

	@FXML
	void floralDelivery() throws IOException {
		SceneLoading.loadScene("views/requestTypes/FloralRequest.fxml");
	}

	@FXML
	void laundryServices() throws IOException {
		SceneLoading.loadScene("views/requestTypes/LaundryRequest.fxml");
	}

	@FXML
	void mealDelivery() throws IOException {
		SceneLoading.loadScene("views/requestTypes/MealRequest.fxml");
	}

	@FXML
	void labResults() throws IOException {
		SceneLoading.loadScene("views/requestTypes/LabRequest.fxml");
	}

	// Sidebar
	@FXML
	void openSidebarLayout() {
		SidebarUtil.openSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
	}

	@FXML
	void closeSidebarLayout() {
		SidebarUtil.closeSidebar(sidebar, closeSidebarHiddenButton, sidebarHamburger);
	}
}
