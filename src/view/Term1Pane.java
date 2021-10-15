package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Module;


public class Term1Pane extends GridPane {
	
	private static ListView<Module> listView1;
	private static ListView<Module> listViewReserved;
	private static Button btnAdd;
	private static Button btnRemove;
	private static Button btnConfirm;
	
	public Term1Pane () {
		
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);
		column0.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHalignment(HPos.RIGHT);
		column1.setHgrow(Priority.ALWAYS);
		
		RowConstraints row = new RowConstraints();
		row.setValignment(VPos.CENTER);
		row.setVgrow(Priority.ALWAYS);
		
		RowConstraints row1 = new RowConstraints();
		row1.setValignment(VPos.CENTER);
		row1.setVgrow(Priority.ALWAYS);
		
		this.getRowConstraints().addAll(row);

		this.getColumnConstraints().addAll(column0, column1);
		
		Label UST1M = new Label("Unselected Term 1 Modules");

		Label RT1M = new Label("Reserved Term 1 Modules");
		
		listView1 = new ListView<Module>();
		listView1.setPrefSize(400, 400);
		listView1.setFixedCellSize(30);
		VBox.setVgrow(listView1, Priority.ALWAYS);
		
		listViewReserved = new ListView<Module>();
		listViewReserved.setPrefSize(400, 400);
		listViewReserved.setFixedCellSize(30);
		VBox.setVgrow(listViewReserved, Priority.ALWAYS);
		
		VBox lbl = new VBox(UST1M, listView1);
		lbl.setSpacing(10);
		lbl.setAlignment(Pos.TOP_LEFT);
		this.add(lbl, 0, 0);
		
		VBox list = new VBox(RT1M, listViewReserved);
		list.setSpacing(10);
		list.setAlignment(Pos.TOP_LEFT);
		this.add(list, 1, 0);
		
		Label l1 = new Label("Reserve 30 credits worth of term 1 modules");
		
		HBox label1 = new HBox(l1);
		label1.setAlignment(Pos.TOP_RIGHT);
		this.add(label1, 0, 1);
		
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");
		
		HBox lblBtn = new HBox(btnAdd, btnRemove, btnConfirm);
		lblBtn.setSpacing(15);
		lblBtn.setAlignment(Pos.TOP_LEFT);
		btnConfirm.setDisable(true);
		this.add(lblBtn, 1, 1);
	}

	//count the number of modules in the listview
	public static int CountModules() {
		return listViewReserved.getItems().size();
	}
	
	//set the button to enabled
	public static void enableButton() {
		btnConfirm.setDisable(false);
	}
	
	//methods to add modules
	public static void addReserveTerm1Modules(ObservableList<Module> modules) {
		listView1.setItems(modules);
	}
	
	public static void addSelectedReservedT1Module() {
		listViewReserved.getItems().add(listView1.getSelectionModel().getSelectedItem());
	}
	
	public static void addSelectedReserveT1ModuleBack() {
		listView1.getItems().add(listViewReserved.getSelectionModel().getSelectedItem());
	}
	
	
	//methods to remove modules
	public static void removeSelectedReserveT1Module() {
		listView1.getItems().remove(listView1.getSelectionModel().getSelectedItem());
	}
	
	public static void removeReserveT1Module() {
		listViewReserved.getItems().remove(listViewReserved.getSelectionModel().getSelectedItem());
	}
	
	
	//methods for getting modules
	public static Module getSelectedT1Module() {
		return listViewReserved.getSelectionModel().getSelectedItem();
	}
	
	public static ObservableList<Module> getReservedTerm1Modules() {
		return listViewReserved.getItems();
	}
	
	public static ObservableList<Module> getModules() {
		return listView1.getItems();
	}
	
	
	//method to set module
	public static void setSavedModule(Module m) {
		listViewReserved.getItems().add(m);
	}
	
	
	//methods to check if a list view is empty
	public static boolean isReservedTerm1ModuleSelected() {
		return listView1.getSelectionModel().isEmpty();
	}
	
	public static boolean isReservedTerm1ModuleSelected2() {
		return listViewReserved.getSelectionModel().isEmpty();
	}
	
	
	
	//event handlers
	public static void addSelectedTerm1ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}

	public static void removeSelectedTerm1ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public static void confirmSelectedTerm1ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
}
