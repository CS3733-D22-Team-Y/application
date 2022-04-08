package edu.wpi.cs3733.d22.teamY.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import edu.wpi.cs3733.d22.teamY.DBManager;
import edu.wpi.cs3733.d22.teamY.model.MedEquipReq;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ActServReqTablePageController {
	@FXML private TableView<MedEquipReq> medEquipReqTableView;

	@FXML Pane sidebarPane;
	@FXML private JFXButton closeSidebarHiddenButton;
	@FXML private JFXHamburger sidebarHamburger;

	AnchorPane sidebar = null;

	public void initialize() throws IOException {
		List<MedEquipReq> medEquipReqs;
		try {
			medEquipReqs = DBManager.getAll(MedEquipReq.class);
		} catch (Exception e) {
			e.printStackTrace();
			medEquipReqs = Collections.emptyList();
		}

		ObservableList<MedEquipReq> locationsObservable = FXCollections.observableList(medEquipReqs);

		TableColumn<MedEquipReq, String> requestNum = new TableColumn<>("Request Number");
		TableColumn<MedEquipReq, String> roomID = new TableColumn<>("Room ID");
		TableColumn<MedEquipReq, String> patientName = new TableColumn<>("Target Node ID");
		TableColumn<MedEquipReq, String> assignedNurse = new TableColumn<>("Assigned Nurse");
		TableColumn<MedEquipReq, String> requestStatus = new TableColumn<>("Request Status");
		TableColumn<MedEquipReq, String> additionalNotes = new TableColumn<>("Additional Notes");
		TableColumn<MedEquipReq, String> equipmentTypeSelected = new TableColumn<>("Equipment Type");

		requestNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));
		roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
		assignedNurse.setCellValueFactory(new PropertyValueFactory<>("assignedNurse"));
		requestStatus.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
		additionalNotes.setCellValueFactory(new PropertyValueFactory<>("additionalNotes"));
		equipmentTypeSelected.setCellValueFactory(new PropertyValueFactory<>("equipmentTypeSelected"));

		medEquipReqTableView.getColumns().add(requestNum);
		medEquipReqTableView.getColumns().add(roomID);
		medEquipReqTableView.getColumns().add(patientName);
		medEquipReqTableView.getColumns().add(assignedNurse);
		medEquipReqTableView.getColumns().add(requestStatus);
		medEquipReqTableView.getColumns().add(additionalNotes);
		medEquipReqTableView.getColumns().add(equipmentTypeSelected);
		medEquipReqTableView.setItems(locationsObservable);

		sidebar = SidebarUtil.initializeSidebar(sidebarPane);
		openSidebarLayout();
	}

	// back button
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

	public static class ChangeTheme {
		// Base pane for displaying new scenes
		@FXML private Pane mapPane;
		// Sidebar
		@FXML private Pane sidebarPane;
		@FXML private JFXButton closeSidebarHiddenButton;
		@FXML private JFXHamburger sidebarHamburger;
		AnchorPane sidebar = null;

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
	}
}
