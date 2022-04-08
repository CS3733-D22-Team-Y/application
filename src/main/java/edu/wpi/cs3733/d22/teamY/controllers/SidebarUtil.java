package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SidebarUtil {

	/**
	* Initializes the sidebar. Should be called in the initialize() function of any screen that has a
	* sideber.
	*
	* @param sidebarPane The pane the sidebar is put on.
	* @return The AnchorPane the elements of the sidebar are put on.
	* @throws IOException
	*/
	protected static AnchorPane initializeSidebar(Pane sidebarPane) throws IOException {
		Pane paneToLoad = FXMLLoader.load(App.class.getResource("views/SideBar.fxml"));
		AnchorPane sidebar = (AnchorPane) paneToLoad.lookup("#mainPane");
		sidebarPane.getChildren().clear();
		sidebarPane.getChildren().add(paneToLoad);
		return sidebar;
	}

	/**
	* Provides the necessary functionality to open the sidebar.
	*
	* @param sidebar The pane the sidebar is on.
	* @param closeSidebarHiddenButton The hidden button on the main scene's pane.
	* @param sidebarHamburger The hamburger button on the main scene's pane.
	*/
	protected static void openSidebar(
			AnchorPane sidebar, JFXButton closeSidebarHiddenButton, JFXHamburger sidebarHamburger) {
		sidebarHamburger.setVisible(false);
		closeSidebarHiddenButton.setVisible(true);
		sidebar.setVisible(true);
	}

	/**
	* Provides the necessary functionality to close the sidebar.
	*
	* @param sidebar The pane the sidebar is on.
	* @param closeSidebarHiddenButton The hidden button on the main scene's pane.
	* @param sidebarHamburger The hamburger button on the main scene's pane.
	*/
	protected static void closeSidebar(
			AnchorPane sidebar, JFXButton closeSidebarHiddenButton, JFXHamburger sidebarHamburger) {
		sidebarHamburger.setVisible(true);
		closeSidebarHiddenButton.setVisible(false);
		sidebar.setVisible(false);
	}
}
