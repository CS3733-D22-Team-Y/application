package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ChangeThemeController {
	// Base pane for displaying new scenes
	@FXML private Pane mapPane;
	// Sidebar
	@FXML private Pane sidebarPane;
	@FXML private JFXButton closeSidebarHiddenButton;
	@FXML private JFXHamburger sidebarHamburger;
	@FXML private JFXButton theme1Button;
	@FXML private JFXButton normalTheme;
	AnchorPane sidebar = null;
	String activeTheme = "views/css/Theme1.css";

	@FXML
	void initialize() throws IOException {
		sidebar = SidebarUtil.initializeSidebar(sidebarPane);
		openSidebarLayout();
	}

	@FXML
	void mainMenu() throws IOException {
		SceneLoading.loadScene("views/Map.fxml");
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

	@FXML
	void changeTheme() throws IOException {
		theme1Button.getScene().getStylesheets().clear();
		theme1Button
				.getScene()
				.getStylesheets()
				.add(
						Objects.requireNonNull(App.class.getResource("views/css/Theme1.css")).toExternalForm());
	}

	@FXML
	void backToNormal() throws IOException {
		normalTheme.getScene().getStylesheets().clear();
		normalTheme
				.getScene()
				.getStylesheets()
				.add(
						Objects.requireNonNull(App.class.getResource("views/css/NormalTheme.css"))
								.toExternalForm());
	}
}
