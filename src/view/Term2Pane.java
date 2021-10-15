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

public class Term2Pane extends GridPane {

	private static ListView<Module> listView;
	private static ListView<Module> listView2;
	private static Button btnAdd;
	private static Button btnRemove;
	private static Button btnConfirm;

	public Term2Pane () {

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
		
		Label UST2M = new Label("Unselected Term 2 Modules");

		Label RT2M = new Label("Reserved Term 2 Modules");

		listView = new ListView<Module>();
		listView.setPrefSize(500, 300);
		VBox.setVgrow(listView, Priority.ALWAYS);

		listView2 = new ListView<Module>();
		listView2.setPrefSize(500, 300);
		VBox.setVgrow(listView2, Priority.ALWAYS);

		VBox lbl = new VBox(UST2M, listView);
		lbl.setSpacing(10);
		lbl.setAlignment(Pos.TOP_LEFT);
		this.add(lbl, 0, 0);

		VBox list = new VBox(RT2M, listView2);
		list.setSpacing(10);
		list.setAlignment(Pos.TOP_LEFT);
		this.add(list, 1, 0);

		Label l1 = new Label("Reserve 30 credits worth of term 2 modules");
		
		HBox label1 = new HBox(l1);
		label1.setAlignment(Pos.TOP_RIGHT);
		this.add(label1, 0, 1);

		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");

		HBox lblBtn = new HBox(btnAdd, btnRemove, btnConfirm);
		lblBtn.setSpacing(15);
		btnConfirm.setDisable(true);
		lblBtn.setAlignment(Pos.TOP_LEFT);
		this.add(lblBtn, 1, 1);
		
		//this.setGridLinesVisible(true);
	}

	//count number of modules in listView
	public static int CountModules() {
		return listView2.getItems().size();
	}
	
	//set the button to enabled
	public static void enableButton() {
		btnConfirm.setDisable(false);
	}
	
	//Methods to add modules
	public static void addReserveT2Modules(ObservableList<Module> m) {
		listView.setItems(m);
	}

	public static void addSelectedReservedT2Module() {
		listView2.getItems().add(listView.getSelectionModel().getSelectedItem());
	}

	public static void addSelectedReserveT2ModuleBack() {
		listView.getItems().add(listView2.getSelectionModel().getSelectedItem());
	}
	
	
	//methods to remove modules
	public static void removeSelectedReserveT2Module() {
		listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
	}

	public static void removeReserveT2Module() {
		listView2.getItems().remove(listView2.getSelectionModel().getSelectedItem());
	}
	
	
	//methods to get selected module
	public static Module getSelectedT2Module() {
		return listView2.getSelectionModel().getSelectedItem();
	}

	
	//method to get all modules in listView2
	public static ObservableList<Module> getReservedTerm2Modules() {
		return listView2.getItems();
	}
	
	
	//method to set a module
	public static void setSavedModule(Module m) {
		listView2.getItems().add(m);
	}

	
	//checking if empty
	public static boolean isReservedTerm2ModuleSelected() {
		return listView.getSelectionModel().isEmpty();
	}
	
	public static boolean isReservedTerm2ModuleSelected2() {
		return listView2.getSelectionModel().isEmpty();
	}

	
	//event handlers
	public static void addSelectedTerm1ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}

	public static void removeSelectedTerm1ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}

	public static void confirmSelectedTerm2ReserveModuleHandler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}


}
