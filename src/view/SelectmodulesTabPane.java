package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Module;


public class SelectmodulesTabPane extends GridPane {
	
	private ListView<Module> listView;
	private ListView<Module> listView2;
	private ListView<Module> listView3;
	private ListView<Module> listView4;
	private ListView<Module> listView5;
	private Button btnAdd;
	private Button btnRemove;
	private Button Reset;
	private Button btnAdd2;
	private Button btnRemove2;
	private Button Submit;
	private TextField txt; 
	private TextField txt2; 
	private int credit;
	private int credit2;
	
	public SelectmodulesTabPane() {
		
		this.setAlignment(Pos.CENTER);
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
		
		RowConstraints row2 = new RowConstraints();
		row2.setValignment(VPos.CENTER);
		row2.setVgrow(Priority.ALWAYS);

		this.getRowConstraints().addAll(row, row1, row2);

		this.getColumnConstraints().addAll(column0, column1);
		
		Label USM1 = new Label("Unselected Term 1 modules");
		
		Label T1 = new Label("Term 1");
		Label T2 = new Label("Term 2");
		
		Label USM2 = new Label("Unselected Term 2 modules");
		
		Label CT1C = new Label("Current Term 1 credits: ");
		Label CT2C = new Label("Current Term 2 credits: ");
		
		Label SYLM = new Label("Selected Year Long modules");
		Label ST1M = new Label("Selected Term 1 modules");
		Label ST2M = new Label("Selected Term 2 modules");
		
		txt = new TextField();
		txt.setText("0");
		txt.setPrefSize(50, 10);
		txt.setEditable(false);
		VBox.setVgrow(txt, Priority.ALWAYS);
		
		txt2 = new TextField();
		txt2.setText("0");
		txt2.setPrefSize(50, 10);
		txt2.setEditable(false);
		VBox.setVgrow(txt2, Priority.ALWAYS);
		
		listView = new ListView<Module>();
		listView.setPrefSize(400, 50);
		listView.setFixedCellSize(30);
		VBox.setVgrow(listView, Priority.ALWAYS);

		
		listView2 = new ListView<Module>();
		listView2.setPrefSize(400, 150);
		listView2.setFixedCellSize(30);
		VBox.setVgrow(listView2, Priority.ALWAYS);

		
		listView3 = new ListView<Module>();
		listView3.setPrefSize(400, 80);
		listView3.setFixedCellSize(30);
		//VBox.setVgrow(listView3, Priority.ALWAYS);
		
		listView4 = new ListView<Module>();
		listView4.setPrefSize(400, 150);
		listView4.setFixedCellSize(30);
		VBox.setVgrow(listView4, Priority.ALWAYS);
		
		listView5 = new ListView<Module>();
		listView5.setPrefSize(400, 150);
		listView5.setFixedCellSize(30);
		VBox.setVgrow(listView5, Priority.ALWAYS);
		
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnAdd2 = new Button("Add");
		btnRemove2 = new Button("Remove");
		Reset = new Button("Reset");
		Reset.setPrefSize(70, 30);
		Reset.setAlignment(Pos.CENTER);
		Submit = new Button("Submit");
		Submit.setPrefSize(80, 30);
		Submit.setAlignment(Pos.CENTER);
		
		VBox UST1M = new VBox(USM1, listView);
		UST1M.setSpacing(5);
		UST1M.setAlignment(Pos.TOP_LEFT);
		this.add(UST1M, 0, 0);
		
		HBox btnAndLbl = new HBox(T1, btnAdd, btnRemove);
		btnAndLbl.setSpacing(15);
		btnAndLbl.setAlignment(Pos.CENTER);
		this.add(btnAndLbl, 0, 1);
		
		VBox UST2M = new VBox(USM2, listView2);
		UST2M.setSpacing(5);
		UST2M.setAlignment(Pos.BOTTOM_LEFT);
		this.add(UST2M, 0, 2);
	
		
		HBox btnAndLbl2 = new HBox(T2, btnAdd2, btnRemove2);
		btnAndLbl2.setSpacing(15);
		btnAndLbl2.setAlignment(Pos.CENTER);
		VBox.setVgrow(btnAndLbl2, Priority.ALWAYS);
		this.add(btnAndLbl2, 0, 3);
		
		HBox btnAndLbl3 = new HBox(CT1C, txt);
		btnAndLbl3.setSpacing(10);
		btnAndLbl3.setAlignment(Pos.CENTER);
		VBox.setVgrow(btnAndLbl3, Priority.ALWAYS);
		this.add(btnAndLbl3, 0, 5);
		
		VBox SYL = new VBox(SYLM, listView3);
		SYL.setSpacing(5);
		SYL.setAlignment(Pos.TOP_LEFT);
		//this.add(SYL, 1, 0);
		
		VBox ST1 = new VBox(SYL, ST1M, listView4);
		ST1.setSpacing(5);		
		ST1.setAlignment(Pos.CENTER_LEFT);
		GridPane.setRowSpan(ST1, 2);
		this.add(ST1, 1, 0);
		
		VBox ST2 = new VBox(ST2M, listView5);
		ST2.setSpacing(5);
		ST2.setAlignment(Pos.BOTTOM_LEFT);
		this.add(ST2, 1, 2);
		
		HBox btnAndLbl4 = new HBox(CT2C, txt2);
		btnAndLbl4.setSpacing(10);
		btnAndLbl4.setAlignment(Pos.CENTER);
		VBox.setVgrow(btnAndLbl4, Priority.ALWAYS);
		this.add(btnAndLbl4, 1, 3);
		
		HBox buttons = new HBox(Reset, Submit);
		buttons.setSpacing(80);
		buttons.setAlignment(Pos.CENTER);
		GridPane.setColumnSpan(buttons, 2);
		this.add(buttons, 0, 6);
			
		//this.setGridLinesVisible(true);
		
	}
	
	//check selected modules
	public boolean isTerm1ModuleSelected() {
		return listView.getSelectionModel().isEmpty();
	}
	
	public boolean isTerm2ModuleSelected() {
		return listView2.getSelectionModel().isEmpty();
	}
	
	public boolean isTerm1ModuleSelected2() {
		return listView4.getSelectionModel().isEmpty();
	}
	
	public boolean isTerm2ModuleSelected2() {
		return listView5.getSelectionModel().isEmpty();
	}
	
	
	//Methods for getting module/modules
	public void getT1Modules() {
		listView.getSelectionModel().getSelectedItem();
	}
	
	public void getTerm2Modules() {
		listView2.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedT1Module() {
		return listView4.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedT2Module() {
		return listView5.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module> getAllSelectedT1Modules() {
		return listView.getItems();
	}
	
	public ObservableList<Module> getAllSelectedT2Modules() {
		return listView2.getItems();
	}
	
	public ObservableList<Module> getTerm1SelectedModules() {
		return listView4.getItems();
	}
	
	public ObservableList<Module> getTerm2SelectedModules() {
		return listView5.getItems();
	}
	
	public ObservableList<Module> getYearLongModule() {
		return listView3.getItems();
	}

	
	
	
	//Methods for removing the modules
	public void removeTerm1Module() {
		listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
	}
	
	public void removeSelectedT2Module() {
		listView5.getItems().remove(listView5.getSelectionModel().getSelectedItem());
	}
	
	public void removeTerm2Module() {
		listView2.getItems().remove(listView2.getSelectionModel().getSelectedItem());
	}
	
	public void removeSelectedT1Module() {
		listView4.getItems().remove(listView4.getSelectionModel().getSelectedItem());
	}
	
	
	//resets the select modules tab
	public void reset() {
		listView.getItems().addAll(listView4.getItems());
		listView2.getItems().addAll(listView5.getItems());
		listView4.getItems().clear();
		listView5.getItems().clear();
	}
	
	
	//Function for adding modules to and from the list views
	public void addSelectedT1ModuleBack() {
		listView.getItems().add(listView4.getSelectionModel().getSelectedItem());
	}
	
	public void addSelectedT2ModuleBack() {
		listView2.getItems().add(listView5.getSelectionModel().getSelectedItem());
	}
	
	public void addUST1M(Module module) {
		listView.getItems().add(module);
	}
	
	public void addUST2M(Module module) {
		listView2.getItems().add(module);
	}
	
	public void addSYLM(Module module) {
		listView3.getItems().add(module);
	}
	
	public void addMandatoryT1Module(Module module) {
		listView4.getItems().add(module);
	}
	
	public void addMandatoryT2Module(Module module) {	
		listView5.getItems().add(module);
	}
	
	public void addST1() {
		listView4.getItems().add(listView.getSelectionModel().getSelectedItem());
	}
	
	public void addST2() {
		listView5.getItems().add(listView2.getSelectionModel().getSelectedItem());
	}
	
	
	//Getting the credits added and removed for each term and total
	public int getCredit() {
		return credit;
	}
	
	public void addCredit() {
		credit = credit + 15;
	}
	
	public void removeCredit() {
		credit = credit - 15;
	}
	
	public void updateTextField() {
		txt.setText(String.valueOf(credit));
	}
	
	public int getCredit2() {
		return credit2;
	}

	public void addCredit2() {
		credit2 = credit2 + 15;
	}
	
	public void removeCredit2() {
		credit2 = credit2 - 15;
	}
	
	public void updateTextField2() {
		txt2.setText(String.valueOf(credit2));
	}
	
	public int getTotalCredit() {
		int i = Integer.parseInt(txt.getText()) + Integer.parseInt(txt2.getText());
		return i;
	}
	
	//set modules
	public void setSelectedModules (ObservableList<Module> m) {
		listView.setItems(m);
		listView2.setItems(m);
		listView3.setItems(m);
		listView4.setItems(m);
		listView5.setItems(m);
	}
	
	
	
	//Event Handlers
	public void addSelectedTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	
	public void addSelectedTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		btnAdd2.setOnAction(handler);
	}
	
	public void RemoveSelectedT1ModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public void RemoveSelectedT2ModuleHandler(EventHandler<ActionEvent> handler) {
		btnRemove2.setOnAction(handler);
	}
	
	public void ResetHandler(EventHandler<ActionEvent> handler) {
		Reset.setOnAction(handler);
	}
	
	public void SubmitHandler(EventHandler<ActionEvent> handler) {
		Submit.setOnAction(handler);
	}

}
